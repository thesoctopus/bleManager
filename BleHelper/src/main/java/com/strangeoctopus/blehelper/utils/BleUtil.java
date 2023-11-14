package com.strangeoctopus.blehelper.utils;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.strangeoctopus.blehelper.ViseBle;

/**
 * @Description: 蓝牙基础操作工具类
 * @author: strangeOctopus
 * @date: 16/8/5 20:43.
 */
public class BleUtil {

    public static void enableBluetooth(Activity activity, int requestCode) {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        if (ActivityCompat.checkSelfPermission(ViseBle.getInstance().getContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public static boolean isSupportBle(Context context) {
        if (context == null || !context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            return false;
        }
        BluetoothManager manager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        return manager.getAdapter() != null;
    }

    public static boolean isBleEnable(Context context) {
        if (!isSupportBle(context)) {
            return false;
        }
        BluetoothManager manager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);

        if (manager.getAdapter() == null){
            return false;
        }
        return manager.getAdapter().isEnabled();
    }

}
