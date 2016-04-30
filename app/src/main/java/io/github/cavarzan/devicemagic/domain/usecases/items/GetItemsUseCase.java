package io.github.cavarzan.devicemagic.domain.usecases.items;

import io.github.cavarzan.devicemagic.model.Download;
import rx.Observable;

public interface GetItemsUseCase {

    Observable<Download> invoke();

}
