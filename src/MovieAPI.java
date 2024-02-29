import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MovieAPI {
	
    private final String apiKey;

    public MovieAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getRealTimeMovieInfo() {
       String apiUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.xml?key=" + apiKey;
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        return response.toString();
    }
    
}