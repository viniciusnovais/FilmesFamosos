package filmesfamosos.com.br.filmesfamosos.Utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by PDA_Vinicius on 21/04/2018.
 */

public class NetWorking {

    public static String makeRequest(String urlString) throws IOException{

        HttpURLConnection httpURLConnection = null;
        BufferedReader reader = null;
        String result;

        try{

            URL url = new URL(urlString);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();

            if (inputStream ==null){
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null){
                buffer.append(line.concat("\n"));
            }

            if (buffer.length() == 0){
                return null;
            }

            result = buffer.toString();
        }finally {

            if (httpURLConnection !=null){
                httpURLConnection.disconnect();
            }

            if (reader !=null){

                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("Stream","erro fechando strem",e);
                }
            }
        }

        return result;
    }
}
