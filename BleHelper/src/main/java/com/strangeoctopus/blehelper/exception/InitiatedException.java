package com.strangeoctopus.blehelper.exception;

import com.strangeoctopus.blehelper.common.BleExceptionCode;

/**
 * @Description: 初始化异常
 * @author: strangeOctopus
 * @date: 16/8/14 10:30.
 */
public class InitiatedException extends BleException {
    public InitiatedException() {
        super(BleExceptionCode.INITIATED_ERR, "Initiated Exception Occurred! ");
    }
}
