package com.chaocodes.plannit.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// View for everything, controlled by MainController
public class PlannitView implements View
{
	private JFrame wrapper;
	private JPanel container;
	private JPanel footer;

	private View currentView;

	private JButton addEvent;

	public PlannitView() {}

	private void initialWrapper() {
		wrapper = new JFrame();
	}

	private void initialContainer() {
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
		wrapper.add(container);
	}

	private void initialFooter() {
		footer = new JPanel();
		footer.setLayout(new GridLayout(0, 1, 0, 0));
		addEvent = new JButton("Add Event");
		footer.add(addEvent);
	}

	@Override
	public void initial() {
		initialWrapper();
		initialContainer();
		initialFooter();
	}

	private void updateContainer() {
		container.removeAll();
		container.add(currentView.getContainer(), BorderLayout.CENTER);
		container.add(footer, BorderLayout.SOUTH);
	}

	@Override
	public void update() {
		updateContainer();
	}

	private void refreshContainer() {
		container.repaint();
		container.revalidate();
	}

	@Override
	public void refresh() {
		refreshContainer();
	}

	public JFrame getWrapper() {
		return wrapper;
	}

	@Override
	public JPanel getContainer() {
		return container;
	}

	public JPanel getFooter() {
		return footer;
	}

	public View getCurrentView() {
		return currentView;
	}

	public void setCurrentView(View currentView) {
		this.currentView = currentView;
	}

	public JButton getAddEvent() {
		return addEvent;
	}
}
