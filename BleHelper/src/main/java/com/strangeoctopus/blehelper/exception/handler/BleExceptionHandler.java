package com.strangeoctopus.blehelper.exception.handler;

import com.strangeoctopus.blehelper.exception.BleException;
import com.strangeoctopus.blehelper.exception.ConnectException;
import com.strangeoctopus.blehelper.exception.GattException;
import com.strangeoctopus.blehelper.exception.InitiatedException;
import com.strangeoctopus.blehelper.exception.OtherException;
import com.strangeoctopus.blehelper.exception.TimeoutException;

/**
 * @Description: 异常处理
 * @author: strangeOctopus
 * @date: 16/8/14 10:35.
 */
public abstract class BleExceptionHandler {
    public BleExceptionHandler handleException(BleException exception) {
        if (exception != null) {
            if (exception instanceof ConnectException) {
                onConnectException((ConnectException) exception);
            } else if (exception instanceof GattException) {
                onGattException((GattException) exception);
            } else if (exception instanceof TimeoutException) {
                onTimeoutException((TimeoutException) exception);
            } else if (exception instanceof InitiatedException) {
                onInitiatedException((InitiatedException) exception);
            } else {
                onOtherException((OtherException) exception);
            }
        }
        return this;
    }

    /**
     * connect failed
     */
    protected abstract void onConnectException(ConnectException e);

    /**
     * gatt error status
     */
    protected abstract void onGattException(GattException e);

    /**
     * operation timeout
     */
    protected abstract void onTimeoutException(TimeoutException e);

    /**
     * operation inititiated error
     */
    protected abstract void onInitiatedException(InitiatedException e);

    /**
     * other exceptions
     */
    protected abstract void onOtherException(OtherException e);
}
