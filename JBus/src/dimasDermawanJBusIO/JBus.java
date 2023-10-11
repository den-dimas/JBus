package dimasDermawanJBusIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class JBus {
    public static void main(String[] args) {
        String filepath = "C:\\Users\\Den\\Documents\\_perkuliahan\\Semester 3\\Pemrograman Berorientasi Objek dan Praktikum\\Praktikum\\JBus\\data\\station.json";
        Gson gson = new Gson();

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(filepath));

            List<Station> statsionjson = gson.fromJson(buffer, new TypeToken<List<Station>>() {}.getType());

            statsionjson.forEach(s -> System.out.println(s.toString()));
            System.out.println();

            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Bus createBus() {
        Price price = new Price(10000, 0);
        Station depok = new Station("DEPOK", City.DEPOK, "DEPOK");
        Station jakarta = new Station( "JAKARTA", City.JAKARTA, "JAKARTA");

        return new Bus("Dermawan's Bus", Facility.AC, price, 25, BusType.REGULER, City.DEPOK, depok, jakarta);
    }
}
