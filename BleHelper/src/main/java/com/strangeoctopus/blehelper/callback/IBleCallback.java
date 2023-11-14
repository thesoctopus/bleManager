package com.strangeoctopus.blehelper.callback;

import com.strangeoctopus.blehelper.core.BluetoothGattChannel;
import com.strangeoctopus.blehelper.exception.BleException;
import com.strangeoctopus.blehelper.model.BluetoothLeDevice;

/**
 * @Description: 操作数据回调
 * @author: strangeOctopus
 * @date: 2022/10/2022 19:42
 */
public interface IBleCallback {
    void onSuccess(byte[] data, BluetoothGattChannel bluetoothGattChannel, BluetoothLeDevice bluetoothLeDevice);

    void onFailure(BleException exception);
}
