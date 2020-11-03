package ej2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		int id = 1;
		Scanner escan = new Scanner(System.in);
		Scanner sn = new Scanner(System.in);
		int op;
		String email;
		Calendar c = Calendar.getInstance();
		Date horaActual = c.getTime();
		boolean salir = false;
		boolean salir2 = false;
		ArrayList<Anuncio> an = new ArrayList<Anuncio>();
		
		GestorContactos g = GestorContactos.getInstance();
		Tablon t = Tablon.getInstance();
		
		ArrayList<String> InteresesAux= new ArrayList();
		
		String ruta;
		ruta = g.getRuta();
		g.leerDeFichero(ruta);
		
		boolean encontrado=false;
		
		Contacto actual=new Contacto();
		
		while(salir2 == false) {
			System.out.println(t.getAnuncios());
			System.out.println("Menu Anuncios");
			System.out.println("");
			System.out.println("1.Iniciar Sesion.");
			System.out.println("2.Registrarse.");
			System.out.println("3.Salir");
			op = escan.nextInt();
			//Aqui comienza iniciar sesion
			if(op == 1) {
				System.out.println("Introducir email : ");
				email = sn.nextLine();
				for(int i=0;i<g.getContactos().size();i++) {
					if(g.getContactos().get(i).getEmail().equals(email)) {
						 actual= g.getContactos().get(i);
						encontrado=true;
					}
						
				}
				
				
				if (encontrado) {
					if(actual.getEmail().equals(email)) {
						while(salir == false) {
							System.out.println("Bienvenido "+actual.getNombre());
							System.out.println("");
							System.out.println("1.Crear Anuncio");
							System.out.println("2.Mostrar tu tablón");
							System.out.println("3.Salir");
							System.out.println("Introducir opcion  :");
							int op2 = escan.nextInt();
							
							
							if(op2 != 1 && op2 != 2 && op2 != 3) {
								System.out.println("Opcion no valida");
							}else if(op2 == 1) {
								System.out.println("1.Anuncio General.");
								System.out.println("2.Anuncio Individualizado.");
								System.out.println("3.Anuncio Tematico");
								System.out.println("4.Anuncio Flash");
								int op3 = escan.nextInt();
								
								
								Fabrica f = new Fabrica();
								Scanner sc1 = new Scanner(System.in);
								String titulo;
								String cuerpo;
								String destinatario;
								String intereses;
								Anuncio a = f.getAnuncio(op3);
								ArrayList<String> dest = new ArrayList<String>();
								ArrayList<String> aux = new ArrayList<String>();
								
								boolean loop = true;
								int option;
								int diaI,mesI,anoI,horaI,minI,diaF,mesF,anoF,horaF,minF;

								
								
								switch(op3){
								
								
								case 1:
									
									a.setId(id);
									a.setTipo("General");
									
									for(int i=0;i<g.getContactos().size();i++) {
										aux.add(g.getContactos().get(i).getEmail());
									}
									a.setDestinatarios(aux);
									a.setPropietario(g.getContacto(email).getEmail());
									a.setIntereses(g.getInteresesValidos());
									System.out.println("Introducir titulo del anuncio : ");
									titulo = sc1.nextLine();
									System.out.println("Introducir cuerpo del anuncio : ");
									cuerpo = sc1.nextLine();
									a.setTitulo(titulo);
									a.setCuerpo(cuerpo);
									a.setFechainicio(horaActual);
									
									an.add(a);
									id++;
									break;
									
								case 2:
							
									a.setId(id);
									a.setTipo("Individualizado");
									while(loop == true) {
										System.out.println("Introduce el email del destinatario : ");
										destinatario = sc1.nextLine();
										if(g.existeContacto(destinatario)==1) {
											dest.add(g.getContacto(email).getEmail());
										}else {
											System.out.println("No existe el destinatario.");
										}
										
										System.out.println("¿Desea introducir otro destinatario?");
										System.out.println("1.Si");
										System.out.println("2.No");
										option = sc1.nextInt();
										if(option==1) {
											loop = true;
										}else if(option == 2) {
											loop = false;
										}else {
											System.out.println("Opcion no valida");
										}
									}
									
									a.setDestinatarios(dest);		
									a.setPropietario(g.getContacto(email).getEmail());
									a.setIntereses(g.getInteresesValidos());
									
									System.out.println("Introducir titulo del anuncio : ");
									titulo = sc1.nextLine();
									
									System.out.println("Introducir cuerpo del anuncio : ");
									cuerpo = sc1.nextLine();
									
									a.setTitulo(titulo);
									a.setCuerpo(cuerpo);
									a.setFechainicio(horaActual);
									
									
									an.add(a);
									id++;
									break;
									
									
								case 3:

									String comprobar;
									
									
									a.setId(id);
									a.setTipo("Tematico");
								
								while(!loop) {
									
									System.out.println("Escriba uno de los siguientes intereses: \n");
									System.out.println("Pintura    Música    Deporte  \n");
									System.out.println("Pesca      Cine      Fotografía  \n");
									System.out.println("Viajes     Tecnología \n");
									intereses = sc1.nextLine();
									while(!g.validarElemento(intereses)) {
										System.out.println("Interes no válido\n");
										System.out.println("Introduce de nuevo el interes:");
										intereses = sc1.nextLine();
									}
									InteresesAux.add(intereses);
									
									System.out.println("¿Desea añadir otro interés?\n");
									System.out.println("Escribe Y/N");
									comprobar = sc1.nextLine();
									if(comprobar!="Y" || comprobar!="y") {
										loop=true;
									}
								}
								
									
									
									for(int i=0;i<g.getContactos().size();i++) {
										aux.add(g.getContactos().get(i).getEmail());
									}
								
									a.setDestinatarios(aux);
									a.setPropietario(g.getContacto(email).getEmail());
									a.setIntereses(InteresesAux);
									
									System.out.println("Introducir titulo del anuncio : ");
									titulo = sc1.nextLine();
									
									System.out.println("Introducir cuerpo del anuncio : ");
									cuerpo = sc1.nextLine();
									
									a.setTitulo(titulo);
									a.setCuerpo(cuerpo);
									a.setFechainicio(horaActual);
									
									
									an.add(a);
									t.setAnuncios(an);
									id++;
									break;
									
								case 4:
									a.setId(id);
									a.setTipo("Flash");
									for(int i=0;i<g.getContactos().size();i++) {
										aux.add(g.getContactos().get(i).getEmail());
									}
									a.setDestinatarios(aux);
									a.setPropietario(g.getContacto(email).getEmail());
									a.setIntereses(g.getInteresesValidos());
									
									System.out.println("Introducir titulo del anuncio : ");
									titulo = sc1.nextLine();
									
									System.out.println("Introducir cuerpo del anuncio : ");
									cuerpo = sc1.nextLine();
									
									a.setTitulo(titulo);
									a.setCuerpo(cuerpo);
									System.out.println("Introducir año inicio : ");
									anoI = escan.nextInt();
									c.set(c.YEAR,anoI);
									System.out.println("Introducir mes inicio : ");
									mesI = escan.nextInt();
									c.set(c.MONTH,mesI);
									System.out.println("Introducir dia inicio : ");
									diaI = escan.nextInt();
									c.set(c.DAY_OF_MONTH,diaI);
									System.out.println("Introducir hora inicio : ");
									horaI = escan.nextInt();
									c.set(c.HOUR_OF_DAY,horaI);
									System.out.println("Introducir minuto inicio : ");
									minI = escan.nextInt();
									c.set(c.MINUTE,minI);
									c.set(c.SECOND,00);
									Date fechaInicio = c.getTime();
									a.setFechainicio(fechaInicio);
									
									
									System.out.println("Introducir año fin : ");
									anoF = escan.nextInt();
									c.set(c.YEAR,anoF);
									System.out.println("Introducir mes fin : ");
									mesF = escan.nextInt();
									c.set(c.MONTH,mesF);
									System.out.println("Introducir dia fin : ");
									diaF = escan.nextInt();
									c.set(c.DAY_OF_MONTH,diaF);
									System.out.println("Introducir hora fin : ");
									horaF = escan.nextInt();
									c.set(c.HOUR_OF_DAY,horaF);
									System.out.println("Introducir minuto fin : ");
									minF = escan.nextInt();
									c.set(c.MINUTE,minF);
									c.set(c.SECOND,00);
									Date fechaFin = c.getTime();
									a.setFechafin(fechaFin);
									
									
									an.add(a);
									id++;
									break;
									
									
									
								}
							}else if(op2 == 2){
								
									t.setAnuncios(an);
									
									ArrayList<Anuncio> a = new ArrayList<Anuncio>(t.getAnuncios());
									
									for(int j=0;j<a.size();j++) {
										if(a.get(j).getTipo().equalsIgnoreCase("Individualizado") && a.get(j).getDestinatarios().contains(g.getContacto(email))) {
											String cadena2 = t.getInfoAnuncio(a.get(j));
											System.out.println(cadena2);
										}else if(a.get(j).getTipo().equals("Flash") && horaActual.equals(a.get(j).getFechainicio()) && !horaActual.equals(a.get(j).getFechafin())){
											System.out.println(t.getInfoAnuncio(a.get(j)));
										}else if(a.get(j).getTipo().equalsIgnoreCase("Tematico") && g.getContacto(email).getIntereses().contains(a.get(j).getIntereses())) {
											System.out.println(t.getInfoAnuncio(a.get(j)));
										}else {
											System.out.println(t.getInfoAnuncio(a.get(j)));
										}
										
									}
								}
								
								
							
							else if(op2 == 3) {
								salir = true;
							}
						}
						
					}
				}else {
					System.out.println("Contacto no encontrado.");
				}
				
				
				//Registrarse
				
			}else if(op == 2){
				System.out.println("Introduce email por favor : \n");
				String contactoEmail= sn.nextLine();
													
				while(g.existeContacto(contactoEmail)!= 0) {
					
					System.out.println("Introduce de nuevo un email por favor : \n");
					contactoEmail= sn.nextLine();
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
				
			}else if(op == 3) {
				salir2 = true;
				System.out.println("Saliendo del programa...");
			}else {
				System.out.println("Opcion no valida.");
			}
			
			
		}
		
		
		
	}
}
