package com.sdchang.permissionpolice.demo;

import android.Manifest;
import android.content.Context;
import com.sdchang.permissionpolice.lib.registry.PermissionUsageReceiver;

/**
 *
 */
public class DemoPermissionUsageReceiver extends PermissionUsageReceiver {

    @Override
    protected void setupPermissionUsage(Context context) {
        usesPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        usesPermission(Manifest.permission.READ_CONTACTS);
        usesPermission(Manifest.permission.READ_PHONE_STATE);
        usesPermission(Manifest.permission.ACCESS_WIFI_STATE);
    }
}