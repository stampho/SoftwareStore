package hu.sed.prf.softwarestore.controller.sale;

import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.dao.sale.SaleDAO;
import hu.sed.prf.softwarestore.entity.product.Product;
import hu.sed.prf.softwarestore.entity.sale.Sale;

import javax.annotation.PostConstruct;

import java.io.Serializable;
 

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.ChartSeries;
 
@Named
@ViewScoped
public class Chart implements Serializable {
 
	private static final long serialVersionUID = 8424999345575246254L;
	
	@Inject
	SaleDAO saleDAO;
 
    protected AbstractGenericDAO<Sale, Long> getEntityDao() {
		return saleDAO;
	}
    
    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
    
    private int totalQty = 0;
    
    @PostConstruct
    public void init() {
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }
 
    private BarChartModel initBarModel() {
    	List<Sale> totalSales = saleDAO.list();
    	HashMap<String, Integer> productsMap = new HashMap<String, Integer>();
    	
    	for (Sale actSale : totalSales){
    		List<Product> products = actSale.getProducts();
    		for (Product actProd : products){
    			String product_name = actProd.getCompany() + " " + actProd.getName() + " " + actProd.getVersion();
    			if (!productsMap.containsKey(product_name)) {
    				productsMap.put(product_name, 1);
    			} else {
    				Integer qty = (Integer)productsMap.get(product_name);
    				if (qty != null){
    					qty++;
    					if (qty > totalQty) totalQty = qty; 
    					productsMap.put(product_name, qty);
    				}
    			}
    		}
    	}
    	
        BarChartModel model = new BarChartModel();
        ChartSeries prods = new ChartSeries();
        prods.setLabel("Products");
        
        LinkedHashMap<String, Integer> productsLinkedMap = sortHashMapByValues(productsMap);
        
        for (Entry<String, Integer> entry : productsLinkedMap.entrySet()) {
        	prods.set(entry.getKey(), entry.getValue());
        }
 
        model.addSeries(prods);
         
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Bestseller products");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Products");
        xAxis.setTickAngle(-50);
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quantity");
        yAxis.setMin(0);
        yAxis.setMax(totalQty + 2);
    }
    
    /*
     * Sort Hashmap
     * FIXME
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public LinkedHashMap<String, Integer> sortHashMapByValues(HashMap passedMap) {
    	   List mapKeys = new ArrayList(passedMap.keySet());
    	   List mapValues = new ArrayList(passedMap.values());
    	   Collections.sort(mapValues);
    	   Collections.sort(mapKeys);

    	   LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();

    	   Iterator valueIt = mapValues.iterator();
    	   while (valueIt.hasNext()) {
    	       Object val = valueIt.next();
    	       Iterator keyIt = mapKeys.iterator();

    	       while (keyIt.hasNext()) {
    	           Object key = keyIt.next();
    	           String comp1 = passedMap.get(key).toString();
    	           String comp2 = val.toString();

    	           if (comp1.equals(comp2)){
    	               passedMap.remove(key);
    	               mapKeys.remove(key);
    	               sortedMap.put((String)key, (Integer)val);
    	               break;
    	           }

    	       }

    	   }
    	   return sortedMap;
    	}
}