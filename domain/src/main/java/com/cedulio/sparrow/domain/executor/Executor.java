package com.cedulio.sparrow.domain.executor;

import com.cedulio.sparrow.domain.interactor.base.AbstractAsyncUseCase;

/**
 * Created by cedulio on 07/02/16.
 */
public interface Executor {

    void execute(final AbstractAsyncUseCase interactor);
}
