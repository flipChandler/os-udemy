package br.com.udemy.osudemy.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable{
	
	// FAZ SENTIDO VER OS PRODUTOS QUE COMPREI EM RELAÇÃO AO PEDIDO QUE ELE ESTÁ, E NÃO COM OS ITENS_PEDIDO
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	@Column(name = "img_url")
	private String imgUrl;
	
	/*
	 	muitos p/ muitos, cria-se uma tabela de associação
		@JoinTable = nome da tabela de associação
		joinColumns = pk da tabela atual
		inverseJoinColumns = pk da outra tabela	 
	*/
	@ManyToMany
	@JoinTable(name = "tb_product_category", 
	joinColumns = @JoinColumn(name = "product_id"),
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	
	/* One product To Many OrderItem 
	   id.product = id.getProduct de OrderItem */
	@OneToMany(mappedBy = "id.product")   
	private Set<OrderItem> items = new HashSet<>();
	
	public Product() {

	}

	public Product(Long id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}	
	
	public Set<Category> getCategories() {
		return categories;
	}
	
	// VARRER A COLLECTION DE OrderItem, ADICIONANDO CADA PRODUTO NO set 
	// SEMPRE NO GET, PQ getProduct tem getOrderItem, que tem getProduct ... Jackson entra em loop
	// AO BUSCAR UM PRODUTO, NÃO EXIBA OS SEUS PEDIDOS
	@JsonIgnore 
	public Set<Order> getOrders() {
		Set<Order> set = new HashSet<>();
		for (OrderItem item : this.items) {
			set.add(item.getOrder());
		}
		return set;
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
