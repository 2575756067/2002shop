package com.example.shopkuang.ui.brand;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopkuang.R;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.bean.pinpai.BrandBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandShopActivity extends AppCompatActivity {

    @BindView(R.id.barnd_shop_recycle)
    RecyclerView barndShopRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_shop);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        List<BrandBean.DataBeanX.DataBean> list = (List<BrandBean.DataBeanX.DataBean>) MyApp.getMap().get("list");
        //自定义分割线    白色
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.shape_rlv_line));
        barndShopRecycle.addItemDecoration(dividerItemDecoration);
        barndShopRecycle.setLayoutManager(new LinearLayoutManager(this));
        BrandShopAdapter adapter = new BrandShopAdapter(this, list);
        barndShopRecycle.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        adapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int id = list.get(pos).getId();
                MyApp.getMap().put("id", id);
                startActivity(new Intent(BrandShopActivity.this, BrandListActivity.class));
            }
        });
    }
}