////////////////////////////////////////////////////////////////////
// [Mirco] [Giardina] [1136663]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem {
    enum Prodotti {
        Panini, Fritti, Bevande
    }

    private Prodotti itemType;
    private String name;
    private double prince;

    public MenuItem(Prodotti itemType, String name, double price) {
        this.itemType = itemType;
        this.name = name;
        this.prince = price;
    }

    public Prodotti getItemType() {
        return this.itemType;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.prince;
    }
}
