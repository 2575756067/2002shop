package com.example.shopkuang.ui.sort;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.shopkuang.MainActivity;
import com.example.shopkuang.R;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseFragment;
import com.example.shopkuang.bean.shop.type.TypeDataBean;
import com.example.shopkuang.bean.shop.type.TypeTabBean;
import com.example.shopkuang.interfaces.home.ITypeHome;
import com.example.shopkuang.presenter.TypePresenter;
import com.example.shopkuang.utils.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;

public class SortFragment extends BaseFragment<ITypeHome.presenter> implements ITypeHome.View {
    @BindView(R.id.mTab_type)
    VerticalTabLayout mTabType;
    @BindView(R.id.mVp_type)
    CustomViewPager mVpType;
    private ArrayList<Fragment> fragments;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected TypePresenter createPrenter() {
        return new TypePresenter(this);
    }

    @Override
    protected void initView() {
        mVpType.setScanScroll(false);
    }

    @Override
    protected void initData() {
        presenter.getTypeTabData();

    }

    @Override
    public void getTypeTabReturn(TypeTabBean result) {
        initTabData(result.getData().getCategoryList()); //垂直TAB数据
    }

    private void initTabData(List<TypeTabBean.DataBean.CategoryListBean> categoryList) {

        fragments = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            TypeFragment typeFragment = new TypeFragment();
            int id = categoryList.get(i).getId();
            Bundle bundle = new Bundle();
            bundle.putInt("sortId",id);
            typeFragment.setArguments(bundle);
            fragments.add(typeFragment);

        }
        SortTabAdapter adapter = new SortTabAdapter(getChildFragmentManager(),categoryList,fragments);
        mVpType.setAdapter(adapter);
        mTabType.setupWithViewPager(mVpType);
    }


    @Override
    public void getTypeDataReturn(TypeDataBean result) {

    }
}
