/**
 * 
 */
package dataTransfer;

import java.io.Serializable;
import java.util.Arrays;

/**
 * This is a TRANSPOR PACKET of the PARCEL class so we don't
 * have to transfer pictures and the other fun stuff
 * @author Matthew
 *
 */
public class ParcelTP implements Serializable {

	public final ItemTP[] items;
	public final int x,y;
	
	/**
	 * @param items
	 * @param x
	 * @param y
	 */
	public ParcelTP(ItemTP[] items, int x, int y) {

		this.items = items;
		this.x = x;
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(items);
		result = prime * result + x;
		result = prime * result + y;
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
		ParcelTP other = (ParcelTP) obj;
		if (!Arrays.equals(items, other.items))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	
	
}
