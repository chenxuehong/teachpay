package com.tencent.qcloud.tim.uikit.modules.conversation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.interfaces.IConversationAdapter;
import com.tencent.qcloud.tim.uikit.modules.conversation.interfaces.IConversationLayout;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

public class ConversationLayout extends RelativeLayout implements IConversationLayout {

    private TitleBarLayout mTitleBarLayout;
    private ConversationListLayout mConversationList;
    private TextView tvSearch;
    private ImageView ivContracts;
    private ImageView ivClass;
    private LinearLayout llItems;

    public ConversationLayout(Context context) {
        super(context);
        init();
    }

    public ConversationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ConversationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化相关UI元素
     */
    private void init() {
        inflate(getContext(), R.layout.conversation_layout, this);
        mTitleBarLayout = findViewById(R.id.conversation_title);
        mConversationList = findViewById(R.id.conversation_list);
        tvSearch = findViewById(R.id.conversation_tv_search);
        ivContracts = findViewById(R.id.conversation_iv_contracts);
        ivClass = findViewById(R.id.conversation_iv_class);
        llItems = findViewById(R.id.conversation_ll_items);
        tvSearch.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onListener!=null){
                            onListener.onSearchClicked(view);
                        }
                    }
                }
        );
        ivContracts.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onListener!=null){
                            onListener.onContractsClicked(view);
                        }
                    }
                }
        );
        ivClass.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onListener!=null){
                            onListener.onClassClicked(view);
                        }
                    }
                }
        );
    }

    public void initDefault() {
        mTitleBarLayout.setTitle(getResources().getString(R.string.conversation_title), TitleBarLayout.POSITION.MIDDLE);
        mTitleBarLayout.getLeftGroup().setVisibility(View.GONE);
        mTitleBarLayout.setRightIcon(R.drawable.conversation_more);
        mTitleBarLayout.getRightIcon().setVisibility(View.GONE);
        final IConversationAdapter adapter = new ConversationListAdapter();
        mConversationList.setAdapter(adapter);
        ConversationManagerKit.getInstance().loadConversation(new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                adapter.setDataProvider((ConversationProvider) data);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                ToastUtil.toastLongMessage(getContext().getString(R.string.load_msg_error));
            }
        });
    }

    public TitleBarLayout getTitleBar() {
        return mTitleBarLayout;
    }

    @Override
    public void setParentLayout(Object parent) {

    }

    public void showSearchView(boolean isShow){
        tvSearch.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    public void showConversationItems(boolean isShow){
        llItems.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    public void showClass(boolean isShow){
        ivClass.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    @Override
    public ConversationListLayout getConversationList() {
        return mConversationList;
    }

    public void addConversationInfo(int position, ConversationInfo info) {
        mConversationList.getAdapter().addItem(position, info);
    }

    public void removeConversationInfo(int position) {
        mConversationList.getAdapter().removeItem(position);
    }

    @Override
    public void setConversationTop(int position, ConversationInfo conversation) {
        mConversationList.closeMenu();
        ConversationManagerKit.getInstance().setConversationTop(position, conversation);
    }

    @Override
    public void deleteConversation(int position, ConversationInfo conversation) {
        mConversationList.closeMenu();
        ConversationManagerKit.getInstance().deleteConversation(position, conversation);
    }
    private OnListener onListener;
    public void setOnListener(OnListener onListener){
        this.onListener = onListener;
    }

    public interface OnListener{
        void onSearchClicked(View view);
        void onContractsClicked(View view);
        void onClassClicked(View view);
    }
}
