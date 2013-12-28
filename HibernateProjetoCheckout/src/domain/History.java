package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Historico")
public class History {
	
	@Id
	@SequenceGenerator(name = "history_id", sequenceName = "history_id")
	@GeneratedValue(generator = "history_id", strategy = GenerationType.AUTO)
	@Column(name="CD_HISTORICO")
	private Long id;	
	@Column(name="DT_DATA", nullable=false)
	private Date date;	
	@Column(name="NR_PRECO", nullable=false)
	private Double price;
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="CD_CLIENTE", referencedColumnName="CD_CLIENTE", nullable=false)
	private Customer customer;
	@OneToMany(mappedBy="id.history") 
	private List<HistoryProduct> historyProduct; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDay() {
		return date;
	}
	public void setDay(Date day) {
		this.date = day;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}	
}