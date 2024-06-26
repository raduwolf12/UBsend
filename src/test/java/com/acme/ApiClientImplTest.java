/*
 * @author = Radu
 */
package com.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.acme.client.impl.ApiClientImpl;
import com.acme.config.Properties;
import com.acme.model.LoginRequest;
import com.acme.model.LoginResponse;
import com.acme.model.ParcelShop;
import com.acme.model.ParcelShopResponse;

/**
 * The Class ApiClientImplTest.
 */
public class ApiClientImplTest {

	/** The rest template. */
	@Mock
	private RestTemplate restTemplate;

	/** The properties. */
	@Mock
	private Properties properties;

	/** The api client. */
	@InjectMocks
	private ApiClientImpl apiClient;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		when(properties.getBaseURL()).thenReturn("https://staging-api.ubsend.io/v1/");
		when(properties.getClientID()).thenReturn("15342");
	}

	/**
	 * Test login.
	 */
	@Test
	void testLogin() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("test");
		loginRequest.setPassword("test");

		LoginResponse mockResponse = new LoginResponse();
		mockResponse.setAccessToken("test-token");

		when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(LoginResponse.class)))
				.thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

		LoginResponse response = apiClient.login(loginRequest);
		assertNotNull(response);
		assertNotNull(response.getAccessToken());
	}

	/**
	 * Test login fail.
	 */
	@Test
	public void testLogin_Fail() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("test");
		loginRequest.setPassword("test");

		when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(LoginResponse.class)))
				.thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

		assertThrows(RuntimeException.class, () -> {
			apiClient.login(loginRequest);
		});
	}

	/**
	 * Test get parcel shops.
	 */
	@Test
	void testGetParcelShops() {
		String token = "test-token";
		String carrier = "CORREOS";
		String country = "ES";
		int limit = 10;

		List<ParcelShop> mockParcelShops = new ArrayList<>();
		mockParcelShops.add(new ParcelShop());
		ResponseEntity<List<ParcelShop>> mockResponseEntity = ResponseEntity.ok(mockParcelShops);

		ParameterizedTypeReference<List<ParcelShop>> responseType = new ParameterizedTypeReference<List<ParcelShop>>() {
		};

		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(responseType)))
				.thenReturn(mockResponseEntity);

		ParcelShopResponse response = apiClient.getParcelShops(token, carrier, country, limit);
		assertNotNull(response);
		assertNotNull(response.getParcelShops());
		assertEquals(mockParcelShops.size(), response.getParcelShops().size());
	}

	/**
	 * Test get parcel shops fail.
	 */
	@Test
	void testGetParcelShops_Fail() {
		String token = "test-token";
		String carrier = "CORREOS";
		String country = "ES";
		int limit = 10;

		ParameterizedTypeReference<List<ParcelShop>> responseType = new ParameterizedTypeReference<List<ParcelShop>>() {
		};

		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(responseType)))
				.thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

		assertThrows(RuntimeException.class, () -> {
			apiClient.getParcelShops(token, carrier, country, limit);
		});
	}

}
