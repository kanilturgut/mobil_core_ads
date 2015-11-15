package com.kanilturgut.mobilecoreaddprovider.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kanilturgut.mobilecoreaddprovider.R;

/**
 * Author   : kanilturgut
 * Date     : 15/11/15
 * Time     : 21:14
 */
public class NativeAdAdapter extends BaseAdapter {

    Context mContext;
    String[] points;

    public NativeAdAdapter(Context context, String[] points) {
        mContext = context;
        this.points = points;
    }

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
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_add_layout, parent, false);

        TextView title, detail;

        title = (TextView) row.findViewById(R.id.mc_nativeAd_mainTV);
        detail = (TextView) row.findViewById(R.id.mc_nativeAd_descriptionTV);

        title.setText(points[position]);
        detail.setText(points[position]);

        return row;
    }
}
