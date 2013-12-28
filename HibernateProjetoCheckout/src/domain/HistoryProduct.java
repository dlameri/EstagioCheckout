package domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;

@AssociationOverrides({
	@AssociationOverride(name="pk.product", joinColumns=@JoinColumn(name="CD_Produto")),
	@AssociationOverride(name="pk.history", joinColumns=@JoinColumn(name="CD_Historico"))
})
public class HistoryProduct {

	@EmbeddedId
	private HistoryProductId id;
}