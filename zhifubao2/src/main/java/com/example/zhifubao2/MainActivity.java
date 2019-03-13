package com.example.zhifubao2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 支付
     */
    private Button mBtnPay;
    private static final int SDK_PAY_FLAG = 1001;
    private String orderInfo="alipay_sdk=alipay-sdk-java-3.1.0&app_id=2018080760951276&biz_content=%7B%22out_trade_no%22%3A%2220190218114559974%22%2C%22subject%22%3A%22%E5%85%AB%E7%BB%B4%E7%A7%BB%E5%8A%A8%E9%80%9A%E4%BF%A1%E5%AD%A6%E9%99%A2-%E7%BB%B4%E5%BA%A6%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.28%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fmobile.bwstudent.com%2FpayApiProd%2FaliPay%2FmovieNotification&sign=ML3NzABYZBLbbMLc55Bw5ky3GLINAXOEkMkUj0jfF2FjKSADotfEkk1cjV8zv15Z0VELS6RK4TDb99mOphwlXMov14gid8Ni7uksDEcz4SvSsgDg6l%2Fo5h%2Fzq9hlWi79if91axbwsmAalevLTwuQwk4K4ZD5uT9%2FMyUYg1uqLrY5aOGwfae326QOyXiyMp7dzE0QnOlzN8vPZ26hG%2FSZWP03TpR0Gny%2BaL%2BQyKWsYnQcFKLTNcns6hD%2FhQ7%2FLn0v0qMBku%2BGXeSLgcBSZPkgAQvW54bTXwOCeVMnUeRuW%2F%2FfaGUdZY5a%2BZLKPNU6pGiufTzWxTEtpLim3c7OzCUPxg%3D%3D&sign_type=RSA2&timestamp=2019-02-20+18%3A55%3A47&version=1.0";
    @SuppressLint("HandlerLeak")
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // Result result = new Result((String) msg.obj);
//            Toast.makeText(this, "asdfg",
//                    Toast.LENGTH_LONG).show();
          //  ToastUtil.showToast((String) msg.obj);
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mBtnPay = (Button) findViewById(R.id.btnPay);
        mBtnPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnPay:
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(MainActivity.this);

                        Map<String, String> result = alipay.payV2(orderInfo, true);

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
                break;
        }


    }
}
