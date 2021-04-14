package com.huihe.base_lib.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

import com.huihe.base_lib.db.LanguageEntity;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static void copyTextToClipboard(Context context,final String text) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("playerId", text);
        clipboardManager.setPrimaryClip(clipData);
    }
    /**
     * 在指定集合中过滤出对应的集合
     *
     * @param list
     * @param newText
     * @return
     */
    public static List<String> getFilterData(List<String> list, String newText) {
        List<String> newlist = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                if (str.trim().toLowerCase().contains(newText.trim().toLowerCase())) {
                    newlist.add(str);
                }
            }
        }
        return newlist;
    }

    /**
     * 将LanguageEntity集合中数据转成多个字符串并用insertStr分隔
     *
     * @param context
     * @param languageArr
     * @param insertStr
     * @return
     */
    public static String languageList2String(Context context, String[] languageArr, String insertStr) {
        if (languageArr == null || languageArr.length == 0) {
            return "";
        }
        if (languageArr.length == 1 && TextUtils.isEmpty(languageArr[0])) {
            return "";
        }
        if (languageArr != null && languageArr.length > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < languageArr.length; i++) {

                if (MultiLanguageUtil.getInstance().isZh()) {
                    languageArr[i] = PListParserUtils.findChineseForEnglish(context, languageArr[i]);
                }
                stringBuffer.append(languageArr[i]);
                if (i != languageArr.length - 1) {
                    stringBuffer.append(insertStr);
                }
            }
            return stringBuffer.toString();
        } else {
            return null;
        }
    }

    public static String languageEnglishList2String(List<LanguageEntity> languageEntities, String insertStr) {
        if (languageEntities != null && languageEntities.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < languageEntities.size(); i++) {
                LanguageEntity languageEntity = languageEntities.get(i);
                stringBuffer.append(languageEntity.getCode());
                if (i != languageEntities.size() - 1) {
                    stringBuffer.append(insertStr);
                }
            }
            return stringBuffer.toString();
        } else {
            return null;
        }
    }

    /**
     * 将LanguageEntity集合中数据转成多个字符串并用insertStr分隔
     *
     * @param languageEntities
     * @param insertStr
     * @return
     */
    public static String languageList2String(List<LanguageEntity> languageEntities, String insertStr) {
        if (languageEntities != null && languageEntities.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < languageEntities.size(); i++) {
                LanguageEntity languageEntity = languageEntities.get(i);
                if (MultiLanguageUtil.getInstance().isZh()) {
                    stringBuffer.append(languageEntity.getValue());
                } else {
                    stringBuffer.append(languageEntity.getCode());
                }

                if (i != languageEntities.size() - 1) {
                    stringBuffer.append(insertStr);
                }
            }
            return stringBuffer.toString();
        } else {
            return null;
        }
    }

    public static String list2String(List<String> stringList, String insertStr) {
        if (stringList != null && stringList.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < stringList.size(); i++) {
                stringBuffer.append(stringList.get(i));
                if (i != stringList.size() - 1) {
                    stringBuffer.append(insertStr);
                }
            }
            return stringBuffer.toString();
        } else {
            return "";
        }
    }

    public static int getStringIndexFromArr(String[] englishArr, String desStr) {
        if (englishArr == null || englishArr.length == 0 || TextUtils.isEmpty(desStr)) {
            return -1;
        }
        for (int i = 0; i < englishArr.length; i++) {
            if (desStr.equals(englishArr[i])) {

                return i;
            }
        }
        return -1;
    }

    public static String appendHeadStr(String content, String insertStr) {

        if (TextUtils.isEmpty(content)){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(insertStr).append(content);
        return stringBuilder.toString().trim();
    }

    public static String noIsNull(String score) {
        if (TextUtils.isEmpty(score)){
            return  "";
        }
        return score;
    }
}
