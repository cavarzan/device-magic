package io.github.cavarzan.devicemagic.domain.usecases.items;

import io.github.cavarzan.devicemagic.domain.interactors.base.BaseUseCase;
import io.github.cavarzan.devicemagic.domain.repository.items.DownloadRepository;
import io.github.cavarzan.devicemagic.model.Download;
import io.github.cavarzan.devicemagic.util.CallInExecutorThanMainThread;
import rx.Observable;
import rx.Scheduler;

public class GetItemsUseCaseImpl extends BaseUseCase implements GetItemsUseCase {

    private final DownloadRepository repository;

    public GetItemsUseCaseImpl(Scheduler executor, DownloadRepository repository) {
        super(executor);
        this.repository = repository;
    }

    @Override
    public Observable<Download> invoke() {
        return repository
                .findAll()
                .compose(new CallInExecutorThanMainThread<>(executor));
    }

}
