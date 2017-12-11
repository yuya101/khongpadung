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
import com.tmadigital.khongpagung.dao.ProductDetailNewDescriptionItemDao;
import com.tmadigital.khongpagung.dao.ProductDetailNewHeaderItemDao;
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
public class ProductDetailDescriptionFragment extends Fragment {

    private static String proID;
    private SamplinkMethod samplinkMethod;
    private Button exchange_button;
    private HtmlTextView other_detail;
    private ProductDetailNewCollectionItemDao daoAll;

    public ProductDetailDescriptionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ProductDetailDescriptionFragment newInstance(String prodDao) {
        ProductDetailDescriptionFragment fragment = new ProductDetailDescriptionFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_product_detail_description, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        samplinkMethod = new SamplinkMethod();

        exchange_button = (Button) rootView.findViewById(R.id.exchange_button);
        other_detail = (HtmlTextView) rootView.findViewById(R.id.other_detail);

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
                                qr_code_image.setScaleType(ImageView.ScaleType.CENTER_CROP);

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
        TextView other_title = (TextView) rootView.findViewById(R.id.other_title);
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
        String detail = "";
        int i = 0;
        int num = 1;
        ProductDetailNewHeaderItemDao header = dao.getCategoryHeader().get(0);
        ProductDetailNewDescriptionItemDao desc = dao.getCategoryDescription().get(0);

        if(dao.getProductDescription().equals("")){
            String propertyNamehtml;

            if (Build.VERSION.SDK_INT >= 24)
            {
                propertyNamehtml = Html.fromHtml(dao.getProductDescription(), Html.FROM_HTML_MODE_LEGACY).toString();
            }
            else
            {
                propertyNamehtml = Html.fromHtml(dao.getProductDescription()).toString();
            }

            detail = propertyNamehtml;
            i = 1;
        }

        if (!header.getHeader1().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader1() + " : " + desc.getDescription1();
            num++;
        }

        if (!header.getHeader2().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader2() + " : " + desc.getDescription2();
            num++;
        }

        if (!header.getHeader3().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader3() + " : " + desc.getDescription3();
            num++;
        }

        if (!header.getHeader4().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader4() + " : " + desc.getDescription4();
            num++;
        }

        if (!header.getHeader5().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader5() + " : " + desc.getDescription5();
            num++;
        }

        if (!header.getHeader6().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader6() + " : " + desc.getDescription6();
            num++;
        }

        if (!header.getHeader7().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader7() + " : " + desc.getDescription7();
            num++;
        }

        if (!header.getHeader8().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader8() + " : " + desc.getDescription8();
            num++;
        }

        if (!header.getHeader9().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader9() + " : " + desc.getDescription9();
            num++;
        }

        if (!header.getHeader10().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader10() + " : " + desc.getDescription10();
            num++;
        }

        if (!header.getHeader11().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader11() + " : " + desc.getDescription11();
            num++;
        }

        if (!header.getHeader12().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader12() + " : " + desc.getDescription12();
            num++;
        }

        if (!header.getHeader13().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader13() + " : " + desc.getDescription13();
            num++;
        }

        if (!header.getHeader14().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader14() + " : " + desc.getDescription14();
            num++;
        }

        if (!header.getHeader15().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader15() + " : " + desc.getDescription15();
            num++;
        }

        if (!header.getHeader16().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader16() + " : " + desc.getDescription16();
            num++;
        }

        if (!header.getHeader17().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader17() + " : " + desc.getDescription17();
            num++;
        }

        if (!header.getHeader18().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader18() + " : " + desc.getDescription18();
            num++;
        }

        if (!header.getHeader19().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader19() + " : " + desc.getDescription19();
            num++;
        }

        if (!header.getHeader20().equals("")){
            if (i > 0){
                detail = detail + "\n";
            }else{
                i = 1;
            }
            detail = detail + num + "." + header.getHeader20() + " : " + desc.getDescription20();
            num++;
        }

        if (detail.equals("")){
            other_detail.setHtml("-");
        }else{
            other_detail.setHtml(detail);
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
