package com.strangeoctopus.blehelper.callback;

import com.strangeoctopus.blehelper.core.DeviceMirror;
import com.strangeoctopus.blehelper.exception.BleException;

/**
 * @Description: 连接设备回调
 * @author: strangeOctopus
 * @date: 2022/8/1 23:00.
 */
public interface IConnectCallback {
    //连接成功
    void onConnectSuccess(DeviceMirror deviceMirror);

    //连接失败
    void onConnectFailure(BleException exception);

    //连接断开
    void onDisconnect(boolean isActive);
}
