////////////////////////////////////////////////////////////////////
// [Mirco] [Giardina] [1136663]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem {
    public enum Prodotti {
        Panini, Fritti, Bevande
    }

    private Prodotti itemType;
    private String name;
    private double prince;

    public MenuItem(Prodotti itemType, String name, double price) {
        if(price<0)
        {
            throw new IllegalArgumentException("Prezzo negativo");
        }
        this.itemType = itemType;
        this.name = name;
        this.prince = price;
    }

    public Prodotti getItemType() {
        return this.itemType;
    }

    public double getPrice() {
        return this.prince;
    }
}
