package io.github.cavarzan.devicemagic.di.modules;

import dagger.Module;
import dagger.Provides;
import  io.github.cavarzan.devicemagic.ui.main.MainActivity;

@Module
public class MainModule extends ActivityModule {

    public MainModule(MainActivity activity) {
        super(activity);
      }

}
