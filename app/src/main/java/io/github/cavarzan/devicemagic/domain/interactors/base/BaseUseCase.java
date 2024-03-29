package io.github.cavarzan.devicemagic.domain.interactors.base;

import rx.Scheduler;

public abstract class BaseUseCase {

    protected Scheduler executor;

    public BaseUseCase(Scheduler executor) {
        this.executor = executor;
    }

}
