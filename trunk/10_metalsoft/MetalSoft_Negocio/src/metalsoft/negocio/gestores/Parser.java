/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.datos.dbobject.Condicioniva;
import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.negocio.produccion.TipoMaterial;
import metalsoft.datos.dbobject.Pieza;
import metalsoft.negocio.ItemCombo;
import metalsoft.negocio.compras.Responsable;
import metalsoft.negocio.rrhh.Domicilio;
import metalsoft.negocio.ventas.Cliente;
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

    public static metalsoft.datos.dbobject.Domicilio parseToDomicilioDB(Domicilio x)
    {
        metalsoft.datos.dbobject.Domicilio db=new metalsoft.datos.dbobject.Domicilio();
        db.setCalle(x.getCalle());
        db.setDepto(x.getDepto());
        db.setNumerocalle(x.getNumeroCalle());
        db.setPiso(x.getPiso());
        db.setTorre(x.getTorre());
        return db;
    }

    public static Domicilio parseToDomicilio(metalsoft.datos.dbobject.Domicilio x)
    {
        Domicilio neg=new Domicilio();
        neg.setCalle(x.getCalle());
        neg.setDepto(x.getDepto());
        neg.setNumeroCalle(x.getNumerocalle());
        neg.setPiso(x.getPiso());
        neg.setTorre(x.getTorre());
        return neg;
    }

    public static metalsoft.datos.dbobject.Responsable parseToResponsableDB(Responsable x)
    {
        metalsoft.datos.dbobject.Responsable db=new metalsoft.datos.dbobject.Responsable();
        db.setApellido(x.getApellido());
        db.setEmail(x.getEmail());
        db.setFax(x.getFax());
        db.setNombre(x.getNombre());
        db.setNrodocumento(x.getNroDocumento());
        db.setTelefono(x.getTelefono());
        return db;
    }

    public static Responsable parseToResponsable(metalsoft.datos.dbobject.Responsable x)
    {
        Responsable neg=new Responsable();
        neg.setApellido(x.getApellido());
        neg.setEmail(x.getEmail());
        neg.setFax(x.getFax());
        neg.setNombre(x.getNombre());
        neg.setNroDocumento(x.getNrodocumento());
        neg.setTelefono(x.getTelefono());
        return neg;
    }

    public static ItemCombo[] parseToItemCombo(metalsoft.datos.dbobject.Cliente[] x)
    {
        ItemCombo[] items=new ItemCombo[x.length];
        int index=0;
        for(metalsoft.datos.dbobject.Cliente c:x)
        {
            items[index]=new ItemCombo(String.valueOf(index), c.getRazonsocial());
            index++;
        }
        return items;
    }

    
}
