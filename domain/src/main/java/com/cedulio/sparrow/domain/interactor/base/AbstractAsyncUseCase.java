package com.cedulio.sparrow.domain.interactor.base;

import com.cedulio.sparrow.domain.executor.Executor;
import com.cedulio.sparrow.domain.executor.MainThread;

public abstract class AbstractAsyncUseCase implements AsyncUseCase {
    protected Executor   mThreadExecutor;
    protected MainThread mMainThread;

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    public AbstractAsyncUseCase(Executor threadExecutor, MainThread mainThread) {
        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
    }

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public void onFinished() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    public void execute() {

        this.mIsRunning = true;
        mThreadExecutor.execute(this);
    }

    public abstract void run();
}
