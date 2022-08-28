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
