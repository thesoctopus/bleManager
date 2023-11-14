package com.strangeoctopus.blehelper.exception.handler;

import com.strangeoctopus.blehelper.exception.ConnectException;
import com.strangeoctopus.blehelper.exception.GattException;
import com.strangeoctopus.blehelper.exception.InitiatedException;
import com.strangeoctopus.blehelper.exception.OtherException;
import com.strangeoctopus.blehelper.exception.TimeoutException;


/**
 * @Description: 异常默认处理
 * @author: strangeOctopus
 * @date: 16/8/14 10:35.
 */
public class DefaultBleExceptionHandler extends BleExceptionHandler {
    @Override
    protected void onConnectException(ConnectException e) {
//        ViseLog.e(e.getDescription());
    }

    @Override
    protected void onGattException(GattException e) {
//        ViseLog.e(e.getDescription());
    }

    @Override
    protected void onTimeoutException(TimeoutException e) {
//        ViseLog.e(e.getDescription());
    }

    @Override
    protected void onInitiatedException(InitiatedException e) {
//        ViseLog.e(e.getDescription());
    }

    @Override
    protected void onOtherException(OtherException e) {
//        ViseLog.e(e.getDescription());
    }
}
