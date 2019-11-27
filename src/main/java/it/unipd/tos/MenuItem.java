////////////////////////////////////////////////////////////////////
// [Mirco] [Giardina] [1136663]
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

public class MenuItem 
{
	enum Prodotti{Panini,Fritti,Bevande}
	private Prodotti itemType;
	private String name;
	double price;
	
	public MenuItem(Prodotti itemType, String name, double price)
	{
		this.itemType=itemType;
		this.name=name;
		this.price=price;
	}
	
	Prodotti getItemType()
	{
		return this.itemType;
	}
	
	String getName()
	{
		return this.name;
	}
	
	double getPrice()
	{
		return this.price;
	}
}
