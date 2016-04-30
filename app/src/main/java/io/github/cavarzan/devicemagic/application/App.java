package io.github.cavarzan.devicemagic.application;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import io.github.cavarzan.devicemagic.di.ForApplication;
import io.github.cavarzan.devicemagic.di.components.ApplicationComponent;
import io.github.cavarzan.devicemagic.environment.EnvironmentConfiguration;

public class App extends Application {

    protected ApplicationComponent graph;

    @ForApplication
    @Inject
    protected Context context;

    @Inject
    protected EnvironmentConfiguration environmentConfiguration;

    public void onCreate() {
        super.onCreate();
        graph = createComponent();
        environmentConfiguration.configure();
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (graph == null) {
            createComponent();
        }
        return graph;
    }

    protected ApplicationComponent createComponent() {
        graph = ApplicationComponent.Initializer.init(this);
        graph.inject(this);
        return graph;
    }

}
