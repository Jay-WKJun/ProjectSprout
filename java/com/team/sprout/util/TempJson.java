package com.team.sprout.util;

import java.util.ArrayList;

import com.google.gson.JsonObject;

public class TempJson {
	private String name;
	private String desc;
	private ArrayList<JsonObject> values;
	
	public TempJson() {
		
	}
	public TempJson(String name, String desc, ArrayList<JsonObject> values) {
		
		this.name = name;
		this.desc = desc;
		this.values = values;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public ArrayList<JsonObject> getValues() {
		return values;
	}
	public void setValues(ArrayList<JsonObject> values) {
		this.values = values;
	}
	
	@Override
	public String toString() {
		return "TempJson [name=" + name + ", desc=" + desc + ", values=" + values + "]";
	}
	
}
