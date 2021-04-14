package com.huihe.base_lib.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public interface table {

        interface LanguageTable {
            String TABLE_NAME = "language";

            interface Column {
                String _ID = "id";
                String _KEY = "Lkey";
                String _CODE = "code";
                String _VALUE = "value";
            }

            String CREATE_TABLE_SQL =
                    "CREATE TABLE " + TABLE_NAME + "("
                            + Column._ID + " INTEGER PRIMARY KEY,"
                            + Column._KEY + " VARCHAR(30),"
                            + Column._CODE + " VARCHAR(20),"
                            + Column._VALUE + " VARCHAR(20)"
                            + ")";
        }

        interface MechanismHistoryTable {
            String TABLE_NAME = "mechanism_history";

            interface Column {
                String _ID = "id";
                String _HISTORY = "history";
            }

            String CREATE_TABLE_SQL =
                    "CREATE TABLE " + TABLE_NAME + "("
                            + Column._ID + " INTEGER PRIMARY KEY,"
                            + Column._HISTORY + " VARCHAR(30)"
                            + ")";
        }
    }

    public MySQLiteOpenHelper(@Nullable Context context) {
        super(context, "qmore.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table.MechanismHistoryTable.CREATE_TABLE_SQL);
        db.execSQL(table.LanguageTable.CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
