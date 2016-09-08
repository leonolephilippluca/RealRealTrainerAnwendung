package util;

import java.awt.Dimension;
import java.awt.Toolkit;

import gui.controls.TrainerBorderPane;


public class ScreenSize {
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static double APPLICATION_WIDTH = new TrainerBorderPane().getWidth();
	public static double APPLICATION_HEIGHT = new TrainerBorderPane().getHeight();
}
