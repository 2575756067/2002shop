package com.example.shopkuang.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseActivity;
import com.example.shopkuang.bean.bean.login.LoginBean;
import com.example.shopkuang.bean.bean.login.LoginUserInfoBean;
import com.example.shopkuang.bean.bean.login.RegisterBean;
import com.example.shopkuang.interfaces.home.ILoginHome;
import com.example.shopkuang.presenter.LoginPresenter;
import com.example.shopkuang.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<ILoginHome.Presenter> implements ILoginHome.View {
    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_pw)
    EditText inputPw;
    //    @BindView(R.id.img_pw)
//    ImageView imgPw;
    @BindView(R.id.layout_pw)
    FrameLayout layoutPw;

    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forger)
    TextView tvForger;
    @BindView(R.id.btn_login_login)
    Button btnLoginLogin;

    private ImageView imgpw;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPrenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initView() {
        imgpw = findViewById(R.id.img_pw);
        imgpw.setTag(1);
        tvRegister = (TextView) findViewById(R.id.tv_register);

        //todo  注册
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,100);
            }
        });

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_login_login, R.id.img_pw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                login();
                break;
            case R.id.img_pw:
                int tag = (int) imgpw.getTag();
                if (tag == 1) {
                    imgpw.setImageResource(R.mipmap.ic_pw_open);
                    imgpw.setTag(2);
                    inputPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    imgpw.setImageResource(R.mipmap.ic_pw_close);
                    imgpw.setTag(1);
                    inputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
        }
    }

    private void login() {
        String username = inputUsername.getText().toString();
        String pw = inputPw.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw)) {
            presenter.getLogin(username, pw);
        } else {
            initPopwindow();
        }
    }

    private void initPopwindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.login_pop_layout, null);
        PopupWindow pw = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pw.showAtLocation(view, Gravity.CENTER, 0, 0);
        Button btn_ok = view.findViewById(R.id.btn_ok);
        ImageView iv_finish = view.findViewById(R.id.iv_finish);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });

        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });
    }

    @Override
    public void getLoginReturn(LoginBean result) {
        if (!TextUtils.isEmpty(result.getData().getToken())) {
            SpUtils.getInstance().setValue("token", result.getData().getToken());
            SpUtils.getInstance().setValue("uid", result.getData().getUserInfo().getUid());

            //回传值
            String name = inputUsername.getText().toString();
            Intent intent = getIntent();
            intent.putExtra("name", name);
            setResult(100, intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==100){
            String name = data.getStringExtra("name");
            String pw = data.getStringExtra("pw");
            inputUsername.setText(name);
            inputPw.setText(pw);
        }
    }

    @Override
    public void getRegisterReturn(RegisterBean result) {

    }

    @Override
    public void getLoginoutReturn(LoginoutBean result) {

    }

    @Override
    public void LoginUserInfoReturn(LoginUserInfoBean result) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
