// En la práctica, la clase debería ir en <es.uco.pw.data.dao>
package ej2;


import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Date;

// Sería recomendable tener una clase DAO que guardara los métodos comunes (p.ej. getConnection()) y 
// de la que heredase esta clase y el resto de DAOs
public class AnuncioDAO {
  // Método que establece la conexión con la base de datos
  // NOTA: Los métodos estáticos no son obligatorios (ni siquiera los más apropiados):
  // Se ha escrito de esta manera únicamente para facilitar la ejecución
  public static Connection getConnection(){
	// En primer lugar, obtenemos una instancia del driver de MySQL
	Connection con=null;
	try {
		
		File f = new File("C:\\Users\\w10\\git\\repository\\Ej2\\src\\ej2\\config.properties"); //establecemos la ruta del archivo config.properties
		  String confPath = f.getPath(); //convertimos la ruta del fichero config a una variable string
		  
		  Properties appProps = new Properties();
		  appProps.load(new FileInputStream(confPath)); // cargamos las propiedades del fichero
		  
		
		  
		  String acceso = appProps.getProperty("path"); //cojemos los parametros para conectar con la base de datos
		  String user = appProps.getProperty("user");
		  String pass = appProps.getProperty("pass");	
		  
		
	  Class.forName("com.mysql.jdbc.Driver");
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
  public static int GuardarAnuncio(Anuncio a){
	int status=0;
	try{
		
		File f = new File("C:\\Users\\w10\\git\\repository\\Ej2\\src\\ej2\\sql.properties");//obtenemos la ruta del fichero sql.properties
		  String confPath = f.getPath();//Pasamos el path del fichero a una variable String
		  
		  Properties appProps = new Properties();
		  appProps.load(new FileInputStream(confPath));//Cargamos las propiedades
		  
		  String save = appProps.getProperty("nuevo_anuncio");//pasamos la instruccion sql a una variable.
		
		
		Connection con=getConnection();
		// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
		// Lo habitual es que las consultas y sentencias SQL estén en un fichero de propiedades aparte, no en código
		PreparedStatement ps=con.prepareStatement(save);
		// El orden de los parámetros debe coincidir con las '?' del código SQL
		
		
		
		
		ps.setInt(1,a.getId());
		ps.setString(2,a.getTipo());
		ps.setString(3,a.getTitulo());
		ps.setString(4,a.getEstado());
		ps.setString(5,a.getPropietario());
		ps.setString(6,a.getFechainicio().toString());
		ps.setString(7,a.getFechafin().toString());
		ps.setString(8,a.getCuerpo());
		
		status = ps.executeUpdate();
	// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
	// podemos capturar directamente SQLException
	}catch(Exception e){System.out.println(e);}
	// El invocante siempre debería tener información del resultado de la sentencia SQL
	return status;
}
  
  public static int GuardarDestinatarios(Anuncio a,String email){
	int status=0;
	try{


		File f = new File("C:\\Users\\w10\\git\\repository\\Ej2\\src\\ej2\\sql.properties");//obtenemos la ruta del fichero sql.properties
		  String confPath = f.getPath();//Pasamos el path del fichero a una variable String
		  
		  Properties appProps = new Properties();
		  appProps.load(new FileInputStream(confPath));//Cargamos las propiedades
		  
		  String save = appProps.getProperty("guardar_destinatarios");//pasamos la instruccion sql a una variable.
		
		
		
		
		Connection con=getConnection();
		// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
		// Lo habitual es que las consultas y sentencias SQL estén en un fichero de propiedades aparte, no en código
		PreparedStatement ps=con.prepareStatement(save);
		// El orden de los parámetros debe coincidir con las '?' del código SQL
		ps.setInt(1,a.getId());
		ps.setString(2,email);
		
		
		status = ps.executeUpdate();
	// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
	// podemos capturar directamente SQLException
	}catch(Exception e){System.out.println(e);}
	// El invocante siempre debería tener información del resultado de la sentencia SQL
	return status;
}  
  
  
  
  
  
  
  
  
// Método para actualizar un usuario
public static int update(Anuncio a){
	int status=0;
	try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("update Anuncio set Titulo=?,Fecha_inicio=?,Fecha_fin=?,Cuerpo=? where Id=?");
		ps.setString(1,a.getTitulo());
		ps.setString(2,a.getFechainicio().toString());
		ps.setString(3,a.getFechafin().toString());
		ps.setString(4,a.getCuerpo());
		
		// En este caso, 'id' va después, conforme al orden de la sentencia SQL
		ps.setInt(4,a.getId());
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}
	return status;
}

// Para la consulta, se ha tomado una estructura Hash (columna-tabla, valor)
public static Hashtable<String,String> mostrarAnuncio  (Anuncio a) {
	Statement stmt = null; 
	Hashtable<String,String> resul = null;
	try {
		
		
		File f = new File("C:\\Users\\w10\\git\\repository\\Ej2\\src\\ej2\\sql.properties");//obtenemos la ruta del fichero sql.properties
		  String confPath = f.getPath();//Pasamos el path del fichero a una variable String
		  
		  Properties appProps = new Properties();
		  appProps.load(new FileInputStream(confPath));//Cargamos las propiedades
		  
		  String mostrarAnuncio = appProps.getProperty("mostar anuncio");//pasamos la instruccion sql a una variable.
		
		
		Connection con=getConnection();
		// En consultas, se hace uso de un Statement 
		stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(mostrarAnuncio);
	    while (rs.next()) {
	    	int id = rs.getInt("Id");
	        String tipo = rs.getString("Tipo");
	        String titulo = rs.getString("Titulo");
	        String propietario = rs.getString("Propietario");
	        String fecha_inicio = rs.getString("Fecha_inicio");
	        String fecha_fin = rs.getString("Fecha_fin");
	        String cuerpo = rs.getString("Cuerpo");


	        resul = new Hashtable<String,String>();
			resul.put("Id",Integer.toString(id) );
			resul.put("Tipo", tipo);
			resul.put("Titulo", titulo);
			resul.put("Propietario", propietario);     
			resul.put("Fecha_inicio", fecha_inicio);     
			resul.put("Fecha_fin", fecha_fin);     
			resul.put("Cuerpo", cuerpo);      
	        System.out.println(id + "\t" + tipo +
	                               "\t" + titulo + "\t" + propietario+ "\t" + fecha_inicio+ "\t" + fecha_fin+ "\t" + cuerpo);
	    }
	    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
	    if (stmt != null) 
	    	stmt.close(); 
	} catch (Exception e) {
		System.out.println(e);
	} 
	return resul;
} 

public static int delete(Anuncio a){
	int status=0;
	try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("delete from Anuncio where Id=?");
		ps.setString(1,Integer.toString(a.getId()));
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}

	return status;
}



public static ArrayList<Anuncio> meterAnuncios(){
	Statement stmt = null; 
	ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();
	
	try{
		
		
		File f = new File("C:\\Users\\w10\\git\\repository\\Ej2\\src\\ej2\\sql.properties");//obtenemos la ruta del fichero sql.properties
		  String confPath = f.getPath();//Pasamos el path del fichero a una variable String
		  
		  Properties appProps = new Properties();
		  appProps.load(new FileInputStream(confPath));//Cargamos las propiedades
		  
		  String todos_anuncios = appProps.getProperty("seleccionar_anuncios");//pasamos la instruccion sql a una variable.
		
		
		Connection con=getConnection();
		stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(todos_anuncios);
	    while (rs.next()) {
	    	int id = rs.getInt("Id");
	    	String tipo = rs.getString("Tipo");
	        String titulo = rs.getString("Titulo");
	        String estado = rs.getString("Estado");
	        String propietario = rs.getString("Propietario");
	        String fecha_inicio = rs.getString("Fecha_inicio");
	        String fecha_fin = rs.getString("Fecha_fin");
	        String cuerpo = rs.getString("Cuerpo");
	        Anuncio a = new Anuncio();
	        
	        

			a.setFechainicio(fecha_inicio);
			a.setFechafin(fecha_fin);
	        
	        
	        
	        
	        a.setId(id);
	        a.setTipo(tipo);
	        a.setTitulo(titulo);
	        a.setEstado(estado);
	        a.setPropietario(propietario);
	        a.setCuerpo(cuerpo);
	        anuncios.add(a);
	        
	        
	    }
	    
	    if (rs != null) 
	    	rs.close();
	}catch(Exception e){System.out.println(e);}

	return anuncios;
}


public static int meterIntereses(Contacto c,String interes){
	
	int status=0;
	try{
		
		File f = new File("C:\\Users\\w10\\git\\repository\\Ej2\\src\\ej2\\sql.properties");//obtenemos la ruta del fichero sql.properties
		  String confPath = f.getPath();//Pasamos el path del fichero a una variable String
		  
		  Properties appProps = new Properties();
		  appProps.load(new FileInputStream(confPath));//Cargamos las propiedades
		  
		  String save = appProps.getProperty("nuevo_C_I");//pasamos la instruccion sql a una variable.
		
		
		
		
		Connection con=getConnection();
		// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
		// Lo habitual es que las consultas y sentencias SQL estén en un fichero de propiedades aparte, no en código
		PreparedStatement ps=con.prepareStatement(save);
		// El orden de los parámetros debe coincidir con las '?' del código SQL
		ps.setString(1,c.getEmail());
		ps.setString(2,interes);
		
		status = ps.executeUpdate();
	// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
	// podemos capturar directamente SQLException
	}catch(Exception e){System.out.println(e);}
	// El invocante siempre debería tener información del resultado de la sentencia SQL
	return status;
	
}

	
}
