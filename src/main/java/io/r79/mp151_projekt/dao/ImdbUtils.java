package io.r79.mp151_projekt.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by R79 on 06.01.2015.
 */
public class ImdbUtils {

    private ImdbUtils() {
        //hide constructor
    }

    public static String receiveImdbLink(String movieTitleInput) {
        URL URL;
        String inputLine = "";
        String code = "";
        Document doc = null;
        String IMDBID = "";


        String movieTitle = movieTitleInput;
        if(movieTitle.lastIndexOf('/')>=0) {
            movieTitle = movieTitle.substring(0, movieTitle.lastIndexOf('/'));
        }
        movieTitle = movieTitle.trim().replace(' ', '+');

        try {
            URL = new URL("http://www.omdbapi.com/?t=" + movieTitle
                    + "&y=&plot=short&r=xml");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    URL.openStream()));
            while ((inputLine = in.readLine()) != null) {
                code += inputLine;
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            doc = loadXMLFromString(code);
        } catch (Exception e) {
            e.printStackTrace();
        }

        doc.normalize();

        NodeList nList = doc.getElementsByTagName("movie");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                IMDBID = eElement.getAttribute("imdbID");
            }
        }

        return "http://www.imdb.com/title/" + IMDBID;
    }


    private static Document loadXMLFromString(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }
}
