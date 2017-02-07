package ru.ankanashov.calc.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ru.ankanashov.calc.BaseSolver;
import ru.ankanashov.calc.login.Logger;

public class CalcFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static final int BTN_SIZE = 50;
	private static final int BTN_FONT_SIZE = 20;
	private static final int BTN_COUNT = 16;
	
	//private BaseSolver solver;
	private CalcEvents events;
	
	private JTextField tfInp;
	private JButton btnEq;
	private JButton btnClear;
	private StackView stackVw;
	
	private JButton[] buttons;
	
	public CalcFrame(BaseSolver solver, Logger logger){
		super("Calculator v2.0");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (InstantiationException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {			
			e.printStackTrace();
		}
		
		JPanel center = new JPanel();
		JScrollPane right = new JScrollPane();
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));		
		
		buttons = new JButton[BTN_COUNT];
			
		Box box = new Box(BoxLayout.X_AXIS);
		JPanel p2 = new JPanel(new GridLayout(4, 4));
		
		tfInp = new JTextField();		
		tfInp.setFont(new Font("arial", 0, 18));
		tfInp.setPreferredSize(new Dimension(100, 30));
		btnEq = new JButton("=");
		btnEq.setFont(new Font("arial", 0, BTN_FONT_SIZE));
		btnEq.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				events.onEqual();
			}
		});
		
		btnClear = new JButton("C");
		btnClear.setFont(new Font("arial", 0, BTN_FONT_SIZE));
		btnClear.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				
				events.onClear();
			}
		});
		
		stackVw = new StackView();
		//stackVw.setPreferredSize(new Dimension(200, 20+BTN_SIZE*4));
		stackVw.setFont(new Font("arial", 0, 14));
		
		addKeyboardEvents();
		
		box.add(tfInp);
		box.add(btnEq);
		box.add(btnClear);
		
		initButtons();
		
		events = new CalcEvents(tfInp, solver, logger, stackVw);
		
		for(JButton b : buttons){
			b.addActionListener(new ButtonActionListener(events));
			
			b.setPreferredSize(new Dimension(BTN_SIZE, BTN_SIZE));
			b.setFont(new Font("arial", 0, BTN_FONT_SIZE));
			p2.add(b);		
		}
		
		center.add(box);
		center.add(p2);
		
		right.setPreferredSize(new Dimension(200, 20+BTN_SIZE*4));
		right.setViewportView(stackVw);		
		
		getContentPane().add(center);
		getContentPane().add(right);
		
		pack();
		
		setVisible(true);
	}
	
	private void addKeyboardEvents(){
		tfInp.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				switch(c){
					case '+':
						events.onAdd(); e.consume(); break;
					case '-':
						events.onSub(); e.consume(); break;
					case '/':
						events.onDiv(); e.consume(); break;
					case '=':
						events.onEqual(); e.consume(); break;
					case '*':
						events.onMult(); e.consume(); break;
					case '.':
						events.onDotBtn(); e.consume(); break;
					default:
						//input numbers only						
					    if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || 
					    		(c == KeyEvent.VK_DELETE) || (c == '.'))) {
					        getToolkit().beep();
					        e.consume();
					    }
					    break;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
	}
	
	private void initButtons(){
		//https://en.wikipedia.org/wiki/List_of_Unicode_characters
		char sqrtChar = '\u221a';
		
		buttons[0] = new JButton("1");
		buttons[1] = new JButton("2");
		buttons[2] = new JButton("3");
		buttons[3] = new JButton("+");
		
		buttons[4] = new JButton("4");
		buttons[5] = new JButton("5");
		buttons[6] = new JButton("6");
		buttons[7] = new JButton("-");
		
		buttons[8] = new JButton("7");
		buttons[9] = new JButton("8");
		buttons[10] = new JButton("9");
		buttons[11] = new JButton("*");
		
		buttons[12] = new JButton(Character.toString(sqrtChar));
		buttons[13] = new JButton("0");
		buttons[14] = new JButton(".");
		buttons[15] = new JButton("/");		
	}
	
}
