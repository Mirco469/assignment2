package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class BillTest {
    
    Bill conto;
    List<MenuItem> listaProdotti;
    double tot;
    
    @Before
    public void before()
    {
        conto = new Bill();
        listaProdotti = new ArrayList<MenuItem>();
        tot = 0;
    }
    
    // Issue 1
    @Test
    public void testCalcoloTotaleContoConAlcuniProdotti() throws TakeAwayBillException {
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Primavera", 3.12));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "Patatine", 2.71));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Bevande, "Acqua", 1.41));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "Gamberi", 5));
        tot = conto.getOrderPrice(listaProdotti);
        assertEquals(12.24, tot,0.0);
    }
    
    //Issue 2
    @Test
    public void testScontoSulPaninoMenoCaroSeOrdinatiPiuDiCinquePanini() throws TakeAwayBillException
    {
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Filè", 3.12));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "is", 2.71));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "numberOne", 1.42));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "No", 3.41));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Invarianti", 7.41));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Please", 3.71));
        tot = conto.getOrderPrice(listaProdotti);
        //21.78 - 0.71
        assertEquals(21.07, tot,0.0);
    }
    
    //Issue 3
    @Test
    public void testSconto10PercentoSeTotaleTraFrittiEPaniniSupera50Euro() throws TakeAwayBillException
    {
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Filè", 4));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "is", 5));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "numberOne", 7));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "No", 2));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "Invarianti", 10));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Please", 15));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Bevande, "Please", 6));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "Please", 7));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Fritti, "No", 9));
        tot = conto.getOrderPrice(listaProdotti);
        //65 - 65*0.1 (lo sconto del 10% si applica a tutto il totale)
        assertEquals(58.5, tot,0.0);
    }
    
    @Test
    public void testNienteSconto10PercentoSeTotaleSupera50EuroUsandoBevande() throws TakeAwayBillException
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
        tot = conto.getOrderPrice(listaProdotti);
        //34 euro di cibo, 31 euro di bevande = 65 (niente sconto)
        assertEquals(65, tot,0.0);
    }
    
    //Issue 5
    @Test
    public void testCommissioneSeImportoMinoreDi10Euro() throws TakeAwayBillException
    {
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Panini, "Filè", 4));
        listaProdotti.add(new MenuItem(MenuItem.Prodotti.Bevande, "is", 5));
        tot = conto.getOrderPrice(listaProdotti);
        //Commissione di 0.5
        assertEquals(9.5, tot,0.0);
    }
    
    //Issue 4
    @org.junit.Rule
    public ExpectedException error= ExpectedException.none();
    
    @Test
    public void testNonPiuDi30Elementi() throws TakeAwayBillException
    {
        error.expect(TakeAwayBillException.class);
        error.expectMessage("Errore, ordine contiene più di 30 elementi");
        
        for(int i=0;i<31;i++)
        {
            listaProdotti.add(new MenuItem(MenuItem.Prodotti.Bevande, "Filè"+i, 4));
        }
        tot = conto.getOrderPrice(listaProdotti);
        //Mai raggiunta
    }
    
    //Altri test
    
    @Test
    public void testOrdineSenzaListaProdotti() throws TakeAwayBillException
    {
        error.expect(IllegalArgumentException.class);
        error.expectMessage("Lista prodotti vuota");
        conto.getOrderPrice(null);
        //Mai raggiunta
    }
}
