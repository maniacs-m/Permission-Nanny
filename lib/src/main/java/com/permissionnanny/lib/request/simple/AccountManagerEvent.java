package com.permissionnanny.lib.request.simple;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.permissionnanny.lib.Err;
import com.permissionnanny.lib.Event;
import com.permissionnanny.lib.NannyBundle;
import com.permissionnanny.lib.PPP;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import timber.log.Timber;

/**
 *
 */
public class AccountManagerEvent<T> implements Event {

    @PPP public static final String FILTER = "AccountManagerEvent";

    @PPP public static final String CALLBACK = "callback";

    private Handler mHandler;
    private AccountManagerCallback<T> mCallback;
    private Class mType;

    public AccountManagerEvent(AccountManagerCallback<T> callback, @Nullable Handler handler, Class<T> type) {
        mHandler = handler != null ? handler : new Handler(Looper.getMainLooper());
        mCallback = callback;
        mType = type;
    }

    @Override
    public String filter() {
        return FILTER;
    }

    @Override
    public void process(Context context, Intent intent) {
        final Bundle entity = new NannyBundle(intent).getEntityBody();
        if (entity == null) {
            Timber.wtf(Err.NO_ENTITY);
            return;
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mCallback.run(new AccountManagerFuture<T>() {
                    @Override
                    public boolean cancel(boolean mayInterruptIfRunning) {
                        return false;
                    }

                    @Override
                    public boolean isCancelled() {
                        return false;
                    }

                    @Override
                    public boolean isDone() {
                        return true;
                    }

                    @Override
                    public T getResult() throws OperationCanceledException, IOException, AuthenticatorException {
                        Object value = entity.get(CALLBACK);
                        if (value != null && value.getClass() != mType) {
                            // TODO #75: Track server error!
                            return null;
                        }
                        return (T) value;
                    }

                    @Override
                    public T getResult(long timeout, TimeUnit unit)
                            throws OperationCanceledException, IOException, AuthenticatorException {
                        return getResult();
                    }
                });
            }
        });
    }
}
