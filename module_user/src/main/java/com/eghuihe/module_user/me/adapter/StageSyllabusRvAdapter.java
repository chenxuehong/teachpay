package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.bean.StageSyllabusBean;
import com.eghuihe.module_user.bean.params.StageSyllabusParam;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StageSyllabusRvAdapter extends CommonRVAdapter<StageSyllabusBean> {

    private EditText endestEtEndIndex;
    private EditText endestEtTitle;
    Map<Integer, StageSyllabusParam> map;
    private OnListener onListener;
    private String endestStartIndex;

    public Map<Integer, StageSyllabusParam> getMap() {
        return map;
    }

    public void setMap(Map<Integer, StageSyllabusParam> map) {
        this.map = map;
    }

    public StageSyllabusRvAdapter(int layoutId, Context context, List<StageSyllabusBean> data, OnListener onListener) {
        super(layoutId, context, data);
        this.onListener = onListener;
        map = new HashMap<>();
    }

    @Override
    protected void covert(ViewHolder viewHolder, StageSyllabusBean stageSyllabusBean, final int position) {
        viewHolder.setText(R.id.item_stage_syllabus_tv_startIndex, stageSyllabusBean.startIndex);
        EditText etEndIndex = viewHolder.getView(R.id.item_stage_syllabus_et_endIndex);
        EditText etTitle = viewHolder.getView(R.id.item_stage_syllabus_et_title);
        StageSyllabusParam stageSyllabusParam = map.get(position);
        if (stageSyllabusParam != null) {
            String endIndex = stageSyllabusParam.endIndex;
            String title = stageSyllabusParam.title;
            etEndIndex.setText(endIndex);
            etTitle.setText(title);
        }
        etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (map != null) {
                    if (!TextUtils.isEmpty(editable.toString())) {
                        StageSyllabusParam stageSyllabusParam = map.get(position);
                        if (stageSyllabusParam == null) {
                            stageSyllabusParam = new StageSyllabusParam(stageSyllabusBean.startIndex, "", editable.toString());
                        } else {
                            stageSyllabusParam.setStartIndex(stageSyllabusBean.startIndex);
                            stageSyllabusParam.setTitle(editable.toString());
                        }
                        map.put(position, stageSyllabusParam);
                    }
                }
            }
        });
        etEndIndex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString())) {
                    Integer endIndex = Integer.valueOf(editable.toString().trim());
                    int totalNum = Integer.valueOf(stageSyllabusBean.totalNum);
                    if (endIndex > totalNum) {
                        ToastUtils.showShortToast(context, "结束数不能大于总节数");
                        etEndIndex.setText("");
                        return;
                    }

                    if (map != null) {
                        if (!TextUtils.isEmpty(editable.toString())) {
                            StageSyllabusParam stageSyllabusParam = map.get(position);
                            if (stageSyllabusParam == null) {
                                stageSyllabusParam = new StageSyllabusParam(stageSyllabusBean.startIndex, editable.toString(), "");
                            } else {
                                stageSyllabusParam.setStartIndex(stageSyllabusBean.startIndex);
                                stageSyllabusParam.setEndIndex(editable.toString());
                            }
                            map.put(position, stageSyllabusParam);
                        }
                    }
                    if (endIndex == totalNum) {
                        // 添加最后一个阶段
                        if (onListener != null) {
                            onListener.prohibitOrAllowAdd(false);
                        }
                    } else {
                        if (onListener != null) {
                            onListener.prohibitOrAllowAdd(true);
                        }
                    }
                }

            }
        });
        if (position == getItemCount() - 1) {
            endestEtEndIndex = etEndIndex;
            endestEtTitle = etTitle;
            endestStartIndex = stageSyllabusBean.startIndex;
        }
    }

    public boolean checkNextInput() {
        List<StageSyllabusBean> stageSyllabusBeans = getData();
        if (endestEtEndIndex != null && stageSyllabusBeans != null && stageSyllabusBeans.size() > 0) {
            StageSyllabusBean stageSyllabusBean = stageSyllabusBeans.get(0);
            if (TextUtils.isEmpty(endestEtEndIndex.getText().toString().trim())) {
                ToastUtils.showShortToast(context, "请输入结束数");
                return false;
            }
            int totalNum = Integer.valueOf(stageSyllabusBean.totalNum);
            int endIndex = Integer.valueOf(endestEtEndIndex.getText().toString().trim());
            if (endIndex > totalNum) {
                ToastUtils.showShortToast(context, "不能超过课节数".concat(String.valueOf(endIndex).concat("节")));
                return false;
            }
            int startIndex = Integer.valueOf(endestStartIndex);
            if (endIndex < startIndex) {
                ToastUtils.showShortToast(context, "结束数不能小于开始数");
                return false;
            }

            if (TextUtils.isEmpty(endestEtTitle.getText().toString().trim())) {
                ToastUtils.showShortToast(context, "请输入标题");
                return false;
            }
            return true;
        }
        ToastUtils.showShortToast(context, "请将该课节信息填写完整");
        return false;
    }

    public boolean checkInput() {

        if (!checkNextInput()) {
            return false;
        }
        List<StageSyllabusBean> stageSyllabusBeans = getData();
        if (endestEtEndIndex != null && stageSyllabusBeans != null && stageSyllabusBeans.size() > 0) {
            StageSyllabusBean stageSyllabusBean = stageSyllabusBeans.get(0);
            int totalNum = Integer.valueOf(stageSyllabusBean.totalNum);
            int endIndex = Integer.valueOf(endestEtEndIndex.getText().toString().trim());
            if (endIndex < totalNum) {
                ToastUtils.showShortToast(context, "请将大纲填写完整");
                return false;
            }
        }
        return true;
    }

    public String getCurStartIndex() {
        if (endestEtEndIndex != null) {
            int endIndex = Integer.valueOf(endestEtEndIndex.getText().toString().trim());
            return String.valueOf(endIndex + 1);
        }
        return "";
    }

    public interface OnListener {
        // 禁止或允许添加新区间
        void prohibitOrAllowAdd(boolean isAllow);
    }
}
