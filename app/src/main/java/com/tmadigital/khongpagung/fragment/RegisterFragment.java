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
import com.tmadigital.khongpagung.dao.LoginItemCollectionDao;
import com.tmadigital.khongpagung.dao.LoginItemDao;
import com.tmadigital.khongpagung.dao.RegisterItemCollectionDao;
import com.tmadigital.khongpagung.dao.RegisterItemDao;
import com.tmadigital.khongpagung.manager.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class RegisterFragment extends Fragment {

    private EditText registerNameET;
    private EditText registerLastNameET;
    private EditText registerEmailET;
    private EditText registerPasswordET;
    private EditText registerConfirmPasswordET;
    private Button registerBtn;
    private Button registerFBBtn;
    private LoginButton fbLogin;
    private CallbackManager callbackManager;

    public RegisterFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
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
        registerNameET = (EditText) rootView.findViewById(R.id.registerNameET);
        registerLastNameET = (EditText) rootView.findViewById(R.id.registerLastNameET);
        registerEmailET = (EditText) rootView.findViewById(R.id.registerEmailET);
        registerPasswordET = (EditText) rootView.findViewById(R.id.registerPasswordET);
        registerConfirmPasswordET = (EditText) rootView.findViewById(R.id.registerConfirmPasswordET);
        registerBtn = (Button) rootView.findViewById(R.id.registerBtn);
        fbLogin = (LoginButton) rootView.findViewById(R.id.fbLogin);

        fbLogin.setReadPermissions("user_friends", "email");
        fbLogin.setFragment(this);
        fbLogin.setBackgroundResource(R.drawable.signupfbbtn);
        fbLogin.setText("");
        fbLogin.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);


        registerBtn.setOnClickListener(registerOnClickListener);



        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Profile profile = Profile.getCurrentProfile();

                final String userId = loginResult.getAccessToken().getUserId();
                String accessToken = loginResult.getAccessToken().getToken();

                final String profileImgUrl = "https://graph.facebook.com/" + userId + "/picture?type=large";


                //Glide.with(MainActivity.this)
                //      .load(profileImgUrl)
                //      .into(profileImgView);

                Call<RegisterItemCollectionDao> call = HttpManager.getInstance().getRegisterApiService().registerData("-",
                        "-",
                        userId,
                        "-");
                call.enqueue(new Callback<RegisterItemCollectionDao>() {
                    @Override
                    public void onResponse(Call<RegisterItemCollectionDao> call,
                                           Response<RegisterItemCollectionDao> response) {
                        if (response.isSuccessful()){
                            RegisterItemCollectionDao regdao = response.body();

                            if (regdao.getStatus().toString().equals("success")){
                                RegisterItemDao itemDao = regdao.getMemberinfo().get(0);

                                SharedPreferences sp = getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putInt("memberID", Integer.parseInt(itemDao.getMemberID()));
                                editor.putString("memberName", (itemDao.getBname()+" "+itemDao.getBlastname()));
                                editor.putString("memberMobile", itemDao.getBmobile());
                                editor.putString("memberPoint", itemDao.getPoint());
                                editor.putString("memberPointShow", itemDao.getPointShow());
                                editor.putString("memberGender", itemDao.getGender());
                                editor.apply();

                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }else{
                                Call<LoginItemCollectionDao> callLogin = HttpManager.getInstance().getLoginApiService().loginData(userId,
                                        "-");
                                callLogin.enqueue(new Callback<LoginItemCollectionDao>() {
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



                                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                                startActivity(intent);
                                            } else {

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
                            }  //---- if (regdao.getStatus().toString().equals("success"))
                        }else{
                            // Handle
                            try {
                                showToast(response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }  //------  if (response.isSuccessful())
                    }

                    @Override
                    public void onFailure(Call<RegisterItemCollectionDao> call,
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

    final View.OnClickListener registerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!(registerNameET.getText().toString().equals(""))){
                if (!(registerLastNameET.getText().toString().equals(""))){
                    if (!(registerEmailET.getText().toString().equals(""))){
                        if (!(registerPasswordET.getText().toString().equals(""))){
                            if (!(registerConfirmPasswordET.getText().toString().equals(""))){
                                if (registerPasswordET.getText().toString().equals(registerConfirmPasswordET.getText().toString())){
                                    Call<RegisterItemCollectionDao> call = HttpManager.getInstance().getRegisterApiService().registerData(registerNameET.getText().toString(),
                                            registerLastNameET.getText().toString(),
                                            registerEmailET.getText().toString(),
                                            registerPasswordET.getText().toString());
                                    call.enqueue(new Callback<RegisterItemCollectionDao>() {
                                        @Override
                                        public void onResponse(Call<RegisterItemCollectionDao> call,
                                                               Response<RegisterItemCollectionDao> response) {
                                            if (response.isSuccessful()){
                                                RegisterItemCollectionDao regdao = response.body();

                                                if (regdao.getStatus().toString().equals("success")){
                                                    RegisterItemDao itemDao = regdao.getMemberinfo().get(0);

                                                    SharedPreferences sp = getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = sp.edit();
                                                    editor.putInt("memberID", Integer.parseInt(itemDao.getMemberID()));
                                                    editor.putString("memberName", (itemDao.getBname()+" "+itemDao.getBlastname()));
                                                    editor.putString("memberMobile", itemDao.getBmobile());
                                                    editor.putString("memberPoint", itemDao.getPoint());
                                                    editor.putString("memberPointShow", itemDao.getPointShow());
                                                    editor.putString("memberGender", itemDao.getGender());
                                                    editor.apply();

                                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                                    startActivity(intent);
                                                }else{
                                                    showToast("Email มีอยู่ในระบบแล้วค่ะ\nกรุณาลองใหม่อีกครั้งค่ะ");
                                                }  //---- if (regdao.getStatus().toString().equals("success"))
                                            }else{
                                                // Handle
                                                try {
                                                    showToast(response.errorBody().string());
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }  //------  if (response.isSuccessful())
                                        }

                                        @Override
                                        public void onFailure(Call<RegisterItemCollectionDao> call,
                                                              Throwable t) {
                                            showToast(t.toString());
                                        }
                                    });
                                } else {
                                    showToast("Password และ Confirm Password ต้องเหมือนกันค่ะ !");
                                }  //------  if (registerPasswordET.getText().toString().equals(registerConfirmPasswordET.getText().toString()))
                            } else {
                                showToast("กรุณากรอก Confirm Password ของท่านค่ะ !");
                            }  //------  if (!(registerConfirmPasswordET.getText().toString().equals("")))
                        } else {
                            showToast("กรุณากรอก Password ของท่านค่ะ !");
                        }  //------  if (!(registerPasswordET.getText().toString().equals("")))
                    } else {
                        showToast("กรุณากรอก Email ของท่านค่ะ !");
                    } //----  if (!(registerEmailET.getText().toString().equals("")))
                } else {
                    showToast("กรุณากรอกนามสกุลของท่านค่ะ !");
                }  //----- if (!(registerLastNameET.getText().toString().equals("")))
            } else {
                showToast("กรุณากรอกชื่อของท่านค่ะ !");
            }  //------  if (!(registerNameET.getText().toString().equals("")))
        }  //-----  public void onClick(View v)
    };



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
