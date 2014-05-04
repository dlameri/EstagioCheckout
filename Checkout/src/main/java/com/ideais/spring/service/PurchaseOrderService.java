package com.ideais.spring.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideais.spring.dao.interfaces.PurchaseOrderDaoBehavior;
import com.ideais.spring.domain.checkout.Item;
import com.ideais.spring.domain.checkout.PurchaseOrder;
import com.ideais.spring.exceptions.MissingQuantityStockException;
import com.ideais.spring.service.interfaces.ItemServiceBehavior;
import com.ideais.spring.service.interfaces.PurchaseOrderServiceBehavior;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("purchaseOrderService")
public class PurchaseOrderService implements PurchaseOrderServiceBehavior {
	
	@Autowired
    private PurchaseOrderDaoBehavior purchaseOrderDao;
	@Autowired
    private ItemServiceBehavior itemService;
    private static final String ORDER_KEY = "order";

    @Override
    public PurchaseOrder findById(Long id, Long customerId) {
        return purchaseOrderDao.findById(id, customerId);
    }
    
    @Override
    public PurchaseOrder findByPurchaseDate(Date date, Long customerId) {
        return purchaseOrderDao.findByPurchaseDate(date, customerId);
    }
    
    @Override
    public List<PurchaseOrder> findAllByCustomerId(Long customerId) {
        return purchaseOrderDao.findAll(customerId);
    }

    @Override
    public void save(PurchaseOrder object) {
    	purchaseOrderDao.saveOrUpdate(object);
    }

    @Override
    public void remove(PurchaseOrder object) {
    	purchaseOrderDao.remove(object);
    }
    
    @Override
    public void setPurchaseOrderInSession(HttpServletRequest request, PurchaseOrder purchaseOrder) {
		request.getSession().setAttribute(ORDER_KEY, purchaseOrder);
    }
    
    @Override
    public void requestItemsFromStock(PurchaseOrder order) throws IOException, JSONException, MissingQuantityStockException {
    	if (order != null && order.getShoppingCart() != null && order.getShoppingCart().getShoppingCartLines() != null) {
    		
	    	for (int i = 0; i < order.getShoppingCart().getShoppingCartLines().size(); i++) {
	    		Item item = itemService.getItem(order.getShoppingCart().getShoppingCartLines().get(i).getItemId());
	    		if (item != null) {
	    			order.getShoppingCart().getShoppingCartLines().get(i).setItem(item);
	    		}
	    	}
    	}
    }

	@Override
	public void removeOrderFromSession(HttpSession session) {
		session.setAttribute(ORDER_KEY, null);
	}

}