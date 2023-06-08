package com.example.DercoRoomBackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.DercoRoomBackend.repositories.*;
import com.example.DercoRoomBackend.repositories.catalogue2.PaymentRepository;
import com.example.DercoRoomBackend.repositories.catalogue2.ShippingRepository;

@SpringBootApplication
public class DercoRoomBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DercoRoomBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo
	(
		UserRepository userRepository,
		CartRepository cartRepository,
		ShopRepository shopRepository,
		ProductRepository productRepository,
		ConditionRepository conditionRepository,
		SizeRepository sizeRepository,
		MaterialRepository materialRepository,
		TypeRepository typeRepository,
		SeasonRepository seasonRepository,
		PricetypeRepository pricetypeRepository,
		PaymentRepository paymentRepository,
		ShippingRepository shippingRepository
	)
	{
		return args -> {};
	}

}
