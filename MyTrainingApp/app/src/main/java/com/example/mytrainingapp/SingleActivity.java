package com.example.mytrainingapp;

import static com.example.mytrainingapp.ListOfConstants.*;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mytrainingapp.helpers.DateHelper;
import com.example.mytrainingapp.helpers.HashMapHelper;
import com.example.mytrainingapp.helpers.SharedPreferencesHelper;
import com.example.mytrainingapp.log.MyLogger;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/02
 */
public class SingleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationDispatcher.init(this);
        SharedPreferencesHelper.setContext(this);
        SharedPreferencesHelper.fillWithStringMap(HashMapHelper.fillMapWithAddressAndNames(), ADDRESS_NAMES);
        MyLogger.setConfiguration(SingleActivity.class, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyLogger.info(DateHelper.currentDate() + LOG_SLASH + APP_STARTED);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyLogger.info(DateHelper.currentDate() + LOG_SLASH + APP_CLOSED);
    }
}