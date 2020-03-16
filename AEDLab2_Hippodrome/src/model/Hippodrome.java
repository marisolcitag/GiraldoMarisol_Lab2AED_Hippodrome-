package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Hippodrome<R,B> implements IHippodrome<R, B>{
	
	private HashMap<String, B> bets;
	private int[] tracksDistances;
	private Stack<R> secondLap;
	private Queue<R> ArrivalOrder;
	private boolean isSecondLap;
	
	public Hippodrome() {
		bets = new HashMap<String, B>();
		secondLap = new Stack<>();
		ArrivalOrder = new LinkedList<R>();
		isSecondLap = false;
		tracksDistances = new int[10];
		modelateTracks();
	}
	
	public void startRace() {
		if(isSecondLap) {
			
		}else {
			
			ArrayList<Runner> runners = new ArrayList<>();
			int size = ArrivalOrder.size();
			for (int i = 0; i < size; i++) {
				Runner r = (Runner) ArrivalOrder.poll();
				double speed =  ((Math.random()*40 + 20))*1000;
				 
			}
		}
		isSecondLap = !isSecondLap;
	}
	
	private void modelateTracks() {
		tracksDistances[0] = 400;
		tracksDistances[1] = 407;
		tracksDistances[2] = 414;
		tracksDistances[3] = 422;
		tracksDistances[4] = 430;
		tracksDistances[5] = 437;
		tracksDistances[6] = 445;
		tracksDistances[7] = 453;
		tracksDistances[8] = 460;
		tracksDistances[9] = 468;
	}
	
	public void addRunner(R runner) {
		if(ArrivalOrder.size()<10) {
			
			ArrivalOrder.add(runner);
		}
	}
	
	public void addBet(B bet) {
		bets.put(((Bet) bet).getiD(), bet);
	}
	
	public B getBet(String id) { return bets.get(id); }

	public HashMap<String, B> getBets() {
		return bets;
	}

	public void setBets(HashMap<String, B> bets) {
		this.bets = bets;
	}

	public int[] getTracksDistances() {
		return tracksDistances;
	}

	public void setTracksDistances(int[] tracksDistances) {
		this.tracksDistances = tracksDistances;
	}

	public Stack<R> getSecondLap() {
		return secondLap;
	}

	public void setSecondLap(Stack<R> secondLap) {
		this.secondLap = secondLap;
	}

	public Queue<R> getArrivalOrder() {
		return ArrivalOrder;
	}

	public void setArrivalOrder(Queue<R> arrivalOrder) {
		ArrivalOrder = arrivalOrder;
	}

	public boolean isSecondLap() {
		return isSecondLap;
	}

	public void setSecondLap(boolean isSecondLap) {
		this.isSecondLap = isSecondLap;
	}
	
}

