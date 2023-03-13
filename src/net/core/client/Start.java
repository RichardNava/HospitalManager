package net.core.client;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;
import net.core.dao.CollectionsDAO;

public class Start {

    public static void main(String[] args) throws IOException {
        var ls = new CollectionsDAO();
//        ls.inputProvincias().forEach(s -> System.out.println(s.getName()));
        ls.provToCSV("src\\net\\core\\data\\poblacion_provincias.csv");
    }

}
