package np.com.yogeshupreti.colorseperator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends Activity {
    RecyclerView rView;
    static String TYPE_MATCHING = "matching";
    RView adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        rView = findViewById(R.id.rView);
        Matching m = new Matching();
        m.addColumnA("pegion");
        m.addColumnA("mice");
        m.addColumnA("squirell");
        m.addColumnA("elephant");
        m.addColumnB("nuts");
        m.addColumnB("message");
        m.addColumnB("jerry");
        m.addColumnB("mammal");
        m.addAnswer("pegion","message");
        m.addAnswer("elephant","mammal");
        m.addAnswer("mice","jerry");
        m.addAnswer("squirell","nuts");


        List<Common> c = new ArrayList<>();
        c.add(m);
        adapter = new RView(c,getApplicationContext());
        rView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        rView.setAdapter(adapter);
    }
}