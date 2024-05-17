package com.maihe.pocdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PocDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocDeliveryApplication.class, args);

    }

}
