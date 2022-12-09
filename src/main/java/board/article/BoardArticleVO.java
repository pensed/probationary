package board.article;

import java.sql.Date;

public class BoardArticleVO {
	
	private String num;
	private String writer;
	private String title;
	private String content;
	private String is_private;
	private Date date;
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIs_private() {
		return is_private;
	}
	public void setIs_private(String is_private) {
		this.is_private = is_private;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
