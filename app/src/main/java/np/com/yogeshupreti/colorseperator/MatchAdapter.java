package np.com.yogeshupreti.colorseperator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MatchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    List<String> cola;
    List<String> colb;
    LayoutInflater inflater;
    IMatchCallBack callback;
    List<Button> buttons;
    List<Button> buttonsCopy;
    List<Button> activeButtons;

    public MatchAdapter(Context context, List<String> cola, IMatchCallBack callback) {
        this.context = context;
        this.cola = cola;
        this.callback = callback;
        inflater = LayoutInflater.from(context);
        buttons = new ArrayList<>();
        buttonsCopy = new ArrayList<>();
        activeButtons = new ArrayList<>();
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.match_recycler,null,false);

        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int pos=position;
                ((MatchViewHolder)holder).setColA(cola.get(position));
                buttons.add(((MatchViewHolder)holder).colA);
                buttonsCopy.add(((MatchViewHolder)holder).colA);
        ((MatchViewHolder)holder).colA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.sendMatchSelection(util.MATCH_COLUMN_TYPE_A,((Button)v).getText().toString(),pos);
            }
        });
    }



    public void setButtonColorNActive(int position, int previous,boolean active,int color)
    {
          //Set All Buttons That are not matched to Blue, So that previously chosen button is disabled

        int colour = 0;
        if(color==util.COLOR_BLUE)
        {
            colour = context.getResources().getColor(R.color.blue);
        }else if(color==util.COLOR_GREEN)
        {
            colour = context.getResources().getColor(R.color.green);
        }else if(color==util.COLOR_YELLOW)
        {
            colour = context.getResources().getColor(R.color.yellow);
        }

        if(active)
        {
            buttons.get(position).setBackgroundColor(colour);
           try {
               if (activeButtons.contains(buttons.get(previous))) {
               } else {
                   buttons.get(previous).setBackgroundColor(context.getResources().getColor(R.color.blue));
               }
           }catch(Exception e){
               Log.d("OutBound", "setButtonColorNActive: ");
           }
        }
        else{
            buttons.get(position).setBackgroundColor(context.getResources().getColor(R.color.green));
            buttons.get(position).setEnabled(false);
            activeButtons.add(buttons.get(position));
            buttonsCopy.remove(buttons.get(position));

        }

    }


    private void setAllButtonsBlue()
    {
        for(Button button:buttonsCopy)
        {
            button.setBackgroundColor(context.getResources().getColor(R.color.blue));
        }
    }

    @Override
    public int getItemCount() {
        return this.cola.size();
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder{

        Button colA;

        public MatchViewHolder(View itemView) {
            super(itemView);
            colA = itemView.findViewById(R.id.col);
        }

        public void setColA(String colA) {
            this.colA.setText(colA);
        }
    }


}
