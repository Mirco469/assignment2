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
        double totBevande = 0; //Totale dei prodotti di bevande
        
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
            else if(p.getItemType()==MenuItem.Prodotti.Bevande)
            {
                totBevande += p.getPrice();
            }
        }
        if(tot < 10)
        {
            tot += 0.5;
        }
        if(numPanini > 5)
        {
            tot -= minPanini/2;
        }
        if(tot-totBevande > 50)
        {
            tot -= tot*0.1;
        }
        return tot;
    }
    
}
