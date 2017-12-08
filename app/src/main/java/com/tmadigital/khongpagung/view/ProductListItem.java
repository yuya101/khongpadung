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
import com.tmadigital.khongpagung.dao.AddCartItemDao;
import com.tmadigital.khongpagung.manager.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ProductListItem extends BaseCustomViewGroup {
    ImageView ivProductImage;
    TextView tvProductName;
    ImageView ivStar1;
    ImageView ivStar2;
    ImageView ivStar3;
    ImageView ivStar4;
    ImageView ivStar5;
    TextView tvProductPoint;
    Button btnGetProduct;
    String productID;
    private String memberID;

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
        ivProductImage = (ImageView) findViewById(R.id.ivProductImage);
        tvProductName = (TextView) findViewById(R.id.tvProductName);
        ivStar1 = (ImageView) findViewById(R.id.ivStar1);
        ivStar2 = (ImageView) findViewById(R.id.ivStar2);
        ivStar3 = (ImageView) findViewById(R.id.ivStar3);
        ivStar4 = (ImageView) findViewById(R.id.ivStar4);
        ivStar5 = (ImageView) findViewById(R.id.ivStar5);
        tvProductPoint = (TextView) findViewById(R.id.tvProductPoint);
        btnGetProduct = (Button) findViewById(R.id.btnGetProduct);

        btnGetProduct.setOnClickListener(addToCartListener);


        SharedPreferences sp = getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
        memberID = String.valueOf(sp.getInt("memberID", 0));
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
//                .placeholder(R.color.white)
                .crossFade()
                .override(400,400)
                .centerCrop()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        ivProductImage.setImageResource(0);
                        ivProductImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

                        return false;
                    }
                })
                .into(ivProductImage);

        /*
           TODO: Glide Format Using Function
           // Insert after .load()
           .placeholder(Image From Drawable) = Show Image Before Real Image Load Complete
           .error(Image From Drawable) = Show Image When Image Load Error
           .transform() = Use for Make Source Image Mark By Circle Transformation Like Circle Image (Use In Menu User Image Circle)

           We can download transformation from github to use with .transform() - Name Of Source is "glide-transformations". Can Load By Library Dependency

           .diskCacheStrategy(DiskCacheStrategy.ALL) = Use In case This Image Use More Than 1 Times, If This Image Use 1 Time We not use this Method
         */
    }

    public void setTvProductName(String text) {
        tvProductName.setText(text);
    }

    public void setTvProductPoint(String text) {
        tvProductPoint.setText(text);
    }

    public void setProductPosition(String proID) {productID = proID;}


    private void showToast(String text){
        Toast.makeText(Contextor.getInstance().getContext(),
                text,
                Toast.LENGTH_SHORT)
                .show();
    }


    final OnClickListener addToCartListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Integer.parseInt(memberID) > 0) {
                Call<AddCartItemDao> call = HttpManager.getInstance().getAddCartApiService().updateAddCartData(memberID, productID);
                call.enqueue(new Callback<AddCartItemDao>() {
                    @Override
                    public void onResponse(Call<AddCartItemDao> call,
                                           Response<AddCartItemDao> response) {
                        if (response.isSuccessful()){
                            AddCartItemDao cartItemDao = response.body();

                            if (cartItemDao.getStatus().toString().equals("success")){
                                showToast("เพิ่มสินค้าลงตะกร้าเรียบร้อยแล้วค่ะ.");
                            }else{
                                showToast("ไม่สามารถเพิ่มสินค้าลงตะกร้าได้ค่ะ\nกรุณาลองใหม่อีกครั้ง !");
                            }  //------  if (cartItemDao.getStatus().toString().equals("success"))
                        }else{
                            // Handle
                            try {
                                showToast(response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }  //----  if (response.isSuccessful())
                    }

                    @Override
                    public void onFailure(Call<AddCartItemDao> call,
                                          Throwable t) {
                        showToast(t.toString());
                    }
                });
            }else{
                Intent intent = new Intent(getContext(), LoginActivity.class);
                getContext().startActivity(intent);
            }  //-------  if (Integer.parseInt(memberID) > 0)
        }
    };
}
