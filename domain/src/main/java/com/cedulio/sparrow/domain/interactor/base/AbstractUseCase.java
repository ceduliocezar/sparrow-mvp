package com.cedulio.sparrow.domain.interactor.base;

import com.cedulio.sparrow.domain.executor.Executor;
import com.cedulio.sparrow.domain.executor.MainThread;

public abstract class AbstractUseCase implements UseCase {
    protected Executor threadExecutor;
    protected MainThread mainThread;

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    public AbstractUseCase(Executor threadExecutor, MainThread mainThread) {
        this.threadExecutor = threadExecutor;
        this.mainThread = mainThread;
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
        threadExecutor.execute(this);
    }

    public abstract void run();

    public MainThread getMainThread() {
        return mainThread;
    }
}
