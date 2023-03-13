package net.core.dao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.core.models.Hospital;
import net.core.models.Provincia;

public final class CollectionsDAO {

    private final List<String[]> datos = new ArrayList<>();
    private final Set<Hospital> hospitales = new TreeSet<>();
    private final Path URL = Path.of("src\\net\\core\\data\\hospitales_capacidad.csv").toAbsolutePath();
    private String[] indices;

    public CollectionsDAO() {
        inputData(csvToList(URL));
    }

    public List<String> csvToList(Path f) {
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(f, Charset.forName("ISO-8859-1"));
            indices = list.remove(0).split(";");
        } catch (IOException ex) {
            Logger.getLogger(CollectionsDAO.class.getName()).log(Level.SEVERE, "Ruta no encontrada", ex);
        }
        return list;
    }
    public Stream<String> provToCSV(String file) throws IOException{
        List<String> list = Files.readAllLines(Path.of(file).toAbsolutePath(), Charset.forName("UTF-8"));
        List<String> list2 = new ArrayList<>();
        Map<String,Integer> popu = new HashMap<>();
        for (int i = 0; i < list.size(); i+=2) {
            var arr = list.get(i).split(";");
            if(arr[2].isBlank()) continue;
            popu.putIfAbsent(arr[2], Integer.valueOf(arr[arr.length-1].replace(".", "")));
            list2.add(list.get(i));
            
        }
        popu.forEach((k,v)-> setPop(k,v));
       return list2.stream();
    }

    private void setPop(String key, int value) {
        inputProvincias().stream()
                .filter(p -> key.substring(0, 4).equalsIgnoreCase(remove1(p.getName().substring(0, 4))))
                .peek(p -> p.setPoblacion(value))
                .map(p -> p.toCSV())
                .forEach(s -> {
            try {
                Files.writeString(Path.of("src\\net\\core\\data\\provincias.csv").toAbsolutePath(), s, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                Logger.getLogger(CollectionsDAO.class.getName()).log(Level.SEVERE, "Archivo no encontrado", ex);
            }
        });
        
    }

    private String remove1(String texto) {
        String original = "¿¡¬√ƒ≈∆«»… ÀÃÕŒœ–—“”‘’÷ÿŸ⁄€‹›ﬂ‡·‚„‰ÂÊÁËÈÍÎÏÌÓÔÒÚÛÙıˆ¯˘˙˚¸˝ˇ";
        String ascii = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyy";
        String output = texto;
        for (int i = 0; i < original.length(); i++) {
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }
        return output;
    }

    private void inputData(Collection<String> data) {
        this.dataToList(data);
        this.inputHospitals(data);
    }

    public void dataToList(Collection<String> data) {
        for (String dato : data) {
            datos.add(dato.split(";"));
        }
    }

    public void inputHospitals(Collection<String> data) { // TODO: aÒadir ids provincias y CCAA
        for (String dato : data) {
            String[] rows = dato.split(";");
            hospitales.add(new Hospital(rows[0], rows[1], rows[5], Integer.parseInt(rows[6])));
        }
    }

    public List<Provincia> inputProvincias() {
        return datos.stream()
                .map(arr -> new Provincia(Integer.parseInt(arr[4]), Integer.parseInt(arr[2]), arr[5]))
                .distinct()
                .sorted((a, b) -> a.getID() - b.getID())
                .collect(Collectors.toList());
    }

    public List<String[]> getHospitalesDatos() {
        return datos;
    }

    public Set<Hospital> getHospitales() {
        return hospitales;
    }

    public String[] getIndices() {
        return indices;
    }
}

