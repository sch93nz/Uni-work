/**
 * 
 */
package dataTransfer;

import java.io.Serializable;
import java.util.Arrays;

/**
 * This is a TRANSPOR PACKET of the ENTITY class so we don't
 * have to transfer pictures and the other fun stuff
 * 
 * @author Matthew
 *
 */
public class EntityTP implements Serializable{

	public final String ID;
	public final String Name;
	public final ItemTP[] item;
	public final Long money;
	public final int x,y;
	public final int otherX,otherY;
	public final boolean canFly,armored;
	public final int health;
	public final int mana;
	public final Long exp;
	public final String place;
	public final String[] Sayings= null;
	public final char Type; // will hold ether E , B ,P, H ,N
	public final ItemTP[] armour;
	public final ItemTP Weapon;
	
	

	/**
	 * @param iD
	 * @param name
	 * @param item
	 * @param money
	 * @param x
	 * @param y
	 * @param canFly
	 * @param armored
	 * @param health
	 * @param maxHealth
	 * @param mana
	 * @param place 
	 * @param maxMana
	 * @param exp
	 * @param type
	 * @param armour
	 * @param weapon
	 */
	public EntityTP(String iD, String name, ItemTP[] item, Long money, int x,int otherX,
			int y,int otherY, boolean canFly, boolean armored, int health, 
			int mana, String place, Long exp, char type, ItemTP[] armour,
			ItemTP weapon) {
		super();
		ID = iD;
		Name = name;
		this.item = item;
		this.money = money;
		this.x = x;
		this.y = y;
		this.canFly = canFly;
		this.armored = armored;
		this.health = health;
		this.otherX=otherX;
		this.otherY=otherY;
		this.mana = mana;
		this.place=place;
		this.exp = exp;
		Type = type;
		this.armour = armour;
		Weapon = weapon;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + Arrays.hashCode(Sayings);
		result = prime * result + Type;
		result = prime * result + ((Weapon == null) ? 0 : Weapon.hashCode());
		result = prime * result + (armored ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(armour);
		result = prime * result + (canFly ? 1231 : 1237);
		result = prime * result + ((exp == null) ? 0 : exp.hashCode());
		result = prime * result + health;
		result = prime * result + Arrays.hashCode(item);
		result = prime * result + mana;
		result = prime * result + ((money == null) ? 0 : money.hashCode());
		result = prime * result + otherX;
		result = prime * result + otherY;
		result = prime * result + ((place == null) ? 0 : place.hashCode());
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
		EntityTP other = (EntityTP) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (!Arrays.equals(Sayings, other.Sayings))
			return false;
		if (Type != other.Type)
			return false;
		if (Weapon == null) {
			if (other.Weapon != null)
				return false;
		} else if (!Weapon.equals(other.Weapon))
			return false;
		if (armored != other.armored)
			return false;
		if (!Arrays.equals(armour, other.armour))
			return false;
		if (canFly != other.canFly)
			return false;
		if (exp == null) {
			if (other.exp != null)
				return false;
		} else if (!exp.equals(other.exp))
			return false;
		if (health != other.health)
			return false;
		if (!Arrays.equals(item, other.item))
			return false;
		if (mana != other.mana)
			return false;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money))
			return false;
		if (otherX != other.otherX)
			return false;
		if (otherY != other.otherY)
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}



}
