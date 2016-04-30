package io.github.cavarzan.devicemagic.ui.main;

import io.github.cavarzan.devicemagic.di.ActivityScope;
import io.github.cavarzan.devicemagic.domain.usecases.items.GetItemByUidUseCase;
import io.github.cavarzan.devicemagic.domain.usecases.items.GetItemsUseCase;
import io.github.cavarzan.devicemagic.ui.base.BasePresenter;
import rx.Observable;
import rx.functions.Action1;

import javax.inject.Inject;

@ActivityScope
public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    GetItemsUseCase itemsUseCase;

    @Inject
    GetItemByUidUseCase getItemByUidUseCase;

    @Inject
    public MainPresenter() {
    }

    @SuppressWarnings("ConstantConditions")
    public void load() {
        add(
                itemsUseCase
                        .invoke()
                        .flatMap(o -> Observable.from(o.uids()))
                        .flatMap(uid -> getItemByUidUseCase.invoke(uid))
                        .toList()
                        .compose(deliverFirst())
                        .subscribe(split(
                                (view, list) -> {
                                    view.requestFinished();
                                    if (list.isEmpty()) {
                                        view.emptyResult();
                                    } else {
                                        view.dataAvailable(list);
                                    }

                                },
                                (view, throwable) -> {
                                    throwable.printStackTrace();
                                    view.requestFinished();
                                    view.dataLoadError();
                                }
                        ), Throwable::printStackTrace)

        );
    }
}
