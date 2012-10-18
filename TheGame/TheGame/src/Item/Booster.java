package Item;

import dataTransfer.ItemTP;


public class Booster extends Item{

	private int boostAmount;
	private BoosterType type;

	//SMALLMANA, SMALLHEALTH, LARGEHEALTH, LARGEMANA
	public Booster(ItemTP i) {
		super(i);
		boostAmount=i.attackDefence;
		if (i.type.equals(BoosterType.LARGEHEALTH.toString())){
			type=BoosterType.LARGEHEALTH;
		}else if (i.type.equals(BoosterType.SMALLHEALTH.toString())){
			type=BoosterType.SMALLHEALTH;
		}else if (i.type.equals(BoosterType.LARGEMANA.toString())){
			type=BoosterType.LARGEMANA;
		}else 
			type=BoosterType.SMALLMANA;
	}
	/**
	 * 
	 * @param value
	 * @param description
	 * @param title
	 * @param boostAmount
	 * @param t
	 */
	public Booster(String id, int value, String description, String title, int boostAmount, BoosterType t) {
		super(id, value, description, title);
		this.boostAmount = boostAmount;
		this.type = t;
	}



	/**
	 * used to pack the item up
	 *'W' means that its A weapon
	 */
	public ItemTP pack(int n) {
		return new ItemTP(id,title,type.toString(), description, value, boostAmount,0,'B',n);

	}
	/**
	 * @return the boostAmount
	 */
	public int getBoostAmount() {
		return boostAmount;
	}
	/**
	 * @return the t
	 */
	public BoosterType getType() {
		return type;
	}

}