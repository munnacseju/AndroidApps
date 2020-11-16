package jutecbd.munna.julibrary;

import com.google.firebase.database.Exclude;

public class BookRegistrationHandle {
    private String  name, writerName, departmentName, language, imageDownloadUri;
    private String bookId;
    private String price;
    private String version;
    private String key;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public BookRegistrationHandle(String bookId, String name, String writerName, String departmentName, String language, String imageDownloadUri, String price, String version) {
        this.name = name;
        this.writerName = writerName;
        this.departmentName = departmentName;
        this.language = language;
        this.imageDownloadUri = imageDownloadUri;
        this.price = price;
        this.version = version;
        this.bookId = bookId;
    }

    public BookRegistrationHandle() {
    }
    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImageDownloadUri() {
        return imageDownloadUri;
    }

    public void setImageDownloadUri(String imageDownloadUri) {
        this.imageDownloadUri = imageDownloadUri;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
