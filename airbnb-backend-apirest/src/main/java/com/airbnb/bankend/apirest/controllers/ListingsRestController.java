package com.airbnb.bankend.apirest.controllers;

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
import com.airbnb.bankend.apirest.models.entity.SpecialPrices;
import com.airbnb.bankend.apirest.models.service.IListingsService;
import com.airbnb.bankend.apirest.models.service.ISpecialPricesService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class ListingsRestController {
	
	@Autowired
	private IListingsService listingsService;
	@Autowired
	private ISpecialPricesService speciaPricesService;
	
	@GetMapping("/listings")
	public List<Listings> index(){
		
		List<Listings> listingsFinded = listingsService.findAll();
		for (Listings listings : listingsFinded) {
			List<SpecialPrices> specialPricesList = speciaPricesService.findByListing(listings.getId());
			listings.setSpecialPricesList(specialPricesList);
		}
		return listingsFinded;
	}
	
	@GetMapping("/listings/{id}")
	public Listings show(@PathVariable Long id) {
		List<SpecialPrices> specialPricesList = speciaPricesService.findByListing(id);
		System.out.println("size de la lista specialPricesList:: "+specialPricesList.size());
		Listings listingToReturn = listingsService.findById(id);
		listingToReturn.setSpecialPricesList(specialPricesList);
		return listingToReturn;
	}
	
	@PostMapping("/listings")
	@ResponseStatus(HttpStatus.CREATED)
	public Listings create(@RequestBody Listings listings) {
		String [] name = listings.getName().split(" ");
		StringBuilder slug = new StringBuilder();
		if (name.length>1) {
			for (int i=0; i<name.length;i++) {
				slug.append(name[i].toLowerCase());
				if (i<(name.length-1)) slug.append("-");
			}
		}
		listings.setSlug(slug.toString());
		return listingsService.save(listings);
	}
	
	@PutMapping("/listings/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Listings update(@RequestBody Listings listing, @PathVariable Long id) {
		Listings listingActual = listingsService.findById(id);
		listingActual.setName(listing.getName());
		listingActual.setDescription(listing.getDescription());
		listingActual.setAdults(listing.getAdults());
		listingActual.setChildren(listing.getChildren());
		listingActual.setIsPetsAllowed(listing.getIsPetsAllowed());
		listingActual.setBasePrice(listing.getBasePrice());
		listingActual.setCleaningFee(listing.getCleaningFee());
		listingActual.setImageUrl(listing.getImageUrl());
		listingActual.setWeeklyDiscount(listing.getWeeklyDiscount());
		listingActual.setMonthlyDiscount(listing.getMonthlyDiscount());
		return listingsService.save(listingActual);
	}
	
	@DeleteMapping("/listings/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		listingsService.delete(id);
		
	}
	
}
