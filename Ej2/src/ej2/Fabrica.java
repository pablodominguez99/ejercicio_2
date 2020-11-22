package ej2;

public class Fabrica implements Ifactory {

	@Override
	public Anuncio getAnuncio(int i) {
		
		
		if(i==2) {
			return new AnuncioIndividualizado();
		}else if(i==4) {
			return new AnuncioFlash();
		}else if(i==3) {
			return new AnuncioTematico();
		}else if(i==1){
			return new Anuncio();
		}else {
			System.out.println("Tipo de anuncio no valido.");
		}
		
		return null;
		
	}
	
}
