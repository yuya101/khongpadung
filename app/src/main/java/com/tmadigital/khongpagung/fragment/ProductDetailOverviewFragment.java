package com.tmadigital.khongpagung.fragment;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tmadigital.khongpagung.R;
import com.tmadigital.khongpagung.dao.ProductAllItemDao;
import com.tmadigital.khongpagung.dao.ProductDetailNewCollectionItemDao;
import com.tmadigital.khongpagung.manager.HttpManager;
import com.tmadigital.khongpagung.method.SamplinkMethod;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class ProductDetailOverviewFragment extends Fragment {

    private static String proID;
    private ImageView product_image_iv;
    private TextView product_name_tv;
    private HtmlTextView property_detail;
    private Button exchange_button;
    private SamplinkMethod samplinkMethod;
    private TextView other_title;
    private ProductDetailNewCollectionItemDao daoAll;

    public ProductDetailOverviewFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ProductDetailOverviewFragment newInstance(String prodDao) {
        ProductDetailOverviewFragment fragment = new ProductDetailOverviewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        proID = prodDao;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_detail_overview, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(final View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        samplinkMethod = new SamplinkMethod();

        product_image_iv = (ImageView) rootView.findViewById(R.id.product_image_iv);
        product_name_tv = (TextView) rootView.findViewById(R.id.product_name_tv);
        property_detail = (HtmlTextView) rootView.findViewById(R.id.property_detail);
        other_title = (TextView) rootView.findViewById(R.id.other_title);
        exchange_button = (Button) rootView.findViewById(R.id.exchange_button);

        exchange_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_qr_code_show);
                dialog.setCancelable(true);

                TextView qr_product_name = (TextView) dialog.findViewById(R.id.qr_product_name);
                TextView qr_product_code = (TextView) dialog.findViewById(R.id.qr_product_code);
                final ImageView qr_code_image = (ImageView) dialog.findViewById(R.id.qr_code_image);

                Glide.with(getContext())
                        .load(daoAll.getProductDetail().get(0).getQrcode())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade()
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model,
                                                       Target<GlideDrawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource,
                                                           String model, Target<GlideDrawable> target,
                                                           boolean isFromMemoryCache, boolean isFirstResource) {
                                qr_code_image.setImageResource(0);
                                //qr_code_image.setScaleType(ImageView.ScaleType.CENTER_CROP);

                                return false;
                            }
                        })
                        .into(qr_code_image);

                qr_product_name.setText(daoAll.getProductDetail().get(0).getName());
                qr_product_code.setText(daoAll.getProductDetail().get(0).getProductCode());

                Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(),  "fonts/Kanit-Bold.ttf");
                qr_product_code.setTypeface(custom_font);
                qr_product_code.setIncludeFontPadding(false);

                dialog.show();
            }
        });

        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(),  "fonts/Kanit-Bold.ttf");
        other_title.setTypeface(custom_font);
        other_title.setIncludeFontPadding(false);

        Call<ProductDetailNewCollectionItemDao> call = HttpManager.getInstance().getProductDetailNewApiService().loadProductDetailData(proID);
        call.enqueue(new Callback<ProductDetailNewCollectionItemDao>() {
            @Override
            public void onResponse(Call<ProductDetailNewCollectionItemDao> call, Response<ProductDetailNewCollectionItemDao> response) {
                if (response.isSuccessful()){
                    daoAll = response.body();
                    insertData(daoAll);
                }else{
                    //Handle
                    try {
                        samplinkMethod.showToast(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }  //-----  if (response.isSuccessful())
            }

            @Override
            public void onFailure(Call<ProductDetailNewCollectionItemDao> call, Throwable t) {
                samplinkMethod.showToast(t.toString());
            }
        });
    }

    private void insertData(ProductDetailNewCollectionItemDao dao) {
        //---- Load External Library Glide
        Glide.with(getContext())
                .load(dao.getProductDetail().get(0).getPicture1())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //.placeholder(getResources().getDrawable(R.drawable.splash_title))
                .crossFade()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model,
                                               Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource,
                                                   String model, Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        product_image_iv.setImageResource(0);
                        //product_image_iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

                        return false;
                    }
                })
                .into(product_image_iv);


        product_name_tv.setText(dao.getProductDetail().get(0).getName());

        if (dao.getProductDetail().get(0).getProperty().equals("")){
            property_detail.setText("-");
        }else{
            String propertyNamehtml;

            if (Build.VERSION.SDK_INT >= 24)
            {
                propertyNamehtml = Html.fromHtml(dao.getProductDetail().get(0).getProperty(), Html.FROM_HTML_MODE_LEGACY).toString();
//                propertyNamehtml = propertyNamehtml.replace("&AMP;", "&");
//                propertyNamehtml = propertyNamehtml.replace("<p>", "");
//                propertyNamehtml = propertyNamehtml.replace("</p>", "");
//                propertyNamehtml = propertyNamehtml.replace("&nbsp;", " ");
//                propertyNamehtml = propertyNamehtml.replace("<br>", "");
//                propertyNamehtml = propertyNamehtml.replace("</br>", "");
//                propertyNamehtml = propertyNamehtml.replace("<br />", "\n");
            }
            else
            {
                propertyNamehtml = Html.fromHtml(dao.getProductDetail().get(0).getProperty()).toString();
//                propertyNamehtml = propertyNamehtml.replace("&AMP;", "&");
//                propertyNamehtml = propertyNamehtml.replace("<p>", "");
//                propertyNamehtml = propertyNamehtml.replace("</p>", "");
//                propertyNamehtml = propertyNamehtml.replace("&nbsp;", " ");
//                propertyNamehtml = propertyNamehtml.replace("<br>", "");
//                propertyNamehtml = propertyNamehtml.replace("</br>", "");
//                propertyNamehtml = propertyNamehtml.replace("<br />", "\n");
            }
            property_detail.setHtml(propertyNamehtml);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

}
