package com.strangeoctopus.blehelper.exception;

import com.strangeoctopus.blehelper.common.BleExceptionCode;

/**
 * @Description: 超时异常
 * @author: strangeOctopus
 * @date: 16/8/14 10:29.
 */
public class TimeoutException extends BleException {
    public TimeoutException() {
        super(BleExceptionCode.TIMEOUT, "Timeout Exception Occurred! ");
    }
}
