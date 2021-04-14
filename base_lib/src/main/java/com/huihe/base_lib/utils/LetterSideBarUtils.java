package com.huihe.base_lib.utils;

import com.huihe.base_lib.db.LanguageEntity;
import com.huihe.base_lib.utils.pinyin.PinYinUtil;

import java.util.List;

public class LetterSideBarUtils {
    public static int getIndexInList(List<LanguageEntity> languageEntityList, String letter) {
        if (languageEntityList != null) {
            for (int i = 0; i < languageEntityList.size(); i++) {
                LanguageEntity languageEntity = languageEntityList.get(i);
                if (MultiLanguageUtil.getInstance().isZh()) {
                    String firstSpell = PinYinUtil.getFirstSpell(languageEntity.getValue());
                    firstSpell = String.valueOf(firstSpell.charAt(0)).toUpperCase();
                    if (firstSpell.equals(letter)) {
                        return i;
                    }
                } else {
                    String firstSpell = String.valueOf(languageEntity.getCode().charAt(0)).toUpperCase();
                    if (firstSpell.equals(letter)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public static int getIndexInList2(List<String> stringList, String letter) {
        if (stringList != null) {
            for (int i = 0; i < stringList.size(); i++) {
                String s = stringList.get(i);
                if (MultiLanguageUtil.getInstance().isZh()) {
                    String firstSpell = PinYinUtil.getFirstSpell(s);
                    firstSpell = String.valueOf(firstSpell.charAt(0)).toUpperCase();
                    if (firstSpell.equals(letter)) {
                        return i;
                    }
                } else {
                    String firstSpell = String.valueOf(s.charAt(0)).toUpperCase();
                    if (firstSpell.equals(letter)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
