package org.goldengates.bluescales.nodes;

import org.goldengates.bluescales.api.Node;
import org.goldengates.bluescales.data.UserData;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.Script;

public class Banking extends Node {

	public Banking(Script s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate() throws InterruptedException {
		if (Banks.FALADOR_EAST.contains(s.myPlayer()))
			return (s.getInventory().getAmount(UserData.foodName) != UserData.foodAmount)
					|| (!UserData.useShortcut && s.getInventory().getAmount("Dusty key") != 1)
					|| (s.getInventory().getAmount("Falador teleport") != 0);
		return false;
	}

	@Override
	public void execute() throws InterruptedException {
		if (s.getBank().isOpen()) {
			withdrawDustyKey(UserData.useShortcut, s.getInventory().getAmount("Dusty key"));
			withdrawFaladorTeleport(s.getInventory().getAmount("Falador teleport"));
			withdrawFood(s.getInventory().getAmount(UserData.foodName));
		} else {
			s.getBank().open();
		}
	}

	private void withdrawDustyKey(boolean useShortcut, long currentAmount) {
		if (UserData.useShortcut) {
			if (currentAmount > 0) {
				s.getBank().depositAll("Dusty key");
			}
		} else {
			if (currentAmount < 1) {
				s.getBank().withdraw("Dusty key", 1);
			} else if (currentAmount > 1) {
				s.getBank().deposit(currentAmount);
			}
		}
	}

	private void withdrawFaladorTeleport(long currentAmount) {

	}

	private void withdrawFood(long currentAmount) {

	}

}
