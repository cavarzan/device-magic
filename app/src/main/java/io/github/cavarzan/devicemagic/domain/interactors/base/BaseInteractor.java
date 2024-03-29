package io.github.cavarzan.devicemagic.domain.interactors.base;

import rx.Scheduler;

public abstract class BaseInteractor {

    protected Scheduler executor;

    public BaseInteractor(Scheduler executor) {
        this.executor = executor;
    }

}
