package com.android.yeunglabs.simplelauncher;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.yeunglabs.simplelauncher.ImageHelper;

public class GridviewAdapter extends BaseAdapter
{
    private ArrayList<AppDetail> listApps;
    private Activity activity;

    public GridviewAdapter(Activity activity,ArrayList<AppDetail> listApps) {
        super();
        this.listApps= listApps;
        this.activity = activity;
    }

    @Override
    public int getCount() {

        return listApps.size();
    }

    @Override
    public AppDetail getItem(int position) {
        return listApps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public void updateAppList(ArrayList<AppDetail> listApps) {
        this.listApps = listApps;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator = activity.getLayoutInflater();

        if(convertView==null)
        {
            convertView = inflator.inflate(R.layout.list_item, null);
        }

        ImageView appIcon = (ImageView)convertView.findViewById(R.id.item_app_icon);
        Bitmap bitmap = ImageHelper.drawableToBitmap(listApps.get(position).icon);
//
//        float scale = listApps.get(position).scale;
//        float alpha = listApps.get(position).alpha;
//
//        appIcon.setAlpha(alpha);
//        appIcon.setScaleX(scale);
//        appIcon.setScaleY(scale);

        // TODO fix padding, get closer together
        appIcon.setImageDrawable(new BitmapDrawable(ImageHelper.getRoundedCornerBitmap(bitmap, 250, 10)));
        appIcon.setPadding(16 * (position % 2), 64 * ((position % 2)), 16 * ((position % 2) + 1), 64 * ((position % 2) + 1));

//        int padding = listApps.get(position).padding;
//        Log.d("padding", Integer.toString(padding));
//        appIcon.setPadding(padding, 0, 0, 0);

        // add labels
//        TextView appLabel = (TextView)convertView.findViewById(R.id.item_app_label);
//        appLabel.setText(listApps.get(position).label);

        return convertView;
    }
}
