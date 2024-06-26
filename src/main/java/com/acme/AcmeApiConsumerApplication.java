/*
 * @author = Radu
 */
package com.acme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.acme.service.ParcelShopService;

/**
 * The Class AcmeApiConsumerApplication.
 */
@SpringBootApplication
public class AcmeApiConsumerApplication {

	/** The parcel shop service. */
	private final ParcelShopService parcelShopService;

	/**
	 * Instantiates a new acme api consumer application.
	 *
	 * @param parcelShopService the parcel shop service
	 */
	@Autowired
	public AcmeApiConsumerApplication(ParcelShopService parcelShopService) {
		this.parcelShopService = parcelShopService;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AcmeApiConsumerApplication.class, args);
	}

	/**
	 * Application runner.
	 *
	 * @return the application runner
	 */
	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> parcelShopService.callAndSave();
	}
}
