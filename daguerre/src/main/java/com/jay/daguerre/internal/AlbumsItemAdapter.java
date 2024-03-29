package com.jay.daguerre.internal;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;

import android.widget.ImageView;
import android.widget.TextView;

import com.jay.daguerre.R;
import com.jay.daguerre.provider.ImageLoader;

/**
 * Created by jay on 2017/11/29 上午10:18
 */
class AlbumsItemAdapter extends BaseRecyclerAdapter<Media.Album, AlbumsItemAdapter.ViewHolder> {


    private OnItemClickListener mOnItemClickListener;

    AlbumsItemAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.daguerre_albums_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Media.Album item = getItem(position);
        holder.mTextName.setText(item.name);
        holder.mTextCount.setText(mContext.getString(R.string.daguerre_albums_member_count, item.resourceCount));
        ImageLoader imageLoader = ConfigParams.getInstance().getImageLoader();
        if (imageLoader != null) {
            imageLoader.loadAlbumImage(mContext, holder.mImage, item.cover.data);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onAlbumsItemClick(v);
                }
            }
        });
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImage;
        private final TextView mTextName;
        private final TextView mTextCount;

        ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mTextName = itemView.findViewById(R.id.text_name);
            mTextCount = itemView.findViewById(R.id.text_count);
        }
    }

    interface OnItemClickListener {
        void onAlbumsItemClick(View itemView);
    }
}
