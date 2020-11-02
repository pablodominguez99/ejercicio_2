package ej2;

import java.util.ArrayList;
import java.util.Date;

public class Anuncio {

	private int id;
	private String tipo;
	private String titulo;
	private Contacto propietario;
	private ArrayList<Contacto> destinatarios;
	private Date fechainicio;
	private Date fechafin;
	private ArrayList<String> intereses;
	private String cuerpo;
	
	
	public Anuncio (){
		
	}
	
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Contacto getPropietario() {
		return propietario;
	}
	public void setPropietario(Contacto propietario) {
		this.propietario = propietario;
	}
	public ArrayList<Contacto> getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(ArrayList<Contacto> destinatarios) {
		this.destinatarios = destinatarios;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	public String getTipo() {
		return tipo;
	}

	public Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public Date getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}


	public ArrayList<String> getIntereses() {
		return intereses;
	}


	public void setIntereses(ArrayList<String> intereses) {
		this.intereses = intereses;
	}
	
	
	
	
}
