package domain.valueobject;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CategoryIdFactory {
    private Set<CategoryId> createdIdList =new HashSet<>();
    private Random random;
    public CategoryIdFactory(){
        random=new Random(Calendar.getInstance().getTimeInMillis());
    }
    public CategoryId createNewId(){
        CategoryId out;
        while (true){
            out=new CategoryId(Long.toString(random.nextLong()));
            boolean hasCreated=false;
            for(CategoryId p: createdIdList){
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
