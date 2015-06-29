package com.sdchang.permissionpolice.dagger;

import com.sdchang.permissionpolice.ConfirmRequestActivity;
import com.sdchang.permissionpolice.ProxyService;
import com.sdchang.permissionpolice.dagger.ActivityComponent.ActivityScope;
import com.sdchang.permissionpolice.missioncontrol.AppListActivity;
import dagger.Component;

import javax.inject.Scope;

/**
 *
 */
@ActivityScope
@Component(modules = {}, dependencies = AppComponent.class)
public interface ActivityComponent {

    @Scope
    @interface ActivityScope {}

    void inject(ConfirmRequestActivity victim);

    void inject(AppListActivity victim);

    void inject(ProxyService victim);
}