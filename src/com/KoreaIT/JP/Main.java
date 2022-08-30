package com.KoreaIT.JP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.KoreaIT.uiil.DBUtil;
import com.KoreaIT.uiil.SecSql;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);

		System.out.println("==프로그램 시작==");

		while (true) {

			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				System.out.println("===종료===");
				break;
			}

			if (cmd.equals("article write")) {
				System.out.println("== 게시물 작성 ==");
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Connection conn = null;
				PreparedStatement pstmt = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/JDBCTest?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

					conn = DriverManager.getConnection(url, "root", "");
					System.out.println("연결 성공!");

//					String sql = "INSERT INTO article";
//					sql += " SET regDate = NOW()";
//					sql += ", updateDate = NOW()";
//					sql += ", title = '" + title + "'";
//					sql += ", `body` = '" + body + "';";

					SecSql sql = new SecSql();
					sql.append("INSERT INTO article");
					sql.append(" SET regDate = NOW()");
					sql.append(", updateDate = NOW()");
					sql.append(", title = ?", title);
					sql.append(", `body` = ?", body);

					int id = DBUtil.insert(conn, sql);

					System.out.println(sql);

					System.out.printf("%d번글이 생성되었습니다\n", id);

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

			} else if (cmd.startsWith("article modify ")) {

				int id = Integer.parseInt(cmd.split(" ")[2]);

				Connection conn = null;
				PreparedStatement pstmt = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/JDBCTest?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

					conn = DriverManager.getConnection(url, "root", "");
					System.out.println("연결 성공!");

//					String sql = "UPDATE article";
//					sql += " SET updateDate = NOW()";
//					sql += ", title = '" + title + "'";
//					sql += ", `body` = '" + body + "'";
//					sql += " WHERE id = " + id + ";";

					SecSql sql = new SecSql();
					sql.append("SELECT COUNT(*)");
					sql.append("FROM article");
					sql.append("WHERE id = ?", id);

					int articlesCount = DBUtil.selectRowIntValue(conn, sql);

					if (articlesCount == 0) {
						System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
						continue;
					}

					System.out.printf("== %d번 게시물 수정 ==\n", id);

					System.out.printf("새 제목 : ");
					String title = sc.nextLine();
					System.out.printf("새 내용 : ");
					String body = sc.nextLine();

					sql = new SecSql();
					sql.append("UPDATE article");
					sql.append("SET updateDate = NOW()");
					sql.append(", title = ?", title);
					sql.append(", `body` = ?", body);
					sql.append(" WHERE id = ?;", id);

					System.out.println(sql);

					DBUtil.update(conn, sql);

					System.out.printf("%d번 게시물이 수정 되었습니다.\n", id);

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

			} else if (cmd.startsWith("article delete ")) {
				int id = Integer.parseInt(cmd.split(" ")[2]);

				Connection conn = null;
				PreparedStatement pstmt = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/JDBCTest?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

					conn = DriverManager.getConnection(url, "root", "");
					System.out.println("연결 성공!");

					SecSql sql = new SecSql();
					sql.append("SELECT COUNT(*)");
					sql.append("FROM article");
					sql.append("WHERE id = ?", id);

					int articlesCount = DBUtil.selectRowIntValue(conn, sql);

					if (articlesCount == 0) {
						System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
						continue;
					}

					System.out.printf("== %d번 게시물 삭제 ==\n", id);

					sql = new SecSql();
					sql.append("DELETE FROM article");
					sql.append("WHERE id = ?", id);

					DBUtil.delete(conn, sql);

					System.out.printf("%d번 게시물이 삭제 되었습니다\n", id);

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

			} else if (cmd.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				List<Article> articles = new ArrayList<>();

				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/JDBCTest?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

					conn = DriverManager.getConnection(url, "root", "");
					System.out.println("연결 성공!");

//					String sql = "SELECT *";
//					sql += " FROM article";
//					sql += " ORDER BY id DESC";
					

					SecSql sql = new SecSql();
					sql.append("SELECT *");
					sql.append("FROM article");
					sql.append(" ORDER BY id DESC");

					List<Map<String, Object>> articlesListMap = DBUtil.selectRows(conn, sql);

					for (Map<String, Object> articleMap : articlesListMap) {
						articles.add(new Article(articleMap));
					}

					if (articles.size() == 0) {
						System.out.println("게시물이 없습니다");
						continue;
					}

					System.out.println("번호    |    제목");

					for (int i = 0; i < articles.size(); i++) {
						Article article = articles.get(i);
						System.out.printf("%4d    |    %s\n", article.id, article.title);
					}

				} catch (ClassNotFoundException e) {
					System.out.println("드라이버 로딩 실패");
				} catch (SQLException e) {
					System.out.println("에러: " + e);
				} finally {
					try {
						if (rs != null && !rs.isClosed()) {
							rs.close();
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
					try {
						if (conn != null && !conn.isClosed()) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}

		}
	}

}