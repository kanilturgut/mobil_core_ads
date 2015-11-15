package com.kanilturgut.mobilecoreaddprovider.ads;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ironsource.mobilcore.AdUnitEventListener;
import com.ironsource.mobilcore.MobileCore;
import com.ironsource.mobilcore.NativeAdsAdapter;
import com.kanilturgut.mobilecoreaddprovider.R;
import com.kanilturgut.mobilecoreaddprovider.adapter.NativeAdAdapter;

public class NativeActivity extends AppCompatActivity {

    Context mContext = this;
    ListView myNativeList;

    String[] NUMBERS = new String[25];
    private NativeAdAdapter nativeAdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
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

        setDummyList();

        myNativeList = (ListView) findViewById(R.id.myNativeList);
        nativeAdAdapter = new NativeAdAdapter(mContext, NUMBERS);


        /*
            !!!!! WTF !!!!!

            COMPLIANCE NOTE

            Native Ads must include the advertising app’s icon, its name and the Google Play badge
            to be compliant (see mandatory fields in the table below). If you apply edits
            that are non-compliant, you acknowledge and agree that we will have no liability
            for your customization.
         */

        MobileCore.setAdUnitEventListener(new AdUnitEventListener() {


            @Override
            public void onAdUnitEvent(MobileCore.AD_UNITS ad_units, EVENT_TYPE event_type, MobileCore.AD_UNIT_TRIGGER... ad_unit_triggers) {

                if (ad_units == MobileCore.AD_UNITS.NATIVE_ADS &&
                        event_type == AdUnitEventListener.EVENT_TYPE.AD_UNIT_READY) {
                    for (MobileCore.AD_UNIT_TRIGGER myTrigger : ad_unit_triggers) {
                        if (myTrigger.equals(MobileCore.AD_UNIT_TRIGGER.APP_START)) {
                            // Build mobileCore native ads adapter and
                            // attach it to your listview or gridview

                            MobileCore.setNativeAdsBannerSupport(true);

                            NativeAdsAdapter nativeAdsAdapter = MobileCore.buildNativeAdsAdapter(
                                    NativeActivity.this,
                                    nativeAdAdapter,
                                    R.layout.custom_add_layout
                            );

                            myNativeList.setAdapter(nativeAdsAdapter);
                        }
                    }
                }
            }
        });

        MobileCore.loadAdUnit(MobileCore.AD_UNITS.NATIVE_ADS,
                MobileCore.AD_UNIT_TRIGGER.APP_START);

    }

    /*
        Create a dummy list
     */
    private void setDummyList() {
        for (int i = 0; i < 25; i++) {
            NUMBERS[i] = String.valueOf(i + 1);
        }
    }

}
