package com.airbnb.bankend.apirest.models.service;

import java.util.List;

import com.airbnb.bankend.apirest.models.entity.Listings;

public interface IListingsService {
	
	public List<Listings> findAll();

	public Listings findById(Long id);
	
	public Listings save(Listings listings);
	
	public void delete(Long id);
	

}
