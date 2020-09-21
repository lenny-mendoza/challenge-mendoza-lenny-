package com.airbnb.bankend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.airbnb.bankend.apirest.models.entity.Reservation;

public interface IReservationDao extends CrudRepository<Reservation, Long>{
	
}
