package com.example.mytrainingapp.helpers;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/02
 */
public class HashMapHelper {

	private HashMapHelper() {}

	public static Map<String, String> fillMapWithAddressAndNames() {
		Map<String, String> map = new HashMap<>();
		map.put("0x50b66a5cdc3247a1c88f53ad711cd1a35885cd20", "Sister's wallet");
		map.put("0x1a1ec25dc08e98e5e93f1104b5e5cdd298707d31", "Spare wallet");
		map.put("0x39bea96e13453ed52a734b6aceed4c41f57b2271", "Dima's wallet");
		return map;
	}
}
