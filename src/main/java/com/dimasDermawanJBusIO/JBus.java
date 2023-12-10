package com.dimasDermawanJBusIO;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import com.dimasDermawanJBusIO.controller.BasicGetController;
import com.dimasDermawanJBusIO.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JBus {
    public static void main(String[] args) {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(JsonDBEngine::join));
    }
}
