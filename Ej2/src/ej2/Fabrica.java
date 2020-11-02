package ej2;

public class Fabrica implements Ifactory {

	@Override
	public Anuncio getAnuncio(String tipo) {
		
		int id = 1;
		
		if(tipo.equalsIgnoreCase("INDIVIDUALIZADO")) {
			return new AnuncioIndividualizado();
		}else if(tipo.equalsIgnoreCase("FLASH")) {
			return new AnuncioFlash();
		}else if(tipo.equalsIgnoreCase("TEMATICO")) {
			return new AnuncioTematico();
		}else if(tipo.equalsIgnoreCase("GENERAL")){
			return new Anuncio();
		}else {
			System.out.println("Tipo de anuncio no valido.");
		}
		
	}
	
}
