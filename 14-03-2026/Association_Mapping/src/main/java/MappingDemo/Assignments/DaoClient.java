package MappingDemo.Assignments;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DaoClient {

	static OrderDao dao = new DaoBackend();
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		boolean t = true;

		while (t) {
			System.out.println("1-ADD, 2--VIEW BY ORDER ID, 3--VIEW BY CUSTOMER NAME,4--EXIT");

			int mtype = scan.nextInt();
			if (mtype == 4) {
				System.out.println("Exited Successfully");
				t = false;
				break;
			}

			try {
				processMenu(mtype);
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}

		}
		;

	}

	public static void processMenu(int mtype) throws Exception {

		switch (mtype) {

		case 1:
			addOrder();
			break;

		case 2:
			viewOrderByOrderID();
			break;

		case 3:
			viewOrdersByCustName();
			break;

		default:
			System.out.println("Invalid option");
		}

	}

	public static void addOrder() throws Exception {

		System.out.println("Enter Order Amount:");
		double amount = scan.nextDouble();

		System.out.println("Enter Customer ID:");
		int cid = scan.nextInt();

		System.out.println("Enter Customer Name:");
		String cname = scan.next();

		Customer c = new Customer();
		c.setCustomerID(cid);
		c.setCustomerName(cname);

		Order o = new Order();
		o.setOrderAmount(amount);
		o.setOrderDate(LocalDate.now());
		o.setCustomer(c);

		dao.addOrder(o, o.getOrderID());

		System.out.println("Order Added Successfully");

	}

	public static void viewOrderByOrderID() throws Exception {

		System.out.println("Enter Order ID:");
		int oid = scan.nextInt();

		Order o = dao.getOrder(oid);

		System.out.println(o);

	}

	public static void viewOrdersByCustName() throws Exception {

		System.out.println("Enter Customer Name:");
		String cname = scan.next();

		List<Order> list = dao.getOrders(cname);

		for (Order o : list) {
			System.out.println(o);
		}

	}

}