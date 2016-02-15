package org.goldengates.bluescales;

import java.awt.Graphics2D;

import org.goldengates.bluescales.api.Node;
import org.goldengates.bluescales.data.UserData;
import org.goldengates.bluescales.gui.GUI;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "GoldenGates", info = "", logo = "", name = "GoldenScales", version = 0.01)
public class GoldenScales extends Script {

	@Override
	public void onStart() throws InterruptedException {
		gui = new GUI();
		while (gui.isVisible()) {
			sleep(500);
		}
		UserData.useShortcut = getSkills().getDynamic(Skill.AGILITY) >= 70;
		startTime = System.currentTimeMillis();
	}

	@Override
	public int onLoop() throws InterruptedException {
		for (Node node : nodes) {
			if (node.validate()) {
				node.execute();
			}
		}
		return 250;
	}

	@Override
	public void onExit() throws InterruptedException {

	}

	@Override
	public void onPaint(Graphics2D g) {
		if (!gui.isVisible()) {
			long runTime = System.currentTimeMillis() - startTime;
			g.drawString("Time Running: ", 330, 370);
			g.drawString("" + formatTime(runTime), 415, 370);
		}
	}

	private Node[] nodes = {};
	private GUI gui;
	private long startTime;

	public String formatTime(long ms) {
		long s = ms / 1000, m = s / 60, h = m / 60;
		s %= 60;
		m %= 60;
		h %= 24;
		return String.format("%02d:%02d:%02d", h, m, s);
	}

}
