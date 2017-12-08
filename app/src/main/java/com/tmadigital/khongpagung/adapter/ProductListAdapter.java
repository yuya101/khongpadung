package com.tmadigital.khongpagung.adapter;

import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;

import com.tmadigital.khongpagung.R;
import com.tmadigital.khongpagung.dao.ProductAllItemDao;
import com.tmadigital.khongpagung.manager.ProductList2DManager;
import com.tmadigital.khongpagung.view.ProductListItem;

/**
 * Created by Maximus101 on 2/24/2017 AD.
 */

public class ProductListAdapter extends BaseAdapter {

    int lastPosition = -1;
    private String productNamehtml;


    @Override
    public int getCount() {
        if (ProductList2DManager.getInstance().getDao() == null)
            return 0;
        if (ProductList2DManager.getInstance().getDao().getProducts() == null)
            return 0;
        return ProductList2DManager.getInstance().getDao().getProducts().size();
    }

    @Override
    public Object getItem(int position) {
        return ProductList2DManager.getInstance().getDao().getProducts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductListItem item;

        if (convertView != null)
        {
            item = (ProductListItem) convertView;
        }
        else
        {
            item = new ProductListItem(parent.getContext());
        }  //------  if (convertView != null)

        ProductAllItemDao dao = (ProductAllItemDao) getItem(position);

        if (Build.VERSION.SDK_INT >= 24)
        {
            productNamehtml = Html.fromHtml(dao.getName().toString(), Html.FROM_HTML_MODE_LEGACY).toString();
            productNamehtml = productNamehtml.replace("&AMP;", "&");
        }
        else
        {
            productNamehtml = Html.fromHtml(dao.getName().toString()).toString();
            productNamehtml = productNamehtml.replace("&AMP;", "&");
        }

        item.setTvProductName(productNamehtml);
        item.setTvProductPoint(dao.getPrice() + " P" );
        item.setImageUrl(dao.getPicture());
        item.setProductPosition(dao.getProid().toString());

        if (position > lastPosition) {
            Animation anim = AnimationUtils.loadAnimation(parent.getContext(),
                    R.anim.up_from_bottom);
            item.startAnimation(anim);
            lastPosition = position;
        }


        return item;
    }
}
