package com.airbnb.bankend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.airbnb.bankend.apirest.models.entity.SpecialPrices;

public interface ISpecialPricesDao extends CrudRepository<SpecialPrices, Long>{
	
	@Transactional
	@Modifying
	@Query("delete from SpecialPrices where id=:id and listing_Id=:idListing")
	public void deleteSpecialPricesById(@Param("id") Long id, @Param("idListing") Long idListing);
	
	@Query(value="select * from special_prices where listing_Id=:idListing ", nativeQuery=true)
	public List<SpecialPrices> findByListing(@Param("idListing") Long idListing);
}
