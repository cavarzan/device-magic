package io.github.cavarzan.devicemagic.environment;

import android.app.Application;
import android.os.StrictMode;

import io.github.cavarzan.devicemagic.BuildConfig;
import io.github.cavarzan.devicemagic.di.ForApplication;

import javax.inject.Inject;
import javax.inject.Singleton;
import rx.schedulers.Schedulers;

import timber.log.Timber;
import io.github.cavarzan.devicemagic.util.logging.CrashReportingTree;


@Singleton
public class EnvironmentConfiguration {

    @Inject
    @ForApplication
    Application app;

    @Inject
    public EnvironmentConfiguration() {
    }

    public void configure() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

}
