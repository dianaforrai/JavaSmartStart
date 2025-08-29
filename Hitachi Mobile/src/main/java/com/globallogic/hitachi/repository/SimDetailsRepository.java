package com.globallogic.hitachi.repository;

import com.globallogic.hitachi.entity.SimDetails;
import com.globallogic.hitachi.exception.SIMDoesNotExistException;
import java.math.BigInteger;
import java.util.List;

public interface SimDetailsRepository {
    List<SimDetails> findByStatus(String status);
    SimDetails findBySimNumberAndServiceNumber(BigInteger simNumber, BigInteger serviceNumber) throws SIMDoesNotExistException;
    void updateSimStatus(Integer simId, String status) throws SIMDoesNotExistException;
}