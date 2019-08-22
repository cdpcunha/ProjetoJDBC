package ImplementServicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import InterfaceServicos.DepartmentDAO;
import ObjetosEntidades.Department;
import db.DB;
import db.DbException;

public class DepartmentJDBC implements DepartmentDAO {
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
			if (rowsAffected == 0) {
				throw new DbException("Nenhuma linha afetada !");
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Override
	public void updateDepartment(Integer id) {
		Connection conn = this.conn;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE DEPARTMENT SET NameDep = ? WHERE Id = ?");
			ps.setString(1, "Modificar Nome");
			ps.setInt(2, id);
			ps.executeUpdate();
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected == 0) {
				throw new DbException("Erro não previsto, nenhuma linha atualizada.");
			}

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	@Override
	public void deleteByID(Department obj) {
		Connection conn = this.conn;
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("DELETE FROM DEPARTMENT WHERE ID = ?");
			ps.setInt(1, obj.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public Department findByID(int id) {
		Connection conn = this.conn;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM DEPARTMENT WHERE ID = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Department dep = InstanciaDep(rs);
				return dep;
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeResultSet(rs);
		}
		return null;
	}

	@Override
	public List<Department> findAll() {
		List<Department> listdep = new ArrayList<Department>();
		Connection conn = this.conn;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM DEPARTMENT");
			rs = ps.executeQuery();
			while (rs.next()) {
				Department dep = InstanciaDep(rs);
				listdep.add(dep);
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeResultSet(rs);
		}
		return listdep;
	}

	private Department InstanciaDep(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("NameDep"));
		return dep;
	}

}
