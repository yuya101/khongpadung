package com.tmadigital.khongpagung.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;
import com.tmadigital.khongpagung.R;
import com.tmadigital.khongpagung.activity.LoginActivity;
import com.tmadigital.khongpagung.activity.ProductDetailActivity;
import com.tmadigital.khongpagung.dao.AddCartItemDao;
import com.tmadigital.khongpagung.manager.HttpManager;
import com.tmadigital.khongpagung.method.SamplinkMethod;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ProductListItem extends BaseCustomViewGroup {

    private ImageView product_image_iv;
    private TextView product_name_tv;
    private Button show_more_detail_btn;
    private String proID;
    private SamplinkMethod samplinkMethod;

    public ProductListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public ProductListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public ProductListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public ProductListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_product_item, this);
    }

    private void initInstances() {
        // findViewById here
        samplinkMethod = new SamplinkMethod();

        product_image_iv = (ImageView) findViewById(R.id.product_image_iv);
        product_name_tv = (TextView) findViewById(R.id.product_name_tv);
        show_more_detail_btn = (Button) findViewById(R.id.show_more_detail_btn);

        show_more_detail_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                intent.putExtra("proID", proID);
                getContext().startActivity(intent);
            }
        });
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int fullwidth = MeasureSpec.getSize(widthMeasureSpec); // width in px
        int width = fullwidth;
        int height = (int)(fullwidth * 1.45);

        int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                width,
                MeasureSpec.EXACTLY
        );

        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                height,
                MeasureSpec.EXACTLY
        );

        //---- Send Width Height To Child Views
        super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec);

        //self Width And Height
        setMeasuredDimension(width, height);  //----- Scale In px
    }


    public void setImageUrl(String url) {
        //---- Load External Library Glide
        Glide.with(getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.white)
                .crossFade()
                .centerCrop()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        product_image_iv.setImageResource(0);
                        product_image_iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

                        return false;
                    }
                })
                .into(product_image_iv);
    }

    public void setTvProductName(String text) {
        product_name_tv.setText(text);
    }
    public void setProductID(String text) {proID = text;}
}
