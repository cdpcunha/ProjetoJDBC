package InterfaceServicos;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import ObjetosEntidades.Department;
import ObjetosEntidades.Seller;

public interface SellerDAO {
	void insert(Scanner sc) throws SQLException, ParseException;
	void deleteByID(Integer id);
	Seller findByID(int id);
	List<Seller> findAll();
	List<Seller> findByDepartment(Department dep);
	void updateSeller(Seller obj);
	void updateSalario();
}
