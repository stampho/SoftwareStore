package hu.sed.prf.softwarestore.controller.sale;

import java.util.List;

import hu.sed.prf.softwarestore.controller.core.AbstractDataModel;
import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.dao.sale.SaleDAO;
import hu.sed.prf.softwarestore.entity.sale.Sale;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class SaleDataModel extends AbstractDataModel<Sale, Long> {

	private static final long serialVersionUID = 5613779066546861879L;

	@Inject
	private SaleDAO saleDAO;

	@Override
	protected AbstractGenericDAO<Sale, Long> getEntityDao() {
		return saleDAO;
	}

	public void loadByUsername(String username) {
		List<Sale> userSales = saleDAO.getSalesByUsername(username);
		setList(userSales);
	}

}
