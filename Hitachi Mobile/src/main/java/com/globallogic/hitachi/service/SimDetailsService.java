package com.globallogic.hitachi.service;

import com.globallogic.hitachi.entity.SimDetails;
import com.globallogic.hitachi.exception.SIMDoesNotExistException;
import com.globallogic.hitachi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class SimDetailsService {

    public List<SimDetails> getActiveSimDetails() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        List<SimDetails> activeSimDetails = null;

        try {
            transaction = session.beginTransaction();

            // Using Hibernate to fetch all SIM details
            Query<SimDetails> query = session.createQuery("FROM SimDetails", SimDetails.class);
            List<SimDetails> allSimDetails = query.getResultList();

            // Using Java 8 Streams and filters to get active SIM details
            activeSimDetails = allSimDetails.stream()
                    .filter(sim -> "active".equalsIgnoreCase(sim.getStatus()))
                    .collect(Collectors.toList());

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return activeSimDetails;
    }

    public String fetchSimStatus(BigInteger simNumber, BigInteger serviceNumber) throws SIMDoesNotExistException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        String status = null;

        try {
            transaction = session.beginTransaction();

            Query<SimDetails> query = session.createQuery(
                    "FROM SimDetails WHERE simNumber = :simNumber AND serviceNumber = :serviceNumber",
                    SimDetails.class
            );
            query.setParameter("simNumber", simNumber);
            query.setParameter("serviceNumber", serviceNumber);

            SimDetails simDetails = query.uniqueResult();

            if (simDetails == null) {
                throw new SIMDoesNotExistException("No SIM details found for given service and SIM number");
            }

            status = simDetails.getStatus();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (e instanceof SIMDoesNotExistException) {
                throw e;
            }
            e.printStackTrace();
        }

        return status;
    }

    public void updateSimStatusToActive(Integer simId) throws SIMDoesNotExistException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            SimDetails simDetails = session.get(SimDetails.class, simId);
            if (simDetails == null) {
                throw new SIMDoesNotExistException("No SIM details found for given Id");
            }

            simDetails.setStatus("active");
            session.update(simDetails);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (e instanceof SIMDoesNotExistException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}