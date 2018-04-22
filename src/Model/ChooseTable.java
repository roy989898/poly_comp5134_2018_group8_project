package Model;

public class ChooseTable {

	private int id;
	private int studentid;
	private int teacherid;
	private String teachername;
	private String teachercourse;

	public ChooseTable(int id, int studentid, int teacherid, String teachername, String teachercourse) {
		super();
		this.id = id;
		this.studentid = studentid;
		this.teacherid = teacherid;
		this.teachername = teachername;
		this.teachercourse = teachercourse;
	}

	protected int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	protected int getStudentid() {
		return studentid;
	}

	protected void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	protected int getTeacherid() {
		return teacherid;
	}

	protected void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}

	protected String getTeachername() {
		return teachername;
	}

	protected void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	protected String getTeachercourse() {
		return teachercourse;
	}

	protected void setTeachercourse(String teachercourse) {
		this.teachercourse = teachercourse;
	}
	
	
	

}
