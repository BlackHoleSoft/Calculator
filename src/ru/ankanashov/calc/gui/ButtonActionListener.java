package ru.ankanashov.calc.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonActionListener implements ActionListener {
	
	private CalcEvents events;
	
	public ButtonActionListener(CalcEvents events) {
		this.events = events;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		JButton btn = (JButton)e.getSource();
		boolean isNum = true;
		int num = 0;
		
		try{
			num = Integer.parseInt(btn.getText());
		}catch(NumberFormatException ex){
			isNum = false;
		}
		
		if(isNum){
			events.onNumBtn(num);
		}else{
			
			switch(btn.getText()){
				case "+": events.onAdd();
			              break;
				case "-": events.onSub();
			              break;
				case "*": events.onMult();
			              break;
				case "/": events.onDiv();
				          break;
				case ".": events.onDotBtn();
			              break;
				default:  events.onSqrt();
			              break;			              
			}
			
		}
		
		events.requestFocus();
		
	}
	
}
