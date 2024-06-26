/*
 * @author = Radu
 */
package com.acme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class LoginRequest.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

	/** The username. */
	private String username;

	/** The password. */
	private String password;

	/** The omit claims. */
	private Boolean omitClaims;
}
