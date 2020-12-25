package com.example.shopkuang.adapter.channe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shopkuang.bean.bean.ChanneBean;
import com.example.shopkuang.ui.ChaneActivity.ChannelFragment;

import java.util.ArrayList;
import java.util.List;

public class ChanneTabAdapter extends FragmentPagerAdapter {

    private ArrayList<ChannelFragment> fragments;
    private List<ChanneBean.DataBean.CategoryListBean> categoryList;

    public ChanneTabAdapter(@NonNull FragmentManager fm, ArrayList<ChannelFragment> fragments, List<ChanneBean.DataBean.CategoryListBean> categoryList) {
        super(fm);
        this.fragments = fragments;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categoryList.get(position).getName();
    }
}
