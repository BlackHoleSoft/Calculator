package ru.ankanashov.calc;

import ru.ankanashov.calc.gui.LoginFrame;

public class Main {	
	
	private BaseSolver solver;

	public static void main(String[] args) {
		new Main();
	}
	
	public Main(){
		solver = new BaseSolver();		
		new LoginFrame(solver);
	}

}
