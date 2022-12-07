package board.register;

import java.sql.Date;

public class BoardRegisterVO {
	private int num;
	private String writer;
	private String title;
	private Date date;
	private int cnt;
	private String content;
	private String is_private;
	
	public BoardRegisterVO() {
		System.out.println("BoardRegisterVO 불러오기 성공");
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
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

}
