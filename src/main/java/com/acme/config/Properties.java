/*
 * @author = Radu
 */
package com.acme.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * The Class Properties.
 */
@Data
@ConfigurationProperties("prop")
@Configuration
public class Properties {

	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The client ID. */
	private String clientID;
	
	/** The carrier. */
	private String carrier;
	
	/** The country. */
	private String country;
	
	/** The limit. */
	private int limit;
	
	/** The base URL. */
	private String baseURL;
}
