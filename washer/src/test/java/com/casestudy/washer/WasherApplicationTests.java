package com.casestudy.washer;

import com.casestudy.washer.controller.WasherController;
import com.casestudy.washer.models.OrderDetails;
import com.casestudy.washer.service.WasherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Stream;

import static reactor.core.publisher.Mono.when;


@SpringBootTest
class WasherApplicationTests {

	@Autowired
	WasherController wc;

	@MockBean
	WasherService ws;
//
//	@Test
//	public void findUnassignedTest() {
//		when(ws.getUnassignedOrders()).thenReturn(Stream.of(
//				new OrderDetails()
//		))
//	}

}
