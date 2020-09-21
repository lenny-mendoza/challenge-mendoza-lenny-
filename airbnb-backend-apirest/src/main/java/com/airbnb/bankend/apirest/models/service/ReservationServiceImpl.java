package com.airbnb.bankend.apirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airbnb.bankend.apirest.models.dao.IListingsDao;
import com.airbnb.bankend.apirest.models.dao.IReservationDao;
import com.airbnb.bankend.apirest.models.entity.Listings;
import com.airbnb.bankend.apirest.models.entity.Reservation;


@Service
public class ReservationServiceImpl implements IReservationService {
	
	@Autowired
	private IReservationDao reservationDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Reservation> findAll() {
		return (List<Reservation>) reservationDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Reservation findById(Long id) {
		return reservationDao.findById(id).orElse(null);	
	}

	@Override
	@Transactional
	public Reservation save(Reservation reservation) {
		return reservationDao.save(reservation);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		reservationDao.deleteById(id);
	}
	
}
