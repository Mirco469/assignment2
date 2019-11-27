package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class BillTest {

    @Test
    public void CalcoloTotaleConto() throws TakeAwayBillException {
        Bill conto = new Bill();
        List<MenuItem> listaProdotti = new ArrayList<MenuItem>();
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Filè", 3.12));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "is", 2.71));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Bevande, "numberOne", 1.41));
        double tot = conto.getOrderPrice(listaProdotti);
        assertEquals(7.24, tot,0.0);
    }

}
