package com.airbnb.bankend.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.airbnb.bankend.apirest.models.entity.Listings;

public interface IListingsDao extends CrudRepository<Listings, Long>{

}
