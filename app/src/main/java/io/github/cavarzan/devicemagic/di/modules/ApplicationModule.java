package io.github.cavarzan.devicemagic.di.modules;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.cavarzan.devicemagic.application.App;
import io.github.cavarzan.devicemagic.di.ForApplication;
import io.github.cavarzan.devicemagic.domain.executors.JobExecutor;
import io.github.cavarzan.devicemagic.domain.executors.ThreadExecutor;
import io.github.cavarzan.devicemagic.domain.repository.items.DownloadRepository;
import io.github.cavarzan.devicemagic.domain.repository.items.DownloadRepositoryImpl;
import io.github.cavarzan.devicemagic.domain.repository.items.ItemRemoteRepository;
import io.github.cavarzan.devicemagic.domain.usecases.items.GetItemByUidUseCase;
import io.github.cavarzan.devicemagic.domain.usecases.items.GetItemByUidUseCaseImpl;
import io.github.cavarzan.devicemagic.domain.usecases.items.GetItemsUseCase;
import io.github.cavarzan.devicemagic.domain.usecases.items.GetItemsUseCaseImpl;
import okhttp3.OkHttpClient;
import rx.Scheduler;
import rx.schedulers.Schedulers;
// android-hipster-needle-module-provides-import

@Module
public class ApplicationModule {

    public static final String MAIN_THREAD_HANDLER = "main_thread_handler";

    protected App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @ForApplication
    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @ForApplication
    @Provides
    @Singleton
    public App provideApp() {
        return application;
    }

    @Provides
    @Singleton
    public Scheduler provideScheduler() {
        return Schedulers.from(new JobExecutor());
    }

    @ForApplication
    @Provides
    @Singleton
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public ThreadExecutor provideThreadExecutor() {
        return new JobExecutor();
    }

    @Provides @NonNull @Named(MAIN_THREAD_HANDLER) @Singleton
    public Handler provideMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    @Singleton
    GetItemsUseCase provideGetItemsUseCase(Scheduler executor, DownloadRepository repository) {
       return new GetItemsUseCaseImpl(executor, repository);
    }
    @Provides
    @Singleton
    DownloadRepository provideItemRepository(OkHttpClient client) {
       return new DownloadRepositoryImpl(new ItemRemoteRepository(client));
    }
    @Provides
    @Singleton
    GetItemByUidUseCase provideGetItemByUidUseCase(Scheduler executor, DownloadRepository repository) {
       return new GetItemByUidUseCaseImpl(executor, repository);
    }
    // android-hipster-needle-module-provides-method

}
