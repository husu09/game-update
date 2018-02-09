package com;

import java.awt.AWTException;

import com.sun.jna.platform.win32.User32;

public class GameUpdateStart {

	public static void main(String[] args) throws InterruptedException, AWTException {
		User32.INSTANCE.EnumWindows(new WindowsEnumProc(), null);
	}
}
