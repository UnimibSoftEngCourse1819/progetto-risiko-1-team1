package com.drisk.domain;

import java.util.LinkedList;
import java.util.List;

public class GameManager {
	
	private List<Player> players;
	private static GameManager instance;	
	
	private GameManager() {
		players = new LinkedList<>();
	}
	
	public static GameManager getInstance() {
		if (instance == null)
			instance = new GameManager();
		return instance;
	}
		
	//"template" perchè posso inizializzare il gioco sia attraverso il database
	//con una mappa predefinita, sia inizializzando una mappa nuova passata come
	//json dal client. Quindi in realtà ci saranno due implementaizoni diverse.
	public void initGameTemplate() {
		initPlayers();
		initMap();
		initCards();
		initPlayersMission();
		initPlayersTerritories();
		initTanks();
	}

	private void initPlayers() {
		players = MatchManager.getInstance().getPlayers();
	}
	
	public void initMap() {
		Map.getInstance().createMap("easy");
	}
	
	public void initCards() {
		//da implementare TODO
	}
	
	public void initPlayersMission() {
		//da implementare TODO
	}
	
	public void initPlayersTerritories() {
		List<Territory> territories = Map.getInstance().getTerritories();
		for (int i = 0; i < territories.size(); ++i) {
			players.get(i % players.size()).addTerritoryOwned(territories.get(i));
		}
	}
	
	public void initTanks() {
		//da implementare TODO
	}
	
	public boolean checkWin(Player currentPlayer) {
		List<Territory> territories = Map.getInstance().getTerritories();
		int totalNumberOfTerritories = territories.size();
		
		int currentPlayerNumberOfTerritories = currentPlayer.getNumberOfTerritoriesOwned();
		double playerTerritoriesRate = (double) currentPlayerNumberOfTerritories / totalNumberOfTerritories;
		return playerTerritoriesRate >= (double) 2 / 3;	
	}
	
	public boolean checkLoss() {
		//da implementare TODO
		return false;
	}

	public List<Player> getPlayers() {
		return players;
	}
	
}