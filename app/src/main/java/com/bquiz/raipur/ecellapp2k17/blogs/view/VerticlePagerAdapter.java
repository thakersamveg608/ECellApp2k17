package com.bquiz.raipur.ecellapp2k17.blogs.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bquiz.raipur.ecellapp2k17.helper.image_loaders.GlideImageLoader;
import com.bquiz.raipur.ecellapp2k17.helper.image_loaders.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bquiz.raipur.ecellapp2k17.R;
import com.bquiz.raipur.ecellapp2k17.blogs.model.data.BlogData;
import com.tomergoldst.tooltips.ToolTip;
import com.tomergoldst.tooltips.ToolTipsManager;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class VerticlePagerAdapter extends PagerAdapter  {


    private List<BlogData> blogDataList = new ArrayList<>();
    Context mContext;
    LayoutInflater mLayoutInflater;
    private ImageLoader imageLoader;
    private CardView blogCard;
    private int length;
    private AVLoadingIndicatorView progressBar;

    public VerticlePagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new GlideImageLoader(mContext);
    }

    @Override
    public int getCount() {
        return this.blogDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    void setBlogDataList(List<BlogData> blogDataList)
    {
        this.blogDataList=blogDataList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.blog_card, container, false);

        blogCard = (CardView) itemView.findViewById(R.id.blogCard);
        final BlogData data=blogDataList.get(position);


        TextView title = (TextView) itemView.findViewById(R.id.blog_title);
//        TextView owner=(TextView)itemView.findViewById(R.id.blog_owner);
        TextView date=(TextView)itemView.findViewById(R.id.blog_date);
        TextView body=(TextView)itemView.findViewById(R.id.blog_body);
        TextView read_more=(TextView)itemView.findViewById(R.id.blog_read_more);
        ImageView blogImage= (ImageView) itemView.findViewById(R.id.blog_image);
        progressBar = (AVLoadingIndicatorView) itemView.findViewById(R.id.progressBar_blogs);
        RelativeLayout layout = (RelativeLayout) itemView.findViewById(R.id.blog_relative_layout);

        length = (data.getBody()).length();
        if(length>270)
        {
            read_more.setVisibility(View.VISIBLE);
        }
        else
        {
            read_more.setVisibility(View.INVISIBLE);
            read_more.setClickable(false);
        }

        imageLoader.loadImage(data.getImage(),blogImage,progressBar);
        title.setText(data.getTitle());
        date.setText(data.getDate());
        body.setText(Html.fromHtml(Html.fromHtml(data.getBody()).toString()));


        read_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = (FragmentActivity)(mContext);
                FragmentManager fm = activity.getSupportFragmentManager();
                ReadMoreBlogsFragment readMoreBlogsFragment = new ReadMoreBlogsFragment();
                readMoreBlogsFragment.setData(data);
                readMoreBlogsFragment.show(fm, "read_more");

                /*
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                try {
                    customTabsIntent.launchUrl(mContext, Uri.parse(data.getUrl()));
                }
                catch (Exception e)
                {
                    Toast.makeText(mContext, "Error opening custom tabs", Toast.LENGTH_SHORT).show();
                }
                */


            }
        });
        container.addView(itemView);





        final ToolTipsManager mToolTipsManager;
        mToolTipsManager = new ToolTipsManager();
        final ToolTip.Builder builder = new ToolTip.Builder(mContext,title,layout, "Swipe up to read next blog." , ToolTip.POSITION_ABOVE);
        builder.setBackgroundColor(R.color.black);

        if(position==0) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mToolTipsManager.show(builder.build());
                }
            },3000);
        }


        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
