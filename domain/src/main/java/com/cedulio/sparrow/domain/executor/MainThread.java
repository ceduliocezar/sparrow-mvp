package com.cedulio.sparrow.domain.executor;

public interface MainThread {
    void post(final Runnable runnable);
}
