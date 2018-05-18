package filmesfamosos.com.br.filmesfamosos.Service;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import filmesfamosos.com.br.filmesfamosos.Model.Movie;
import filmesfamosos.com.br.filmesfamosos.Utils.NetWorking;

/**
 * Created by PDA_Vinicius on 21/04/2018.
 */

public class WebService {


    public static List<Movie> getMovies() {
        List<Movie> lista = new ArrayList<>();
        JSONObject jsonObject;
        try {
            jsonObject =
                    new JSONObject(
                            NetWorking.makeRequest("http://api.themoviedb.org/3/movie/popular?api_key=5ef17c68d28274d819abdd71c6fd31bc"));

            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                Movie m = new Movie();

                m.setId(jsonObject.getInt("id"));
                m.setAdult(jsonObject.getBoolean("adult"));
                m.setOriginalLanguage(jsonObject.getString("original_language"));
                m.setVoteCount(jsonObject.getInt("vote_count"));
                m.setVideo(jsonObject.getBoolean("video"));
                m.setTitle(jsonObject.getString("title"));
                m.setBackDropPath(jsonObject.getString("backdrop_path"));
                m.setPosterPath(jsonObject.getString("poster_path"));
                m.setPopularity(jsonObject.getDouble("popularity"));
                m.setVoteAverage(jsonObject.getLong("vote_average"));
                m.setOriginalTitle(jsonObject.getString("original_title"));
                m.setOverview(jsonObject.getString("overview"));
                m.setReleaseDate(jsonObject.getString("release_date"));

                lista.add(m);
            }


        } catch (JSONException e) {
            lista.add(new Movie(e.getMessage()));
            return lista;
        }

        return lista;

    }

    public static List<Movie> getMoviesTopRated() {
        List<Movie> lista = new ArrayList<>();
        JSONObject jsonObject;
        try {
            jsonObject =
                    new JSONObject(
                            NetWorking.makeRequest("http://api.themoviedb.org/3/movie/top_rated?api_key=5ef17c68d28274d819abdd71c6fd31bc"));

            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                Movie m = new Movie();

                m.setId(jsonObject.getInt("id"));
                m.setAdult(jsonObject.getBoolean("adult"));
                m.setOriginalLanguage(jsonObject.getString("original_language"));
                m.setVoteCount(jsonObject.getInt("vote_count"));
                m.setVideo(jsonObject.getBoolean("video"));
                m.setTitle(jsonObject.getString("title"));
                m.setBackDropPath(jsonObject.getString("backdrop_path"));
                m.setPosterPath(jsonObject.getString("poster_path"));
                m.setPopularity(jsonObject.getDouble("popularity"));
                m.setVoteAverage(jsonObject.getLong("vote_average"));
                m.setOriginalTitle(jsonObject.getString("original_title"));
                m.setOverview(jsonObject.getString("overview"));
                m.setReleaseDate(jsonObject.getString("release_date"));

                lista.add(m);
            }


        } catch (JSONException e) {
            lista.add(new Movie(e.getMessage()));
            return lista;
        }

        return lista;

    }

}
