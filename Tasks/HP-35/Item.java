public class Item {
    private ItemType type;
    private int value = 0;

    public Item(ItemType type){
        this.type = type;
    }
    public Item(ItemType itemType, int value){
        this.type = ItemType.VALUE;
        this.value = value;
    }

    public ItemType getType() {
        return type;
    }

    public int getValue(){return value;}

}
