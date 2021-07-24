package br.com.udemy.osudemy.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.udemy.osudemy.entities.pk.OrderItemPK;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// como esse id é composto, ele tem que ser instanciado para não dar NullPointerException
	// PQ O ID É COMPOSTO DE ORDER_ID E PRODUCT_ID NA CLASSE OrderItemPK
	@EmbeddedId   
	private OrderItemPK id = new OrderItemPK(); 
	
	private Integer quantity;
	
	private Double price;	
	
	public OrderItem() {

	}
	
	//  CONSTRUTOR COM order e product setados manualmente
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		id.setOrder(order); 
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	// GETTERS AND SETTERS DA PK COMPOSTA DE ORDER E PRODUCT
	
	@JsonIgnore   // PARA NÃO ENTRAR NO LOOP 
	public Order getOrder() {
		return this.id.getOrder();
	}
	
	public void setOrder(Order order) {
		this.id.setOrder(order);
	}
	
	public Product getProduct() {
		return this.id.getProduct();
	}
	
	public void setProduct(Product product) {
		this.id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
