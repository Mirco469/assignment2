////////////////////////////////////////////////////////////////////
// [Mirco] [Giardina] [1136663]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import org.junit.Test;
import org.junit.rules.ExpectedException;


public class MenuItemTest {

    @Test(expected = IllegalArgumentException.class)
    public void testAggiuntaProdottiPrezzoNegativo()
    {
        MenuItem item = new MenuItem(MenuItem.Prodotti.Panini, "Primavera", -3.12);
        //Mai raggiunto
    }

}
