package charstars.uscfit;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class BadgeAdapter extends RecyclerView.Adapter<BadgeAdapter.MyViewHolder> {

    private Context mContext;
    private List<Badge> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public BadgeAdapter(Context mContext, List<Badge> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_badges_display, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Badge album = albumList.get(position);
        holder.title.setText(album.getDescription());
        holder.count.setText(album.getDate().toString());

        // loading album cover using Glide library
        if(album.level.equals(BadgeLevel.FIFTY)){

            Glide.with(mContext).load(R.drawable.barbell).into(holder.thumbnail);
        }else if(album.level.equals(BadgeLevel.HUNDRED)){
            Glide.with(mContext).load(R.drawable.barbell).into(holder.thumbnail);

        }else if(album.level.equals(BadgeLevel.ONETHOUSAND)){

            Glide.with(mContext).load(R.drawable.barbell).into(holder.thumbnail);
        }else if(album.level.equals(BadgeLevel.FIVEHUNDRED)){

            Glide.with(mContext).load(R.drawable.barbell).into(holder.thumbnail);
        }


//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
//        PopupMenu popup = new PopupMenu(mContext, view);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.menu_album, popup.getMenu());
//        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
//        popup.show();
        return;
    }

//    /**
//     * Click listener for popup menu items
//     */
//    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
//
//        public MyMenuItemClickListener() {
//        }
//
//        @Override
//        public boolean onMenuItemClick(MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.action_add_favourite:
//                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.action_play_next:
//                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
//                    return true;
//                default:
//            }
//            return false;
//        }
//    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
