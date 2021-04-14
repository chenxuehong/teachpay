package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.view.View;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.MechanismUserEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.utils.glide.GlideTools;

public class CustomerManageRvAdapter extends EmptyRVAdapter<MechanismUserEntity> {
    private OnListener onListener;
    public CustomerManageRvAdapter(int layoutId, Context context,OnListener onListener) {
        super(layoutId, context);
        this.onListener = onListener;
    }

    @Override
    protected void convert(ViewHolder viewHolder, MechanismUserEntity mechanismUserEntity, int position) {

        MechanismUserEntity.Map map = mechanismUserEntity.getMap();
        if (map!=null){
            UserInfoEntity userinfo = map.getUserinfo();
            if (userinfo!=null){
                String nick_name = userinfo.getNick_name();
                String avatar = userinfo.getAvatar();
                String mobile = userinfo.getMobile();
                CircleImageView ivHead = viewHolder.getView(R.id.item_customer_manager_iv_head);
                viewHolder.setText(R.id.item_customer_manager_tv_name,nick_name);
                GlideTools.loadHeadImage(context,userinfo.getAvatar(),ivHead);
                viewHolder.setText(R.id.item_customer_manager_tv_phone,mobile);
            }
        }

        String status = mechanismUserEntity.getStatus();
        Boolean is_new = mechanismUserEntity.getIs_new();
        if ("1".equals(status)){
            viewHolder.setVisible(R.id.item_customer_manager_ll_setType,true);
            viewHolder.setVisible(R.id.item_customer_manager_tv_type,false);
            viewHolder.setOnClickListener(R.id.item_customer_manager_tv_newCustomer, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (onListener!=null){
                        onListener.setNewCustomer(viewHolder,mechanismUserEntity);
                    }
                }
            });
            viewHolder.setOnClickListener(R.id.item_customer_manager_tv_oldCustomer, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onListener!=null){
                        onListener.setOldCustomer(viewHolder,mechanismUserEntity);
                    }
                }
            });
        }else if (is_new!=null && is_new){
            viewHolder.setText(R.id.item_customer_manager_tv_type,"新客");
            viewHolder.setVisible(R.id.item_customer_manager_tv_type,true);
            viewHolder.setVisible(R.id.item_customer_manager_ll_setType,false);
        }else {
            viewHolder.setText(R.id.item_customer_manager_tv_type,"老客");
            viewHolder.setVisible(R.id.item_customer_manager_tv_type,true);
            viewHolder.setVisible(R.id.item_customer_manager_ll_setType,false);
        }
    }

    public interface OnListener{
        void setNewCustomer(ViewHolder viewHolder, MechanismUserEntity mechanismUserEntity);
        void setOldCustomer(ViewHolder viewHolder, MechanismUserEntity mechanismUserEntity);
    }
}
