package io.github.cavarzan.devicemagic.environment;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.cavarzan.devicemagic.application.App;
import io.github.cavarzan.devicemagic.di.ForApplication;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@Module
public class EnvironmentModule {

    static final int DISK_CACHE_SIZE = (int) 1_000_000L;

    private final App app;

    public EnvironmentModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(@ForApplication Context app) {

        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        okHttpBuilder.cache(cache);
        okHttpBuilder.readTimeout(30, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(30, TimeUnit.SECONDS);
        okHttpBuilder.connectTimeout(30, TimeUnit.SECONDS);

        return okHttpBuilder.build();

    }

}
