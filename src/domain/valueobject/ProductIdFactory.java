package domain.valueobject;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ProductIdFactory {
    private Set<ProductId> createdIdList =new HashSet<>();
    private Random random;
    public ProductIdFactory(){
        random=new Random(Calendar.getInstance().getTimeInMillis());
    }
    public ProductId createNewId(){
        ProductId out;
        while (true){
            out=new ProductId(Long.toString(random.nextLong()));
            boolean hasCreated=false;
            for(ProductId p: createdIdList){
                if(p.equals(out)){
                    hasCreated=true;
                }
            }
            if(!hasCreated)break;
        }
        createdIdList.add(out);
        return out;
    }

}
