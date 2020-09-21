package com.airbnb.bankend.apirest.models.service;

import java.util.List;

import com.airbnb.bankend.apirest.models.entity.Listings;
import com.airbnb.bankend.apirest.models.entity.Reservation;

public interface IReservationService {
	
	public List<Reservation> findAll();

	public Reservation findById(Long id);
	
	public Reservation save(Reservation reservation);
	
	public void delete(Long id);
	

}
