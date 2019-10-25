package domain.valueobject;

import java.util.List;

public class Productor {
    private List<String>data;
    private String fullText;
    private String shortText;
    public static final String productorSeparator="、";
    public static final String inShortSuffix=" ほか";
    public Productor(List<String>data){
        this.data=data;

        StringBuilder fullTextBuilder=new StringBuilder();
        for(int i=0;i<this.data.size();i++){
            fullTextBuilder.append(data.get(i));
            if(i!=data.size()-1) {
                fullTextBuilder.append(productorSeparator);
            }
        }
        fullText=fullTextBuilder.toString();

        if(data.size()<=1){
            shortText=fullText;
        }else{
            shortText=data.get(0)+inShortSuffix;
        }


    }

    public String getFullText() {
        return fullText;
    }

    public String getShortText() {
        return shortText;
    }

    public int getNumPeople(){
        return data.size();
    }
}
