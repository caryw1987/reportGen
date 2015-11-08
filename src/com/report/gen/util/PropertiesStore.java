package com.report.gen.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PropertiesStore {

	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static List<PropertiesStore> getPropertiesStoreList(String[] names, String[] values) {
		ArrayList<PropertiesStore> tempList = new ArrayList<PropertiesStore>();
		PropertiesStore p = new PropertiesStore();
		for (int i = 0; i < values.length; i++) {
			p.setName(names[i]);
			p.setValue(values[i]);
			tempList.add(p);
		}
		return tempList;
	}

	public static List<PropertiesStore> getPropertiesStoreList(HashMap allValues) {
		ArrayList<PropertiesStore> tempList = new ArrayList<PropertiesStore>();
		PropertiesStore p;
		Iterator allKey = allValues.keySet().iterator();
		while (allKey.hasNext()) {
			p = new PropertiesStore();
			int key = Integer.valueOf(allKey.next().toString());
			String name = allValues.get(key).toString();
			p.setName(name);
			p.setValue(String.valueOf(key));
			tempList.add(p);
		}
		return tempList;
	}
}
