package MappingDemo.Assignments;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class DaoBackend implements OrderDao {

	EntityManagerFactory emf;
	EntityManager em;

	DaoBackend() {
		emf = Persistence.createEntityManagerFactory("JPA-PU");
		em = emf.createEntityManager();
	}

	@Override
	public boolean addOrder(Order order, int orderID) throws Exception {

		em.getTransaction().begin();

		Order or = new Order();
		or.setOrderID(orderID);
		or.setOrderAmount(order.getOrderAmount());
		or.setOrderDate(order.getOrderDate());
		or.setCustomer(order.getCustomer());

		em.persist(or);

		em.getTransaction().commit();

		return true;
	}

	@Override
	public Order getOrder(int orderID) throws Exception {

		em.getTransaction().begin();

		Query q = em.createQuery("SELECT o FROM Order o WHERE o.orderID = :oID");
		q.setParameter("oID", orderID);

		@SuppressWarnings("unchecked")
		List<Order> list = q.getResultList();

		Order result = null;

		if (!list.isEmpty()) {
			result = list.get(0);
		}

		em.getTransaction().commit();

		return result;
	}

	@Override
	public List<Order> getOrders(String customerName) throws Exception {

		em.getTransaction().begin();

		Query q = em.createQuery("SELECT o FROM Customer c JOIN c.orderlist o WHERE c.customerName = :custName");

		q.setParameter("custName", customerName);

		@SuppressWarnings("unchecked")
		List<Order> list = q.getResultList();

		em.getTransaction().commit();

		return list;
	}

	public void close() {
		em.close();
		emf.close();
	}
}