package com.example.mytrainingapp.helpers;

import static android.content.Context.MODE_PRIVATE;
import static com.example.mytrainingapp.ListOfConstants.NO_NAME;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/03
 */
public class SharedPreferencesHelper {

	private static Context context;

	private SharedPreferencesHelper() {}

	public static void setContext(Context context) {
		SharedPreferencesHelper.context = context;
	}

	public static void fillWithStringMap(Map<String, String> map, String preferencesName) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesName, MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		for(Map.Entry<String, String> entry : map.entrySet()) {
			editor.putString(entry.getKey(), entry.getValue());
		}
		editor.commit();
	}

	public static String getAddressName(String address, String preferencesName) {
		return context.getSharedPreferences(preferencesName, MODE_PRIVATE).getString(address, NO_NAME);
	}
}
