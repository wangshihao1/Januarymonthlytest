package com.bawei.wangshihao0120.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.wangshihao0120.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.qrtv_password)
    TextView qrtvPassword;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.qret_password)
    EditText qretPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                 String phone = etName.getText().toString();
                 String password = etPassword.getText().toString();
                 String qretpassword = qretPassword.getText().toString();
                 if (TextUtils.isEmpty(phone)&&TextUtils.isEmpty(password)&&TextUtils.isEmpty(qretpassword)){
                     Toast.makeText(this,"输入不能为空",Toast.LENGTH_SHORT).show();
                 }
                 else if (password==qretpassword){
                     Toast.makeText(this,"密码验证错误",Toast.LENGTH_SHORT).show();
                 }
                   else {startActivity(new Intent(this,LoginActivity.class));}
                 break;
        }
    }
}
