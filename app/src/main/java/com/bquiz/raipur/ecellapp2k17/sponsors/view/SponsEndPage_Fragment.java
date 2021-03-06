package com.bquiz.raipur.ecellapp2k17.sponsors.view;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bquiz.raipur.ecellapp2k17.R;
import com.bquiz.raipur.ecellapp2k17.sponsors.model.data.SponsData;
import com.wang.avi.AVLoadingIndicatorView;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by vrihas on 6/27/2017.
 */

public class SponsEndPage_Fragment extends android.support.v4.app.DialogFragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button button;
    private ImageView image1,bg_spons,downArrow;
    private SponsData sponsData;
    private TextView textTitle,spons_desc;
    private Context context;
    private AVLoadingIndicatorView progressBar;
    private String url = "http://google.com/";

    public SponsEndPage_Fragment(){
        //Required empty constructor
    }


    public static SponsEndPage_Fragment newInstance(String param1, String param2) {
        SponsEndPage_Fragment sponsEndPage_fragment = new SponsEndPage_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        sponsEndPage_fragment.setArguments(args);
        return sponsEndPage_fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
            d.getWindow().setWindowAnimations(R.style.Dialog_anim);
        }
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog_theme);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.spons_end_page, container, false);
        image1 = (ImageView) view.findViewById(R.id.sponsImage);
        downArrow = (ImageView) view.findViewById(R.id.down_arrow);
        bg_spons = (ImageView) view.findViewById(R.id.bgSpons);
        textTitle = (TextView) view.findViewById(R.id.sponsTitle);
        spons_desc = (TextView) view.findViewById(R.id.sponsDesc);
        button = (Button) view.findViewById(R.id.sponsButton);
        progressBar = (AVLoadingIndicatorView) view.findViewById(R.id.progressBar_spons_end);
        textTitle.setText(sponsData.getSponsName());
        spons_desc.setText(sponsData.getBody());
        Glide.with(this).load(R.drawable.spons_endpage).into(bg_spons);//  sponsData.getBg_spons()
        int radius = 30; // corner radius, higher value = more rounded
        int margin = 5;
        Glide.with(this)
                .load(sponsData.getImage1()) .listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }) //sponsData.getImage1
                .bitmapTransform(new RoundedCornersTransformation(context, radius, margin))
                .into(image1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(getContext(),Uri.parse(url));

            }
        });

        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SponsEndPage_Fragment.this.dismiss();
            }
        });

        return view;
    }


    public void setData(final SponsData data) {
        sponsData=data;
    }

}


