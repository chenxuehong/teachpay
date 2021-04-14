package com.huihe.base_lib.utils;

import android.content.Context;
import android.text.TextUtils;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListFormatException;
import com.dd.plist.PropertyListParser;
import com.huihe.base_lib.db.LanguageEntity;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class PListParserUtils {

    // 语言分隔线
    public static final String SEPARATOR_LINE = "/";

    /**
     * 从plixt文件中找到中文语言字符串对应的英文语言字符串
     *
     * @param context
     * @param chineseForimatStr
     * @return
     */
    public static String findEnglishForChinese(Context context, String chineseForimatStr) {
        if (TextUtils.isEmpty(chineseForimatStr) || context == null) {
            return "";
        }
        try {
            NSArray nsArray = (NSArray) PropertyListParser.parse(context.getAssets().open("Language.plist"));

            for (int i = 0; i < nsArray.count(); i++) {

                String chinese = ((NSDictionary) nsArray.objectAtIndex(i)).objectForKey("value").toJavaObject().toString();
                if (chineseForimatStr.equals(chinese)) {
                    String english = ((NSDictionary) nsArray.objectAtIndex(i)).objectForKey("code").toJavaObject().toString();

                    return english;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyListFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String findChineseForEnglish(Context context, String englishForimatStr) {
        if (TextUtils.isEmpty(englishForimatStr) || context == null) {
            return englishForimatStr;
        }
        try {
            NSArray nsArray = (NSArray) PropertyListParser.parse(context.getAssets().open("Language.plist"));

            for (int i = 0; i < nsArray.count(); i++) {

                String english = ((NSDictionary) nsArray.objectAtIndex(i)).objectForKey("code").toJavaObject().toString();
                if (englishForimatStr.equals(english)) {
                    String chinese = ((NSDictionary) nsArray.objectAtIndex(i)).objectForKey("value").toJavaObject().toString();

                    return chinese;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyListFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return englishForimatStr;
    }

    public static String getJianXie(Context context, String targetLanguage) {
        try {
            NSArray nsArray = (NSArray) PropertyListParser.parse(context.getAssets().open("Language.plist"));

            for (int i = 0; i < nsArray.count(); i++) {

                String englishLang = ((NSDictionary) nsArray.objectAtIndex(i)).objectForKey("code").toJavaObject().toString();
                if (targetLanguage.equals(englishLang)) {
                    String key = ((NSDictionary) nsArray.objectAtIndex(i)).objectForKey("key").toJavaObject().toString();

                    return key;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyListFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getChineseList(Context context) {
        List<String> languages = new ArrayList<>();
        try {
            NSArray nsArray = (NSArray) PropertyListParser.parse(context.getAssets().open("Language.plist"));
            for (int i = 0; i < nsArray.count(); i++) {

                String language = ((NSDictionary) nsArray.objectAtIndex(i)).objectForKey("value").toJavaObject().toString();
                languages.add(language);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyListFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return languages;
    }

    public static List<LanguageEntity> getLanguageEntityList(Context context) {
        List<LanguageEntity> languageEntities = new ArrayList<>();
        try {
            NSArray nsArray = (NSArray) PropertyListParser.parse(context.getAssets().open("Language.plist"));
            for (int i = 0; i < nsArray.count(); i++) {

                String code = ((NSDictionary) nsArray.objectAtIndex(i)).objectForKey("code").toJavaObject().toString();
                String value = ((NSDictionary) nsArray.objectAtIndex(i)).objectForKey("value").toJavaObject().toString();
                String key = ((NSDictionary) nsArray.objectAtIndex(i)).objectForKey("key").toJavaObject().toString();
                LanguageEntity languageEntity = new LanguageEntity(code, key, value);
                languageEntities.add(languageEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyListFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return languageEntities;
    }

    public static List<String> findEnglishListForChineseList(Context context, List<String> languageList) {
        List<String> englishList = new ArrayList<>();
        if (languageList != null && languageList.size() > 0) {

            for (int i = 0; i < languageList.size(); i++) {
                String Language = languageList.get(i);
                String english = findEnglishForChinese(context, Language);
                englishList.add(english);
            }
        }
        return englishList;
    }
}
