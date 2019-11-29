/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoparse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Student
 */
public class AutoParse {
    
    private static String[] models;
    private static String[][][] marks;
    
    public static void main(String[] args) {
        models = new String[20];
        String URL = "https://www.lada.ru/";
        try {
            parseHTML(URL);
            //getDataFromURL(URL);
        } catch (IOException ex) {
            Logger.getLogger(AutoParse.class.getName()).log(Level.SEVERE, null, ex);
        }
        Window w = new Window();
        w.setVisible(true);
    }
    
    private static void parseHTML(String URL) throws IOException{
        Document html = Jsoup.connect(URL).get();
        String title = html.title();
        System.out.println(title);
        
        String select = ".cars-menu__wrapper .cars-menu__sem";
        Elements items = html.select(select);
        int n = items.size();
        
        models = new String[n];
        marks = new String[n][][];
        
        for (int i = 0; i<n; i++){
            Element group = items.get(i);
            title = group.getElementsByTag("p").text();
            models[i] = title;
            System.out.println(title);
           
            Elements items2 = group.select("a");
            int k = items2.size();
            String[][] ans = new String[2][k];
            for (int j = 0; j<k; j++){
                Element item = items2.get(j);
                title = item.text();
                String url = item.absUrl("href");
                ans[0][j] = title;
                ans[1][j] = url;
                System.out.println("\t"+title+" "+url);
            }
            marks[i] = ans;
        }
    }
    
    public static String[] getModels(){
        return models;
    }
    
    public static String[] getMarks(int id){
        return marks[id][0];
    }
    /*
    public static String[] getMarks(int n) throws IOException{
        String select = ".cars-menu__wrapper .cars-menu__sem";
        Elements models = html.select(select);
        int n = models.size();
        this.models = new String[n];
        for (Element model: models){
            title = model.getElementsByTag("p").text();
            //String cat_url = category.absUrl("href");
            //System.out.println(title+": "+cat_url);
            //getItemsFromURL(cat_url);
            System.out.println(title);
        }
        
    }
*/
    
}
