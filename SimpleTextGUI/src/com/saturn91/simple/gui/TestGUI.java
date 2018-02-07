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
		gui.addLine("Es ist ein paradiesmatisches Land, in dem einem gebratene Satzteile in den Mund fliegen. Nicht einmal von der allm�chtigen Interpunktion werden die Blindtexte beherrscht � ein geradezu unorthographisches Leben. Eines Tages aber beschlo� eine kleine Zeile Blindtext, ihr Name war Lorem Ipsum, hinaus zu gehen in die weite Grammatik. Der gro�e Oxmox riet ihr davon ab, da es dort wimmele von b�sen Kommata, wilden Fragezeichen und hinterh�ltigen Semikoli, doch das Blindtextchen lie� sich nicht beirren. Es packte seine sieben Versalien, schob sich sein Initial in den G�rtel und machte sich auf den Weg. Als es die ersten H�gel des Kursivgebirges erklommen hatte, warf es einen letzten Blick zur�ck auf die Skyline seiner Heimatstadt Buchstabhausen, die Headline von Alphabetdorf und die Subline seiner eigenen Stra�e, der Zeilengasse. Wehm�tig lief ihm eine rethorische Frage �ber die Wange, dann setzte es seinen Weg fort. Unterwegs traf es eine Copy. Die Copy warnte das Blindtextchen, da, wo sie herk�me w�re sie zigmal umgeschrieben worden und alles, was von ihrem Ursprung noch �brig w�re, sei das Wort �und� und das Blindtextchen solle umkehren und wieder in sein eigenes, sicheres Land zur�ckkehren.");
	}
}
