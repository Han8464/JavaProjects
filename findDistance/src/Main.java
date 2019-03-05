import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class DistanceBetweenCitys
{
    HashMap<String, HashMap<String, Integer>> C2CDistance = new HashMap<>();

    public void insert(String c1, String c2, int d)
    {
        HashMap<String, Integer> cityDis = new HashMap<>();
        cityDis.put(c2, d);
        C2CDistance.put(c1, cityDis);
    }

    public int get(String c1, String c2) {
        HashMap<String, Integer> cityDis = new HashMap<>();
        cityDis = C2CDistance.get(c1);
        return cityDis.get(c2);
    }
}

public class Main {

    public static void main(String args[])
    {
        ArrayList<String> citys = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        String city = sc.next();
        while(!city.equals("###"))
        {
            citys.add(city);
            city = sc.next();
        }
        for(int i = 0; i < citys.size(); i++)
        {
            for(int j = 0; j < citys.size();j++)
            {

            }
        }

    }



}
