package domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class HistoryProductId {
	
	@ManyToOne
	@JoinColumn(name="CD_Produto")
	private Product product;

	@ManyToOne
	@JoinColumn(name="CD_Historico")
	private History history;
}