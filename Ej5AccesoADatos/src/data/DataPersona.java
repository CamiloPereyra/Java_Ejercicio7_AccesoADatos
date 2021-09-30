package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

import entities.Documento;
import entities.Persona;
import entities.Rol;

public class DataPersona {
	Scanner s = null;

	public LinkedList<Persona> getAll() {
		DataRol dr = new DataRol();
		PreparedStatement stmt =null;
		ResultSet rs =null;
		LinkedList<Persona> pers = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre,apellido,tipo_doc, nro_doc, email, tel, habilitado from persona"
					);
			rs=stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					Persona p=new Persona();
					p.setDocumento(new Documento());
					p.setId(rs.getInt("id"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.getDocumento().setTipo(rs.getString("tipo_doc"));;
					p.getDocumento().setNro(rs.getString("nro_doc"));
					p.setEmail(rs.getString("email"));
					p.setTel(rs.getString("tel"));
					p.setHabilitado(rs.getBoolean("habilitado"));
					
					dr.setRoles(p);
					pers.add(p);
				}
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(stmt != null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pers;		
	
	
	}
	
	public Persona getByUser(Persona per) {
		DataRol dr = new DataRol();
		Persona p = null;
		PreparedStatement stmt =null;
		ResultSet rs =null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre,apellido,tipo_doc, nro_doc, email, tel, habilitado from persona where email = ? and password=?"
					);
			stmt.setString(1, per.getEmail());
			stmt.setString(2, per.getPassword());
			rs=stmt.executeQuery();
			
			if(rs != null && rs.next()) {
				p=new Persona();
				p.setDocumento(new Documento());
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.getDocumento().setTipo(rs.getString("tipo_doc"));;
				p.getDocumento().setNro(rs.getString("nro_doc"));
				p.setEmail(rs.getString("email"));
				p.setTel(rs.getString("tel"));
				p.setHabilitado(rs.getBoolean("habilitado"));
				
				dr.setRoles(p);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(stmt != null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return p;		
	}

	public Persona getByDocument(Persona per) {

		DataRol dr = new DataRol();
		Persona p = null;
		PreparedStatement stmt =null;
		ResultSet rs =null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre,apellido,tipo_doc, nro_doc, email, tel, habilitado from persona where tipo_doc = ? and nro_doc=?"
					);
			stmt.setString(1, per.getDocumento().getTipo());
			stmt.setString(2, per.getDocumento().getNro());
			rs=stmt.executeQuery();
			
			if(rs != null && rs.next()) {
				p=new Persona();
				p.setDocumento(new Documento());
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.getDocumento().setTipo(rs.getString("tipo_doc"));;
				p.getDocumento().setNro(rs.getString("nro_doc"));
				p.setEmail(rs.getString("email"));
				p.setTel(rs.getString("tel"));
				p.setHabilitado(rs.getBoolean("habilitado"));
				
				dr.setRoles(p);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(stmt != null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return p;		
	
	}

	public LinkedList<Persona> getByApellido(Persona per) {

		DataRol dr = new DataRol();
		PreparedStatement stmt =null;
		ResultSet rs =null;
		LinkedList<Persona> pers = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre,apellido,tipo_doc, nro_doc, email, tel, habilitado from persona where apellido = ?"
					);
			stmt.setString(1, per.getApellido());
			rs=stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					Persona p=new Persona();
					p.setDocumento(new Documento());
					p.setId(rs.getInt("id"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.getDocumento().setTipo(rs.getString("tipo_doc"));;
					p.getDocumento().setNro(rs.getString("nro_doc"));
					p.setEmail(rs.getString("email"));
					p.setTel(rs.getString("tel"));
					p.setHabilitado(rs.getBoolean("habilitado"));
					
					dr.setRoles(p);
					pers.add(p);
				}
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(stmt != null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pers;		
	}

	public void add(Persona p) {
		s = new Scanner(System.in);
		Rol r = new Rol();
		DataRol dr = new DataRol();
		Boolean otroRol = true;
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into persona(tipo_doc, nro_doc,nombre, apellido, email, tel, habilitado, password) values(?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, p.getDocumento().getTipo());
			stmt.setString(2, p.getDocumento().getNro());
			stmt.setString(3, p.getNombre());
			stmt.setString(4, p.getApellido());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getTel());
			stmt.setBoolean(7, p.isHabilitado());
			stmt.setString(8, p.getPassword());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setId(keyResultSet.getInt(1));
            }
            
			while(otroRol) {
				r = this.executeRol();
				if (p.hasRol(r)) System.out.println("\n Esta persona ya tiene ese rol asignado ");
				else {
					dr.setRoles(p);
					dr.saveRoles(p, r);
					System.out.print("\n rol asinado correctamente ");
				}
				System.out.print("\n Desea agregar otro rol? \n (1 = SI / 2 = NO): ");
				if(s.nextInt() == 1) {
					otroRol = true;
				}else otroRol =false;
			}
				
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}

	public Rol executeRol() {
		s = new Scanner(System.in);
		DataRol dr = new DataRol();
		Rol r = new Rol();
		int idRol = 0;
		Boolean band = true;
		
		System.out.println("Lista de Roles: ");
		System.out.println(dr.getAll());
		while(band) {
			System.out.print("Ingrese ID del rol elegido: ");
			idRol = s.nextInt();
			r.setId(idRol);
			r = dr.getById(r);
			if(r.getDescripcion() != null) {
				band=false;
			}
			else System.out.print("\n ID de Rol invalido intente nuevamente.");
		}
		
		return r;
	}
	
	public void update(Persona p) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update persona set tipo_doc= ? , nro_doc =? , nombre=?, apellido= ?, email = ?, tel=? ,  habilitado=? ,  password=?  where id =?");
			stmt.setString(1, p.getDocumento().getTipo());
			stmt.setString(2, p.getDocumento().getNro());
			stmt.setString(3, p.getNombre());
			stmt.setString(4, p.getApellido());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getTel());
			stmt.setBoolean(7, p.isHabilitado());
			stmt.setString(8, p.getPassword());
			stmt.setInt(9, p.getId());
			stmt.executeUpdate();
			
			this.updateRoles(p);
		
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}

	private void updateRoles(Persona p) {
		 s = new Scanner(System.in);
		DataRol dr = new DataRol();
		Rol r = new Rol();
		Boolean tieneRol = false;
		Boolean band = true;
		int b1 = 1;
		for(int i=1;i<=dr.getAll().size();i++) {
			 r.setId(i);
			 if(p.hasRol(r)) tieneRol=true ; 
		}
		
		if(tieneRol) {
			System.out.println("\n Esta persona ya tiene roles asignados: ");
			System.out.println("\n" + p);	
			System.out.print("\n Desea añadir otro rol? (S/N): ");
			if(s.nextLine().trim().equalsIgnoreCase("S")) {
				r = this.executeRol();
				dr.setRoles(p);
				p.addRol(r);
				dr.saveRoles(p, r);
				System.out.print("\n rol asinado correctamente ");
			}
			System.out.print("\n Desea eliminar algun rol? (S/N): ");
			if(s.nextLine().trim().equalsIgnoreCase("S")) {
				while(band) {
					r = this.executeRol();
					if(p.hasRol(r)) {
						dr.setRoles(p);
						p.removeRol(r);
						dr.undoneRol(p, r);
						System.out.print("\n rol eliminado correctamente"
								+"\n Desea eliminar otro? (S/N): ");
						if(s.nextLine().trim().equalsIgnoreCase("S")) band = true; else band=false;
					}else {
						System.out.println("Esta persona no contiene ese rol"
								+ "\n intente nuevamente");
					}
				}
			}
		}else {
			System.out.println("\n Esta persona NO tiene roles asignados ");
			System.out.println("\n A continuacion se asignaran roles: ");
			while(b1 == 1) {
				r = this.executeRol();
				dr.setRoles(p);
				p.addRol(r);
				dr.saveRoles(p, r);
				
				System.out.print("\n rol asignado correctamente ");
				System.out.println("\n Desea añadir otro rol? \n 1 = SI / 2 = NO: ");
				if(s.nextInt() != 1) {
					b1 = 2;
				}
				
			}
			
		}
	}
	
	public void delete(Persona p) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from persona where id=?");
			stmt.setInt(1, p.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}

}

