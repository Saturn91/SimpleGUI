package com.saturn91.simple.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public abstract class GUI extends JFrame{

	private static final long serialVersionUID = -1613166043727267630L;
	private JTextPane textOutput;
	private StringBuilder textBuffer;
	private JPanel panel;
	private JPanel background;
	private JScrollPane scrollPane;

	private boolean fullscreen = false;
	private boolean centered = false;
	
	private FontMetrics fontMetrics;

	public static final int default_border = 10;
	public static final Color background1 = new Color(0.15f, 0.5f, 0.15f);
	public static final Color background2 = new Color(0.3f, 0.6f, 0.3f);

	public GUI(String title, String[] btnNames, boolean fullscreen, boolean centered){
		this.centered = centered;
		init(title, btnNames, fullscreen);
	}
	
	public GUI(String title, String[] btnNames) {
		init(title, btnNames, false);
	}
	
	private void init(String title, String[] btnNames, boolean fullscreen) {
		this.setTitle(title);
		this.setLayout(null);
		if(fullscreen) {
			this.fullscreen = true;
			super.setExtendedState(JFrame.MAXIMIZED_BOTH);
			super.setUndecorated(true);
			super.setResizable(false);
		}else{
			super.setSize(750, 420);
		}

		this.setLocationRelativeTo(null);
		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {

			}

			@Override
			public void componentResized(ComponentEvent e) {
				setBounds();	
				setLocationRelativeTo(null);
			}

			@Override
			public void componentMoved(ComponentEvent e) {

			}

			@Override
			public void componentHidden(ComponentEvent e) {

			}
		});

		initTextArea();
		initButtons(btnNames);

		background = new JPanel();		
		background.setBackground(background2);
		background.setBounds(0, 0, getWidth(), getHeight());
		add(background);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				guiKeyTyped(e);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				guiKeyReleased(e);				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				guiKeyTyped(e);				
			}
		});
		this.setVisible(true);		
		setBounds();
	}

	private void initTextArea() {
		textOutput = new JTextPane();
		
		if(centered) {
			StyledDocument doc = textOutput.getStyledDocument();
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, doc.getLength(), center, false);
		}

		textOutput.setForeground(Color.GREEN);
		textOutput.setBackground(background1);

		textOutput.setEditable(false);
		textOutput.setFont(new Font("Helvetica", Font.PLAIN, 18));
		fontMetrics = textOutput.getFontMetrics(textOutput.getFont());
		scrollPane = new JScrollPane(textOutput);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(default_border, default_border, getWidth()-2*default_border-10, getHeight()-2*default_border-getHeight()*1/4);
		add(scrollPane);

		textBuffer = new StringBuilder();
	}

	private void initButtons(String[] btnNames) {
		
		Component[] toRemove = null;
		
		if(panel != null) {
			toRemove = panel.getComponents();
		}else {
			panel = new JPanel();
			add(panel);
		}
		
		panel.setBackground(background2);
		panel.setBounds(default_border,  2*default_border + (getHeight()-2*default_border-getHeight()*1/4), getWidth()-2*default_border-10, getHeight()*1/4);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));

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
		
		if(fullscreen) {
			JButton btn = new JButton("Close");
			btn.setBackground(background1);
			btn.setForeground(Color.YELLOW);
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(1);					
				}
			});

			panel.add(btn);
		}	
		
		if(toRemove != null) {
			for(int i = 0; i < toRemove.length; i++) {
				panel.remove(toRemove[i]);
			}
		}
		panel.revalidate();
		repaint();
	}

	private void setBounds() {
		panel.setBounds(default_border,  2*default_border + (getHeight()-2*default_border-getHeight()*1/4), getWidth()-2*default_border-10, getHeight()*1/4);
		background.setBounds(0, 0, getWidth(), getHeight());
		scrollPane.setBounds(default_border, default_border, getWidth()-2*default_border-10, getHeight()-2*default_border-getHeight()*1/4);
	}

	public void addText(String text) {
		textBuffer.append(text);
		redrawText();
	}

	public void setText(String text) {
		textBuffer.setLength(0);
		textBuffer.append(text);
		redrawText();
	}
	
	public void redrawText() {
		StringBuilder linedText = new StringBuilder();
		StringBuilder temp = new StringBuilder(textBuffer.toString());
		for(final Character c : textBuffer.toString().toCharArray()) {
		    if(fontMetrics.stringWidth(temp.toString()) > scrollPane.getWidth()-1) { 
		    	temp.setLength(temp.length() - 1); 
		    	linedText.append(temp.toString()+ "\n"); 
		        temp = new StringBuilder(c.toString()); 
		    }
		}
		linedText.append(temp.toString()); 
		textOutput.setText(linedText.toString().substring(0, linedText.length()-1));
	}
	
	public void addLine(String line) {
		addText(line + "\n");
	}

	public void setSize(int width, int height) {
		super.setSize(width, height);
		setBounds();
	}
	
	public void setBtnList(String[] btnNames) {
		initButtons(btnNames);
	}

	protected abstract void onButton(String btnName);
	
	public void setCentered(boolean centered) {
		this.centered = centered;
		initTextArea();
	}
	
	public abstract void guiKeyTyped(KeyEvent e);
	
	public abstract void guiKeyReleased(KeyEvent e);
	
	public abstract void guiKeyPressed(KeyEvent e);
}
