package ej2;

import java.util.ArrayList;
import java.util.Date;

public class Anuncio {

	private int id;
	private String tipo;
	private String titulo;
	private String propietario;
	private ArrayList<String> destinatarios;
	private String fechainicio;
	private String fechafin;
	private ArrayList<String> intereses;
	private String cuerpo;
	private String estado;
	
	
	public Anuncio (){
		
	}
	
	
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getEstado() {
		return estado;
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
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	public ArrayList<String> getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(ArrayList<String> destinatarios) {
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

	public String getFechafin() {
		return fechafin;
	}

	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}

	public String getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}


	public ArrayList<String> getIntereses() {
		return intereses;
	}


	public void setIntereses(ArrayList<String> intereses) {
		this.intereses = intereses;
	}
	
	
	
	
}
