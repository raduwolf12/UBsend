/*
 * @author = Radu
 */
package com.acme.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class ParcelShopResponse.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParcelShopResponse {
	
	/** The parcel shops. */
	private List<ParcelShop> parcelShops;
}
