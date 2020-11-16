package jutecbd.munna.julibrary;

public class cartHandle {
    private String name, price, id, itemCount;
    private int itemNo;

    public cartHandle() {
    }

    public cartHandle(String name, String price, String id, String itemCount) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.itemCount = itemCount;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }
}
