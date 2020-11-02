package ej2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		int id = 1;
		Scanner sn = new Scanner(System.in);
		Scanner teclado = new Scanner(System.in);
		int op;
		String email;
		Calendar c = Calendar.getInstance();
		Date horaActual = c.getTime();
		
		GestorContactos g = GestorContactos.getInstance();
		Tablon t = Tablon.getInstance();
		
		String ruta;
		ruta = g.getRuta();
		g.leerDeFichero(ruta);
		
		
		
		System.out.println("Menu Anuncios");
		System.out.println("");
		System.out.println("1.Iniciar Sesion.");
		System.out.println("2.Registrarse.");
		op = sn.nextInt();
		
		if(op == 1) {
			System.out.println("Introducir email : ");
			email = sn.nextLine();
			for(int i=0;i<g.getContactos().size();i++) {
				if(g.getContactos().get(i).getEmail().equals(email)) {
					System.out.println("Bienvenido "+g.getContactos().get(i).getNombre());
					System.out.println("");
					System.out.println("1.Crear Anuncio");
					System.out.println("2.Mostrar Anuncios");
					System.out.println("Introducir opcion  :");
					int op2 = sn.nextInt();
					if(op2 != 1 && op2 != 2) {
						System.out.println("Opcion no valida");
					}else if(op2 == 1) {
						System.out.println("1.Anuncio General.");
						System.out.println("2.Anuncio Individualizado.");
						System.out.println("3.Anuncio Tematico");
						System.out.println("4.Anuncio Flash");
						int op3 = sn.nextInt();
						switch(op2) {
						case 1:
							Fabrica f = new Fabrica();
							Scanner sc1 = new Scanner(System.in);
							String titulo;
							String cuerpo;
							
							Anuncio a = f.getAnuncio("General");
							a.setId(id);
							a.setTipo("General");
							a.setDestinatarios(g.getContactos());
							a.setPropietario(g.getContacto(email));
							a.setIntereses(g.getInteresesValidos());
							System.out.println("Introducir titulo del anuncio : ");
							titulo = sc1.nextLine();
							System.out.println("Introducir cuerpo del anuncio : ");
							cuerpo = sc1.nextLine();
							a.setTitulo(titulo);
							a.setCuerpo(cuerpo);
							a.setFechainicio(horaActual);
							
							
							
							
						}
					}else {
						ArrayList<Anuncio> a = new ArrayList<Anuncio>(t.getAnuncios());
						String cadena;
						for(int j=0;j<a.size();j++) {
							if(a.get(j).getTipo().equalsIgnoreCase("individualizado") && a.get(j).getDestinatarios().contains(g.getContacto(email))) {
								String cadena2 = t.getInfoAnuncio(a.get(j));
								System.out.println(cadena2);
							}
							if(a.get(j).getTipo().equals("flash"){
								System.out.println("....");
							}
							
							if(a.get(j).getTipo().equalsIgnoreCase("tematico") && g.getContacto(email).getIntereses().contains(intereses)) {
								System.out.println(t.getInfoAnuncio(a.get(j)));
							}
							cadena = t.getInfoAnuncio(a.get(j));
							System.out.println(cadena);
						}
					}
				}else {
					System.out.println("No se ha encontrado el contacto");
				}
			}
			
		}else if(op == 2){
			System.out.println("Introduce email por favor : \n");
			String contactoEmail= teclado.nextLine();
												
			while(g.existeContacto(contactoEmail)!= 0) {
				
				System.out.println("Introduce de nuevo un email por favor : \n");
				contactoEmail= teclado.nextLine();
			}
			
			
			Scanner tec = new Scanner(System.in);
			boolean out = false;
			System.out.println( "Introduce nombre por favor : \n");
			
			String nombre = tec.nextLine();
			
			System.out.println( "Introduce apellidos por favor : \n");
			
			String apellidos = tec.nextLine();
			
			System.out.println("Introduce la fecha de nacimiento :    ");
			System.out.println("(Por favor sigua el formato DD/MM/AAAA)\n");
			String fechaN = tec.nextLine();
			
			while(!g.validarFecha(fechaN)) {
				System.out.println("\nFecha no válida");
				System.out.println("Introduce la fecha de nacimiento :    ");
				System.out.println("(Por favor sigua el formato DD/MM/AAAA)\n");
				fechaN = tec.nextLine();
			}
			
			ArrayList<String> aux = new ArrayList<String>();
			while(!out) {
				System.out.println("Introduzca sus intereses");
				System.out.println("Escriba alguno de los siguientes : \n");
				System.out.println("Pintura    Música    Deporte  \n");
				System.out.println("Pesca      Cine      Fotografía  \n");
				System.out.println("Viajes     Tecnología \n");
				
				String interes = tec.nextLine();
				if(!g.validarElemento(interes)) {
					System.out.println("Interes no válido.\n");
				}else {
					aux.add(interes);
					
					System.out.println("¿Desea añadir otro interes?");
					System.out.println("1.Si");
					System.out.println("2.No");
					int op2 = tec.nextInt();
					
					if(op2 == 2) {
						out = true;
					} 
					
					if(op2 == 1) {
						System.out.println("Contacto añadido.");
					}
					
					if(op2 != 1 && op2 != 2) {
						System.out.println("Opcion no valida.");
					}
						
				}
					
			}	
			
			
			g.crearContacto(nombre,apellidos,contactoEmail,fechaN,aux);
			
			GestorContactos.press_any_key_to_continue();
			GestorContactos.clearConsole();
			
		}else {
			System.out.println("Opcion no valida.");
		}
	}
}
