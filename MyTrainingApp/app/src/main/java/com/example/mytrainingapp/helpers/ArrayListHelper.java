package com.example.mytrainingapp.helpers;

import java.util.List;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/02
 */
public class ArrayListHelper {

	private ArrayListHelper() {}

	public static List changeOrder(List list) {
		for(int i = 0, j = list.size() - 1; i < list.size() / 2; i++, j--) {
			Object temp = list.get(i);
			list.set(i, list.get(j));
			list.set(j, temp);
		}
		return list;
	}
}
