package com.tmadigital.khongpagung.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tmadigital.khongpagung.R;
import com.tmadigital.khongpagung.adapter.ProductListAdapter;
import com.tmadigital.khongpagung.dao.ProductAllItemCollectionDao;
import com.tmadigital.khongpagung.dao.ProductAllItemDao;
import com.tmadigital.khongpagung.manager.HttpManager;
import com.tmadigital.khongpagung.manager.ProductList2DManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ProductAllFragment extends Fragment {

    GridView gridView;
    ProductListAdapter listAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    ProductList2DManager productList2DManager;


    public interface MainFragmentListener {
        void onProductItemClicked(ProductAllItemDao dao);
    }

    public ProductAllFragment() {
        super();
    }

    public static ProductAllFragment newInstance() {
        ProductAllFragment fragment = new ProductAllFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_product_all, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        gridView = (GridView) rootView.findViewById(R.id.gridView);
        listAdapter = new ProductListAdapter();
        gridView.setAdapter(listAdapter);

        gridView.setOnItemClickListener(gridViewItemClickListener);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
            }
        });

        AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view,
                                 int firstVisibleItem,
                                 int visibleItemCount,
                                 int totalItemCount) {
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0);
            }
        };

        gridView.setOnScrollListener(scrollListener);
        productList2DManager = ProductList2DManager.getInstance();

        reloadData();
    }

    private void reloadData() {
        //----- Start To Call ApiService
        Call<ProductAllItemCollectionDao> call = HttpManager.getInstance().getService().loadProductList("1", "");
        call.enqueue(new Callback<ProductAllItemCollectionDao>() {
            @Override
            public void onResponse(Call<ProductAllItemCollectionDao> call,
                                   Response<ProductAllItemCollectionDao> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()){
                    ProductAllItemCollectionDao dao = response.body();
                    ProductList2DManager.getInstance().setDao(dao);
                    listAdapter.notifyDataSetChanged();

                    SharedPreferences sp = getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                    String memberID = String.valueOf(sp.getInt("memberID", 0));
                } else {
                    // Handle
                    try {
                        showToast(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductAllItemCollectionDao> call,
                                  Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
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

    /**********
     * Listener Zone
     **********/
    final AdapterView.OnItemClickListener gridViewItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position < productList2DManager.getDao().getProducts().size()){
                ProductAllItemDao dao = productList2DManager.getDao().getProducts().get(position);
                MainFragment.MainFragmentListener listener = (MainFragment.MainFragmentListener) getActivity();
                listener.onProductItemClicked(dao);
            }
        }
    };
}
