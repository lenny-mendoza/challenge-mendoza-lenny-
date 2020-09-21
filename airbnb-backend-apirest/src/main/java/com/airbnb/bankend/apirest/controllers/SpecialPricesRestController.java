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

import com.airbnb.bankend.apirest.models.entity.SpecialPrices;
import com.airbnb.bankend.apirest.models.service.ISpecialPricesService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class SpecialPricesRestController {
	
	@Autowired
	private ISpecialPricesService specialPricesService;
	
	@GetMapping("/specialPrice")
	public List<SpecialPrices> index(){
		return specialPricesService.findAll();
	}
	
	@GetMapping("/specialPrice/{id}")
	public SpecialPrices show(@PathVariable Long id) {
		return specialPricesService.findById(id);
	}
	
	@PostMapping("/{listingId}/specialPrice")
	@ResponseStatus(HttpStatus.CREATED)
	public SpecialPrices create(@PathVariable Long listingId, @RequestBody SpecialPrices specialPrice) {
		specialPrice.setListingId(listingId);
		System.out.println("Precio "+specialPrice.getPrice());
		System.out.println("DatePrecio "+specialPrice.getDatePrice());
		
		return specialPricesService.save(specialPrice);
	}
	
	@PutMapping("/specialPrice/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public SpecialPrices update(@RequestBody SpecialPrices specialPrice, @PathVariable Long id) {
		SpecialPrices specialPriceActual = specialPricesService.findById(id);
		specialPriceActual.setDatePrice(specialPrice.getDatePrice());
		specialPriceActual.setPrice(specialPrice.getPrice());
		return specialPricesService.save(specialPriceActual);
	}
	
	@DeleteMapping("/specialPrice/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		specialPricesService.delete(id);
		
	}
	
	@DeleteMapping("/{idListing}/specialPrice/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public SpecialPrices deleteSpecialPricesById(@PathVariable Long id, @PathVariable Long idListing) {
		SpecialPrices specialPriceToDelete = specialPricesService.findById(id);
		specialPricesService.deleteSpecialPricesById(id, idListing);
		return specialPriceToDelete;
	}
	
	
	@GetMapping("/findByListing/{idListing}")
	public List<SpecialPrices> findByListing(@PathVariable Long idListing){
		return specialPricesService.findByListing(idListing);
	}
}
