////////////////////////////////////////////////////////////////////
// [Mirco] [Giardina] [1136663]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

public class Bill implements TakeAwayBill{

    public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
        double tot = 0; //Totale conto
        int numPanini = 0;  //Numero panini comprati in totale
        double minPanini = Double.MAX_VALUE; //Il panino che costa meno
        
        for(MenuItem p : itemsOrdered)
        {
            tot += p.getPrice();
            if(p.getItemType()==MenuItem.Prodotti.Panini)
            {
                numPanini++;
                if(p.getPrice() < minPanini)
                {
                    minPanini = p.getPrice();
                }
            }
        }
        if(numPanini > 5)
        {
            tot -= minPanini/2;
        }
        return tot;
    }
    
}
