package charstars.uscfit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.MyViewHolder> {

    private List<Workout> workoutList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category, length, date, caloriesBurned;

        public MyViewHolder(View view) {
            super(view);
            category = view.findViewById(R.id.category);
            length =  view.findViewById(R.id.length);
            date = view.findViewById(R.id.date);
            caloriesBurned = view.findViewById(R.id.caloriesBurned);
        }
    }


    public WorkoutAdapter(List<Workout> workoutList) {
        this.workoutList = workoutList;
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
        holder.length.setText(Integer.toString(workout.getLength()) + workout.getQuant().getMeasurement());
        holder.date.setText(workout.getDate());
        holder.caloriesBurned.setText(Integer.toString(workout.getCaloriesBurned()));
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }
}