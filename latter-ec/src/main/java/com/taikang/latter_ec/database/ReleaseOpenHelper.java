package com.taikang.latter_ec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Time：2018/12/18
 * Author: gaonz
 * Description:
 */
public class ReleaseOpenHelper extends DaoMaster.OpenHelper {
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
    }
}
