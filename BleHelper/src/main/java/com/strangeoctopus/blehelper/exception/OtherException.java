package com.strangeoctopus.blehelper.exception;

import com.strangeoctopus.blehelper.common.BleExceptionCode;

/**
 * @Description: 其他异常
 * @author: strangeOctopus
 * @date: 16/8/14 10:32.
 */
public class OtherException extends BleException {
    public OtherException(String description) {
        super(BleExceptionCode.OTHER_ERR, description);
    }
}
