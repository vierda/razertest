package com.razer.demo.applicationtest.common.util;


public abstract class DefaultSubscriber<T> extends rx.Subscriber<T> {

    public abstract void onCompleted();
    public abstract void onError(Throwable e);
    public abstract void onNext(T t);
}

