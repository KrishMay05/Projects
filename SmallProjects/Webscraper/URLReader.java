package SmallProjects.Webscraper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
public class URLReader {
    public static void main(String[] args) throws IOException {
        Scanner area = new Scanner(System.in);
        // String[] cities = {"Accra", "Algiers", "Amsterdam", "Anchorage", "Athens", "Auckland", "Baghdad", "Bangkok", "Barcelona", "Beijing", "Beirut", "Bengaluru", "Berlin", "Bogota", "Boston", "Budapest", "Buenos Aires", "Cairo", "Cape Town", "Caracas", "Chicago", "Dallas", "Denver", "Dubai", "Hong Kong", "Kabul", "Kathmandu", "Kolkata", "Kuala Lumpur", "La Paz", "Lagos", "Las Vegas", "Lima", "London", "Mexico City", "Miami", "Mumbai", "Nairobi", "New Delhi", "New York", "Oslo", "Paris", "Phoenix", "Prague", "Rome", "San Francisco", "Seattle", "Seoul", "Shanghai", "Singapore", "Stockholm", "Sydney", "Tokyo", "Toronto", "Warsaw", "Zagreb" };
        // System.out.println("Please Enter The Number Of City You Want To Know The Time Of? There are "+cities.length+ " options." );
        // int cityNum = area.nextInt();
        // try{
        //     System.out.println(cities[cityNum-1]+" : "+time(cities[cityNum-1]));
        // } catch (Exception e) {
        //     System.out.println("Not that many cities!");
        // }
        System.out.println("Please Enter The Name Of The City You Want To Know The Time Of?" );
        String cityName = area.nextLine();
        try{
            time(cityName);
            System.out.println(cityName +" : "+time(cityName));
        } catch (Exception e) {
            System.out.println("Sorry the city is not in the database.");
        }

        area.close();     
    }  
    public static String time(String Location) throws IOException{
         URL weather = new URL("https://www.timeanddate.com/worldclock/");
        BufferedReader read = new BufferedReader(new InputStreamReader(weather.openStream()));
        String htmlCode;
        int charNum;
        int charNum2;
        while( (htmlCode = read.readLine())!=null){
            if(htmlCode.contains(Location)){
                //System.out.println(htmlCode.indexOf("New York"));
                charNum = htmlCode.indexOf(Location);
                //System.out.println(htmlCode.substring(charNum,charNum+100));
                //System.out.println(htmlCode); 
                if(htmlCode.substring(charNum,charNum+100).contains(":")){
                    charNum2 = htmlCode.substring(charNum,charNum+150).indexOf(":");
                    return htmlCode.substring(charNum+charNum2-2,charNum+charNum2+6);

                }
            }
            
        }
        read.close(); 
        return "error";

    }
}