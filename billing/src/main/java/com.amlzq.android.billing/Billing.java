package com.amlzq.android.billing;

import com.amlzq.android.billing.alipay.AliPay;

/**
 * 结算助手
 * 统一入口
 */

public class Billing {

    /**
     * result code
     * 支付成功
     */
    public static final int RC_PAYMENT_SUCCESSFUL = 200;
    /**
     * result code
     * 支付取消
     */
    public static final int RC_PAYMENT_CANCELL = 201;
    /**
     * result code
     * 支付失败
     */
    public static final int RC_PAYMENT_FAILED = 202;

    public String getResultMessage(int code) {
        String message = "";
        switch (code) {
            case RC_PAYMENT_SUCCESSFUL:
                message = "";
                break;
            case RC_PAYMENT_CANCELL:
                message = "";
                break;
            case RC_PAYMENT_FAILED:
                message = "";
                break;
            default:
                message = "";
                break;
        }
        return message;
    }

    public void payment() {
        AliPay alipay = new AliPay();
        alipay.startAppPay();
    }

}