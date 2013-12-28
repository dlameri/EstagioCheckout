package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Produto")
public class Customer {

	@Id
	@SequenceGenerator(name = "customer_id", sequenceName = "customer_id")
	@GeneratedValue(generator = "customer_id", strategy = GenerationType.AUTO)
	@Column(name="CD_CLIENTE")
	private Long id;

}
