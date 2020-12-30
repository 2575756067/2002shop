package com.example.shopkuang.ui.shopaddress;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseActivity;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.interfaces.home.IAddressHome;
import com.example.shopkuang.presenter.AddressPresenter;
import com.example.shopkuang.bean.bean.address.AddressAddProvinceBean;
import com.example.shopkuang.bean.bean.address.AddressBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopAddressesAddActivity extends BaseActivity<IAddressHome.Presenter> implements IAddressHome.View, View.OnClickListener {

    IAddressHome.Presenter presenter;
    @BindView(R.id.address_add_name)
    EditText addressAddName;
    @BindView(R.id.address_add_phone)
    EditText addressAddPhone;

    @BindView(R.id.address_add_xiangxi)
    EditText addressAddXiangxi;
    @BindView(R.id.address_add_moren)
    RadioButton addressAddMoren;
    @BindView(R.id.address_add_btn_cancel)
    Button addressAddBtnCancel;
    @BindView(R.id.address_add_btn_ok)
    Button addressAddBtnOk;
    @BindView(R.id.addreess_con)
    ConstraintLayout addreessCon;

    // recyclerView 选中Item 的颜色
    private int defaultSelectedColor = Color.parseColor("#AB2B2B");
    // recyclerView 未选中Item 的颜色
    private int defaultUnSelectedColor = Color.parseColor("#262626");
    // 确定字体不可以点击时候的颜色
    private int defaultSureUnClickColor = Color.parseColor("#7F7F7F");
    // 确定字体可以点击时候的颜色
    private int defaultSureCanClickColor = Color.parseColor("#AB2B2B");

    private int defaultTabCount = 3; //tab 的数量
    private String defaultProvince = "省份"; //显示在上面tab中的省份
    private String defaultCity = "城市"; //显示在上面tab中的城市
    private String defaultDistrict = "区县"; //显示在上面tab中的区县

    private AddressAddProvinceBean addressAddProvinceBean;  // 总数据
    private AddressAddProvinceBean.DataBean mSelectProvice; //选中 省份 bean
    private AddressAddProvinceBean.DataBean mSelectCity;//选中 城市  bean
    private AddressAddProvinceBean.DataBean mSelectDistrict;//选中 区县  bean

    private int mSelectProvicePosition = 0; //选中 省份 位置
    private int mSelectCityPosition = 0;//选中 城市  位置
    private int mSelectDistrictPosition = 0;//选中 区县  位置

    private TabLayout mTabLayout; // tabLayout  pw
    private List<AddressAddProvinceBean.DataBean> mRvData; // 用来在recyclerview显示的数据  pw
    private TextView mTvSure; //确定  pw
    private RecyclerView mRvList; // 显示数据的RecyclerView pw
    private ShopAddressesAddAdapter mAdapter;   // pw recyclerview 的 adapter
    private PopupWindow window;     //pw
    private EditText addressAddShengQuXian;

    @Override
    protected int getLayout() {
        return R.layout.activity_shop_addresses_add;
    }

    @Override
    protected IAddressHome.Presenter createPrenter() {
        return new AddressPresenter(this);
    }

    @Override
    protected void initView() {
        //显示省份城市的数据
        addressAddShengQuXian = findViewById(R.id.address_add_sheng_qu_xian);
        addressAddShengQuXian.setFocusable(false);//让EditText失去焦点，然后获取点击事件
    }

    @Override
    protected void initData() {
        presenter = new AddressPresenter(this);
    }


    @OnClick({R.id.address_add_moren, R.id.address_add_btn_cancel, R.id.address_add_btn_ok, R.id.address_add_sheng_qu_xian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.address_add_moren:
                break;
            case R.id.address_add_btn_cancel:
                finish();
                break;
            case R.id.address_add_btn_ok:
                //todo  保存地址
                SaveAddress();
                break;
            case R.id.address_add_sheng_qu_xian:
                initPw();
                break;
        }
    }

    //todo  保存地址
    private void SaveAddress() {
        String name = addressAddName.getText().toString();  //todo  姓名
        String phone = addressAddPhone.getText().toString();//todo  手机号
        String address = addressAddShengQuXian.getText().toString();  //todo  省区县
        String detailsAddress = addressAddXiangxi.getText().toString(); //todo 详细地址

        //todo  跳转到地址页面
        Intent intent = new Intent(this,ShopAddressesActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("phone",phone);
        intent.putExtra("address",address);
        intent.putExtra("detailsAddress",detailsAddress);
        startActivity(intent);
    }

    private void initPw() {
        //点击省份
        // UI
        View rootView = View.inflate(this, R.layout.address_add_province_pw, null);
        window = new PopupWindow(rootView, ViewGroup.LayoutParams.MATCH_PARENT, 1000);
        presenter.getAddressProvince(1);

        //集合
        mRvData = new ArrayList<AddressAddProvinceBean.DataBean>();

        // 确定
        mTvSure = rootView.findViewById(R.id.tvSure);
        mTvSure.setTextColor(defaultSureUnClickColor);
        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.tvSure) {
                    if (mSelectProvice != null &&
                            mSelectCity != null &&
                            mSelectDistrict != null) {
                        //   回调接口
                        if (addressAddShengQuXian != null) {
                            addressAddShengQuXian.setText(mSelectProvice.getName() + " " + mSelectCity.getName() + " " + mSelectDistrict.getName());
                            window.dismiss();
                            WindowManager.LayoutParams attributes = getWindow().getAttributes();
                            attributes.alpha = 1f;
                            getWindow().setAttributes(attributes);
                        }
                    } else {
                        Toast.makeText(ShopAddressesAddActivity.this, "地址还没有选完整哦", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // tablayout初始化
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tlTabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText(defaultProvince));
        mTabLayout.addTab(mTabLayout.newTab().setText(defaultCity));
        mTabLayout.addTab(mTabLayout.newTab().setText(defaultDistrict));

        mTabLayout.addOnTabSelectedListener(tabSelectedListener);

        // recyclerview adapter的绑定
        mRvList = (RecyclerView) rootView.findViewById(R.id.rvList);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShopAddressesAddAdapter(this, mRvData);
        mRvList.setAdapter(mAdapter);

        // 初始化默认的本地数据  也提供了方法接收外面数据
        mRvList.post(new Runnable() {
            @Override
            public void run() {
                presenter.getAddressProvince(1);
            }
        });

        //pw显示
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(null);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.5f;
        getWindow().setAttributes(attributes);
        window.showAtLocation(addreessCon, Gravity.BOTTOM, 0, 0);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 1f;
                getWindow().setAttributes(attributes);
            }
        });
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mRvData = null;
    }

    /**
     * TabLayout 切换事件
     */
    private int pid;
    TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mRvData.clear();
            switch (tab.getPosition()) {
                case 0:
                    mAdapter.notifyDataSetChanged();
                    // 滚动到这个位置
                    mRvList.smoothScrollToPosition(mSelectProvicePosition);
                    break;
                case 1:
                    // 点到城市的时候要判断有没有选择省份
                    if (mSelectProvice != null) {
                        for (AddressAddProvinceBean.DataBean itemBean : mRvData) {
                            if (itemBean.getName().equals(mSelectProvice.getName()))
                                mRvData.add(itemBean);
                        }
                    } else {
                        Toast.makeText(ShopAddressesAddActivity.this, "请您先选择省份", Toast.LENGTH_SHORT).show();
                    }
                    mAdapter.notifyDataSetChanged();
                    // 滚动到这个位置
                    mRvList.smoothScrollToPosition(mSelectCityPosition);
                    break;
                case 2:
                    // 点到区的时候要判断有没有选择省份与城市
                    if (mSelectProvice != null && mSelectCity != null) {
                        for (AddressAddProvinceBean.DataBean itemBean : mRvData) {
                            if (itemBean.getName().equals(mSelectCity.getName()))
                                mRvData.add(itemBean);
                        }
                    } else {
                        Toast.makeText(ShopAddressesAddActivity.this, "请您先选择省份与城市", Toast.LENGTH_SHORT).show();
                    }
                    mAdapter.notifyDataSetChanged();
                    // 滚动到这个位置
                    mRvList.smoothScrollToPosition(mSelectDistrictPosition);
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    @Override
    public void getAddressData(AddressBean result) {

    }

    //省市列表
    @Override
    public void getAddressProvince(AddressAddProvinceBean result) {
        List<AddressAddProvinceBean.DataBean> data = result.getData();
        mRvData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    //内部适配器
    class ShopAddressesAddAdapter extends BaseAdapter {

        public ShopAddressesAddAdapter(Context context, List Data) {
            super(context, Data);
        }

        @Override
        protected int getLayout(int type) {
            return R.layout.item_address_text;
        }

        @Override
        protected void bindData(Object data, VH vh) {

            int tabSelectPosition = mTabLayout.getSelectedTabPosition();
            AddressAddProvinceBean.DataBean bean = (AddressAddProvinceBean.DataBean) data;

            TextView mTitle = (TextView) vh.getViewById(R.id.item_address_add_TvTitle);
            mTitle.setText(bean.getName());
            mTitle.setTextColor(defaultUnSelectedColor);

            // 设置选中效果的颜色
            switch (tabSelectPosition) {
                case 0:
                    if (bean != null && mSelectProvice != null &&
                            bean.getName().equals(mSelectProvice.getName())) {
                        mTitle.setTextColor(defaultSelectedColor);
                    }
                    break;
                case 1:
                    if (bean != null && mSelectCity != null &&
                            bean.getName().equals(mSelectCity.getName())) {
                        mTitle.setTextColor(defaultSelectedColor);
                    }
                    break;
                case 2:
                    if (bean != null &&
                            mSelectDistrict != null &&
                            bean.getName().equals(mSelectDistrict.getName())) {
                        mTitle.setTextColor(defaultSelectedColor);
                    }
                    break;
            }

            // 设置点击之后的事件
            mTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 点击 分类别
                    switch (tabSelectPosition) {
                        case 0:
                            mSelectProvice = bean;
                            // 清空后面两个的数据
                            mSelectCity = null;
                            mSelectDistrict = null;
                            mSelectCityPosition = 0;
                            mSelectDistrictPosition = 0;
                            mTabLayout.getTabAt(1).setText(defaultCity);
                            mTabLayout.getTabAt(2).setText(defaultDistrict);
                            // 设置这个对应的标题
                            mTabLayout.getTabAt(0).setText(mSelectProvice.getName());
                            // 跳到下一个选择
                            mTabLayout.getTabAt(1).select();
                            // 灰掉确定按钮
                            mTvSure.setTextColor(defaultSureUnClickColor);
                            presenter.getAddressProvince(bean.getId());
                            mSelectProvicePosition = bean.getId();
                            break;
                        case 1:
                            mSelectCity = bean;
                            // 清空后面一个的数据
                            mSelectDistrict = null;
                            mSelectDistrictPosition = 0;
                            mTabLayout.getTabAt(2).setText(defaultDistrict);
                            // 设置这个对应的标题
                            mTabLayout.getTabAt(1).setText(mSelectCity.getName());
                            // 跳到下一个选择
                            mTabLayout.getTabAt(2).select();
                            // 灰掉确定按钮
                            mTvSure.setTextColor(defaultSureUnClickColor);
                            presenter.getAddressProvince(bean.getId());
                            mSelectCityPosition = bean.getId();
                            break;
                        case 2:
                            mSelectDistrict = bean;
                            // 没了，选完了，这个时候可以点确定了
                            mTabLayout.getTabAt(2).setText(mSelectDistrict.getName());
                            notifyDataSetChanged();
                            // 确定按钮变亮
                            mTvSure.setTextColor(defaultSureCanClickColor);
                            presenter.getAddressProvince(bean.getId());
                            mSelectDistrictPosition = bean.getId();
                            break;
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}