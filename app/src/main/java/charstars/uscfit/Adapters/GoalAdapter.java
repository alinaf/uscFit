package charstars.uscfit.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import charstars.uscfit.Goal;
import charstars.uscfit.RootObjects.Quantifier;
import charstars.uscfit.R;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.MyViewHolder> {
    private List<Goal> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView desc, category;
        public ProgressBar progressBar;
        public RelativeLayout relativeLayout;
        public MyViewHolder(View v) {
            super(v);
            desc = v.findViewById(R.id.description);
            category = v.findViewById(R.id.category);
            progressBar = v.findViewById(R.id.progress);
            relativeLayout = v.findViewById(R.id.goalRowLayout);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GoalAdapter(List<Goal> mDataset) {
        this.mDataset = mDataset;
        Log.d("inside adapter", mDataset.toString());
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GoalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goal_list_row, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Goal activity = mDataset.get(position);
        String quantifier = activity.getQuantifier();
        holder.desc.setTextColor(Color.GRAY);
        if(activity.getGoalNum()==1){
            quantifier = quantifier.substring(0, quantifier.length()-1);
        }
        if(activity.getQuantifier().equals(Quantifier.MINUTES.getMeasurement())){
            holder.desc.setText(activity.getDescription()+" for "+activity.getGoalNum()+" "+quantifier);
        }else{
            holder.desc.setText(activity.getDescription()+" "+activity.getGoalNum()+" "+quantifier);
        }
        if(activity.getGoalNum()==1){
            quantifier = quantifier+"s";
        }
        holder.category.setText(activity.getTrackingNum()+"/"+activity.getGoalNum()+" "+quantifier+" finished");
        Log.d("goalapadter", (int)(activity.getProgress()*100)+"");
        holder.progressBar.setProgress((int)(activity.getProgress()*100));
        holder.relativeLayout.setTag(activity);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(mDataset== null){
            return 0;
        }
        return mDataset.size();
    }
}