package com.example.shopkuang.ui.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseActivity;
import com.example.shopkuang.bean.bean.login.LoginBean;
import com.example.shopkuang.bean.bean.login.RegisterBean;
import com.example.shopkuang.interfaces.home.ILoginHome;
import com.example.shopkuang.presenter.LoginPresenter;
import com.example.shopkuang.utils.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterActivity extends BaseActivity<ILoginHome.Presenter> implements ILoginHome.View {

    ILoginHome.Presenter presenter;
    @BindView(R.id.et_admin)
    EditText etAdmin;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_agin_pwd)
    EditText etAginPwd;
    @BindView(R.id.et_auth_code)
    EditText etAuthCode;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private String username;
    private String token;

    @Override
    protected int getLayout() {
        return R.layout.register_activity_layout;
    }

    @Override
    protected LoginPresenter createPrenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initView() {
        ImageView authcode = findViewById(R.id.iv_auth_code);
        Button register = findViewById(R.id.btn_register);
        //刚进来的验证码
        Bitmap bitmap = CodeUtils.getInstance().createBitmap();
        authcode.setImageBitmap(bitmap);

        //点击切换验证码
        authcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = CodeUtils.getInstance().createBitmap();
                authcode.setImageBitmap(bitmap);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegist();
            }
        });
    }

    @Override
    protected void initData() {
        presenter = new LoginPresenter(this);
    }

    private void initRegist() {

        //todo 账号
        username = etAdmin.getText().toString();
        String pw = etPwd.getText().toString();         //todo 密码
        String pw2 = etAginPwd.getText().toString();    //todo 确认密码
        String ver = etAuthCode.getText().toString();   //验证码

        //不能为空
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw) && !TextUtils.isEmpty(pw2)) {
            //todo  密码正确
            if (pw.equals(pw2)) {
                presenter.getRegister(username, pw);
            } else {
                Toast.makeText(this, "两次密码输入的不一样", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void getLoginReturn(LoginBean result) {

    }

    @Override
    public void getRegisterReturn(RegisterBean result) {
//        token = result.getData().getToken().toString();
        int errno = result.getErrno();
        if (errno == 0) {
            finish();
        } else {
            //回传值到登录界面
            String name = etAdmin.getText().toString();
            String pw = etPwd.getText().toString();
            Intent intent = getIntent();
            intent.putExtra("name", name);
            intent.putExtra("pw", pw);
            setResult(100, intent);
            finish();

        }
//        //如果获得token不为空
//        if (!TextUtils.isEmpty(token)) {
//            //保存到sp中
//            SpUtils.getInstance().setValue(username, token);
//            SpUtils.getInstance().setValue("uid", result.getData().getUserInfo().getUid());
//

//
//            finish();
//
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
