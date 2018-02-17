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

/**
 * Created by flowing on 2/8/18.
 */

public class MatchAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;

    List<String> colb;
    Map<String,String> answer;
    LayoutInflater inflater;
    Object currentA;
    Object currentB;
    List<View> views;
    IMatchCallBack callback;
    List<Button> buttons;
    List<Button> buttonsCopy;
    List<Button> activeButtons;

    public MatchAdapter1(Context context, List<String> colb, IMatchCallBack callback) {
        this.context = context;
        this.callback  = callback;
        this.colb = colb;
        inflater = LayoutInflater.from(context);
        views = new ArrayList<>();
        buttons = new ArrayList<>();
        buttonsCopy = new ArrayList<>();

        activeButtons = new ArrayList<>();
    }



    public void setButtonColorNActive(int position,int previous,boolean active,int color)
    {
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
            buttons.get(previous).setBackgroundColor(context.getResources().getColor(R.color.blue));
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.match_recycler,null,false);

        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int pos = position;
        ((MatchViewHolder)holder).setCol(colb.get(position));
        buttons.add(((MatchViewHolder)holder).col);
        buttonsCopy.add(((MatchViewHolder)holder).col);
        ((MatchViewHolder) holder).col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            callback.sendMatchSelection(util.MATCH_COLUMN_TYPE_B,((Button)v).getText().toString(),pos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.colb.size();
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder{


        Button col;
        public MatchViewHolder(View itemView) {
            super(itemView);
            col= itemView.findViewById(R.id.col);
        }
        public void setCol(String a)
        {
            this.col.setText(a);
        }

    }


}
