package funciones;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class Funciones{

	//El calendario gregoriano usa los meses del 0 al 11 asique en los lugares que se utilizen
	//meses se vera un -1 o un +1 dependiendo si estoy guardando un mes en un metodo de la clase
	//GregorianCalendar(-1) o si estoy extrayendo un mes en un metodo de la clase GregorianCalendar(+1)
	//-----------------------------------------------------------------------------------------
	//Metodos de la clase Date:
	public static Date fechaActual(){
		//la fecha de date empieza en el 1/1/1970, si yo a date le doy como parametro un numero,
		//date me dara la fecha desde el 1/1/1970 hasta el numero que le puse contando en milisegundos
		Date f=new Date();
		return f;
	}
	//----------------------------------------------------------------------
	//metodos de la clase GregorianCalendar
	//------------------------------------------------
	//metodos de la clase GregorianCalendar:
	public static int traerAnio(GregorianCalendar f){
		return f.get(Calendar.YEAR);	
	}
	public static int traerMes(GregorianCalendar f){
		return f.get(Calendar.MONTH)+1;	
	}
	public static int traerDia(GregorianCalendar f){
		return f.get(Calendar.DAY_OF_MONTH);	
	}
	public static int traerHora(GregorianCalendar f){
		return f.get(Calendar.HOUR_OF_DAY);
	}
	public static int traerMinutos(GregorianCalendar f){
		return f.get(Calendar.MINUTE);
	}
	public static int traerSegundos(GregorianCalendar f){
		return f.get(Calendar.SECOND);
	}
	public static int traerDiaDeSemana(GregorianCalendar f){
		return f.get(Calendar.DAY_OF_WEEK);
	}
	//otros metodos usando la clase GregorianCalendar
	public static String traerFechaCorta(GregorianCalendar f){
		return (traerDia(f)+"/"+traerMes(f)+"/"+traerAnio(f));
	}
	public static boolean esBisiesto(int anio){
		return (((anio%4==0) && (anio%100!=0)) || (anio%100==0 && anio%400==0));
		//ver si es bisiesto en varias lanes
		//	if(anio%4==0){
		//		if(anio%100!=0){
		//			 bisiesto=1;
		//		}else if(anio%400==0){
		//			bisiesto=1;
		//		}
		//	}
	}
	public static boolean esFechaValida(int anio, int mes, int dia){
		//retorna false si es invalida y true si es valida
		//para que funcione los parametros pasados no deben venir de la clase Gregorian
		//Calendar, ya que la clase Gregorian calendar si le pasas el mes "12" te lo convertira
		//en 0, si le apsas "13" te lo convertira en 1, con los dias creo que tambien es igual
		//por lo tanto, primero se pasan los valores al metodo y luego, si quieres, creas
		//la instancia GregorianCalendar apra el manejo de fechas.
		boolean valida=true;
		if(anio<=0 || anio>9999) valida=false;
		if(mes<1 || mes>12) valida=false;
		if(dia<1) valida=false;
		switch(mes){
		case 1: if(dia>31) valida=false;	break;
		case 2:	if(esBisiesto(anio)==true){
			if(dia>29) valida=false;

		}else{
			if(dia>28) valida=false;
		}
		break;
		case 3: if(dia>31) valida=false;	break;
		case 4: if(dia>30) valida=false;	break;
		case 5: if(dia>31) valida=false;	break;
		case 6: if(dia>30) valida=false;	break;
		case 7: if(dia>31) valida=false;	break;
		case 8: if(dia>31) valida=false;	break;
		case 9: if(dia>30) valida=false;	break;
		case 10: if(dia>31) valida=false;	break;
		case 11: if(dia>30) valida=false;	break;
		case 12: if(dia>31) valida=false;	break;
		default: break;
		}
		return valida;
		
	}
	public static GregorianCalendar traerFecha(String  fecha){
		//recibe un Parametro de la forma “dd/mm/aaaa" 
		//el metodo substring te separa un string en varias variables segun la terminacion que le pongas
		//el ultimo numero substring(0,2) en este caso es 2, ese ultimo numero indica donde esta el separador
		//asique si en ese lugar hay un /, un * un 0, o cualquier cosa, este metodo no te lo guardara sea lo que sea
		int dia=Integer.parseInt(fecha.substring(0,2));
		int mes=Integer.parseInt(fecha.substring(3,5));
		int anio=Integer.parseInt(fecha.substring(6,10));
		GregorianCalendar fecha_gregoriana=new GregorianCalendar(anio,mes-1,dia);
		return fecha_gregoriana;  
	}
	public static int[] traerFecha2(String fecha){
		//recibe un Parametro de la forma “dd/mm/aaaa" 
		//DEVUELVE UN ARRAY DE ENTEROS conteniendo dia[0],mes[1], anio[2]
		//modo de uso:
			//int arrayFecha[] = Funciones.traerFecha2(fechaCorta);
			//dia = arrayFecha[0];  y asi con mes y anio
		//el metodo substring te separa un string en varias variables segun la terminacion que le pongas
		//el ultimo numero substring(0,2) en este caso es 2, ese ultimo numero indica donde esta el separador
		//asique si en ese lugar hay un /, un * un 0, o cualquier cosa, este metodo no te lo guardara sea lo que sea
		int arrayFecha[] = new int[3];
		int dia=Integer.parseInt(fecha.substring(0,2));
		int mes=Integer.parseInt(fecha.substring(3,5));
		int anio=Integer.parseInt(fecha.substring(6,10));
		arrayFecha[0] = dia;
		arrayFecha[1] = mes;
		arrayFecha[2] = anio;
		return arrayFecha;  
	}
	public static String traerFechaCortaHora(GregorianCalendar f){ 
		//Retorna “dd/mm/aaaa  hh:mm:ss" 
		return (traerDia(f)+"/"+traerMes(f)+"/"+traerAnio(f)+"  "+ traerHora(f)+":"+traerMinutos(f)+":"+traerSegundos(f));
	}
	public static GregorianCalendar traerFechaProximo(GregorianCalendar fecha, int cantDias){
		//yo le paso una x cantidad de dias y una fecha x como parametro y retorna otra fecha "y" que es producto 
		//de la suma de la cantidad de dias pasados desde esa fecha
		GregorianCalendar f=new GregorianCalendar(traerAnio(fecha),traerMes(fecha)-1,traerDia(fecha));
		f.add(Calendar.DAY_OF_MONTH, cantDias);
		return f;
	}
	public static boolean esDiaHabil(GregorianCalendar fecha){
		//Consideramos habil de lunes a viernes. 
		//fecha.get(Calendar.DAY_OF_WEEK) nos devuelve un numero segun el dia
		//1=jueves, 2=viernes, 3=sabado, 4=domingo, 5=lunes, 6=martes, 7=miercoles, 
		//usamos un casting y una variable tipo byte( -127/127) ya que ocupa menos espacio
		//que un int y solo necesitamos numeros del 1 al 7
		byte dia=(byte)fecha.get(Calendar.DAY_OF_WEEK);
		return (dia!=3 && dia!=4);
	}
	public static String traerDiaDeLaSemana(GregorianCalendar fecha){
		String dia[]={"Jueves","Viernes","Sabado","Domingo","Lunes","Martes","Miercoles"};
		return dia[fecha.get(Calendar.DAY_OF_WEEK)-1]; //le pongo el -1 porque empieza en dia[0], cuando
		//el Calendar me devuelve a partir del dia 1 a 7
		/* otra forma:
	String resultado;
switch(fecha.get(Calendar.DAY_OF_WEEK)){
case 1: resultado="jueves";break;
case 2: resultado="viernes";break;
case 3: resultado="sabado";break;
case 4: resultado="domingo";break;
case 5: resultado="lunes";break;
case 6: resultado="martes";break;
case 7: resultado="miercoles";break;
default: resultado="No se pudo obtener el dia";break;
}
return resultado;
		 */
	}
	public static String traerMesEnLetras(GregorianCalendar fecha){
		//Gregorian Calendar usa los meses de 0 a 11, asique si le paso por parametro el 12 me imprimira un
		//0, o sea, enero, si le paso un 13 me imprimira un 1, o sea febrero, si le paso un 12y le resto 1
		//fecha.get(Calendar.MONTH)-1 de esta forma, me dara error, porque si el metodo devuelve 0, sera 
		//0-1-->-1 por ende la posicion mes[-1] no existe, para solucionarlo
		String mes[]={"Diciembre","Enero", "Febrero",
				"Marzo", "Abril", "Mayo", "Junio", 
				"Julio", "Agosto", "Septiembre",
				"Octubre", "Noviembre"};
		return mes[fecha.get(Calendar.MONTH)];
	}
	public static String traerFechaLarga(GregorianCalendar f){
		//Ejemplo: “sabado 20 de Agosto del 2016" 
		return traerDiaDeLaSemana(f)+" "+traerDia(f)+" de "+traerMesEnLetras(f)+" del "+traerAnio(f);
	}
	public static boolean sonFechasIguales(GregorianCalendar f,GregorianCalendar f1){;
	return (traerAnio(f)==traerAnio(f1) && traerMes(f)==traerMes(f1) && traerDia(f)==traerDia(f1));
	}
	public static boolean sonFechasHorasIguales(GregorianCalendar f,GregorianCalendar f1){
		//nunca van a ser iguales a menos que le pase los mismos parametros a los 2 objetos, ya que
		//tiene que ser el dia, mes y anio, y hora y minutos y segundos iguales, para eso tendria que poner
		//un while y que salga del while cuando se cumple tal hora y termine el programa.
		return (sonFechasIguales(f,f1) && traerHora(f)==traerHora(f1) 
				&& traerMinutos(f)==traerMinutos(f1) && traerSegundos(f)==traerSegundos(f1));
	}
	public static int traerCantDiasDeUnMes(int anio,int mes){
	//le paso como parametro a gregorian calendar anio, mes y 1, ya que esta definido
	//que si o si se le debe pasar anio mes y dia, no se puede pasar solo el anio y mes
	//y le resto el -1 ya que el mes en el calendario gregoriano tiene lso meses de 0 a 11
	//asique si le paso por ejemplo 5 como mes(mayo) entonces contamos... 0-enero, 1-febrero,
	//2-marzo, 3-abril, 4-mayo, "5-junio" nos estaria mostrando a junio!, es por eso que si le resto
	//el -1, me va a mostrar a mayo, ahora si le paso como parametro un "0" me marcara a diciembre
	//ya que 0-1= -1,es como un loop, siempre hay que validar la fecha antes de pasarle algo com parametro
	GregorianCalendar fecha=new GregorianCalendar(anio,mes-1,1);
	return fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
}
	public static int compararFechas(GregorianCalendar fecha1,GregorianCalendar fecha2){
		/*compararFechas:compara el anio,mes,dia y nose que mas de estas 2 fechas pasadas como parametro
		 * usando el metodo compareTo() proveniente de la clase Date, 
		 * donde se usa asi: fecha1.compareTo(fecha2) compara fecha1 con fecha2.
		 * retorna "0" si las 2 fechas son iguales
		 * retorna "1" si fecha 1 es  mayor a fecha 2
		 * retorna "-1" si fecha 1 es menor a fecha 2
		 * 	fecha1.compareTo(fecha2); //fecha1 = fecha2, se mostrara un 0 en la consola
			fecha1.compareTo(fecha2); //fecha1 > fecha2, devuelve un valor mayor que 0
			fecha1.compareTo(fecha2); //fecha1 < fecha2, devuelve un valor menor que 0
		 */
		return fecha1.compareTo(fecha2);
	}
	//---------------------------------------------------------------------------------------------
	//Otros metodos:	
	public static double aproximar2Decimal(double valor){
		//Si el tercer decimal es mayor o igual 5, suma 1 al segundo decimal
		//Aca estoy usando el metodo de la clase Math, llamado rint, el cual sirve para redondear
		//numeros decimales, le paso el valor por ej 10.65341 y le multiplico 100 y queda
		//1065.341 a ese numero le aplico Math.rint(1065.0341) y me retornara 1065.0 y a ese numero
		//lo divido por 100 y me quedara 10.65
		//nota: tambien esta el metodo  Math.round() el cual hace lo mismo pero en ves de dejarte
		//el valor como 1065.0 (como un double), te deja el valor como 1065 (como un long)
		return Math.rint(valor*100)/100;
	}  
	public static boolean esNumero(char c){
		//metodo length() se usa para devolver el numero de caracteres de un String, en este caso tenemos 10
		//numeros por lo que nos retornara 10.
		//metodo charAt: simula lo que es un char normal en C, o sea, poder poner cadena[1].
		//sirve para ir posicion por posicion por el array de caracteres o buscar un caracter especifico
		//charAt(0)=0, charAt(1)=1... (en este ejemplo)
		boolean retorno=false;
		String diccionario=("0123456789");
		for (int i=0;i<diccionario.length();i++){ 
			if(c==diccionario.charAt(i)){
				retorno=true;
			}
		}
		return retorno;
	}
	public static boolean esLetra(char c){
		/*
 //esta primera forma de hacerlo usa un diccionario y compara caracter por caracter, es mas ineficiente
 //y lleva mas lineas de codigo y creo q no podes usar la enie o tal ves si, pero es mejor evitarlo.
 //la otra forma de hacerlo es usando caracteres ASCII
	boolean retorno=false;
	String diccionario=("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ");
	for(int x=0;x<diccionario.length();x++){
		if(c==diccionario.charAt(x)){
			retorno=true;
		}
	}
	return retorno;
		 */
		//--------------------------------
		//Otra forma de hacerlo a traves de caracteres Ascii
		//El metodo codePointAt() devuelve el codigo ASCII de un caracter en concreto. 
		//El caracter sera el que coincida con el indice que se pasa como parametro al metodo.
		//usando las comillas simples ' ' podemos tambien devolver el valor ASCII de un caracter
		//en este ejemplo usamos las comillas simples ya que es mucho mas facil.
		boolean retorno=false;
		if((c>='A' && c<='Z') || (c>='a' && c<='z')){
			retorno=true;
		}
		return retorno;
		//*Nota: se puede hacer todo en una sola linea:
		//return ((c>='A' && c<='Z') || (c>='a' && c<='z'))
	}
	public static boolean esCadenaNros(String cadena){
		//este metodo valida que solo se ingresen numeros en la cadena.
		//Si hay otra cosa que no sean numeros devolvera false, caso contrario devolvera true
		boolean retorno=true;
		for(int x=0;x<cadena.length();x++){
			if(esNumero(cadena.charAt(x))==false){ 
				retorno=false;
			}
		}
		return retorno;
	}
	public static boolean esCadenaLetras(String cadena){
		//este metodo valida que solo se ingresen letras en la cadena.
		//Si hay otra cosa que no sean letras devolvera false, caso contrario devolvera true
		boolean retorno=true;
		for(int x=0;x<cadena.length();x++){
			if(esLetra(cadena.charAt(x))==false){
				retorno=false;
			}
		}
		return retorno;
	}
	public static double convertirADouble(int n){ 
		return ((double) n);
	}
	public static int convertirAInt(char c){
		//primero lo pasa a String y despues a int
		return Integer.parseInt(""+c);	
	}
	public static char validarSexo(char sexo) throws Exception{
	//si se ingresa un sexo incorrecto te tirara a la excepcion, caso contrario devolvera el mismo sexo
		if(sexo!='f'&&sexo!='F'&&sexo!='m'&&sexo!='M') 
			throw new Exception("Ha ingresado un sexo incorrecto: "+sexo);
		return sexo;
	}
	public static String validarCuil(String cuil) throws Exception{
		//este metodo valida si el cuil es correcto, si es correcto retorna ese mismo cuil, caso contrario
		//hace una excepcion...Este es el procedimiento:
		/*En el cuil los primeros dos digitos corresponden a 27(F) y 20(M).
	 *El CUIL consta de 11 numeros. Los 10 primeros (2 + 8) constituyen el codigo de  
	 *identificacion y el ultimo, el digito de verificacion. Para obtener esta verificacion 
	 *se procede de  la siguiente forma: A cada digito del codigo, se lo multiplica por los
	  siguientes numeros (respectivamente) 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 y cada valor obtenido,
	   se suma para obtener una expresion (que llamaremos "valor 1". A este "valor 1", se 
	   le saca el resto de la division entera a 11. Se obtiene de esta forma un numero 
	   (del 0 al 10) (que llamamos "valor 2"). Sacamos la diferencia entre 11 y el "valor 2",
	    y obtenemos un valor comprendido entre 1 y 11 (llamemosle "valor 3"). Si "valor 3"=11,
	     el codigo verificador es cero. Si "valor 3"=10, el codigo verificador es 9. 
	   En cualquier otro caso, el codigo verificador es "valor 3".  Ejemplo numerico con un 
	   numero de CUIT, que es 20-17254359-7.   
	    5, 4, 3, 2, 7, 6, 5, 4, 3, 2
		 */
	//este metodo solo valida que sea un cuil valido, pero no valida si el cuil le pertenece
	//a un hombre o una mujer, ya que para eso habria que pasarle otro parametro al metodo,
	//el parametro sexo.
	int valor1=0, valor2=0,valor3=0;
	boolean valida=false;
	if(cuil.length()==11){
		if(cuil.charAt(0)=='2' && (cuil.charAt(1)=='0' || cuil.charAt(1)=='7')){
			valor1=convertirAInt(cuil.charAt(0))*5;
			valor1=convertirAInt(cuil.charAt(1))*4+valor1;
			valor1=convertirAInt(cuil.charAt(2))*3+valor1;
			valor1=convertirAInt(cuil.charAt(3))*2+valor1;
			valor1=convertirAInt(cuil.charAt(4))*7+valor1;
			valor1=convertirAInt(cuil.charAt(5))*6+valor1;
			valor1=convertirAInt(cuil.charAt(6))*5+valor1;
			valor1=convertirAInt(cuil.charAt(7))*4+valor1;
			valor1=convertirAInt(cuil.charAt(8))*3+valor1;
			valor1=convertirAInt(cuil.charAt(9))*2+valor1;
			valor2=valor1%11;
			valor3=11-valor2;
			if(valor3==11 && cuil.charAt(10)=='0')valida=true;
			if(valor3==10 && cuil.charAt(9)=='9')valida=true;
			if(valor3==convertirAInt(cuil.charAt(10)))valida=true;
			}
		}
	if(valida==false){
		throw new Exception("Ha ingresado un cuil incorrecto: "+cuil);
	}
	return cuil;
	}


}