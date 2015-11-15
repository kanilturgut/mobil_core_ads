package com.kanilturgut.mobilecoreaddprovider.ads;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ironsource.mobilcore.AdUnitEventListener;
import com.ironsource.mobilcore.MobileCore;
import com.kanilturgut.mobilecoreaddprovider.R;

public class InterstitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        MobileCore.setAdUnitEventListener(new AdUnitEventListener() {
            @Override
            public void onAdUnitEvent(MobileCore.AD_UNITS adUnit, EVENT_TYPE eventType,
                                      MobileCore.AD_UNIT_TRIGGER... trigger) {
                if (adUnit == MobileCore.AD_UNITS.INTERSTITIAL &&
                        eventType == AdUnitEventListener.EVENT_TYPE.AD_UNIT_READY) {

                    for (MobileCore.AD_UNIT_TRIGGER myTrigger : trigger) {
                        if (myTrigger.equals(MobileCore.AD_UNIT_TRIGGER.APP_START)) {
                            MobileCore.showInterstitial(InterstitialActivity.this,
                                    MobileCore.AD_UNIT_TRIGGER.APP_START, null);
                        }
                    }
                }
            }
        });


        MobileCore.loadAdUnit(MobileCore.AD_UNITS.INTERSTITIAL,
                MobileCore.AD_UNIT_TRIGGER.APP_START);

    }

}
