
package beans;

public class Student {

	private int id = 0;
	private String studentId = null;
	private String password = null;

	public Student(int id, String studentId, String password) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}