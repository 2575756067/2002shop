package com.live.ui;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.live.R;
import com.live.base.BaseActivity;
import com.live.model.bean.CreateRoomBean;
import com.live.model.bean.MeRoomBean;
import com.live.model.bean.UpdateRoomBean;
import com.live.presenter.CreateRoomPresenter;
import com.live.view.ICreateRoom;

import java.util.HashMap;
import java.util.Map;

public class CreateRoomActivity extends BaseActivity<ICreateRoom.Presenter> implements ICreateRoom.View, View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    ConstraintLayout create_toolbar;
    ImageView imgBack;
    TextView room;
    TextView succeed;

    //create
    TextView create_name;
    TextView create_icon;
    TextView create_psd;
    RadioGroup create_rg;
    RadioButton publicity;
    RadioButton privacy;
    Button create_btn_ok;
    ConstraintLayout create_con;
    private int Isopen;

    //update
    TextView update_name;
    TextView update_roomid;
    TextView update_icon;
    Button update_btn_ok;
    ConstraintLayout update_con;

    @Override
    protected int getLayout() {
        return R.layout.activity_create_room;
    }

    @Override
    protected ICreateRoom.Presenter createPersenter() {
        return new CreateRoomPresenter();
    }

    @Override
    protected void initView() {

        //toolbar
        create_toolbar = findViewById(R.id.create_toolbar);
        imgBack = create_toolbar.findViewById(R.id.img_back);
        room = create_toolbar.findViewById(R.id.tv_room);
        succeed = create_toolbar.findViewById(R.id.tv_succeed);

        //create
        create_name = findViewById(R.id.create_room_name);
        create_icon = findViewById(R.id.create_room_icon);
        create_rg = findViewById(R.id.create_rg);
        create_btn_ok = findViewById(R.id.create_btn_ok);
        create_con = findViewById(R.id.create_con);
        create_psd = findViewById(R.id.create_psd);
        publicity = findViewById(R.id.create_publicity);
        privacy = findViewById(R.id.create_privacy);

        //update
        update_name = findViewById(R.id.update_name);
        update_roomid = findViewById(R.id.update_roomid);
        update_icon = findViewById(R.id.update_icon);
        update_btn_ok = findViewById(R.id.update_btn_ok);
        update_con = findViewById(R.id.update_con);

        imgBack.setOnClickListener(this);
        create_btn_ok.setOnClickListener(this);
        update_btn_ok.setOnClickListener(this);
        create_rg.setOnCheckedChangeListener(this);

    }

    @Override
    protected void initData() {
        persenter.meroom();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.img_back) {
            finish();
        } else if (id == R.id.create_btn_ok) {
            initCreate();
        } else if (id == R.id.update_btn_ok) {
            initUpdate();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.create_publicity) {
            Isopen = 1;
        } else if(checkedId == R.id.create_privacy){
            Isopen = 2;
            create_psd.setVisibility(View.VISIBLE);
        }
    }

    private void initCreate() {
        String Name = create_name.getText().toString();
        String Icon = create_icon.getText().toString();
        String Psd = create_psd.getText().toString();

        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(Icon) && Isopen > 0) {
            if (!TextUtils.isEmpty(Psd)) {
                map.put("password", Psd);
            }
            map.put("room_name", Name);
            map.put("room_icon", Icon);
            map.put("isopen", String.valueOf(Isopen));
            persenter.create(map);
        } else {
            Toast.makeText(this, "房间id、房间背景图、选择房间公开/私密不可以为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void initUpdate() {
        String Name = update_name.getText().toString();
        String Roomid = update_roomid.getText().toString();
        String Icon = update_icon.getText().toString();

        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(Roomid)) {
            if (!TextUtils.isEmpty(Name)) {
                map.put("name", Name);
            }
            if (!TextUtils.isEmpty(Icon)) {
                map.put("icon", Icon);
            }
            map.put("roomid", Roomid);
            persenter.update(map);
        } else {
            Toast.makeText(this, "房间id不可以为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void Createreturn(CreateRoomBean result) {
        if (result.getErrno() == 0) {
            CreateRoomBean.DataBean data = result.getData();
            room.setVisibility(View.VISIBLE);
            succeed.setVisibility(View.VISIBLE);
            update_con.setVisibility(View.VISIBLE);
            create_con.setVisibility(View.GONE);
            update_roomid.setText(data.getId());
            succeed.setText("创建成功");
            room.setText(data.getName());
        } else {
            succeed.setVisibility(View.VISIBLE);
            succeed.setText("创建失败");
            Toast.makeText(this, result.getErrmsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void MeRoomturn(MeRoomBean result) {
        if (result.getErrno() == 0) {
            MeRoomBean.DataBean data = result.getData();
            succeed.setVisibility(View.GONE);
            room.setVisibility(View.VISIBLE);
            update_con.setVisibility(View.VISIBLE);
            create_con.setVisibility(View.GONE);
            room.setText(data.getName());
        } else {
            Toast.makeText(this, result.getErrmsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void Updatereturn(UpdateRoomBean result) {
        if (result.getErrno() == 0) {
            UpdateRoomBean.DataBean data = result.getData();
            room.setVisibility(View.VISIBLE);
            succeed.setVisibility(View.VISIBLE);
            update_con.setVisibility(View.VISIBLE);
            create_con.setVisibility(View.GONE);
            succeed.setText("修改成功");
            room.setText(data.getName());
        } else {
            succeed.setVisibility(View.VISIBLE);
            succeed.setText("修改失败");
            Toast.makeText(this, result.getErrmsg(), Toast.LENGTH_SHORT).show();
        }
    }

}