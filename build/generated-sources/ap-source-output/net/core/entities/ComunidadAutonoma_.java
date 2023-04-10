package net.core.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.core.entities.Provincia;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-10T12:22:34", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(ComunidadAutonoma.class)
public class ComunidadAutonoma_ { 

    public static volatile SingularAttribute<ComunidadAutonoma, Integer> id;
    public static volatile SingularAttribute<ComunidadAutonoma, Integer> poblacion;
    public static volatile SingularAttribute<ComunidadAutonoma, String> nombre;
    public static volatile ListAttribute<ComunidadAutonoma, Provincia> provinciaList;

}