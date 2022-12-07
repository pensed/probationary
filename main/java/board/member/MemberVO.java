package board.member;

public class MemberVO {
	private String id;
	private String pwd;
	private boolean rememberId;
	
	public MemberVO() {
		System.out.println("*");
	}
	
	public MemberVO(String id, String pwd, boolean rememberId) {
		this.id = id;
		this.pwd = pwd;
		this.rememberId = rememberId;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public boolean getRememberId() {
		return rememberId;
	}

	public void setRememberId(boolean rememberId) {
		this.rememberId = rememberId;
	}
}
