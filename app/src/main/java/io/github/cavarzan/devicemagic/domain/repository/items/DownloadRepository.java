package io.github.cavarzan.devicemagic.domain.repository.items;

import io.github.cavarzan.devicemagic.model.Download;
import rx.Observable;

public interface DownloadRepository {

    Observable<Download> findAll();

    Observable<Download> findOne(String uid);
}
