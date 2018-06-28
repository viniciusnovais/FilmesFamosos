package filmesfamosos.com.br.filmesfamosos;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import filmesfamosos.com.br.filmesfamosos.Adapter.FilmesListAdapter;
import filmesfamosos.com.br.filmesfamosos.Model.Movie;
import filmesfamosos.com.br.filmesfamosos.Service.WebService;
import filmesfamosos.com.br.filmesfamosos.Utils.VerificaConexao;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FilmesListAdapter adapter;
    private FloatingActionButton fabPai, fabFilho1, fabFilho2;
    private TextView tvErro;
    private LinearLayout ll1, ll2, layoutCarregamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        ll1 = findViewById(R.id.layout1);
        ll2 = findViewById(R.id.layout2);
        fabPai = findViewById(R.id.fabPai);
        fabFilho1 = findViewById(R.id.fab1);
        fabFilho2 = findViewById(R.id.fab2);
        tvErro = findViewById(R.id.tvErro);
        layoutCarregamento = findViewById(R.id.layoutCarregamento);

        fabPai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ll1.getVisibility() == View.VISIBLE
                        || ll2.getVisibility() == View.VISIBLE) {
                    ll1.setVisibility(View.GONE);
                    ll2.setVisibility(View.GONE);
                } else {

                    ll1.setVisibility(View.VISIBLE);
                    ll2.setVisibility(View.VISIBLE);
                }
            }
        });


        fabFilho2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VerificaConexao.isNetworkConnected(MainActivity.this)) {
                    fabPai.setImageResource(R.drawable.top_games_star);
                    new GetMovieTopRatedTask().execute();
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.sem_internet), Toast.LENGTH_SHORT).show();
                }

            }
        });

        fabFilho1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (VerificaConexao.isNetworkConnected(MainActivity.this)) {
                    fabPai.setImageResource(R.drawable.popular);
                    new GetMovieTask().execute();
                } else {
                    layoutCarregamento.setVisibility(View.GONE);
                    tvErro.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    tvErro.setText(getString(R.string.sem_internet));
                }
            }
        });

        if (VerificaConexao.isNetworkConnected(this)) {
            new GetMovieTask().execute();
        } else {
            layoutCarregamento.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            tvErro.setVisibility(View.VISIBLE);
            tvErro.setText(getString(R.string.sem_internet));
        }
    }


    public class GetMovieTask extends AsyncTask {


        @Override
        protected Object doInBackground(Object[] objects) {


            return WebService.getMovies();

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            layoutCarregamento.setVisibility(View.GONE);

            final List<Movie> lista = (List<Movie>) o;
            if (lista.size() > 0) {

                if (lista.get(0).getMsgErro() == null) {
                    recyclerView.setVisibility(View.VISIBLE);
                    tvErro.setVisibility(View.GONE);
                    GridLayoutManager glm = new GridLayoutManager(MainActivity.this, 2);
                    recyclerView.setLayoutManager(glm);

                    adapter = new FilmesListAdapter(MainActivity.this, lista);
                    recyclerView.setAdapter(adapter);

                    adapter.ItemClickListener(new FilmesListAdapter.ItemClick() {
                        @Override
                        public void onClick(int position) {
                            Intent i = new Intent(MainActivity.this, DetalheFilmeActivity.class);
                            i.putExtra("movie", lista.get(position));
                            startActivity(i);
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, lista.get(0).getMsgErro(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, getString(R.string.lista_vazia), Toast.LENGTH_SHORT).show();
            }

        }
    }

    public class GetMovieTopRatedTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {


            return WebService.getMoviesTopRated();

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            layoutCarregamento.setVisibility(View.GONE);

            final List<Movie> lista = (List<Movie>) o;

            if (lista.size() > 0) {

                if (lista.get(0).getMsgErro() == null) {
                    recyclerView.setVisibility(View.VISIBLE);
                    tvErro.setVisibility(View.GONE);
                    GridLayoutManager glm = new GridLayoutManager(MainActivity.this, 2);
                    recyclerView.setLayoutManager(glm);

                    adapter = new FilmesListAdapter(MainActivity.this, lista);
                    recyclerView.setAdapter(adapter);

                    adapter.ItemClickListener(new FilmesListAdapter.ItemClick() {
                        @Override
                        public void onClick(int position) {
                            Intent i = new Intent(MainActivity.this, DetalheFilmeActivity.class);
                            i.putExtra("movie", lista.get(position));
                            startActivity(i);
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.erro), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, getString(R.string.lista_vazia), Toast.LENGTH_SHORT).show();

            }

        }
    }
}
