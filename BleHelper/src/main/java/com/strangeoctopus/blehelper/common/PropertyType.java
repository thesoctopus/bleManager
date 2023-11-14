package com.strangeoctopus.blehelper.common;

/**
 * @Description: 属性类型
 * @author: strangeOctopus
 * @date: 2022/10/2022 20:27
 */
public enum PropertyType {
    PROPERTY_READ(0x01),
    PROPERTY_WRITE(0x02),
    PROPERTY_NOTIFY(0x04),
    PROPERTY_INDICATE(0x08);

    private int propertyValue;

    PropertyType(int propertyValue) {
        this.propertyValue = propertyValue;
    }

    public int getPropertyValue() {
        return propertyValue;
    }
}
