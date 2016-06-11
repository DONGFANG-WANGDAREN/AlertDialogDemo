package com.qiwang.alertdialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private SweetAlertDialog mSweetAlertDialog;
    private int mTimes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTimes = 3;
    }

    @OnClick(R.id.button2)
    public void reset() {
        mTimes = 3;
        Toast.makeText(this, "密码可被输入的次数已被重置！", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button1)
    public void show() {

        showDialog();
    }

    private void showDialog() {

        mSweetAlertDialog = new SweetAlertDialog(this);
        mSweetAlertDialog.setContentText(" ");
        if (mTimes <= 0) {
            mSweetAlertDialog.setTitleText("设备已被锁定");
        } else {
            mSweetAlertDialog.setTitleText("密码输入错误，还能输入" + mTimes + "次")
                    .showCancelButton(true)
                    .setCancelText("取消")
                    .setConfirmText("重新输入")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            mSweetAlertDialog.dismiss();

                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            mTimes--;
                            mSweetAlertDialog.dismiss();
                            showDialog();
                        }
                    });
        }
        mSweetAlertDialog.show();
    }

}
