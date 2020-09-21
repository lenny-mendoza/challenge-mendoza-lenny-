package com.airbnb.bankend.apirest.models.service;

import java.util.List;

import com.airbnb.bankend.apirest.models.entity.SpecialPrices;

public interface ISpecialPricesService {
	
	public List<SpecialPrices> findAll();

	public SpecialPrices findById(Long id);
	
	public SpecialPrices save(SpecialPrices specialPrices);
	
	public void delete(Long id);
	
	public void deleteSpecialPricesById(Long id, Long idListing);
	
	public List<SpecialPrices> findByListing(Long idListing);
}
