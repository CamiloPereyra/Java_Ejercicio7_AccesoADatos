package logic;

import java.util.LinkedList;
import java.util.Scanner;
import data.DataPersona;
import data.DataRol;
import entities.Documento;
import entities.Persona;


public class Login {
	private DataPersona dp;
	Scanner scan = null;
	
	public Login() {
		dp=new DataPersona();
	}
	
	public Persona validate(Persona p) {
		/* para hacer mas seguro el manejo de contraseñas aca debemos generar un hash 
		 * de la password usando un sifrado aritmetico como sha256 y usar el hash en lugar de la pass en plano*/
		return dp.getByUser(p);
	}
	
	public LinkedList<Persona> getAll(){
		return dp.getAll();
	}
	
	public Persona getByDocumento(Persona per) {
		return dp.getByDocument(per);
	}

	public LinkedList<Persona> getByApellido(Persona p) {
		
		return dp.getByApellido(p);
	}

	public void add() {
		
		Documento newD = new Documento();
		Persona newP = new Persona();	
		System.out.println("Ingrese datos de la nueva persona: ");
		this.loadData(newP,newD);
		
		dp.add(newP);
			
		System.out.print("\n Se genero una nueva persona con ID: ");
		System.out.println(newP.getId());
			
	}
	
	public void loadData(Persona newP, Documento newD) {
		scan = new Scanner(System.in);
		System.out.print("\n Ingrese TIPO DE DOCUMENTO de la  Persona: ");
		newD.setTipo(scan.nextLine());
		System.out.print("\n Ingrese NRO DE DOCUMENTO de la  Persona: ");
		newD.setNro(scan.nextLine());
		newP.setDocumento(newD);
		
		System.out.print("\n Ingrese NOMBRE de la  Persona: ");
		newP.setNombre(scan.nextLine());
		System.out.print("\n Ingrese APELLIDO de la  Persona: ");
		newP.setApellido(scan.nextLine());
		
		System.out.print("\n Ingrese EMAIL de la  Persona: ");
		newP.setEmail(scan.nextLine());
		System.out.print("\n Ingrese TELEFONO de la  Persona: ");
		newP.setTel(scan.nextLine());
		System.out.print("Esta habilitado? (S/N): ");
		newP.setHabilitado(scan.nextLine().trim().equalsIgnoreCase("S"));
		System.out.print("\n Ingrese CONTRASEÑA de la  Persona: ");
		newP.setPassword(scan.nextLine());
	
		
	}
	
	public Persona update(Persona p) {
		System.out.println("Ingrese los nuevos datos de la persona: ");
		this.loadData(p,p.getDocumento());
		dp.update(p);
		return p;
	}

	public void delete(Persona p) {
		DataRol dr = new DataRol();
		dr.undoneAllRoles(p);
		dp.delete(p);
		System.out.print("\n Se elimino correctamente la persona");
		
	}

	
}
