package com.kanilturgut.mobilecoreaddprovider;

import android.app.Application;

import com.ironsource.mobilcore.MobileCore;

/**
 * Author   : kanilturgut
 * Date     : 14/11/15
 * Time     : 20:00
 */
public class AppController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        String DEV_HASH = getResources().getString(R.string.DEV_HASH);
        MobileCore.init(this, DEV_HASH, MobileCore.LOG_TYPE.DEBUG,
                MobileCore.AD_UNITS.INTERSTITIAL,
                MobileCore.AD_UNITS.STICKEEZ,
                MobileCore.AD_UNITS.NATIVE_ADS,
                MobileCore.AD_UNITS.DIRECT_TO_MARKET);

    }
}
