package com.sri.shaadi.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sri.shaadi.R;
import com.sri.shaadi.requests.MatchRequest;
import com.sri.shaadi.requests.MatchesRequestsActivity;
import com.sri.shaadi.requests.ShaadiDatabase;

/*
* @author Srinidhi A
*
* Splash Screen with Logo of Shaadi as first activity after Launch
* Also Inserting a Test data for the database to access in next Screen
* */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        insertTempData();
        new Handler().postDelayed(new Runnable() {
            // Using handler with postDelayed called runnable run method
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MatchesRequestsActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }

        }, 3 * 1000); // wait for 3 seconds
    }

    // Inserting three Request test Data to show the cards in the Next Screen
    public void insertTempData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShaadiDatabase shaadiDatabase = ShaadiDatabase.getDbInstance(SplashActivity.this);
                if (!shaadiDatabase.requestsDao().isExist(1)) {
                    MatchRequest matchRequest = new MatchRequest();
                    matchRequest.setId(1);
                    matchRequest.setName("Kruthika");
                    matchRequest.setAge(25);
                    matchRequest.setHeight("5'.4'");
                    matchRequest.setQualification("BE Computer Science");
                    matchRequest.setWork("Software professional");
                    matchRequest.setAddress("Chitradurga, Karnataka, India");
                    matchRequest.setStatus(1);
                    shaadiDatabase.requestsDao().insertRequest(matchRequest);
                }
                if (!shaadiDatabase.requestsDao().isExist(2)) {

                    MatchRequest matchRequest = new MatchRequest();
                    matchRequest.setId(2);
                    matchRequest.setName("Krushitha");
                    matchRequest.setAge(31);
                    matchRequest.setHeight("5'.2'");
                    matchRequest.setQualification("M-Tech Phd");
                    matchRequest.setWork("Teacher");
                    matchRequest.setAddress("Shimoga, Karnataka, India");
                    matchRequest.setStatus(1);
                    shaadiDatabase.requestsDao().insertRequest(matchRequest);
                }

                if (!shaadiDatabase.requestsDao().isExist(3)) {
                    MatchRequest matchRequest = new MatchRequest();
                    matchRequest.setId(3);
                    matchRequest.setName("Laali");
                    matchRequest.setAge(30);
                    matchRequest.setHeight("5'.4'");
                    matchRequest.setQualification("BE");
                    matchRequest.setWork("HR professional");
                    matchRequest.setAddress("Baengaluru, Karnataka, India");
                    matchRequest.setStatus(1);
                    shaadiDatabase.requestsDao().insertRequest(matchRequest);
                }

            }
        }).start();
    }
}