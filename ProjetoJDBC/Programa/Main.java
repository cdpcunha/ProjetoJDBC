package Programa;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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
	
	System.out.print("Entre com o código do departamento a ser pesquisado : ");
	idvar = sc.nextInt();
	sc.nextLine();
	List<Seller> listSel = new ArrayList<Seller>();
	listSel = sellerDao.findByDepartment(idvar);
	for(Seller x: listSel) {
		System.out.println(x);
	}
	try {
		sellerDao.insert();
	}catch (SQLException e) {
		System.out.println(e.getLocalizedMessage());
	}catch (ParseException e) {
		System.out.println(e.getLocalizedMessage());
	}
	
	
	sc.close();
	}

}
