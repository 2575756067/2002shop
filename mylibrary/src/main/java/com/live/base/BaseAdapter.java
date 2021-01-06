package com.live.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<D> extends RecyclerView.Adapter {

    List<D> mData; //adapter的数据
    protected Context context;
    protected IListClick click;
    protected IItemViewClick iItemViewClick;

    //定义条目回调接口
    public void addListClick(IListClick click){
        this.click = click;
    }

    //接口回调 给条目元素点击
    public void addItemViewClick(IItemViewClick click){
        this.iItemViewClick = click;
    }

    public BaseAdapter(Context context, List<D> Data) {
        this.context = context;
        this.mData = Data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout=getLayout(viewType);
        if(layout<=0){
            new RuntimeException("非法布局");
        }
        View view = LayoutInflater.from(context).inflate(layout,parent,false);
        VH vh=new VH(view);//绑定视图给VH
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //接口回调的调用
                if(click!=null){//多条目点击也能获取
                    click.itemClick(vh.getLayoutPosition());
                }
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindData(mData.get(position), (VH) holder);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //多布局
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    //获取集合数据
    protected  List<D> getData(){
        return mData;
    }

    protected abstract int getLayout(int type);

    protected abstract void bindData(D data,VH vh);

    //定义条目回调接口
    public interface IListClick{
        void itemClick(int pos);
    }

    //接口回调 给条目元素点击
    public interface IItemViewClick<D>{
        //条目中的元素点击
        void itemViewClick(int viewid, D data);
    }

    public class VH extends RecyclerView.ViewHolder{
        SparseArray views = new SparseArray();
        public VH(@NonNull View itemView) {
            super(itemView);
        }
        //查找item的view
        public View getViewById(int id){//查找item的view
            View view = (View) views.get(id);
            if(view == null){
                view = itemView.findViewById(id);
                views.append(id,view);
            }
            return view;
        }
    }
}
