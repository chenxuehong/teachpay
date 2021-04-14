package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RvSyllabusAdapter extends CommonRVAdapter<String> {
    Map<Integer, String> map;
    private String[] split;

    public Map<Integer, String> getTitleMap() {
        return map;
    }

    public RvSyllabusAdapter(int layoutId, Context context, List<String> data) {
        super(layoutId, context, data);
        map = new HashMap<>();
    }

    @Override
    protected void covert(ViewHolder viewHolder, String data, final int position) {

        viewHolder.setText(R.id.item_addsalecourse_title_tv_title, data);
        EditText etTitle = viewHolder.getView(R.id.item_addsalecourse_title_et_title);
        if (split != null && position < split.length) {
            map.put(position, split[position]);
            etTitle.setText(split[position]);
        }
        etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (map != null) {
                    if (!TextUtils.isEmpty(charSequence.toString())) {
                        map.put(position, charSequence.toString());
                    } else {
                        map.remove(position);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setTitleUrl(String titile_url) {
        if (!TextUtils.isEmpty(titile_url)) {
            split = titile_url.split("#\\$\\*");
            notifyDataSetChanged();
        }
    }
}
