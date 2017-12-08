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
                    case 2 :
                        return ProductNearGoneFragment.newInstance();
                    case 3 :
                        return ProductComingSoonFragment.newInstance();
                    default :
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0 :
                        return "All";
                    case 1 :
                        return "New";
                    case 2 :
                        return "Nearly Gone";
                    case 3 :
                        return "Coming Soon";
                    default :
                        return "";
                }
            }
        });

        slidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.slidingTabLayout);
        //slidingTabLayout.setDistributeEvenly(true);  // Make It Fit With Screen
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

        MenuItem categoryIconItem = (MenuItem) menu.findItem(R.id.category_icon);
        final MenuItem basketIconItem = (MenuItem) menu.findItem(R.id.basket_icon);
        MenuItem searchIconItem = (MenuItem) menu.findItem(R.id.search_icon);

        searchIconItem.setVisible(isHidden());

        View actionView = MenuItemCompat.getActionView(basketIconItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();


        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(basketIconItem);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.basket_icon:
                showMemberCart();
                return true;
            case R.id.category_icon:
                showCategory();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void showMemberCart() {
        if (Integer.parseInt(memberID) > 0){
            Intent intent = new Intent(getActivity(), CartActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }


    private void showCategory() {

    }


    private void setupBadge() {
        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }


}
