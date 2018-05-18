package filmesfamosos.com.br.filmesfamosos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import filmesfamosos.com.br.filmesfamosos.Model.Movie;

/**
 * Created by PDA_Vinicius on 21/04/2018.
 */

public class DetalheFilmeActivity extends AppCompatActivity {

    private Movie movie = new Movie();
    private ImageView imageView;
    private TextView tvTitle,tvSinopse,tvLancamento,tvAvaliacao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_filme_activity);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        imageView = findViewById(R.id.imageView);
        tvTitle = findViewById(R.id.title);
        tvSinopse = findViewById(R.id.sinopse);
        tvAvaliacao = findViewById(R.id.avaliacao);
        tvLancamento = findViewById(R.id.dataLancamento);

        Picasso.with(this).load("http://image.tmdb.org/t/p/original/" + movie.getBackDropPath()).into(imageView);

        tvTitle.setText(movie.getTitle());

        tvSinopse.setText(movie.getOverview());

        tvLancamento.setText(movie.getReleaseDate());

        tvAvaliacao.setText(String.format("%.2f",movie.getVoteAverage()));
    }
}
