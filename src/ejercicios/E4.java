package ejercicios;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;

public class E4 {

	public static void main(String[] args) {
		File f = new File(System.getProperty("user.home")+System.getProperty("file.separator")+"AD"+System.getProperty("file.separator")+"misPropiedades.props");
		Scanner scanner = new Scanner(System.in);
		int res;
		do {
			System.out.println("1. Mostrar Propiedades.");
			System.out.println("2. Introducir Propiedad.");
			System.out.println("3. Eliminar Propiedad.");
			System.out.println("4. Salir.");
			System.out.println("Introduzca la opción deseada.");
			res = scanner.nextInt();
			while(res<1 || res > 4) {
				System.out.println("Introduzca una opción valida.");
				res = scanner.nextInt();
			}
			switch(res) {
			case 1:
				mostrarPropiedades(f);
				break;
			case 2:
				introducirPropiedad(f);
				break;
			case 3:
				eliminarPropiedad(f);
				break;
			default:
			}
		} while (res!=4);
		scanner.close();
	}
	
	private static Properties cargar(File f) {
		if (f.exists() && f.canRead()) {
			FileReader fr = null;
			try {
				fr = new FileReader(f);
				Properties auxContactos = new Properties();
				auxContactos.load(fr);
				System.out.println("Lectura completada con exito.");
				return auxContactos;
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}finally {

				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		System.out.println("Lectura fallida, generando nuevas Propiedades.");
		return new Properties();
		}
	
	private static void guardar(Properties a, File f) {
		FileWriter fw = null;
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (f.canWrite()) {
		try {
			fw = new FileWriter(f);
			a.store(fw,"Fichero de Configuración");
			fw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			System.out.println("Escritura completada con exito");
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		} else {
			System.out.println("No se pudo escribir.");
		}
	}
	
	public static void mostrarPropiedades(File f) {
		Properties p = cargar(f);
		if (!p.isEmpty()) {
			Iterator<Entry<Object,Object>> iterator= p.entrySet().iterator();
			Entry<Object,Object> aux;
			while (iterator.hasNext()) {
				aux = iterator.next();
				System.out.println(aux.getKey().toString()+" = "+aux.getValue().toString());
			}
		}
	}
	
	public static void introducirPropiedad(File f) {
		Properties p = cargar(f);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String clave;
		String valor;
		System.out.println("Introduzca el nombre de la propiedad.");
		clave = scan.nextLine();
		System.out.println("Introduzca el valor de la propiedad.");
		valor = scan.nextLine();
		//scan.close() no es necesario porque se cerrara System.in en el main.
		p.put(clave, valor);
		guardar(p,f);
	}
	
	public static void eliminarPropiedad(File f) {
		Properties p = cargar(f);
		if (!p.isEmpty()) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			String clave;
			System.out.println("Introduzca el nombre de la propiedad.");
			clave = scan.nextLine();
			//scan.close(); no es necesario porque se cerrara System.in en el main.
			if(p.remove(clave)==null) {
				System.out.println("La propiedad no existía");
			} else {
				System.out.println("La propiedad ha sido borrada.");
			}
		}
		guardar(p,f);
	}
	
	}

