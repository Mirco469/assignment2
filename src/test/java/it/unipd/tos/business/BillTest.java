package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class BillTest {
    
    Bill conto;
    List<MenuItem> listaProdotti;
    
    @Before
    public void before()
    {
        conto = new Bill();
        listaProdotti = new ArrayList<MenuItem>();
    }
    
    @Test
    public void CalcoloTotaleContoConAlcuniProdotti() throws TakeAwayBillException {
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Primavera", 3.12));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "Patatine", 2.71));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Bevande, "Acqua", 1.41));
        double tot = conto.getOrderPrice(listaProdotti);
        assertEquals(7.24, tot,0.0);
    }
    
    @Test
    public void ScontoSulPaninoMenoCaroSeOrdinatiPiuDiCinquePanini() throws TakeAwayBillException
    {
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Filè", 3.12));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "is", 2.71));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "numberOne", 1.42));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "No", 3.41));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Invarianti", 7.41));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Please", 3.71));
        double tot = conto.getOrderPrice(listaProdotti);
        //22.78 - 1.42/2
        assertEquals(21.07, tot,0.0);
    }
}
