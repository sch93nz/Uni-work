/**
 * 
 */
package characters;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import Item.Armour;
import Item.Booster;
import Item.BoosterType;
import Item.Item;
import Item.Weapon;
import Item.key;
import Item.keyType;
import dataTransfer.EntityTP;
import dataTransfer.ItemTP;

/**
 * @author User
 *
 */
public class Entity {

	protected final String ID;
	protected String Name;
	protected int level;
	protected Map<Item,Integer> item;
	protected long money;
	protected Point location; // Tile Location
	protected boolean canFly,armored;
	protected int health,maxHealth;
	protected int mana,maxMana;
	protected Long exp;
	protected String[] Sayings= null;
	protected Stack<Point> path;
	protected char Type = 'E';
	protected Weapon weapon;
	protected Armour[] armour = new Armour[5];
	protected String place="";
	protected int Strength, magic, defence;

	/**
	 * @param iD
	 * @param name
	 * @param level
	 * @param item
	 * @param money
	 * @param location
	 * @param canFly
	 * @param armored
	 * @param health
	 * @param maxHealth
	 * @param mana
	 * @param maxMana
	 * @param exp
	 */
	public Entity(String iD, String name, Map<Item,Integer> item, long money, Point location,
			boolean canFly, boolean armored, int health,  
			int mana, String place, Long exp, String[] equipped) {
		super();
		this.exp = exp;
		levelUP();
		checkStates();
		this.place=place;
		ID = iD;
		this.item = item;
		this.money = money;
		this.location = location;
		this.Name = name;
		// need some crazy method to work out what tile its on
		this.canFly = canFly;
		this.armored = armored;
		if (health >-1)
			this.health = health;
		else
			this.health=this.maxHealth;
		if (mana >-1)
			this.mana = mana;
		else
			this.mana=this.maxMana;

		/* Iterates the list of items, and checks against the list of "equipped" items
		   to see what needs to be appended to the entity's appendages. */
		if (equipped !=null && equipped.length > 0)


			for ( Entry<Item, Integer> n: item.entrySet()){
				Item i = n.getKey();
				if (i instanceof Armour || i instanceof Weapon)
					for (int j = 0; j < equipped.length; j++)
						if (i.getID().equals(equipped[j])){
							if (i instanceof Armour) this.addArmour((Armour)i);
							else if (i instanceof Weapon) this.addWeapon((Weapon)i);
						}
			}

	}

	/**
	 * loads a weapon in to the characters weapon slot
	 * @param w
	 */
	public void addWeapon(Weapon w){
		if (w instanceof Weapon){
			if (weapon == null)
				this.weapon=w;
			else {
				removeWeapon();
				this.weapon=w;
			}
		}
	}

	public void removeWeapon() {
		addItem(weapon);
		weapon= null;
	}

	/**
	 * gets the armour off the enitiy so you can loaded it into the xml or the gui
	 * 
	 */
	public Armour[] getArmour(){
		return armour;
	}

	/**
	 * creates  a Entity from a EntityTP
	 * @param p
	 */
	public Entity(EntityTP p){
		ID = p.ID;
		Name = p.Name;

		this.money = p.money;
		this.location = new Point(p.x,p.y);
		// need some crazy method to work out what tile its on
		this.canFly = p.canFly;
		this.armored = p.armored;

		this.place = p.place;
		if (!(health <=-1))
			this.health = p.health;

		if (!(mana <=-1))
			this.mana = p.mana;

		this.exp = p.exp;
		loadItems(p.item);
		levelUP();
		checkStates();
	}

	protected void checkStates() {
		maxHealth =(level-1)*50+100 ;
		maxMana =(level-1)*50+100 ;
		Strength = 14+level;
		defence = 14+level;
		mana =14+level;


	}

	protected void loadItems(ItemTP[] i) {
		if(item==null)
			item=new HashMap<Item,Integer>();

		for (ItemTP k :i){
			if(k.instance=='W'){
				item.put(new Weapon(k), k.number);
			}else if(k.instance=='B'){
				item.put(new Booster(k), k.number);
			}else if(k.instance=='A'){
				item.put(new Armour(k), k.number);
			}else if(k.instance=='I'){
				item.put(new Item(k), k.number);
			}
		}
	}

	/**
	 * @return the item
	 */
	public Map<Item,Integer> getItem() {
		return item;
	}


	/**
	 * uses a item if that is the last of the item in its inventory then
	 * it will remove it from the inventory
	 * @param item
	 * @return true if that item was used false otherwise
	 */
	public boolean useItem(Item i){
		if(item.containsKey(i)){
			if (i instanceof Booster){
				Booster b = (Booster) i;
				if (b.getType() == (BoosterType.LARGEHEALTH)){
					health+=b.getBoostAmount();

				}else if (b.getType() == (BoosterType.SMALLHEALTH)){
					health+=b.getBoostAmount();
				}else if (b.getType() == (BoosterType.LARGEMANA)){
					mana+=b.getBoostAmount();
				}else 
					mana+=b.getBoostAmount();
			}
		}else if (i instanceof Armour){
			Armour a = (Armour) i;
			addArmour(a);
		}else if (i instanceof Weapon){
			Weapon w = (Weapon) i;
			addWeapon(w);
		}else if (i instanceof key){
			key k = (key) i;
			if (k.getType().equals(keyType.RED.toString())){

			}else if (k.getType().equals(keyType.BLUE.toString())){

			}else if (k.getType().equals(keyType.GREEN.toString())){

			}else if (k.getType().equals(keyType.YELLOW.toString())){

			}else if (k.getType().equals(keyType.SKELETON.toString())){

			}

			remove(i);
		}
		return false;
	}
	/**
	 * called when attacking a Entity 
	 * the Entity must be passed
	 */
	public void attack(Entity e){
		if(e.inRange(location, weapon.getRange()))
			e.takeAttack(weapon.getAttack());
	}

	/**
	 * Sets the id number of the location this entity is in
	 * @param l id of the location
	 */
	public void setPlace(String l){
		this.place = l;
	}

	/**
	 * Gets the id number of the location this entity is in
	 * @return location's id
	 */
	public String getPlace(){
		return this.place;
	}

	/**
	 * used for taking damage
	 * @param a
	 */
	public void takeAttack(int a){
		int neg=defence;//the amount to negate from the attack
		for(Armour d:armour){
			neg+=d.getDefence();
		}
		health-=a-(neg/10);
	}

	/**
	 * used to see if it is in range of the weapon
	 */
	public boolean inRange(Point2D p, int Range){
		return location.distance(p)<Range;
	}

	/**
	 * @return the money
	 */
	public long getMoney() {
		return money;
	}


	public Weapon getWeapon(){
		return this.weapon;
	}


	/**
	 * adjust the money
	 * by adding it to the current money
	 * so negative if taking away positive if 
	 * adding
	 * @param Adjusts the money
	 */
	public void adjustMoney(long l) {
		money+=l;
	}

	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * @return the canFly
	 */
	public boolean CanFly() {
		return canFly;
	}

	/**
	 * @param can the character fly 
	 */
	public void setCanFly(boolean canFly) {
		this.canFly = canFly;
	}

	/**
	 * @return the armored
	 */
	public boolean isArmered() {
		return armored;
	}

	/**
	 * @param is the character armored
	 */
	public void setArmored(boolean armored) {
		this.armored = armored;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param sets the Health for the character
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the maxHealth
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * @param set the maximum health
	 */
	protected void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
	 * @return the mana
	 */
	public int getMana() {
		return mana;
	}

	/**
	 * @param adjusts the mana level
	 */
	public void setMana(int mana) {
		this.mana = mana;
	}

	/**
	 * @return the maxMana
	 */
	public int getMaxMana() {
		return maxMana;
	}

	/**
	 * @param set the maximum Mana
	 */
	protected void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}

	/**
	 * @return the experience
	 */
	public Long getExp() {
		return exp;
	}

	/**
	 * @param used to add exp
	 */
	public void addExp(Long eXp) {
		eXp = Math.abs(eXp);
		this.exp += exp;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}



	protected void levelUP() {
		if(exp >= nextLevel()){
			setMaxMana(getMaxMana()+(100));
			setMaxHealth(getMaxHealth()+100);

			++level;

			setHealth(getMaxHealth());
			setMana(getMaxMana());

			++Strength;
			++defence;
			++magic;
		}
	}

	public long nextLevel(){
		return Math.round(Math.pow(10,(level-1)));
	}


	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Sets the path that this entity shall take. If is a player, when the player
	 * clicks on a location in the world, the List is rebuilt, and added via this method.
	 * At this stage, NPC will not use the path, and will remain static within the location.
	 * @param path
	 */
	public void setPath(Stack<Point> path){
		this.path = path;
	}


	/**
	 * Returns the next point on the entity's move stack.
	 * @return
	 */
	public Point getNextCell(){
		if (path.isEmpty()) return null;
		else return path.pop();
	}


	/**
	 * @return sayings
	 */
	public String[] getSayings() {
		return Sayings;
	}


	/**
	 * @param sets the Sayings
	 */
	public void setSayings(String[] sayings) {
		Sayings = sayings;
	}

	/**
	 * checks to make sure that the armour is going
	 * into the right position
	 * @param position
	 * @param t
	 * @return
	 */
	public boolean checkArmour(int position, Armour t){
		//0=head 1=body 2=leggings 3=gloves 4=wings
		switch (t.getType()){
		case HELMET:
			return position==0;
		case BODY:
			return position==1;
		case LEGGINGS:
			return position==2;
		case GLOVES:
			return position==3;
		case WINGS:
			return position==4;
		default:
			return false;
		}
	}

	/**
	 * Used to add armour to the Entity only really called
	 * by the Player automaticaly works out were it goes
	 * and replaces it if needed
	 * @param a
	 * @return
	 */
	public boolean addArmour(Armour a){
		//0=head 1=body 2=leggings 3=gloves 4=wings
		switch (a.getType()){
		case HELMET:
			if (armour[0]==null){
				armour[0]=a;
			}else{
				addItem(armour[0]);
				armour[0]=a;
			}
			return true;
		case BODY:
			if (armour[1]==null){
				armour[1]=a;
			}else{
				addItem(armour[1]);
				armour[1]=a;
			}
			return true;
		case LEGGINGS:
			if (armour[2]==null){
				armour[2]=a;
			}else{
				addItem(armour[2]);
				armour[2]=a;
			}
			return true;
		case GLOVES:
			if (armour[3]==null){
				armour[3]=a;
			}else{
				addItem(armour[3]);
				armour[3]=a;
			}
			return true;
		case WINGS:
			if (armour[4]==null){
				armour[4]=a;
			}else{
				addItem(armour[4]);
				armour[4]=a;
			}
			return true;
		default:
			return false;
		}
	}

	/**
	 * adds the item to the inventory
	 * if there is already that item in the inventory 
	 * it will just update the ammount
	 * @param i
	 */
	public void addItem(Item i) {
		if (item==null){
			item=new HashMap<Item,Integer>();
		}
		int k=1;
		if(item.containsKey(i)){
			k = item.get(i)+1;
			remove(i);
		} 
		item.put(i, k);

	}

	/**
	 * removes the item from the inventory 
	 * if that is the last of that item then it will 
	 * remove the reference to that item
	 * but it will return the item so it can be used as
	 * a use item as well
	 */
	public Item remove(Item i){
		if (item==null){
			item=new HashMap<Item,Integer>();
			return null;
		}
		if(item.containsKey(i)){
			if(item.get(i)>1){
				int k = item.get(i)-1;
				item.remove(i);
				item.put(i, k);
				return i;
			} else {
				item.remove(i);
				return i;
			}
		} else{
			return null;
		}
	}

	/**
	 * buys the item for its value
	 * @param i
	 */
	public void buy(Item i) {
		adjustMoney(-i.getValue());
		addItem(i);

	}

	/**
	 * sells the item for its value
	 * @param i
	 */
	public Item sell(Item i) {
		adjustMoney(i.getValue());
		return remove(i);
	}

	/**
	 * used to pack up the object into a EntityTP object
	 * so its ready for transfer
	 * @return
	 */
	public EntityTP pack(){
		ItemTP[] items= new ItemTP[item.size()];

		int i=0;
		for (Entry<Item, Integer> n: item.entrySet()){
			items[i]=n.getKey().pack(n.getValue());
			++i;
		}
		ItemTP[] armourlist = new ItemTP[5];
		i=0;
		for (Item n: armour){
			armourlist[i]=n.pack(1);
			++i;
		}
		System.out.println("packing: " + this.toString());
		return new EntityTP(ID,Name,items,money,location.x,0,
				location.y,0,canFly,armored,health,mana,place,
				exp,Type,armourlist,weapon.pack(1));
	}

	/**
	 * used to redefine the object but only if they have the same id 
	 * otherwise it will fail
	 * @param e
	 * @return
	 */
	public boolean Unpack(EntityTP e){
		if(e.ID!=this.ID){
			return false;
		} 

		Name = e.Name;

		this.money = e.money;
		this.location = new Point(e.x,e.y);
		this.canFly = e.canFly;
		this.armored = e.armored;
		this.health = e.health;

		this.mana = e.mana;
		this.place=e.place;
		this.exp = e.exp;
		loadItems(e.item);
		levelUP();
		checkStates();
		weapon=new Weapon(e.Weapon);
		loadArmour(e.armour);
		return true;
	}

	protected void loadArmour(ItemTP[] a) {
		for(ItemTP i : a){
			Armour arm = new Armour(i);
			addArmour(arm);
		}

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
		result = prime * result + Strength;
		result = prime * result + Type;
		result = prime * result + (armored ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(armour);
		result = prime * result + (canFly ? 1231 : 1237);
		result = prime * result + defence;
		result = prime * result + ((exp == null) ? 0 : exp.hashCode());
		result = prime * result + health;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + level;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + magic;
		result = prime * result + mana;
		result = prime * result + maxHealth;
		result = prime * result + maxMana;
		result = prime * result + (int) (money ^ (money >>> 32));
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
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
		Entity other = (Entity) obj;
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
		if (Strength != other.Strength)
			return false;
		if (Type != other.Type)
			return false;
		if (armored != other.armored)
			return false;
		if (!Arrays.equals(armour, other.armour))
			return false;
		if (canFly != other.canFly)
			return false;
		if (defence != other.defence)
			return false;
		if (exp == null) {
			if (other.exp != null)
				return false;
		} else if (!exp.equals(other.exp))
			return false;
		if (health != other.health)
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (level != other.level)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (magic != other.magic)
			return false;
		if (mana != other.mana)
			return false;
		if (maxHealth != other.maxHealth)
			return false;
		if (maxMana != other.maxMana)
			return false;
		if (money != other.money)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (weapon == null) {
			if (other.weapon != null)
				return false;
		} else if (!weapon.equals(other.weapon))
			return false;
		return true;
	}

}
