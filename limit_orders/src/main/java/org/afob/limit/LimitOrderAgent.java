package org.afob.limit;

import org.afob.execution.ExecutionClient;
import org.afob.prices.PriceListener;

import java.math.BigDecimal;

public class LimitOrderAgent implements PriceListener {

    public ExecutionClient ec;
    private final List<Orders> orders;

    public LimitOrderAgent(final ExecutionClient ec, List<Orders> orders) {
        this.orders = orders;
		this.ec = ec;
    }


    @Override
    public void priceTick(String productId, BigDecimal price) {

        BigDecimal bg = new BigDecimal("100");
    	
    	if(productId == "IBM" && price.compareTo(bg) < 1) {
    		try {
				ec.buy("IBM", 1000);
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
    	}

    }

    public void addOrder(String actionFlag, String productId, int amount, BigDecimal limit) {

			 Orders order = new Orders(actionFlag, productId, amount, limit);
			 orders.add(order);
             orders.stream().map(o->{if(o.actionFlag == sell && limit.compareTo(PriceListener.priceTick(o.productId))<1){ec.sell(o.productId,o.amount)} else if(o.actionFlag == buy && limit.compareTo(PriceListener.priceTick(o.productId))<1){ec.buy(o.productId,o.amount)} });
	}

}
