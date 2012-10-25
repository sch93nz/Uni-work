/**
 * This is a base class
 */
package Item;

import dataTransfer.ItemTP;


/**
 * @author User
 *
 */
public class Item {

	protected String id;
	protected int value;
	protected String description="";
	protected String title;
	
	/**
	 * @param value
	 * @param description
	 * @param title
	 */
	public Item(String id,int value, String description, String title) {
		this.value = value;
		this.description = description;
		this.title = title;
		this.id = id;
	}
	
	public Item(ItemTP i){
		this.id= i.ID;
		this.value = i.Value;
		this.description = i.description;
		this.title = i.title;
	}

	public String getID(){
		return this.id;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	public ItemTP pack(int n) {
		return new ItemTP(id,title,"Item", description, value, 0,0,'I',n);

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + value;
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
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	
	public String toString(){
		String s = "";
		s += this.id + " " + this.getTitle() + " : $" + this.getValue() + "\n";
		s += this.getDescription() +"\n";
		if(this instanceof Booster){
			Booster b = (Booster) this;
			s+= b.getType().toString() + " amount" + b.getBoostAmount() + "\n";
		}
		else if(this instanceof Armour){
			Armour a = (Armour) this;
			s+= a.getType().toString() + "\n";
		}
		else if(this instanceof Weapon){
			Weapon w = (Weapon)this;
			s+= w.getType().toString() + "\n";
		}
		return s;
	}

}
