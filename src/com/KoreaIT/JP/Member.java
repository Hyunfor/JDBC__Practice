package com.KoreaIT.JP;

import java.util.Map;

public class Member {

	public int id;
	public String loginId;
	public String loginPw;
	public String name;
	public Map<String, Object> MemberMap;
	public String regDate;
	public String updateDate;
	
	public Member(int id, String loginId, String name){
		this.id = id;
		this.loginId = loginId;
		this.name = name;
	}
	
	public Member(int id, String regDate, String updateDate, String loginId, String loginPw, String name){
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
	}
	
	
	
	public Member(Map<String, Object>[] membersListMap) {
		this.id = (int) MemberMap.get("id");
		this.loginId = (String) MemberMap.get("loginId");
		this.loginPw = (String) MemberMap.get("loginPw");
		this.name = (String) MemberMap.get("name");
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", loginId=" + loginId + ", loginPw=" + loginPw + ", name=" + name + "]";
	}
	
	

}
