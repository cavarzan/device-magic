package io.github.cavarzan.devicemagic.di.modules;

import dagger.Module;
import dagger.Provides;
import io.github.cavarzan.devicemagic.di.ActivityScope;
import io.github.cavarzan.devicemagic.ui.base.BaseActivity;

@ActivityScope
@Module
public class ActivityModule {

    protected BaseActivity baseActivity;

    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    protected BaseActivity activity() {
        return baseActivity;
    }
}
