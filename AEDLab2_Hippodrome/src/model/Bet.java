package model;

public class Bet {

	private String iD;
	private String name;
	private String horse;
	private int budget;
	
	public Bet(String iD, String name, String horse, int budget) {
		this.iD = iD;
		this.name = name;
		this.horse = horse;
		this.budget = budget;
	}
	
	public String getiD() {
		return iD;
	}
	public void setiD(String iD) {
		this.iD = iD;
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
	public void setHorse(String horse){
		this.horse = horse;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
}
