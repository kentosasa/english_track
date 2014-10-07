package com.kento.db.english_track;

import java.util.Date;

public class Score {
	Date created_at;
	int score;
	int crt_cnt;
	int mis_cnt;
	int jp_en_cnt;
	int jp_en_crt_cnt;
	int en_ja_cnt;
	int en_ja_crt_cnt;

	public Score(int score, int crt_cnt, int mis_cnt, int jp_en_cnt, int jp_en_crt_cnt, int en_ja_cnt, int en_ja_crt_cnt){
		created_at = new Date();
		this.score = score;
		this.crt_cnt = crt_cnt;
		this.mis_cnt = mis_cnt;
		this.jp_en_cnt = jp_en_cnt;
		this.jp_en_crt_cnt = jp_en_crt_cnt;
		this.en_ja_cnt = en_ja_cnt;
		this.en_ja_crt_cnt = en_ja_crt_cnt;
	}
	public Date getCreated_at(){
		return created_at;
	}
	public int getScore(){
		return score;
	}
	public int getCtr_cnt(){
		return crt_cnt;
	}
	public int  getMis_cnt() {
		return mis_cnt;
	}
	public int getJp_en_cnt() {
		return jp_en_cnt;
	}
	public int getJp_en_crt_cnt() {
		return jp_en_crt_cnt;
	}
	public int getEn_ja_cnt(){
		return en_ja_cnt;
	}
	public int getEn_ja_crt_cnt() {
		return en_ja_crt_cnt;
	}
}
