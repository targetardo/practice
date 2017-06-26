package test;

import dialog.MainView;
import controller.IController;
import controller.JPAController;

public class TestMainView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainView view = new MainView();
		view.setController(new JPAController());
		view.setVisible(true);
	}
}
