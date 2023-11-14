package com.strangeoctopus.blehelper.common;

/**
 * @Description: BLE异常Code
 * @author: strangeOctopus
 * @date: 16/8/14 10:43.
 */
public enum BleExceptionCode {
    TIMEOUT,    //超时
    CONNECT_ERR,    //连接异常
    GATT_ERR,   //GATT异常
    INITIATED_ERR,  //初始化异常
    OTHER_ERR   //其他异常
}
