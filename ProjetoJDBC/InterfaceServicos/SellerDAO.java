package InterfaceServicos;

import java.util.List;
import ObjetosEntidades.Seller;

public interface SellerDAO {
	void insert(Seller obj);
	void update(Seller obj);
	void deleteByID(Seller obj);
	Seller findByID(int id);
	List<Seller> findAll();
}
