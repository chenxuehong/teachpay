package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.utils.TextUtils;
import com.huihe.base_lib.model.LiveDetailEntity;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.utils.JsonUtil;
import com.tencent.qcloud.tim.tuikit.live.R;
import com.tencent.qcloud.tim.tuikit.live.modules.liveroom.TUILiveRoomAudienceLayout;

public class DefaultGroupLiveAudienceActivity extends AppCompatActivity {
    private static final String TAG = "DefaultGroupLiveAudienceActivity";

    private TUILiveRoomAudienceLayout mTUILiveRoomAudienceLayout;

    public static final String KEY_DATA = "data";
    private LiveDetailEntity liveDetailEntity;

    public static void start(Context context, String data) {
        Intent starter = new Intent(context, DefaultGroupLiveAudienceActivity.class);
        starter.putExtra(KEY_DATA, data);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            String json = intent.getStringExtra(KEY_DATA);
            liveDetailEntity = JsonUtil.fromJson(json, LiveDetailEntity.class);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setTurnScreenOn(true);
        } else {
        }
        setContentView(R.layout.live_activity_default_gourp_live_audience);

        initLiveRoomAudienceLayout();
        initLiveRoomAudienceLayoutDelegate();
    }

    private void initLiveRoomAudienceLayout() {
        mTUILiveRoomAudienceLayout = findViewById(R.id.layout_room_audience);
        String room_id = liveDetailEntity.getRoom_id();
        LiveDetailEntity.Map map = liveDetailEntity.getMap();
        int roomId = TextUtils.isEmpty(room_id) ? 0 : Integer.valueOf(room_id);
        String anchorId = "";
        String cdnURL = "";
        String mechanism_id = "";
        if (map != null) {
            MasterMechanismModel.MasterMechanismEntity masterMechanismEntity = map.getMasterMechanismEntity();
            if (masterMechanismEntity != null) {
                String user_id = masterMechanismEntity.getUser_id();
                anchorId = user_id;
                mechanism_id = masterMechanismEntity.getId();
            }
        }
        cdnURL = "http://live.curiousmore.com/live/1400491548_".concat(mechanism_id).concat("_").concat(anchorId).concat("_main.flv");
        mTUILiveRoomAudienceLayout.initWithRoomId(getSupportFragmentManager(), roomId, anchorId, true, cdnURL,JsonUtil.toJson(liveDetailEntity));
    }

    private void initLiveRoomAudienceLayoutDelegate() {
        mTUILiveRoomAudienceLayout.setLiveRoomAudienceDelegate(new TUILiveRoomAudienceLayout.TUILiveRoomAudienceDelegate() {
            @Override
            public void onClose() {
                finish();
            }

            @Override
            public void onError(int errorCode, String message) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mTUILiveRoomAudienceLayout.onBackPressed();
    }
}