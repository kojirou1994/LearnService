package com.putotyra.learnservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Button btnStartService, btnStopService, btnBindService, btnUnbindService, btnGetCurrentNumber;
    private Intent serviceIntent;
    private EchoService echoService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceIntent = new Intent(this, EchoService.class);

        btnStartService = (Button) findViewById(R.id.btnStartService);
        btnStopService = (Button) findViewById(R.id.btnStopService);
        btnBindService = (Button) findViewById(R.id.btnBindService);
        btnUnbindService = (Button) findViewById(R.id.btnUnbindService);
        btnGetCurrentNumber = (Button) findViewById(R.id.btnGetCurrentNumber);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnbindService.setOnClickListener(this);
        btnGetCurrentNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartService:
                startService(serviceIntent);
                break;
            case R.id.btnStopService:
                stopService(serviceIntent);
                break;
            case R.id.btnBindService:
                bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                if (echoService != null) {
                    unbindService(this);
                    echoService = null;
                }
                break;
            case R.id.btnGetCurrentNumber:
                if (echoService != null) {
                    System.out.println("当前服务中的数字是：" + echoService.getCurrentNum());
                }
                break;
//            default:
//                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("onServiceConnected");

        echoService = ((EchoService.EchoServiceBinder) service).getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        System.out.println("onServiceDisconnected");
    }
}
