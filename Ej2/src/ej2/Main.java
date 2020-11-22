package ej2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		int op;
		int op2;
		int op3;
		int id = 0;										//VARIABLES ENTERAS AUXILIARES
		
		
		boolean salir = false;								
		boolean salir2 = false;								//VARIABLES BOOLEANAS ENTERAS
		boolean salir3 = false;
		boolean encontrado;
		
		
		Scanner escan = new Scanner(System.in);				//ESCANERES
		Scanner sn = new Scanner(System.in);
		
		String email;										//STRINGS AUXILIARES
		
		
		
		
		ArrayList<Anuncio> an = new ArrayList<Anuncio>();	//ARRAYLISTS AUXILIARES
		ArrayList<String> InteresesAux= new ArrayList();	
		
		GestorContactos g = GestorContactos.getInstance();	//INSTANCIAS AUXILIARES
		Tablon t = Tablon.getInstance();	
		
		
		// metemos los anuncios de la base de datos en el programa
		an = AnuncioDAO.meterAnuncios();
		for(int i=0;i<an.size();i++) {
			System.out.println(an.get(i).getCuerpo());
		}
		
		
		
		ArrayList<Contacto> guardados = new ArrayList<Contacto>();	//Estamos metiendo contactos desde base de
																	//datos
		guardados = ContactoDAO.meterContactos();
		g.setContactos(guardados);
		
		
		
		
		if(an.isEmpty()) {
			id = 1;
		}else {
			id = an.get(an.size()-1).getId();
			id++;
		}
		
		
		
		while(salir == false) 
		{
			
			//PRIMER MENU
			
			
			
			System.out.println("Menu Anuncios");
			System.out.println("");
			System.out.println("1.Iniciar Sesion.");
			System.out.println("2.Registrarse.");
			System.out.println("0.Salir");
			
			
			op = escan.nextInt();
			
			
			
			
			switch(op) 
			{
			case 1:
				//OPCION 1 ESCOGIDA INICIAMOS SESIÓN
				
				Contacto actual=new Contacto();									//CONTACTO DE INICIO DE SESIÓN AUXILIAR
				encontrado=false;
				
				System.out.println("Introducir email : ");						//COMPROBAMOS EL EMAIL, SI ES ENCONTRADO DEVOLVEMOS TRUE
				email = sn.nextLine();											//Y GUARDAMOS LA INFORMACIÓN DEL CONTACTO
				
				//java.util.Hashtable<String,String> result = ContactoDAO.mostrarContacto(email);
				for(int i=0;i<guardados.size();i++) {
					if(guardados.get(i).getEmail().contentEquals(email)) {
						actual = guardados.get(i);
						encontrado = true;
					}
				}
				
					if (!guardados.isEmpty()) // esto esta actualizado para BASE DE DATOS
					{	
						
																				//ENTRAMOS EN EL SEGUNDO MENÚ PERSONAL
						if(encontrado)
						{
							
							//SEGUNDO MENU INICIO DE SESIÓN CORRECTO
							
							salir2=false;
							while(salir2 == false)
							{
								System.out.println("\n");
								GestorContactos.clearConsole();
								System.out.println("Bienvenido "+actual.getNombre());
								System.out.println("");
								System.out.println("1.Crear Anuncio");
								System.out.println("2.Mostrar tu tablón");
								System.out.println("3.Gestor de Anuncios");
								System.out.println("0.Volver al menu principal");
								System.out.println(" Introducir opcion  :");
								
								op2 = escan.nextInt();
								System.out.println("\n");
								
								switch(op2) 
								{
								case 1:
									 //ESCOGIDO "CREAR ANUNCIO"
									{
										do{
											//MENU CREAR ANUNCIO (TERCER MENÚ)
											
											
											System.out.println("1.Anuncio General.");
											System.out.println("2.Anuncio Individualizado.");
											System.out.println("3.Anuncio Tematico");
											System.out.println("4.Anuncio Flash");
											System.out.println("0.Volver");
											
											op3 = escan.nextInt();
											System.out.println(" ");

											
											Fabrica f = new Fabrica();
											Anuncio a = f.getAnuncio(op3);								
											Scanner sc1 = new Scanner(System.in);
											
											String titulo;
											String cuerpo;
											String destinatario;
											String intereses;
											
											ArrayList<String> dest = new ArrayList<String>();
											ArrayList<String> aux = new ArrayList<String>();
											
											boolean loop;
											int option;
											
			
											
											
												switch(op3)
												{
												
												
												case 1: 														//CREACIÓN ANUNCIO GENERAL
													
													
													
													for(int i=0;i<g.getContactos().size();i++)
													{
														aux.add(g.getContactos().get(i).getEmail());
													}
													a.setDestinatarios(aux);
													a.setPropietario(g.getContacto(email).getEmail()); // el propietario es el contacto que esta en el menu.
													a.setIntereses(g.getInteresesValidos());
													System.out.println("Introducir titulo del anuncio : ");
													titulo = sc1.nextLine();
													System.out.println("Introducir cuerpo del anuncio : ");
													cuerpo = sc1.nextLine();
													
													
													
													
													a.setId(id);						//ESTABLECEMOS LOS VALORES DE ANUNCIO a Y LO
													System.out.println(id);
													a.setTipo("General");				//AÑADIMOS A LA LISTA GENERAL DE ANUNCIOS
													System.out.println(a.getTipo());
													a.setTitulo(titulo);
													a.setCuerpo(cuerpo);
													a.setFechainicio("21/11/2020 00:00");
													a.setFechafin("21/11/2020 00:00");
													a.setEstado("Editado");
													an.add(a);
													int status = AnuncioDAO.GuardarAnuncio(a);
													
													if(status!=0) {
														System.out.println("\n¡Anuncio creado!");
														System.out.println("RECUERDE que no será visible hasta que lo publique en el GESTOR DE ANUNCIOS");

													}else {
														System.out.println("Error al guardar anuncio en la base de datos.");
													}
													
													for(int i=0;i<g.getContactos().size();i++) {
														status = AnuncioDAO.GuardarDestinatarios(a,g.getContactos().get(i).getEmail());
														if(status == 0) {
															System.out.println("Error al guardar destinatario en la base de datos");
														}
													}
													
													
													
													
													
													id++;
													
													
													break;
													
													
													
												case 2:		
													
																											//CREACIÓN ANUNCIO INDIVIDUALIZADO
													
													
													
													int out;
													do{
														
														
														
														System.out.println("Por favor introduzca el email del usuario destinatario");
														destinatario = sc1.nextLine();
														
														if(g.existeContacto(destinatario)==1)
														{
															System.out.println("Destinatario guardado");
															aux.add(destinatario);
														}
														else if (g.existeContacto(destinatario)==0)
														{
															System.out.println("Destinatario no encontrado");
														}
														else {
															System.out.println("Email de destinatario no válido");
														}
														
														
														
														do {
															
															System.out.println("\n¿Desea introducir otro destinatario?");
															
															System.out.println("1.Si");
															System.out.println("2.No");

															out = escan.nextInt();
															if(out!=1 && out!=2) {
																System.out.println("Opción no válida");
															}
															
															
														}while (out!=1 && out!=2);
														
														
														
													}while(out==1);
													
													

													
													
													System.out.println("Introducir titulo del anuncio : ");
													titulo = sc1.nextLine();
													
													System.out.println("Introducir cuerpo del anuncio : ");
													cuerpo = sc1.nextLine();
													

							
													
													
													a.setId(id);
													a.setTipo("Individualizado");
													a.setDestinatarios(aux);
													a.setPropietario(g.getContacto(email).getEmail());
													a.setIntereses(InteresesAux);											
													a.setTitulo(titulo);
													a.setCuerpo(cuerpo);
													a.setFechainicio("21/11/2020 00:00");
													a.setFechafin("21/11/2020 00:00");
													a.setEstado("Editado");
													for(int i=0;i<aux.size();i++) {
														status = AnuncioDAO.GuardarDestinatarios(a, aux.get(i));
														if(status == 0) {
															System.out.println("Error al guardar en la base de datos.");
														}
													}
													
													status = AnuncioDAO.GuardarAnuncio(a);
													if(status == 0) {
														System.out.println("Error al guardar en la base de datos.");
													}else {
														System.out.println("Anuncio guardado en la base de datos de forma correcta.");
													}
													
													System.out.println("\n¡Anuncio creado!\n");

													an.add(a);
													t.setAnuncios(an);
													id++;
													
														
												break;
													
													
												case 3:														//CREACION ANUNCIO TEMÁTICO
													
													
																						
													do{
														
														System.out.println("Escriba UNO de los siguientes intereses: \n");
														System.out.println("Pintura    Música    Deporte  \n");
														System.out.println("Pesca      Cine      Fotografía  \n");
														System.out.println("Viajes     Tecnología \n");
														
														intereses = sc1.nextLine();
														
														
														while(!g.validarElemento(intereses)) 
														{
															System.out.println("Interes no válido\n");
															System.out.println("Introduce de nuevo el interes:");
															intereses = sc1.nextLine();
														}
														InteresesAux.add(intereses);
														Contacto auxiliar =new Contacto();
														
														for(int i=0;i<g.getContactos().size();i++)				//AÑADIMOS LAS PERSONAS INTERESADAS EN
														{														//EL INTERES INDICADO			
															
															auxiliar=g.getContactos().get(i);
															
															for(int j=0;j<auxiliar.getIntereses().size();j++) 
															{
																if(intereses==auxiliar.getIntereses().get(j)) {
																	aux.add(g.getContactos().get(i).getEmail());
																}
															}
															
														}
														
														do {
															
															System.out.println("\n¿Desea introducir otro interes?");
															
															System.out.println("1.Si");
															System.out.println("2.No");

															out = escan.nextInt();
															if(out!=1 && out!=2) {
																System.out.println("Opción no válida");
															}
															
															
														}while (out!=1 && out!=2);
														
														
														
													}while(out==1);
													
																			
													
													System.out.println("Introducir titulo del anuncio : ");
													titulo = sc1.nextLine();
													
													System.out.println("Introducir cuerpo del anuncio : ");
													cuerpo = sc1.nextLine();
													
													
													
													a.setTipo("Tematico");
													a.setDestinatarios(aux);
													for(int i=0;i<g.getContactos().size();i++) {
														status = AnuncioDAO.GuardarDestinatarios(a,g.getContactos().get(i).getEmail());
														if(status == 0) {
															System.out.println("Error al guardar destinatario en la base de datos");
														}
													}
													a.setPropietario(g.getContacto(email).getEmail());
													a.setIntereses(InteresesAux);
													for(int i=0;i<InteresesAux.size();i++) {
														status = AnuncioDAO.meterIntereses(actual,InteresesAux.get(i));
														if(status == 0) {
															System.out.println("Error al guardar intereses en la base de datos.");
														}
													}
													a.setTitulo(titulo);
													a.setCuerpo(cuerpo);
													a.setFechainicio("21/11/2020 00:00");
													a.setFechafin("21/11/2020 00:00");
													a.setEstado("Editado");
													System.out.println("\n¡Anuncio creado!\n");
													a.setIntereses(InteresesAux);

													status = AnuncioDAO.GuardarAnuncio(a);
													if(status == 0) {
														System.out.println("Error al guardar en la base de datos.");
													}
												
													
													an.add(a);
													t.setAnuncios(an);
													id++;
													break;
													
												case 4:													//CREACION ANUNCIO FLASH
													a.setId(id);
													String fecha_inicio;
													String fecha_fin;
													String hora_inicio;
													String hora_fin;
													a.setTipo("Flash");
													
													
													
													System.out.println("Introducir titulo del anuncio : ");
													titulo = sc1.nextLine();
													
													System.out.println("Introducir cuerpo del anuncio : ");
													cuerpo = sc1.nextLine();
													
													a.setTitulo(titulo);
													a.setCuerpo(cuerpo);
													do {
														System.out.println("Introduce la fecha de inicio del anuncio: (Por favor sigue el formato DD/MM/YYYY) ");
														 fecha_inicio=sn.nextLine();
														
														if (!g.validarFecha(fecha_inicio)) {
															System.out.println("Fecha introducida no válida ");
															
														}
													}while(!g.validarFecha(fecha_inicio));
													
															
													System.out.println("Introducir hora inicio (Por favor, sigue el formato HH:MM ej 18:00) ");
													hora_inicio = sc1.nextLine();
													
													a.setFechainicio(fecha_inicio+" "+hora_inicio);
													
													
													
													
													do {
														System.out.println("Introduce la fecha de fin del anuncio: (Por favor sigue el formato DD/MM/YYYY) ");
														 fecha_fin=sn.nextLine();
														
														if (!g.validarFecha(fecha_fin)) {
															System.out.println("Fecha introducida no válida ");

														}
													}while(!g.validarFecha(fecha_fin));
													
															
													System.out.println("Introducir hora fin (Por favor, sigue el formato HH:MM ej 18:00) ");
													hora_fin = sc1.nextLine();
													
													
													
													for(int i=0;i<g.getContactos().size();i++) 
													{
														aux.add(g.getContactos().get(i).getEmail());
													}
													
													a.setFechafin(fecha_fin+" "+hora_fin);
													a.setDestinatarios(aux);
													a.setPropietario(g.getContacto(email).getEmail());
													a.setIntereses(g.getInteresesValidos());
													a.setEstado("Editado");
													
													status = AnuncioDAO.GuardarAnuncio(a);
													
													if(status!=0) {
														System.out.println("\n¡Anuncio creado!");
														System.out.println("RECUERDE que no será visible hasta que lo publique en el GESTOR DE ANUNCIOS");

													}else {
														System.out.println("Error al guardar anuncio en la base de datos.");
													}
													
													for(int i=0;i<g.getContactos().size();i++) {
														status = AnuncioDAO.GuardarDestinatarios(a,g.getContactos().get(i).getEmail());
														if(status == 0) {
															System.out.println("Error al guardar destinatario en la base de datos");
														}
													}

													
													an.add(a);
													id++;
													break;
													
												case 0 :
													salir3 =true;
													
												}
										
										}while(op3!=0);
										
													
									}									//FIN OPCION "CREAR ANUNCIO" DEL MENU 2
								break;
								
								
								case 2:
									//ESCOGIDO "MOSTRAR TU TABLÓN" EN MENÚ 2

									
									for(int i=0;i<an.size();i++) 
									{
										if(an.get(i).getTipo().contentEquals("General") || an.get(i).getEstado().contentEquals("Publicado") )
										{
											t.mostrarAnuncio(an.get(i));
										}
										
										
										
										else if(an.get(i).getTipo().contentEquals("Individualizado") || an.get(i).getEstado().contentEquals("Publicado"))
										{
											for (int j=0; j<an.get(i).getDestinatarios().size();j++)
											{
												if(an.get(i).getDestinatarios().get(j).contentEquals(actual.getEmail()))
												{
													t.mostrarAnuncio(an.get(i));

												}
											}
										}
										
										
										else if(an.get(i).getTipo().contentEquals("Tematico") || an.get(i).getEstado().contentEquals("Publicado"))
										{
											for (int j=0; j<an.get(i).getDestinatarios().size();j++)
											{
												if(an.get(i).getDestinatarios().get(j).contentEquals(actual.getEmail()))
												{
													t.mostrarAnuncio(an.get(i));

												}
											}
										}
										
										else if(an.get(i).getTipo().contentEquals("Flash") || an.get(i).getEstado().contentEquals("Publicado"))
										{
											//Falta una funcion
											
										}
										
									}
									
									
									
									
									
									//FIN ESCOGIDO "MOSTRAR TU TABLÓN" EN MENÚ 2
								break;
								
								
								
								case 3:      //MENU GESTOR ANUNCIOS
									
									
									boolean primeravez=true;
									
									for(int i=0;i<an.size();i++)
									{
										if(an.get(i).getPropietario().contentEquals(actual.getEmail()))
												{
													 primeravez=false;
												}
									}
								
									if(primeravez)
									{
										System.out.println("¡Vaya, parece que todavía no tienes nigún anuncio!");
										System.out.println("\nPara crear tu primer Anuncio vuelve al menú anterior y selecciona 'Crear anuncio'");
										GestorContactos.press_any_key_to_continue();
										break;
									}
									else {
										
										{
											do{
												//MENU CREAR ANUNCIO ((SEGUNDO)TERCER MENÚ)

												System.out.println("1.Editar anuncio / guardar anuncio");
												System.out.println("2.Publicar anuncio");
												System.out.println("3.Archivar anuncio");
												System.out.println("4.Buscar por fecha");
												System.out.println("5.Buscar por tema de interés");
												System.out.println("6.Buscar por usuario propietario");
												System.out.println("7.Buscar por usuario destinatario");
												System.out.println("8.Eliminar anuncio");

												System.out.println("0.Volver");
												
												op3 = escan.nextInt();
												System.out.println(" ");

												
												
												
												
													switch(op3)
													{
													
													
													case 1: 		
														
														int id_editar;
														boolean existe=false;
														boolean cambiado=false;
														
														System.out.println(" A continuación te mostrarmos tus anuncios: ");

														
														for(int i=0;i<an.size();i++)
														{
															if(an.get(i).getPropietario().contentEquals(actual.getEmail()))
																	{
																		t.getInfoAnuncio(an.get(i));
																	}
														}
														
														
														do {
															System.out.println("Introduce el id del anuncio a modificar :");
															
															id_editar = escan.nextInt();
															
															for(int i=0;i<an.size();i++)
															{
																if(an.get(i).getId()==id_editar)
																		{
																			existe=true;
																		}
															}
															
															if(!existe) {
																System.out.println("Id no válido, por favor, introduce uno válido");
															}
																												
															
														}while(!existe);
														
														
														System.out.println("Selecciona el campo a editar del anuncio");
														System.out.println("1.Título");
														System.out.println("2.Cuerpo");
														System.out.println("3.Fecha Inicio                               (Solo anuncios 'Flash')");
														System.out.println("4.Fecha finalización                         (Solo anuncios 'Flash') ");
														System.out.println("5.Usuarios destinatarios.                    (Solo anuncios 'Individualizados')");
														System.out.println("6.Modificar Intereses usuarios destinatarios.(Solo anuncios 'Temáticos'");
														System.out.println("0.Volver");
												
														
														int elemento_editar = escan.nextInt();
														
														switch(elemento_editar)
														{
														case 1: //MODIFICACIÓN DE TÍTULO
															
															System.out.println("Por favor introduce un nuevo título: ");
															String nuevo_titulo=sn.nextLine();
															
															for(int i=0;i<an.size();i++)
															{
																if(an.get(i).getId()==id_editar)
																		{
																			an.get(i).setTitulo(nuevo_titulo);
																			cambiado=true;
																			an.get(i).setEstado("Editado");

																		}
															}
															break;
															
														case 2://MODIFICACIÓN DE CUERPO
															
															System.out.println("Por favor introduce un nuevo Cuerpo del anuncio");
															String nuevo_Cuerpo=sn.nextLine();
															
															for(int i=0;i<an.size();i++)
															{
																if(an.get(i).getId()==id_editar)
																		{
																			an.get(i).setTitulo(nuevo_Cuerpo);
																			cambiado=true;
																			an.get(i).setEstado("Editado");

																			
																		}
															}
															
															
															break;

														case 3://MODIFICACIÓN DE FECHA DE PUBLICACIÓN
														
															boolean es_flash=false;
															Anuncio a_modificar= new Anuncio ();
															for(int i=0;i<an.size();i++)
															{
																if(an.get(i).getId()==id_editar)
																		{
																			an.get(i).getTipo().contentEquals("Flash");
																			es_flash=true;
																			
																			a_modificar=an.get(i);
																		}
															}
															
															if(es_flash) {
																String fecha_inicio;
																do {
																	System.out.println("Introduce la fecha de inicio del anuncio: (Por favor sigue el formato DD/MM/YYYY) ");
																	 fecha_inicio=sn.nextLine();
																	
																	if (!g.validarFecha(fecha_inicio)) {
																		System.out.println("Fecha introducida no válida ");

																	}
																}while(!g.validarFecha(fecha_inicio));
					
																
																		
																System.out.println("Introducir hora inicio (Por favor, sigue el formato HH:MM ej 18:00) ");
																String hora_inicio = sn.nextLine();
																
																
																a_modificar.setFechainicio(fecha_inicio+" "+hora_inicio);
																
																cambiado=true;
																
																a_modificar.setEstado("En espera");

																

															}
															else {
																System.out.println("El anuncio seleccionado no es tipo 'Flash'");
															}

															break;
														
														case 4://MODIFICACIÓN DE FECHA FINALIZACIÓN
														
															
															es_flash=false;
															a_modificar=null;
															for(int i=0;i<an.size();i++)
															{
																if(an.get(i).getId()==id_editar)
																		{
																			an.get(i).getTipo().contentEquals("Flash");
																			es_flash=true;
																			
																			a_modificar=an.get(i);
																		}
															}
															
															if(es_flash) {
																String fecha_fin;
																
																do {
																	System.out.println("Introduce la fecha de fin del anuncio: (Por favor sigue el formato DD/MM/YYYY) ");
																	 fecha_fin=sn.nextLine();
																	
																	if (!g.validarFecha(fecha_fin)) {
																		System.out.println("Fecha introducida no válida ");

																	}
																}while(!g.validarFecha(fecha_fin));
					
																
																
																		
																System.out.println("Introducir hora fin (Por favor, sigue el formato HH:MM ej 18:00) ");
																String hora_fin = sn.nextLine();
																
																
																
																a_modificar.setFechafin(fecha_fin+" "+hora_fin);
																cambiado=true;
																a_modificar.setEstado("En espera");

															}
															else {
																System.out.println("El anuncio seleccionado no es tipo 'Flash'");
															}
															
															break;
														
														case 5://MODIFICACIÓN DE USUARIOS DESTINATARIOS(INDIVIDUALIZADO)
														boolean es_individualizado=false;
														ArrayList<String> aux = new ArrayList<String>();
														int out;
														a_modificar=null;
														

														
															for(int i=0;i<an.size();i++)
															{
																if(an.get(i).getId()==id_editar)
																		{
																			an.get(i).getTipo().contentEquals("Individualizado");
																			es_individualizado=true;
																			
																			a_modificar=an.get(i);
																		}
															}
															
															if(es_individualizado) 
															{

																do{
																	
																	
																	
																	System.out.println("Por favor introduzca el email del usuario destinatario");
																	String destinatario = sn.nextLine();
																	
																	if(g.existeContacto(destinatario)==1)
																	{
																		System.out.println("Destinatario guardado");
																		aux.add(destinatario);
																	}
																	else if (g.existeContacto(destinatario)==0)
																	{
																		System.out.println("Destinatario no encontrado");
																	}
																	else {
																		System.out.println("Email de destinatario no válido");
																	}
																	
																	
																	
																	do {
																		
																		System.out.println("\n¿Desea introducir otro destinatario?");
																		
																		System.out.println("1.Si");
																		System.out.println("2.No");

																		out = escan.nextInt();
																		if(out!=1 && out!=2) {
																			System.out.println("Opción no válida");
																		}
																		
																		
																	}while (out!=1 && out!=2);
																	
																	
																	
																}while(out==1);
																
																
															a_modificar.setDestinatarios(aux);	
															cambiado=true;
															a_modificar.setEstado("Editado");

															}
															else {
																System.out.println("El anuncio seleccionado no es tipo 'Individualizado'");
															}
															
															
															
															
															break;
														
														case 6://MODIFICACIÓN DE INTERESES (TEMÁTICO)
														
															
															boolean es_tematico=false;
															a_modificar=null;
															aux=null;
															

															
																for(int i=0;i<an.size();i++)
																{
																	if(an.get(i).getId()==id_editar)
																			{
																				an.get(i).getTipo().contentEquals("Tematico");
																				es_tematico=true;
																				
																				a_modificar=an.get(i);
																			}
																}
																
																if(es_tematico) 
																{

																	
																	
																	do{
																		
																		System.out.println("Escriba UNO de los siguientes intereses: \n");
																		System.out.println("Pintura    Música    Deporte  \n");
																		System.out.println("Pesca      Cine      Fotografía  \n");
																		System.out.println("Viajes     Tecnología \n");
																		
																		String intereses = sn.nextLine();
																		
																		
																		while(!g.validarElemento(intereses)) 
																		{
																			System.out.println("Interes no válido\n");
																			System.out.println("Introduce de nuevo el interes:");
																			intereses = sn.nextLine();
																		}
																		InteresesAux.add(intereses);
																		
																		Contacto auxiliar =new Contacto();
																		
																		for(int i=0;i<g.getContactos().size();i++)				//AÑADIMOS LAS PERSONAS INTERESADAS EN
																		{														//EL INTERES INDICADO			
																			
																			auxiliar=g.getContactos().get(i);
																			
																			for(int j=0;j<auxiliar.getIntereses().size();j++) 
																			{
																				if(intereses==auxiliar.getIntereses().get(j)) {
																					aux.add(g.getContactos().get(i).getEmail());
																				}
																			}
																			
																		}
																		
																		do {
																			
																			System.out.println("\n¿Desea introducir otro interes?");
																			
																			System.out.println("1.Si");
																			System.out.println("2.No");

																			out = escan.nextInt();
																			if(out!=1 && out!=2) {
																				System.out.println("Opción no válida");
																			}
																			
																			
																		}while (out!=1 && out!=2);
																		
																		
																		
																	}while(out==1);
																	
																	a_modificar.setIntereses(InteresesAux);
																	a_modificar.setDestinatarios(aux);	
																	a_modificar.setEstado("Editado");
																	cambiado=true;
																	
																	
																}
																else {
																	System.out.println("El anuncio seleccionado no es tipo 'Temático'");
																}
																
																
															
															
															
															break;
															
															
															
														}
														if (cambiado) {
															System.out.println("El anuncio ha sido editado satisfactoriamente");
														}
														
													
														
														break;
														
														
														
													case 2:		//PUBLICAR ANUNCIO
															
															boolean found=false;
															System.out.println("A continuación se muestran sus anuncios 'editados' : ");
															

															
															for(int i=0;i<an.size();i++)
															{
																if(an.get(i).getPropietario().contentEquals(actual.getEmail()))
																		{
																			t.getInfoAnuncio(an.get(i));
																		}
															}
														
															boolean es_flash=false;
															System.out.println("\nPor favor, introduce el ID del anuncio que desee publicar: ");
															int ID_publicar = escan.nextInt();
															
															for(int i=0;i<an.size();i++)
															{
																if(an.get(i).getId()==ID_publicar)
																		{
																			
																			an.get(i).setEstado("Publicado");
																			found=true;
																			
																			if(an.get(i).getTipo().contentEquals("Flash"))
																			{
																				es_flash=true;
																			}
																			
																		}
															}
															
															if(found) {
																System.out.println("¡Publicado! El anuncio será visible a partir de ahora\n");
																GestorContactos.press_any_key_to_continue();
															}
															else if(es_flash){
																
																System.out.println("¡Publicado! El anuncio será visible a partir de la fecha indicada de inicio\n");
																GestorContactos.press_any_key_to_continue();

															}
															else {
																System.out.println("Algo ha salido mal, vuelve a intentarlo por favor.");

															}
															
															
													
														break;
														
														
													case 3:			//ARCHIVAR ANUNCIO										

														
														System.out.println("A continuación se muestran sus anuncios 'publicados' : ");
														

														
														for(int i=0;i<an.size();i++)
														{
															if(an.get(i).getPropietario().contentEquals(actual.getEmail()) && an.get(i).getEstado().contentEquals("Publicado"))
																	{
																		t.getInfoAnuncio(an.get(i));
																	}
														}
														
														System.out.println("\nPor favor, introduce el ID del anuncio que desee publicar: ");
														ID_publicar = escan.nextInt();
														
														
														found=false;

														
														for(int i=0;i<an.size();i++)
														{
															if(an.get(i).getId()==ID_publicar)
																	{
																		
																		an.get(i).setEstado("Archivado");
																		found=true;
																														
																	}
														}
														
														if(found) {
															System.out.println("El anuncio ha sido archivado");
															GestorContactos.press_any_key_to_continue();

														}
														
														
														break;
														
													case 4:			//BUSCAR POR FECHA										

														String fecha;
														
														do {
															System.out.println("Introduce la fecha de inicio del anuncio a buscar: (Por favor sigue el formato DD/MM/YYYY) ");
															fecha=sn.nextLine();
															
															if (!g.validarFecha(fecha)) {
																System.out.println("Fecha introducida no válida ");

															}
														}while(!g.validarFecha(fecha));
														
														
														for(int i=0;i<an.size();i++)
														{
															
															if(fecha.contentEquals(an.get(i).getFechainicio())) {
																t.getInfoAnuncio(an.get(i));
															}
																												
														}
														
														
														break;
														
														
													case 5:			//BUSCAR POR TEMA DE INTERES									
														String interes;
														do{
															System.out.println("Introduzca el interes del anuncio a buscar");
															System.out.println("Escriba uno de los siguientes : \n");
															System.out.println("Pintura    Música    Deporte  \n");
															System.out.println("Pesca      Cine      Fotografía  \n");
															System.out.println("Viajes     Tecnología \n");
															
															 interes = sn.nextLine();
															System.out.println("");
															
															if(!g.validarElemento(interes))
															{
																System.out.println("Interes no válido");
															}
															
														
														
														}while(!g.validarElemento(interes));
														
														if(interes=="musica" || interes=="música" ||interes=="Musica"||interes=="Música")
														{
															for(int i=0;i<an.size();i++)
															{
																for(int j=0; j<an.get(i).getIntereses().size();j++) {
																	
																	
																	String auxiliar=an.get(i).getIntereses().get(j);
																	if(auxiliar=="musica" || auxiliar=="música" ||auxiliar=="Musica"||auxiliar=="Música")
																	{
																		
																	t.getInfoAnuncio(an.get(i));
																														
																	}
																	
																}
																																
															}
															
															
														}
														else if(interes=="tecnologia" || interes=="Tecnologia" ||interes=="Tecnología"||interes=="tecnología")
														{
															for(int i=0;i<an.size();i++)
															{
																for(int j=0; j<an.get(i).getIntereses().size();j++) {
																	
																	
																	String auxiliar=an.get(i).getIntereses().get(j);
																	if(auxiliar=="tecnologia" || auxiliar=="Tecnologia" ||auxiliar=="Tecnología"||auxiliar=="tecnología")
																	{
																		
																	t.getInfoAnuncio(an.get(i));
																														
																	}
																	
																}
																																
															}
															
															
														}
														else if(interes=="fotografia" || interes=="fotografía" ||interes=="Fotografia"||interes=="Fotografía")
														{
															for(int i=0;i<an.size();i++)
															{
																for(int j=0; j<an.get(i).getIntereses().size();j++) {
																	
																	
																	String auxiliar=an.get(i).getIntereses().get(j);
																	if(auxiliar=="fotografia" || auxiliar=="fotografía" ||auxiliar=="Fotografia"||auxiliar=="Fotografía")
																	{
																		
																	t.getInfoAnuncio(an.get(i));
																														
																	}
																	
																}
																																
															}
															
															
														}
														
														else if(interes=="Pintura" || interes=="pintura")
														{
															for(int i=0;i<an.size();i++)
															{
																for(int j=0; j<an.get(i).getIntereses().size();j++) {
																	
																	
																	String auxiliar=an.get(i).getIntereses().get(j);
																	if(auxiliar=="Pintura" || auxiliar=="pintura" )
																	{
																		
																	t.getInfoAnuncio(an.get(i));
																														
																	}
																	
																}
																																
															}
																
														}
														else if(interes=="Cine" || interes=="cine")
														{
															for(int i=0;i<an.size();i++)
															{
																for(int j=0; j<an.get(i).getIntereses().size();j++) {
																	
																	
																	String auxiliar=an.get(i).getIntereses().get(j);
																	if(auxiliar=="Cine" || auxiliar=="cine" )
																	{
																		
																	t.getInfoAnuncio(an.get(i));
																														
																	}
																	
																}
																																
															}
																
														}
														else if(interes=="Pesca" || interes=="pesca")
														{
															for(int i=0;i<an.size();i++)
															{
																for(int j=0; j<an.get(i).getIntereses().size();j++) {
																	
																	
																	String auxiliar=an.get(i).getIntereses().get(j);
																	if(auxiliar=="Pesca" || auxiliar=="pesca" )
																	{
																		
																	t.getInfoAnuncio(an.get(i));
																														
																	}
																	
																}
																																
															}
																
														}
														else if(interes=="Deporte" || interes=="deporte")
														{
															for(int i=0;i<an.size();i++)
															{
																for(int j=0; j<an.get(i).getIntereses().size();j++) {
																	
																	
																	String auxiliar=an.get(i).getIntereses().get(j);
																	if(auxiliar=="Pintura" || auxiliar=="pintura" )
																	{
																		
																	t.getInfoAnuncio(an.get(i));
																														
																	}
																	
																}
																																
															}
																
														}
														else if(interes=="Viajes" || interes=="viajes")
														{
															for(int i=0;i<an.size();i++)
															{
																for(int j=0; j<an.get(i).getIntereses().size();j++) {
																	
																	
																	String auxiliar=an.get(i).getIntereses().get(j);
																	if(auxiliar=="Viajes" || auxiliar=="viajes" )
																	{
																		
																	t.getInfoAnuncio(an.get(i));
																														
																	}
																	
																}
																																
															}
																
														}
														
														
														
														break;
														
														
													case 6:			//BUSCAR POR PROPIETARIO										
													String email_usuario_prop;
													
														System.out.println("Por favor introduzca el email del propietario a buscar sus anuncios");
														
														email_usuario_prop = sn.nextLine();
														
													
														for(int i=0; i<an.size();i++)
														{
															if(an.get(i).getPropietario().contentEquals(email_usuario_prop))
															{
																t.getInfoAnuncio(an.get(i));

															}
														}
														
														break;
														
														
													case 7:			//BUSCAR POR DESTINATARIO										

														
														String email_usuario_dest;
														
														System.out.println("Por favor introduzca el email del destinatario a buscar sus anuncios");
														
														email_usuario_dest = sn.nextLine();
														
													
														for(int i=0; i<an.size();i++)
														{
															for(int j=0;j<an.get(i).getDestinatarios().size();j++)
															{
																if(an.get(i).getDestinatarios().get(j).contentEquals(email_usuario_dest)) 
																{
																	t.getInfoAnuncio(an.get(i));

																}
															}
														}
														
														break;
														
													case 8:         //ELIMINAR ANUNCIO
														
														
														System.out.println("A continuación se muestran sus anuncios 'publicados' : ");
														

														
														for(int i=0;i<an.size();i++)
														{
															if(an.get(i).getPropietario().contentEquals(actual.getEmail()))
																	{
																		t.getInfoAnuncio(an.get(i));
																	}
														}
														
														System.out.println("\nPor favor, introduce el ID del anuncio que desee publicar: ");
														ID_publicar = escan.nextInt();
														
														
														found=false;

														
														for(int i=0;i<an.size();i++)
														{
															if(an.get(i).getId()==ID_publicar)
																	{
																		
																		an.remove(i);
																		found=true;										
																	}
														}
														
														if(found) {
															System.out.println("El anuncio ha sido eliminado");
														}
														
														
														
														
														
														break;
														
														
													case 0 :
														salir3 =true;
														
													}
											
											}while(op3!=0);
											
										break;		
										}
										
									}
								
								
								case 0:
									salir2 = true;
									GestorContactos.press_any_key_to_continue();
									GestorContactos.clearConsole();
								break;
								
								
								default:
									System.out.println("Opcion no valida");
									GestorContactos.press_any_key_to_continue();
									GestorContactos.clearConsole();
								break;
								}

								
								
						}									//FIN USUARIO ENCONTRADO, CORRECTO INICIO DE SESIÓN
						
					}							//FIN SEGUNDO MENÚ 2 TRAS "INICIO DE SESIÓN"
						
				}
					
					
				else 				//OPCION EMAIL NO ENCONTRADO		
				{
					System.out.println("Ese email no esta asociado a ninguna cuenta");
					
					
					GestorContactos.press_any_key_to_continue();
					GestorContactos.clearConsole();
					break;
				}
				
			break;
			
			
			case 2:
				
			//ESCOGIDO "REGISTRARSE" EN EL MENÚ 1
				
				
			
				System.out.println("Introduce un email por favor : \n");
				String contactoEmail= sn.nextLine();
				System.out.println(" ");
													
				while(g.existeContacto(contactoEmail)!= 0) {
					
					System.out.println("El email no es válido o ya se encuentra en uso");
					System.out.println("Por favor introduzca de nuevo un email:\n");
					contactoEmail= sn.nextLine();
				}
				
				
				Scanner tec = new Scanner(System.in);
				boolean out = false;
				System.out.println( "Introduce nombre por favor : \n");
				
				String nombre = tec.nextLine();
				System.out.println("");
				
				System.out.println( "Introduce apellidos por favor : \n");
				
				String apellidos = tec.nextLine();
				System.out.println("");
				
				System.out.println("Introduce la fecha de nacimiento :    ");
				System.out.println("(Por favor sigua el formato DD/MM/AAAA)\n");
				String fechaN = tec.nextLine();
				System.out.println("");
				
				while(!g.validarFecha(fechaN)) {
					System.out.println("\nFecha no válida");
					System.out.println("Introduce la fecha de nacimiento :    ");
					System.out.println("(Por favor sigua el formato DD/MM/AAAA)\n");
					fechaN = tec.nextLine();
					System.out.println("");
				}
				
				ArrayList<String> aux = new ArrayList<String>();
				
				while(!out) {
					System.out.println("Introduzca sus intereses");
					System.out.println("Escriba alguno de los siguientes : \n");
					System.out.println("Pintura    Música    Deporte  \n");
					System.out.println("Pesca      Cine      Fotografía  \n");
					System.out.println("Viajes     Tecnología \n");
					
					String interes = tec.nextLine();
					System.out.println("");
					
					if(!g.validarElemento(interes)) {
						System.out.println("\nInteres no válido.\n");
					}else {
						aux.add(interes);
						do {
							System.out.println("¿Desea añadir otro interes?");
							System.out.println("1.Si");
							System.out.println("2.No");
							op2 = tec.nextInt();
							System.out.println("");
							
							if(op2 == 2) {
								out = true;
								System.out.println("Contacto añadido.");
							}
							
							if(op2 != 1 && op2 != 2) {
								System.out.println("Opcion no valida.");
								
							}
						}while(op2 != 1 && op2 != 2);
						
							
					}
						
				}	
				
				
				Contacto contacto = new Contacto(nombre,apellidos,contactoEmail,fechaN,aux);
				int a = ContactoDAO.saveContacto(contacto);
				
				if(a!=0) {
					System.out.println("Contacto guardado de forma correcta");
				}else {
					System.out.println("Error al guardar contacto");
				}
				
				GestorContactos.press_any_key_to_continue();
				GestorContactos.clearConsole();
				
									//FIN REGISTRARSE
			
			break;
			
			case 0: //OPCIÓN SALIR DEL PROGRAMA
				
				salir = true;
				System.out.println("Saliendo del programa...");
			
			break;
			
			
			default:
				
				System.out.println("Opcion no valida.");	
				
			break;
			}
			
			
			

				
				
				
		} //FIN WHILE
			
			
	} //FIN CLASS
		
		
		
} //FIN MAIN
	
	

