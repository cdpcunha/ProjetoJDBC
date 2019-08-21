package Programa;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ImplementServicos.SellerJDBC;
import InterfaceServicos.SellerDAO;
import ObjetosEntidades.Department;
import ObjetosEntidades.Seller;
import db.DB;

public class Main {

	public static void main(String[] args) {
		SellerDAO sellerDao = new SellerJDBC(DB.getConnection());
		Scanner sc = new Scanner(System.in);
		Integer idvar = 0;
		char op = 'a';
		Seller seller = new Seller();
		Department dep = new Department();
		List<Seller> listSel = new ArrayList<Seller>();

		System.out.print("Pesquisar vendedor (s/n)?");
		op = sc.nextLine().charAt(0);
		if (op == 's') {
			System.out.print("Entre com o código do vendedor a ser pesquisado : ");
			idvar = sc.nextInt();
			sc.nextLine();
			seller = sellerDao.findByID(idvar);
			System.out.println(seller);
			System.out.println("------------------------------------------------------------------------------------");
		}

		System.out.print("Pesquisar departamento (s/n)?");
		op = sc.nextLine().charAt(0);
		if (op == 's') {
			System.out.print("Entre com o código do departamento a ser pesquisado : ");
			idvar = sc.nextInt();
			sc.nextLine();
			listSel = new ArrayList<Seller>();
			dep = new Department(idvar, null);
			listSel = sellerDao.findByDepartment(dep);
			System.out.print("------------------------------------------------------------------------------------");

		}
		for (Seller x : listSel) {
			System.out.println(x);
		}

		System.out.println("------------------------------------------------------------------------------------");
		listSel = sellerDao.findAll();
		for (Seller x : listSel) {
			System.out.println(x);
		}
		System.out.println("------------------------------------------------------------------------------------");
System.out.print("Inserir novo Vendedor (s/n)?");
		op = sc.nextLine().charAt(0);
		if (op == 's') {
			try {
				sellerDao.insert(sc);
			} catch (SQLException e) {
				System.out.println(e.getLocalizedMessage());
			} catch (ParseException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}

		System.out.print("Qual o ID do vendedor a ser alterado ?");
		idvar = sc.nextInt();
		sc.nextLine();
		seller = sellerDao.findByID(idvar);
		seller.setName("Teste de renomeação");
		sellerDao.updateSeller(seller);

		sc.close();
	}

}
