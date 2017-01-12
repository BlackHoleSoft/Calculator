package ru.ankanashov.calc.gui;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ru.ankanashov.calc.BaseSolver;
import ru.ankanashov.calc.login.Logger;

public class CalcEvents {
	
	private JTextField field;
	private BaseSolver solver;
	private Logger logger;	
	private StringBuilder currentAct;
	
	public CalcEvents(JTextField tf, BaseSolver s, Logger logger){
		field = tf;
		solver = s;
		this.logger = logger;
		currentAct = new StringBuilder();	
	}
	
	public void requestFocus(){
		field.requestFocusInWindow();
	}
	
	public void onDotBtn(){
		field.setText(field.getText()+".");
		field.requestFocusInWindow();
	}
	
	public void onNumBtn(int num){
		field.setText(field.getText()+num);
	}
	
	public void onSqrt() {		
		try{
			solver.reset();
			solver.addOperand(Double.parseDouble(field.getText()));
			solver.sqrt();
			double result = solver.getResult();			
			logger.log("sqrt:"+field.getText()+"="+result);
			field.setText(result+"");
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Wrong data in input field!");
			ex.printStackTrace();
		}
	}

	public void onDiv() {
		try{
			solver.addOperand(Double.parseDouble(field.getText()));
			solver.divide();
			currentAct.append(field.getText() + "/");
			field.setText("");
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Wrong data in input field!");
			ex.printStackTrace();
		}
	}

	public void onMult() {
		try{
			solver.addOperand(Double.parseDouble(field.getText()));
			solver.mult();
			currentAct.append(field.getText() + "*");
			field.setText("");
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Wrong data in input field!");
			ex.printStackTrace();
		}
	}

	public void onSub() {
		try{
			solver.addOperand(Double.parseDouble(field.getText()));
			solver.subtract();
			currentAct.append(field.getText() + "-");
			field.setText("");
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Wrong data in input field!");
			ex.printStackTrace();
		}
	}

	public void onAdd() {
		try{			
			solver.addOperand(Double.parseDouble(field.getText()));
			solver.add();
			currentAct.append(field.getText() + "+");
			field.setText("");			
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Wrong data in input field!");
			ex.printStackTrace();
		}
	}

	public void onEqual(){
		try{
			solver.addOperand(Double.parseDouble(field.getText()));
			currentAct.append(field.getText());
			field.setText(solver.getResult()+"");
			solver.reset();
			currentAct.append("="+field.getText());
			logger.log(currentAct.toString());
			currentAct = new StringBuilder();
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Wrong data in input field!");
			ex.printStackTrace();
		}		
	}
	
}
