package com.live.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.live.MyApplication;
import com.live.R;
import com.live.adapter.RoomAdapter;
import com.live.base.BaseActivity;
import com.live.base.BaseAdapter;
import com.live.model.bean.MeRoomBean;
import com.live.model.bean.StartLiveBean;
import com.live.model.bean.RoomBean;
import com.live.presenter.RoomPresenter;
import com.live.view.IRoom;

import java.util.ArrayList;
import java.util.List;

/*主界面*/
public class RoomActivity extends BaseActivity<IRoom.Presenter> implements IRoom.View, View.OnClickListener {

    ImageView imgBack;
    TextView title;
    RecyclerView recyList;
    ImageView imgHome;
    ImageView imgStartLive;

    List<RoomBean.DataBean> roomBeans;
    RoomAdapter roomAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_room;
    }

    @Override
    protected IRoom.Presenter createPersenter() {
        return new RoomPresenter();
    }

    @Override
    protected void initView() {

        imgBack = findViewById(R.id.img_back);
        title = findViewById(R.id.title_room);
        recyList = findViewById(R.id.recy_list);
        imgStartLive = findViewById(R.id.img_startLive);
        imgHome = findViewById(R.id.img_home);

        imgHome.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        imgStartLive.setOnClickListener(this);

        recyList.setLayoutManager(new LinearLayoutManager(MyApplication.application));
        roomBeans = new ArrayList<>();
        roomAdapter = new RoomAdapter(this,roomBeans);
        recyList.setAdapter(roomAdapter);

        roomAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int id = roomBeans.get(pos).getId();
                Intent intent = new Intent(RoomActivity.this, LiveActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        //获取房间列表
        persenter.room();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.img_startLive) {
            //获取自己房间信息
            persenter.meroom();
        } else if (id == R.id.img_back) {
            finish();
        }else if(id == R.id.img_home){
            //创建房间
            Intent intent = new Intent(RoomActivity.this, CreateRoomActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void roomreturn(RoomBean result) {
        roomBeans.clear();
        if (result.getErrno() == 0) {
            List<RoomBean.DataBean> data = result.getData();
            roomBeans.addAll(data);
            roomAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, result.getErrmsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void MeRoomreturn(MeRoomBean result) {
        if (result.getErrno() == 0) {
            MeRoomBean.DataBean data = result.getData();
            Intent intent = new Intent(RoomActivity.this, PushActivity.class);
            intent.putExtra("id",data.getId());
            startActivity(intent);
        } else {
            Toast.makeText(this, result.getErrmsg(), Toast.LENGTH_SHORT).show();
        }
    }

}
