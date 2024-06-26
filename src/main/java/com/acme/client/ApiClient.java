/*
 * @author = Radu
 */
package com.acme.client;

import com.acme.model.LoginRequest;
import com.acme.model.LoginResponse;
import com.acme.model.ParcelShopResponse;

/**
 * The Interface ApiClient.
 */
public interface ApiClient {

	/**
	 * Login.
	 *
	 * @param request the request
	 * @return the login response
	 */
	public LoginResponse login(LoginRequest request);

	/**
	 * Gets the parcel shops.
	 *
	 * @param token   the token
	 * @param carrier the carrier
	 * @param country the country
	 * @param limit   the limit
	 * @return the parcel shops
	 */
	public ParcelShopResponse getParcelShops(String token, String carrier, String country, int limit);
}
