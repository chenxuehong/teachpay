package com.huihe.base_lib.utils;

import android.text.TextUtils;

public class DataLoader {

    private static DataLoader mDataLoader;

    public static DataLoader getInstance() {

        if (mDataLoader == null) {
            synchronized (DateUtils.class) {
                if (mDataLoader == null) {

                    mDataLoader = new DataLoader();
                }
            }
        }
        return mDataLoader;
    }

    String[] themeTopicChineseArr = {
            "自我介绍",
            "学习教育",
            "聊聊音乐",
            "聊聊家人",
            "聊聊穿着",
            "聊聊朋友",
            "聊聊美食",
            "节日和习俗",
            "聊聊综艺节目",
            "聊聊近期计划",
            "聊聊爱好",
            "聊聊学习",
            "聊聊电视剧",
            "聊聊电影",
            "聊聊工作",
            "怎么学好这个语言",
            "关于旅游",
            "聊聊地理",
            "谈谈理想",
            "聊聊爱情",
            "聊聊天气",
            "聊一些趣事",
            "动物",
            "植物",
            "颜色",
            "问候语",
            "形状",
            "身体部位",
            "交通工具",
            "运动",
            "情绪",
            "水果",
            "时间",
            "汽车",
            "日常",
            "农场动物",
            "玩具",
            "万圣节",
            "圣诞节",
            "恐龙",
            "食物",
            "房子",
            "宠物",
            "天气"
    };

    String[] themeSceneChineseArr = {
            "餐馆",
            "邮局",
            "医院及健康场景",
            "工作和上班场景",
            "酒店",
            "商场购物",
            "公共交通",
            "机场",
            "农场",
            "校园",
            "旅行",
            "超市",
            "驾驶",
            "婚礼",
            "电影院",
            "娱乐场所",
            "体育馆",
            "博物馆",
            "酒吧或KTV"
    };

    String[] themeTopicEngArr = {
            "Self introduction",
            "Education",
            "Music",
            "Family Member",
            "Wearings",
            "Friends",
            "Food",
            "Festival and Customs",
            "TV show",
            "Recent Planning",
            "Hobbies",
            "Talk about learning",
            "Drama",
            "Movie",
            "Job",
            "How to master a foreign language",
            "Travelling",
            "Geography",
            "Dream",
            "Love",
            "Weather",
            "Funny and interesting things",
            "Animals",
            "Plants",
            "Colors",
            "Greetings",
            "Shapes",
            "Body parts",
            "Vehicles",
            "Sports",
            "Emotions",
            "Fruit",
            "Time",
            "Car",
            "Daily routine",
            "Farm animals",
            "Toys",
            "Halloween",
            "Christmas",
            "Dinosaurs",
            "Food",
            "House",
            "Pets",
            "Weather"

    };

    String[] themeSceneEngArr = {
            "Restaurant",
            "Post Office",
            "Hospital and health scene",
            "Job, work area",
            "Hotel",
            "Shopping mall",
            "Public transportation",
            "Airport",
            "Farm",
            "Campus",
            "Travel",
            "Supermarket",
            "Drive",
            "Wedding",
            "Cinema",
            "Entertainment Venues",
            "GYM",
            "Museum",
            "Bar or KTV"
    };

    public String[] getThemeTopicChineseArr() {
        return themeTopicChineseArr;
    }

    public String[] getThemeTopicEngArr() {
        return themeTopicEngArr;
    }

    public String[] getThemeSceneicArr() {
        if (MultiLanguageUtil.getInstance().isZh()) {
            return themeSceneChineseArr;
        } else {
            return themeSceneEngArr;
        }
    }

    public String[] getThemeSceneicChineseArr() {
        return themeSceneChineseArr;
    }

    public String[] getThemeSceneicEngArr() {
        return themeSceneEngArr;
    }

    public int getThemeTopicIndex(String content) {

        if (FormatUtil.checkHasChese(content)) {
            for (int i = 0; i < themeTopicChineseArr.length; i++) {

                if (themeTopicChineseArr[i].equals(content)) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < themeTopicEngArr.length; i++) {
                if (themeTopicEngArr[i].equals(content)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int getThemeSceneIndex(String content) {

        if (FormatUtil.checkHasChese(content)) {
            for (int i = 0; i < themeSceneChineseArr.length; i++) {

                if (themeSceneChineseArr[i].equals(content)) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < themeSceneEngArr.length; i++) {
                if (themeSceneEngArr[i].equals(content)) {
                    return i;
                }
            }
        }
        return -1;
    }

    String[] sexArr = new String[]{
            "男",
            "女"
    };
    String[] sexEngArr = new String[]{
            "Male",
            "Female"
    };
    String[] relationshipArr = new String[]{
            "父子",
            "母子",
            "其他"
    };
    String[] syllabusArr = new String[]{
            "默认大纲",
            "自定义大纲",
            "阶段性大纲"
    };

    public String[] getSyllabusArr() {
        return syllabusArr;
    }

    public String[] getRelationshipArr() {
        return relationshipArr;
    }

    public String[] getSexArr() {
        if (MultiLanguageUtil.getInstance().isZh()) {
            return sexArr;
        } else {
            return sexEngArr;
        }
    }

    public String getCooperation(String zhCooperation, String enCooperation) {

        if (MultiLanguageUtil.getInstance().isZh()) {
            return zhCooperation;
        } else {
            return enCooperation;
        }
    }


    private String[] storeTypeArr = {
            "才艺",
            "体育运动",
            "教辅学科",
            "益智",
            "其他"
    };

    public String[] getStoreTypeArr() {
        return storeTypeArr;
    }

    public String[] getCategoriesChildArr(String store_type) {
        if (TextUtils.isEmpty(store_type)) {
            return null;
        }
        String[] arr = null;
        switch (store_type) {
            case "才艺":
                arr = new String[]{
                        "口才",
                        "乐器",
                        "歌唱",
                        "美术",
                        "书法",
                        "表演",
                        "舞蹈"
                };
                break;
            case "体育运动":
                arr = new String[]{
                        "游泳",
                        "体适能",
                        "乒乓球",
                        "羽毛球",
                        "篮球",
                        "足球",
                        "轮滑",
                        "高尔夫"
                };
                break;
            case "益智":
                arr = new String[]{
                        "棋类",
                        "乐高",
                        "编程",

                };
                break;
            case "教辅学科":
                arr = new String[]{
                        "语文",
                        "数学",
                        "英文",
                        "日语",
                        "考研",
                        "出国留学"

                };
                break;
        }
        return arr;
    }

    public String[] typeArr = {
            "Scenery",
            "Custom",
            "Funny",
            "Delicacy",
            "Education",
            "Other"
    };

    public String[] typeChineseArr = {
            "风景",
            "习俗",
            "搞笑",
            "美食",
            "学历",
            "其他"
    };

    public int getClassFyIndex(String classfy) {
        for (int i = 0; i < typeArr.length; i++) {
            if (typeArr[i].equals(classfy)) {
                return i;
            }
        }
        return -1;
    }

    public String getChineseClassFy(int index) {

        if (index == -1) {
            return "其他";
        }
        return typeChineseArr[index];
    }
}
