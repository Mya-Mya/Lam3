package domain.valueobject;

public class CategoryId {
    private String value;
    public CategoryId(String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
    public boolean equals(CategoryId id){
        return value.equals(id.getValue());
    }
}
