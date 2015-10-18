package com.permissionnanny.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;

/**
 *
 */
public class BundleUtil {
    public static String toString(@Nullable Bundle bundle) {
        if (bundle == null) {
            return "null bundle";
        }

        JSONObject json = new JSONObject();
        try {
            for (String key : bundle.keySet()) {
                Object val = bundle.get(key);
                Object str = val;
                if (val != null && val.getClass().isArray()) {
                    str = Arrays.deepToString((Object[]) val);
                } else if (val instanceof Bundle) {
                    str = BundleUtil.toString((Bundle) val);
                }
                json.put(key, str);
            }
            return json.toString(4);
        } catch (JSONException e) {
            Timber.e(e, "Error parsing bundle to json.");
            return "Error parsing bundle to json.";
        }
    }

    public static String toString(@Nullable Intent intent) {
        if (intent == null) {
            return "null intent";
        }
        return toString(intent.getExtras());
    }
}
