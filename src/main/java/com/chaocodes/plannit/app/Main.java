package com.chaocodes.plannit.app;

import javax.swing.SwingUtilities;

import com.chaocodes.plannit.controller.MainController;

public class Main
{
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainController mc = new MainController();
				mc.initial();
			}
		});
	}
}