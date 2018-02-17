package np.com.yogeshupreti.colorseperator;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by flowing on 2/7/18.
 */

public class RView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Common> list;
    Context context;
    LayoutInflater inflater;

    MatchAdapter adapterA;
    MatchAdapter1 adapterB;


    public RView(List<Common> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
            if(list.get(position).getType().equals(Quiz.TYPE_MATCHING))
            return 1;
            return 0;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.match, parent, false);
            Holder holder = new Holder(view);
            return holder;


    }




    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        MatchAdapterMain adapter = new MatchAdapterMain(context,((Matching)list.get(position)).getColumnA(),((Matching)list.get(position)).getColumnB(),((Matching)list.get(position)).getAnswer());
        ((Holder)holder).matchRView.setAdapter(adapter);
        ((Holder)holder).matchRView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true));

//        ((Holder)holder).tView.setText(((Matching)list.get(position)).getType());



    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView tView;
        RecyclerView matchRView;
        RecyclerView matchRViewRight;

    public Holder(View itemView) {
        super(itemView);
//        tView = itemView.findViewById(R.id.match_topic);
        matchRView =  itemView.findViewById(R.id.inner_recycler_cola);
        matchRViewRight = itemView.findViewById(R.id.inner_recycler_colb);
    }

}
}
