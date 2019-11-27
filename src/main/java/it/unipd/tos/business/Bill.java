////////////////////////////////////////////////////////////////////
// [Mirco] [Giardina] [1136663]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

public class Bill implements TakeAwayBill{

    public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
        return 0;
    }
    
}
