package com.sri.shaadi.requests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.sri.shaadi.R;
import com.sri.shaadi.splash.SplashActivity;

import java.util.ArrayList;


/*
* @author Srinidhi A
* Activity which shows the list of requests from Room Database
*
* */

public class MatchesRequestsActivity extends AppCompatActivity implements RecyclerMatchesAdapter.CallbackListener {

    RecyclerView recyclerViewMatches;
    ArrayList<MatchRequest> matchArrayList;
    RecyclerMatchesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewMatches = (RecyclerView) findViewById(R.id.recyclerview_matches);
        matchArrayList = new ArrayList<>();
        adapter = new RecyclerMatchesAdapter(MatchesRequestsActivity.this, matchArrayList, this);
        recyclerViewMatches.setHasFixedSize(true);
        recyclerViewMatches.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMatches.setAdapter(adapter);
        getMatchesList();
    }

    //  Which Fetches lsit of requests from database
    private void getMatchesList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShaadiDatabase shaadiDatabase = ShaadiDatabase.getDbInstance(MatchesRequestsActivity.this);
                matchArrayList = (ArrayList<MatchRequest>) shaadiDatabase.requestsDao().getRequestsList();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addData(matchArrayList);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }


    // After Accepting requestw will update status database and Ui
    @Override
    public void onAcceptClick(MatchRequest matchRequest, int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShaadiDatabase shaadiDatabase = ShaadiDatabase.getDbInstance(MatchesRequestsActivity.this);
                matchRequest.setStatus(2);
                int updateStatus = shaadiDatabase.requestsDao().updateRequest(matchRequest);
                if (updateStatus == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            matchArrayList.remove(position);
                            matchArrayList.add(position, matchRequest);
                            adapter.addData(matchArrayList);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        }).start();

    }

    // After Declining requestw will update status database and Ui
    @Override
    public void onDeclineClick(MatchRequest matchRequest, int position) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ShaadiDatabase shaadiDatabase = ShaadiDatabase.getDbInstance(MatchesRequestsActivity.this);
                matchRequest.setStatus(3);
                int updateStatus = shaadiDatabase.requestsDao().updateRequest(matchRequest);
                if (updateStatus == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            matchArrayList.remove(position);
                            matchArrayList.add(position, matchRequest);
                            adapter.addData(matchArrayList);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        }).start();

    }
}