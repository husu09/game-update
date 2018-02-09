package com;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;

public class WindowsEnumProc implements WNDENUMPROC {

	private boolean isTest;
	private RobotTool robotTool;

	public WindowsEnumProc() throws AWTException {
		isTest = true;
		robotTool = new RobotTool();
	}

	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	private void exec(String path) {
		try {
			Process ps = Runtime.getRuntime().exec(path);
			ps.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean callback(HWND hWnd, Pointer data) {
		char[] charArr = new char[1024];
		User32.INSTANCE.GetWindowText(hWnd, charArr, charArr.length);
		String winText = Native.toString(charArr);
		if (winText.contains("Eclipse")) {
			if (isTest) {
				isTest = false;
				return true;
			}
			sleep(5000);
			// eclipse 窗口最大化显示在最前
			User32.INSTANCE.SetForegroundWindow(hWnd);
			User32.INSTANCE.ShowWindow(hWnd, User32.SW_MAXIMIZE);
			// update
			exec("cmd /c start F:/svn-temp/game-server-update.bat");
			// stop
			robotTool.mouseClick(1850, 930);
			robotTool.inputString(KeyEvent.VK_S, KeyEvent.VK_T, KeyEvent.VK_O, KeyEvent.VK_P);
			robotTool.keyClick(KeyEvent.VK_ENTER);
			robotTool.mouseRightClick(1850, 930);
			robotTool.mouseClick(1860, 800);
			// star
			robotTool.mouseClick(195, 60);
			robotTool.mouseClick(227, 86);
			return false;
		}
		return true;
	}

}
