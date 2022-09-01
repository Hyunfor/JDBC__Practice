package com.KoreaIT.JP.test;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.JP.Main;

public class MemberController{ // 테스트 계정 시도 중

	private List<Member> members;
	private Scanner sc;
	
	public MemberController(Scanner sc, List<Member> members) {
		this.sc = sc;
		this.members = members;
	}
	
	public void makeTestData() {
		System.out.println("테스트를 위한 계정을 생성합니다.");

		members.add(new Member("이름1", "계정1"));
		members.add(new Member("이름2", "계정2"));
		members.add(new Member("이름3", "계정3"));
	}

	

}
