package com.airbnb.bankend.apirest.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.airbnb.bankend.apirest.models.entity.Listings;
import com.airbnb.bankend.apirest.models.entity.Reservation;
import com.airbnb.bankend.apirest.models.entity.SpecialPrices;
import com.airbnb.bankend.apirest.models.service.IListingsService;
import com.airbnb.bankend.apirest.models.service.IReservationService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class ReservationRestController {
	
	@Autowired
	private IReservationService reservationService;
	
	@Autowired
	private IListingsService listingsService;
	
	@GetMapping("/reservation")
	public List<Reservation> index(){
		return reservationService.findAll();
	}
	
	@GetMapping("/reservation/{id}")
	public Reservation show(@PathVariable Long id) {
		return reservationService.findById(id);
	}
	
	@PostMapping("/reservation")
	@ResponseStatus(HttpStatus.CREATED)
	public Reservation create(@RequestBody Reservation reservation) {
		return reservationService.save(reservation);
	}
	
	@PutMapping("/reservation/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Reservation update(@RequestBody Reservation reservation, @PathVariable Long id) {
		Reservation reservationActual = reservationService.findById(id);
		reservationActual.setCheckin(reservation.getCheckin());
		reservationActual.setCheckout(reservation.getCheckout());
		reservationActual.setNightsCount(reservation.getNightsCount());
		reservationActual.setDiscount(reservation.getDiscount());
		reservationActual.setNightsCost(reservation.getNightsCost());
		return reservationService.save(reservationActual);
	}
	
	@DeleteMapping("/reservation/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		reservationService.delete(id);
		
	}
	
	@GetMapping("/{id}/checkout")
	public Reservation show(@PathVariable Long id, @RequestBody Reservation reservation) {
		Date today = new Date ();
		double costNight;
		double discount;
		double total;
		int days = (int) ((reservation.getCheckout().getTime()-reservation.getCheckin().getTime()) / 1000 / 60 / 60 / 24);
		if (today.compareTo(reservation.getCheckin()) > 0 || today.compareTo(reservation.getCheckout()) > 0) {
			throw new IllegalArgumentException("Error => Checkin and Checkout should be greater than today's date. ");
		}else if (days > 28) {
			throw new IllegalArgumentException("Error => Can not book a listing for more then 28 days. ");	
		}else if (reservation.getCheckout().compareTo(reservation.getCheckin()) < 0) {
			throw new IllegalArgumentException("Error => Checkout should be greater than Checkin. ");	
		}
		Listings listings = listingsService.findById(id);
		if (listings != null) {
			costNight=listings.getBasePrice();
			if (listings.getSpecialPricesList().size()>0) {
				for (SpecialPrices specialPrices: listings.getSpecialPricesList() ) {
					if ((specialPrices.getDatePrice().compareTo(reservation.getCheckin()) == 0) || (specialPrices.getDatePrice().compareTo(reservation.getCheckout()) == 0) )  {
						costNight=specialPrices.getPrice();
					}
				}
			}
			if (costNight == listings.getBasePrice()) {
				discount= 0;	
			}else {
				discount= (costNight * 100) / listings.getBasePrice();
			}
			total = costNight*days;
			Reservation reservationToShow = new Reservation();
			reservationToShow.setCheckin(reservation.getCheckin());
			reservationToShow.setCheckout(reservation.getCheckout());
			reservationToShow.setNightsCount(days);
			reservationToShow.setNightsCost(costNight);
			reservationToShow.setDiscount(discount);
			reservationToShow.setCleaningFee(listings.getCleaningFee());
			reservationToShow.setTotal(total);
			
			return reservationToShow;
		}else {
			throw new IllegalArgumentException("Error => The listing does not exist");	
		}
		
	}
	

}
