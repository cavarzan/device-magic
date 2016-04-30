package io.github.cavarzan.devicemagic.domain.repository.items;

import io.github.cavarzan.devicemagic.domain.entity.converter.DownloadConverter;
import io.github.cavarzan.devicemagic.model.Download;
import rx.Observable;

public class DownloadRepositoryImpl implements DownloadRepository {

    ItemRemoteRepository remoteRepository;

    public DownloadRepositoryImpl(ItemRemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    public Observable<Download> findAll() {
        return remoteRepository
                .findAll()
                .map(DownloadConverter::wrapDownloads);
    }

    @Override
    public Observable<Download> findOne(String uid) {
        return remoteRepository
                .findOne(uid)
                .map(DownloadConverter::wrap);
    }
}
