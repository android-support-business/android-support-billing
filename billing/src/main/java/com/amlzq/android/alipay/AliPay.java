package com.amlzq.android.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.amlzq.android.log.Log;

import java.util.Map;

/**
 * https://docs.open.alipay.com/204/105465/
 **/

public class AliPay {
    String TAG = "com.amlzq.android.billing.alipay.AliPay";

    public void startAppPay(final Activity activity, final String orderInfo,
                            final Handler handler, final int resultMessageCode) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.d(TAG, result.toString());

                Message msg = handler.obtainMessage(resultMessageCode);
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 处理支付宝支付结果
     */
    public String handleResult(Object obj) {
        @SuppressWarnings("unchecked")
        PayResult payResult = new PayResult((Map<String, String>) obj);
        /**
         对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
         */
        String resultStatus = "-1";
        String message = "";
        if (!TextUtils.isEmpty(payResult.getResultStatus())) {
            resultStatus = payResult.getResultStatus();
        }
        Log.e(TAG, resultStatus);
        if (TextUtils.equals(resultStatus, "4001")) {
            // 这个返回码没测试，暂定网页版支付宝支付成功返回
            message = "支付成功";
            resultStatus = "100" + "." + message;
        } else if (TextUtils.equals(resultStatus, "9000")) {
            message = "支付成功";
            resultStatus = "100" + "." + message;
        } else if (TextUtils.equals(resultStatus, "8000")) {
            message = "正在确认支付结果";
            resultStatus = "8000" + "." + message;
        } else if (TextUtils.equals(resultStatus, "6004")) {
            message = "未获取到支付结果";
            resultStatus = "6004" + "." + message;
        } else if (TextUtils.equals(resultStatus, "4000")) {
            message = "订单支付失败";
            resultStatus = "-100" + "." + message;
        } else if (TextUtils.equals(resultStatus, "5000")) {
            message = "重复请求";
            resultStatus = "5000" + "." + message;
        } else if (TextUtils.equals(resultStatus, "6001")) {
            message = "支付取消";
            resultStatus = "0" + "." + message;
        } else if (TextUtils.equals(resultStatus, "6002")) {
            message = "网络连接出错";
            resultStatus = "6002" + "." + message;
        } else {
            message = "其它支付错误";
            resultStatus = "" + "." + message;
        }
        Log.e(TAG, message);
        return resultStatus;
    }

}