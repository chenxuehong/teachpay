package com.huihe.base_lib.ui.widget.popup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.huihe.base_lib.R;

import java.util.ArrayList;
import java.util.List;

public class CustomPopMenuAdapter extends BaseAdapter {

    private List<PopupMenuItem> dataSource = new ArrayList<>();
    private Context context;

    public CustomPopMenuAdapter(Context context, List<PopupMenuItem> dataSource) {
        this.dataSource = dataSource;
        this.context = context;
    }

    public void setDataSource(final List<PopupMenuItem> datas) {
        dataSource = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.custom_pop_menu_item, null);
            holder = new ViewHolder();
            holder.menu_icon = convertView.findViewById(R.id.pop_menu_icon);
            holder.menu_lable = convertView.findViewById(R.id.pop_menu_label);
            convertView.setTag(holder);
        } else {// 有直接获得ViewHolder
            holder = (ViewHolder) convertView.getTag();
        }
        PopupMenuItem item = (PopupMenuItem) getItem(position);
        holder.menu_icon.setVisibility(View.VISIBLE);
        if(item.imgRes <= 0){
            holder.menu_icon.setVisibility(View.GONE);
        }else{
            holder.menu_icon.setImageResource(item.imgRes);
            holder.menu_icon.setVisibility(View.VISIBLE);
        }
        holder.menu_lable.setText(item.content);
        return convertView;
    }

    static class ViewHolder {
        TextView menu_lable;
        ImageView menu_icon;
    }
}
