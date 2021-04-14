package com.huihe.base_lib.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteDBDao {

    private final MySQLiteOpenHelper liteOpenHelper;
    private static MySQLiteDBDao intance;

    public static MySQLiteDBDao getInstance(Context context) {
        if (intance == null) {
            synchronized (MySQLiteDBDao.class) {
                if (intance == null) {
                    intance = new MySQLiteDBDao(context);
                }
            }
        }
        return intance;
    }

    private MySQLiteDBDao(Context context) {
        liteOpenHelper = new MySQLiteOpenHelper(context);
    }

    public void insert(String tableName, LanguageEntity languageEntity) {
        if (TextUtils.isEmpty(tableName) || languageEntity == null) {
            return;
        }
        List<LanguageEntity> languageEntities = queryAll(tableName);
        if (languageEntities != null) {
            for (int i = 0; i < languageEntities.size(); i++) {
                LanguageEntity languageEntity1 = languageEntities.get(i);
                if (languageEntity1.getCode().equals(languageEntity.getCode())) {
                    return;
                }
            }
        }
        if (liteOpenHelper != null) {
            try {
                SQLiteDatabase writableDatabase = liteOpenHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(MySQLiteOpenHelper.table.LanguageTable.Column._KEY, languageEntity.getKey());
                contentValues.put(MySQLiteOpenHelper.table.LanguageTable.Column._CODE, languageEntity.getCode());
                contentValues.put(MySQLiteOpenHelper.table.LanguageTable.Column._VALUE, languageEntity.getValue());
                writableDatabase.insert(tableName, null, contentValues);
                writableDatabase.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(String tableName, String whereClause, String value) {
        if (liteOpenHelper != null) {
            try {
                SQLiteDatabase writableDatabase = liteOpenHelper.getWritableDatabase();
                writableDatabase.delete(tableName, whereClause + "=?", new String[]{value});
                writableDatabase.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<LanguageEntity> queryAll(String tableName) {
        if (liteOpenHelper != null) {
            List<LanguageEntity> languageEntities = new ArrayList<>();
            try {
                SQLiteDatabase readableDatabase = liteOpenHelper.getReadableDatabase();
                Cursor cursor = readableDatabase.rawQuery("select * from " + tableName, null);
                while (cursor.moveToNext()) {
                    String key = cursor.getString(1);
                    String code = cursor.getString(2);
                    String value = cursor.getString(3);
                    LanguageEntity languageEntity = new LanguageEntity(code, key, value);
                    languageEntities.add(languageEntity);
                }
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return languageEntities;
        }
        return null;
    }
    public void insertMechanismHistory(String tableName, String history) {
        if (TextUtils.isEmpty(tableName) || TextUtils.isEmpty(history)) {
            return;
        }
        if (liteOpenHelper != null) {
            try {
                SQLiteDatabase writableDatabase = liteOpenHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(MySQLiteOpenHelper.table.MechanismHistoryTable.Column._HISTORY, history);
                writableDatabase.insert(tableName, null, contentValues);
                writableDatabase.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteMechanismHistory(String tableName, String history) {
        if (liteOpenHelper != null) {
            try {
                SQLiteDatabase writableDatabase = liteOpenHelper.getWritableDatabase();
                writableDatabase.delete(tableName, MySQLiteOpenHelper.table.MechanismHistoryTable.Column._HISTORY + "=?", new String[]{history});
                writableDatabase.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public List<String> queryAllMechanismHistoryList(String tableName) {
        if (liteOpenHelper != null) {
            List<String> historyList = new ArrayList<>();
            try {
                SQLiteDatabase readableDatabase = liteOpenHelper.getReadableDatabase();
                Cursor cursor = readableDatabase.rawQuery("select * from " + tableName, null);
                while (cursor.moveToNext()) {
                    String history = cursor.getString(1);
                    historyList.add(history);
                }
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return historyList;
        }
        return null;
    }
}
