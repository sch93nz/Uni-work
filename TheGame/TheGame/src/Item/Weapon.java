/**
 * 
 */
package Item;

import dataTransfer.ItemTP;


/**
 * @author Matthew
 *
 */
public class Weapon extends Item {

	private int attack,range;
	private WeaponType type;
	
	/**
	 * @param value
	 * @param description
	 * @param title
	 * @param attack
	 * @param range
	 * @param type
	 */
	public Weapon(String id,int value, String description, String title, int attack, int range, 
			WeaponType type) {
		super(id, value, description, title);
		this.attack = attack;
		this.range = range;
		this.type = type;
	}

	public Weapon(ItemTP i){
		super(i.ID,i.Value, i.description, i.title);
		this.attack = i.attackDefence;
		this.range = i.range;
		if (i.type.equals(WeaponType.MELEE.toString())){
			this.type=WeaponType.MELEE;
		}else if (i.type.equals(WeaponType.MAGIC.toString())){
			this.type=WeaponType.MAGIC;
		}else if (i.type.equals(WeaponType.RANGED.toString())){
			this.type=WeaponType.RANGED;
		}
	}
	


	/**
	 * @return gets the attack strength
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * @return gets the range
	 */
	public int getRange() {
		return range;
	}

	/**
	 * @return gets the weapons type
	 */
	public WeaponType getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + attack;
		result = prime * result + range;
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
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Weapon))
			return false;
		Weapon other = (Weapon) obj;
		if (attack != other.attack)
			return false;
		if (range != other.range)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	
	/**
	 * used to pack the item up
	 *'W' means that its A weapon
	 */
	public ItemTP pack(int n) {
		return new ItemTP(id,title,type.toString(), description, value, attack,range,'W',n);
		
	}
	
}
