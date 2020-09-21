package com.airbnb.bankend.apirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airbnb.bankend.apirest.models.dao.ISpecialPricesDao;
import com.airbnb.bankend.apirest.models.entity.Listings;
import com.airbnb.bankend.apirest.models.entity.SpecialPrices;


@Service
public class SpecialPricesServiceImpl implements ISpecialPricesService {
	
	@Autowired
	private ISpecialPricesDao specialPricesDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<SpecialPrices> findAll() {
		return (List<SpecialPrices>) specialPricesDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public SpecialPrices findById(Long id) {
		return specialPricesDao.findById(id).orElse(null);	
	}

	@Override
	@Transactional
	public SpecialPrices save(SpecialPrices specialPrices) {
		return specialPricesDao.save(specialPrices);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		specialPricesDao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void deleteSpecialPricesById(Long id, Long idListing) {
		specialPricesDao.deleteSpecialPricesById(id, idListing);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SpecialPrices> findByListing(Long idListing) {
		return specialPricesDao.findByListing(idListing);
	}
}
