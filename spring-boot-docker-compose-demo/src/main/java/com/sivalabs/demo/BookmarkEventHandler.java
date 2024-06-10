package com.sivalabs.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BookmarkEventHandler {
    private static final Logger log = LoggerFactory.getLogger(BookmarkEventHandler.class);

    @KafkaListener(topics = "demo-topic")
    public void handle(BookmarkCreatedEvent event) {
        log.info("Received BookmarkCreatedEvent:{}: ", event);
    }
}