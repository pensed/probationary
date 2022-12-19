import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TestBoardFilter {
	
	private Logger logger = LogManager.getLogger(LogTest.class);
	
	@Disabled
	@Test
	void filterLoginVerificationFirst() {
		String uri = "/BoardArticleDeleteForm.jsp";
		String id  = "kim";
		
		if(uri.contains("Delete")||uri.contains("Update")) {
			if(id==null || id.trim().length() <=0) {
				logger.info("href=\"/BoardListForm.jsp\"");
			}else {
				logger.info(uri);
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
					logger.info("href=\"/BoardListForm.jsp\"");
				} 
				break;
			default:
				logger.info("chain.doFilter(request, response);");
				break;
		}
	}

}
