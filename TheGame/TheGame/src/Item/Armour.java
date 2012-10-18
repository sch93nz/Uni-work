/**
 * 
 */
package Item;

import dataTransfer.ItemTP;


/**
 * @author Matthew
 *
 */
public class Armour extends Item {

	private int defence;
	private ArmourType type;
	
	/**
	 * @param value
	 * @param description
	 * @param title
	 * @param defence
	 * @param type
	 */

	public Armour(String id,int value, String description, String title, int defence,
			ArmourType type) {
		super(id,value, description, title);
		this.defence = defence;
		this.type = type;
	}

	public Armour(ItemTP i){
		super(i.ID,i.Value, i.description, i.title);
		this.defence = i.attackDefence;
		if (i.type.equals(ArmourType.BODY.toString())){
			this.type= ArmourType.BODY;
		}else if (i.type.equals(ArmourType.GLOVES.toString())){
			this.type= ArmourType.GLOVES;
		}else if (i.type.equals(ArmourType.LEGGINGS.toString())){
			this.type= ArmourType.LEGGINGS;
		}else if (i.type.equals(ArmourType.HELMET.toString())){
			this.type= ArmourType.HELMET;
		}else if (i.type.equals(ArmourType.WINGS.toString())){
			this.type= ArmourType.WINGS;
		}
	}
	

	/**
	 * @return the defence
	 */
	public int getDefence() {
		return defence;
	}

	/**
	 * @return gets the Armour type
	 */
	public ArmourType getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + defence;
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
		if (!(obj instanceof Armour))
			return false;
		Armour other = (Armour) obj;
		if (defence != other.defence)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	/**
	 * used to pack the item up
	 *'A' means that its ARmour
	 */
	
	public ItemTP pack(int n) {
		return new ItemTP(id,title,type.toString(), description, value, defence,0,'A',n);
		
	}
	
}
