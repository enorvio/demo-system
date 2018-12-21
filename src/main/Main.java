package main;

import java.io.FileNotFoundException;
import GUI.graphic;

public class Main {

	public static void main(String args[]) {
		new graphic();
		try {
			xmlReader.Reader reader = new xmlReader.Reader("src/invoices/invoices1.xml");
			reader.createTree("Invoices");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
