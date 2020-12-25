package com.example.shopkuang.ui.sort;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopkuang.R;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.base.BaseFragment;
import com.example.shopkuang.bean.bean.type.TypeDataBean;
import com.example.shopkuang.bean.bean.type.TypeTabBean;
import com.example.shopkuang.interfaces.home.ITypeHome;
import com.example.shopkuang.presenter.TypePresenter;
import com.example.shopkuang.utils.ImageLoader;
import com.example.shopkuang.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TypeFragment extends BaseFragment<ITypeHome.presenter> implements ITypeHome.View {

    @BindView(R.id.iv_typeinfo_head_img)
    ImageView ivTypeinfoHeadImg;
    @BindView(R.id.tv_typeinfo_head_desc)
    TextView tvTypeinfoHeadDesc;
    @BindView(R.id.tv_typeinfo_title)
    TextView tvTypeinfoTitle;
    @BindView(R.id.mRlv_info)
    RecyclerView mRlvInfo;
    private int sortId;


    @Override
    protected int getLayout() {
        return R.layout.fragment_type_data;
    }

    @Override
    protected TypePresenter createPrenter() {
        return new TypePresenter(this);
    }

    @Override
    protected void initView() {
        sortId = (int) getArguments().get("sortId");
        mRlvInfo.setLayoutManager(new GridLayoutManager(getActivity(),3));

    }

    @Override
    protected void initData() {
        presenter.getTypeData(sortId);
    }

    @Override
    public void getTypeTabReturn(TypeTabBean result) {

    }

    @Override
    public void getTypeDataReturn(TypeDataBean result) {
        TypeDataBean.DataBean.CurrentCategoryBean currentCategory = result.getData().getCurrentCategory();
        List<TypeDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList = currentCategory.getSubCategoryList();

        ImageLoader.loadImage(currentCategory.getWap_banner_url(),ivTypeinfoHeadImg);
        TxtUtils.setTextView(tvTypeinfoHeadDesc,currentCategory.getFront_desc());
        TxtUtils.setTextView(tvTypeinfoTitle,currentCategory.getName()+"分类");



        ArrayList<TypeDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list = new ArrayList<>();
        list.addAll(subCategoryList);
        TypeFragAdapter adapter = new TypeFragAdapter(getActivity(), list);
        mRlvInfo.setAdapter(adapter);

        adapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int id = list.get(pos).getId();
                Intent intent = new Intent(getActivity(),SortActivity.class);
                //intent.putExtra("idtype",id);
                MyApp.getMap().put("typelist",list);
                startActivity(intent);
        }
        });
    }
}
