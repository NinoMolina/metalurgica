/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Empleado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Cargo;
import entity.Categoria;
import entity.Domicilio;
import entity.Tipodocumento;
import entity.Usuario;
import entity.Turno;
import java.util.HashSet;
import java.util.Set;
import entity.Detalleplanificacionproduccion;
import entity.Ejecucionetapaproduccion;
import entity.Mantenimientocorrectivo;
import entity.Disponibilidadhoraria;
import entity.Asistencia;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class EmpleadoJpaController {

    public EmpleadoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) throws PreexistingEntityException, Exception {
        if (empleado.getTurnoSet() == null) {
            empleado.setTurnoSet(new HashSet<Turno>());
        }
        if (empleado.getDetalleplanificacionproduccionSet() == null) {
            empleado.setDetalleplanificacionproduccionSet(new HashSet<Detalleplanificacionproduccion>());
        }
        if (empleado.getEjecucionetapaproduccionSet() == null) {
            empleado.setEjecucionetapaproduccionSet(new HashSet<Ejecucionetapaproduccion>());
        }
        if (empleado.getMantenimientocorrectivoSet() == null) {
            empleado.setMantenimientocorrectivoSet(new HashSet<Mantenimientocorrectivo>());
        }
        if (empleado.getDisponibilidadhorariaSet() == null) {
            empleado.setDisponibilidadhorariaSet(new HashSet<Disponibilidadhoraria>());
        }
        if (empleado.getAsistenciaSet() == null) {
            empleado.setAsistenciaSet(new HashSet<Asistencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo cargo = empleado.getCargo();
            if (cargo != null) {
                cargo = em.getReference(cargo.getClass(), cargo.getIdcargo());
                empleado.setCargo(cargo);
            }
            Categoria categoria = empleado.getCategoria();
            if (categoria != null) {
                categoria = em.getReference(categoria.getClass(), categoria.getIdcategoria());
                empleado.setCategoria(categoria);
            }
            Domicilio domicilio = empleado.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                empleado.setDomicilio(domicilio);
            }
            Tipodocumento tipodocumento = empleado.getTipodocumento();
            if (tipodocumento != null) {
                tipodocumento = em.getReference(tipodocumento.getClass(), tipodocumento.getIdtipodocumento());
                empleado.setTipodocumento(tipodocumento);
            }
            Usuario usuario = empleado.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                empleado.setUsuario(usuario);
            }
            Set<Turno> attachedTurnoSet = new HashSet<Turno>();
            for (Turno turnoSetTurnoToAttach : empleado.getTurnoSet()) {
                turnoSetTurnoToAttach = em.getReference(turnoSetTurnoToAttach.getClass(), turnoSetTurnoToAttach.getIdturno());
                attachedTurnoSet.add(turnoSetTurnoToAttach);
            }
            empleado.setTurnoSet(attachedTurnoSet);
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach : empleado.getDetalleplanificacionproduccionSet()) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet.add(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach);
            }
            empleado.setDetalleplanificacionproduccionSet(attachedDetalleplanificacionproduccionSet);
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSet = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach : empleado.getEjecucionetapaproduccionSet()) {
                ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSet.add(ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach);
            }
            empleado.setEjecucionetapaproduccionSet(attachedEjecucionetapaproduccionSet);
            Set<Mantenimientocorrectivo> attachedMantenimientocorrectivoSet = new HashSet<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoSetMantenimientocorrectivoToAttach : empleado.getMantenimientocorrectivoSet()) {
                mantenimientocorrectivoSetMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoSetMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoSetMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoSet.add(mantenimientocorrectivoSetMantenimientocorrectivoToAttach);
            }
            empleado.setMantenimientocorrectivoSet(attachedMantenimientocorrectivoSet);
            Set<Disponibilidadhoraria> attachedDisponibilidadhorariaSet = new HashSet<Disponibilidadhoraria>();
            for (Disponibilidadhoraria disponibilidadhorariaSetDisponibilidadhorariaToAttach : empleado.getDisponibilidadhorariaSet()) {
                disponibilidadhorariaSetDisponibilidadhorariaToAttach = em.getReference(disponibilidadhorariaSetDisponibilidadhorariaToAttach.getClass(), disponibilidadhorariaSetDisponibilidadhorariaToAttach.getId());
                attachedDisponibilidadhorariaSet.add(disponibilidadhorariaSetDisponibilidadhorariaToAttach);
            }
            empleado.setDisponibilidadhorariaSet(attachedDisponibilidadhorariaSet);
            Set<Asistencia> attachedAsistenciaSet = new HashSet<Asistencia>();
            for (Asistencia asistenciaSetAsistenciaToAttach : empleado.getAsistenciaSet()) {
                asistenciaSetAsistenciaToAttach = em.getReference(asistenciaSetAsistenciaToAttach.getClass(), asistenciaSetAsistenciaToAttach.getAsistenciaPK());
                attachedAsistenciaSet.add(asistenciaSetAsistenciaToAttach);
            }
            empleado.setAsistenciaSet(attachedAsistenciaSet);
            em.persist(empleado);
            if (cargo != null) {
                cargo.getEmpleadoSet().add(empleado);
                cargo = em.merge(cargo);
            }
            if (categoria != null) {
                categoria.getEmpleadoSet().add(empleado);
                categoria = em.merge(categoria);
            }
            if (domicilio != null) {
                domicilio.getEmpleadoSet().add(empleado);
                domicilio = em.merge(domicilio);
            }
            if (tipodocumento != null) {
                tipodocumento.getEmpleadoSet().add(empleado);
                tipodocumento = em.merge(tipodocumento);
            }
            if (usuario != null) {
                usuario.getEmpleadoSet().add(empleado);
                usuario = em.merge(usuario);
            }
            for (Turno turnoSetTurno : empleado.getTurnoSet()) {
                turnoSetTurno.getEmpleadoSet().add(empleado);
                turnoSetTurno = em.merge(turnoSetTurno);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : empleado.getDetalleplanificacionproduccionSet()) {
                Empleado oldIdempleadoOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = detalleplanificacionproduccionSetDetalleplanificacionproduccion.getIdempleado();
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdempleado(empleado);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                if (oldIdempleadoOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion != null) {
                    oldIdempleadoOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                    oldIdempleadoOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(oldIdempleadoOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion);
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetEjecucionetapaproduccion : empleado.getEjecucionetapaproduccionSet()) {
                Empleado oldEmpleadoOfEjecucionetapaproduccionSetEjecucionetapaproduccion = ejecucionetapaproduccionSetEjecucionetapaproduccion.getEmpleado();
                ejecucionetapaproduccionSetEjecucionetapaproduccion.setEmpleado(empleado);
                ejecucionetapaproduccionSetEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSetEjecucionetapaproduccion);
                if (oldEmpleadoOfEjecucionetapaproduccionSetEjecucionetapaproduccion != null) {
                    oldEmpleadoOfEjecucionetapaproduccionSetEjecucionetapaproduccion.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccionSetEjecucionetapaproduccion);
                    oldEmpleadoOfEjecucionetapaproduccionSetEjecucionetapaproduccion = em.merge(oldEmpleadoOfEjecucionetapaproduccionSetEjecucionetapaproduccion);
                }
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoSetMantenimientocorrectivo : empleado.getMantenimientocorrectivoSet()) {
                Empleado oldEmpleadoOfMantenimientocorrectivoSetMantenimientocorrectivo = mantenimientocorrectivoSetMantenimientocorrectivo.getEmpleado();
                mantenimientocorrectivoSetMantenimientocorrectivo.setEmpleado(empleado);
                mantenimientocorrectivoSetMantenimientocorrectivo = em.merge(mantenimientocorrectivoSetMantenimientocorrectivo);
                if (oldEmpleadoOfMantenimientocorrectivoSetMantenimientocorrectivo != null) {
                    oldEmpleadoOfMantenimientocorrectivoSetMantenimientocorrectivo.getMantenimientocorrectivoSet().remove(mantenimientocorrectivoSetMantenimientocorrectivo);
                    oldEmpleadoOfMantenimientocorrectivoSetMantenimientocorrectivo = em.merge(oldEmpleadoOfMantenimientocorrectivoSetMantenimientocorrectivo);
                }
            }
            for (Disponibilidadhoraria disponibilidadhorariaSetDisponibilidadhoraria : empleado.getDisponibilidadhorariaSet()) {
                Empleado oldIdempleadoOfDisponibilidadhorariaSetDisponibilidadhoraria = disponibilidadhorariaSetDisponibilidadhoraria.getIdempleado();
                disponibilidadhorariaSetDisponibilidadhoraria.setIdempleado(empleado);
                disponibilidadhorariaSetDisponibilidadhoraria = em.merge(disponibilidadhorariaSetDisponibilidadhoraria);
                if (oldIdempleadoOfDisponibilidadhorariaSetDisponibilidadhoraria != null) {
                    oldIdempleadoOfDisponibilidadhorariaSetDisponibilidadhoraria.getDisponibilidadhorariaSet().remove(disponibilidadhorariaSetDisponibilidadhoraria);
                    oldIdempleadoOfDisponibilidadhorariaSetDisponibilidadhoraria = em.merge(oldIdempleadoOfDisponibilidadhorariaSetDisponibilidadhoraria);
                }
            }
            for (Asistencia asistenciaSetAsistencia : empleado.getAsistenciaSet()) {
                Empleado oldEmpleado1OfAsistenciaSetAsistencia = asistenciaSetAsistencia.getEmpleado1();
                asistenciaSetAsistencia.setEmpleado1(empleado);
                asistenciaSetAsistencia = em.merge(asistenciaSetAsistencia);
                if (oldEmpleado1OfAsistenciaSetAsistencia != null) {
                    oldEmpleado1OfAsistenciaSetAsistencia.getAsistenciaSet().remove(asistenciaSetAsistencia);
                    oldEmpleado1OfAsistenciaSetAsistencia = em.merge(oldEmpleado1OfAsistenciaSetAsistencia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpleado(empleado.getIdempleado()) != null) {
                throw new PreexistingEntityException("Empleado " + empleado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getIdempleado());
            Cargo cargoOld = persistentEmpleado.getCargo();
            Cargo cargoNew = empleado.getCargo();
            Categoria categoriaOld = persistentEmpleado.getCategoria();
            Categoria categoriaNew = empleado.getCategoria();
            Domicilio domicilioOld = persistentEmpleado.getDomicilio();
            Domicilio domicilioNew = empleado.getDomicilio();
            Tipodocumento tipodocumentoOld = persistentEmpleado.getTipodocumento();
            Tipodocumento tipodocumentoNew = empleado.getTipodocumento();
            Usuario usuarioOld = persistentEmpleado.getUsuario();
            Usuario usuarioNew = empleado.getUsuario();
            Set<Turno> turnoSetOld = persistentEmpleado.getTurnoSet();
            Set<Turno> turnoSetNew = empleado.getTurnoSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetOld = persistentEmpleado.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetNew = empleado.getDetalleplanificacionproduccionSet();
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSetOld = persistentEmpleado.getEjecucionetapaproduccionSet();
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSetNew = empleado.getEjecucionetapaproduccionSet();
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSetOld = persistentEmpleado.getMantenimientocorrectivoSet();
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSetNew = empleado.getMantenimientocorrectivoSet();
            Set<Disponibilidadhoraria> disponibilidadhorariaSetOld = persistentEmpleado.getDisponibilidadhorariaSet();
            Set<Disponibilidadhoraria> disponibilidadhorariaSetNew = empleado.getDisponibilidadhorariaSet();
            Set<Asistencia> asistenciaSetOld = persistentEmpleado.getAsistenciaSet();
            Set<Asistencia> asistenciaSetNew = empleado.getAsistenciaSet();
            List<String> illegalOrphanMessages = null;
            for (Asistencia asistenciaSetOldAsistencia : asistenciaSetOld) {
                if (!asistenciaSetNew.contains(asistenciaSetOldAsistencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asistencia " + asistenciaSetOldAsistencia + " since its empleado1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cargoNew != null) {
                cargoNew = em.getReference(cargoNew.getClass(), cargoNew.getIdcargo());
                empleado.setCargo(cargoNew);
            }
            if (categoriaNew != null) {
                categoriaNew = em.getReference(categoriaNew.getClass(), categoriaNew.getIdcategoria());
                empleado.setCategoria(categoriaNew);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                empleado.setDomicilio(domicilioNew);
            }
            if (tipodocumentoNew != null) {
                tipodocumentoNew = em.getReference(tipodocumentoNew.getClass(), tipodocumentoNew.getIdtipodocumento());
                empleado.setTipodocumento(tipodocumentoNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                empleado.setUsuario(usuarioNew);
            }
            Set<Turno> attachedTurnoSetNew = new HashSet<Turno>();
            for (Turno turnoSetNewTurnoToAttach : turnoSetNew) {
                turnoSetNewTurnoToAttach = em.getReference(turnoSetNewTurnoToAttach.getClass(), turnoSetNewTurnoToAttach.getIdturno());
                attachedTurnoSetNew.add(turnoSetNewTurnoToAttach);
            }
            turnoSetNew = attachedTurnoSetNew;
            empleado.setTurnoSet(turnoSetNew);
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSetNew = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSetNew) {
                detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSetNew.add(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSetNew = attachedDetalleplanificacionproduccionSetNew;
            empleado.setDetalleplanificacionproduccionSet(detalleplanificacionproduccionSetNew);
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSetNew = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach : ejecucionetapaproduccionSetNew) {
                ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSetNew.add(ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach);
            }
            ejecucionetapaproduccionSetNew = attachedEjecucionetapaproduccionSetNew;
            empleado.setEjecucionetapaproduccionSet(ejecucionetapaproduccionSetNew);
            Set<Mantenimientocorrectivo> attachedMantenimientocorrectivoSetNew = new HashSet<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach : mantenimientocorrectivoSetNew) {
                mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoSetNew.add(mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach);
            }
            mantenimientocorrectivoSetNew = attachedMantenimientocorrectivoSetNew;
            empleado.setMantenimientocorrectivoSet(mantenimientocorrectivoSetNew);
            Set<Disponibilidadhoraria> attachedDisponibilidadhorariaSetNew = new HashSet<Disponibilidadhoraria>();
            for (Disponibilidadhoraria disponibilidadhorariaSetNewDisponibilidadhorariaToAttach : disponibilidadhorariaSetNew) {
                disponibilidadhorariaSetNewDisponibilidadhorariaToAttach = em.getReference(disponibilidadhorariaSetNewDisponibilidadhorariaToAttach.getClass(), disponibilidadhorariaSetNewDisponibilidadhorariaToAttach.getId());
                attachedDisponibilidadhorariaSetNew.add(disponibilidadhorariaSetNewDisponibilidadhorariaToAttach);
            }
            disponibilidadhorariaSetNew = attachedDisponibilidadhorariaSetNew;
            empleado.setDisponibilidadhorariaSet(disponibilidadhorariaSetNew);
            Set<Asistencia> attachedAsistenciaSetNew = new HashSet<Asistencia>();
            for (Asistencia asistenciaSetNewAsistenciaToAttach : asistenciaSetNew) {
                asistenciaSetNewAsistenciaToAttach = em.getReference(asistenciaSetNewAsistenciaToAttach.getClass(), asistenciaSetNewAsistenciaToAttach.getAsistenciaPK());
                attachedAsistenciaSetNew.add(asistenciaSetNewAsistenciaToAttach);
            }
            asistenciaSetNew = attachedAsistenciaSetNew;
            empleado.setAsistenciaSet(asistenciaSetNew);
            empleado = em.merge(empleado);
            if (cargoOld != null && !cargoOld.equals(cargoNew)) {
                cargoOld.getEmpleadoSet().remove(empleado);
                cargoOld = em.merge(cargoOld);
            }
            if (cargoNew != null && !cargoNew.equals(cargoOld)) {
                cargoNew.getEmpleadoSet().add(empleado);
                cargoNew = em.merge(cargoNew);
            }
            if (categoriaOld != null && !categoriaOld.equals(categoriaNew)) {
                categoriaOld.getEmpleadoSet().remove(empleado);
                categoriaOld = em.merge(categoriaOld);
            }
            if (categoriaNew != null && !categoriaNew.equals(categoriaOld)) {
                categoriaNew.getEmpleadoSet().add(empleado);
                categoriaNew = em.merge(categoriaNew);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getEmpleadoSet().remove(empleado);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getEmpleadoSet().add(empleado);
                domicilioNew = em.merge(domicilioNew);
            }
            if (tipodocumentoOld != null && !tipodocumentoOld.equals(tipodocumentoNew)) {
                tipodocumentoOld.getEmpleadoSet().remove(empleado);
                tipodocumentoOld = em.merge(tipodocumentoOld);
            }
            if (tipodocumentoNew != null && !tipodocumentoNew.equals(tipodocumentoOld)) {
                tipodocumentoNew.getEmpleadoSet().add(empleado);
                tipodocumentoNew = em.merge(tipodocumentoNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getEmpleadoSet().remove(empleado);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getEmpleadoSet().add(empleado);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Turno turnoSetOldTurno : turnoSetOld) {
                if (!turnoSetNew.contains(turnoSetOldTurno)) {
                    turnoSetOldTurno.getEmpleadoSet().remove(empleado);
                    turnoSetOldTurno = em.merge(turnoSetOldTurno);
                }
            }
            for (Turno turnoSetNewTurno : turnoSetNew) {
                if (!turnoSetOld.contains(turnoSetNewTurno)) {
                    turnoSetNewTurno.getEmpleadoSet().add(empleado);
                    turnoSetNewTurno = em.merge(turnoSetNewTurno);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetOldDetalleplanificacionproduccion : detalleplanificacionproduccionSetOld) {
                if (!detalleplanificacionproduccionSetNew.contains(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion.setIdempleado(null);
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccion : detalleplanificacionproduccionSetNew) {
                if (!detalleplanificacionproduccionSetOld.contains(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion)) {
                    Empleado oldIdempleadoOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getIdempleado();
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.setIdempleado(empleado);
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    if (oldIdempleadoOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion != null && !oldIdempleadoOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.equals(empleado)) {
                        oldIdempleadoOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                        oldIdempleadoOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(oldIdempleadoOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetOldEjecucionetapaproduccion : ejecucionetapaproduccionSetOld) {
                if (!ejecucionetapaproduccionSetNew.contains(ejecucionetapaproduccionSetOldEjecucionetapaproduccion)) {
                    ejecucionetapaproduccionSetOldEjecucionetapaproduccion.setEmpleado(null);
                    ejecucionetapaproduccionSetOldEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSetOldEjecucionetapaproduccion);
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetNewEjecucionetapaproduccion : ejecucionetapaproduccionSetNew) {
                if (!ejecucionetapaproduccionSetOld.contains(ejecucionetapaproduccionSetNewEjecucionetapaproduccion)) {
                    Empleado oldEmpleadoOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion = ejecucionetapaproduccionSetNewEjecucionetapaproduccion.getEmpleado();
                    ejecucionetapaproduccionSetNewEjecucionetapaproduccion.setEmpleado(empleado);
                    ejecucionetapaproduccionSetNewEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSetNewEjecucionetapaproduccion);
                    if (oldEmpleadoOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion != null && !oldEmpleadoOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion.equals(empleado)) {
                        oldEmpleadoOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccionSetNewEjecucionetapaproduccion);
                        oldEmpleadoOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion = em.merge(oldEmpleadoOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion);
                    }
                }
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoSetOldMantenimientocorrectivo : mantenimientocorrectivoSetOld) {
                if (!mantenimientocorrectivoSetNew.contains(mantenimientocorrectivoSetOldMantenimientocorrectivo)) {
                    mantenimientocorrectivoSetOldMantenimientocorrectivo.setEmpleado(null);
                    mantenimientocorrectivoSetOldMantenimientocorrectivo = em.merge(mantenimientocorrectivoSetOldMantenimientocorrectivo);
                }
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoSetNewMantenimientocorrectivo : mantenimientocorrectivoSetNew) {
                if (!mantenimientocorrectivoSetOld.contains(mantenimientocorrectivoSetNewMantenimientocorrectivo)) {
                    Empleado oldEmpleadoOfMantenimientocorrectivoSetNewMantenimientocorrectivo = mantenimientocorrectivoSetNewMantenimientocorrectivo.getEmpleado();
                    mantenimientocorrectivoSetNewMantenimientocorrectivo.setEmpleado(empleado);
                    mantenimientocorrectivoSetNewMantenimientocorrectivo = em.merge(mantenimientocorrectivoSetNewMantenimientocorrectivo);
                    if (oldEmpleadoOfMantenimientocorrectivoSetNewMantenimientocorrectivo != null && !oldEmpleadoOfMantenimientocorrectivoSetNewMantenimientocorrectivo.equals(empleado)) {
                        oldEmpleadoOfMantenimientocorrectivoSetNewMantenimientocorrectivo.getMantenimientocorrectivoSet().remove(mantenimientocorrectivoSetNewMantenimientocorrectivo);
                        oldEmpleadoOfMantenimientocorrectivoSetNewMantenimientocorrectivo = em.merge(oldEmpleadoOfMantenimientocorrectivoSetNewMantenimientocorrectivo);
                    }
                }
            }
            for (Disponibilidadhoraria disponibilidadhorariaSetOldDisponibilidadhoraria : disponibilidadhorariaSetOld) {
                if (!disponibilidadhorariaSetNew.contains(disponibilidadhorariaSetOldDisponibilidadhoraria)) {
                    disponibilidadhorariaSetOldDisponibilidadhoraria.setIdempleado(null);
                    disponibilidadhorariaSetOldDisponibilidadhoraria = em.merge(disponibilidadhorariaSetOldDisponibilidadhoraria);
                }
            }
            for (Disponibilidadhoraria disponibilidadhorariaSetNewDisponibilidadhoraria : disponibilidadhorariaSetNew) {
                if (!disponibilidadhorariaSetOld.contains(disponibilidadhorariaSetNewDisponibilidadhoraria)) {
                    Empleado oldIdempleadoOfDisponibilidadhorariaSetNewDisponibilidadhoraria = disponibilidadhorariaSetNewDisponibilidadhoraria.getIdempleado();
                    disponibilidadhorariaSetNewDisponibilidadhoraria.setIdempleado(empleado);
                    disponibilidadhorariaSetNewDisponibilidadhoraria = em.merge(disponibilidadhorariaSetNewDisponibilidadhoraria);
                    if (oldIdempleadoOfDisponibilidadhorariaSetNewDisponibilidadhoraria != null && !oldIdempleadoOfDisponibilidadhorariaSetNewDisponibilidadhoraria.equals(empleado)) {
                        oldIdempleadoOfDisponibilidadhorariaSetNewDisponibilidadhoraria.getDisponibilidadhorariaSet().remove(disponibilidadhorariaSetNewDisponibilidadhoraria);
                        oldIdempleadoOfDisponibilidadhorariaSetNewDisponibilidadhoraria = em.merge(oldIdempleadoOfDisponibilidadhorariaSetNewDisponibilidadhoraria);
                    }
                }
            }
            for (Asistencia asistenciaSetNewAsistencia : asistenciaSetNew) {
                if (!asistenciaSetOld.contains(asistenciaSetNewAsistencia)) {
                    Empleado oldEmpleado1OfAsistenciaSetNewAsistencia = asistenciaSetNewAsistencia.getEmpleado1();
                    asistenciaSetNewAsistencia.setEmpleado1(empleado);
                    asistenciaSetNewAsistencia = em.merge(asistenciaSetNewAsistencia);
                    if (oldEmpleado1OfAsistenciaSetNewAsistencia != null && !oldEmpleado1OfAsistenciaSetNewAsistencia.equals(empleado)) {
                        oldEmpleado1OfAsistenciaSetNewAsistencia.getAsistenciaSet().remove(asistenciaSetNewAsistencia);
                        oldEmpleado1OfAsistenciaSetNewAsistencia = em.merge(oldEmpleado1OfAsistenciaSetNewAsistencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = empleado.getIdempleado();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getIdempleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Asistencia> asistenciaSetOrphanCheck = empleado.getAsistenciaSet();
            for (Asistencia asistenciaSetOrphanCheckAsistencia : asistenciaSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Asistencia " + asistenciaSetOrphanCheckAsistencia + " in its asistenciaSet field has a non-nullable empleado1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cargo cargo = empleado.getCargo();
            if (cargo != null) {
                cargo.getEmpleadoSet().remove(empleado);
                cargo = em.merge(cargo);
            }
            Categoria categoria = empleado.getCategoria();
            if (categoria != null) {
                categoria.getEmpleadoSet().remove(empleado);
                categoria = em.merge(categoria);
            }
            Domicilio domicilio = empleado.getDomicilio();
            if (domicilio != null) {
                domicilio.getEmpleadoSet().remove(empleado);
                domicilio = em.merge(domicilio);
            }
            Tipodocumento tipodocumento = empleado.getTipodocumento();
            if (tipodocumento != null) {
                tipodocumento.getEmpleadoSet().remove(empleado);
                tipodocumento = em.merge(tipodocumento);
            }
            Usuario usuario = empleado.getUsuario();
            if (usuario != null) {
                usuario.getEmpleadoSet().remove(empleado);
                usuario = em.merge(usuario);
            }
            Set<Turno> turnoSet = empleado.getTurnoSet();
            for (Turno turnoSetTurno : turnoSet) {
                turnoSetTurno.getEmpleadoSet().remove(empleado);
                turnoSetTurno = em.merge(turnoSetTurno);
            }
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet = empleado.getDetalleplanificacionproduccionSet();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : detalleplanificacionproduccionSet) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdempleado(null);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
            }
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet = empleado.getEjecucionetapaproduccionSet();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetEjecucionetapaproduccion : ejecucionetapaproduccionSet) {
                ejecucionetapaproduccionSetEjecucionetapaproduccion.setEmpleado(null);
                ejecucionetapaproduccionSetEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSetEjecucionetapaproduccion);
            }
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSet = empleado.getMantenimientocorrectivoSet();
            for (Mantenimientocorrectivo mantenimientocorrectivoSetMantenimientocorrectivo : mantenimientocorrectivoSet) {
                mantenimientocorrectivoSetMantenimientocorrectivo.setEmpleado(null);
                mantenimientocorrectivoSetMantenimientocorrectivo = em.merge(mantenimientocorrectivoSetMantenimientocorrectivo);
            }
            Set<Disponibilidadhoraria> disponibilidadhorariaSet = empleado.getDisponibilidadhorariaSet();
            for (Disponibilidadhoraria disponibilidadhorariaSetDisponibilidadhoraria : disponibilidadhorariaSet) {
                disponibilidadhorariaSetDisponibilidadhoraria.setIdempleado(null);
                disponibilidadhorariaSetDisponibilidadhoraria = em.merge(disponibilidadhorariaSetDisponibilidadhoraria);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Empleado.findAll");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Empleado findEmpleado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
