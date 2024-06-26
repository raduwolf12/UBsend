/*
 * @author = Radu
 */
package com.acme;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acme.client.ApiClient;
import com.acme.config.Properties;
import com.acme.model.LoginRequest;
import com.acme.model.LoginResponse;
import com.acme.model.ParcelShop;
import com.acme.model.ParcelShopResponse;
import com.acme.repository.ParcelShopRepository;
import com.acme.service.impl.ParcelShopServiceImpl;

/**
 * The Class ParcelShopServiceImplTest.
 */
public class ParcelShopServiceImplTest {

	/** The api client. */
	@Mock
	private ApiClient apiClient;

	/** The properties. */
	@Mock
	private Properties properties;

	/** The parcel shop repository. */
	@Mock
	private ParcelShopRepository parcelShopRepository;

	/** The parcel shop service. */
	@InjectMocks
	private ParcelShopServiceImpl parcelShopService;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		when(properties.getUsername()).thenReturn("assignment-test@ubsend.com");
		when(properties.getPassword()).thenReturn("p0DrmE)E+BQH$]KasMSb");
		when(properties.getCarrier()).thenReturn("CORREOS");
		when(properties.getCountry()).thenReturn("ES");
		when(properties.getLimit()).thenReturn(10);
	}

	/**
	 * Test process parcel shops.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testProcessParcelShops() throws Exception {
		LoginResponse mockLoginResponse = new LoginResponse();
		mockLoginResponse.setAccessToken("test-token");

		ParcelShopResponse mockParcelShopResponse = new ParcelShopResponse();
		List<ParcelShop> mockParcelShops = new ArrayList<>();

		mockParcelShops.add(new ParcelShop());
		mockParcelShopResponse.setParcelShops(mockParcelShops);

		when(apiClient.login(any(LoginRequest.class))).thenReturn(mockLoginResponse);
		when(apiClient.getParcelShops(anyString(), anyString(), anyString(), anyInt()))
				.thenReturn(mockParcelShopResponse);

		parcelShopService.callAndSave();

		verify(apiClient, times(1)).login(any(LoginRequest.class));
		verify(apiClient, times(1)).getParcelShops(anyString(), anyString(), anyString(), anyInt());
		verify(parcelShopRepository, times(1)).saveAll(mockParcelShops);
	}
}
