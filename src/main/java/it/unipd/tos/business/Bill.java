////////////////////////////////////////////////////////////////////
// [Mirco] [Giardina] [1136663]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

public class Bill implements TakeAwayBill{

    public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
        double tot = 0;
        for(MenuItem p : itemsOrdered)
        {
            tot += p.getPrice();
        }
        return tot;
    }
    
}
