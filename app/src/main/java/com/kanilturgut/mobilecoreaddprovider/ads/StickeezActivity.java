package com.kanilturgut.mobilecoreaddprovider.ads;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ironsource.mobilcore.AdUnitEventListener;
import com.ironsource.mobilcore.MobileCore;
import com.kanilturgut.mobilecoreaddprovider.R;

public class StickeezActivity extends AppCompatActivity {

    Context mContext = this;
    TextView textView;

//    MobileCore.AD_UNIT_TRIGGER trigger = MobileCore.AD_UNIT_TRIGGER.parse("my_custom_trigger");
    MobileCore.AD_UNIT_TRIGGER trigger = MobileCore.AD_UNIT_TRIGGER.GAME_LEVEL_END_WIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stickeez);
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

        textView = (TextView) findViewById(R.id.textView);

        MobileCore.setAdUnitEventListener(new AdUnitEventListener() {

            @Override
            public void onAdUnitEvent(MobileCore.AD_UNITS ad_units, EVENT_TYPE event_type, MobileCore.AD_UNIT_TRIGGER... ad_unit_triggers) {

                if (ad_units == MobileCore.AD_UNITS.STICKEEZ &&
                        event_type == AdUnitEventListener.EVENT_TYPE.AD_UNIT_READY) {

                    for (MobileCore.AD_UNIT_TRIGGER myTrigger : ad_unit_triggers) {
                        if (myTrigger.equals(trigger)) {

                            MobileCore.setStickeezPositionBelowView(StickeezActivity.this, R.id.textView);
                            MobileCore.showStickee(StickeezActivity.this,
                                    trigger);
                        }
                    }
                }
            }

        });

        MobileCore.loadAdUnit(MobileCore.AD_UNITS.STICKEEZ,
                trigger);
    }

    @Override
    protected void onPause() {
        MobileCore.hideStickee();

        super.onPause();
    }
}
