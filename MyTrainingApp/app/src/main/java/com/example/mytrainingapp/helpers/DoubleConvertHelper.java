package com.example.mytrainingapp.helpers;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/03
 */
public class DoubleConvertHelper {

	private DoubleConvertHelper() {}

	public static double convertInDouble(String str, int pos) {
		StringBuilder sb = new StringBuilder();
		pos = str.length()-pos;
		if(pos >= 0) {
			sb.append(str.substring(0, pos) + "." + str.substring(pos));
		}
		else {
			sb.append(str);
			for(int i = 0; i < -pos; i++) {
				sb.insert(0, "0");
			}
			sb.insert(0, ".");
		}
		return Double.parseDouble(sb.toString());
	}
}
