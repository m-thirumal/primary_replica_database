/**
 * 
 */
package in.thirumal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.thirumal.dao.OrderRepository;
import in.thirumal.model.Order;
import in.thirumal.transaction.ReadOnly;

/**
 * 
 */
@Service
public class OrderService {
	
	private OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	@Transactional
	public int createOrder() {//Routes to Primary
		return orderRepository.create(Order.builder().name("T").build());
	}

	@Transactional(readOnly = true)
	@ReadOnly
	public List<Order> listOrder() {//Routes to Replica
		return orderRepository.list();
	}

}
