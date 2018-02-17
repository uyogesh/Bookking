package np.com.yogeshupreti.colorseperator;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class MatchAdapterMain extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<String> cola;
    List<String> colb;
    HashMap<String,String> answer;
    String currentSelectionA;
    String currentSelectionB;
    int currentSelectionAposition=10;
    int currentSelectionBposition=10;

    MatchAdapter adapterA;
    MatchAdapter1 adapterB;

    MatchHolderPrimary holder;
    List<RecyclerView.Adapter> adapters;

    public MatchAdapterMain(Context context, List<String> cola, List<String> colb, HashMap<String, String> answer) {
        this.context = context;
        this.cola = cola;
        this.colb = colb;
        this.answer = answer;
        adapters= new ArrayList<>();
    }

    IMatchCallBack callback = new IMatchCallBack() {
        @Override
        public void sendMatchSelection(int type, String value,int position) {
            int selA = currentSelectionAposition;
            int selB = currentSelectionBposition;
            if(type==util.MATCH_COLUMN_TYPE_A)
            {
                currentSelectionA = value;
                currentSelectionAposition = position;
                if(currentSelectionB!=null)
                {
                    if(checkAnswer(currentSelectionA,currentSelectionB))
                    {
                        ((MatchAdapter)adapters.get(0)).setButtonColorNActive(position,selA, false,util.COLOR_GREEN);
                        ((MatchAdapter1)adapters.get(1)).setButtonColorNActive(currentSelectionBposition,selB,false,util.COLOR_GREEN);

                        notifyDataChange();
                    }
                    else{
                        ((MatchAdapter)adapters.get(0)).setButtonColorNActive(position,selA,true,util.COLOR_YELLOW);
                        adapters.get(0).notifyDataSetChanged();
                    }
                }else{
                    ((MatchAdapter)adapters.get(0)).setButtonColorNActive(position,selA,true,util.COLOR_YELLOW);
                    adapters.get(0).notifyDataSetChanged();
                }

            }
            else if(type==util.MATCH_COLUMN_TYPE_B)
            {
                currentSelectionB = value;
                currentSelectionBposition = position;
                if(currentSelectionA!=null)
                {

                    if(checkAnswer(currentSelectionA,currentSelectionB))
                    {
                        ((MatchAdapter1)adapters.get(1)).setButtonColorNActive(position,selB,false,util.COLOR_GREEN);
                        ((MatchAdapter)adapters.get(0)).setButtonColorNActive(currentSelectionAposition,selA,false,util.COLOR_GREEN);
                        notifyDataChange();

                    }
                    else{
                        ((MatchAdapter1)adapters.get(1)).setButtonColorNActive(position,selB,true,util.COLOR_YELLOW);
                        adapters.get(1).notifyDataSetChanged();

                    }

                }else{
                    ((MatchAdapter1)adapters.get(1)).setButtonColorNActive(position,selB,true,util.COLOR_YELLOW);
                    adapters.get(1).notifyDataSetChanged();
                }
            }



        }
    };

    private void notifyDataChange(){
        adapters.get(1).notifyDataSetChanged();
        adapters.get(0).notifyDataSetChanged();
    }

    private boolean checkAnswer(String a,String b)
    {

            if(answer.containsKey(a))
            {
                if(b.equals(answer.get(a)))
                {
                    return true;
                }
            }
        return false;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.match,parent,false );

        return new MatchHolderPrimary(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        this.holder = (MatchHolderPrimary)holder;
        adapterA = new MatchAdapter(context,cola,callback);
        adapterB = new MatchAdapter1(context,colb,callback);
        if(!adapters.contains(adapterA)){
            adapters.add(adapterA);
        }
        if(!adapters.contains(adapterB)){
            adapters.add(adapterB);
        }
        ((MatchHolderPrimary)holder).recyclerColA.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        ((MatchHolderPrimary)holder).recyclerColB.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

        ((MatchHolderPrimary)holder).recyclerColA.setAdapter(adapterA);
        ((MatchHolderPrimary)holder).recyclerColB.setAdapter(adapterB);


    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class MatchHolderPrimary extends RecyclerView.ViewHolder{
        RecyclerView recyclerColA;
        RecyclerView recyclerColB;
        TextView title;

        public MatchHolderPrimary(View itemView) {
            super(itemView);

            recyclerColA = itemView.findViewById(R.id.inner_recycler_cola);
            recyclerColB = itemView.findViewById(R.id.inner_recycler_colb);
//            title = itemView.findViewById(R.id.match_topic);
        }
    }
}
