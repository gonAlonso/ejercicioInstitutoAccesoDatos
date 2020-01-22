package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IntroducirDatos {
	//IntroducirDatos
	public static String introducirDatos(String s){
		try{
			System.out.print(s);
			return (new BufferedReader (new InputStreamReader (System.in))).readLine();
		}catch (IOException ioe){
			System.out.println("\nError interno en sistema de entrada/salida\n");
		}
		return "";
	}// fin introducirDatos
	
	public static Date introducirFecha(String s){
		BufferedReader bf = new BufferedReader (new InputStreamReader (System.in));
		while(true) {
			try{
				System.out.print(s + " [0+Enter salir]");
				String txt = bf.readLine();
				if(txt.equals("0")) return null;
				return new SimpleDateFormat("dd/MM/yyyy").parse(txt);
			}catch (IOException ioe){
				System.out.println("\nError interno en sistema de entrada/salida\n");
			} catch (ParseException e) {
				System.out.println("Fecha no reconocida. Debe ser formato:" +
						new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			}
		}
	}
	
	public static boolean introducirBooleano(String s){
		BufferedReader bf = new BufferedReader (new InputStreamReader (System.in));
		DateFormat df = DateFormat.getInstance();
		while(true) {
			try{
				System.out.print(s + "[S/N]");
				String txt = bf.readLine();
				if(txt.toUpperCase().equals("S")) return true;
				if(txt.toUpperCase().equals("N")) return false;
			}catch (IOException ioe){
				System.out.println("\nError interno en sistema de entrada/salida\n");
			} catch (Exception e) {
				System.out.println("Valor no reconocido");
			}
		}
	}

	public static int introducirInteger(String string) {
		BufferedReader bf = new BufferedReader (new InputStreamReader (System.in));
		while(true) {
			try{
				System.out.print(string);
				return Integer.parseInt( bf.readLine());
				
			}catch (IOException ioe){
				System.out.println("\nError interno en sistema de entrada/salida\n");
			} catch (Exception e) {
				System.out.println("Valor no reconocido. Vuelve a probar");
			}
		}
	}

}
