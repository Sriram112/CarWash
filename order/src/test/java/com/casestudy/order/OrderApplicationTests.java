package com.casestudy.order;

import com.casestudy.order.controller.orderController;
import com.casestudy.order.exceptionHandlers.APIRequestException;
import com.casestudy.order.models.Car;
import com.casestudy.order.models.OrderDetails;
import com.casestudy.order.repository.orderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;



@SpringBootTest
class OrderApplicationTests {

	@Autowired
	orderController oc;
	@MockBean
	orderRepository or;

	@Test
	public void allOrdersTest() {
		when(or.findAll()).thenReturn(Stream.of(
				new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" )),
				new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" )),
				new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" ))
		).collect(Collectors.toList()));
		assertEquals(3, oc.findallOrders().size());
	}


		@Test
	public void findOrderByIdTest() {
		OrderDetails order = new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" ) );
		when(or.findById("a" )).thenReturn(Optional.of(order));
		assertEquals(order, oc.findById("a" ));
	}

	@Test
	public void deleteOrderByIdTest() {
		when(or.findById("a" )).thenReturn(Optional.of(new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" ) )));
		Map<String, Boolean> response = new HashMap<>();
		response.put("order deleted", Boolean.TRUE);
		assertEquals(response, oc.deleteOrderById("a" ).getBody());
	}

	@Test
	public void addOrderTest() {
		//orderDetails expectedOrder = new orderDetails("a","z@gmail.com","NA","pack_1",1111115555,"888888","NA",new Car(1,"BMW","X5"),"careful cleaning");
		OrderDetails order = new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" ) );
		when(or.save(order)).thenReturn(order);
		assertEquals("NA", oc.addOrder(order).getStatus());
		assertEquals("NA", oc.addOrder(order).getWasherName());
	}

	@Test
	public void findUnassignedTest() {
		when(or.findAll()).thenReturn(Stream.of(
						new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" )),
						new OrderDetails("a", "z@gmail.com", "NA", "pack_1", 1111115555, "888888", "NA", new Car(1, "BMW", "X5" ) ),
						new OrderDetails("a", "z@gmail.com", "NA", "pack_1", 1111115555, "888888", "NA", new Car(1, "BMW", "X5" ) ),
						new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" ) ))
				.collect(Collectors.toList()));
		assertEquals(2, oc.getUnassignedOrders().size());
	}


	@Test
	public void updateStatusTest() {
		OrderDetails order = new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" ) );
		when(or.findById("a" )).thenReturn(Optional.of(order));
		//orderDetails order = new orderDetails("a","z@gmail.com","gwen","pack_1",1111115555,"888888","pending",new Car(1,"BMW","X5"),"careful cleaning");
		when(or.save(order)).thenReturn(order);
		assertEquals("Completed", oc.updateStatus("a" ).getBody().getStatus());
	}



	@Test
	public void cancelOrderTest() {
		OrderDetails order = new OrderDetails("a", "z@gmail.com", "NA", "pack_1", 1111115555, "888888", "NA", new Car(1, "BMW", "X5" ));
		when(or.findById("a" )).thenReturn(Optional.of(order));
		when(or.save(order)).thenReturn(order);
		//assertEquals("The order with ID -> " + order.getOrderId() + " is cancelled successfully", oc.cancelOrder(order));
		assertEquals("Cancelled", order.getStatus());
	}

	@Test
	public void getMyOrdersTest() {
		when(or.findAll()).thenReturn(Stream.of(
				new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" ) ),
				new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" ) ),
				new OrderDetails("a", "au@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" ) )
		).collect(Collectors.toList()));
		assertEquals(2, oc.getMyOrders("z@gmail.com" ).size());
	}

	@Test
	public void getPendingOrdersTest() {
		when(or.findAll()).thenReturn(Stream.of(
				new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" ) ),
				new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Completed", new Car(1, "BMW", "X5" ) ),
				new OrderDetails("a", "au@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Completed", new Car(1, "BMW", "X5" ) )
		).collect(Collectors.toList()));
		assertEquals(1, oc.getPendingOrders().size());
	}

	@Test
	public void getCompletedOrdersTest() {
		when(or.findAll()).thenReturn(Stream.of(
				new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" ) ),
				new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Completed", new Car(1, "BMW", "X5" ) ),
				new OrderDetails("a", "au@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Completed", new Car(1, "BMW", "X5" ) )
		).collect(Collectors.toList()));
		assertEquals(2, oc.getCompletedOrders().size());
	}

	@Test
	public void getCanceledOrdersTest() {
		when(or.findAll()).thenReturn(Stream.of(
				new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Pending", new Car(1, "BMW", "X5" ) ),
				new OrderDetails("a", "z@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Completed", new Car(1, "BMW", "X5" ) ),
				new OrderDetails("a", "au@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Completed", new Car(1, "BMW", "X5" ) ),
				new OrderDetails("a", "au@gmail.com", "gwen", "pack_1", 1111115555, "888888", "Cancelled", new Car(1, "BMW", "X5" ) )
		).collect(Collectors.toList()));
		assertEquals(1, oc.getCancelledOrders().size());
	}

	@Test
	public void assignWasherAlreadyExistsExceptionTest() {
		OrderDetails order = new OrderDetails("a", "z@gmail.com", "hee", "pack_1", 1111115555, "888888", "NA", new Car(1, "BMW", "X5" ) );
		OrderDetails ExistingOrder = new OrderDetails("a", "z@gmail.com", "Noor", "pack_1", 1111115555, "888888", "NA", new Car(1, "BMW", "X5" ));
		when(or.findById("a" )).thenReturn(Optional.of(ExistingOrder));
		when(or.save(ExistingOrder)).thenReturn(ExistingOrder);
		assertThrows(APIRequestException.class, () -> oc.assignWasher(order));
	}

	@Test
	public void assignWasherTest() {
		OrderDetails order = new OrderDetails("f", "z@gmail.com", "Noor", "pack_1", 1111115555, "888888", "NA", new Car(1, "BMW", "X5" ) );
		OrderDetails ExistedOrder = new OrderDetails("f", "z@gmail.com", "NA", "pack_1", 1111115555, "888888", "NA", new Car(1, "BMW", "X5" ) );
		System.out.println(ExistedOrder.getWasherName());
		System.out.println(order.getOrderId());
		when(or.findById("f" )).thenReturn(Optional.of(ExistedOrder));
		when(or.save(ExistedOrder)).thenReturn(ExistedOrder);
		String w = order.getWasherName();
		assertEquals(order.getWasherName(), oc.assignWasher(order).getBody().getWasherName());
		assertEquals("pending", ExistedOrder.getStatus());
		System.out.println(ExistedOrder.getWasherName());
		System.out.println(order.getWasherName());


	}




}