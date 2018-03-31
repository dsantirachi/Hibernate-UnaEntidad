package test;
import java.util.Scanner;
import funciones.Funciones;
import java.util.GregorianCalendar;
import negocio.ClienteABM;
public class TestAgregarCliente {
	public static void main(String[] args) {
		Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
		
		//Pedimos los datos del registro para agregar a la base de datos
		System.out.println("Nombre:");
		String nombre = entradaEscaner.nextLine();
		System.out.println("Apellido:");
		String apellido = entradaEscaner.nextLine();
		System.out.println("Documento:");
		int documento = entradaEscaner.nextInt();
		entradaEscaner.nextLine();  // usamos este next para sacar la basura
		
		System.out.println("Fecha de Nacimiento (dd/mm/aaaa):");
		String fechaCorta = entradaEscaner.nextLine();
		entradaEscaner.close();
		int arrayFecha[] = Funciones.traerFecha2(fechaCorta);
		if(Funciones.esFechaValida(arrayFecha[2], arrayFecha[1], arrayFecha[0])) {
			GregorianCalendar fechaDeNacimiento = Funciones.traerFecha(fechaCorta); //devuelvo un gregorian			
			ClienteABM abm= new ClienteABM();
			long ultimoIdCliente = abm.agregar(apellido, nombre, documento,fechaDeNacimiento);
		}else{
			System.out.println("Imposible agregar el registro, la fecha de Nacimiento es Invalida.");
		}
	}
}