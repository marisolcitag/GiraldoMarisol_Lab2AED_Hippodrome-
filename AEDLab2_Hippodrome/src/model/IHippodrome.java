package model;

public interface IHippodrome<R,B> {
	public void startRace();
	public void addRunner(R runner);
	public void addBet(B bet);
	public B getBet(String id);
}
