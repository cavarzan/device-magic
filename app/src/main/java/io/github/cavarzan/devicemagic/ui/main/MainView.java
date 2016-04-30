package io.github.cavarzan.devicemagic.ui.main;

import java.util.List;

import io.github.cavarzan.devicemagic.model.Download;
import io.github.cavarzan.devicemagic.ui.base.PresenterView;

public interface MainView extends PresenterView {

    void dataAvailable(List<Download> list);

    void dataLoadError();

    void executingRequest();

    void requestFinished();

    void emptyResult();
}
