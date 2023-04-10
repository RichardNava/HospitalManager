package net.core.entities;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.core.entities.Provincia;
import net.core.models.Unidad;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-10T12:22:34", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Hospital.class)
public class Hospital_ { 

    public static volatile SingularAttribute<Hospital, LocalDate> fecha;
    public static volatile SingularAttribute<Hospital, Unidad> unidad;
    public static volatile SingularAttribute<Hospital, Provincia> idProvincia;
    public static volatile SingularAttribute<Hospital, Double> ocupadasnocovid19;
    public static volatile SingularAttribute<Hospital, Double> ingresoscovid19;
    public static volatile SingularAttribute<Hospital, Double> altas24hcovid19;
    public static volatile SingularAttribute<Hospital, Double> totalcamas;
    public static volatile SingularAttribute<Hospital, Integer> id;
    public static volatile SingularAttribute<Hospital, Double> ocupadascovid19;

}