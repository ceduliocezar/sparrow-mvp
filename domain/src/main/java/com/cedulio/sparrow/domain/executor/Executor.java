package com.cedulio.sparrow.domain.executor;

import com.cedulio.sparrow.domain.interactor.base.AbstractUseCase;

public interface Executor {

    void execute(final AbstractUseCase interactor);
}
