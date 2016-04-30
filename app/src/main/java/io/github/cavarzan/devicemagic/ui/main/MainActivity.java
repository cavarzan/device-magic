package io.github.cavarzan.devicemagic.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import io.github.cavarzan.devicemagic.di.ActivityScope;
import io.github.cavarzan.devicemagic.di.components.MainComponent;
import io.github.cavarzan.devicemagic.di.HasComponent;
import io.github.cavarzan.devicemagic.model.Download;
import io.github.cavarzan.devicemagic.ui.base.BaseActivity;
import io.github.cavarzan.devicemagic.R;
import io.github.cavarzan.devicemagic.application.App;
import io.github.cavarzan.devicemagic.di.components.DaggerMainComponent;
import io.github.cavarzan.devicemagic.di.modules.MainModule;

import nucleus.factory.PresenterFactory;

import javax.inject.Inject;

@ActivityScope
public class MainActivity extends BaseActivity<MainPresenter> implements MainView, HasComponent<MainComponent> {

    @Inject
    MainPresenter mainPresenter;

    MainComponent component;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.message_frame)
    View messageView;

    @Bind(R.id.message_text)
    TextView messageText;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private DownloadAdapter adapter;

    protected void injectModule() {
        component = DaggerMainComponent.builder().applicationComponent(App.get(this).getComponent()).mainModule(new MainModule(this)).build();
        component.inject(this);
    }

    public PresenterFactory<MainPresenter> getPresenterFactory() {
        return () -> mainPresenter;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getPresenter().load();
            executingRequest();
            configureRecyclerViewAndAdapter();
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setTitle(R.string.app_name);
            setSupportActionBar(toolbar);
        }
    }

    private void configureRecyclerViewAndAdapter() {
        swipeRefreshLayout.setOnRefreshListener(this::refresh);
        swipeRefreshLayout.setRefreshing(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DownloadAdapter();
        recyclerView.setAdapter(adapter);
    }

    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public MainComponent getComponent() {
        return component;
    }

    @Override
    public void dataAvailable(List<Download> list) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.clear();
        adapter.addAll(list);
    }

    @Override
    public void dataLoadError() {
        swipeRefreshLayout.setRefreshing(false);
        adapter.clear();
        messageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void executingRequest() {
        swipeRefreshLayout.setRefreshing(true);
        messageView.setVisibility(View.GONE);
        messageText.setText(R.string.no_data_available);
    }

    @OnClick(R.id.message_button)
    public void onClickTryAgain() {
        refresh();
    }

    private void refresh() {
        executingRequest();
        getPresenter().load();
    }

    @Override
    public void requestFinished() {
        messageView.setVisibility(View.GONE);
    }

    @Override
    public void emptyResult() {
        swipeRefreshLayout.setRefreshing(false);
        adapter.clear();
        messageView.setVisibility(View.VISIBLE);
        messageText.setText(R.string.no_data_available);
    }
}
