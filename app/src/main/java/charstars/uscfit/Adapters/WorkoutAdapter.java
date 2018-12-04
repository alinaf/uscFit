package charstars.uscfit.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import charstars.uscfit.DataHandlers.UpdateWorkouts;
import charstars.uscfit.R;
import charstars.uscfit.RootObjects.Workout;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.MyViewHolder> {

    private List<Workout> workoutList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category, length, date, caloriesBurned;
        public CheckBox checkBox;
        public RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            category = view.findViewById(R.id.category);
            length = view.findViewById(R.id.length);
            date = view.findViewById(R.id.date);
            caloriesBurned = view.findViewById(R.id.caloriesBurned);
            checkBox =  view.findViewById(R.id.checkBox);
            relativeLayout = view.findViewById(R.id.workoutRowLayout);

        }
    }


    public WorkoutAdapter(List<Workout> workoutList) {
        List<Workout> listOfWorkouts = new ArrayList<Workout>();
         java.util.Date date1 = new java.util.Date();
        for(Workout w: workoutList)
        {
            if(date1.getTime() - w.getDate().getTime() < 5274000000L)
            {
                listOfWorkouts.add(w);
            }
        }
        this.workoutList = listOfWorkouts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_workout_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Workout workout = workoutList.get(position);
        holder.category.setText(workout.getActivity().getCategory());
        holder.length.setText(Integer.toString(workout.getLength()) + " Minutes");
        holder.date.setText(workout.stringDate());
        holder.caloriesBurned.setText(Double.toString(workout.getCaloriesBurned()) + " calories");
        holder.checkBox.setTag(workout);
        if (workout.isCompleted()) {
            holder.checkBox.setChecked(true);
            holder.itemView.setBackgroundColor(Color.argb(211, 211, 211, 211));
        }
        else if(!workout.isCompleted()){
            holder.checkBox.setChecked(false);
            holder.itemView.setBackgroundColor(Color.argb(0, 0, 0, 0));
        }
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }
}