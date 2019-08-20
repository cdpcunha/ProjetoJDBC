package Programa;

import java.util.Scanner;

import ImplementServicos.SellerJDBC;
import InterfaceServicos.SellerDAO;
import ObjetosEntidades.Seller;
import db.DB;

public class Main {

	public static void main(String[] args) {
	SellerDAO sellerDao = new SellerJDBC(DB.getConnection());
	Scanner sc = new Scanner(System.in);
	
	System.out.print("Entre com o código do vendedor a ser pesquisado : ");
	Integer idvar = sc.nextInt();
	sc.nextLine();
	Seller seller = sellerDao.findByID(idvar);
	
	System.out.println(seller);
	sc.close();
	}

}
