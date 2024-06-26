/*
 * @author = Radu
 */
package com.acme.model;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class LoginResponse.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

	/** The email. */
	private String email;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The newsletter opt in. */
	private Boolean newsletterOptIn;

	/** The created at. */
	private OffsetDateTime createdAt;

	/** The username. */
	private String username;

	/** The access token. */
	private String accessToken;

	/** The expires in. */
	private Integer expiresIn;

	/** The refresh token. */
	private String refreshToken;
}
