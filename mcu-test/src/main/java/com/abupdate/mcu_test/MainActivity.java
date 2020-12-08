package com.abupdate.mcu_test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.car.Car;
import android.car.CarInfoManager;
import android.car.CarNotConnectedException;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Car mCar;
    private CarInfoManager mCarInfoManager = null;
    private TextView mcuVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mcuVersion = findViewById(R.id.tv_mcu_version);
        mCar = Car.createCar(MainActivity.this, mServiceConnection);
        if (mCar != null) {
            Log.d(TAG, "init mCar   " + mCar);
            mCar.connect();
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: initCarSensorManager start");
            initCarSensorManager();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 2000);
        }
    };


    private void initCarSensorManager() {
        Log.d(TAG, "initCarSensorManager start");
        try {
            if (mCar != null) {
                Log.d(TAG, "initCarInfoManager getCarManager");
                mCarInfoManager = (CarInfoManager) mCar.getCarManager(Car.INFO_SERVICE);
            }
            if (mCarInfoManager == null) {
                Log.d(TAG, "mCarInfoManager==null ");
            }
        } catch (CarNotConnectedException e) {
            Log.e(TAG, "carImpl can not connect android car service");
        }
    }

    public void bt_get_mcu_version(View view) {
        if (mCarInfoManager != null) {
            String MCU = mCarInfoManager.getMCUVersion();
            Log.i(TAG, "mCarInfoManager.getMCUVersion() : " + MCU);
            mcuVersion.setText(MCU);
        }
    }
}