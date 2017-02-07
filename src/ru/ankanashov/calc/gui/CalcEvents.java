package ru.ankanashov.calc.gui;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ru.ankanashov.calc.BaseSolver;
import ru.ankanashov.calc.login.Logger;

public class CalcEvents {
	
	private final int MAX_SECOND_CACHE_SIZE = 10;
	
	private JTextField field;
	private BaseSolver solver;
	private Logger logger;	
	private StringBuilder currentAct;
	private StackView stackVw;
	
	public CalcEvents(JTextField tf, BaseSolver s, Logger logger, StackView stackVw){
		field = tf;
		solver = s;
		this.logger = logger;
		this.stackVw = stackVw;
		currentAct = new StringBuilder();	
	}
	
	public void requestFocus(){
		field.requestFocusInWindow();
	}
	
	public void onDotBtn(){
		String str = field.getText();
		if(str.charAt(str.length()-1) == '.'){
			return;
		}
		field.setText(str+".");
		field.requestFocusInWindow();
	}
	
	public void onNumBtn(int num){		
		field.setText(field.getText()+num);
	}
	
	public void onSqrt() {		
			solver.sqrt();
			appendActString("sqrt:");			
			field.setText("");
	}

	public void onDiv() {
		try{
			solver.putNumber(Double.parseDouble(field.getText()));
			solver.divide();
			appendActString(field.getText() + "/");
			field.setText("");
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Wrong data in input field!");
			ex.printStackTrace();
		}
	}

	public void onMult() {
		try{
			solver.putNumber(Double.parseDouble(field.getText()));
			solver.mult();
			appendActString(field.getText() + "*");
			field.setText("");
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Wrong data in input field!");
			ex.printStackTrace();
		}
	}

	public void onSub() {
		if(!solver.lastIsNumber()){
			String str = field.getText();
			if(str.length() == 0){
				field.setText(str+"-");
				field.requestFocusInWindow();
				return;
			}			
		}
		try{
			solver.putNumber(Double.parseDouble(field.getText()));
			solver.subtract();
			appendActString(field.getText() + "-");
			field.setText("");
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Wrong data in input field!");
			ex.printStackTrace();
		}
	}

	public void onAdd() {
		try{
			solver.putNumber(Double.parseDouble(field.getText()));
			solver.add();
			appendActString(field.getText() + "+");
			field.setText("");			
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Wrong data in input field!");
			ex.printStackTrace();
		}
	}
	
	public void onClear(){
		field.setText("");
		solver.reset();
	}

	public void onEqual(){
		try{
			solver.putNumber(Double.parseDouble(field.getText()));
			appendActString(field.getText());
			double result = solver.getResult();
			if(result % 1 == 0){
				field.setText((long)result+"");
			}else{
				field.setText(result+"");
			}			
			solver.reset();
			appendActString("="+field.getText());
			logger.log(currentAct.toString());
			appendActString("\n");
			currentAct = new StringBuilder();
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Wrong data in input field!");
			ex.printStackTrace();
		}
		
		if(solver.getCache().getSecondCache().size() > MAX_SECOND_CACHE_SIZE){
			new Thread(new Runnable() {				
				@Override
				public void run() {
					solver.getCache().getSecondCache().clear();
					System.out.println("Second cache is clear");
				}
			}).start();
		}
	}	
	
	private void appendActString(String str){
		currentAct.append(str);
		stackVw.pushString(str);
	}
	
}
