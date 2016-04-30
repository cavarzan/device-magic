package io.github.cavarzan.devicemagic.di.components;

import dagger.Component;
import io.github.cavarzan.devicemagic.di.ActivityScope;
import io.github.cavarzan.devicemagic.di.modules.MainModule;
import io.github.cavarzan.devicemagic.ui.main.MainActivity;
import io.github.cavarzan.devicemagic.ui.main.MainFragment;

@ActivityScope
@Component(dependencies = {ApplicationComponent.class}, modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity activity);
    void inject(MainFragment fragment);

}
