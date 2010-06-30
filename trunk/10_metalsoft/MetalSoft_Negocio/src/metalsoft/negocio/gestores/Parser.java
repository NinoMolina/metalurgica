/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.datos.dbobject.Condicioniva;
import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.negocio.produccion.TipoMaterial;
import metalsoft.datos.dbobject.Pieza;
import metalsoft.negocio.ventas.CondicionIva;
/**
 *
 * @author Vicky
 */
public class Parser {

    public static TipoMaterial[] parseToTipomaterial(Tipomaterial[] tm) {
        if(tm==null)return null;

        TipoMaterial[] c=new TipoMaterial[tm.length];
        for(int i=0;i<tm.length;i++)
        {
            TipoMaterial x=new TipoMaterial();
            x.setNombre(tm[i].getNombre());
            x.setDescripcion(tm[i].getDescripcion());
            c[i]=x;
        }
        return c;
    }
    public static metalsoft.negocio.ventas.Pieza[] parseToPieza(Pieza[] tm) {
        if(tm==null)return null;

        metalsoft.negocio.ventas.Pieza[] c=new metalsoft.negocio.ventas.Pieza[tm.length];
        for(int i=0;i<tm.length;i++)
        {
            metalsoft.negocio.ventas.Pieza x=new metalsoft.negocio.ventas.Pieza();
            x.setNombre(tm[i].getNombre());
            x.setDimensiones(tm[i].getDimensiones());

            //x.setTipo(tm[i].getTipomaterial());
            c[i]=x;
        }
        return c;
    }

    public static CondicionIva[] parseToCondIva(Condicioniva[] ci) {
        if(ci==null) return null;
        CondicionIva[] x=new CondicionIva[ci.length];
        CondicionIva object=null;
        for(int i=0;i<ci.length;i++)
        {
            object=new CondicionIva();
            object.setNombre(ci[i].getNombre());
            object.setDescripcion(ci[i].getDescripcion());
            x[i]=object;
        }
        return x;
    }

}
