package io.github.cavarzan.devicemagic.domain.usecases.items;

import io.github.cavarzan.devicemagic.model.Download;
import rx.Observable;

public interface GetItemByUidUseCase {

    Observable<Download> invoke(String uid);

}
