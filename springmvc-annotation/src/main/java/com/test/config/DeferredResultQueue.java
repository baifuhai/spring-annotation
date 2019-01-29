package com.test.config;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DeferredResultQueue {

    private static Queue<DeferredResult<String>> queue = new ConcurrentLinkedQueue<>();

    public static void add(DeferredResult<String> deferredResult){
        queue.add(deferredResult);
    }

    public static DeferredResult<String> get(){
        return queue.poll();
    }

}
