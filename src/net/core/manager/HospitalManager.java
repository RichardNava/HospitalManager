
package net.core.manager;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.core.dao.JdbcDAO;
import net.core.entities.ComunidadAutonoma;


public class HospitalManager {
    private Set<ComunidadAutonoma> comunidades;

    public HospitalManager() {
        try(JdbcDAO dao = new JdbcDAO("jdbc:sqlite:hospital_manager.db")){            
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
    
    
    
}
