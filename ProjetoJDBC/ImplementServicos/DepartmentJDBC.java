package ImplementServicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import InterfaceServicos.DepartmentDAO;
import ObjetosEntidades.Department;
import db.DB;
import db.DbException;

public class DepartmentJDBC implements DepartmentDAO{
	private Connection conn;
	
	public DepartmentJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Department obj) {
		Connection conn = this.conn;
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("ISNERT INTO DEPARTMENT('Id','NameDep') VALUES (?,?)");
			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getNome());
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected == 0) {
				throw new DbException("Nenhuma linha afetada !");
			}
		}
		catch(SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByID(Department obj) {
		Connection conn = this.conn;
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("DELETE FROM DEPARTMENT WHERE ID = ?");
			ps.setInt(1, obj.getId());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		finally {
			DB.closeStatement(ps);
		}

	}
		

	@Override
	public Department findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
