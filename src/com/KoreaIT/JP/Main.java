package com.KoreaIT.JP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("=== 프로그램 시작 ===");
		
		List<Article> articles = new ArrayList<>();
		
		int lastArticleId = 0;
		
		while(true) {
			
			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("exit")) {
				System.out.println("=== 프로그램 종료 ===");
				break;
			}
			
			if(cmd.equals("article write")) {
				System.out.println("=== 게시물 작성 ===");
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
				System.out.println("title : " + title);
				System.out.println("body : " + body);
				
				Article article = new Article(id, title, body);
				
				System.out.println(article);
				
				articles.add(article);
				Connection conn = null;
				PreparedStatement pstmt = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/JDBCTest?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

					conn = DriverManager.getConnection(url, "root", "");
					System.out.println("연결 성공!");

					String sql = "INSERT INTO article";
					sql += " SET regDate = NOW()";
					sql += ", updateDate = NOW()";
					sql += ", title = '" + title + "'";
					sql += ", `body` = '" + body + "';";

					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);

					int affectedRows = pstmt.executeUpdate();

					System.out.println(affectedRows + "열에 적용됨");

				} catch (ClassNotFoundException e) {
					System.out.println("드라이버 로딩 실패");
				} catch (SQLException e) {
					System.out.println("에러: " + e);
				} finally {
					try {
						if (conn != null && !conn.isClosed()) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						if (pstmt != null && !pstmt.isClosed()) {
							pstmt.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				lastArticleId++;
				
				continue;
			} else if(cmd.equals("article list")) {
				System.out.println("=== 게시물 리스트 ===");
				
				if(articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				
				System.out.println("번호   |    제목");
				
				for(int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%4d   |   %s\n", article.id, article.title);
				}
				
			}
			
		}

	}

}
