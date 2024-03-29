package com.strangeoctopus.blehelper.callback.scan;

import android.os.ParcelUuid;

import com.strangeoctopus.blehelper.model.BluetoothLeDevice;

import java.util.UUID;

/**
 * @Description: 根据指定uuid过滤设备
 * @author: strangeOctopus
 * @date: 2022/9/12 23:20.
 */
public class UuidFilterScanCallback extends ScanCallback {
    private UUID uuid;//设备uuid

    public UuidFilterScanCallback(IScanCallback scanCallback) {
        super(scanCallback);
    }

    public UuidFilterScanCallback setUuid(String uuid) {
        this.uuid = UUID.fromString(uuid);
        return this;
    }

    public UuidFilterScanCallback setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    @Override
    public BluetoothLeDevice onFilter(BluetoothLeDevice bluetoothLeDevice) {
        BluetoothLeDevice tempDevice = null;
        if (bluetoothLeDevice != null && bluetoothLeDevice.getDevice() != null
                && bluetoothLeDevice.getDevice().getUuids() != null
                && bluetoothLeDevice.getDevice().getUuids().length > 0) {
            for (ParcelUuid parcelUuid : bluetoothLeDevice.getDevice().getUuids()) {
                if (uuid != null && uuid == parcelUuid.getUuid()) {
                    tempDevice = bluetoothLeDevice;
                }
            }
        }
        return tempDevice;
    }
}
