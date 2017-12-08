package com.tmadigital.khongpagung.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tmadigital.khongpagung.R;
import com.tmadigital.khongpagung.activity.MainActivity;
import com.tmadigital.khongpagung.activity.RegisterActivity;
import com.tmadigital.khongpagung.dao.LoginItemCollectionDao;
import com.tmadigital.khongpagung.dao.LoginItemDao;
import com.tmadigital.khongpagung.dao.ProductInCartItemCollectionDao;
import com.tmadigital.khongpagung.manager.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class LoginFragment extends Fragment {

    private EditText signinEmailET;
    private EditText signinPasswordET;
    private Button signinBtn;
    private Button forgetPasswordBtn;
    private Button newAccountBtn;
    private LoginButton fbLogin;
    private CallbackManager callbackManager;

    public LoginFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        checkLoginStatus();
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void checkLoginStatus() {
        SharedPreferences sp = getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String memberID = String.valueOf(sp.getInt("memberID", 0));

        if (Integer.parseInt(memberID) > 0) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        signinEmailET = (EditText) rootView.findViewById(R.id.signinEmailET);
        signinPasswordET = (EditText) rootView.findViewById(R.id.signinPasswordET);
        signinBtn = (Button) rootView.findViewById(R.id.signinBtn);
        forgetPasswordBtn = (Button) rootView.findViewById(R.id.forgetPasswordBtn);
        newAccountBtn = (Button) rootView.findViewById(R.id.newAccountBtn);

        newAccountBtn.setOnClickListener(newAccountListener);


        fbLogin = (LoginButton) rootView.findViewById(R.id.fbLogin);

        fbLogin.setReadPermissions("user_friends", "email");
        fbLogin.setFragment(this);
        fbLogin.setBackgroundResource(R.drawable.signupfbbtn);
        fbLogin.setText("");
        fbLogin.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);



        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Profile profile = Profile.getCurrentProfile();

                String userId = loginResult.getAccessToken().getUserId();
                String accessToken = loginResult.getAccessToken().getToken();

                final String profileImgUrl = "https://graph.facebook.com/" + userId + "/picture?type=large";



                Call<LoginItemCollectionDao> call = HttpManager.getInstance().getLoginApiService().loginData(userId,
                        "-");
                call.enqueue(new Callback<LoginItemCollectionDao>() {
                    @Override
                    public void onResponse(Call<LoginItemCollectionDao> call,
                                           Response<LoginItemCollectionDao> response) {
                        if (response.isSuccessful()){
                            LoginItemCollectionDao logindao = response.body();

                            if (logindao.getStatus().toString().equals("success")){
                                LoginItemDao itemDao = logindao.getMemberinfo().get(0);

                                SharedPreferences sp = getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putInt("memberID", Integer.parseInt(itemDao.getMemberID()));
                                editor.putString("memberName", (itemDao.getBname()+" "+itemDao.getBlastname()));
                                editor.putString("memberMobile", itemDao.getBmobile());
                                editor.putString("memberPoint", itemDao.getPoint());
                                editor.putString("memberPointShow", itemDao.getPointShow());
                                editor.putString("memberGender", itemDao.getGender());
                                editor.putString("memberFBPicture", profileImgUrl);
                                editor.apply();

                                memberInCartSummary(itemDao.getMemberID().toString());

                            } else {
                                showToast("ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง\nกรุณาลองใหม่อีกครั้งค่ะ");
                            }  // -----  if (logindao.getStatus().toString().equals("success"))
                        } else {
                            // Handle
                            try {
                                showToast(response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }  //-------  if (response.isSuccessful())
                    }

                    @Override
                    public void onFailure(Call<LoginItemCollectionDao> call,
                                          Throwable t) {
                        showToast(t.toString());
                    }
                });
            }

            @Override
            public void onCancel() {
                // App code
                showToast("Log In Cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                exception.printStackTrace();
                showToast("Log In Error");
            }
        });


        signinBtn.setOnClickListener(signinClickListener);
    }


    private void memberInCartSummary(String memberID) {
        if (Integer.parseInt(memberID) > 0){
            Call<ProductInCartItemCollectionDao> callSummary = HttpManager.getInstance().getProductInCartApiService().loadProductInCartData(memberID);
            callSummary.enqueue(new Callback<ProductInCartItemCollectionDao>() {
                @Override
                public void onResponse(Call<ProductInCartItemCollectionDao> callSummary, Response<ProductInCartItemCollectionDao> response) {
                    if (response.isSuccessful()){
                        ProductInCartItemCollectionDao inCartDao = response.body();

                        if (inCartDao.getStatus().toString().equals("success")){
                            String productInCartSummary = inCartDao.getProductIncartSummarys().toString();

                            SharedPreferences spSummary = getContext().getSharedPreferences("productSummary", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editorSummary = spSummary.edit();
                            editorSummary.putInt("productInCartSummary", Integer.parseInt(productInCartSummary));
                            editorSummary.apply();


                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }  //----  if (inCartDao.getStatus().toString().equals("success"))
                    }else{
                        // Handle
                        try {
                            showToast(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }  //---  if (response.isSuccessful())
                }

                @Override
                public void onFailure(Call<ProductInCartItemCollectionDao> callSummary, Throwable t) {
                    showToast(t.toString());
                }
            });
        }
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
        Toast toast = Toast.makeText(Contextor.getInstance().getContext(),
                text,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    final View.OnClickListener signinClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!(signinEmailET.getText().toString().equals(""))){
                if (!(signinPasswordET.getText().toString().equals(""))){

                    Call<LoginItemCollectionDao> call = HttpManager.getInstance().getLoginApiService().loginData(signinEmailET.getText().toString(),
                            signinPasswordET.getText().toString());
                    call.enqueue(new Callback<LoginItemCollectionDao>() {
                        @Override
                        public void onResponse(Call<LoginItemCollectionDao> call,
                                               Response<LoginItemCollectionDao> response) {
                            if (response.isSuccessful()){
                                LoginItemCollectionDao logindao = response.body();

                                if (logindao.getStatus().toString().equals("success")){
                                    LoginItemDao itemDao = logindao.getMemberinfo().get(0);

                                    SharedPreferences sp = getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putInt("memberID", Integer.parseInt(itemDao.getMemberID()));
                                    editor.putString("memberName", (itemDao.getBname()+" "+itemDao.getBlastname()));
                                    editor.putString("memberMobile", itemDao.getBmobile());
                                    editor.putString("memberPoint", itemDao.getPoint());
                                    editor.putString("memberPointShow", itemDao.getPointShow());
                                    editor.putString("memberGender", itemDao.getGender());
                                    editor.apply();

                                    memberInCartSummary(itemDao.getMemberID().toString());

                                } else {
                                    showToast("ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง\nกรุณาลองใหม่อีกครั้งค่ะ");
                                }  // -----  if (logindao.getStatus().toString().equals("success"))
                            } else {
                                // Handle
                                try {
                                    showToast(response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }  //-------  if (response.isSuccessful())
                        }

                        @Override
                        public void onFailure(Call<LoginItemCollectionDao> call,
                                              Throwable t) {
                            showToast(t.toString());
                        }
                    });

                } else {
                    showToast("กรุณากรอก Password ของท่านค่ะ !");
                }  //-----  if (signinPasswordET.getText().toString().equals(""))
            } else {
                showToast("กรุณากรอก Email ของท่านค่ะ !");
            }  //------  if (signinEmailET.getText().toString().equals(""))
        }
    };



    final View.OnClickListener newAccountListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), RegisterActivity.class);
            startActivity(intent);
        }
    };



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
