package io.github.cavarzan.devicemagic.domain.usecases.items;

import io.github.cavarzan.devicemagic.domain.repository.items.DownloadRepository;
import io.github.cavarzan.devicemagic.model.Download;
import io.github.cavarzan.devicemagic.util.CallInExecutorThanMainThread;
import rx.Scheduler;
import io.github.cavarzan.devicemagic.domain.interactors.base.BaseUseCase;
import rx.Observable;

public class GetItemByUidUseCaseImpl extends BaseUseCase implements GetItemByUidUseCase {

    private final DownloadRepository repository;

    public GetItemByUidUseCaseImpl(Scheduler executor, DownloadRepository repository) {
        super(executor);
        this.repository = repository;
    }

    @Override
    public Observable<Download> invoke(String uid) {
        return repository
                .findOne(uid)
                .compose(new CallInExecutorThanMainThread<>(executor));
    }

}
