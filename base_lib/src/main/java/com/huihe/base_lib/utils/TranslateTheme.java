package com.huihe.base_lib.utils;

import android.content.Context;

import com.huihe.base_lib.R;

import java.util.ArrayList;
import java.util.List;

public class TranslateTheme {
    private String[] split;
    private String type;
    private String content;
    private Context context;
    private List<String> sceneList = new ArrayList<>();

    public TranslateTheme(Context context, String[] split) {
        this.context = context;
        this.split = split;
        sceneList.clear();
        sceneList.add("Scene");
        sceneList.add("场景");
        sceneList.add("场景直播");
        sceneList.add("Scene broadcast");
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public TranslateTheme invoke() {
        if (MultiLanguageUtil.getInstance().isZh()) {
            if (sceneList.contains(split[0])) {
                type = context.getResources().getString(R.string.scene);
                int themeSceneIndex = DataLoader.getInstance().getThemeSceneIndex(split[1]);
                if (themeSceneIndex != -1) {
                    String[] themeSceneicChineseArr = DataLoader.getInstance().getThemeSceneicChineseArr();
                    content = themeSceneicChineseArr[themeSceneIndex];
                }else {
                    content = split[1];
                }

            } else {
                type = context.getResources().getString(R.string.topic_exchange);
                int themeTopicIndex = DataLoader.getInstance().getThemeTopicIndex(split[1]);
                if (themeTopicIndex != -1) {
                    String[] themeTopicChineseArr = DataLoader.getInstance().getThemeTopicChineseArr();
                    content = themeTopicChineseArr[themeTopicIndex];
                }else {
                    content = split[1];
                }

            }
        }else {
            if (sceneList.contains(split[0])) {
                type = context.getResources().getString(R.string.scene);
                int themeSceneIndex = DataLoader.getInstance().getThemeSceneIndex(split[1]);
                if (themeSceneIndex != -1) {
                    String[] themeSceneicChineseArr = DataLoader.getInstance().getThemeSceneicEngArr();
                    content = themeSceneicChineseArr[themeSceneIndex];
                }else {
                    content = split[1];
                }

            } else {
                type = context.getResources().getString(R.string.topic_exchange);
                int themeTopicIndex = DataLoader.getInstance().getThemeTopicIndex(split[1]);
                if (themeTopicIndex != -1) {
                    String[] themeTopicChineseArr = DataLoader.getInstance().getThemeTopicEngArr();
                    content = themeTopicChineseArr[themeTopicIndex];
                }else {
                    content = split[1];
                }

            }
        }
        return this;
    }
}