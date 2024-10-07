package ejercicios;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

public class E3 {

	public static void main(String[] args) {
		File f1 = new File(System.getProperty("user.home") + "\\AD", "refranes.txt");
		File f2 = new File(System.getProperty("user.home") + "\\AD\\copias");

		copyTextFile(f1, f2, false); // Bien a la primera, falla a la segunda
		copyTextFile(f1, f2, true); // Bien
		copyTextFile(f1, f1, true); // Cuidado, Falla
		copyTextFile(f1, f2, false); // Falla
		copyTextFile(f2, f1, false); // Falla

	}

	public static void copyTextFile(File in, File out, boolean flag) {
		if (!in.equals(out)) {
			if (!in.isDirectory()) {
				if (out.isFile()) {
					if (flag) {
						copyTextFile(in, out);
					} else {
						System.out.println("No tiene permiso para sobreescribir " + out.getName());
					}
				} else if (out.isDirectory()) {
					FilenameFilter txtFilter = new FilenameFilter() {
						@Override
						public boolean accept(File dir, String name) {

							return name.equals(in.getName());
						}
					};
					String[] list = out.list(txtFilter);
					if (list.length == 0 || flag) {
						File aux = new File(
								out.getAbsolutePath() + System.getProperty("file.separator") + in.getName());
						copyTextFile(in, aux);
					} else {
						System.out.println(
								"No tiene permiso para sobreescribir " + in.getName() + " en " + out.getName());
					}
				}
			} else {
				System.out.println("El archivo de origen no puede ser un directorio.");
			}
		} else {
			System.out.println("El archivo de origen y de destino no puede ser el mismo.");
		}
	}

	private static void copyTextFile(File f1, File f2) {
		try {
			f2.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (f1.canRead() && f2.canWrite()) {
			FileReader fr = null;
			FileWriter fw = null;
			try {
				fr = new FileReader(f1);
				fw = new FileWriter(f2);
				int i;
				do {
					i = fr.read();
					fw.write(i);
				} while (i != -1);
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
