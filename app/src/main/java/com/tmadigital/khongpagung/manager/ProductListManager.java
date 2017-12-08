package com.tmadigital.khongpagung.manager;

import android.content.Context;
import android.os.Bundle;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tmadigital.khongpagung.dao.ProductItemDao;

import java.util.List;

/**
 * Created by nuuneoi on 11/16/2014.
 * Create For Collect List Of JSON For Use in All Application
 */
public class ProductListManager {

    private static ProductListManager instance;

    public static ProductListManager getInstance() {
        if (instance == null)
            instance = new ProductListManager();
        return instance;
    }

    private Context mContext;
    private List<ProductItemDao> dao;

    private ProductListManager() {
        mContext = Contextor.getInstance().getContext();
    }


    public List<ProductItemDao> getDao() {
        return dao;
    }

    public void setDao(List<ProductItemDao> dao) {
        this.dao = dao;
    }


    public Bundle onSaveInstanceState(){
        return null;
    }

    public void onRestoreInatanceState(Bundle saveInstanceState){

    }
}
