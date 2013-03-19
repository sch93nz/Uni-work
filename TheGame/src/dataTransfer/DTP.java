/**
 * 
 */
package dataTransfer;

import java.io.Serializable;
import java.util.Arrays;

/**
 * This creates a DATA TRANSFER PACKET that is serialized
 * to send across the network
 * 		
 * (data.getEntity()!=null) do a lot of shit
 *
 * (data.getItems()!= null) do some shit
 * (data.getPlayer()!=null) do even more shit
 * after doing all the unpacking /shitting the game peices are no considered to
 * be uptodate and the client will wait for the player to make a move then it will send back
 * a command string which is in DTP  the player field must not be null
 * if more than one command there will be one strign for each command.
 * the thing will be sent in a String[]
 * @author schmidmatt
 *
 */
public class DTP implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4850852977525234580L;
	private EntityTP[] entity = null; // holds the other characters
	private ParcelTP[] items = null; // holds the items that are on the map
	private EntityTP player = null; // holds the player
	private String[] command=new String[]{"",""}; // and commands made by the player
	
	/**
	 * 
	 * @param entity
	 * @param items
	 * @param player
	 */
	public DTP(EntityTP[] entity, ParcelTP[] items, EntityTP player ,String[] com) {
		this.entity = entity;
		this.items = items;
		this.player = player;
		this.command= com;
	}
	
	
	
	/**
	 * 
	 * @param entity
	 * @param items
	 */
	public DTP(EntityTP[] entity, ParcelTP[] items) {
		this.entity = entity;
		this.items = items;
	}
	
	/**
	 * @return the entity
	 */
	public EntityTP[] getEntity() {
		return entity;
	}
	/**
	 * @return the items
	 */
	public ParcelTP[] getItems() {
		return items;
	}
	/**
	 * @return the player
	 */
	public EntityTP getPlayer() {
		return player;
	}
	
	/**
	 * @return the command
	 */
	public String[] getCommand() {
		return command;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(command);
		result = prime * result + Arrays.hashCode(entity);
		result = prime * result + Arrays.hashCode(items);
		result = prime * result + ((player == null) ? 0 : player.hashCode());
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
		DTP other = (DTP) obj;
		if (!Arrays.equals(command, other.command))
			return false;
		if (!Arrays.equals(entity, other.entity))
			return false;
		if (!Arrays.equals(items, other.items))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}
	
}
