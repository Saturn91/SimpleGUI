package com.saturn91.simple.gui;

public class TestGUI {

	public static void main(String[] args) {
		String[] btnNames = {"OK", "v", "^", "Option 1", "Option2"};
		new GUI("Hello", btnNames) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onButton(String btnName) {
				handleBtn(btnName);						
			}

			private void handleBtn(String btnName) {
				addText("Btn " + btnName + " was pressed!\n");
			}
		};
	}
}
