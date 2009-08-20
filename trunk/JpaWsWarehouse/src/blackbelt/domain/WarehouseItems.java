package blackbelt.domain;

public class WarehouseItems {
	private Warehouses warehouse;
	private Items item;
	public WarehouseItems() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WarehouseItems(Warehouses warehouse, Items item) {
		super();
		this.warehouse = warehouse;
		this.item = item;
	}
	public Warehouses getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouses warehouse) {
		this.warehouse = warehouse;
	}
	public Items getItem() {
		return item;
	}
	public void setItem(Items item) {
		this.item = item;
	}
	
	
}
