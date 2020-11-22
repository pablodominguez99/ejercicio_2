package ej2;

import java.util.ArrayList;


public class AnuncioIndividualizado extends Anuncio {
	
	GestorContactos g = GestorContactos.getInstance();
	
	public AnuncioIndividualizado() {
		
	}
	
	public ArrayList<Contacto> returnTarget(String nombre){
		
		ArrayList<Contacto> ret = new ArrayList<Contacto>();
		
		for(int i=0;i<g.getContactos().size();i++) {
			if(g.getContactos().get(i).getNombre().equals(nombre)) {
				ret.add(g.getContactos().get(i));
			}
		}
		return ret;
	}
	
	
}
