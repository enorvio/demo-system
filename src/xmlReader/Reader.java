package xmlReader;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import tree.*;

public class Reader {
	Scanner sc;
	String filename;
	
	public Reader(String filename) throws FileNotFoundException{
		this.filename = filename;
		this.sc = new Scanner(new File(filename));
	}

	public String[] getNextLine() {
		String s = this.sc.next();
		if (this.sc.hasNext()) {
			while (s.charAt(s.length()-1) != '>') {
				s += ' ' + this.sc.next();
			}
				}
		return separate(s);
		}

	public String[] separate(String s) {
		int l = s.length();
		String k = "";
		int i = 1;
		String[] result = new String[2];
		while (s.charAt(i) != '>') {
			k += s.charAt(i);
			i++;
		}
		i += 1;
		if (i == l) {
			result[0] = k;
			result[1] = null;
			return result;
		}
		else {
			result[0] = k;
			String m = "";
			while (s.charAt(i) != '<') {
				m += s.charAt(i);
				i++;
			}
			result[1] = m;
			return result;
		}
	}
	
	public Tree createTree(String startTag) {
		Tree t = new Tree(this.getNextLine());
		this.fillTree(t.getRoot(), startTag);
		return t;
	}
	
	public void fillTree(Node n, String startTag) {
		while (this.sc.hasNext()) {
			String[] s = this.getNextLine();
			if (!(s[0].charAt(0) == '/')) {
				Node m = new Node(n, s);
				n.addChild(m);
				if (!(s[0].equals(startTag)) && s[1] == null) {
					fillTree(m, startTag);
				} 
			} else {
				break;
			}
		}
		
	}

}
