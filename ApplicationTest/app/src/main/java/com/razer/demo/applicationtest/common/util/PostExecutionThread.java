package com.razer.demo.applicationtest.common.util;


import rx.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
