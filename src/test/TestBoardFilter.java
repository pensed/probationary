import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TestBoardFilter {
	@Disabled
	@Test
	void filterLoginVerificationFirst() {
		String uri = "/BoardArticleDeleteForm.jsp";
		String id  = "kim";
		
		if(uri.contains("Delete")||uri.contains("Update")) {
			if(id==null || id.trim().length() <=0) {
				System.out.println("href=\"/BoardListForm.jsp\"");
			}else {
				System.out.println(uri);
			}
		}
		
	}
	
	@Test
	void filterLoginVerificationSecond() {
		String uri="/BoardArticleDeleteForm.jsp";
		String id ="yeom";
		
		switch(uri) {
			case "/BoardArticleDeleteForm.jsp":
			case "/BoardArticleUpdateForm.jsp":
			case "/boardmember":
			case "/boardregister":
				if(id==null || id.trim().length() <= 0) {
					System.out.println("href=\"/BoardListForm.jsp\"");
				} 
				break;
			default:
				System.out.println("chain.doFilter(request, response);");
				break;
		}
	}

}
