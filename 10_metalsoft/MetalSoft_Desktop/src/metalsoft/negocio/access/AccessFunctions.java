/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.access;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;

/**
 *
 * @author Nino
 */
public class AccessFunctions {

    public static long nvoNroPiezaReal(Connection cn) {
        String query = "{ ? = call nvonropiezareal()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int nvoNroPedido(Connection cn) {
        String query = "{ ? = call nvonropedido()}";
        int result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.execute();
            result = cs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static String nvoNroProducto(Connection cn) {
        String query = "{ ? = call nvonroproducto()}";
        String result = "-1";
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.VARCHAR);
            cs.execute();
            result = cs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroPresupuesto(Connection cn) {
        String query = "{ ? = call nvonropresupuesto()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroPlanificacionProduccion(Connection cn) {
        String query = "{ ? = call nvonroplanificacionproduccion()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroCliente(Connection cn) {
        String query = "{ ? = call nvonrocliente()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroEmpleado(Connection cn) {
        String query = "{ ? = call nvonroempleado()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroEmpresaMetalurgica(Connection cn) {
        String query = "{ ? = call nvonroempresametalurgica()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroProveedor(Connection cn) {
        String query = "{ ? = call nvonroproveedor()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroProveedorMantenimiento(Connection cn) {
        String query = "{ ? = call nvonroprovmantenimiento()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroPedidoMatriz(Connection cn) {
        String query = "{ ? = call nvonropedidomatriz()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroMaquina(Connection cn) {
        String query = "{ ? = call nvonromaquina()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static String nvoNroOrden(Connection cn) {
        String query = "{ ? = call nvonroorden(?)}";
        String result = "-1";
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.VARCHAR);
            cs.execute();
            result = cs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroMateriaPrima(Connection cn) {
        String query = "{ ? = call nvonromateriaprima(?)}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.VARCHAR);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int cantPiezasXProducto(long id, Connection cn) {
        String query = "{ ? = call cantpiezasxproducto(?)}";
        int result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.setLong(2, id);
            cs.execute();
            result = cs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static boolean esProductoSinAlgunaEtapa(long idProd, Connection cn) {
        String query = "{ ? = call esproductosinalgunaetapa(?)}";
        boolean result = false;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
            cs.setLong(2, idProd);
            cs.execute();
            result = cs.getBoolean(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int cantPiezasDePedido(long idPed, Connection cn) {
        String query = "{ ? = call cantpiezasdepedido(?)}";
        int result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.setLong(2, idPed);
            cs.execute();
            result = cs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int cantPiezasDePedido2(long idPed, Connection cn) {
        String query = "{ ? = call cantpiezasdepedido2(?)}";
        int result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.setLong(2, idPed);
            cs.execute();
            result = cs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static double ingresoXPedido(long idpedido, Connection cn) {
        String query = "{ ? = call ingresoxpedido(?)}";
        double result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.DOUBLE);
            cs.setLong(2, idpedido);
            cs.execute();
            result = cs.getDouble(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroEjecucionPlanificacionProduccion(Connection cn) {
        String query = "{ ? = call nvonroejecucionplanificacion()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroEjecucionEtapa(Connection cn) {
        String query = "{ ? = call nvonroejecucionetapa()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static boolean esUltimaEjecucionEtapaDeProduccion(Long idejecucion) {

        String query = "{ ? = call esUltimaEjecucionEtapaDeProduccion(?)}";
        boolean result = false;
        PostgreSQLManager pg = new PostgreSQLManager();
        CallableStatement cs = null;
        try {
            cs = pg.concectGetCn().prepareCall(query);
            cs.setLong(2, idejecucion);
            cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
            cs.execute();
            result = cs.getBoolean(1);
        } catch (Exception ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cs.close();
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static boolean esUltimaEjecucionEtapaDePieza(long idejecucionproduccion, long idproducto, long idpieza) {

        String query = "{ ? = call esUltimaEjecucionEtapaDePieza(?,?,?)}";
        boolean result = false;
        PostgreSQLManager pg = new PostgreSQLManager();
        CallableStatement cs = null;
        try {
            cs = pg.concectGetCn().prepareCall(query);
            cs.setLong(2, idejecucionproduccion);
            cs.setLong(3, idproducto);
            cs.setLong(4, idpieza);
            cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
            cs.execute();
            result = cs.getBoolean(1);
        } catch (Exception ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cs.close();
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;

    }

    public static long nvoNroTrabajoTercerizado(Connection cn) {
        String query = "{ ? = call nvonrotrabajotercerizado()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroMantenimientoPreventivo(Connection cn) {
        String query = "{ ? = call nvonromantenimientopreventivo()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Date calcularHoraFinPrevistaPlanificacionProduccion(Long idPlanificacionProduccion) {
        String query = "{ ? = call horafinprevistaplanifprod(?)}";
        Date result = null;
        PostgreSQLManager pg = new PostgreSQLManager();
        CallableStatement cs = null;
        try {
            cs = pg.concectGetCn().prepareCall(query);
            cs.setLong(2, idPlanificacionProduccion);
            cs.registerOutParameter(1, java.sql.Types.TIME);
            cs.execute();
            result = cs.getTime(1);
        } catch (Exception ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cs.close();
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }

    public static long nvoNroPlanificacionCalidad(Connection cn) {
        String query = "{ ? = call nvonroplanificacioncalidad()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroProductoReal() {
        String query = "{ ? = call nvonroproductoreal()}";
        long result = -1;
        PostgreSQLManager pg = new PostgreSQLManager();
        CallableStatement cs = null;
        try {
            cs = pg.concectGetCn().prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (Exception ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cs.close();
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static long nvoNroEjecucionPlanificacionCalidad(Connection cn) {
        String query = "{ ? = call nvonroejecplanifcalidad()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long nvoNroEjecucionProcesoCalidad(Connection cn) {
        String query = "{ ? = call nvonroejecprocalidad()}";
        long result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BIGINT);
            cs.execute();
            result = cs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static boolean esUltimaEjecucionProcesoCalidadDePieza(long idejecucionplanificacioncalidad, long idproducto, long idpieza) {
        String query = "{ ? = call esUltimaEjecProcCalidadDePieza(?,?,?)}";
        boolean result = false;
        PostgreSQLManager pg = new PostgreSQLManager();
        CallableStatement cs = null;
        try {
            cs = pg.concectGetCn().prepareCall(query);
            cs.setLong(2, idejecucionplanificacioncalidad);
            cs.setLong(3, idproducto);
            cs.setLong(4, idpieza);
            cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
            cs.execute();
            result = cs.getBoolean(1);
        } catch (Exception ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cs.close();
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static boolean esUltimaEjecucionProcesoCalidadDeCalidad(Long idejecucion) {
        String query = "{ ? = call esUltimaEjecProcCalidadDeCalidad(?)}";
        boolean result = false;
        PostgreSQLManager pg = new PostgreSQLManager();
        CallableStatement cs = null;
        try {
            cs = pg.concectGetCn().prepareCall(query);
            cs.setLong(2, idejecucion);
            cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
            cs.execute();
            result = cs.getBoolean(1);
        } catch (Exception ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cs.close();
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
