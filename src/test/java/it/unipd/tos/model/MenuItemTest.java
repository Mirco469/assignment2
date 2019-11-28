////////////////////////////////////////////////////////////////////
// [Mirco] [Giardina] [1136663]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MenuItemTest {

    @org.junit.Rule
    public ExpectedException error= ExpectedException.none();
    
    @Test
    public void testAggiuntaProdottiPrezzoNegativo()
    {
        error.expect(IllegalArgumentException.class);
        error.expectMessage("Prezzo negativo");
        new MenuItem(MenuItem.Prodotti.Panini, "Primavera", -3.12);
        //Mai raggiunta
    }

}
