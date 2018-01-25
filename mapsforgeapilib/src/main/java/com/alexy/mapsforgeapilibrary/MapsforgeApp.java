package com.alexy.mapsforgeapilibrary;

import android.app.Application;

import org.mapsforge.map.android.graphics.AndroidGraphicFactory;

/**
 * Created by alexeykrichun on 22/01/2018.
 */

public class MapsforgeApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidGraphicFactory.createInstance(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AndroidGraphicFactory.clearResourceMemoryCache();
    }
}
