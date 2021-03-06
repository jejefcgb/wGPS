package com.jduban.gps.utils;

/**
 * Created by jduban on 17/03/2015.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jduban.gps.R;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_COORDINATE = 1;
    private static final int TYPE_ACCURACY = 2;
    private static final int TYPE_LOCATION = 3;

    private ArrayList<String> mValues;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        int holderId;

        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);

            if (TYPE_LOCATION == ViewType){
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                holderId = 3;
            }
            if (TYPE_ACCURACY == ViewType){
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                holderId = 2;
            }
            else if (TYPE_COORDINATE == ViewType) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                holderId = 1;
            }
        }
    }


    public RecyclerAdapter(ArrayList<String> Titles) {
        mValues = Titles;
    }


    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == TYPE_COORDINATE) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coordinate, parent, false);
            return new ViewHolder(v, viewType);

        }
        if (viewType == TYPE_ACCURACY) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.accuracy, parent, false);
            return new ViewHolder(v, viewType);

        }else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
            return new ViewHolder(v, viewType);

        }else if (viewType == TYPE_LOCATION) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.location, parent, false);
            return new ViewHolder(v, viewType);
        }

        return null;

    }


    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {

        if (holder.holderId != 0    ) // Coordinates and saved locations
            holder.textView.setText(mValues.get(position - 1));

    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return mValues.size() + 1; // the number of items in the list will be +1 the titles including the header view.
    }


    // With the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        else if (position == 1)
            return TYPE_COORDINATE;
        else if (position == 2)
            return TYPE_ACCURACY;
        else
            return TYPE_LOCATION;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}
