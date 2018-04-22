package Model;

public class TeacherStudent {
	

	private String name;
	private int id;
	private String pwd;
	private String chance;
	private String days;
	private String time;
	private boolean isTeacher;
	public TeacherStudent(String name, int id, String pwd, String chance, String days, String time,
			boolean isTeacher) {
		super();
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.chance = chance;
		this.days = days;
		this.time = time;
		this.isTeacher = isTeacher;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getChance() {
		return chance;
	}
	public void setChance(String chance) {
		this.chance = chance;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public boolean isTeacher() {
		return isTeacher;
	}
	public void setTeacher(boolean isTeacher) {
		this.isTeacher = isTeacher;
	}
	
	
	
	

}
