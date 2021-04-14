package com.eghuihe.module_dynamic.ui.adapter;

import android.content.Context;

import com.eghuihe.module_dynamic.R;
import com.huihe.base_lib.model.NoteCommentEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.List;

public class NoteCommentRvAdapter extends CommonRVAdapter<NoteCommentEntity> {

    public NoteCommentRvAdapter(int layoutId, Context context, List<NoteCommentEntity> data) {
        super(layoutId, context, data);
    }

    @Override
    protected void covert(ViewHolder viewHolder, NoteCommentEntity noteCommentEntity, int position) {
        NoteCommentEntity.MapBean map = noteCommentEntity.getMap();
        if (map != null) {
            UserInfoEntity userinfo = map.getUserinfo();
            UserInfoEntity replyinfo = map.getReplyinfo();
            if (noteCommentEntity.isIs_reply()) {
                viewHolder.setVisible(R.id.item_note_comment_tv_UserNick2, true);
                viewHolder.setVisible(R.id.item_note_comment_tv_huifu, true);
                viewHolder.setVisible(R.id.item_note_comment_tv_replyinfoNick, true);
                viewHolder.setVisible(R.id.item_note_comment_tv_reply_content, true);
                viewHolder.setVisible(R.id.item_note_comment_tv_UserNick, false);
                viewHolder.setVisible(R.id.item_note_comment_tv_content, false);
                viewHolder.setText(R.id.item_note_comment_tv_UserNick2,
                        userinfo.getNick_name());
                viewHolder.setText(R.id.item_note_comment_tv_replyinfoNick,
                        replyinfo.getNick_name().concat(":"));
                viewHolder.setText(R.id.item_note_comment_tv_reply_content,
                        noteCommentEntity.getContent());
            } else {
                viewHolder.setText(R.id.item_note_comment_tv_UserNick,
                        userinfo.getNick_name().concat(":"));
                viewHolder.setText(R.id.item_note_comment_tv_content,
                        noteCommentEntity.getContent());
            }
        }
    }
}
