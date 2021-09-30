package ui;
import java.util.LinkedList;
import java.util.Scanner;
import entities.Documento;
import entities.Persona;
import logic.Login;
public class Menu {
	
	Scanner s = null;
	Login ctrlLogin = new Login();
	
	
	public void start() {
		s = new Scanner(System.in);
		Persona p = login();
		System.out.println("Bienvenido "+p.getNombre()+" "+p.getApellido() );
		System.out.println();
		
		String command;
		do {
			command = getCommand();
			executeCommand(command);
			System.out.println();
		}while(!command.equalsIgnoreCase("salir"));
		
		s.close();
	}
	
	private void executeCommand(String command) {
		switch(command) {
		case"list":
				System.out.println(ctrlLogin.getAll());
			break;
		case"find":
			System.out.println(find());
			break;
		case"search":
			System.out.println(search());
			break;
		case"new":
			ctrlLogin.add();
			break;
		case"edit":
			System.out.println(edit());
			break;
		case"delete":
			delete();
			break;
		default:
			break;
		}
	}
	
	private String getCommand() {
		System.out.println("Lista de Comandos: ");
		System.out.println("list (para listar todos)");
		System.out.println("find (para buscar por tipo y nro documento)");
		System.out.println("search (para listar por apellido)");
		System.out.println("new (para crear nueva persona y asignar roles)");
		System.out.println("edit (para buscar por tipo y nro documento y actualizar los datos)");
		System.out.println("delete (para buscar una persona por tipo y nro documento y eliminarla)");
		System.out.println();
		System.out.print("Ingrese un comando: ");
		return s.nextLine();
	}
	
	public Persona login() {
		Persona p = new Persona();
		
		System.out.print("Email: ");
		p.setEmail(s.nextLine());
		
		System.out.print("Password: ");
		p.setPassword(s.nextLine());
		
		p=ctrlLogin.validate(p);
		return p;	
	}
	
	public Persona find() {
		System.out.println();
		Persona p = new Persona();
		Documento d =new Documento();
		
		p.setDocumento(d);
		
		System.out.print("Tipo Documento: ");
		d.setTipo(s.nextLine());
		
		System.out.print("Nro Documento: ");
		d.setNro(s.nextLine());

		return ctrlLogin.getByDocumento(p);	
	}
	
	public LinkedList<Persona> search() {
		System.out.println();
		Persona p = new Persona();

		
		System.out.print("Ingrese Apellido: ");
		p.setApellido(s.nextLine());
		
		

		return ctrlLogin.getByApellido(p);	
	}

	

	
	public Persona edit() {
		System.out.println();
		Persona p = new Persona();
		p = this.find();
		
		return ctrlLogin.update(p);	
	}

	
	public void delete() {
		System.out.println();
		Persona p = new Persona();
		p = this.find();
		ctrlLogin.delete(p);	
	}

}
