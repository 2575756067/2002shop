package com.example.shopkuang.ui.me;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.shopkuang.R;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseFragment;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.ui.Details.favorites.FavoritesActivity;
import com.example.shopkuang.ui.login.LoginActivity;
import com.example.shopkuang.utils.SpUtils;

import butterknife.BindView;

public class MeFragment extends BaseFragment {
    @BindView(R.id.iv_my_head)
    ImageView ivMyHead;
    @BindView(R.id.tv_my_name)
    TextView tvMyName;
    @BindView(R.id.tv_my_sign)
    TextView tvsign;
    @BindView(R.id.iv_my_details)
    ImageView ivMyDetails;
    @BindView(R.id.ll_my_shoucang)
    LinearLayout ll_favorites;


    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        //todo 跳转到登录页面
        ivMyDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(SpUtils.getInstance().getString("token"))){
                    //点击个人资料把名字传过去
                    String name = tvMyName.getText().toString();
                    MyApp.getMap().put("name",name);
                    startActivity(new Intent(getActivity(), MyDetailsActivity.class));
                }else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivityForResult(intent, 100);
                }
            }
        });
        //todo  跳转到个人资料 详情
        ivMyHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击个人资料把名字传过去
                String name = tvMyName.getText().toString();
                MyApp.getMap().put("name",name);
                startActivity(new Intent(getActivity(), MyDetailsActivity.class));
            }
        });

        //todo  跳转到收藏页面
        ll_favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FavoritesActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 100) {
            String name = data.getStringExtra("name");
            tvMyName.setText(name);
        }
    }
}
