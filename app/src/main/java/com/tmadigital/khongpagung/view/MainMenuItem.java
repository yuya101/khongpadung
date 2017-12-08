package com.tmadigital.khongpagung.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;
import com.tmadigital.khongpagung.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainMenuItem extends BaseCustomViewGroup {


    private String memberPointShow;
    private String memberID;
    private String incartSummaryPoint;
    private ImageView menuUserPictureIV;
    private String memberFBPicture;
    private CircleImageView profileImage;
    private ImageButton home_icon_ib;
    private Button home_text_btn;
    private ImageButton product_icon_ib;
    private Button product_text_btn;
    private ImageButton about_icon_ib;
    private Button about_text_btn;
    private ImageButton activity_icon_ib;
    private Button activity_text_btn;
    private ImageButton map_icon_ib;
    private Button map_text_btn;

    public MainMenuItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public MainMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public MainMenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public MainMenuItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.framelayout_menu, this);
    }

    private void initInstances() {
        // findViewById here
        home_icon_ib = (ImageButton) findViewById(R.id.home_icon_ib);
        home_text_btn = (Button) findViewById(R.id.home_text_btn);
        product_icon_ib = (ImageButton) findViewById(R.id.product_icon_ib);
        product_text_btn = (Button) findViewById(R.id.product_text_btn);
        about_icon_ib = (ImageButton) findViewById(R.id.about_icon_ib);
        about_text_btn = (Button) findViewById(R.id.about_text_btn);
        activity_icon_ib = (ImageButton) findViewById(R.id.activity_icon_ib);
        activity_text_btn = (Button) findViewById(R.id.activity_text_btn);
        map_icon_ib = (ImageButton) findViewById(R.id.map_icon_ib);
        map_text_btn = (Button) findViewById(R.id.map_text_btn);
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

}
