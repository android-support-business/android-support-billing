package com.amlzq.asb;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.amlzq.android.billing.alipay.AliPay;

public class MainActivity extends Activity {

    TextView mTVInfo;

    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 11:

                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTVInfo = findViewById(R.id.tv_info);
    }

    @Override
    protected void onDestroy() {
        // 清除以该Handler为target的所有Message（包括Callback）
        // Remove all Runnable and Message.
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    public void onAlipayEvent(View view) {
        AliPay aliPay = new AliPay();
        aliPay.startAppPay(this, "", mHandler, 11);
    }

    public void onWechatPayEvent(View view) {
    }

    public void onUnionPayEvent(View view) {
    }

}
