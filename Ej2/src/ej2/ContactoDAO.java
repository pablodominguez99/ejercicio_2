package ej2;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;


public class ContactoDAO {
	// Método que establece la conexión con la base de datos
	  // NOTA: Los métodos estáticos no son obligatorios (ni siquiera los más apropiados):
	  // Se ha escrito de esta manera únicamente para facilitar la ejecución
	  public static Connection getConnection(){
		// En primer lugar, obtenemos una instancia del driver de MySQL
		Connection con=null;
		try {
		  Class.forName("com.mysql.jdbc.Driver");
		  
		  
		  File f = new File("C:\\Users\\w10\\git\\repository\\Ej2\\src\\ej2\\config.properties"); //establecemos la ruta del archivo config.properties
		  String confPath = f.getPath(); //convertimos la ruta del fichero config a una variable string
		  
		  Properties appProps = new Properties();
		  appProps.load(new FileInputStream(confPath)); // cargamos las propiedades del fichero
		  
		
		  
		  String acceso = appProps.getProperty("path"); //cojemos los parametros para conectar con la base de datos
		  String user = appProps.getProperty("user");
		  String pass = appProps.getProperty("pass");	
		  
		  // Introducir correctamente el nombre y datos de conexión - Idealmente, estos datos se 
		  // indican en un fichero de propiedades
		  con= DriverManager.getConnection(acceso,user,pass);
		// Importante capturar 
		} catch(Exception e) {
		  System.out.println(e);
		}
		return con;
	  }
	  
	  // Método para insertar una fila
	  // En ningún caso es recomendable el paso por parámetro de los valores individuales
	  // Se recomienda utilizar el UserBean o una clase envoltorio User que tenga estas propiedades
	  public static int saveContacto(Contacto c){
		int status=0;
		try{
			
			File f = new File("C:\\Users\\w10\\git\\repository\\Ej2\\src\\ej2\\sql.properties");//obtenemos la ruta del fichero sql.properties
			  String confPath = f.getPath();//Pasamos el path del fichero a una variable String
			  
			  Properties appProps = new Properties();
			  appProps.load(new FileInputStream(confPath));//Cargamos las propiedades
			  
			  String save = appProps.getProperty("nuevo_contacto");//pasamos la instruccion sql a una variable.
			
			
			
			
			Connection con=getConnection();
			// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
			// Lo habitual es que las consultas y sentencias SQL estén en un fichero de propiedades aparte, no en código
			PreparedStatement ps=con.prepareStatement(save);
			// El orden de los parámetros debe coincidir con las '?' del código SQL
			ps.setString(1,c.getNombre());
			ps.setString(2,c.getApellidos());
			ps.setString(3,c.getEmail());
			ps.setString(4,c.getFechaN());
			status = ps.executeUpdate();
		// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
		// podemos capturar directamente SQLException
		}catch(Exception e){System.out.println(e);}
		// El invocante siempre debería tener información del resultado de la sentencia SQL
		return status;
	}
	  
	// Método para actualizar un usuario
	public static int updateContacto(Contacto c){
		int status=0;
		
		 
		
		try{
			
			File f = new File("C:\\Users\\w10\\git\\repository\\Ej2\\src\\ej2\\sql.properties");//obtenemos la ruta del fichero sql.properties
			  String confPath = f.getPath();//Pasamos el path del fichero a una variable String
			  
			  Properties appProps = new Properties();
			  appProps.load(new FileInputStream(confPath));//Cargamos las propiedades
			  
			  String update = appProps.getProperty("update_contacto");//pasamos la instruccion sql a una variable.
			
			Connection con=getConnection();
			PreparedStatement ps=con.prepareStatement(update);
			ps.setString(1,c.getNombre());
			ps.setString(2,c.getApellidos());
			ps.setString(3,c.getFechaN());
			ps.setString(4,c.getEmail());
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}

	// Para la consulta, se ha tomado una estructura Hash (columna-tabla, valor)
	public static Hashtable<String,String> mostrarContacto (String email) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			

			File f = new File("C:\\Users\\w10\\git\\repository\\Ej2\\src\\ej2\\sql.properties");//obtenemos la ruta del fichero sql.properties
			  String confPath = f.getPath();//Pasamos el path del fichero a una variable String
			  
			  Properties appProps = new Properties();
			  appProps.load(new FileInputStream(confPath));//Cargamos las propiedades
			  
			  String mostrar = appProps.getProperty("mostrar_contacto");//pasamos la instruccion sql a una variable.
			
			
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(mostrar);
		    while (rs.next()) {
		    	String nombre = rs.getString("nombre");
		        String apellidos = rs.getString("apellidos");
		        String fecha_nacimiento = rs.getString("fecha_nacimiento");
		        resul = new Hashtable<String,String>();
		        resul.put("nombre", nombre);
		        resul.put("apellidos", apellidos);
		        resul.put("email", email);
		        resul.put("fecha_nacimiento", fecha_nacimiento);        
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 

	public static int delete(String email){
		int status=0;
		try{
			Connection con=getConnection();
			PreparedStatement ps=con.prepareStatement("delete from Contacto where email=?");
			ps.setString(1,email);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}

		return status;
	}
	
	
	public static ArrayList<Contacto> meterContactos(){
		Statement stmt = null; 
		ArrayList<Contacto> contactos = new ArrayList<Contacto>();
		try{
			
			File f = new File("C:\\Users\\w10\\git\\repository\\Ej2\\src\\ej2\\sql.properties");//obtenemos la ruta del fichero sql.properties
			  String confPath = f.getPath();//Pasamos el path del fichero a una variable String
			  
			  Properties appProps = new Properties();
			  appProps.load(new FileInputStream(confPath));//Cargamos las propiedades
			  String seleccionar_contactos = appProps.getProperty("seleccionar_contactos");
			
			Connection con=getConnection();
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(seleccionar_contactos);
		    while (rs.next()) {
		    	String nombre = rs.getString("nombre");
		        String apellidos = rs.getString("apellidos");
		        String email = rs.getString("email");
		        String fecha_nacimiento = rs.getString("fecha_nacimiento");
		        Contacto c = new Contacto(nombre,apellidos,email,fecha_nacimiento);  
		        contactos.add(c);
		    }
		    
		    if (stmt != null) 
		    	stmt.close();
		}catch(Exception e){System.out.println(e);}

		return contactos;
	}
	
	
	
}
