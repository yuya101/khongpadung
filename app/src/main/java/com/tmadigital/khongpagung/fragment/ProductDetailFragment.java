package com.tmadigital.khongpagung.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tmadigital.khongpagung.R;
import com.tmadigital.khongpagung.activity.CartActivity;
import com.tmadigital.khongpagung.activity.LoginActivity;
import com.tmadigital.khongpagung.dao.AddCartItemDao;
import com.tmadigital.khongpagung.dao.ProductAllItemDao;
import com.tmadigital.khongpagung.dao.ProductDetailItemCollectionDao;
import com.tmadigital.khongpagung.dao.ProductDetailItemDao;
import com.tmadigital.khongpagung.dao.ProductDetailReviewItemDao;
import com.tmadigital.khongpagung.manager.HttpManager;
import com.tmadigital.khongpagung.manager.ProductDetailManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class ProductDetailFragment extends Fragment {

    private ImageButton button;
    private ImageView productDetailImageIV;
    private TextView tvProductName;
    private TextView rateCountShowTV;
    private TextView productDetailPropertyContentTV;
    private TextView productDetailPriceShowTV;
    private TextView reviewTopicTV;
    private TableLayout productDetailShowReviewTableLayout;
    private String memberID;

    ProductAllItemDao dao;

    public ProductDetailFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ProductDetailFragment newInstance(ProductAllItemDao dao) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao", dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        dao = getArguments().getParcelable("dao");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        button = (ImageButton) rootView.findViewById(R.id.redeemProductBtn);
        button.setOnClickListener(redeemonclicklistener);


        //  Init Item On View fragment_product_detail
        productDetailImageIV = (ImageView) rootView.findViewById(R.id.productDetailImageIV);
        tvProductName = (TextView) rootView.findViewById(R.id.tvProductName);
        rateCountShowTV = (TextView) rootView.findViewById(R.id.rateCountShowTV);
        productDetailPropertyContentTV = (TextView) rootView.findViewById(R.id.productDetailPropertyContentTV);
        productDetailPriceShowTV = (TextView) rootView.findViewById(R.id.productDetailPriceShowTV);
        reviewTopicTV = (TextView) rootView.findViewById(R.id.reviewTopicTV);
        productDetailShowReviewTableLayout = (TableLayout) rootView.findViewById(R.id.productDetailShowReviewTableLayout);


        Call<ProductDetailItemCollectionDao> call = HttpManager.getInstance().getProductDetailService().loadProductDetailData(dao.getProid());
        call.enqueue(new Callback<ProductDetailItemCollectionDao>() {
            @Override
            public void onResponse(Call<ProductDetailItemCollectionDao> call,
                                   Response<ProductDetailItemCollectionDao> response) {
                if (response.isSuccessful()){
                    ProductDetailItemCollectionDao productdetaildao = response.body();
                    ProductDetailManager.getInstance().setDao(productdetaildao);  // เตรียมไว้ใช้ในหน้าหลังเมื่อต้องการใช้งานข้อมูล Product ที่เลือกไว้

                    ProductDetailItemDao productDao = productdetaildao.getProductDetail().get(0);


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    {
                        Spanned productPropertyhtml = Html.fromHtml(productDao.getProperty().toString(), Html.FROM_HTML_MODE_LEGACY);
                        productPropertyhtml = Html.fromHtml(String.valueOf(productPropertyhtml), Html.FROM_HTML_MODE_LEGACY);
                        //productDetailPropertyContentTV.setText(Html.fromHtml(productPropertyhtml , Html.FROM_HTML_MODE_LEGACY));
                        productDetailPropertyContentTV.setText(productPropertyhtml);


                        String productNamehtml = Html.fromHtml(productDao.getName().toString(), Html.FROM_HTML_MODE_LEGACY).toString();
                        productNamehtml = productNamehtml.replace("&AMP;", "&");
                        tvProductName.setText(productNamehtml);
                    }
                    else
                    {
                        String productPropertyhtml = Html.fromHtml(productDao.getProperty().toString()).toString();
                        productDetailPropertyContentTV.setText(Html.fromHtml(productPropertyhtml));

                        String productNamehtml = Html.fromHtml(productDao.getName().toString()).toString();
                        productNamehtml = productNamehtml.replace("&AMP;", "&");
                        tvProductName.setText(Html.fromHtml(productNamehtml));
                    }


                    rateCountShowTV.setText("(" + (productDao.getClickCount().toString()) + ")");
                    productDetailPriceShowTV.setText(productDao.getPrice().toString() + " P");
                    Glide.with(ProductDetailFragment.this)
                            .load(productDao.getPicture())
                            //.placeholder(R.drawable.loading)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .override(400,400)
                            .listener(new RequestListener<String, GlideDrawable>() {
                                @Override
                                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                    productDetailImageIV.setImageResource(0);
                                    productDetailImageIV.setScaleType(ImageView.ScaleType.CENTER_CROP);

                                    return false;
                                }
                            })
                            .into(productDetailImageIV);

                    SharedPreferences sp = getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                    memberID = String.valueOf(sp.getInt("memberID", 0));


                    if (productdetaildao.getReview().get(0).getReviewstatus().toString().equals("Found")){
                        createShowReviewProductLayout(productdetaildao);
                    }else{
                        reviewTopicTV.setVisibility(View.GONE);
                    }  //----  if (productdetaildao.getReview().get(0).getReviewstatus().toString().equals("Found"))
                }else{
                    // Handle
                    try {
                        showToast(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductDetailItemCollectionDao> call,
                                  Throwable t) {
                showToast(t.toString());
            }
        });
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_product_detail, menu);

        MenuItem menuItem = (MenuItem) menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        shareActionProvider.setShareIntent(getShareIntent());
    }

    private Intent getShareIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "สินค้าน่าสนใจจาก Samplink");
        intent.putExtra(Intent.EXTRA_TEXT, "ลองดูสินค้าตัวนี้นะ น่าสนใจมากๆ");
        return intent;
    }

    private void showToast(String text){
        Toast.makeText(Contextor.getInstance().getContext(),
                text,
                Toast.LENGTH_SHORT)
                .show();
    }

    private int convertPixelToDP(int i) {
        float scale = getResources().getDisplayMetrics().density;
        int dpInPixel = (int) (i * scale + 0.5f);

        return dpInPixel;
    }


    private void createShowReviewProductLayout(ProductDetailItemCollectionDao productdetaildao) {
        int productReviewCount = productdetaildao.getReview().size();
        ProductDetailReviewItemDao reviewProductDao;
        int j = 0;

        for (int i=0; i<productReviewCount; i++){
            reviewProductDao = productdetaildao.getReview().get(i);

            TableRow row = new TableRow(getContext());
            TableLayout.LayoutParams tp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(tp);
            row.setPadding(0, 0, 0, 0);

            LinearLayout reviewLinearLayout = new LinearLayout(getContext());
            LinearLayout.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            reviewLinearLayout.setLayoutParams(lp);
            reviewLinearLayout.setOrientation(LinearLayout.VERTICAL);
            reviewLinearLayout.setPadding(0, convertPixelToDP(10), 0, convertPixelToDP(10));


            TextView productDetailReviewTopic = new TextView(getContext());
            productDetailReviewTopic.setLayoutParams(new LinearLayout.LayoutParams(convertPixelToDP(330),
                                                    LinearLayout.LayoutParams.WRAP_CONTENT));
            productDetailReviewTopic.setPadding(0, convertPixelToDP(5), 0, convertPixelToDP(5));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {
                Spanned reviewTopichtml = Html.fromHtml(reviewProductDao.getReviewTopic().toString(), Html.FROM_HTML_MODE_LEGACY);
                reviewTopichtml = Html.fromHtml(String.valueOf(reviewTopichtml), Html.FROM_HTML_MODE_LEGACY);
                //productDetailReviewTopic.setText(Html.fromHtml(reviewTopichtml , Html.FROM_HTML_MODE_LEGACY));
                productDetailReviewTopic.setText(reviewTopichtml);
            }
            else
            {
                String reviewTopichtml = Html.fromHtml(reviewProductDao.getReviewTopic().toString()).toString();
                productDetailReviewTopic.setText(Html.fromHtml(reviewTopichtml));
            }  //----  if (Build.VERSION.SDK_INT >= 24)

            productDetailReviewTopic.setMaxLines(1);
            productDetailReviewTopic.setEllipsize(TextUtils.TruncateAt.END);
            productDetailReviewTopic.setTextColor(getResources().getColor(R.color.blackGray));
            productDetailReviewTopic.setTextSize(convertPixelToDP(6));
            productDetailReviewTopic.setTypeface(null, Typeface.BOLD);
            productDetailReviewTopic.setBackgroundColor(Color.TRANSPARENT);
            reviewLinearLayout.addView(productDetailReviewTopic);


            LinearLayout reviewStarLinearLayout = new LinearLayout(getContext());
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                    LinearLayout.LayoutParams.WRAP_CONTENT);
            reviewStarLinearLayout.setLayoutParams(lp2);
            reviewStarLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            reviewStarLinearLayout.setPadding(0, convertPixelToDP(5), 0, convertPixelToDP(5));

            for(int z=1; z<=5; z++){
                ImageView starImage = new ImageView(getContext());
                starImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                starImage.setImageResource(R.drawable.starpink);
                starImage.setPadding(0, 0, convertPixelToDP(10), 0);
                reviewStarLinearLayout.addView(starImage);
            }  //-----  for(int z=1; z<5; z++)

            reviewLinearLayout.addView(reviewStarLinearLayout);


            TextView productDetailReviewContent = new TextView(getContext());
            productDetailReviewContent.setLayoutParams(new LinearLayout.LayoutParams(convertPixelToDP(330),
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            productDetailReviewContent.setPadding(0, 0, 0, convertPixelToDP(10));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {
                Spanned reviewTopichtml = Html.fromHtml(reviewProductDao.getReviewDetail().toString(), Html.FROM_HTML_MODE_LEGACY);
                //productDetailReviewContent.setText(Html.fromHtml(reviewTopichtml , Html.FROM_HTML_MODE_LEGACY));
                productDetailReviewContent.setText(reviewTopichtml);
            }
            else
            {
                String reviewTopichtml = Html.fromHtml(reviewProductDao.getReviewDetail().toString()).toString();
                productDetailReviewContent.setText(Html.fromHtml(reviewTopichtml));
            }  //----  if (Build.VERSION.SDK_INT >= 24)

            productDetailReviewContent.setText(reviewProductDao.getReviewDetail().toString());
            //productDetailReviewContent.setMaxLines(1);
            productDetailReviewContent.setEllipsize(TextUtils.TruncateAt.END);
            productDetailReviewContent.setTextColor(getResources().getColor(R.color.blackGray));
            productDetailReviewContent.setTextSize(convertPixelToDP(5));
            //productDetailReviewContent.setTypeface(null, Typeface.BOLD);
            productDetailReviewContent.setBackgroundColor(Color.TRANSPARENT);
            reviewLinearLayout.addView(productDetailReviewContent);


            TextView productDetailReviewName = new TextView(getContext());
            productDetailReviewName.setLayoutParams(new LinearLayout.LayoutParams(convertPixelToDP(330),
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            productDetailReviewName.setPadding(0, convertPixelToDP(5), 0, convertPixelToDP(5));
            productDetailReviewName.setText("Posted By : " + reviewProductDao.getMemberName().toString());
            productDetailReviewName.setMaxLines(1);
            productDetailReviewName.setEllipsize(TextUtils.TruncateAt.END);
            productDetailReviewName.setTextColor(getResources().getColor(R.color.blackGray));
            productDetailReviewName.setTextSize(convertPixelToDP(4));
            productDetailReviewName.setTypeface(null, Typeface.BOLD);
            productDetailReviewName.setBackgroundColor(Color.TRANSPARENT);
            reviewLinearLayout.addView(productDetailReviewName);


            TextView productDetailReviewTime = new TextView(getContext());
            productDetailReviewTime.setLayoutParams(new LinearLayout.LayoutParams(convertPixelToDP(330),
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            productDetailReviewTime.setPadding(0, convertPixelToDP(5), 0, convertPixelToDP(5));
            productDetailReviewTime.setText("Posted Date : " + reviewProductDao.getReviewDate().toString());
            productDetailReviewTime.setMaxLines(1);
            productDetailReviewTime.setEllipsize(TextUtils.TruncateAt.END);
            productDetailReviewTime.setTextColor(getResources().getColor(R.color.blackGray));
            productDetailReviewTime.setTextSize(convertPixelToDP(4));
            productDetailReviewTime.setTypeface(null, Typeface.BOLD);
            productDetailReviewTime.setBackgroundColor(Color.TRANSPARENT);
            reviewLinearLayout.addView(productDetailReviewTime);


            row.addView(reviewLinearLayout);
            productDetailShowReviewTableLayout.addView(row,j);

            j++;



            TableRow row2 = new TableRow(getContext());
            TableRow.LayoutParams tp2 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            row2.setLayoutParams(tp2);
            row2.setPadding(0, convertPixelToDP(5), 0, convertPixelToDP(10));


            View line = new View(getContext());
            TableRow.LayoutParams lp3 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, convertPixelToDP(2));
            line.setLayoutParams(lp3);
            line.setBackgroundResource(R.color.overlay_color);
            row2.addView(line);
            productDetailShowReviewTableLayout.addView(row2,j);

            j++;

        }  //------  for (int i=0; i<productReviewCount; i++)
    }

    /*
         * Restore Instance State Here
    */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }


    final View.OnClickListener redeemonclicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Integer.parseInt(memberID) > 0){
                Call<AddCartItemDao> call = HttpManager.getInstance().getAddCartApiService().updateAddCartData(memberID, dao.getProid());
                call.enqueue(new Callback<AddCartItemDao>() {
                    @Override
                    public void onResponse(Call<AddCartItemDao> call,
                                           Response<AddCartItemDao> response) {
                        if (response.isSuccessful()){
                            AddCartItemDao cartItemDao = response.body();

                            if (cartItemDao.getStatus().toString().equals("success")){
                                Intent intent = new Intent(getActivity(), CartActivity.class);
                                startActivity(intent);
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
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }  //------- if (Integer.parseInt(memberID) > 0)
        }
    };

}
