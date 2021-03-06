package com.permissionnanny.simple;

import android.Manifest;
import android.content.Context;
import android.content.pm.PermissionInfo;
import android.location.LocationManager;
import android.os.Bundle;
import com.permissionnanny.ProxyFunction;
import com.permissionnanny.R;
import com.permissionnanny.lib.request.RequestParams;
import com.permissionnanny.lib.request.simple.LocationRequest;

import javax.inject.Inject;

/**
 *
 */
public class LocationOperation {
    public static final SimpleOperation[] operations = new SimpleOperation[]{
            new SimpleOperation(LocationRequest.ADD_GPS_STATUS_LISTENER,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    PermissionInfo.PROTECTION_DANGEROUS,
                    R.string.dialogTitle_locationAddGpsStatusListener, 3, null),
            new SimpleOperation(LocationRequest.ADD_NMEA_LISTENER,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    PermissionInfo.PROTECTION_DANGEROUS,
                    R.string.dialogTitle_locationAddNmeaListener, 5, null),
            new SimpleOperation(LocationRequest.ADD_PROXIMITY_ALERT,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    PermissionInfo.PROTECTION_DANGEROUS,
                    R.string.dialogTitle_locationAddProximityAlert, 1, new ProxyFunction() {
                @Override
                public void execute(Context context, RequestParams request, Bundle response) {
                    LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                    mgr.addProximityAlert(request.double0, request.double1, request.float0,
                            request.long0, request.pendingIntent0);
                }
            }),
            new SimpleOperation(LocationRequest.GET_LAST_KNOWN_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    PermissionInfo.PROTECTION_DANGEROUS,
                    R.string.dialogTitle_locationGetLastKnownLocation, 1, new ProxyFunction() {
                @Override
                public void execute(Context context, RequestParams request, Bundle response) {
                    LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                    response.putParcelable(request.opCode, mgr.getLastKnownLocation(request.string0));
                }
            }),
            new SimpleOperation(LocationRequest.REMOVE_PROXIMITY_ALERT,
                    null,
                    PermissionInfo.PROTECTION_NORMAL,
                    R.string.dialogTitle_locationRemoveProximityAlert, 1, new ProxyFunction() {
                @Override
                public void execute(Context context, RequestParams request, Bundle response) {
                    LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                    mgr.removeProximityAlert(request.pendingIntent0);
                }
            }),
            new SimpleOperation(LocationRequest.REMOVE_UPDATES,
                    null,
                    PermissionInfo.PROTECTION_NORMAL,
                    R.string.dialogTitle_locationRemoveUpdates, 3, null),
            new SimpleOperation(LocationRequest.REQUEST_LOCATION_UPDATES,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    PermissionInfo.PROTECTION_DANGEROUS,
                    R.string.dialogTitle_locationRequestLocationUpdates, 9, new ProxyFunction() {
                @Override
                public void execute(Context context, RequestParams request, Bundle response) {
                    LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                    mgr.requestLocationUpdates(request.long0, request.float0, request.criteria0,
                            request.pendingIntent0);
                }
            }),
            new SimpleOperation(LocationRequest.REQUEST_LOCATION_UPDATES1,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    PermissionInfo.PROTECTION_DANGEROUS,
                    R.string.dialogTitle_locationRequestLocationUpdates, 9, null),
            new SimpleOperation(LocationRequest.REQUEST_LOCATION_UPDATES2,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    PermissionInfo.PROTECTION_DANGEROUS,
                    R.string.dialogTitle_locationRequestLocationUpdates, 9, null),
            new SimpleOperation(LocationRequest.REQUEST_LOCATION_UPDATES3,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    PermissionInfo.PROTECTION_DANGEROUS,
                    R.string.dialogTitle_locationRequestLocationUpdates, 9, null),
            new SimpleOperation(LocationRequest.REQUEST_SINGLE_UPDATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    PermissionInfo.PROTECTION_DANGEROUS,
                    R.string.dialogTitle_locationRequestLocationUpdates, 9, null),
            new SimpleOperation(LocationRequest.REQUEST_SINGLE_UPDATE1,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    PermissionInfo.PROTECTION_DANGEROUS,
                    R.string.dialogTitle_locationRequestLocationUpdates, 9, null),
            new SimpleOperation(LocationRequest.REQUEST_SINGLE_UPDATE2,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    PermissionInfo.PROTECTION_DANGEROUS,
                    R.string.dialogTitle_locationRequestLocationUpdates, 9, new ProxyFunction() {
                @Override
                public void execute(Context context, RequestParams request, Bundle response) {
                    LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                    mgr.requestSingleUpdate(request.string0, request.pendingIntent0);
                }
            }),
            new SimpleOperation(LocationRequest.REQUEST_SINGLE_UPDATE3,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    PermissionInfo.PROTECTION_DANGEROUS,
                    R.string.dialogTitle_locationRequestLocationUpdates, 9, new ProxyFunction() {
                @Override
                public void execute(Context context, RequestParams request, Bundle response) {
                    LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                    mgr.requestSingleUpdate(request.criteria0, request.pendingIntent0);
                }
            }),
    };

    public static SimpleOperation getOperation(String opCode) {
        for (SimpleOperation operation : operations) {
            if (operation.mOpCode.equals(opCode)) {
                return operation;
            }
        }
        return null;
    }

    @Inject
    public LocationOperation() {}
}
