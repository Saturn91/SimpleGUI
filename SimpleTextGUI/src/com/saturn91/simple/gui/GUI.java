package com.saturn91.simple.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public abstract class GUI extends JFrame{
	
	private static final long serialVersionUID = -1613166043727267630L;
	private JTextArea textOutput;
	private StringBuilder textBuffer;
	
	public static final int default_border = 10;
	public static final Color background1 = new Color(0.15f, 0.5f, 0.15f);
	public static final Color background2 = new Color(0.3f, 0.6f, 0.3f);
	
	public GUI(String title, String[] btnNames){
		this.setTitle(title);
		this.setLayout(null);
		this.setSize(750, 420);
		this.setLocation(50, 50);
		//TODO set location allignement center
		this.setResizable(false);
		
		
		
		initTextArea();
		initButtons(btnNames);
		
		JPanel background = new JPanel();		
		background.setBackground(background2);
		background.setBounds(0, 0, getWidth(), getHeight());
		add(background);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);		
	}
	
	private void initTextArea() {
		textOutput = new JTextArea();
		
		textOutput.setForeground(Color.GREEN);
		textOutput.setBackground(background1);
		
		textOutput.setEditable(false);
		textOutput.setFont(new Font("Helvetica", Font.PLAIN, 18));
		
		JScrollPane scrollPane = new JScrollPane(textOutput,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(default_border, default_border, getWidth()-2*default_border-10, getHeight()-2*default_border-100);
		add(scrollPane);
		
		textBuffer = new StringBuilder();
	}
	
	private void initButtons(String[] btnNames) {
		JPanel panel = new JPanel();
		panel.setBackground(background2);
		panel.setBounds(default_border,  2*default_border + (getHeight()-2*default_border-100), getWidth()-2*default_border-10, 100);
		panel.setLayout(new FlowLayout());
		
		for(int i = 0; i < btnNames.length; i++) {
			final JButton btn = new JButton(btnNames[i]);	
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onButton(btn.getText());
				}
			});
			btn.setBackground(background1);
			btn.setForeground(Color.GREEN);
			
			panel.add(btn);
		}
		
		
		
		add(panel);
	}
	
	public void addText(String text) {
		textBuffer.append(text);
		textOutput.setText(textBuffer.toString());
	}
	
	public void setText(String text) {
		textBuffer.setLength(0);
		textBuffer.append(text);
		textOutput.setText(text);
	}

	protected abstract void onButton(String btnName);
	
}
