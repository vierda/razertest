package com.razer.demo.applicationtest.common.util;


import rx.Scheduler;
import rx.schedulers.Schedulers;

public class BackgroundThread implements PostExecutionThread{

    @Override
    public Scheduler getScheduler() {
        return Schedulers.computation();
    }
}
