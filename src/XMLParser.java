import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class XMLParser {
    public static void main(String[] args) {
        try {
            File inputFile = new File("movie_data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList movieList = doc.getElementsByTagName("movie");
            for (int i = 0; i < movieList.getLength(); i++) {
                Node movieNode = movieList.item(i);
                if (movieNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element movieElement = (Element) movieNode;
                    String movieName = movieElement.getElementsByTagName("movieNm").item(0).getTextContent();
                    String director = movieElement.getElementsByTagName("director").item(0).getTextContent();
                    // 필요한 정보를 추출하여 사용하기
                    System.out.println("영화 이름: " + movieName);
                    System.out.println("감독: " + director);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}