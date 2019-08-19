package Programa;

import ImplementServicos.SellerJDBC;
import InterfaceServicos.SellerDAO;
import ObjetosEntidades.Seller;
import db.DB;

public class Main {

	public static void main(String[] args) {
	
	SellerDAO sellerDao = new SellerJDBC(DB.getConnection());	
	Seller seller = sellerDao.findByID(6);
	
	System.out.println(seller);
	}

}
