/**
 * 
 */
package Item;

import java.awt.Point;
import java.util.ArrayList;

import dataTransfer.ItemTP;
import dataTransfer.ParcelTP;

/**
 * Used to store items on the world map 
 * and in dungeons 
 * @author schmidmatt
 *
 */
public class Parcel {

	// if it is stored in a dungeon
	//need to work on this logic
	
	private Point location = null; 
	private ArrayList<Item> items = new ArrayList<Item>();
	
	/**
	 * @param location
	 * @param items
	 */
	public Parcel(Point location, Item[] items) {
		this.location = location;
		for (Item e :items){
		this.items.add(e);
		}
	}
	/**
	 * 
	 * @param p
	 */
	public Parcel(ParcelTP p){
		
		this.location= new Point(p.x,p.y);
		int i=0;
		for (ItemTP k :p.items){
			if(k.instance=='W'){
				items.add(new Weapon(k));
			}else if(k.instance=='B'){
				items.add(new Booster(k));
			}else if(k.instance=='A'){
				items.add(new Armour(k));
			}else if(k.instance=='I'){
				items.add(new Item(k));
			}
		}
	}
	
	
	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}
	
	/**
	 * @return the items
	 */
	public Item[] getItems() {
		return (Item[]) items.toArray();
	}
	
	public ParcelTP pack(){
	ItemTP[] sending = new ItemTP[items.size()];
		int i=0;
		for (Item it: items){
			sending[i] = it.pack(1); 
			++i;
		}
		return new ParcelTP(sending,location.x,location.y);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parcel other = (Parcel) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
	
	
	
	
}
