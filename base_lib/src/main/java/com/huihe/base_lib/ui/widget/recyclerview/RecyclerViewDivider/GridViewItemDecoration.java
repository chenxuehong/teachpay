package com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewDivider;

import android.graphics.Canvas;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.huihe.base_lib.ui.adapter.PinnedHeaderAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.recyclerview.IPinnedHeaderDecoration;

import java.util.List;

/**
 * 为RecyclerView增加间距
 * 预设2列，如果是3列，则左右值不同
 */
public class GridViewItemDecoration extends RecyclerView.ItemDecoration implements IPinnedHeaderDecoration {
    private int space = 0;
    private int pos;
    private int column;
    private int reduceColumn;

    private int TYPE_1 = 100;
    private int TYPE_2 = 101;
    private int left;
    private int right;
    private int top;
    private int bottom;

    private int type;

    public GridViewItemDecoration(int column, int space, int reduceColumn) {
        this.column = column;
        this.space = space;
        this.reduceColumn = reduceColumn;
        type = TYPE_1;
    }

    public GridViewItemDecoration(int column, int left, int right, int top, int bottom, int reduceColumn) {
        this.column = column;
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.reduceColumn = reduceColumn;
        type = TYPE_2;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        pos = parent.getChildAdapterPosition(view);
        if (pos < reduceColumn) {
            return;
        }

        //该View在整个RecyclerView中位置。
        pos = pos - reduceColumn;

        if (type == TYPE_1) {
            outRect.top = space;
        } else {
            outRect.top = top;
        }


        //取模
        //  第一列
        if (pos % column == 0) {
            outRect.left = space;
            if (column == 1) {
                if (type == TYPE_1) {
                    outRect.right = space;
                } else {
                    outRect.right = right;
                }

            } else {
                if (type == TYPE_1) {
                    outRect.right = space / 2;
                } else {
                    outRect.right = right / 2;
                }
            }
        } else if (pos % column == column - 1) {
            //最后一列
            if (type == TYPE_1) {
                outRect.left = space / 2;
                outRect.right = space;
            } else {
                outRect.left = left / 2;
                outRect.right = right;
            }

        } else {
            // 中间部分
            if (type == TYPE_1) {
                outRect.left = space / 2;
                outRect.right = space / 2;
            } else {
                outRect.left = left / 2;
                outRect.right = right / 2;
            }

        }
    }

    private Rect mPinnedHeaderRect = null;
    private int mPinnedHeaderPosition = -1;

    /**
     * 把要固定的View绘制在上层
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //确保是PinnedHeaderAdapter的adapter,确保有View
        if (parent.getAdapter() instanceof PinnedHeaderAdapter && parent.getChildCount() > 0) {
            PinnedHeaderAdapter adapter = (PinnedHeaderAdapter) parent.getAdapter();
            //找到要固定的pin view
            View firstView = parent.getChildAt(0);
            int firstAdapterPosition = parent.getChildAdapterPosition(firstView);
            int pinnedHeaderPosition = getPinnedHeaderViewPosition(firstAdapterPosition, adapter);
            mPinnedHeaderPosition = pinnedHeaderPosition;
            if (pinnedHeaderPosition != -1) {
                List data = adapter.getData();
                if (data != null && data.size() > 0) {
                    ViewHolder pinnedHeaderViewHolder = adapter.onCreateViewHolder(parent,
                            pinnedHeaderPosition);
                    adapter.onBindViewHolder(pinnedHeaderViewHolder, pinnedHeaderPosition);
                    //要固定的view
                    View pinnedHeaderView = pinnedHeaderViewHolder.itemView;
                    adapter.hideView(pinnedHeaderView);
                    ensurePinnedHeaderViewLayout(pinnedHeaderView, parent);
                    int sectionPinOffset = 0;
                    for (int index = 0; index < parent.getChildCount(); index++) {
                        if (adapter.isPinnedPosition(parent.getChildAdapterPosition(parent.getChildAt(index)))) {
                            View sectionView = parent.getChildAt(index);
                            int sectionTop = sectionView.getTop();
                            int pinViewHeight = pinnedHeaderView.getHeight();
                            if (sectionTop < pinViewHeight && sectionTop > 0) {
                                sectionPinOffset = sectionTop - pinViewHeight;
                            }
                        }
                    }
                    int saveCount = c.save();
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) pinnedHeaderView.getLayoutParams();
                    if (layoutParams == null) {
                        throw new NullPointerException("PinnedHeaderItemDecoration");
                    }
                    c.translate(layoutParams.leftMargin, sectionPinOffset);
                    c.clipRect(0, 0, parent.getWidth(), pinnedHeaderView.getMeasuredHeight());
                    pinnedHeaderView.draw(c);
                    c.restoreToCount(saveCount);
                    if (mPinnedHeaderRect == null) {
                        mPinnedHeaderRect = new Rect();
                    }
                    mPinnedHeaderRect.set(0, 0, parent.getWidth(), pinnedHeaderView.getMeasuredHeight() + sectionPinOffset);
                }

            } else {
                mPinnedHeaderRect = null;
            }

        }
    }

    /**
     * 根据第一个可见的adapter的位置去获取临近的一个要固定的position的位置
     *
     * @param adapterFirstVisible 第一个可见的adapter的位置
     * @return -1：未找到 >=0 找到位置
     */
    private int getPinnedHeaderViewPosition(int adapterFirstVisible, PinnedHeaderAdapter adapter) {
        for (int index = adapterFirstVisible; index >= 0; index--) {
            if (adapter.isPinnedPosition(index)) {
                return index;
            }
        }
        return -1;
    }

    private void ensurePinnedHeaderViewLayout(View pinView, RecyclerView recyclerView) {
        if (pinView.isLayoutRequested()) {
            /**
             * 用的是RecyclerView的宽度测量，和RecyclerView的宽度一样
             */
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) pinView.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("PinnedHeaderItemDecoration");
            }
            int widthSpec = View.MeasureSpec.makeMeasureSpec(
                    recyclerView.getMeasuredWidth() - layoutParams.leftMargin - layoutParams.rightMargin, View.MeasureSpec.EXACTLY);

            int heightSpec;
            if (layoutParams.height > 0) {
                heightSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY);
            } else {
                heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            }
            pinView.measure(widthSpec, heightSpec);
            pinView.layout(0, 0, pinView.getMeasuredWidth(), pinView.getMeasuredHeight());
        }
    }

    @Override
    public Rect getPinnedHeaderRect() {
        return mPinnedHeaderRect;
    }

    @Override
    public int getPinnedHeaderPosition() {
        return mPinnedHeaderPosition;
    }
}