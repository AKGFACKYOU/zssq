package com.example.jack.mymusic.login.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jack.mymusic.AppStringUtil;
import com.example.jack.mymusic.MainActivity;
import com.example.jack.mymusic.R;
import com.example.jack.mymusic.bean.LoginResponse;
import com.example.jack.mymusic.utils.AppConfigUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jack on 2017/6/5.
 */

public class LoginFragment extends BaseFragment{
    private static final String TAG = "LoginFragment";
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pass)
    EditText etPass;
    Unbinder unbinder;
    @BindView(R.id.bt_register)
    AppCompatButton btRegister;
    @BindView(R.id.bt_login)
    AppCompatButton btLogin;
    Unbinder unbinder1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, null);
        unbinder1 = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.bt_register, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.bt_login:
                String name = etName.getText().toString();
                String password = etPass.getText().toString();

                if (!AppStringUtil.checkUserName(name)) {
                    etName.setError("用户名格式不正确");
                    return;
                }

                if (!AppStringUtil.checkPassword(password)) {
                    etPass.setError("密码格式不正确");
                    return;
                }
                login(name, password);



                break;
            case R.id.bt_register:
                RegisterFragment registerFragment = new RegisterFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_content, registerFragment);
                //将fragment 添加到返回栈
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }

    private void login(String username, String password) {
        Toast.makeText(getActivity(), "登陆啦", Toast.LENGTH_SHORT).show();
        OkHttpClient client = new OkHttpClient();

        String url = "https://leancloud.cn:443/1.1/login?username=" + username + "&password=" + password;

        //测试账号  aaa  123456
        //测试账号  weidong  123456
        //测试账号  bbb  123456
        //测试账号  ccc  123456
        //测试账号  ddd  123456
        Request request = new Request.Builder()
                .addHeader("X-LC-Id", "kCFRDdr9tqej8FRLoqopkuXl-gzGzoHsz")
                .addHeader("X-LC-Key", "bmEeEjcgvKIq0FRaPl8jV2Um")
                .addHeader("Content-Type", "application/json")
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e(TAG, "登陆失败 ");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //https://leancloud.cn/docs/error_code.html#_202
                final String result = response.body().string();
                Log.e(TAG, "onResponse: " + result);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        if (response.code() == 200) {//获取请求结果码
                            //解析成功的数据
                            Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
                            //解析Json后需要将Json封装为Java的对象

                            //参数一  json的字符串
                            //参数二  json转换后的对象
                            LoginResponse loginResponse = new Gson().fromJson(result, LoginResponse.class);

                            Log.e(TAG, "结果是："+loginResponse.toString() );


                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            //getActivity()  == LoginActivity
                            getActivity().finish();


                            AppConfigUtils.getInstance().setGuide(getActivity(),false);


                        } else {

                            Toast.makeText(getActivity(), "登陆失败", Toast.LENGTH_SHORT).show();
                        }
                        closeProgressDialog();
                    }
                });

            }
        });

    }
}
