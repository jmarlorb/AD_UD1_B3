package ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class E1 {

	public static void main(String[] args) {
		String dir = System.getProperty("user.dir");
		String fileName = "sentences.txt";
		File f = new File(dir, fileName);
		if (!f.canRead()) {
			f = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "AD"
					+ System.getProperty("file.separator") + "refranes.txt");
		}
		leerFileReader(f);
		System.out.println();
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		leerBufferedReader(f);
	}

	private static void leerFileReader(File f) {
		FileReader fr = null;
		try {
			fr = new FileReader(f);
			int i;
			do {
				i = fr.read();
				System.out.print((char) i);
			} while (i != -1);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void leerBufferedReader(File f) {
		FileReader fr = null;
		BufferedReader bufferR = null;
		try {
			fr = new FileReader(f);
			bufferR = new BufferedReader(fr);
			String linea;
			do{
				linea = bufferR.readLine();
				if (linea!=null) System.out.println(linea);
			} while (linea!=null);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
