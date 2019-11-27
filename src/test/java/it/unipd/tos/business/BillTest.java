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
    
    // Issue 1
    @Test
    public void CalcoloTotaleContoConAlcuniProdotti() throws TakeAwayBillException {
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Primavera", 3.12));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "Patatine", 2.71));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Bevande, "Acqua", 1.41));
        double tot = conto.getOrderPrice(listaProdotti);
        assertEquals(7.24, tot,0.0);
    }
    
    //Issue 2
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
        //21.78 - 0.71
        assertEquals(21.07, tot,0.0);
    }
    
    //Issue 3
    @Test
    public void Sconto10PercentoSeTotaleTraFrittiEPaniniSupera50Euro() throws TakeAwayBillException
    {
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Filè", 4));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "is", 5));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "numberOne", 7));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "No", 2));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "Invarianti", 10));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Please", 15));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Bevande, "Please", 6));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "Please", 7));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "No", 9));
        double tot = conto.getOrderPrice(listaProdotti);
        //59 - 5.9
        assertEquals(53.1, tot,0.0);
    }
    
    @Test
    public void NienteSconto10PercentoSeTotaleSupera50EuroUsandoBevande() throws TakeAwayBillException
    {
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Filè", 4));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "is", 5));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "numberOne", 7));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "No", 2));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Bevande, "Invarianti", 10));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Bevande, "Please", 15));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Bevande, "Please", 6));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "Please", 7));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "No", 9));
        double tot = conto.getOrderPrice(listaProdotti);
        //34 euro di cibo, 31 euro di bevande = 65 (niente sconto)
        assertEquals(65, tot,0.0);
    }
}
