package com.tmadigital.khongpagung.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.inthecheesefactory.thecheeselibrary.view.SlidingTabLayout;
import com.tmadigital.khongpagung.R;
import com.tmadigital.khongpagung.activity.CartActivity;
import com.tmadigital.khongpagung.activity.LoginActivity;
import com.tmadigital.khongpagung.dao.ProductAllItemDao;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ProductDetailMainFragment extends Fragment {

    private static String prodID;
    ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;
    private String memberID;


    public ProductDetailMainFragment() {
        super();
    }

    public static ProductDetailMainFragment newInstance(String dao) {
        ProductDetailMainFragment fragment = new ProductDetailMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        prodID = dao;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Fragment level's variables
        setHasOptionsMenu(true);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_detail_main, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0 :
                        return ProductDetailOverviewFragment.newInstance(prodID);
                    case 1 :
                        return ProductDetailDescriptionFragment.newInstance(prodID);
                    default :
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0 :
                        return "Overview";
                    case 1 :
                        return "Description";
                    default :
                        return "";
                }
            }
        });

        slidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.slidingTabLayout);
        slidingTabLayout.setDistributeEvenly(true);  // Make It Fit With Screen
        //slidingTabLayout.bringToFront();
        slidingTabLayout.setTabsBackgroundColor(getResources().getColor(R.color.slidingtab_bgcolor));
        slidingTabLayout.setViewPager(viewPager);

        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.white);
            }
        });

        SharedPreferences sp = getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
        memberID = String.valueOf(sp.getInt("memberID", 0));
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

    private void onRestoreInstanceState(Bundle savedInstanceState){
        // Restore Instance State Here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
