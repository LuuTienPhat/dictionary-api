package com.example.demo.service;

public interface StatService {
	float getMonthOverMonthGrowthRateOfCustomers();
	
	float getMonthOverMonthGrowthRateOfOrders();
	
	float getMonthOverMonthGrowthRateOfProducts();
	
	float getMonthOverMonthGrowthRateOfCategories();
	
	float getMonthOverMonthGrowthRateOfRevenue();
	
	float getDayOverDayGrowthRateOfOrders();
	
	float getTotalRevenueToday();
	
	float getTotalRevenueThisMonth();
}
