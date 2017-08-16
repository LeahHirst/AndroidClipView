package com.ahirst.clipviewexample;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ahirst.clipview.ClipView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamhirst on 16/08/2017.
 */

// TODO: Javadocs

public class ParameterListAdapter extends BaseAdapter {

    Context context;
    List<String> mItems = new ArrayList<String>();
    ClipView mClipView;

    public ParameterListAdapter(Context context, ClipView clipView) {
        this.context = context;
        mClipView = clipView;

        // Add the sliders
        mItems.add("clipPaddingLeft");
        mItems.add("clipPaddingTop");
        mItems.add("clipPaddingRight");
        mItems.add("clipPaddingBottom");
        mItems.add("clipRadiusTopLeft");
        mItems.add("clipRadiusTopRight");
        mItems.add("clipRadiusBottomLeft");
        mItems.add("clipRadiusBottomRight");
        mItems.add("clipTopLeftVertX");
        mItems.add("clipTopLeftVertY");
        mItems.add("clipTopRightVertX");
        mItems.add("clipTopRightVertY");
        mItems.add("clipBottomLeftVertX");
        mItems.add("clipBottomLeftVertY");
        mItems.add("clipBottomRightVertX");
        mItems.add("clipBottomRightVertY");
    }

    public int getCount() {
        return mItems.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View convertView, ViewGroup viewGroup) {
        // Inflate the view if needed
        View view = (convertView == null) ? View.inflate(context, R.layout.list_parameter, null) : convertView;

        // Create a copy of i
        final int item = i;

        // Get the relevant views
        TextView label = view.findViewById(R.id.item_title);
        SeekBar slider = view.findViewById(R.id.item_slider);

        // Set the label text
        label.setText(mItems.get(item));

        // Handle slider changes
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressDp, boolean b) {
                // Convert the progress into DP
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                int progress = Math.round(progressDp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
                // Determine what action the slider should take
                switch (mItems.get(item)) {
                    case "clipPaddingLeft":
                        mClipView.setClipPadding(progress, -1, -1, -1);
                        break;
                    case "clipPaddingTop":
                        mClipView.setClipPadding(-1, progress, -1, -1);
                        break;
                    case "clipPaddingRight":
                        mClipView.setClipPadding(-1, -1, progress, -1);
                        break;
                    case "clipPaddingBottom":
                        mClipView.setClipPadding(-1, -1, -1, progress);
                        break;
                    case "clipRadiusTopLeft":
                        mClipView.setClipRadius(progress, -1, -1, -1);
                        break;
                    case "clipRadiusTopRight":
                        mClipView.setClipRadius(-1, progress, -1, -1);
                        break;
                    case "clipRadiusBottomLeft":
                        mClipView.setClipRadius(-1, -1, progress, -1);
                        break;
                    case "clipRadiusBottomRight":
                        mClipView.setClipRadius(-1, -1, -1, progress);
                        break;
                    case "clipTopLeftVertX":
                        mClipView.setVertex(progress, -1, -1, -1, -1, -1, -1, -1);
                        break;
                    case "clipTopLeftVertY":
                        mClipView.setVertex(-1, progress, -1, -1, -1, -1, -1, -1);
                        break;
                    case "clipTopRightVertX":
                        mClipView.setVertex(-1, -1, progress, -1, -1, -1, -1, -1);
                        break;
                    case "clipTopRightVertY":
                        mClipView.setVertex(-1, -1, -1, progress, -1, -1, -1, -1);
                        break;
                    case "clipBottomLeftVertX":
                        mClipView.setVertex(-1, -1, -1, -1, progress, -1, -1, -1);
                        break;
                    case "clipBottomLeftVertY":
                        mClipView.setVertex(-1, -1, -1, -1, -1, progress, -1, -1);
                        break;
                    case "clipBottomRightVertX":
                        mClipView.setVertex(-1, -1, -1, -1, -1, -1, progress, -1);
                        break;
                    case "clipBottomRightVertY":
                        mClipView.setVertex(-1, -1, -1, -1, -1, -1, -1, progress);
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        return view;
    }
}
