// En la práctica, la clase debería ir en <es.uco.pw.data.dao>
package ej2;


import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
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
	  Class.forName("com.mysql.jdbc.Driver");
	  // Introducir correctamente el nombre y datos de conexión - Idealmente, estos datos se 
	  // indican en un fichero de propiedades
	  con= DriverManager.getConnection("jdbc:mysql://oraclepr.uco.es:3306/i82doalp","i82doalp","1234");
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
		Connection con=getConnection();
		// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
		// Lo habitual es que las consultas y sentencias SQL estén en un fichero de propiedades aparte, no en código
		PreparedStatement ps=con.prepareStatement("insert into Anuncio (Id,Tipo,Titulo,Propietario,Fecha_inicio,Fecha_fin,Cuerpo) values(?,?,?,?,?,?,?)");
		// El orden de los parámetros debe coincidir con las '?' del código SQL
		
		
		
		
		ps.setInt(1,a.getId());
		ps.setString(2,a.getTipo());
		ps.setString(3,a.getTitulo());
		ps.setString(4,a.getPropietario());
		ps.setString(5,a.getFechainicio().toString());
		ps.setString(6,a.getFechafin().toString());
		ps.setString(7,a.getCuerpo());
		
		status = ps.executeUpdate();
	// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
	// podemos capturar directamente SQLException
	}catch(Exception e){System.out.println(e);}
	// El invocante siempre debería tener información del resultado de la sentencia SQL
	return status;
}
  
  public static int GuardarDestinatarios(Anuncio a, Contacto c){
	int status=0;
	try{
		Connection con=getConnection();
		// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
		// Lo habitual es que las consultas y sentencias SQL estén en un fichero de propiedades aparte, no en código
		PreparedStatement ps=con.prepareStatement("insert into A_D (id_anuncio,email) values(?,?)");
		// El orden de los parámetros debe coincidir con las '?' del código SQL
		ps.setInt(1,a.getId());
		ps.setString(2, c.getEmail());
		
		
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
		Connection con=getConnection();
		// En consultas, se hace uso de un Statement 
		stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery("select Id,Tipo,Titulo,Propietario,Fecha_inicio,Fecha_fin,Cuerpo  from Anuncio where Id = " + a.getId());
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

	
}
