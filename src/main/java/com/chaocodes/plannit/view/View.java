package com.chaocodes.plannit.view;

import javax.swing.JPanel;

public interface View
{
	public void initial();

	public void update();

	public void refresh();

	public JPanel getContainer();
}
