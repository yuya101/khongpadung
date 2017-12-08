package com.tmadigital.khongpagung.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.tmadigital.khongpagung.R;
import com.tmadigital.khongpagung.fragment.CartFragment;
import com.tmadigital.khongpagung.method.SamplinkMethod;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CartActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private Button menuHomeButton;
    private Button menuRegisterButton;
    private Button menuLogInButton;
    private Button menuRedeemHistoryButton;
    private Button menuPointHistoryButton;
    private Button menuCartButton;
    private Button menuLogOutButton;
    private SamplinkMethod samplinkMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.cartContainer, CartFragment.newInstance())
                    .commit();
        }
    }

    @Override  //------ Use In Activity For Change Default Font
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initInstances() {

        samplinkMethod = new SamplinkMethod();

        //---- Create Toolbar In First Step
        toolbar = (Toolbar) findViewById(R.id.cartToolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.cartDrawerLayout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                CartActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setLogo(R.drawable.samplink_title_logo);

        initMenuClickable();
    }


    private void initMenuClickable() {
//        menuHomeButton = (Button) findViewById(R.id.menuHomeBtn);
//        menuHomeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        menuRegisterButton = (Button) findViewById(R.id.menuRegisterBtn);
//        menuRegisterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        menuLogInButton = (Button) findViewById(R.id.menuLoginBtn);
//        menuLogInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        menuRedeemHistoryButton = (Button) findViewById(R.id.menuHistoryBtn);
//        menuRedeemHistoryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), CheckRedeemProductHistoryActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        menuPointHistoryButton = (Button) findViewById(R.id.menuPointHistoryBtn);
//        menuPointHistoryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), CheckPointHistoryActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        menuCartButton = (Button) findViewById(R.id.menuCartBtn);
//        menuCartButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), CartActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        menuLogOutButton = (Button) findViewById(R.id.menuLogOutBtn);
//        menuLogOutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences settings = getApplication().getSharedPreferences("Login", Context.MODE_PRIVATE);
//                settings.edit().clear().commit();
//
//                SharedPreferences productInCart = getApplication().getSharedPreferences("ProductInCart", Context.MODE_PRIVATE);
//                productInCart.edit().clear().commit();
//
//                samplinkMethod.disconnectFromFacebook();
//
//                Intent intent = new Intent(getApplication(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}
