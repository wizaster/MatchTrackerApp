package Model;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emporium.matchtrackerapp.DeckDetails;
import com.emporium.matchtrackerappv2.R;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    public static final String TAG = "RVAdapter";
    private ArrayList<String> deckImages = new ArrayList<>();
    private ArrayList<DeckId> deckNames = new ArrayList<>();

    private Context context;
    private RecyclerView.ViewHolder viewHolder;
    private Listener listener;

    public RecyclerViewAdapter(){

    }
    public RecyclerViewAdapter(ArrayList<String> deckImages, ArrayList<DeckId> deckNames, Context context, Listener listener){
        this.context = context;
        this.deckImages = deckImages;
        this.deckNames = deckNames;
        this.listener = listener;
        Log.d(TAG, "RecyclerViewAdapter: constructor--<>--<>--<>");

    }
    public interface Listener{
        void onItemClicked(int id);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_deck_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        ArrayList<Integer> colorCombination = getColors(holder, position);
        int nbColors = colorCombination.size();
        for(int i = 0; i < nbColors; i++){
            switch(i){
                case 0:
                    holder.image1.setImageResource(colorCombination.get(i));
                    break;
                case 1:
                    holder.image2.setImageResource(colorCombination.get(i));
                    break;
                case 2:
                    holder.image3.setImageResource(colorCombination.get(i));
                    break;
                case 3:
                    holder.image4.setImageResource(colorCombination.get(i));
                    break;
                case 4:
                    holder.image5.setImageResource(colorCombination.get(i));
                    break;
            }

        }

        holder.deckName.setText((deckNames.get(position)).getName());
        holder.rvLayout.forceLayout();
        final int deckIdClick = (deckNames.get(position)).getId();

        //todo deck stats
        final Context context = getContext();

        holder.rvLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onBindViewHolder: " + deckIdClick);
                listener.onItemClicked(deckIdClick);
            }
        });



    }

    @Override
    public int getItemCount() {
        return deckNames.size();
    }
    public ArrayList<Integer> getColors(ViewHolder holder, final int position){
        ArrayList<Integer> colorCombination = new ArrayList<>();
        int colorLength = (deckImages.get(position)).length();
        String colors = deckImages.get(position);
        for(int i = 0; i < colorLength; i++){
            switch(colors.charAt(i)){
                case 'W':
                    colorCombination.add(R.drawable.ic_plains);
                    break;
                case 'U':
                    colorCombination.add(R.drawable.ic_island);
                    break;
                case 'B':
                    colorCombination.add(R.drawable.ic_swamp);
                    break;
                case 'R':
                    colorCombination.add(R.drawable.ic_mountain);
                    break;
                case 'G':
                    colorCombination.add(R.drawable.ic_forest);
                    break;
                case 'O':
                    colorCombination.add(R.drawable.ic_colorless);
                    break;
            }
        }
        return colorCombination;
    }

    public Context getContext() {
        return context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image1;
        ImageView image2;
        ImageView image3;
        ImageView image4;
        ImageView image5;
        TextView deckName;
        LinearLayout rvLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.color1);
            image2 = itemView.findViewById(R.id.color2);
            image3 = itemView.findViewById(R.id.color3);
            image4 = itemView.findViewById(R.id.color4);
            image5 = itemView.findViewById(R.id.color5);
            deckName = itemView.findViewById(R.id.deck_name);
            rvLayout = itemView.findViewById(R.id.deck_item);
        }
    }
}
