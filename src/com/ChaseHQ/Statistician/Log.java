package com.ChaseHQ.Statistician;

import com.ChaseHQ.Statistician.Config.Config;

public class Log {
	public static void ConsoleLog(String toConsole) {
		System.out.println(Config.LogPrefix + " " + toConsole);
	}
}
