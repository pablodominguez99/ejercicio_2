package ej2;

import java.util.ArrayList;

public class AnuncioTematico extends Anuncio {
	
	GestorContactos g = GestorContactos.getInstance();
	
	
	public AnuncioTematico() {
		super();
	}
	

	public ArrayList<Contacto> contactos_con_target(String interes){
		
		ArrayList<Contacto> ret = new ArrayList<Contacto>();
		
		for(int i=0;i<g.getContactos().size();i++) {
			for(int j=0;j<g.getContactos().get(i).getIntereses().size();j++) {
				if(g.getContactos().get(i).getIntereses().get(j).equals(interes)) {
					ret.add(g.getContactos().get(i));
				}
			}
		}
		
		
		return ret;
	}
	
}
