package InterfaceServicos;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import ObjetosEntidades.Seller;

public interface SellerDAO {
	void insert() throws SQLException, ParseException;
	void update(Seller obj);
	void deleteByID(Seller obj);
	Seller findByID(int id);
	List<Seller> findAll();
	List<Seller> findByDepartment(int id);
}
