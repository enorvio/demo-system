package backend.MLcompiler;

import java.io.*;

public class MLcompiler {

	public void runSML(String commands) {
		try {
			String[] cmdArray = new String[3];
			cmdArray[0] = "sml.bat";
			cmdArray[1] = "newnewdemo.sml";
			cmdArray[2] = commands;
			System.out.println("Executing SML and opening snippets.sml");
			//Process p = Runtime.getRuntime().exec(cmdArray);
			Process p = new ProcessBuilder(cmdArray).start();
			System.out.println("snippets should now open.");
			String line;
			BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((line = bri.readLine()) != null) {
				System.out.println(line);
			}
			bri.close();
			while ((line = bre.readLine()) != null) {
				System.out.println(line);
			}
			bre.close();
			p.waitFor();
			System.out.println("Done.");
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

}
