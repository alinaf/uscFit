package charstars.uscfit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.MyViewHolder> {
    private List<Goal> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView desc, category;
        public MyViewHolder(View v) {
            super(v);
            desc = v.findViewById(R.id.description);
            category = v.findViewById(R.id.category);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GoalAdapter(List<Goal> mDataset) {
        this.mDataset = mDataset;
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
        holder.desc.setText(activity.getDescription());
        holder.category.setText(activity.getTrackingNum()+"/"+activity.getGoalNum()+" "+activity.getQuantifier()+" finished");

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}