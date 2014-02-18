package com.digiarea.closurefx.editors;


public class EditorTest {

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.home"));
//		SublimtextEditor sublimtextEditor = new SublimtextEditor("/Applications/Sublime Text 2.app");
//		sublimtextEditor.setColumn("3");
//		sublimtextEditor.setFile("/Users/norb/Documents/test.js");
//		sublimtextEditor.setLine("3");
//		sublimtextEditor.load();
		
//		TextMateEditor textMateEditor = new TextMateEditor("/Users/norb/Documents/TextMate.app");
//		textMateEditor.setColumn("3");
//		textMateEditor.setFile("/Users/norb/Documents/test.js");
//		textMateEditor.setLine("3");
//		textMateEditor.load();
		
//		WebstormEditor webstormEditor = new WebstormEditor("/Applications/WebStorm.app");
//		webstormEditor.setColumn("3");
//		webstormEditor.setFile("/Users/norb/Documents/test.js");
//		webstormEditor.setLine("3");
//		webstormEditor.load();
		
		EmacsEditor emacsEditor = new EmacsEditor("/Applications/Emacs.app");
		emacsEditor.setColumn("3");
		emacsEditor.setFile("/Users/norb/Documents/test.js");
		emacsEditor.setLine("3");
		emacsEditor.load();
	}

}
