package com.kanilturgut.mobilecoreaddprovider.ads;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.ironsource.mobilcore.AdUnitEventListener;
import com.ironsource.mobilcore.MobileCore;
import com.kanilturgut.mobilecoreaddprovider.R;

public class DirectToMarketActivity extends AppCompatActivity {

    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_to_market);
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

        mButton = (Button) findViewById(R.id.button);
        mButton.setVisibility(View.INVISIBLE);

        MobileCore.setAdUnitEventListener(new AdUnitEventListener() {

            @Override
            public void onAdUnitEvent(MobileCore.AD_UNITS adUnit, EVENT_TYPE eventType,
                                      MobileCore.AD_UNIT_TRIGGER... trigger) {

                if (adUnit == MobileCore.AD_UNITS.DIRECT_TO_MARKET &&
                        eventType == AdUnitEventListener.EVENT_TYPE.AD_UNIT_READY) {

                    for (MobileCore.AD_UNIT_TRIGGER myTrigger : trigger) {
                        if (myTrigger.equals(MobileCore.AD_UNIT_TRIGGER.BUTTON_CLICK)) {
                            // Display your button or banner that triggers the ad unit
                            mButton.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        MobileCore.loadAdUnit(MobileCore.AD_UNITS.DIRECT_TO_MARKET,
                MobileCore.AD_UNIT_TRIGGER.BUTTON_CLICK);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileCore.directToMarket(DirectToMarketActivity.this, MobileCore.AD_UNIT_TRIGGER.BUTTON_CLICK);
            }
        });
    }

}
