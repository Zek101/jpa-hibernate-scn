package blackbelt.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Items {
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	private Equipments equipment;
	
	//@OneToMany
	//private Set<ItemStatus> itemStatus = new HashSet<ItemStatus>();;
	@OneToOne
	private ItemLocations itemLocation;
	
	private String serial;
	private String description;
	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Items(String description) {
		super();
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Equipments getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipments equipment) {
		this.equipment = equipment;
	}

	@Override
	public String toString() {
		return description;
	}

	public ItemLocations getItemLocation() {
		return itemLocation;
	}

	public void setItemLocation(ItemLocations itemLocation) {
		this.itemLocation = itemLocation;
	}

	/*public Set<ItemStatus> getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(Set<ItemStatus> itemStatus) {
		this.itemStatus = itemStatus;
	}
	
	public void addItemStatus(ItemStatus ... itemStatus){
		for(int i = 0; i < itemStatus.length; i++){
			this.itemStatus.add(itemStatus[i]);
		}
	}*/
	
	
	
}
