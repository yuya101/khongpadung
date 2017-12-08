package com.tmadigital.khongpagung.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
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
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tmadigital.khongpagung.R;
import com.tmadigital.khongpagung.dao.CheckRedeemProductHistoryItemCollectionDao;
import com.tmadigital.khongpagung.dao.CheckRedeemProductHistoryItemDao;
import com.tmadigital.khongpagung.manager.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class CheckRedeemProductHistoryFragment extends Fragment {

    private TableLayout checkRedeemShowProductTableLayout;
    private String memberID;

    public CheckRedeemProductHistoryFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static CheckRedeemProductHistoryFragment newInstance() {
        CheckRedeemProductHistoryFragment fragment = new CheckRedeemProductHistoryFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_check_redeem_product_history, container, false);
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

        checkRedeemShowProductTableLayout = (TableLayout) rootView.findViewById(R.id.checkRedeemShowProductTableLayout);

        Call<CheckRedeemProductHistoryItemCollectionDao> call = HttpManager.getInstance()
                                                                .getCheckRedeemProductHistoryApiService()
                                                                .loadHistoryData(memberID);
        call.enqueue(new Callback<CheckRedeemProductHistoryItemCollectionDao>() {
            @Override
            public void onResponse(Call<CheckRedeemProductHistoryItemCollectionDao> call,
                                   Response<CheckRedeemProductHistoryItemCollectionDao> response) {
                if (response.isSuccessful()){
                    CheckRedeemProductHistoryItemCollectionDao itemDao = response.body();

                    if (itemDao.getStatus().toString().equals("success")){
                        createShowCheckRedeemProductLayout(itemDao);
                    }else{
                        showToast(getContext().getString(R.string.check_point_no_order_history_text));
                    }  //-----  if (itemDao.getStatus().toString().equals("success"))
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
            public void onFailure(Call<CheckRedeemProductHistoryItemCollectionDao> call,
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


    private void createShowCheckRedeemProductLayout(CheckRedeemProductHistoryItemCollectionDao itemDao) {
        int productHistoryCount = itemDao.getProductsList().size();
        CheckRedeemProductHistoryItemDao inCart;

        int j = 0;

        for (int i = 0; i < (productHistoryCount); i++) {
            inCart = itemDao.getProductsList().get(i);

            TableRow row = new TableRow(getContext());
            TableRow.LayoutParams tp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(tp);
            row.setPadding(0, convertPixelToDP(20), 0, 0);

            // Start Create Product Detail
            ImageView productImage = new ImageView(getContext());
            productImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(CheckRedeemProductHistoryFragment.this)
                    .load(inCart.getPicture())
                    //.placeholder(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(400,400)
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

            TextView productListDate = new TextView(getContext());
            productListDate.setLayoutParams(new LinearLayout.LayoutParams(convertPixelToDP(210), LinearLayout.LayoutParams.WRAP_CONTENT));
            productListDate.setText("วันที่ " + inCart.getOrderdate());
            productListDate.setMaxLines(1);
            productListDate.setEllipsize(TextUtils.TruncateAt.END);
            productListDate.setTextColor(getResources().getColor(R.color.gray));
            productListDate.setTextSize(convertPixelToDP(4));
            productListDate.setTypeface(null, Typeface.BOLD);
            productListDate.setBackgroundColor(Color.TRANSPARENT);
            productListLayout.addView(productListDate);


            TextView productListName = new TextView(getContext());
            productListName.setLayoutParams(new LinearLayout.LayoutParams(convertPixelToDP(210), LinearLayout.LayoutParams.WRAP_CONTENT));
            productListName.setText(inCart.getName());
            productListName.setMaxLines(3);
            productListName.setEllipsize(TextUtils.TruncateAt.END);
            productListName.setTextColor(getResources().getColor(R.color.blackGray));
            productListName.setTextSize(convertPixelToDP(4));
            productListName.setTypeface(null, Typeface.BOLD);
            productListName.setBackgroundColor(Color.TRANSPARENT);
            productListLayout.addView(productListName);


            TextView productListPoint = new TextView(getContext());
            productListPoint.setLayoutParams(new LinearLayout.LayoutParams(convertPixelToDP(210), LinearLayout.LayoutParams.WRAP_CONTENT));
            productListPoint.setText(inCart.getPrice() + " Points");
            productListPoint.setMaxLines(1);
            productListPoint.setEllipsize(TextUtils.TruncateAt.END);
            productListPoint.setTextColor(getResources().getColor(R.color.blackGray));
            productListPoint.setTextSize(convertPixelToDP(4));
            productListPoint.setTypeface(null, Typeface.BOLD);
            productListPoint.setPadding(0, convertPixelToDP(10), 0, convertPixelToDP(10));
            productListPoint.setBackgroundColor(Color.TRANSPARENT);
            productListLayout.addView(productListPoint);


            ImageView showTransferStatusImage = new ImageView(getContext());
            showTransferStatusImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

            if(Integer.parseInt(inCart.getSendproductflag()) == 0){
                showTransferStatusImage.setImageResource(R.drawable.alerttransferproduct);
            }else{
                showTransferStatusImage.setImageResource(R.drawable.alertreceiveproduct);
            }  //-----  if(Integer.parseInt(inCart.getSendproductflag()) == 0)

            productListLayout.addView(showTransferStatusImage);
            showTransferStatusImage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));


            row.addView(productListLayout);
            checkRedeemShowProductTableLayout.addView(row,j);

            j++;


            TableRow row3 = new TableRow(getContext());
            TableRow.LayoutParams tp3 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            row3.setLayoutParams(tp3);
            row3.setPadding(0, convertPixelToDP(15), 0, convertPixelToDP(10));


            if(inCart.getReviewstatus().toString().equals("yes")){
                ImageButton reviewButton = new ImageButton(getContext());
                TableRow.LayoutParams rvBtn = new TableRow.LayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                rvBtn.span = 2;
                reviewButton.setLayoutParams(rvBtn);
                reviewButton.setBackgroundResource(R.drawable.reviewbutton);
                reviewButton.setId(i);
                reviewButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                row3.addView(reviewButton);
                checkRedeemShowProductTableLayout.addView(row3,j);

                j++;
            }else if(inCart.getReviewstatus().toString().equals("complete")){
                ImageButton reviewButton = new ImageButton(getContext());
                TableRow.LayoutParams rvBtn = new TableRow.LayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                rvBtn.span = 2;
                reviewButton.setLayoutParams(rvBtn);
                reviewButton.setBackgroundResource(R.drawable.editreviewbutton);
                reviewButton.setId(i);
                reviewButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast(getResources().getString(R.string.check_redeem_product_history_complete_review_text));
                    }
                });
                row3.addView(reviewButton);
                checkRedeemShowProductTableLayout.addView(row3,j);

                j++;
            } //-----  if(inCart.getReviewstatus().toString().equals("yes"))




            TableRow row2 = new TableRow(getContext());
            TableRow.LayoutParams tp2 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            row2.setLayoutParams(tp2);
            row2.setPadding(0, convertPixelToDP(5), 0, convertPixelToDP(10));


            View line = new View(getContext());
            TableRow.LayoutParams lp2 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, convertPixelToDP(2));
            lp2.span =2;
            line.setLayoutParams(lp2);
            line.setBackgroundResource(R.color.overlay_color);
            row2.addView(line);
            checkRedeemShowProductTableLayout.addView(row2,j);

            j++;

        }
    }


    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

}
