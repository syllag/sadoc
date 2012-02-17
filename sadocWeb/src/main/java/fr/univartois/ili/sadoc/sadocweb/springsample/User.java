package fr.univartois.ili.sadoc.sadocweb.springsample;

public class User {
	
	private int number;
	private String name;
	
	public User() {
		this.number = 123;
		this.setName("Lahoucine");
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
