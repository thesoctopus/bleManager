package com.strangeoctopus.blehelper.exception;

import com.strangeoctopus.blehelper.common.BleExceptionCode;

import java.io.Serializable;

/**
 * @Description: BLE异常基类
 * @author: strangeOctopus
 * @date: 16/8/14 10:28.
 */
public class BleException implements Serializable {
    private BleExceptionCode code;
    private String description;

    public BleException(BleExceptionCode code, String description) {
        this.code = code;
        this.description = description;
    }

    public BleExceptionCode getCode() {
        return code;
    }

    public BleException setCode(BleExceptionCode code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BleException setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "BleException{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
