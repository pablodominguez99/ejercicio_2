package ejercicio2;

import java.util.ArrayList;

import ejercicio1.Contacto;
import ejercicio1.GestorContactos;

public class AnuncioIndividualizado extends Anuncio {
	
	GestorContactos g = GestorContactos.getInstance();
	
	public AnuncioIndividualizado(int id,String titulo,Contacto propietario,ArrayList<Contacto> destinatarios,String cuerpo) {
		super(id,titulo,propietario,destinatarios,cuerpo);
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
