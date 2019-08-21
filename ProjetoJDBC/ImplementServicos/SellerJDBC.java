package ImplementServicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import InterfaceServicos.SellerDAO;
import ObjetosEntidades.Department;
import ObjetosEntidades.Seller;
import db.DB;

public class SellerJDBC implements SellerDAO {

	private Connection conn;

	public SellerJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Scanner sc) throws SQLException, ParseException {
		Connection conn = this.conn;
		PreparedStatement ps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ps = conn.prepareStatement(
				"INSERT INTO SELLER (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?,?,?,?,?)");
		System.out.println("Entre com os dados do Vendedor");
		System.out.print("Nome :");
		String strAux = sc.nextLine();
		ps.setString(1, strAux);
		System.out.print("Email :");
		strAux = sc.nextLine();
		ps.setString(2, strAux);
		System.out.println("Data de Nascimento : ");
		java.sql.Date sqlDate = new java.sql.Date(sdf.parse(sc.nextLine()).getTime());
		ps.setDate(3, sqlDate);// Datas em SQL devem ser neste formato
		System.out.print("Salário :");
		Double dblAux = sc.nextDouble();
		ps.setDouble(4, dblAux);
		System.out.print("Departamento : ");
		Integer intAux = sc.nextInt();
		ps.setDouble(5, intAux);
		sc.nextLine();
		int rowsAffected = ps.executeUpdate();
		System.out.println("Insert completo com " + rowsAffected + " coluna(s) afetada(s).");
	}
	
	public void updateSeller(Seller obj) {
		Connection conn = this.conn;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"UPDATE SELLER SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? WHERE Id = ? ");
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getEmail());
			ps.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			ps.setDouble(4, obj.getSalary());
			ps.setInt(5, obj.getDepartment().getId());
			ps.setInt(6, obj.getId());
			int rowsAffected = ps.executeUpdate();
			System.out.println(
					"Update completo com " + rowsAffected + " coluna(s) afetada(s) no vendedor de Id = " + obj.getId());

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Override
	public void updateSalario() {
		Connection conn = this.conn;
		PreparedStatement ps = null;
		Scanner sc = new Scanner(System.in);
		try {
			ps = conn.prepareStatement("UPDATE SELLER SET BaseSalary = ? WHERE Id = ?");
			System.out.println("Informe os dados da alteração : ");
			System.out.print("Código (Id) do Vendedor a ser atualizado : ");
			double auxDouble = sc.nextDouble();
			ps.setDouble(2, auxDouble);
			System.out.print("Novo Salário : ");
			auxDouble = sc.nextDouble();
			ps.setDouble(1, auxDouble);
			int rowsAffected = ps.executeUpdate();
			System.out.println("Update completo com " + rowsAffected + " coluna(s) afetada(s).");

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		sc.close();
	}

	@Override
	public void deleteByID(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Seller> findByDepartment(Department department) {
		List<Seller> selList = new ArrayList<Seller>();
		Map<Integer, Department> map = new HashMap<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT S.*, D.NameDep FROM seller S JOIN Department D on S.DepartmentId = D.Id where S.DepartmentId = ? ORDER BY NAME");
			ps.setInt(1, department.getId());
			rs = ps.executeQuery();

			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				if (dep == null) {
					dep = InstanciaDep(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller sel = InstanciaSel(rs, dep);
				selList.add(sel);
			}
			return (ArrayList<Seller>) selList;
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	@Override
	public Seller findByID(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT S.*, D.NameDep FROM seller S JOIN department D ON S.DepartmentId = D.Id WHERE S.Id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Department dep = InstanciaDep(rs);
				Seller sel = InstanciaSel(rs, dep);
				return sel;
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
	public List<Seller> findAll() {
		List<Seller> selList = new ArrayList<Seller>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT S.*, D.NameDep FROM seller S JOIN Department D on S.DepartmentId = D.Id ORDER BY S.Id");
			rs = ps.executeQuery();

			while (rs.next()) {
				Department dep = InstanciaDep(rs);
				Seller sel = InstanciaSel(rs, dep);
				selList.add(sel);
			}
			return (ArrayList<Seller>) selList;
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	private Seller InstanciaSel(ResultSet rs, Department dep) throws SQLException {
		Seller sel = new Seller();
		sel.setId(rs.getInt("Id"));
		sel.setName(rs.getString("Name"));
		sel.setEmail(rs.getString("Email"));
		sel.setBirthDate(rs.getDate("BirthDate"));
		sel.setSalary(rs.getDouble("BaseSalary"));
		sel.setDepartment(dep);
		return sel;
	}

	private Department InstanciaDep(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("NameDep"));
		return dep;
	}

}
