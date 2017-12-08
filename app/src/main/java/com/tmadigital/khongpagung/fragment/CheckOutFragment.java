package com.tmadigital.khongpagung.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tmadigital.khongpagung.R;
import com.tmadigital.khongpagung.activity.CartActivity;
import com.tmadigital.khongpagung.activity.ThankYouActivity;
import com.tmadigital.khongpagung.dao.CheckOutItemDao;
import com.tmadigital.khongpagung.dao.GeoAmphorItemCollectionDao;
import com.tmadigital.khongpagung.dao.GeoDistrictItemCollectionDao;
import com.tmadigital.khongpagung.dao.GeoProvinceItemCollectionDao;
import com.tmadigital.khongpagung.manager.HttpManager;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class CheckOutFragment extends Fragment {

    private Button checkOutBtn;
    private Button cartCancleBtn;
    private EditText checkoutFirstNameET;
    private EditText checkoutLastNameET;
    private EditText checkoutAddressET;
    private EditText checkoutSoiET;
    private EditText checkoutStreetET;
    private Spinner checkoutDistrictSN;
    private Spinner checkoutAmphorSN;
    private Spinner checkoutProvinceSN;
    private EditText checkoutPostCodeET;
    private EditText checkoutOrderNoteET;
    private TextView checkoutSummaryPriceET;

    private ArrayList<String> districtList = new ArrayList<String>();
    private ArrayList<String> amphorList = new ArrayList<String>();
    private ArrayList<String> provinceList = new ArrayList<String>();

    private String[] districtArray;
    private String[] amphorArray;
    private String[] provinceArray;

    private String districtSelectID = "0";
    private String amphorSelectID = "0";
    private String provinceSelectID = "0";


    public CheckOutFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static CheckOutFragment newInstance() {
        CheckOutFragment fragment = new CheckOutFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_check_out, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        checkOutBtn = (Button) rootView.findViewById(R.id.checkOutBtn);
        checkOutBtn.setOnClickListener(checkOutListener);

        cartCancleBtn = (Button) rootView.findViewById(R.id.cartCancleBtn);
        checkoutFirstNameET = (EditText) rootView.findViewById(R.id.checkoutFirstNameET);
        checkoutLastNameET = (EditText) rootView.findViewById(R.id.checkoutLastNameET);
        checkoutAddressET = (EditText) rootView.findViewById(R.id.checkoutAddressET);
        checkoutSoiET = (EditText) rootView.findViewById(R.id.checkoutSoiET);
        checkoutStreetET = (EditText) rootView.findViewById(R.id.checkoutStreetET);
        checkoutDistrictSN = (Spinner) rootView.findViewById(R.id.checkoutDistrictSN);
        checkoutAmphorSN = (Spinner) rootView.findViewById(R.id.checkoutAmphorSN);
        checkoutProvinceSN = (Spinner) rootView.findViewById(R.id.checkoutProvinceSN);
        checkoutPostCodeET = (EditText) rootView.findViewById(R.id.checkoutPostCodeET);
        checkoutOrderNoteET = (EditText) rootView.findViewById(R.id.checkoutOrderNoteET);
        checkoutSummaryPriceET = (TextView) rootView.findViewById(R.id.checkoutSummaryPriceET);

        startInsertData();

        checkoutDistrictSN.setOnItemSelectedListener(districtListener);
        checkoutAmphorSN.setOnItemSelectedListener(amphorListener);
        checkoutProvinceSN.setOnItemSelectedListener(provinceListener);
        cartCancleBtn.setOnClickListener(cartCancleListener);

        loadAddressData();
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

    private void showToast(String text){
        Toast.makeText(Contextor.getInstance().getContext(),
                text,
                Toast.LENGTH_SHORT)
                .show();
    }


    private void startInsertData() {
        SharedPreferences sp = getActivity().getSharedPreferences("ProductInCart", Context.MODE_PRIVATE);
        checkoutSummaryPriceET.setText(sp.getString("SummaryPrice", "5000") + " Points");

        Call<GeoProvinceItemCollectionDao> call = HttpManager.getInstance().getGeoProvinceApiService().queryData();
        call.enqueue(new Callback<GeoProvinceItemCollectionDao>() {
            @Override
            public void onResponse(Call<GeoProvinceItemCollectionDao> call,
                                   Response<GeoProvinceItemCollectionDao> response) {
                if (response.isSuccessful()){
                    GeoProvinceItemCollectionDao geoDao = response.body();

                    if (geoDao.getStatus().toString().equals("success")){
                        createGeoProvinceArrayList(geoDao);

                        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_dropdown_item_1line, districtList);
                        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        checkoutDistrictSN.setAdapter(districtAdapter);

                        ArrayAdapter<String> amphorAdapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_dropdown_item_1line, amphorList);
                        checkoutAmphorSN.setAdapter(amphorAdapter);

                        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_dropdown_item_1line, provinceList);
                        checkoutProvinceSN.setAdapter(provinceAdapter);
                    }else{
                        showToast("ไม่สามารถใช้ข้อมูลผ่านระบบได้ค่ะ\nกรุณาลองใหม่อีกครั้ง !");
                    }
                }else{
                    // Handle
                    try {
                        showToast(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }  //-----  if (response.isSuccessful())
            }

            @Override
            public void onFailure(Call<GeoProvinceItemCollectionDao> call,
                                  Throwable t) {
                showToast(t.toString());
            }
        });
    }


    private void createGeoProvinceArrayList(GeoProvinceItemCollectionDao geoDao) {
        provinceArray = new String[geoDao.getProvincelist().size()];

        for (int i=0; i<(geoDao.getProvincelist().size()); i++){
            provinceArray[i] = geoDao.getProvincelist().get(i).getProvinceid().toString();
            provinceList.add(geoDao.getProvincelist().get(i).getProvincename().toString());
        } //------ for (int i=0; i<(geoDao.getDistrict().size()); i++)
    }


    /*private void createGeoArrayList(GeoItemCollectionDao geoDao) {
        districtArray = new String[geoDao.getDistrict().size()];
        amphorArray = new String[geoDao.getAmphor().size()];
        provinceArray = new String[geoDao.getProvince().size()];

        for (int i=0; i<(geoDao.getDistrict().size()); i++){
            districtArray[i] = geoDao.getDistrict().get(i).getDistrictid().toString();
            districtList.add(geoDao.getDistrict().get(i).getDistrictname().toString());
        } //------ for (int i=0; i<(geoDao.getDistrict().size()); i++)

        for (int i=0; i<(geoDao.getAmphor().size()); i++){
            amphorArray[i] = geoDao.getAmphor().get(i).getAmphorid().toString();
            amphorList.add(geoDao.getAmphor().get(i).getAmphorname().toString());
        } //------ for (int i=0; i<(geoDao.getDistrict().size()); i++)

        for (int i=0; i<(geoDao.getProvince().size()); i++){
            provinceArray[i] = geoDao.getProvince().get(i).getProvinceid().toString();
            provinceList.add(geoDao.getProvince().get(i).getProvincename().toString());
        } //------ for (int i=0; i<(geoDao.getDistrict().size()); i++)
    }*/



    /*
     * Event Listener
     */
    final AdapterView.OnItemSelectedListener districtListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            districtSelectID = districtArray[position];
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            districtSelectID = "0";
        }
    };

    final AdapterView.OnItemSelectedListener amphorListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            amphorSelectID = amphorArray[position];
            checkoutDistrictSN.setAdapter(null);
            districtSelectID = "0";

            Call<GeoDistrictItemCollectionDao> call = HttpManager.getInstance().getGeoDistrictApiService().queryData(amphorSelectID);
            call.enqueue(new Callback<GeoDistrictItemCollectionDao>() {
                @Override
                public void onResponse(Call<GeoDistrictItemCollectionDao> call,
                                       Response<GeoDistrictItemCollectionDao> response) {
                    if (response.isSuccessful()){
                        GeoDistrictItemCollectionDao districtDao = response.body();

                        districtList.clear();
                        districtArray = new String[districtDao.getTumbonlist().size()];

                        for (int i=0; i<(districtDao.getTumbonlist().size()); i++){
                            districtArray[i] = districtDao.getTumbonlist().get(i).getDistrictid().toString();
                            districtList.add(districtDao.getTumbonlist().get(i).getDistrictname().toString());
                        } //------ for (int i=0; i<(geoDao.getDistrict().size()); i++)

                        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_dropdown_item_1line, districtList);
                        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        checkoutDistrictSN.setAdapter(districtAdapter);
                    }else{
                        // Handle
                        try {
                            showToast(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }  //-----  if (response.isSuccessful())
                }

                @Override
                public void onFailure(Call<GeoDistrictItemCollectionDao> call,
                                      Throwable t) {
                    showToast(t.toString());
                }
            });
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            amphorSelectID = "0";
        }
    };

    final AdapterView.OnItemSelectedListener provinceListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            provinceSelectID = provinceArray[position];
            checkoutAmphorSN.setAdapter(null);
            checkoutDistrictSN.setAdapter(null);
            districtSelectID = "0";
            amphorSelectID = "0";


            Call<GeoAmphorItemCollectionDao> call = HttpManager.getInstance().getGeoAmphorApiService().queryData(provinceSelectID);
            call.enqueue(new Callback<GeoAmphorItemCollectionDao>() {
                @Override
                public void onResponse(Call<GeoAmphorItemCollectionDao> call,
                                       Response<GeoAmphorItemCollectionDao> response) {
                    if (response.isSuccessful()){
                        GeoAmphorItemCollectionDao amphorDao = response.body();

                        amphorList.clear();
                        amphorArray = new String[amphorDao.getAmphorlist().size()];

                        for (int i=0; i<(amphorDao.getAmphorlist().size()); i++){
                            amphorArray[i] = amphorDao.getAmphorlist().get(i).getAmphorid().toString();
                            amphorList.add(amphorDao.getAmphorlist().get(i).getAmphorname().toString());
                        } //------ for (int i=0; i<(amphorDao.getAmphorlist().size()); i++)

                        ArrayAdapter<String> amphorAdapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_dropdown_item_1line, amphorList);
                        checkoutAmphorSN.setAdapter(amphorAdapter);

                    }else{
                        // Handle
                        try {
                            showToast(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }  //----- if (response.isSuccessful())
                }

                @Override
                public void onFailure(Call<GeoAmphorItemCollectionDao> call,
                                      Throwable t) {
                    showToast(t.toString());
                }
            });
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            provinceSelectID = "0";
        }
    };



    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }


    private void loadAddressData() {
        
    }


    final View.OnClickListener checkOutListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!(checkoutFirstNameET.getText().toString().equals(""))){
                if(!(checkoutLastNameET.getText().toString().equals(""))){
                    if(!(checkoutAddressET.getText().toString().equals(""))){
                        if(!(checkoutSoiET.getText().toString().equals(""))){
                            if(!(checkoutStreetET.getText().toString().equals(""))){
                                if(districtSelectID != "0"){
                                    if(amphorSelectID != "0"){
                                        if(provinceSelectID != "0"){
                                            if(!(checkoutPostCodeET.getText().toString().equals(""))){
                                                String orderNote = "-";

                                                if(!(checkoutOrderNoteET.getText().toString().equals(""))){
                                                    orderNote = checkoutOrderNoteET.getText().toString();
                                                }  //-----  if(!(checkoutOrderNoteET.getText().toString().equals("")))

                                                final SharedPreferences sp = getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sp.edit();

                                                String memberID = String.valueOf(sp.getInt("memberID", 0));

                                                Call<CheckOutItemDao> call = HttpManager.getInstance().getCheckOutApiService().checkOutAddData(memberID,
                                                        checkoutFirstNameET.getText().toString(), checkoutLastNameET.getText().toString(),
                                                        checkoutAddressET.getText().toString(), checkoutSoiET.getText().toString(),
                                                        checkoutStreetET.getText().toString(), districtSelectID, amphorSelectID,
                                                        provinceSelectID, checkoutPostCodeET.getText().toString(), orderNote);

                                                call.enqueue(new Callback<CheckOutItemDao>() {
                                                    @Override
                                                    public void onResponse(Call<CheckOutItemDao> call,
                                                                           Response<CheckOutItemDao> response) {
                                                        if (response.isSuccessful()){
                                                            CheckOutItemDao checkOutDao = response.body();

                                                            if (checkOutDao.getStatus().toString().equals("success")){
                                                                SharedPreferences.Editor editor = sp.edit();
                                                                editor.putString("memberPoint", checkOutDao.getPoint());
                                                                editor.putString("memberPointShow", checkOutDao.getPointShow());
                                                                editor.apply();

                                                                final SharedPreferences spic = getContext().getSharedPreferences("ProductInCart", Context.MODE_PRIVATE);
                                                                SharedPreferences.Editor editoric = spic.edit();
                                                                editoric.putString("SummaryPrice", "0");
                                                                editoric.apply();

                                                                Intent intent = new Intent(getActivity(), ThankYouActivity.class);
                                                                startActivity(intent);
                                                            }else{
                                                                showToast("ไม่สามารถเชื่อมต่อ Server ได้\nกรุณาลองใหม่อีกครั้งค่ะ!");
                                                            }  //-----  if (checkOutDao.getStatus().toString().equals("success")
                                                        }else{
                                                            // Handle
                                                            try {
                                                                showToast(response.errorBody().string());
                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }  //----  if (response.isSuccessful())
                                                    }

                                                    @Override
                                                    public void onFailure(Call<CheckOutItemDao> call,
                                                                          Throwable t) {
                                                        showToast(t.toString());
                                                    }
                                                });
                                            }else{
                                                showToast("กรุณากรอกหมายเลขไปรษณีย์ของท่านด้วยค่ะ");
                                            }  //-----  if(!(checkoutPostCodeET.getText().toString().equals("")))
                                        }else{
                                            showToast("กรุณาเลือกจังหวัดของท่านด้วยค่ะ");
                                        }  //----- if(provinceSelectID != "0")
                                    }else{
                                        showToast("กรุณาเลือกอำเภอของท่านด้วยค่ะ");
                                    }  //-----  if(amphorSelectID != "0")
                                }else{
                                    showToast("กรุณาเลือกตำบลของท่านด้วยค่ะ");
                                }  //-----  if(districtSelectID != "0")
                            }else{
                                showToast("กรุณากรอกถนนของท่านด้วยค่ะ");
                            }  //------  if(!(checkoutStreetET.getText().toString().equals("")))
                        }else{
                            showToast("กรุณากรอกซอยของท่านด้วยค่ะ");
                        }  //-----  if(!(checkoutSoiET.getText().toString().equals("")))
                    }else{
                        showToast("กรุณากรอกที่อยู่ของท่านด้วยค่ะ");
                    }  //------  if(!(checkoutAddressET.getText().toString().equals("")))
                }else{
                    showToast("กรุณากรอกนามสกุลของท่านด้วยค่ะ");
                }  //--------  if(!(checkoutLastNameET.getText().toString().equals("")))
            }else{
                showToast("กรุณากรอกชื่อของท่านด้วยค่ะ");
            }  //-----  if(!(checkoutFirstNameET.getText().toString().equals("")))
        }
    };


    final View.OnClickListener cartCancleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), CartActivity.class);
            startActivity(intent);
        }
    };

}
