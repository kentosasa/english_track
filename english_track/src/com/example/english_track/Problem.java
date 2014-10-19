package com.example.english_track;

public class Problem {
	String jp = null;
	String en = null;
	int crt_cnt = 0;
	int mis_cnt = 0;
	int id = 0;
	
	public Problem(String en, String jp, int id){
		this.jp = jp;
		this.en = en;
		this.id = id;
	}
	public String getJp(){
		return jp;
	}
	public String getEn() {
		return en;
	}
	public int getCrt(){
		return crt_cnt;
	}
	public void addCrt(){
		crt_cnt++;
	}
	public int getMis() {
		return mis_cnt;
	}
	public void addMis() {
		mis_cnt++;
	}
}