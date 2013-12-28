package domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Produto")
public class Product {

	@Id
	@SequenceGenerator(name = "product_id", sequenceName = "product_id")
	@GeneratedValue(generator = "product_id", strategy = GenerationType.AUTO)
	@Column(name="CD_PRODUTO")
	private Long id;
	@OneToMany(mappedBy="id.produto") 
	private List<HistoryProduct> historyProduct; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}