package com.strangeoctopus.blehelper.callback.scan;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.os.Handler;
import android.os.Looper;

import com.strangeoctopus.blehelper.ViseBle;
import com.strangeoctopus.blehelper.common.BleConfig;
import com.strangeoctopus.blehelper.model.BluetoothLeDevice;
import com.strangeoctopus.blehelper.model.BluetoothLeDeviceStore;
import com.strangeoctopus.blehelper.utils.LogUtil;

import java.util.List;

/**
 * @Description: 扫描设备回调
 * @author: strangeOctopus
 * @date: 2023/2/24
 */
public class ScanCallback extends android.bluetooth.le.ScanCallback implements BluetoothAdapter.LeScanCallback, IScanFilter {
    private static String TAG = ScanCallback.class.getSimpleName();
    protected Handler handler = new Handler(Looper.myLooper());
    protected boolean isScan = true;//是否开始扫描
    protected boolean isScanning = false;//是否正在扫描
    protected BluetoothLeDeviceStore bluetoothLeDeviceStore;//用来存储扫描到的设备
    protected IScanCallback scanCallback;//扫描结果回调

    public ScanCallback(IScanCallback scanCallback) {
        this.scanCallback = scanCallback;
        if (scanCallback == null) {
            throw new NullPointerException("this scanCallback is null!");
        }
        bluetoothLeDeviceStore = new BluetoothLeDeviceStore();
    }

    public ScanCallback setScan(boolean scan) {
        isScan = scan;
        return this;
    }

    public boolean isScanning() {
        return isScanning;
    }

    @SuppressLint("MissingPermission")
    public void scan() {
        if (isScan) {
            if (isScanning) {
                return;
            }
            bluetoothLeDeviceStore.clear();
            if (BleConfig.getInstance().getScanTimeout() > 0) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isScanning = false;

                        if (ViseBle.getInstance().getBluetoothAdapter() != null) {
                            stopScanNow();
                        }

                        if (bluetoothLeDeviceStore.getDeviceMap() != null
                                && bluetoothLeDeviceStore.getDeviceMap().size() > 0) {
                            scanCallback.onScanFinish(bluetoothLeDeviceStore);
                        } else {
                            scanCallback.onScanTimeout();
                        }
                    }
                }, BleConfig.getInstance().getScanTimeout());
            } else if (BleConfig.getInstance().getScanRepeatInterval() > 0) {
                //如果超时时间设置为一直扫描（即 <= 0）,则判断是否设置重复扫描间隔
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isScanning = false;

                        if (ViseBle.getInstance().getBluetoothAdapter() != null) {
                            stopScanNow();
                        }

                        if (bluetoothLeDeviceStore.getDeviceMap() != null
                                && bluetoothLeDeviceStore.getDeviceMap().size() > 0) {
                            scanCallback.onScanFinish(bluetoothLeDeviceStore);
                        } else {
                            scanCallback.onScanTimeout();
                        }
                        isScanning = true;
                        if (ViseBle.getInstance().getBluetoothAdapter() != null) {
                            startScanNow();
                        }
                        handler.postDelayed(this, BleConfig.getInstance().getScanRepeatInterval());
                    }
                }, BleConfig.getInstance().getScanRepeatInterval());
            }
            isScanning = true;
            if (ViseBle.getInstance().getBluetoothAdapter() != null) {
                startScanNow();
            }
        } else {
            isScanning = false;
            if (ViseBle.getInstance().getBluetoothAdapter() != null) {
                stopScanNow();
            }
        }
    }

    public ScanCallback removeHandlerMsg() {
        handler.removeCallbacksAndMessages(null);
        bluetoothLeDeviceStore.clear();
        return this;
    }

    @SuppressLint("MissingPermission")
    private void startScanNow() {
        /*
        扫描前需动态获取ACCESS_FINE_LOCATION权限，否则在Api21以后返回错误
         */
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ViseBle.getInstance().getBluetoothAdapter().getBluetoothLeScanner().startScan(this);
        } else {
            ViseBle.getInstance().getBluetoothAdapter().startLeScan(this);
        }
    }

    @SuppressLint("MissingPermission")
    private void stopScanNow() {
        /* 只有在没有callback的时候才会停下扫描，记得有一个先后顺序，要先断开 */
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ViseBle.getInstance().getBluetoothAdapter().getBluetoothLeScanner().stopScan(this);
        } else {
            ViseBle.getInstance().getBluetoothAdapter().stopLeScan(this);
        }
    }

    @Override
    public void onLeScan(BluetoothDevice bluetoothDevice, int rssi, byte[] scanRecord) {
        LogUtil.v(TAG, "onLeScan");
        BluetoothLeDevice bluetoothLeDevice = new BluetoothLeDevice(bluetoothDevice, rssi, scanRecord, System.currentTimeMillis());
        BluetoothLeDevice filterDevice = onFilter(bluetoothLeDevice);
        if (filterDevice != null) {
            bluetoothLeDeviceStore.addDevice(filterDevice);
            scanCallback.onDeviceFound(filterDevice);
        }
    }

    @Override
    public BluetoothLeDevice onFilter(BluetoothLeDevice bluetoothLeDevice) {
        return bluetoothLeDevice;
    }


    @Override
    public void onScanResult(int callbackType, ScanResult result) {
        LogUtil.v(TAG, "onScanResult");
        BluetoothLeDevice bluetoothLeDevice = new BluetoothLeDevice(result.getDevice(), result.getRssi(), result.getScanRecord().getBytes(), System.currentTimeMillis());
        BluetoothLeDevice filterDevice = onFilter(bluetoothLeDevice);
        if (filterDevice != null) {
            bluetoothLeDeviceStore.addDevice(filterDevice);
            scanCallback.onDeviceFound(filterDevice);
        }
    }

    @Override
    public void onBatchScanResults(List<ScanResult> results) {
        super.onBatchScanResults(results);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onScanFailed(int errorCode) {
        if (errorCode == 1) {
            LogUtil.v("strangeOctopus", "MissingPermission！！！");
        } else if (errorCode == 2)
              /*
                 偶然发现,概率极低，但也处理一下吧
                 Fails to start scan as app cannot be registered.
                 Constant Value: 2 (0x00000002)
                 在startScan扫描的过程中还没来得及stopScan ,app就被系统强制杀掉了，反复多次递增后就会抛出这个异常
                 一旦发生错误只能重启蓝牙
                */
            if (ViseBle.getInstance().getBluetoothAdapter() != null) {
                ViseBle.getInstance().getBluetoothAdapter().disable();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //要等待蓝牙彻底关闭，然后再打开，才能实现重启效果
                            if (ViseBle.getInstance().getBluetoothAdapter().getState() == BluetoothAdapter.STATE_OFF) {
                                ViseBle.getInstance().getBluetoothAdapter().enable();
                                break;
                            }
                        }
                    }
                }).start();
            }
    }
}




