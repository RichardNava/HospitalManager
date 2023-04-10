package net.core.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.core.entities.ComunidadAutonoma;
import net.core.entities.Hospital;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-10T12:22:34", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Provincia.class)
public class Provincia_ { 

    public static volatile SingularAttribute<Provincia, ComunidadAutonoma> idCa;
    public static volatile ListAttribute<Provincia, Hospital> hospitalesList;
    public static volatile SingularAttribute<Provincia, Integer> id;
    public static volatile SingularAttribute<Provincia, Integer> poblacion;
    public static volatile SingularAttribute<Provincia, String> nombre;

}