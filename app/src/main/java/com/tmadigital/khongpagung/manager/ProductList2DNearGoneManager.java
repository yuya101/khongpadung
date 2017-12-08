package com.tmadigital.khongpagung.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tmadigital.khongpagung.dao.ProductAllItemCollectionDao;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ProductList2DNearGoneManager {

    private static ProductList2DNearGoneManager instance;

    public static ProductList2DNearGoneManager getInstance() {
        if (instance == null)
            instance = new ProductList2DNearGoneManager();
        return instance;
    }

    private Context mContext;
    private ProductAllItemCollectionDao dao;

    private ProductList2DNearGoneManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public ProductAllItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(ProductAllItemCollectionDao dao) {
        this.dao = dao;
    }
}
