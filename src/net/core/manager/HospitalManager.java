package net.core.manager;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
            this.provincias = provinciasToStream().toList();
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
    //imprimir el numero de hospitales por cada habitante de cada provincia
    //Nº de camas en funcionamiento / Población * 100.000

    public void numCamasFuncionamientoPromedio() {
        provincias.stream().map(provincia -> numCamasHospitales(provincia) / provincia.getPoblacion() * 100000)
                .forEach(System.out::println);
    }

    public Double numCamasHospitales(Provincia provincia) {
        return provincia.getHospitalesList().stream().mapToDouble(h -> h.getTotalcamas()).sum();
    }

    public ComunidadAutonoma comunidadAutonomaById(int idComunidad) {
        Optional<ComunidadAutonoma> op = comunidades.stream().filter(c -> c.getId() == idComunidad).findFirst();
        //System.out.println(op.get());
        return op.orElse(new ComunidadAutonoma());
    }

    public ComunidadAutonoma comunidadAutonomaById(String nombre) {
        Optional<ComunidadAutonoma> op = comunidades.stream().filter(c -> c.getNombre().equalsIgnoreCase(nombre)).findFirst();
        //System.out.println(op.get());
        return op.orElse(new ComunidadAutonoma());
    }

    public Provincia provinciaById(int idProvincia) {
        return idProvincia > 52 ? new Provincia() : provincias.get(idProvincia - 1);
    }

    public Provincia provinciaByName(String nombre) {
        return provincias.stream().filter(p -> p.getNombre().equalsIgnoreCase(nombre)).findFirst().orElse(new Provincia());
    }

    // Imprimir el número de camas libres por provincia
    public double numCamasLibresPorProvincia(int idProvincia) {
        var provincia = provinciaById(idProvincia);
        double numCamas = provincia.getHospitalesList().stream()
                .mapToDouble(h -> h.getTotalcamas() - (h.getOcupadascovid19() + h.getOcupadasnocovid19())).sum();
        System.out.println(provincia.getNombre() + " camas libres: " + numCamas);
        return numCamas;
    }

    public double numCamasLibresPorProvincia(int idProvincia, LocalDate fechaInteres) {
        var provincia = provinciaById(idProvincia);
        double numCamas
                = provincia.getHospitalesList().stream().filter(h -> fechaInteres.equals(LocalDate.parse(h.getFecha())))
                        .mapToDouble(h -> h.getTotalcamas() - (h.getOcupadascovid19() + h.getOcupadasnocovid19())).sum();
        System.out.println(provincia.getNombre() + " camas libres: " + numCamas);
        return numCamas;
    }

    public double numCamasLibresPorProvincia(String nombreProv, LocalDate fechaInteres) {
        var provincia = provinciaByName(nombreProv);
        double numCamas
                = provincia.getHospitalesList().stream().filter(h -> fechaInteres.equals(LocalDate.parse(h.getFecha())))
                        .mapToDouble(h -> h.getTotalcamas() - (h.getOcupadascovid19() + h.getOcupadasnocovid19())).sum();
        System.out.println(provincia.getNombre() + " camas libres: " + numCamas);
        return numCamas;
    }

    public double mediaCamasOcupadaCovidPorProvincia(String nombreProv, LocalDate fechaInteres) {
        var provincia = provinciaByName(nombreProv);
        double numCamas
                = provincia.getHospitalesList().stream().filter(h -> fechaInteres.equals(LocalDate.parse(h.getFecha())))
                        .mapToDouble(h -> h.getOcupadascovid19() / h.getOcupadasnocovid19()).average().getAsDouble();
        System.out.println(provincia.getNombre() + " camas libres: " + numCamas);
        return numCamas;
    }

}
