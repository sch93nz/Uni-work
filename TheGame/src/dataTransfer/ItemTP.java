/**
 * 
 */
package dataTransfer;

import java.io.Serializable;

/**
 * @author duangmsiri
 *
 */
public class ItemTP implements Serializable{
	
	public final String ID;
	public final String title;
	public final String type;
	public final String description;
	public final int Value;
	public final int attackDefence;
	public final int range;
	public final char instance;
	public final int number;
	
	/**
	 * @param title
	 * @param type
	 * @param description
	 * @param value
	 * @param attackDefence
	 * @param range
	 * @param instance
	 * @param numer
	 */
	public ItemTP(String id,String title, String type, String description, int value,
			int attackDefence, int range, char instance, int numer) {
		super();
		this.ID=id;
		this.title = title;
		this.type = type;
		this.description = description;
		Value = value;
		this.attackDefence = attackDefence;
		this.range = range;
		this.instance = instance;
		this.number = numer;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + Value;
		result = prime * result + attackDefence;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + instance;
		result = prime * result + number;
		result = prime * result + range;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ItemTP other = (ItemTP) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (Value != other.Value)
			return false;
		if (attackDefence != other.attackDefence)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (instance != other.instance)
			return false;
		if (number != other.number)
			return false;
		if (range != other.range)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	

}
