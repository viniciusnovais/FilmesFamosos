package filmesfamosos.com.br.filmesfamosos.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.squareup.picasso.Picasso;

import java.util.List;

import filmesfamosos.com.br.filmesfamosos.Model.Movie;
import filmesfamosos.com.br.filmesfamosos.R;

/**
 * Created by PDA_Vinicius on 21/04/2018.
 */

public class FilmesListAdapter extends RecyclerView.Adapter<FilmesListAdapter.MyViewHolder> {

    private Context context;
    private List<Movie> lista;
    private LayoutInflater layoutInflater;
    public ItemClick itemClick;

    public interface ItemClick{
        void onClick(int position);
    }

    public void ItemClickListener(ItemClick itemClick){
        this.itemClick = itemClick;
    }

    public FilmesListAdapter(Context context, List<Movie> lista) {
        this.context = context;
        this.lista = lista;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.adapter_list_item_filmes, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Movie m = lista.get(position);

        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w342/" + m.getPosterPath())
                .into(holder.imageFilme);

        holder.ratingBar.setRating(m.getVoteAverage());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageFilme;
        RatingBar ratingBar;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageFilme = itemView.findViewById(R.id.image_filme);
            ratingBar = itemView.findViewById(R.id.rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClick.onClick(getAdapterPosition());
        }
    }
}
