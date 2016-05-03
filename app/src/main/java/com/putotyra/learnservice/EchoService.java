package com.putotyra.learnservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class EchoService extends Service {
    public EchoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
