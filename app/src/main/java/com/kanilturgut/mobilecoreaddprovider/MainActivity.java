package com.kanilturgut.mobilecoreaddprovider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.ironsource.mobilcore.AdUnitEventListener;
import com.ironsource.mobilcore.MobileCore;

public class MainActivity extends AppCompatActivity {

    private final String DEV_HASH = getResources().getString(R.string.DEV_HASH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileCore.init(this, DEV_HASH, MobileCore.LOG_TYPE.DEBUG,
                MobileCore.AD_UNITS.INTERSTITIAL, MobileCore.AD_UNITS.STICKEEZ);

        MobileCore.setAdUnitEventListener(new AdUnitEventListener() {
            @Override
            public void onAdUnitEvent(MobileCore.AD_UNITS adUnit, EVENT_TYPE eventType,
                                      MobileCore.AD_UNIT_TRIGGER... trigger) {
                if (adUnit == MobileCore.AD_UNITS.INTERSTITIAL &&
                        eventType == AdUnitEventListener.EVENT_TYPE.AD_UNIT_READY) {
                    for (MobileCore.AD_UNIT_TRIGGER myTrigger : trigger) {
                        if (myTrigger.equals(MobileCore.AD_UNIT_TRIGGER.MAIN_MENU)) {
                            MobileCore.showInterstitial(MainActivity.this,
                                    MobileCore.AD_UNIT_TRIGGER.MAIN_MENU, null);
                        }
                    }
                }
            }
        });

        MobileCore.loadAdUnit(MobileCore.AD_UNITS.INTERSTITIAL,
                MobileCore.AD_UNIT_TRIGGER.MAIN_MENU);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
