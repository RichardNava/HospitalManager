package net.core.dao;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import net.core.entities.ComunidadAutonoma;
import net.core.entities.Hospital;
import net.core.entities.Provincia;
import net.core.models.ProvinciaCSV;
import net.core.models.Unidad;

public class JdbcDAO implements AutoCloseable {

    private final Connection con;
    private final CollectionsDAO cDAO = new CollectionsDAO();

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JdbcDAO.class.getName()).log(Level.SEVERE, "No se ha cargado el driver", ex);
        }
    }

    public JdbcDAO(String URL) throws SQLException {
        this.con = DriverManager.getConnection(URL);

    }

//    public boolean createTable() throws SQLException {
////        for (String in : cDAO.getIndices()) {
////            System.out.println(in.replace("_", "").toLowerCase()+" REAL,");
////        }
//
//        Statement st = con.createStatement();
//        return st.execute("CREATE TABLE hospitales(\n"
//                + "\n"
//                + "ID INTEGER PRIMARY KEY\n"
//                + "\n"
//                + "NOT NULL,\n"
//                + "\n"
//                + "fecha TEXT (15),\n"
//                + "\n"
//                + "unidad TEXT (40),\n"
//                + "\n"
//                + "totalcamas REAL,\n"
//                + "ocupadascovid19 REAL,\n"
//                + "ocupadasnocovid19 REAL,\n"
//                + "ingresoscovid19 REAL,\n"
//                + "altas24hcovid19 REAL\n"
//                + "\n"
//                + ");");
//    }
    public void insertarHospitals() throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO "
                + "hospitales2(fecha,unidad,totalcamas,ocupadascovid19,ocupadasnocovid19,ingresoscovid19,altas24hcovid19,id_provincia)"
                + "VALUES(?,?,?,?,?,?,?,?)");
        long start = System.currentTimeMillis();
        List<String[]> listB = cDAO.getHospitalesDatos().subList(0, 101);
        for (String[] d : listB) {
            ps.setString(1, d[0]);
            ps.setString(2, d[1]);
            ps.setDouble(3, Double.valueOf(d[6]));
            ps.setDouble(4, Double.valueOf(d[7]));
            ps.setDouble(5, Double.valueOf(d[9]));
            ps.setDouble(6, Double.valueOf(d[10]));
            ps.setDouble(7, Double.valueOf(d[11]));
            ps.setDouble(8, Integer.valueOf(d[4]));
            ps.execute();
        }
        long end = System.currentTimeMillis();
        System.out.println("Tiempo de método SIN Batch " + (end - start));
    }

    public void insertHospitals() throws SQLException {

        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO hospitales(fecha,unidad,totalCamas,ocupadasCovid19,ocupadasNoCovid19,ingresosCovid19,altas24hCovid19,id_provincia) "
                + "values(?,?,?,?,?,?,?,?)");
        long start = System.currentTimeMillis();
        cDAO.getHospitalesDatos()
                .stream()
                .skip(15000)
                .limit(30000)
                .forEach((String[] s) -> {
                    try {
                        ps.setString(1, s[0]);
                        ps.setString(2, s[1]);
                        ps.setDouble(3, Double.parseDouble(s[6]));
                        ps.setDouble(4, Double.parseDouble(s[7]));
                        ps.setDouble(5, Double.parseDouble(s[9]));
                        ps.setDouble(6, Double.parseDouble(s[10]));
                        ps.setDouble(7, Double.parseDouble(s[11]));
                        ps.setInt(8, Integer.parseInt(s[4]));
                        ps.addBatch();
                    } catch (SQLException ex) {
                        Logger.getLogger(JdbcDAO.class.getName()).log(Level.SEVERE, "", ex);
                    }
                });
        ps.executeBatch();
        long end = System.currentTimeMillis();
        System.out.println("Tiempo de método CON Batch " + (end - start));
    }

    public void insertarProvincias() throws SQLException, IOException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO provincia(id_ca,nombre,poblacion) values(?,?,?)");
        Files.lines(Path.of("src\\net\\core\\data\\provincias.csv").toAbsolutePath(), Charset.forName("ISO-8859-1"))
                .map(s -> s.split(","))
                .peek(s -> System.out.println(Arrays.toString(s)))
                .forEach((String[] s) -> {
                    try {
                        ps.setInt(1, Integer.parseInt(s[1]));
                        ps.setString(2, s[2]);
                        ps.setInt(3, Integer.parseInt(s[3]));
                        ps.addBatch();
                    } catch (SQLException ex) {
                        Logger.getLogger(JdbcDAO.class.getName()).log(Level.SEVERE, "Error de conexión", ex);
                    }
                });
        ps.executeBatch();
    }

    public void insertComunidadesAutonomas() throws SQLException, IOException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO comunidad_autonoma(nombre,poblacion) values(?,?)");
        Files.lines(Path.of("src\\net\\core\\data\\comunidades_autonomas.csv").toAbsolutePath(), Charset.forName("ISO-8859-1"))
                .map(s -> s.split(","))
                .peek(s -> System.out.println(Arrays.toString(s)))
                .forEach((String[] s) -> {
                    try {
                        ps.setString(1, s[1]);
                        ps.setInt(2, Integer.parseInt(s[2]));
                        ps.addBatch();
                    } catch (SQLException ex) {
                        Logger.getLogger(JdbcDAO.class.getName()).log(Level.SEVERE, "Error de conexión", ex);
                    }
                });
        ps.executeBatch();
    }

    public List<ProvinciaCSV> readProvincias() throws SQLException {
        List<ProvinciaCSV> provincias = new ArrayList<>();
        Statement st = con.createStatement();
        st.execute("""
                   SELECT id,
                          id_ca,
                          nombre,
                          poblacion
                     FROM provincia;""");
        ResultSet rs = st.getResultSet();
        while (rs.next()) {
            ProvinciaCSV p = new ProvinciaCSV(rs.getInt(1), rs.getInt(2), rs.getString(3));
            p.setPoblacion(rs.getInt(4));
            provincias.add(p);
        }
        return provincias;
    }

    public Map<Integer, List<ProvinciaCSV>> groupingProv() throws SQLException {
        return readProvincias()
                .stream()
                .collect(Collectors.groupingBy(p -> p.getIDCCAA()));
    }

    public Map<Integer, Integer> comunidadAutonomaPop() throws SQLException {
        var provMap = groupingProv();
        return provMap
                .keySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                k -> k,
                                k -> provMap
                                        .get(k)
                                        .stream()
                                        .mapToInt(p -> p.getPoblacion())
                                        .sum())
                );
    }

    public List<Hospital> selectHospitalByIDProvincia(int idProvincia) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM hospitales WHERE id_provincia = ?");
        ps.setInt(1, idProvincia);
        ResultSet rs = ps.executeQuery();
        List<Hospital> hospitales = new ArrayList<>();
        while (rs.next()) {
            hospitales.add(new Hospital(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getDouble(5),
                    rs.getDouble(6),
                    rs.getDouble(7),
                    rs.getDouble(8),
                    rs.getInt(9)));
        }
        return hospitales;

    }

    public List<Provincia> selectProvinciaByIDCA(int idComunidadAutonoma) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM provincia WHERE id_ca = ?");
        ps.setInt(1, idComunidadAutonoma);
        ResultSet rs = ps.executeQuery();
        List<Provincia> provincias = new ArrayList<>();
        while (rs.next()) {
            provincias.add(new Provincia(rs.getInt(1), rs.getString(3), rs.getInt(4), selectHospitalByIDProvincia(rs.getInt(1)), rs.getInt(2)));
        }
        return provincias;

    }

    public List<ComunidadAutonoma> selectAllCA() throws SQLException {
        Statement ps = con.createStatement();
        ResultSet rs = ps.executeQuery("SELECT * FROM comunidad_autonoma");
        List<ComunidadAutonoma> comunidades = new ArrayList<>();
        while (rs.next()) {
            comunidades.add(new ComunidadAutonoma(rs.getInt(1), rs.getString(2), rs.getInt(3), selectProvinciaByIDCA(rs.getInt(1))));
        }
        return comunidades;
    }

    @Override
    public void close() throws Exception {
        this.con.close();
    }

}
