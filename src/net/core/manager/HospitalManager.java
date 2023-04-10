package net.core.manager;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.core.dao.JdbcDAO;
import net.core.entities.ComunidadAutonoma;
import net.core.entities.Provincia;

public class HospitalManager {

    private Set<ComunidadAutonoma> comunidades;
    private List<Provincia> provincias;

    public HospitalManager() {
        try (JdbcDAO dao = new JdbcDAO("jdbc:sqlite:hospital_manager.db")) {
            this.comunidades = new HashSet<>(dao.selectAllCA());
        } catch (SQLException ex) {
            Logger.getLogger(HospitalManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(HospitalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Set<ComunidadAutonoma> getComunidades() {
        return comunidades;
    }

    public Map<Integer, Integer> numHospitalesProvincia() {
        return provincias.stream()
                .peek(n -> System.out.println(n.getNombre() + " " + n.getHospitalesList().size()))
                .collect(Collectors.toMap((Provincia k) -> k.getId(), (Provincia p) -> p.getHospitalesList().size()));
    }

    public Stream<Provincia> provinciasToStream() {
        return comunidades.stream()
                .flatMap(n -> n.getProvinciaList().stream().map(p -> p));

    }

    public int numHospitalsCA(List<Provincia> provincias) {
        return provincias.stream().mapToInt(p -> p.getHospitalesList().size()).sum();
    }

    public int hospitalesTotales() {
        return comunidades.stream()
                .peek(c -> System.out.println(c.getNombre() + " " + numHospitalsCA(c.getProvinciaList())))
                .mapToInt(c -> numHospitalsCA(c.getProvinciaList()))
                .sum();
    }
}
