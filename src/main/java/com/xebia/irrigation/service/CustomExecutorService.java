package com.xebia.irrigation.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class CustomExecutorService {

    ThreadPoolExecutor threadPoolExecutor;
    ExecutorService executorService;

    public CustomExecutorService() {
        executorService = Executors.newFixedThreadPool(2);
    }


}
