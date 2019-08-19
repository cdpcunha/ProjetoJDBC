package ImplementServicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import InterfaceServicos.SellerDAO;
import ObjetosEntidades.Department;
import ObjetosEntidades.Seller;
import db.DB;

public class SellerJDBC implements SellerDAO{

	private Connection conn;
	
	public SellerJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
			
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByID(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findByID(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps=conn.prepareStatement("SELECT S.*, D.NameDep FROM seller S JOIN department D ON S.DepartmentId = D.Id WHERE S.Id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setNome(rs.getString("NameDep"));
				
				Seller sel = new Seller();
				sel.setId(rs.getInt("Id"));
				sel.setName(rs.getString("Name"));
				sel.setEmail(rs.getString("Email"));
				sel.setBirthDate(rs.getDate("BirthDate"));
				sel.setSalary(rs.getDouble("BaseSalary"));
				sel.setDepartment(dep);
				return sel;
			}
		}
		catch(SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeResultSet(rs);
		}
		return null;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
