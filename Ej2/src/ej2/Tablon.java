package ej2;

import java.util.ArrayList;

public class Tablon {

	
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

	
	public String getInfoAnuncio(Anuncio a) {
		String cadena;
		
		cadena = "Titulo : "+a.getTitulo()+"Propietario :"+a.getPropietario()+"Cuerpo : "+a.getCuerpo();
		
		return cadena;
		
	}
	
	
	
}
