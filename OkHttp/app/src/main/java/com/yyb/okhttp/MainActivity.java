package com.yyb.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.yyb.httputils.remote.HttpManager;
import com.yyb.httputils.remote.ReqCallBack;
import com.yyb.httputils.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpManager.getInstance(MainActivity.this).getCheckForceUpdate(new ReqCallBack<String>() {
                    @Override
                    public void onReqSuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String data = jsonObject.optString("data");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onReqFailed(int errCode, String errorMsg) {
                        ToastUtil.showLong(MainActivity.this, errorMsg);
                    }
                });
            }
        });
    }
}
