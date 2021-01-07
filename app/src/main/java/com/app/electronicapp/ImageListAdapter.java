package com.app.electronicapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Objects;

public class ImageListAdapter extends ArrayAdapter<uploadinfo> {
    private static final String TAG = "ImageListAdapter";
    private Context mContext;
    private int mResource;
    public ImageListAdapter(Context context, int resource, ArrayList<uploadinfo>  list) {
        super(context, resource, list);
        mContext = context;
        mResource = resource;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the image information


        String TempImagefirstname= Objects.requireNonNull(getItem(position)).getImagefirstname();
        String TempImagelastname= Objects.requireNonNull(getItem(position)).getImagelastname();
        String TempImagephone = Objects.requireNonNull(getItem(position)).getImagephone();
        String TempImageadress = Objects.requireNonNull(getItem(position)).getImageadress();
        String TempImagegender = Objects.requireNonNull(getItem(position)).getImageagender();
        String TempImagetime = Objects.requireNonNull(getItem(position)).getImagetime();
        String TempImagedate = Objects.requireNonNull(getItem(position)).getImagedate();

        String TempImageduration = Objects.requireNonNull(getItem(position)).getImageduration();
        String imageUrl = Objects.requireNonNull(getItem(position)).getImageURL();

        //Create the employee object with the information
        uploadinfo ImageInfo = new uploadinfo(TempImagefirstname,TempImagelastname,TempImagephone,TempImageadress,TempImagegender,TempImagetime,TempImagedate,TempImageduration,imageUrl);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(this.mResource, parent,false);
        TextView imgfname = convertView.findViewById(R.id.ffname);
        TextView  imglast= convertView.findViewById(R.id.lname);
        TextView imgphone = convertView.findViewById(R.id.phoneeee);
        TextView imgadress = convertView.findViewById(R.id.adress);
        TextView imggender= convertView.findViewById(R.id.gender);
        TextView imgtime= convertView.findViewById(R.id.TIME);
        TextView imgdate= convertView.findViewById(R.id.DATE);

        TextView imgduration= convertView.findViewById(R.id.DURRATION);
        ImageView imgView= convertView.findViewById(R.id.image_View);

        imgfname.setText(TempImagefirstname);
        imglast.setText(TempImagelastname);
        imgphone.setText(TempImagephone);
        imgadress.setText(TempImageadress);
        imggender.setText(TempImagegender);
        imgtime.setText(TempImagetime);
        imgdate.setText(TempImagedate);
        imgduration.setText(TempImageduration);

        //Loading image from Glide library.

        Glide.with(convertView.getContext()).load(imageUrl).dontAnimate().into(imgView);
        return convertView;
    }
}