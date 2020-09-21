package com.airbnb.bankend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="listings")
public class Listings implements Serializable{
	
	@XmlElement
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String ownerid;
	private String name;
	private String slug;
	private String description;
	private Integer adults;
	private Integer children;
	private boolean isPetsAllowed;
	private double basePrice;
	private double cleaningFee;
	private String imageUrl;
	private double weeklyDiscount;
	private double monthlyDiscount;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<SpecialPrices> specialPricesList;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@PrePersist
	public void prePersiste() {
		createAt = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAdults() {
		return adults;
	}

	public void setAdults(Integer adults) {
		this.adults = adults;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

	public Boolean getIsPetsAllowed() {
		return isPetsAllowed;
	}

	public void setIsPetsAllowed(Boolean isPetsAllowed) {
		this.isPetsAllowed = isPetsAllowed;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Double getCleaningFee() {
		return cleaningFee;
	}

	public void setCleaningFee(Double cleaningFee) {
		this.cleaningFee = cleaningFee;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getWeeklyDiscount() {
		return weeklyDiscount;
	}

	public void setWeeklyDiscount(Double weeklyDiscount) {
		this.weeklyDiscount = weeklyDiscount;
	}

	public Double getMonthlyDiscount() {
		return monthlyDiscount;
	}

	public void setMonthlyDiscount(Double monthlyDiscount) {
		this.monthlyDiscount = monthlyDiscount;
	}

	public List<SpecialPrices> getSpecialPricesList() {
		return specialPricesList;
	}

	public void setSpecialPricesList(List<SpecialPrices> specialPricesList) {
		this.specialPricesList = specialPricesList;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	private static final long serialVersionUID = 1L;

}
