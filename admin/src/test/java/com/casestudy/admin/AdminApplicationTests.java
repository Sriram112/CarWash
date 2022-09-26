package com.casestudy.admin;

import com.casestudy.admin.controller.AdminController;
import com.casestudy.admin.model.WashPacks;
import com.casestudy.admin.repository.WashPackRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdminApplicationTests {

	@Autowired
	AdminController ac;

	@MockBean
	WashPackRepo wr;

	@Test
	public void findallWpTest() {
		when(wr.findAll()).thenReturn(Stream.of(
				new WashPacks("1", "p1", 80, "ghhh"),
				new WashPacks("1", "p1", 80, "ghhh"),
				new WashPacks("1", "p1", 80, "ghhh")
		).collect(Collectors.toList()));
		assertEquals(3, ac.findallWP().size());
	}

	@Test
	public void findWpByIdTest() {
		WashPacks wp = new WashPacks("1", "p1", 80, "ghhh");
		when(wr.findById("1")).thenReturn(Optional.of(wp));
		assertEquals(wp, ac.findoneWP("1").getBody());
	}

	@Test
	public void deleteByIdTest() {
		when(wr.findById("1")).thenReturn(Optional.of(new WashPacks("1", "p1", 80, "ghhh")));
		Map<String, Boolean> response = new HashMap<>();
		response.put("Washpack Deleted", Boolean.TRUE);
		assertEquals(response, ac.deleteWP("1").getBody());
	}



	@Test
	public void addWPTest() {
		WashPacks wp = new WashPacks("1", "p1", 80, "ghhh");
		when(wr.save(wp)).thenReturn(wp);
		assertEquals(wp, ac.addWP(wp));
	}
}
