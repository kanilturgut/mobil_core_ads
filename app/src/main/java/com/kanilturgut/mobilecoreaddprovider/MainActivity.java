package com.kanilturgut.mobilecoreaddprovider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kanilturgut.mobilecoreaddprovider.ads.DirectToMarketActivity;
import com.kanilturgut.mobilecoreaddprovider.ads.InterstitialActivity;
import com.kanilturgut.mobilecoreaddprovider.ads.NativeActivity;
import com.kanilturgut.mobilecoreaddprovider.ads.StickeezActivity;

public class MainActivity extends AppCompatActivity {

    Context mContext = this;
    String[] points = {"Interstitial", "Stickeez", "Native", "Direct To Market"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, points));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mContext, InterstitialActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mContext, StickeezActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mContext, NativeActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(mContext, DirectToMarketActivity.class));
                        break;
                    default:
                        Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 25;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View row;
            row = inflater.inflate(R.layout.custom_add_layout, parent, false);

            TextView title, detail;

            title = (TextView) row.findViewById(R.id.mc_nativeAd_mainTV);
            detail = (TextView) row.findViewById(R.id.mc_nativeAd_descriptionTV);

            title.setText(String.valueOf(points[position]));
            detail.setText(String.valueOf(points[position]));

            return row;
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
}
