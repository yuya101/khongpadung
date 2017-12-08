package com.tmadigital.khongpagung.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.tmadigital.khongpagung.activity.CheckOutActivity;
import com.tmadigital.khongpagung.activity.LoginActivity;
import com.tmadigital.khongpagung.activity.MainActivity;
import com.tmadigital.khongpagung.dao.ProductInCartItemCollectionDao;
import com.tmadigital.khongpagung.dao.ProductInCartItemDao;
import com.tmadigital.khongpagung.dao.RemoveProductInCartItemDao;
import com.tmadigital.khongpagung.manager.HttpManager;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class CartFragment extends Fragment {

    private Button cashOutBtn;
    private TextView cartSummaryPointTV;
    private TableLayout cartShowProductTableLayout;
    private String memberID;
    private TextView cartMemberRemainPointTV;
    private Integer summaryPoint = 0;
    private Integer memberPoint = 0;
    private Button continueShoppingBtn;

    public CartFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        SharedPreferences sp = getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
        memberID = String.valueOf(sp.getInt("memberID", 0));

        if (Integer.parseInt(memberID) > 0) {
            cashOutBtn = (Button) rootView.findViewById(R.id.cashOutBtn);
            cashOutBtn.setOnClickListener(cashOutListener);

            continueShoppingBtn = (Button) rootView.findViewById(R.id.continueShoppingBtn);
            continueShoppingBtn.setOnClickListener(continueShoppingListener);

            cartSummaryPointTV = (TextView) rootView.findViewById(R.id.cartSummaryPointTV);
            cartShowProductTableLayout = (TableLayout) rootView.findViewById(R.id.cartShowProductTableLayout);
            cartMemberRemainPointTV = (TextView) rootView.findViewById(R.id.cartMemberRemainPointTV);

            Call<ProductInCartItemCollectionDao> call = HttpManager.getInstance().getProductInCartApiService().loadProductInCartData(memberID);
            call.enqueue(new Callback<ProductInCartItemCollectionDao>() {
                @Override
                public void onResponse(Call<ProductInCartItemCollectionDao> call, Response<ProductInCartItemCollectionDao> response) {
                    if (response.isSuccessful()){
                        ProductInCartItemCollectionDao inCartDao = response.body();

                        if (inCartDao.getStatus().toString().equals("success")){
                            String productInCartSummary = inCartDao.getProductIncartSummarys().toString();

                            SharedPreferences spSummary = getContext().getSharedPreferences("productSummary", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editorSummary = spSummary.edit();
                            editorSummary.putInt("productInCartSummary", Integer.parseInt(productInCartSummary));
                            editorSummary.apply();

                            createShowProductLayout(inCartDao);
                        }else{
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }  //----  if (inCartDao.getStatus().toString().equals("success"))
                    }else{
                        // Handle
                        try {
                            showToast(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }  //---  if (response.isSuccessful())
                }

                @Override
                public void onFailure(Call<ProductInCartItemCollectionDao> call, Throwable t) {
                    showToast(t.toString());
                }
            });
        }else{
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }  //------  if (Integer.parseInt(memberID) > 0)
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

    private void showToast(String text){
        Toast.makeText(Contextor.getInstance().getContext(),
                text,
                Toast.LENGTH_SHORT)
                .show();
    }


    private void createShowProductLayout(ProductInCartItemCollectionDao inCartDao) {
        int productInCartCount = inCartDao.getProductInCart().size();
        ProductInCartItemDao inCart;

        /*NumberFormat nf = NumberFormat.getCurrencyInstance();
        String pattern = ((DecimalFormat) nf).toPattern();
        String newPattern = pattern.replace("\u00A4", "").trim();
        NumberFormat newFormat = new DecimalFormat(newPattern);*/

        Typeface fontRSU = Typeface.createFromAsset(getActivity().getAssets(), "fonts/RSU_Regular.ttf");

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setCurrencySymbol(""); // Don't use null.
        formatter.setDecimalFormatSymbols(symbols);
        //formatter.setParseIntegerOnly(true);

        int j = 0;

        for (int i = 0; i < (productInCartCount); i++) {
            inCart = inCartDao.getProductInCart().get(i);
            summaryPoint = summaryPoint + (Integer.parseInt(inCart.getPrice()));
            memberPoint = inCart.getMemberPoint();

            TableRow row = new TableRow(getContext());
            TableRow.LayoutParams tp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(tp);
            row.setPadding(0, convertPixelToDP(15), 0, 0);

            // Start Create Product Detail
            final ImageView productImage = new ImageView(getContext());
            productImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //productImage.setImageResource(R.drawable.sk2);
            Glide.with(CartFragment.this)
                    .load(inCart.getPicture())
                    .placeholder(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            productImage.setImageResource(0);
                            productImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

                            return false;
                        }
                    })
                    .centerCrop()
                    .into(productImage);
            row.addView(productImage);
            productImage.getLayoutParams().height = convertPixelToDP(110);
            productImage.getLayoutParams().width = convertPixelToDP(110);

            LinearLayout productListLayout = new LinearLayout(getContext());
            LinearLayout.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            productListLayout.setLayoutParams(lp);
            productListLayout.setOrientation(LinearLayout.VERTICAL);
            productListLayout.setPadding(convertPixelToDP(10), 0, convertPixelToDP(10), 0);
            //productListLayout.setBackgroundResource(R.drawable.darkgrayblank);

            TextView productListName = new TextView(getContext());
            productListName.setLayoutParams(new LinearLayout.LayoutParams(convertPixelToDP(210), LinearLayout.LayoutParams.WRAP_CONTENT));
            productListName.setText(inCart.getName());
            productListName.setMaxLines(3);
            productListName.setEllipsize(TextUtils.TruncateAt.END);
            productListName.setTextColor(getResources().getColor(R.color.blackGray));
            productListName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            productListName.setTypeface(null, Typeface.BOLD);
            productListName.setBackgroundColor(Color.TRANSPARENT);
            productListName.setTypeface(fontRSU);
            productListLayout.addView(productListName);


            TextView productListPoint = new TextView(getContext());
            productListPoint.setLayoutParams(new LinearLayout.LayoutParams(convertPixelToDP(210), LinearLayout.LayoutParams.WRAP_CONTENT));
            productListPoint.setText(inCart.getPrice().toString() + " Points");
            productListPoint.setMaxLines(1);
            productListPoint.setEllipsize(TextUtils.TruncateAt.END);
            productListPoint.setTextColor(getResources().getColor(R.color.blackGray));
            productListPoint.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19);
            productListPoint.setTypeface(null, Typeface.BOLD);
            productListPoint.setPadding(0, convertPixelToDP(10), 0, convertPixelToDP(10));
            productListPoint.setBackgroundColor(Color.TRANSPARENT);
            productListPoint.setTypeface(fontRSU);
            productListLayout.addView(productListPoint);


            final String deleteProductID = inCart.getProid();

            Button productListDeleteBtn = new Button(getContext());
            productListDeleteBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                                                LinearLayout.LayoutParams.WRAP_CONTENT));
            productListDeleteBtn.setText(R.string.delete_product_in_cart_text);
            productListDeleteBtn.setId(i);
            productListDeleteBtn.setBackgroundColor(Color.TRANSPARENT);
            productListDeleteBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            productListLayout.addView(productListDeleteBtn);
            productListDeleteBtn.setGravity(Gravity.END);
            productListDeleteBtn.setTypeface(fontRSU);
            productListDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<RemoveProductInCartItemDao> call = HttpManager.getInstance().getRemoveProductInCartApiService().removeData(memberID,
                                                                                                                                deleteProductID);
                    call.enqueue(new Callback<RemoveProductInCartItemDao>() {
                        @Override
                        public void onResponse(Call<RemoveProductInCartItemDao> call,
                                               Response<RemoveProductInCartItemDao> response) {
                            if (response.isSuccessful()){
                                Intent intent = new Intent(getActivity(), CartActivity.class);
                                startActivity(intent);
                            }else{
                                // Handle
                                try {
                                    showToast(response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }  //-----  if (response.isSuccessful())
                        }

                        @Override
                        public void onFailure(Call<RemoveProductInCartItemDao> call,
                                              Throwable t) {
                            showToast(t.toString());
                        }
                    });
                }
            });


            row.addView(productListLayout);
            cartShowProductTableLayout.addView(row,j);

            j++;



            TableRow row2 = new TableRow(getContext());
            TableRow.LayoutParams tp2 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            row2.setLayoutParams(tp2);
            row2.setPadding(0, convertPixelToDP(10), 0, convertPixelToDP(10));


            View line = new View(getContext());
            TableRow.LayoutParams lp2 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, convertPixelToDP(2));
            lp2.span =2;
            line.setLayoutParams(lp2);
            line.setBackgroundResource(R.color.overlay_color);
            row2.addView(line);
            cartShowProductTableLayout.addView(row2,j);

            j++;
        }

        cartSummaryPointTV.setText(String.valueOf(summaryPoint) + " Points");
        //cartMemberRemainPointTV.setText(String.valueOf(formatter.format(memberPoint)) + " Points");
        cartMemberRemainPointTV.setText(String.valueOf((memberPoint)) + " Points");

        SharedPreferences sp = getActivity().getSharedPreferences("ProductInCart", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("SummaryPrice", String.valueOf(summaryPoint));
        editor.apply();
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    private int convertPixelToDP(int i) {
        float scale = getResources().getDisplayMetrics().density;
        int dpInPixel = (int) (i * scale + 0.5f);

        return dpInPixel;
    }



    final View.OnClickListener cashOutListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (memberPoint < summaryPoint){
                showToast(getContext().getString(R.string.cart_not_enought_point_show));
            }else{
                Intent intent = new Intent(getActivity(), CheckOutActivity.class);
                startActivity(intent);
            }  //------  if (memberPoint < summaryPoint)
        }
    };


    final View.OnClickListener continueShoppingListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    };

}
