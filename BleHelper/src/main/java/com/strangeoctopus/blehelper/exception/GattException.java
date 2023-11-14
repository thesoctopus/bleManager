package com.strangeoctopus.blehelper.exception;

import com.strangeoctopus.blehelper.common.BleExceptionCode;

/**
 * @Description: Gatt异常
 * @author: strangeOctopus
 * @date: 16/8/14 10:30.
 */
public class GattException extends BleException {
    private int gattStatus;

    public GattException(int gattStatus) {
        super(BleExceptionCode.GATT_ERR, "Gatt Exception Occurred! ");
        this.gattStatus = gattStatus;
    }

    public int getGattStatus() {
        return gattStatus;
    }

    public GattException setGattStatus(int gattStatus) {
        this.gattStatus = gattStatus;
        return this;
    }

    @Override
    public String toString() {
        return "GattException{" +
                "gattStatus=" + gattStatus +
                '}' + super.toString();
    }
}
