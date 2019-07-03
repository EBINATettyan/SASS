package beans;

public class SelfAssessmentData {

	private int id = 0;
	private String studentId = null;
	private int dateId = 0;
	private int comp1 = 0;
	private int comp2 = 0;
	private String comp1Comment = null;
	private String comp2Comment = null;
	private int comp1Check = 0;
	private int comp2Check = 0;

	public SelfAssessmentData(int id, String studentId, int dateId, int comp1, int comp2, String comp1Comment,
			String comp2Comment, int comp1Check, int comp2Check) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.dateId = dateId;
		this.comp1 = comp1;
		this.comp2 = comp2;
		this.comp1Comment = comp1Comment;
		this.comp2Comment = comp2Comment;
		this.comp1Check = comp1Check;
		this.comp2Check = comp2Check;
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

	public int getDateId() {
		return dateId;
	}

	public void setDateId(int dateId) {
		this.dateId = dateId;
	}

	public int getComp1() {
		return comp1;
	}

	public void setComp1(int comp1) {
		this.comp1 = comp1;
	}

	public int getComp2() {
		return comp2;
	}

	public void setComp2(int comp2) {
		this.comp2 = comp2;
	}

	public String getComp1Comment() {
		return comp1Comment;
	}

	public void setComp1Comment(String comp1Comment) {
		this.comp1Comment = comp1Comment;
	}

	public String getComp2Comment() {
		return comp2Comment;
	}

	public void setComp2Comment(String comp2Comment) {
		this.comp2Comment = comp2Comment;
	}

	public int getComp1Check() {
		return comp1Check;
	}

	public void setComp1Check(int comp1Check) {
		this.comp1Check = comp1Check;
	}

	public int getComp2Check() {
		return comp2Check;
	}

	public void setComp2Check(int comp2Check) {
		this.comp2Check = comp2Check;
	}

}