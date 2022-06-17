package com.jay.daguerre.internal;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.jay.daguerre.R;
import com.jay.daguerre.utils.Utils;


public class FolderPopupWindow extends PopupWindow {

    private static final int DEFAULT_IMAGE_FOLDER_SELECT = 0;//默认选中文件夹
    private Context mContext;
    private RecyclerView mRecyclerView;
    private AlbumsItemAdapter mAlbumsItemAdapter;

    public FolderPopupWindow(Context context) {
        this.mContext = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.window_image_folders, null);
        mRecyclerView = view.findViewById(R.id.rv_main_imageFolders);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayout.HORIZONTAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mAlbumsItemAdapter = new AlbumsItemAdapter(mContext);
        mRecyclerView.setAdapter(mAlbumsItemAdapter);
        initPopupWindow(view);
    }

    /**
     * 初始化PopupWindow的一些属性
     */
    private void initPopupWindow(View view) {
        setContentView(view);
        int[] screenSize = Utils.getScreenSize(mContext);
        setWidth(screenSize[0]);
        setHeight((int) (screenSize[1] * 0.6));
        setBackgroundDrawable(new ColorDrawable());
        setOutsideTouchable(true);
        setFocusable(true);
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                }
                return false;
            }
        });
    }

    public AlbumsItemAdapter getAdapter() {
        return mAlbumsItemAdapter;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
