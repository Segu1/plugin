package org.cowCannon.cowCannon;


import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.md_5.bungee.api.ChatColor;

public class Board implements Runnable {
	private final static Board Instance = new Board();

	private Board() {
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
	private void createNewScoreBoard(Player player){
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objetive = scoreboard.registerNewObjective("CowCannon", "dummy");
		
		objetive.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Component colorfulMessage = Component.text("Cow Prototype")
	                .color(TextColor.color(0x00FF00)); // Hex for green
		
		objetive.displayName(colorfulMessage); //this might not work, try later with set
		
		objetive.getScore("").setScore(7);;
		objetive.getScore(ChatColor.GREEN + "Welcome to {server-name}").setScore(6);
		objetive.getScore("").setScore(5);
		objetive.getScore(ChatColor.DARK_PURPLE +  "" + ChatColor.BOLD + "Your stats: ").setScore(4);
		//objetive.getScore(ChatColor.RED + "Health: ").setScore(3);
		//objetive.getScore(ChatColor.DARK_GREEN + "Walked: ").setScore(2);;
		objetive.getScore("").setScore(1);
		objetive.getScore("mc.thisisaip.net").setScore(0);

		
		//to update scoreboard without having to flick
		Team team1 = scoreboard.registerNewTeam("team1");
		String teamKeyString = ChatColor.GOLD.toString();
		
		//we create a team for each updatable data we want to add. 
		team1.addEntry(teamKeyString);
		team1.setSuffix("Walked: ");
		team1.setPrefix("0 cm");
		
		objetive.getScore(teamKeyString).setScore(2);
		
		player.setScoreboard(scoreboard);

	}
	
	private void updateScoreBoard(Player player){
		Scoreboard scoreboard = player.getScoreboard();
		Team team1 = scoreboard.getTeam("team1");
		team1.setSuffix(ChatColor.YELLOW + "" + (player.getStatistic(Statistic.WALK_ONE_CM) + player.getStatistic(Statistic.SPRINT_ONE_CM ) + "cm"));
		
	}
	
	public static Board getInstance() {
		return Instance;
	}

}
