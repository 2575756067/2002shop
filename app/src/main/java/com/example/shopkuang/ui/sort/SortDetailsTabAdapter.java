package com.example.shopkuang.ui.sort;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shopkuang.bean.bean.ChanneBean;
import com.example.shopkuang.ui.ChaneActivity.ChannelFragment;

import java.util.ArrayList;
import java.util.List;

public class SortDetailsTabAdapter extends FragmentPagerAdapter {

    private ArrayList<ChannelFragment> fragments;
    private List<ChanneBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList;

    public SortDetailsTabAdapter(@NonNull FragmentManager fm, ArrayList<ChannelFragment> fragments, List<ChanneBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList) {
        super(fm);
        this.fragments = fragments;
        this.subCategoryList = subCategoryList;
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
        return subCategoryList.get(position).getName();
    }
}
