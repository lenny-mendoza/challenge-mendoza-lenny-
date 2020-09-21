package com.airbnb.bankend.apirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airbnb.bankend.apirest.models.dao.IListingsDao;
import com.airbnb.bankend.apirest.models.entity.Listings;


@Service
public class ListingsServiceImpl implements IListingsService {
	
	@Autowired
	private IListingsDao listingsDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Listings> findAll() {
		return (List<Listings>) listingsDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Listings findById(Long id) {
		return listingsDao.findById(id).orElse(null);	
	}

	@Override
	@Transactional
	public Listings save(Listings listings) {
		return listingsDao.save(listings);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		listingsDao.deleteById(id);
	}
	
}
