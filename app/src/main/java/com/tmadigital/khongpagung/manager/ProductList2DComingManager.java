package com.tmadigital.khongpagung.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tmadigital.khongpagung.dao.ProductAllItemCollectionDao;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ProductList2DComingManager {

    private static ProductList2DComingManager instance;

    public static ProductList2DComingManager getInstance() {
        if (instance == null)
            instance = new ProductList2DComingManager();
        return instance;
    }

    private Context mContext;
    private ProductAllItemCollectionDao dao;

    private ProductList2DComingManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public ProductAllItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(ProductAllItemCollectionDao dao) {
        this.dao = dao;
    }
}
