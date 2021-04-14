package com.eghuihe.module_dynamic.ui.activity;

import android.view.View;

import com.eghuihe.module_dynamic.R;
import com.eghuihe.module_dynamic.R2;
import com.huihe.base_lib.ui.activity.BaseAnimActivity;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import butterknife.BindView;
import butterknife.OnClick;

public class PlayerActivity extends BaseAnimActivity {
    public static final String PLAY_PATH = "url";
    public static final String KEY_TITLE = "title";

    @BindView(R2.id.nice_video_player)
    NiceVideoPlayer videoView;

    @OnClick(R2.id.nice_video_player_close)
    public void onViewClicked(View view) {
        if (view.getId() == R.id.nice_video_player_close) {
            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live_player;
    }

    @Override
    protected void initView() {
        super.initView();
        String path = getIntent().getStringExtra(PLAY_PATH);
        String title = getIntent().getStringExtra(KEY_TITLE);
        videoView.setPlayerType(NiceVideoPlayer.TYPE_NATIVE);
        videoView.setUp(path, null);
        videoView.continueFromLastPosition(true);
        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle(title);
        GlideTools.loadImage(this, path, controller.imageView());
        videoView.setController(controller);
        videoView.start();

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 在onStop时释放掉播放器
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }
}
