package com.strangeoctopus.blehelper.callback.scan;

import com.strangeoctopus.blehelper.model.BluetoothLeDevice;

/**
 * @Description: 扫描过滤接口，根据需要实现过滤规则
 * @author: strangeOctopus
 * @date: 2022/9/10 18:19.
 */
public interface IScanFilter {
    BluetoothLeDevice onFilter(BluetoothLeDevice bluetoothLeDevice);
}
