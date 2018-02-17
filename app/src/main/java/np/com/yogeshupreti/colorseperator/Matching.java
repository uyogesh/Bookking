package np.com.yogeshupreti.colorseperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by flowing on 2/7/18.
 */

public class Matching implements Common {


    String topic;
    List<String> columnA;
    List<String> columnB;
    HashMap<String,String> answer;

    Matching(){
        columnA = new ArrayList<>();
        columnB = new ArrayList<>();
        answer = new HashMap<>();


    }

    public void addColumnA(String entry)
    {
        columnA.add(entry);
    }


    public void addColumnB(String entry)
    {
        columnB.add(entry);
    }

    public List<String> getColumnA(){
        return this.columnA;
    }


    public List<String> getColumnB(){
        return this.columnB;
    }

    public void addAnswer(String a, String b){
        answer.put(a,b);

    }
    public HashMap<String,String> getAnswer()
    {
        return answer;
    }


    @Override
    public String getType() {
        return Quiz.TYPE_MATCHING;
    }
}
