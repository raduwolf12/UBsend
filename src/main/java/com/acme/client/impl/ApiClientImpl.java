/*
 * @author = Radu
 */
package com.acme.client.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.acme.client.ApiClient;
import com.acme.config.Properties;
import com.acme.model.LoginRequest;
import com.acme.model.LoginResponse;
import com.acme.model.ParcelShop;
import com.acme.model.ParcelShopResponse;

/**
 * The Class ApiClientImpl.
 */
@Component
public class ApiClientImpl implements ApiClient {

	/** The rest template. */
	private final RestTemplate restTemplate;

	/** The properties. */
	private final Properties properties;

	/**
	 * Instantiates a new api client impl.
	 *
	 * @param restTemplate the rest template
	 * @param properties   the properties
	 */
	public ApiClientImpl(RestTemplate restTemplate, Properties properties) {
		this.restTemplate = restTemplate;
		this.properties = properties;
	}

	/**
	 * Login.
	 *
	 * @param request the request
	 * @return the login response
	 */
	@Override
	public LoginResponse login(LoginRequest request) {
		try {
			String url = new StringBuilder().append(properties.getBaseURL()).append("auth/login").toString();

			HttpHeaders headers = new HttpHeaders();
			headers.set("ClientId", properties.getClientID());

			HttpEntity<LoginRequest> entity = new HttpEntity<>(request, headers);

			ResponseEntity<LoginResponse> responseEntity = restTemplate.postForEntity(url, entity, LoginResponse.class);

			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				throw new RuntimeException("Invalid login with status code: " + responseEntity.getStatusCode());
			}

			return responseEntity.getBody();
		} catch (Exception e) {
			throw new RuntimeException("Error during login: " + e.getMessage(), e);
		}
	}

	/**
	 * Gets the parcel shops.
	 *
	 * @param token   the token
	 * @param carrier the carrier
	 * @param country the country
	 * @param limit   the limit
	 * @return the parcel shops
	 */
	@Override
	public ParcelShopResponse getParcelShops(String token, String carrier, String country, int limit) {
		try {
			String url = new StringBuilder().append(properties.getBaseURL())
					.append("parcel-shops/parcel-shops?radius=100000").toString();

			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParam("carrier", carrier)
					.queryParam("country", country).queryParam("limit", limit);

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + token);
			headers.set("ClientId", properties.getClientID());
			HttpEntity<Void> entity = new HttpEntity<>(headers);

			ResponseEntity<List<ParcelShop>> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
					entity, new ParameterizedTypeReference<List<ParcelShop>>() {
					});

			if (response.getStatusCode() != HttpStatus.OK) {
				throw new RuntimeException("Invalid login with status code: " + response.getStatusCode());
			}

			List<ParcelShop> parcelShops = response.getBody();
			if (parcelShops == null) {
				throw new RuntimeException("Error response body is null");
			}

			return new ParcelShopResponse(parcelShops);
		} catch (Exception e) {
			throw new RuntimeException("Error during get parcel shop request: " + e.getMessage(), e);
		}

	}

}
