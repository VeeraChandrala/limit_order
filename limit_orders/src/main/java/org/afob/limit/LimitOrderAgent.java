package org.afob.limit;

import org.afob.execution.ExecutionClient;
import org.afob.prices.PriceListener;

import java.math.BigDecimal;

public class LimitOrderAgent implements PriceListener {

    public ExecutionClient ec;
    private final HashMap<String, List<Orders>> ordersMap;

    public LimitOrderAgent(final ExecutionClient ec, List<Orders> orders) {
        this.orders = orders;
		this.ec = ec;
    }


    @Override
    public void priceTick(String productId1, BigDecimal price1) {

        

        /*BigDecimal bg = new BigDecimal("100");
    	
    	if(productId == "IBM" && price.compareTo(bg) < 1) {
    		try {
				ec.buy("IBM", 1000);
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
    	}*/
        List<Order> orders = ordersMap.get(productId1);
        execute(productId, orders, price1);


    }

    public void addOrder(String actionFlag, String productId, int amount, BigDecimal limit) {

			 Order order = new Order(actionFlag, productId, amount, limit);
             if(ordersMap.contains(productId))
             ordersMap.put(productId, ordersMap.get(productId).add(order));
             else
			 ordersMap.put(productId, List.of(order));

    }

    public void execute( List<Orders> orders, price1)
    {
        String productId = orders.get(0).getProductId();
        List<Orders> orders1 = new Arraylist<>();
     While(o::Orders)
        {
            if(o.getActionFlag() == "sell" && o.getLimit()>=price1)
            {
                ec.sell(productId1,order.getAmount);
                orders1.add(o);
            }
            if(o.getActionFlag() == "buy" && order.getLimit()<=price1)
            {
                ec.buy(productId1,order.getAmount);
                orders1.add(o) ;
            }
        }
    ordersMap.put(productId,orders.stream().filter(o-> orders1.isNotPresent(o)).collect(Collectors.toList()));
    }

}
