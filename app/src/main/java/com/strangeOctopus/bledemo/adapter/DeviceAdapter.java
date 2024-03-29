package com.strangeOctopus.bledemo.adapter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.strangeOctopus.bledemo.R;
import com.strangeoctopus.blehelper.model.BluetoothLeDevice;
import com.strangeoctopus.blehelper.utils.HexUtil;

import com.strangeoctopus.blehelper.utils.LogUtil;
import com.vise.xsnow.ui.adapter.helper.HelperAdapter;
import com.vise.xsnow.ui.adapter.helper.HelperViewHolder;

public class DeviceAdapter extends HelperAdapter<BluetoothLeDevice> {

    public DeviceAdapter(Context context) {
        super(context, R.layout.item_scan_layout);
    }

    @Override
    public void HelpConvert(HelperViewHolder viewHolder, int position, BluetoothLeDevice bluetoothLeDevice) {
        TextView deviceNameTv = viewHolder.getView(R.id.device_name);
        TextView deviceMacTv = viewHolder.getView(R.id.device_mac);
        TextView deviceRssiTv = viewHolder.getView(R.id.device_rssi);
        TextView deviceScanRecordTv = viewHolder.getView(R.id.device_scanRecord);
        if (bluetoothLeDevice != null && bluetoothLeDevice.getDevice() != null) {
            String deviceName = bluetoothLeDevice.getDevice().getName();
            if (deviceName != null && !deviceName.isEmpty()) {
                deviceNameTv.setText(deviceName);
            } else {
                deviceNameTv.setText(mContext.getString(R.string.unknown_device));
            }
            deviceMacTv.setText(bluetoothLeDevice.getDevice().getAddress());
            deviceRssiTv.setText(mContext.getString(R.string.label_rssi) + bluetoothLeDevice.getRssi() + "dB");
            deviceScanRecordTv.setText(mContext.getString(R.string.header_scan_record) + ":"
                    + HexUtil.encodeHexStr(bluetoothLeDevice.getScanRecord()));
        }
    }
}
