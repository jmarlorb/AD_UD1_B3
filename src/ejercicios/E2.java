package ejercicios;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class E2 {
	
	public static void main(String[] args) {
		File f1 = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "AD"
				+ System.getProperty("file.separator") + "refranes.txt");
		Scanner scan = new Scanner(System.in);
		System.out.println("Introduzca una vocal.");
		String c = scan.nextLine();
		c = c.substring(0,1);
		while (!esVocal(c.toCharArray()[0])) {
			System.out.println("Introduzca una vocal.");
			c = scan.nextLine();
			c = c.substring(0,1);
		}
		File f2 = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "AD"
				+ System.getProperty("file.separator") + "refranes_CON_"+c+".txt");
		try {
			f2.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		copiaConVocal(f1,f2,c.toCharArray()[0]);
		scan.close();
	}
	
	private static boolean esVocal(char input) {
        char lowerCaseInput = Character.toLowerCase(input);
        // Check for standard vowels (a, e, i, o, u) and accented vowels
        switch (lowerCaseInput) {
            case 'a': case 'e': case 'i': case 'o': case 'u':
            case 'á': case 'é': case 'í': case 'ó': case 'ú':  // Acute accents
            case 'à': case 'è': case 'ì': case 'ò': case 'ù':  // Grave accents
            case 'â': case 'ê': case 'î': case 'ô': case 'û':  // Circumflex
            case 'ã': case 'õ':  // Tilde (Portuguese)
            case 'ä': case 'ë': case 'ï': case 'ö': case 'ü':  // Diaeresis (Umlaut)
            case 'å':  // Scandinavian 'å'
            case 'æ':  // Ligature ae
                return true;
            default:
                return false;
        }
    }
	
	private static void copiaConVocal(File f1, File f2, char vocal) {
		if (f1.canRead() && f2.canWrite()) {
			FileReader fr = null;
			FileWriter fw = null;
			try {
				fr = new FileReader(f1);
				fw = new FileWriter(f2);
				int i;
				do {
					i = fr.read();
					if (esVocal((char)i)) {
						fw.write(vocal);
					} else {
						fw.write(i);
					}
				} while(i!=-1);
				fr.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fr.close();
					fw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
