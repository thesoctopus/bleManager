package com.strangeoctopus.blehelper.exception;

import android.bluetooth.BluetoothGatt;

import com.strangeoctopus.blehelper.common.BleExceptionCode;

/**
 * @Description: 连接异常
 * @author: strangeOctopus
 * @date: 16/8/14 10:29.
 */
public class ConnectException extends BleException {
    private BluetoothGatt bluetoothGatt;
    private int gattStatus;

    public ConnectException(BluetoothGatt bluetoothGatt, int gattStatus) {
        super(BleExceptionCode.CONNECT_ERR, "Connect Exception Occurred! ");
        this.bluetoothGatt = bluetoothGatt;
        this.gattStatus = gattStatus;
    }

    public int getGattStatus() {
        return gattStatus;
    }

    public ConnectException setGattStatus(int gattStatus) {
        this.gattStatus = gattStatus;
        return this;
    }

    public BluetoothGatt getBluetoothGatt() {
        return bluetoothGatt;
    }

    public ConnectException setBluetoothGatt(BluetoothGatt bluetoothGatt) {
        this.bluetoothGatt = bluetoothGatt;
        return this;
    }

    @Override
    public String toString() {
        return "ConnectException{" +
                "gattStatus=" + gattStatus +
                ", bluetoothGatt=" + bluetoothGatt +
                "} " + super.toString();
    }
}
