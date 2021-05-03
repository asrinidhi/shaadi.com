package com.sri.shaadi.requests;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @author Srinidhi on 02,May,2021
 * Which Creates database object.
 */

@Database(entities = MatchRequest.class, exportSchema = false, version = 1)
public abstract class ShaadiDatabase extends RoomDatabase {
    private static final String DB_NAME = "shaadi_db";
    private static ShaadiDatabase dbInstance;

    public static synchronized ShaadiDatabase getDbInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context.getApplicationContext(), ShaadiDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }

    public abstract RequestsDao requestsDao();
}
