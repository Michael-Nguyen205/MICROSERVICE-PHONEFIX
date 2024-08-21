package spring.boot.commonservice.query;

public class GetDetailPhoneCommonQuery {
    private String id;

    // Thêm constructor
    public GetDetailPhoneCommonQuery(String id) {
        this.id = id;
    }

    // Getter và Setter nếu cần
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
