package com.member.model;

public class MemberDTO {

	private int member_index;
	private String member_id;
	private String member_pwd;
	private String member_nickname;
	private String member_email;
	private int point;
	private String regdate;
	private String phone;
	private String prefer_lol;
	private String prefer_battle_ground;
	private String prefer_overwatch;
	private String member_profile;
	
	public int getMember_index() {
		return member_index;
	}
	public void setMember_index(int member_index) {
		this.member_index = member_index;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pwd() {
		return member_pwd;
	}
	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}

	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPrefer_lol() {
		return prefer_lol;
	}
	public void setPrefer_lol(String prefer_lol) {
		this.prefer_lol = prefer_lol;
	}
	public String getPrefer_battle_ground() {
		return prefer_battle_ground;
	}
	public void setPrefer_battle_ground(String prefer_battle_ground) {
		this.prefer_battle_ground = prefer_battle_ground;
	}
	public String getPrefer_overwatch() {
		return prefer_overwatch;
	}
	public void setPrefer_overwatch(String prefer_overwatch) {
		this.prefer_overwatch = prefer_overwatch;
	}

	public String getMember_profile() {
		return member_profile;
	}

	public void setMember_profile(String member_profile) {
		this.member_profile = member_profile;
	}
}
