package ru.ankanashov.calc.gui;

import javax.swing.JTextArea;

public class StackView extends JTextArea {	
	
	private static final long serialVersionUID = 1L;

	public StackView(){
		super();
		this.setEditable(false);
		this.setLineWrap(true);
	}
	
	public void pushString(String str){
		this.setText(this.getText()+str);
	}
	
	public void clear(){
		this.setText("");
	}
	
}
