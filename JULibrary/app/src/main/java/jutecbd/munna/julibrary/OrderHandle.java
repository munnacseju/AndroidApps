package jutecbd.munna.julibrary;
public class OrderHandle {
    private String key;
    private String name, phone, dep, hall, bookid, bookNames, iterNumber, prices, batch;
    private String totalPrice;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public OrderHandle() {
    }

    public OrderHandle(String name, String phone, String dep, String hall, String batch, String bookid, String bookNames, String iterNumber, String prices, String totalPrice, String key) {
        this.name = name;
        this.phone = phone;
        this.dep = dep;
        this.hall = hall;
        this.batch = batch;
        this.bookid = bookid;
        this.bookNames = bookNames;
        this.iterNumber = iterNumber;
        this.prices = prices;
        this.totalPrice = totalPrice;
        this.key = key;
    }



    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookNames() {
        return bookNames;
    }

    public void setBookNames(String bookNames) {
        this.bookNames = bookNames;
    }

    public String getIterNumber() {
        return iterNumber;
    }

    public void setIterNumber(String iterNumber) {
        this.iterNumber = iterNumber;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }
}
