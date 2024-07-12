package com.example.uitmsa_sport;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

import com.example.uitmsa_sport.entities.Club;
import com.squareup.picasso.Picasso;

public class ClubAdapter extends BaseAdapter {

    private Context mContext;

    public Club[] clubs;

    public ClubAdapter(Context c, Club[] clubs) {
        mContext = c;
        this.clubs = clubs;
    }

    public int getCount() {
        return clubs.length;
    }

    public Club getItem(int position) {
        return clubs[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View clubView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            // Inflate the layout for each grid item
            clubView = inflater.inflate(R.layout.club_grid, null);

            // Initialize views inside grid_item_layout.xml
            ImageView imageView = clubView.findViewById(R.id.imageView);
            TextView textView = clubView.findViewById(R.id.textView);

            // Set layout parameters, scale type, padding for ImageView
            imageView.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

            // Set the border drawable as background
            Drawable border = mContext.getResources().getDrawable(R.drawable.border);
            imageView.setBackground(border);

            // Set data to views
            Picasso.get().load(clubs[position].getIconURL()).into(imageView);
            textView.setText(clubs[position].getName());
        } else {
            clubView = convertView;
        }

        return clubView;
    }

}
