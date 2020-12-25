package com.example.shopkuang.ui.sort;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shopkuang.bean.bean.type.TypeTabBean;

import java.util.ArrayList;
import java.util.List;


public class SortTabAdapter extends FragmentPagerAdapter {
    private List<TypeTabBean.DataBean.CategoryListBean> list;
    private ArrayList<Fragment> fragments;

    public SortTabAdapter(@NonNull FragmentManager fm, List<TypeTabBean.DataBean.CategoryListBean> list, ArrayList<Fragment> fragments) {
        super(fm);
        this.list = list;
        this.fragments = fragments;
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
        return list.get(position).getName();
    }
}


