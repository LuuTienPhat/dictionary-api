package com.example.demo.api;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.entities.Order;
import com.example.demo.models.ResponseObject;
import com.example.demo.models.Stat;
import com.example.demo.repo.OrderRepo;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.StatService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
@Slf4j
public class StatController {
	private final StatService statService;
	private final UserService userService;
	private final OrderService orderService;
	private final CategoryService categoryService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> one(@PathVariable Long id) {
		return ResponseEntity.ok().body(userService.getUser(id));
	}

	@GetMapping(value = "")
	public ResponseEntity<ResponseObject> one() {
		LocalDate now = LocalDate.now();

		Stat stat = new Stat();
		stat.setGrowthRateOfCustomers(statService.getMonthOverMonthGrowthRateOfCustomers());
		stat.setGrowthRateOfRevenue(statService.getMonthOverMonthGrowthRateOfRevenue());
		stat.setGrowthRateOfCategories(statService.getMonthOverMonthGrowthRateOfCategories());
		stat.setGrowthRateOfOrders(statService.getMonthOverMonthGrowthRateOfOrders());

		stat.setNumberOfCategories(categoryService.count());
		stat.setNumberOfCustomers(Long.valueOf(userService.getUsersAreCustomers().size()));

		stat.setNumberOfOrdersThisMonth(
				orderService.countByOrderDateBetween(now.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(),
						now.with(TemporalAdjusters.lastDayOfMonth()).atStartOfDay()));

		stat.setGrowthRateOfTodayRevenueCompareToYesterday(statService.getDayOverDayGrowthRateOfOrders());
		
		stat.setTotalRevenueToday(statService.getTotalRevenueToday());
		
		stat.setTotalRevenueThisMonth(statService.getTotalRevenueThisMonth());
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", HttpStatus.OK.value(), "Fetch Statiscial Successfully!", stat));
	}
}
