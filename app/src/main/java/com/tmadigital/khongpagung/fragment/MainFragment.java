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
import com.tmadigital.khongpagung.activity.MainActivity;
import com.tmadigital.khongpagung.activity.ProductDetailActivity;
import com.tmadigital.khongpagung.dao.ProductAllItemDao;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainFragment extends Fragment {

    ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;
    private String memberID;
    private TextView textCartItemCount;
    int mCartItemCount = 0;


    public interface MainFragmentListener {
        void onProductItemClicked(ProductAllItemDao dao);
    }

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
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
                        return ProductAllFragment.newInstance();
                    case 1 :
                        return ProductNewFragment.newInstance();
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
                        return "SMEs สินค้าดี 4.0";
                    case 1 :
                        return "ของดี ของดัง ของขวัญปีใหม่";
                    default :
                        return "";
                }
            }
        });

        slidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.slidingTabLayout);
        //slidingTabLayout.setDistributeEvenly(true);  // Make It Fit With Screen
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

        if (Integer.parseInt(memberID) > 0) {
            SharedPreferences spSummary = getContext().getSharedPreferences("productSummary", Context.MODE_PRIVATE);
            mCartItemCount = spSummary.getInt("productInCartSummary", 0);
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


    private void showToast(String text){
        Toast.makeText(Contextor.getInstance().getContext(),
                text,
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem category_icon = (MenuItem) menu.findItem(R.id.category_icon);
        final MenuItem product_icon = (MenuItem) menu.findItem(R.id.product_icon);
        MenuItem service_icon = (MenuItem) menu.findItem(R.id.service_icon);

        category_icon.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            public Intent intent;

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                SharedPreferences sp = getContext().getSharedPreferences("product", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("typeID", "2");
                editor.putString("catID", "");
                editor.apply();

                intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

                return true;
            }
        });

        product_icon.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            public Intent intent;

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                SharedPreferences sp = getContext().getSharedPreferences("product", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("typeID", "");
                editor.putString("catID", "1");
                editor.apply();

                intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

                return true;
            }
        });

        service_icon.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            public Intent intent;

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
//                SharedPreferences sp = getContext().getSharedPreferences("product", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putString("typeID", "");
//                editor.putString("catID", "2");
//                editor.apply();

                intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

                return true;
            }
        });
    }
}
