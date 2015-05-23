/**
 * Created by jbyeung on 10/30/14.
 */
package com.android.yeunglabs.simplelauncher;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;



public class HomeActivity extends Activity implements AbsListView.OnScrollListener {

    public static final int iconSize = 200;

    private GridviewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadApps();
        loadGridView();

        gridView.setOnScrollListener(this);
    }


    private PackageManager manager;
    private ArrayList<AppDetail> apps;

    private void loadApps() {
        manager = getPackageManager();
        apps = new ArrayList<AppDetail>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for (ResolveInfo ri : availableActivities) {
            AppDetail app = new AppDetail();
            app.label = ri.loadLabel(manager);
            app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
//            app.alpha = 1.0f;
//            app.scale = 1.0f;
            app.padding = 0;
            apps.add(app);
        }

        // delete later, just using to add more icons
        for (ResolveInfo ri : availableActivities) {
            AppDetail app = new AppDetail();
            app.label = ri.loadLabel(manager);
            app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
//            app.alpha = 1.0f;
//            app.scale = 1.0f;
            apps.add(app);
        }
        for (ResolveInfo ri : availableActivities) {
            AppDetail app = new AppDetail();
            app.label = ri.loadLabel(manager);
            app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
//            app.alpha = 1.0f;
//            app.scale = 1.0f;
            apps.add(app);
        }
        for (ResolveInfo ri : availableActivities) {
            AppDetail app = new AppDetail();
            app.label = ri.loadLabel(manager);
            app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
//            app.alpha = 1.0f;
//            app.scale = 1.0f;
            apps.add(app);
        }
    }

    private GridView gridView;

    private void loadGridView() {
        final RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
        container.setBackgroundColor(Color.BLACK);

        double pixelCount= (iconSize * iconSize) * apps.size();
        double layoutSize = 1.5 * Math.sqrt(pixelCount);

        int numberOfColumns = (int) (layoutSize / (iconSize * 1.5));

        mAdapter = new GridviewAdapter(this, apps);
        if (gridView == null)
            gridView = new GridView(this);

        gridView.setLayoutParams(new FrameLayout.LayoutParams((int) layoutSize, (int) layoutSize));//FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        gridView.setNumColumns(numberOfColumns);
        gridView.setHorizontalSpacing(0);
        gridView.setVerticalSpacing(0);

        gridView.setColumnWidth(iconSize);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setGravity(Gravity.CENTER);
        gridView.setPadding(250, 250, 250, 250);

//        setAppPadding();

        gridView.setAdapter(mAdapter);

        container.addView(gridView);

        // Implement On Item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                Intent i = manager.getLaunchIntentForPackage(mAdapter.getItem(position).name.toString());
                HomeActivity.this.startActivity(i);
            }
        });


        container.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        //Remove the listener before proceeding
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            container.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        } else {
                            container.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }

                    }
                }
        );
    }

    private void setBorderEffects() {
        final int numVisibleChildren = gridView.getChildCount();
        final int firstVisiblePosition = gridView.getFirstVisiblePosition();

        for (int i = firstVisiblePosition; i < numVisibleChildren; i++) {
            View view = gridView.getChildAt(i);
            int[] location = new int[2];
            view.getLocationInWindow(location);

            // TODO set scaling size / alpha, trigger borders
            if (locationInBorder(location)){// && view.getAlpha() == 1.0f) {
//                apps.get(i).alpha = 0.3f;
//                apps.get(i).scale = 0.5f;
                view.setAlpha(0.3f);
                view.setScaleX(0.5f);
                view.setScaleY(0.5f);

                view.requestLayout();
//                gridView.invalidateViews();
//                gridView.requestLayout();

            }
            else if (view.getAlpha() != 1.0f)
            {
//                apps.get(i).alpha = 1.0f;
//                apps.get(i).scale = 1.0f;
                view.setAlpha(1.0f);
                view.setScaleX(1.0f);
                view.setScaleY(1.0f);
                view.requestLayout();
//                gridView.invalidateViews();
//                gridView.requestLayout();
            }
        }

//      mAdapter.updateAppList(apps);
    }

//    private void setAppPadding() {
//        double pixelCount= (iconSize * iconSize) * apps.size();
//        double layoutSize = 1.5 * Math.sqrt(pixelCount);
//        int numberOfColumns = (int) (layoutSize / (iconSize * 1.5));
//
//        int row, column;
//
//        Log.d("number of columns", Integer.toString(numberOfColumns));
//        for (int position = 0; position < apps.size(); position++) {
//            row = (int)Math.ceil((double)(position + 1) / (double) numberOfColumns);
//            column = (row * numberOfColumns) - position;
//
//            Log.d("asdf ", "row: " + row);
//            Log.d("asdf ", "col: " + column);
//
//            if (row % 2 == 1)
//                apps.get(position).padding = 50;
//            else
//                apps.get(position).padding = 0;
//        }
//    }

    private boolean locationInBorder(int[] location)
    {
        return (location[0] < 1000 && location[1] < 250) ||
                (location[0] < 1000 && location[1] > 1500) ||
                (location[0] < 250) ||
                (location[1] > 750);
    }

    // Scroll listeners
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        setBorderEffects();

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    // TODO orientation / rotating.
}