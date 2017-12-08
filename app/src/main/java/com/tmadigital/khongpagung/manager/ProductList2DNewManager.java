package com.tmadigital.khongpagung.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tmadigital.khongpagung.dao.ProductAllItemCollectionDao;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ProductList2DNewManager {

    private static ProductList2DNewManager instance;

    public static ProductList2DNewManager getInstance() {
        if (instance == null)
            instance = new ProductList2DNewManager();
        return instance;
    }

    private Context mContext;
    private ProductAllItemCollectionDao dao;

    private ProductList2DNewManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public ProductAllItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(ProductAllItemCollectionDao dao) {
        this.dao = dao;
    }
}
