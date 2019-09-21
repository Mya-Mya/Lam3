package domain.valueobject;

public class ProductId {
    private String value;
    public ProductId(String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
    public boolean equals(ProductId id){
        return value.equals(id.getValue());
    }
}
