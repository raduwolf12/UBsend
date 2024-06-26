/*
 * @author = Radu
 */
package com.acme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.client.ApiClient;
import com.acme.config.Properties;
import com.acme.model.LoginRequest;
import com.acme.model.LoginResponse;
import com.acme.model.ParcelShop;
import com.acme.model.ParcelShopResponse;
import com.acme.repository.ParcelShopRepository;
import com.acme.service.ParcelShopService;

/**
 * The Class ParcelShopServiceImpl.
 */
@Service
public class ParcelShopServiceImpl implements ParcelShopService {

	/** The api client. */
	private final ApiClient apiClient;

	/** The parcel shop repository. */
	private final ParcelShopRepository parcelShopRepository;

	/** The properties. */
	private final Properties properties;

	/**
	 * Instantiates a new parcel shop service impl.
	 *
	 * @param apiClient            the api client
	 * @param parcelShopRepository the parcel shop repository
	 * @param properties           the properties
	 */
	@Autowired
	public ParcelShopServiceImpl(ApiClient apiClient, ParcelShopRepository parcelShopRepository,
			Properties properties) {
		this.apiClient = apiClient;
		this.parcelShopRepository = parcelShopRepository;
		this.properties = properties;
	}

	/**
	 * Call and save.
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void callAndSave() throws Exception {
		try {
			LoginRequest loginRequest = new LoginRequest(properties.getUsername(), properties.getPassword(), null);

			LoginResponse response = apiClient.login(loginRequest);
			if (response == null) {
				throw new Exception("Login failed, the response is null");
			}
			if (response.getAccessToken() == null) {
				throw new Exception("Login failed, access token is null");
			}

			ParcelShopResponse shopResponse = apiClient.getParcelShops(response.getAccessToken(),
					properties.getCarrier(), properties.getCountry(), properties.getLimit());
			List<ParcelShop> list = shopResponse.getParcelShops();
			if (list != null) {
				parcelShopRepository.saveAll(list);
			} else {
				throw new Exception("Parcel shop list is null, something went wrong!");
			}
		} catch (Exception e) {
			throw new Exception("Error processing parcel shops: " + e.getMessage(), e);
		}

	}

}
