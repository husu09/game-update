package com;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class RobotTool {
	private Robot robot;

	public RobotTool() throws AWTException {
		this(1000);
	}

	public RobotTool(int ms) throws AWTException {
		robot = new Robot();
		robot.setAutoDelay(ms);
	}

	public void mouseClick(int x, int y) {
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public void mouseRightClick(int x, int y) {
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON3_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_MASK);
	}

	public void keyClick(int keycode) {
		robot.keyPress(keycode);
		robot.keyRelease(keycode);
	}

	public void inputString(int... keyCodes) {
		for (int keyCode : keyCodes) {
			keyClick(keyCode);
		}
	}
}
