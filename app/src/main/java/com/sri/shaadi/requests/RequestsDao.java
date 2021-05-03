package com.sri.shaadi.requests;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Srinidhi on 03,May,2021
 */

@Dao
public interface RequestsDao {

    @Query("Select * from MatchRequest")
   public List<MatchRequest> getRequestsList();

    @Insert
   public  void insertRequest(MatchRequest matchRequest);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public int updateRequest(MatchRequest matchRequest);

    @Query("SELECT * FROM MatchRequest WHERE id == :id")
    public boolean isExist(int id);
}
