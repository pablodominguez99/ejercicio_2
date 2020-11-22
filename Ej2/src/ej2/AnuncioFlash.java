package ej2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class AnuncioFlash extends Anuncio{

	
	private Date horaInicio;
	private Date horaFin;
	

	public AnuncioFlash() {
		super();
	}
	
	
	
	public Date getHoraInicio() {
		return this.horaInicio;
	}
	
	public Date getHoraFin() {
		return this.horaFin;
	}
	
	public void setFechaInicio(Date d) {
		this.horaInicio = d;
	}
	
	public void setFechaFin(Date d) {
		this.horaFin = d;
	}
	
	public void establecerHoras() {
		
		int diaI,mesI,anoI,horaI,minI,diaF,mesF,anoF,horaF,minF;
		Scanner sn = new Scanner(System.in);
		Calendar c = Calendar.getInstance();
		
		System.out.println("Introducir año inicio : ");
		anoI = sn.nextInt();
		c.set(c.YEAR,anoI);
		System.out.println("Introducir mes inicio : ");
		mesI = sn.nextInt();
		c.set(c.MONTH,mesI);
		System.out.println("Introducir dia inicio : ");
		diaI = sn.nextInt();
		c.set(c.DAY_OF_MONTH,diaI);
		System.out.println("Introducir hora inicio : ");
		horaI = sn.nextInt();
		c.set(c.HOUR_OF_DAY,horaI);
		System.out.println("Introducir minuto inicio : ");
		minI = sn.nextInt();
		c.set(c.MINUTE,minI);
		c.set(c.SECOND,00);
		Date fechaInicio = c.getTime();
		System.out.println("Introducir año fin : ");
		anoF = sn.nextInt();
		c.set(c.YEAR,anoF);
		System.out.println("Introducir mes fin : ");
		mesF = sn.nextInt();
		c.set(c.MONTH,mesF);
		System.out.println("Introducir dia fin : ");
		diaF = sn.nextInt();
		c.set(c.DAY_OF_MONTH,diaF);
		System.out.println("Introducir hora fin : ");
		horaF = sn.nextInt();
		c.set(c.HOUR_OF_DAY,horaF);
		System.out.println("Introducir minuto fin : ");
		minF = sn.nextInt();
		c.set(c.MINUTE,minF);
		c.set(c.SECOND,00);
		Date fechaFin = c.getTime();
		
		setFechaInicio(fechaInicio);
		setFechaFin(fechaFin);
		
		
	}
	
	
}
