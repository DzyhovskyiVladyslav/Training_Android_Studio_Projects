package com.example.mytrainingapp;

import android.app.Activity;
import android.os.Bundle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/02
 */
public class NavigationDispatcher {

    private static NavController navController;

    private NavigationDispatcher() {}

    public static void init(Activity activity) {
        navController = Navigation.findNavController(activity, R.id.nav_host_form);
    }

    public static void next(int idRes) {
        navController.navigate(idRes);
    }

    public static void next(int idRes, Bundle bundle) {
        navController.navigate(idRes, bundle);
    }

    public static void back() {
        navController.popBackStack();
    }
}
