package io.github.cavarzan.devicemagic.ui.main;

import io.github.cavarzan.devicemagic.di.components.MainComponent;
import io.github.cavarzan.devicemagic.ui.base.BaseFragment;
import io.github.cavarzan.devicemagic.ui.base.EmptyPresenter;
import io.github.cavarzan.devicemagic.R;

public class MainFragment extends BaseFragment<EmptyPresenter> {

    @Override
    protected void inject() {
        getComponent(MainComponent.class).inject(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    


}
