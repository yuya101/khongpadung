package com.tmadigital.khongpagung.method;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tmadigital.khongpagung.R;
import com.tmadigital.khongpagung.activity.EventActivity;
import com.tmadigital.khongpagung.activity.MainActivity;
import com.tmadigital.khongpagung.activity.MapActivity;
import com.tmadigital.khongpagung.activity.VideoActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Maximus on 8/27/2017 AD.
 */

public class SamplinkMethod {

    public void disconnectFromFacebook() {
        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }


    public void showToast(String text){
        Toast toast = Toast.makeText(Contextor.getInstance().getContext(),
                text,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public boolean chkNotNullObject(String text) {
        boolean flag = false;

        if (text != null && (!TextUtils.equals(text ,"null")) && (!TextUtils.isEmpty(text))) {
            flag = true;
        }

        return flag;
    }

    public void showLoading(FragmentActivity activity, String title, String message) {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle(title);
        progress.setMessage(message);
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progress.dismiss();
            }
        }, Long.parseLong("3000")); // 3000 milliseconds delay
    }


    public void initMenuClickable(final Activity activity) {
        Button menuHomeBtn = (Button) activity.findViewById(R.id.home_text_btn);
        menuHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, EventActivity.class);
                activity.startActivity(intent);
            }
        });

        Button menuProductBtn = (Button) activity.findViewById(R.id.product_text_btn);
        menuProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }
        });

        Button menuAboutBtn = (Button) activity.findViewById(R.id.about_text_btn);
        menuAboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button menuActivityBtn = (Button) activity.findViewById(R.id.activity_text_btn);
        menuActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, VideoActivity.class);
                activity.startActivity(intent);
            }
        });

        Button menuMapBtn = (Button) activity.findViewById(R.id.map_text_btn);
        menuMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, MapActivity.class);
                activity.startActivity(intent);
            }
        });
    }


}
