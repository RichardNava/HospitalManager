package net.core.client;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import net.core.dao.CollectionsDAO;
import net.core.dao.JDBCDAO;
import net.core.models.Provincia;

public class Start {

    public static void main(String[] args) throws IOException {
        var ls = new CollectionsDAO();
//        ls.inputProvincias().forEach(s -> System.out.println(s.getName()));
        try (JDBCDAO dao = new JDBCDAO("jdbc:sqlite:D:\\Programación\\Proyectos\\HospitalManager\\src\\net\\core\\data\\hospital_manager.db")) {
            //            System.out.println(dao.createTable());
            //            dao.insertarHospitals();
            dao.insertHospitals();
            //            ls.inputProvincias();
            //        ls.setPop(ls.provToCSV2("src\\net\\core\\data\\poblacion_provincias.csv",  Charset.forName("UTF-8")));
            //            dao.insertarProvincias();

//          ls.inputCCAA(dao.comunidadAutonomaPop());
//            dao.insertComunidadesAutonomas();

        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, "Excepcion de SQL", ex);
        } catch (Exception ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, "Otro tipo de Excepcion", ex);
        }

    }

}
