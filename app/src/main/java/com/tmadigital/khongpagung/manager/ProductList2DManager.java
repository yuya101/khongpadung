package com.tmadigital.khongpagung.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tmadigital.khongpagung.dao.ProductAllItemCollectionDao;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ProductList2DManager {

    private static ProductList2DManager instance;

    public static ProductList2DManager getInstance() {
        if (instance == null)
            instance = new ProductList2DManager();
        return instance;
    }

    private Context mContext;
    private ProductAllItemCollectionDao dao;

    private ProductList2DManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public ProductAllItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(ProductAllItemCollectionDao dao) {
        this.dao = dao;
    }
}
