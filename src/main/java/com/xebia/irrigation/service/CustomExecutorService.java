package com.xebia.irrigation.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class CustomExecutorService {

    ThreadPoolExecutor threadPoolExecutor;
ExecutorService executorService;

    public CustomExecutorService() {
    //  threadPoolExecutor=  new ThreadPoolExecutor( 10, 20, 10000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
   executorService= (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    }


}
