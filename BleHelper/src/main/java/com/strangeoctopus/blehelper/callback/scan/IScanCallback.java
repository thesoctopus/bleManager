package com.strangeoctopus.blehelper.callback.scan;

import com.strangeoctopus.blehelper.model.BluetoothLeDevice;
import com.strangeoctopus.blehelper.model.BluetoothLeDeviceStore;

/**
 * @Description: 扫描回调
 * @author: strangeOctopus
 * @date: 2022/9/10 18:14.
 */
public interface IScanCallback {
    //发现设备
    void onDeviceFound(BluetoothLeDevice bluetoothLeDevice);

    //扫描完成
    void onScanFinish(BluetoothLeDeviceStore bluetoothLeDeviceStore);

    //扫描超时
    void onScanTimeout();

}
