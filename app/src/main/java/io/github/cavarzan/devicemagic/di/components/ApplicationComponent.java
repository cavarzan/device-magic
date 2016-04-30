package io.github.cavarzan.devicemagic.di.components;

import android.content.Context;


import javax.inject.Singleton;

import dagger.Component;
import io.github.cavarzan.devicemagic.application.App;
import io.github.cavarzan.devicemagic.di.ForApplication;
import io.github.cavarzan.devicemagic.di.modules.ApplicationModule;
import io.github.cavarzan.devicemagic.domain.repository.items.DownloadRepository;
import io.github.cavarzan.devicemagic.domain.usecases.items.GetItemByUidUseCase;
import io.github.cavarzan.devicemagic.domain.usecases.items.GetItemsUseCase;
import io.github.cavarzan.devicemagic.environment.EnvironmentModule;
// android-hipster-needle-component-injection-import

@Singleton
@Component(modules = {
        ApplicationModule.class,
        EnvironmentModule.class}
)
public interface ApplicationComponent {

    @ForApplication
    Context provideContext();

    void inject(App app);

    GetItemsUseCase provideGetItemsUseCase();

    DownloadRepository provideItemRepository();

    GetItemByUidUseCase provideGetItemByUidUseCase();
    // android-hipster-needle-component-injection-method

    final class Initializer {
        public static ApplicationComponent init(App app) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(app))
                    .environmentModule(new EnvironmentModule(app))
                    .build();
        }
    }

}
