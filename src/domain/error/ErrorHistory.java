package domain.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorHistory {
    private List<ErrorListener>listenerList=new ArrayList<>();
    public void addErrorListener(ErrorListener l){listenerList.add(l);}
    private void fireNewErrorOccured(){
        for(ErrorListener l:listenerList)l.onNewErrorOccured();
    }

    private ErrorHistory instance;
    private ErrorHistory(){

    }
    public ErrorHistory inst(){
        if(instance==null)instance=new ErrorHistory();
        return instance;
    }

    private List<String>history=new ArrayList<>();
    public void addError(String where,String content){
        history.add(new StringBuilder().append(where).append(" : ").append(content).toString());
        fireNewErrorOccured();
    }
    public List<String>getHistory(){
        return history;
    }
}
