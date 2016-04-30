package io.github.cavarzan.devicemagic.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;

import butterknife.ButterKnife;
import nucleus.view.NucleusAppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends NucleusAppCompatActivity<P> {

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        injectModule();
        ButterKnife.bind(this); 
        setPresenterFactory(getPresenterFactory()); 
    }

    protected abstract void injectModule();

    protected abstract int getLayoutResource();

    @CallSuper
    @Override
    public void onDestroy() {
        ButterKnife.unbind(this); 
        super.onDestroy();
    }

}
