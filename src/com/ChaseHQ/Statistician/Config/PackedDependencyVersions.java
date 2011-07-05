package com.ChaseHQ.Statistician.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.ChaseHQ.Statistician.Log;
import com.ChaseHQ.Statistician.StatisticianPlugin;

public abstract class PackedDependencyVersions {
	public static String getVesionFor(String PackedDependecncyName) {
		InputStream is = StatisticianPlugin.getEnabledPlugin().getClass().getClassLoader().getResourceAsStream("com/ChaseHQ/Statistician/Dependency/dependency.versions");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String readLine;
		try {
			while ((readLine = br.readLine()) != null) {
				String[] result = readLine.split(":");
				if (result.length == 2) {
					if (result[0].compareToIgnoreCase(PackedDependecncyName) != 0) continue;
					return result[1];
				}
			}
		} catch (IOException e) {
			Log.ConsoleLog("Could not read dependency.versions internally, Could become a bigger issue");
		}
		return null;
	}
}
