package com.ivy.retrofitplus.activities;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ivy.retrofitplus.R;
import com.ivy.retrofitplus.base.BaseActivity;
import com.ivy.retrofitplus.domain.CurrentUserInfo;
import com.ivy.retrofitplus.net.NetUtils;
import com.ivy.retrofitplus.utils.L;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {


    @BindView(R.id.iv_protrait)
    ImageView mIvProtrait;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.bt_upload_head)
    Button mBtUploadHead;

    @Override
    protected void loadData() {
        getUserInfo();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void initListener() {

    }

    private void getUserInfo() {
        Call<CurrentUserInfo> call = NetUtils.getService().getUserInfo();
        call.enqueue(new Callback<CurrentUserInfo>() {
            @Override
            public void onResponse(Call<CurrentUserInfo> call, final Response<CurrentUserInfo> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvUsername.setText(response.body().getUsername());
                        if (!response.body().getImage_id().equals("1")) {
                            //http://a.33iq.com/data/uploaded-files/big/16/10/10/14760691130.jpg
                            //16/10/10/14760691130
                            String url = "http://a.33iq.com/data/uploaded-files/big/" + response.body().getImage_id() + ".jpg";
                            L.v(url);
                            Glide.with(MainActivity.this).load(url).into(mIvProtrait);

                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<CurrentUserInfo> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_upload_head)
    public void onClick() {
        File file = new File(Environment.getExternalStorageDirectory() + "/" + "2.jpg");
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        L.v("size:" + file.length());

        Call<String> call = NetUtils.getService().uploadHead("big", requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }
}
