package com.ChaseHQ.Statistician.Utils;

import org.bukkit.entity.Player;

public abstract class StringHandler {
	public static String formatForChat(String unFormatted, Player player) {
		String returnString;
		// First Format for colors using standard ampersand system
		returnString = unFormatted.replaceAll("&([0-9a-f])", "\u00A7$1");
		// Next replace our token values
		returnString = returnString.replaceAll("\\{playerName}", player.getName());
		return returnString;
	}
}
