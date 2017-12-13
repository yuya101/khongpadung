package com.tmadigital.khongpagung.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.tmadigital.khongpagung.R;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MapRootDetailFragment extends Fragment {



    public MapRootDetailFragment() {
        super();
    }

    public static MapRootDetailFragment newInstance() {
        MapRootDetailFragment fragment = new MapRootDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Fragment level's variables
        //setHasOptionsMenu(true);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map_root, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        
        SubsamplingScaleImageView imageView = (SubsamplingScaleImageView) rootView.findViewById(R.id.map);
        imageView.setImage(ImageSource.resource(R.drawable.map));
        // ... or ...
        //imageView.setImage(ImageSource.asset("map.png"))
        // ... or ...
        //imageView.setImage(ImageSource.uri("/sdcard/DCIM/DSCM00123.JPG"));
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


}
