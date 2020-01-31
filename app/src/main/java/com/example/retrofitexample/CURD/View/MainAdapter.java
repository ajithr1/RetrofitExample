package com.example.retrofitexample.CURD.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitexample.CURD.ModelRetrofit.Note;
import com.example.retrofitexample.R;

import java.util.List;

import static com.example.retrofitexample.CURD.View.MainActivity.TAG;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Note> notes;
    private RecyclerViewAdapter.ItemClickListener itemClickListener;

    MainAdapter(Context context, List<Note> notes, RecyclerViewAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.notes = notes;
        this.itemClickListener = itemClickListener;
    }

    void setNotes(List<Note> notes) {
        Log.d(TAG, "setNotes: ");
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new RecyclerViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, final int position) {
        final Note note = notes.get(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_note.setText(note.getNote());
        holder.tv_date.setText(note.getDate());
        holder.cardView.setCardBackgroundColor(note.getColor());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(note, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return notes.size();
    }

    List<Note> getTodo_list() {
        return notes;
    }

    static class RecyclerViewAdapter extends RecyclerView.ViewHolder{

        TextView tv_title, tv_note, tv_date;
        CardView cardView;

        RecyclerViewAdapter(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.title1);
            tv_note = itemView.findViewById(R.id.note1);
            tv_date = itemView.findViewById(R.id.date1);
            cardView = itemView.findViewById(R.id.card_item);
        }

        public interface ItemClickListener {
            void onItemClick(Note note, int position);
        }
    }
}
