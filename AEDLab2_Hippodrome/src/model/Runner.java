package model;

public class Runner {
	
	private String name;
	private String horse;
	
	public Runner(String name, String horse) {
		super();
		this.name = name;
		this.horse = horse;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHorse() {
		return horse;
	}
	public void setHorse(String horse) {
		this.horse = horse;
	}

}

