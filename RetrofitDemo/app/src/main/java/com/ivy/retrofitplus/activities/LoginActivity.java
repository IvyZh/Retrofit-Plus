package com.ivy.retrofitplus.activities;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.ivy.retrofitplus.R;
import com.ivy.retrofitplus.base.BaseActivity;
import com.ivy.retrofitplus.net.NetUtils;
import com.ivy.retrofitplus.utils.L;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivy on 2016/10/10.
 *
 * @description:
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.bt_login)
    Button mBtLogin;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.bt_login)
    public void onClick() {
        String userName = getText(mEtUsername);
        String pwd = getText(mEtPwd);

        Call<String> call = NetUtils.getService().login(userName, pwd, 1);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                L.j(response.body());
                try {
                    JSONObject object = new JSONObject(response.body());
                    String status = object.getString("status");
                    if (TextUtils.equals("1", status)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                L.e(t.getMessage());
            }
        });

    }
}
