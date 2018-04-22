package Model;

public class Couse {

	private int id;
	private int studentid;
	private int teacherid;
	private String teachername;
	private String teachercourse;

	public Couse(int id, int studentid, int teacherid, String teachername, String teachercourse) {
		super();
		this.id = id;
		this.studentid = studentid;
		this.teacherid = teacherid;
		this.teachername = teachername;
		this.teachercourse = teachercourse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public int getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getTeachercourse() {
		return teachercourse;
	}

	public void setTeachercourse(String teachercourse) {
		this.teachercourse = teachercourse;
	}
	
	
	

}
