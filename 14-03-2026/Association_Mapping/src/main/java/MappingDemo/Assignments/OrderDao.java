package MappingDemo.Assignments;

import java.util.List;

public interface OrderDao {
	public boolean addOrder(Order order, int OrderID) throws Exception;
	public Order getOrder(int orderID) throws Exception;
	public List<Order> getOrders(String customerName) throws Exception;
}
