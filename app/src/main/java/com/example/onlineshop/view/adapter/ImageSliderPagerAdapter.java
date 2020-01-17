package com.example.onlineshop.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.onlineshop.model.ImagesItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageSliderPagerAdapter extends PagerAdapter {
    private List<ImagesItem> imagesItems;
    private Context mContext;

    public ImageSliderPagerAdapter(List<ImagesItem> imagesItems, Context mContext) {
        this.imagesItems = imagesItems;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return imagesItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ImageView) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //return super.instantiateItem(container, position);
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.get()
                .load(imagesItems.get(position).getSrc())
                .into(imageView);
        //imageView.setImageResource(sliderImageId[position]);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       // super.destroyItem(container, position, object);
        ((ViewPager) container).removeView((ImageView) object);
    }
}
