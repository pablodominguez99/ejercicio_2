package ej2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Calendar;
import java.util.Date;
public class Tablon {

	Calendar c = Calendar.getInstance();				//FORMATO FECHA
	Date horaActual = c.getTime();
	
	private ArrayList<Anuncio> anuncios;
	
	
	private static Tablon tab = null;
	
	
	public static Tablon getInstance() {
		
			if(tab == null) {
				tab = new Tablon();
			}
		
			return tab;
		
	}


	public ArrayList<Anuncio> getAnuncios() {
		return anuncios;
	}


	public void setAnuncios(ArrayList<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
	
	public void determinarEstado(Date fechaInicio){
		
		Date fechaActual= new Date();
		String estado;
		
		if(fechaInicio.after(fechaActual)) {
			estado="publicado";
		}
		
	}
	public void mostrarAnuncio(Anuncio a) {
		
		
		
		System.out.println("\nTitulo            : "+a.getTitulo());
		System.out.println("Cuerpo            : "+a.getCuerpo());
		System.out.println("Propietario       : "+a.getPropietario());
		System.out.println("Fecha Publicación : "+a.getFechainicio());


		
		
		System.out.println("\n------------------------------------");

		
		
	}
	
	public void getInfoAnuncio(Anuncio a) {
		
		
	
		System.out.println("\nTitulo            : "+a.getTitulo());
		System.out.println("Id                : "+ a.getId());
		System.out.println("Tipo              : "+ a.getTipo());
		System.out.println("Cuerpo            : "+a.getCuerpo());
		System.out.println("Propietario       : "+a.getPropietario());
		System.out.println("Fecha Publicación : "+a.getFechainicio());
		System.out.println("Estado del anuncio: "+a.getEstado());

		
		
		System.out.println("\n------------------------------------");

		
		
	}
/*
public String getRuta() {
		
		File file = new File("C:\\Users\\w10\\git\\repository\\Ej2\\src\\ej2\\fichero");
		String propertiesPath = file.getAbsolutePath();
		
		Properties appProperties = new Properties();
		
		try {
			appProperties.load(new FileInputStream(propertiesPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String ruta = appProperties.getProperty("ruta");
		
		
		return ruta;
		
		
	}
	*/
	/*
public void leerDeFichero(String ruta) {	
	
	try {
		FileReader fr;
		fr = new FileReader(ruta);
		BufferedReader br = new BufferedReader(fr);
		String cadena;
		String nombre = "",apellidos = "",email = "",fechaN = "";
		ArrayList<String> intereses =  new ArrayList<String>();
		
		while((cadena = br.readLine())!=null) {
		
				nombre = cadena;
				cadena = br.readLine();
				apellidos = cadena;
				cadena = br.readLine();
				email = cadena;
				cadena = br.readLine();
				fechaN = cadena;
				cadena = br.readLine();
				ArrayList<String> aux = new ArrayList<String>(devolver_array(cadena));
				
				
				crearContacto(nombre,apellidos,email,fechaN,aux);
				
				intereses.clear();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

*/

/*
public void escribirEnFichero() {
	
	String titulo = "";
	String tipo = "";
	String propietario = "";
	String destinatarios = "";
	String fechaInicio = "";
	String fechaFin = "";
	ArrayList<String> intereses = new ArrayList<String>();
	
	
	String rutaFichero = getRuta();
	
	
	
	try {
		FileWriter fichero = new FileWriter(rutaFichero,false);
		PrintWriter pw = new PrintWriter(fichero);
		for(int i=0;i<getAnuncios().size();i++) {
			titulo = getAnuncios().get(i).getTitulo();
			tipo = getAnuncios().get(i).getTipo();
			propietario = getAnuncios().get(i).getPropietario();	
			for(int j=0;j<getAnuncios().get(i).getDestinatarios().size();j++) {
				for(int k=0;j<getAnuncios().get(i).getDestinatarios().size();k++) {
					if(j==getAnuncios().get(j).getDestinatarios().size()-1) {
						destinatarios = destinatarios + getAnuncios().get(j).getDestinatarios().get(k)+".";
					}else {
						destinatarios = destinatarios + getAnuncios().get(j).getDestinatarios().get(k)+",";
					}
				}
			}
			
			intereses = getAnuncios().get(i).getIntereses();
			
			pw.println(titulo);
			pw.println(tipo);
			pw.println(propietario);
			pw.println(destinatarios);
			pw.println();
			for(int j=0;j<intereses.size();j++) {
				if(j== intereses.size()-1) {
					 pw.print(intereses.get(j)+"\n");
				}else {
					 pw.print(intereses.get(j) + ",");
				}
				
			}
			
		}
		pw.close();
		fichero.close();
		
		
		
		
		
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}
	*/
}
