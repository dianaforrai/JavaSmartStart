package com.techlogics.parking.service;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParkingStateStoreProcessor implements Processor<String, String, String, String> {

    private static final Logger logger = LoggerFactory.getLogger(ParkingStateStoreProcessor.class);

    private ProcessorContext<String, String> context;
    private KeyValueStore<String, String> stateStore;
    private final String stateStoreName;

    public ParkingStateStoreProcessor(String stateStoreName) {
        this.stateStoreName = stateStoreName;
    }

    @Override
    public void init(ProcessorContext<String, String> context) {
        this.context = context;
        this.stateStore = context.getStateStore(stateStoreName);
        logger.info("Initialized ParkingStateStoreProcessor with state store: {}", stateStoreName);
    }

    @Override
    public void process(Record<String, String> record) {
        String key = record.key();
        String value = record.value();

        // Store the current parking data in the state store
        stateStore.put(key, value);

        logger.debug("Stored parking data in state store - Key: {}, Value: {}", key, value);

        // Forward the record downstream (optional)
        context.forward(record);
    }

    @Override
    public void close() {
        logger.info("Closing ParkingStateStoreProcessor");
    }
}