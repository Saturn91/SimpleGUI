package com.saturn91.simple.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class TestGUI {

	public static void main(String[] args) {
		String[] btnNames = {"OK", "v", "^", "Option 1", "Option2"};
		String[] btnNames2 = {"Hello", "Back"};
		GUI gui = new GUI("Hello", btnNames, true, false) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onButton(String btnName) {
				handleBtn(btnName);						
			}

			private void handleBtn(String btnName) {
				addText("Btn " + btnName + " was pressed!\n");
				if(btnName.equals("Option 1")) {
					setBtnList(btnNames2);
				}
				if(btnName.equals("Back")) {
					setBtnList(btnNames);
				}
			}

			@Override
			public void guiKeyTyped(KeyEvent e) {
				System.out.println("yep1!");
				addLine("yep1");
			}

			@Override
			public void guiKeyReleased(KeyEvent e) {
				System.out.println("yep2!");
				addLine("yep2");
			}

			@Override
			public void guiKeyPressed(KeyEvent e) {
				System.out.println("yep3!");
				addLine("yep3");
			}
		};	
		
		gui.setCentered(true);
		
	}
}
