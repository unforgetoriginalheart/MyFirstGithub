package com.taikang.latter_ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Time：2018/12/18
 * Author: gaonz
 * Description:
 */
public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DatabaseManager() {
    }

    public void init(Context context) {
        initDao(context);
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();

    }

    public final UserProfileDao getDao() {
        return mDao;
    }
}
