/*
 * @author = Radu
 */
package com.acme.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.acme.model.ParcelShop;

/**
 * The Interface ParcelShopRepository.
 */
@Repository
public interface ParcelShopRepository extends MongoRepository<ParcelShop, String> {
}
