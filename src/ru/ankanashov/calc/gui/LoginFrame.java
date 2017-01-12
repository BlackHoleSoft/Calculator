package ru.ankanashov.calc.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ru.ankanashov.calc.BaseSolver;
import ru.ankanashov.calc.login.Authorization;
import ru.ankanashov.calc.login.Logger;

public class LoginFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private static final int COMPONENTS_GAP = 3;
	
	private JLabel lbLogin;
	private JLabel lbPwd;
	
	private JTextField tfLogin;
	private JPasswordField pwdFld;
	
	private JButton btnLogin;
	private JButton btnNew;
	
	public LoginFrame(BaseSolver solver){
		super("Enter login and password");
		
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
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		GridLayout layout = new GridLayout(3, 2);
		layout.setHgap(COMPONENTS_GAP);
		layout.setVgap(COMPONENTS_GAP);
		setLayout(layout);
		
		lbLogin = new JLabel(" Login (name):");
		lbPwd = new JLabel(" Password:");
		tfLogin = new JTextField();
		tfLogin.setPreferredSize(new Dimension(200, 20));
		pwdFld = new JPasswordField();
		
		btnLogin = new JButton("LogIn");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean logged = Authorization.logIn(tfLogin.getText(), pwdFld.getText());
				if(logged){
					new CalcFrame(solver, new Logger("log.txt", tfLogin.getText()));
					setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null, "Wrong login or password!");
				}
			}
		});
		
		btnNew = new JButton("Register");
		btnNew.addActionListener(new ActionListener() {			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {				
				Authorization.addUser(tfLogin.getText(), pwdFld.getText());
				JOptionPane.showMessageDialog(null, "You have been successfully registered");
			}
		});
		
		getContentPane().add(lbLogin);
		getContentPane().add(tfLogin);
		getContentPane().add(lbPwd);
		getContentPane().add(pwdFld);
		getContentPane().add(btnLogin);
		getContentPane().add(btnNew);
		
		pack();
		setVisible(true);
	}
	
}
