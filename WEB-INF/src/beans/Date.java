package beans;

public class Date {

	private int id = 0;
	private String title = null;
	private String time = null;

	public Date(int id, String title, String time) {
		super();
		this.id = id;
		this.title = title;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}