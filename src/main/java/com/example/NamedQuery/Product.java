package com.example.NamedQuery;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@NamedQueries({
    @NamedQuery(name = "Product.findByName",
            query = "SELECT p FROM Product p WHERE p.name = :productName"),
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Product.findOlderThanYear",
            query = "SELECT * FROM PRODUCT WHERE manufacturingYear < :year",
            resultClass = Product.class),
})

public class Product implements Serializable {

	private static final long serialVersionUID = 7797024568418369998L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "manufacturingYear")
    private int manufacturingYearXYZ;

    @Column(name = "batchID")
    private String batchID;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getManufacturingYear() {
		return manufacturingYear;
	}

	public void setManufacturingYear(int manufacturingYear) {
		this.manufacturingYear = manufacturingYear;
	}

	public String getBatchID() {
		return batchID;
	}

	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", manufacturingYear=" + manufacturingYear
				+ ", batchID=" + batchID + "]";
	}

    
}
