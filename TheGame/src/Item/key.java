/**
 * 
 */
package Item;

import dataTransfer.ItemTP;


/**
 * @author schmidmatt
 *
 */
public class key extends Item {

	keyType type;




	/**
	 * @param id
	 * @param value
	 * @param description
	 * @param title
	 */
	public key(String id, int value, String description, String title, keyType type) {
		super(id, value, description, title);
		this.type=type;

	}

	/**
	 * @param i
	 */
	public key(ItemTP i) {
		super(i);
		if (i.type.equals(keyType.RED.toString())){
			this.type= keyType.RED;
		}else if (i.type.equals(keyType.BLUE.toString())){
			this.type= keyType.BLUE;
		}else if (i.type.equals(keyType.GREEN.toString())){
			this.type= keyType.GREEN;
		}else if (i.type.equals(keyType.YELLOW.toString())){
			this.type= keyType.YELLOW;
		}else if (i.type.equals(keyType.SKELETON.toString())){
			this.type= keyType.SKELETON;
		}
	}


	@Override
	public ItemTP pack(int n) {
		return new ItemTP(id,title,"Item", description, value, 0,0,'K',n);

	}



	public keyType getType(){
		return type;
	}

}
