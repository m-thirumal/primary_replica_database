/**
 * 
 */
package in.thirumal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.thirumal.model.Order;
import in.thirumal.service.OrderService;

/**
 * 
 */
@RestController
@RequestMapping("/order")
public class OrderController {

 	
	private OrderService helloService;

	public OrderController(OrderService helloService) {
		super();
		this.helloService = helloService;
	}
	
	@GetMapping("/create")
	public int createOrder() {
		return helloService.createOrder();
	}
	
	@GetMapping("")
	public List<Order> listOrder() {
		return helloService.listOrder();
	}

}
