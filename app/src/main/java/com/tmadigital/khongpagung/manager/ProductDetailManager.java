package com.tmadigital.khongpagung.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tmadigital.khongpagung.dao.ProductDetailItemCollectionDao;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ProductDetailManager {

    private static ProductDetailManager instance;

    public static ProductDetailManager getInstance() {
        if (instance == null)
            instance = new ProductDetailManager();
        return instance;
    }

    private Context mContext;
    private ProductDetailItemCollectionDao dao;

    private ProductDetailManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public ProductDetailItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(ProductDetailItemCollectionDao dao) {
        this.dao = dao;
    }
}
