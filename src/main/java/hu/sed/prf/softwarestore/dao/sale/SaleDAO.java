package hu.sed.prf.softwarestore.dao.sale;

import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.entity.sale.Sale;

public class SaleDAO extends AbstractGenericDAO<Sale, Long> {

	private static final long serialVersionUID = -5345773105995190878L;

	public SaleDAO() {
		super(Sale.class);
	}

}
