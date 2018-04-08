package negocio;
import java.util.GregorianCalendar;
import java.util.List;
import dao.ClienteDao;
import datos.Cliente;
public class ClienteABM {
	ClienteDao dao = new ClienteDao();
	public Cliente traerCliente( long idCliente) throws Exception{
		// implementar si c es null lanzar Exception(HECHO)
		Cliente c= dao .traerCliente(idCliente);
		if (c==null) throw new Exception("No se encuentra ningun registro con ese ID.");
		return c;
	}
	public Cliente traerCliente( int dni) throws Exception{
		// implementar si c es null lanzar Exception(HECHO)
		Cliente c= dao .traerCliente(dni);
		if (c==null) throw new Exception("No se encuentra ningun registro con ese DNI.");
		return c;
	}
	public int agregar(String apellido, String nombre, int dni,GregorianCalendar fechaDeNacimiento)throws Exception{
		// consultar si existe un cliente con el mismo dni , si existe arrojar la Excepcion (HECHO)
		if(dao.traerCliente(dni)!=null) throw new Exception ("Ya existe un registro con el DNI ingresado.");
		Cliente c= new Cliente(apellido, nombre, dni,fechaDeNacimiento);
		return dao .agregar(c);
	}
	public void modificar(Cliente c) throws Exception{
		/* implementar antes de actualizar que no exista un cliente
		con el mismo documento a modificar y con el mismo id, lanzar la Exception */
		if(dao.traerCliente(c.getDni())!=null && dao.traerCliente(c.getIdCliente())!=null)
		dao .actualizar(c);
	}
	public void eliminar( long idCliente){ /*en este caso es física en gral. no se se
aplicaría este caso de uso, si se hiciera habría que validar que el cliente no tenga
dependencias*/
		Cliente c= dao .traerCliente(idCliente);
		// Implementar que si es null que arroje la excepción la Excepción
		dao .eliminar(c);
	}
	public List<Cliente> traerCliente(){ return dao .traerCliente();}
}