package com.strangeoctopus.blehelper.callback;

import com.strangeoctopus.blehelper.exception.BleException;

/**
 * @Description: 获取信号值回调
 * @author: strangeOctopus
 * @date: 2022/10/19 15:19
 */
public interface IRssiCallback {
    void onSuccess(int rssi);

    void onFailure(BleException exception);
}
