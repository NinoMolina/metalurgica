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
import entity.Detalleplanificacionproduccion;
import java.util.HashSet;
import java.util.Set;
import entity.Empleadoxturno;
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
        if (empleado.getDetalleplanificacionproduccionSet() == null) {
            empleado.setDetalleplanificacionproduccionSet(new HashSet<Detalleplanificacionproduccion>());
        }
        if (empleado.getDetalleplanificacionproduccionSet1() == null) {
            empleado.setDetalleplanificacionproduccionSet1(new HashSet<Detalleplanificacionproduccion>());
        }
        if (empleado.getEmpleadoxturnoSet() == null) {
            empleado.setEmpleadoxturnoSet(new HashSet<Empleadoxturno>());
        }
        if (empleado.getEmpleadoxturnoSet1() == null) {
            empleado.setEmpleadoxturnoSet1(new HashSet<Empleadoxturno>());
        }
        if (empleado.getEjecucionetapaproduccionSet() == null) {
            empleado.setEjecucionetapaproduccionSet(new HashSet<Ejecucionetapaproduccion>());
        }
        if (empleado.getEjecucionetapaproduccionSet1() == null) {
            empleado.setEjecucionetapaproduccionSet1(new HashSet<Ejecucionetapaproduccion>());
        }
        if (empleado.getMantenimientocorrectivoSet() == null) {
            empleado.setMantenimientocorrectivoSet(new HashSet<Mantenimientocorrectivo>());
        }
        if (empleado.getMantenimientocorrectivoSet1() == null) {
            empleado.setMantenimientocorrectivoSet1(new HashSet<Mantenimientocorrectivo>());
        }
        if (empleado.getDisponibilidadhorariaSet() == null) {
            empleado.setDisponibilidadhorariaSet(new HashSet<Disponibilidadhoraria>());
        }
        if (empleado.getDisponibilidadhorariaSet1() == null) {
            empleado.setDisponibilidadhorariaSet1(new HashSet<Disponibilidadhoraria>());
        }
        if (empleado.getAsistenciaSet() == null) {
            empleado.setAsistenciaSet(new HashSet<Asistencia>());
        }
        if (empleado.getAsistenciaSet1() == null) {
            empleado.setAsistenciaSet1(new HashSet<Asistencia>());
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
            Cargo cargo1 = empleado.getCargo1();
            if (cargo1 != null) {
                cargo1 = em.getReference(cargo1.getClass(), cargo1.getIdcargo());
                empleado.setCargo1(cargo1);
            }
            Categoria categoria = empleado.getCategoria();
            if (categoria != null) {
                categoria = em.getReference(categoria.getClass(), categoria.getIdcategoria());
                empleado.setCategoria(categoria);
            }
            Categoria categoria1 = empleado.getCategoria1();
            if (categoria1 != null) {
                categoria1 = em.getReference(categoria1.getClass(), categoria1.getIdcategoria());
                empleado.setCategoria1(categoria1);
            }
            Domicilio domicilio = empleado.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                empleado.setDomicilio(domicilio);
            }
            Domicilio domicilio1 = empleado.getDomicilio1();
            if (domicilio1 != null) {
                domicilio1 = em.getReference(domicilio1.getClass(), domicilio1.getIddomicilio());
                empleado.setDomicilio1(domicilio1);
            }
            Tipodocumento tipodocumento = empleado.getTipodocumento();
            if (tipodocumento != null) {
                tipodocumento = em.getReference(tipodocumento.getClass(), tipodocumento.getIdtipodocumento());
                empleado.setTipodocumento(tipodocumento);
            }
            Tipodocumento tipodocumento1 = empleado.getTipodocumento1();
            if (tipodocumento1 != null) {
                tipodocumento1 = em.getReference(tipodocumento1.getClass(), tipodocumento1.getIdtipodocumento());
                empleado.setTipodocumento1(tipodocumento1);
            }
            Usuario usuario = empleado.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                empleado.setUsuario(usuario);
            }
            Usuario usuario1 = empleado.getUsuario1();
            if (usuario1 != null) {
                usuario1 = em.getReference(usuario1.getClass(), usuario1.getIdusuario());
                empleado.setUsuario1(usuario1);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach : empleado.getDetalleplanificacionproduccionSet()) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet.add(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach);
            }
            empleado.setDetalleplanificacionproduccionSet(attachedDetalleplanificacionproduccionSet);
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet1 = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach : empleado.getDetalleplanificacionproduccionSet1()) {
                detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet1.add(detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach);
            }
            empleado.setDetalleplanificacionproduccionSet1(attachedDetalleplanificacionproduccionSet1);
            Set<Empleadoxturno> attachedEmpleadoxturnoSet = new HashSet<Empleadoxturno>();
            for (Empleadoxturno empleadoxturnoSetEmpleadoxturnoToAttach : empleado.getEmpleadoxturnoSet()) {
                empleadoxturnoSetEmpleadoxturnoToAttach = em.getReference(empleadoxturnoSetEmpleadoxturnoToAttach.getClass(), empleadoxturnoSetEmpleadoxturnoToAttach.getEmpleadoxturnoPK());
                attachedEmpleadoxturnoSet.add(empleadoxturnoSetEmpleadoxturnoToAttach);
            }
            empleado.setEmpleadoxturnoSet(attachedEmpleadoxturnoSet);
            Set<Empleadoxturno> attachedEmpleadoxturnoSet1 = new HashSet<Empleadoxturno>();
            for (Empleadoxturno empleadoxturnoSet1EmpleadoxturnoToAttach : empleado.getEmpleadoxturnoSet1()) {
                empleadoxturnoSet1EmpleadoxturnoToAttach = em.getReference(empleadoxturnoSet1EmpleadoxturnoToAttach.getClass(), empleadoxturnoSet1EmpleadoxturnoToAttach.getEmpleadoxturnoPK());
                attachedEmpleadoxturnoSet1.add(empleadoxturnoSet1EmpleadoxturnoToAttach);
            }
            empleado.setEmpleadoxturnoSet1(attachedEmpleadoxturnoSet1);
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSet = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach : empleado.getEjecucionetapaproduccionSet()) {
                ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSet.add(ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach);
            }
            empleado.setEjecucionetapaproduccionSet(attachedEjecucionetapaproduccionSet);
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSet1 = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach : empleado.getEjecucionetapaproduccionSet1()) {
                ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSet1.add(ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach);
            }
            empleado.setEjecucionetapaproduccionSet1(attachedEjecucionetapaproduccionSet1);
            Set<Mantenimientocorrectivo> attachedMantenimientocorrectivoSet = new HashSet<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoSetMantenimientocorrectivoToAttach : empleado.getMantenimientocorrectivoSet()) {
                mantenimientocorrectivoSetMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoSetMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoSetMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoSet.add(mantenimientocorrectivoSetMantenimientocorrectivoToAttach);
            }
            empleado.setMantenimientocorrectivoSet(attachedMantenimientocorrectivoSet);
            Set<Mantenimientocorrectivo> attachedMantenimientocorrectivoSet1 = new HashSet<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoSet1MantenimientocorrectivoToAttach : empleado.getMantenimientocorrectivoSet1()) {
                mantenimientocorrectivoSet1MantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoSet1MantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoSet1MantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoSet1.add(mantenimientocorrectivoSet1MantenimientocorrectivoToAttach);
            }
            empleado.setMantenimientocorrectivoSet1(attachedMantenimientocorrectivoSet1);
            Set<Disponibilidadhoraria> attachedDisponibilidadhorariaSet = new HashSet<Disponibilidadhoraria>();
            for (Disponibilidadhoraria disponibilidadhorariaSetDisponibilidadhorariaToAttach : empleado.getDisponibilidadhorariaSet()) {
                disponibilidadhorariaSetDisponibilidadhorariaToAttach = em.getReference(disponibilidadhorariaSetDisponibilidadhorariaToAttach.getClass(), disponibilidadhorariaSetDisponibilidadhorariaToAttach.getId());
                attachedDisponibilidadhorariaSet.add(disponibilidadhorariaSetDisponibilidadhorariaToAttach);
            }
            empleado.setDisponibilidadhorariaSet(attachedDisponibilidadhorariaSet);
            Set<Disponibilidadhoraria> attachedDisponibilidadhorariaSet1 = new HashSet<Disponibilidadhoraria>();
            for (Disponibilidadhoraria disponibilidadhorariaSet1DisponibilidadhorariaToAttach : empleado.getDisponibilidadhorariaSet1()) {
                disponibilidadhorariaSet1DisponibilidadhorariaToAttach = em.getReference(disponibilidadhorariaSet1DisponibilidadhorariaToAttach.getClass(), disponibilidadhorariaSet1DisponibilidadhorariaToAttach.getId());
                attachedDisponibilidadhorariaSet1.add(disponibilidadhorariaSet1DisponibilidadhorariaToAttach);
            }
            empleado.setDisponibilidadhorariaSet1(attachedDisponibilidadhorariaSet1);
            Set<Asistencia> attachedAsistenciaSet = new HashSet<Asistencia>();
            for (Asistencia asistenciaSetAsistenciaToAttach : empleado.getAsistenciaSet()) {
                asistenciaSetAsistenciaToAttach = em.getReference(asistenciaSetAsistenciaToAttach.getClass(), asistenciaSetAsistenciaToAttach.getAsistenciaPK());
                attachedAsistenciaSet.add(asistenciaSetAsistenciaToAttach);
            }
            empleado.setAsistenciaSet(attachedAsistenciaSet);
            Set<Asistencia> attachedAsistenciaSet1 = new HashSet<Asistencia>();
            for (Asistencia asistenciaSet1AsistenciaToAttach : empleado.getAsistenciaSet1()) {
                asistenciaSet1AsistenciaToAttach = em.getReference(asistenciaSet1AsistenciaToAttach.getClass(), asistenciaSet1AsistenciaToAttach.getAsistenciaPK());
                attachedAsistenciaSet1.add(asistenciaSet1AsistenciaToAttach);
            }
            empleado.setAsistenciaSet1(attachedAsistenciaSet1);
            em.persist(empleado);
            if (cargo != null) {
                cargo.getEmpleadoSet().add(empleado);
                cargo = em.merge(cargo);
            }
            if (cargo1 != null) {
                cargo1.getEmpleadoSet().add(empleado);
                cargo1 = em.merge(cargo1);
            }
            if (categoria != null) {
                categoria.getEmpleadoSet().add(empleado);
                categoria = em.merge(categoria);
            }
            if (categoria1 != null) {
                categoria1.getEmpleadoSet().add(empleado);
                categoria1 = em.merge(categoria1);
            }
            if (domicilio != null) {
                domicilio.getEmpleadoSet().add(empleado);
                domicilio = em.merge(domicilio);
            }
            if (domicilio1 != null) {
                domicilio1.getEmpleadoSet().add(empleado);
                domicilio1 = em.merge(domicilio1);
            }
            if (tipodocumento != null) {
                tipodocumento.getEmpleadoSet().add(empleado);
                tipodocumento = em.merge(tipodocumento);
            }
            if (tipodocumento1 != null) {
                tipodocumento1.getEmpleadoSet().add(empleado);
                tipodocumento1 = em.merge(tipodocumento1);
            }
            if (usuario != null) {
                usuario.getEmpleadoSet().add(empleado);
                usuario = em.merge(usuario);
            }
            if (usuario1 != null) {
                usuario1.getEmpleadoSet().add(empleado);
                usuario1 = em.merge(usuario1);
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
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1Detalleplanificacionproduccion : empleado.getDetalleplanificacionproduccionSet1()) {
                Empleado oldIdempleado1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion = detalleplanificacionproduccionSet1Detalleplanificacionproduccion.getIdempleado1();
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion.setIdempleado1(empleado);
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                if (oldIdempleado1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion != null) {
                    oldIdempleado1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion.getDetalleplanificacionproduccionSet1().remove(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                    oldIdempleado1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(oldIdempleado1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                }
            }
            for (Empleadoxturno empleadoxturnoSetEmpleadoxturno : empleado.getEmpleadoxturnoSet()) {
                Empleado oldEmpleadoOfEmpleadoxturnoSetEmpleadoxturno = empleadoxturnoSetEmpleadoxturno.getEmpleado();
                empleadoxturnoSetEmpleadoxturno.setEmpleado(empleado);
                empleadoxturnoSetEmpleadoxturno = em.merge(empleadoxturnoSetEmpleadoxturno);
                if (oldEmpleadoOfEmpleadoxturnoSetEmpleadoxturno != null) {
                    oldEmpleadoOfEmpleadoxturnoSetEmpleadoxturno.getEmpleadoxturnoSet().remove(empleadoxturnoSetEmpleadoxturno);
                    oldEmpleadoOfEmpleadoxturnoSetEmpleadoxturno = em.merge(oldEmpleadoOfEmpleadoxturnoSetEmpleadoxturno);
                }
            }
            for (Empleadoxturno empleadoxturnoSet1Empleadoxturno : empleado.getEmpleadoxturnoSet1()) {
                Empleado oldEmpleado1OfEmpleadoxturnoSet1Empleadoxturno = empleadoxturnoSet1Empleadoxturno.getEmpleado1();
                empleadoxturnoSet1Empleadoxturno.setEmpleado1(empleado);
                empleadoxturnoSet1Empleadoxturno = em.merge(empleadoxturnoSet1Empleadoxturno);
                if (oldEmpleado1OfEmpleadoxturnoSet1Empleadoxturno != null) {
                    oldEmpleado1OfEmpleadoxturnoSet1Empleadoxturno.getEmpleadoxturnoSet1().remove(empleadoxturnoSet1Empleadoxturno);
                    oldEmpleado1OfEmpleadoxturnoSet1Empleadoxturno = em.merge(oldEmpleado1OfEmpleadoxturnoSet1Empleadoxturno);
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
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1Ejecucionetapaproduccion : empleado.getEjecucionetapaproduccionSet1()) {
                Empleado oldEmpleado1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion = ejecucionetapaproduccionSet1Ejecucionetapaproduccion.getEmpleado1();
                ejecucionetapaproduccionSet1Ejecucionetapaproduccion.setEmpleado1(empleado);
                ejecucionetapaproduccionSet1Ejecucionetapaproduccion = em.merge(ejecucionetapaproduccionSet1Ejecucionetapaproduccion);
                if (oldEmpleado1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion != null) {
                    oldEmpleado1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion.getEjecucionetapaproduccionSet1().remove(ejecucionetapaproduccionSet1Ejecucionetapaproduccion);
                    oldEmpleado1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion = em.merge(oldEmpleado1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion);
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
            for (Mantenimientocorrectivo mantenimientocorrectivoSet1Mantenimientocorrectivo : empleado.getMantenimientocorrectivoSet1()) {
                Empleado oldEmpleado1OfMantenimientocorrectivoSet1Mantenimientocorrectivo = mantenimientocorrectivoSet1Mantenimientocorrectivo.getEmpleado1();
                mantenimientocorrectivoSet1Mantenimientocorrectivo.setEmpleado1(empleado);
                mantenimientocorrectivoSet1Mantenimientocorrectivo = em.merge(mantenimientocorrectivoSet1Mantenimientocorrectivo);
                if (oldEmpleado1OfMantenimientocorrectivoSet1Mantenimientocorrectivo != null) {
                    oldEmpleado1OfMantenimientocorrectivoSet1Mantenimientocorrectivo.getMantenimientocorrectivoSet1().remove(mantenimientocorrectivoSet1Mantenimientocorrectivo);
                    oldEmpleado1OfMantenimientocorrectivoSet1Mantenimientocorrectivo = em.merge(oldEmpleado1OfMantenimientocorrectivoSet1Mantenimientocorrectivo);
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
            for (Disponibilidadhoraria disponibilidadhorariaSet1Disponibilidadhoraria : empleado.getDisponibilidadhorariaSet1()) {
                Empleado oldIdempleado1OfDisponibilidadhorariaSet1Disponibilidadhoraria = disponibilidadhorariaSet1Disponibilidadhoraria.getIdempleado1();
                disponibilidadhorariaSet1Disponibilidadhoraria.setIdempleado1(empleado);
                disponibilidadhorariaSet1Disponibilidadhoraria = em.merge(disponibilidadhorariaSet1Disponibilidadhoraria);
                if (oldIdempleado1OfDisponibilidadhorariaSet1Disponibilidadhoraria != null) {
                    oldIdempleado1OfDisponibilidadhorariaSet1Disponibilidadhoraria.getDisponibilidadhorariaSet1().remove(disponibilidadhorariaSet1Disponibilidadhoraria);
                    oldIdempleado1OfDisponibilidadhorariaSet1Disponibilidadhoraria = em.merge(oldIdempleado1OfDisponibilidadhorariaSet1Disponibilidadhoraria);
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
            for (Asistencia asistenciaSet1Asistencia : empleado.getAsistenciaSet1()) {
                Empleado oldEmpleado2OfAsistenciaSet1Asistencia = asistenciaSet1Asistencia.getEmpleado2();
                asistenciaSet1Asistencia.setEmpleado2(empleado);
                asistenciaSet1Asistencia = em.merge(asistenciaSet1Asistencia);
                if (oldEmpleado2OfAsistenciaSet1Asistencia != null) {
                    oldEmpleado2OfAsistenciaSet1Asistencia.getAsistenciaSet1().remove(asistenciaSet1Asistencia);
                    oldEmpleado2OfAsistenciaSet1Asistencia = em.merge(oldEmpleado2OfAsistenciaSet1Asistencia);
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
            Cargo cargo1Old = persistentEmpleado.getCargo1();
            Cargo cargo1New = empleado.getCargo1();
            Categoria categoriaOld = persistentEmpleado.getCategoria();
            Categoria categoriaNew = empleado.getCategoria();
            Categoria categoria1Old = persistentEmpleado.getCategoria1();
            Categoria categoria1New = empleado.getCategoria1();
            Domicilio domicilioOld = persistentEmpleado.getDomicilio();
            Domicilio domicilioNew = empleado.getDomicilio();
            Domicilio domicilio1Old = persistentEmpleado.getDomicilio1();
            Domicilio domicilio1New = empleado.getDomicilio1();
            Tipodocumento tipodocumentoOld = persistentEmpleado.getTipodocumento();
            Tipodocumento tipodocumentoNew = empleado.getTipodocumento();
            Tipodocumento tipodocumento1Old = persistentEmpleado.getTipodocumento1();
            Tipodocumento tipodocumento1New = empleado.getTipodocumento1();
            Usuario usuarioOld = persistentEmpleado.getUsuario();
            Usuario usuarioNew = empleado.getUsuario();
            Usuario usuario1Old = persistentEmpleado.getUsuario1();
            Usuario usuario1New = empleado.getUsuario1();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetOld = persistentEmpleado.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetNew = empleado.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1Old = persistentEmpleado.getDetalleplanificacionproduccionSet1();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1New = empleado.getDetalleplanificacionproduccionSet1();
            Set<Empleadoxturno> empleadoxturnoSetOld = persistentEmpleado.getEmpleadoxturnoSet();
            Set<Empleadoxturno> empleadoxturnoSetNew = empleado.getEmpleadoxturnoSet();
            Set<Empleadoxturno> empleadoxturnoSet1Old = persistentEmpleado.getEmpleadoxturnoSet1();
            Set<Empleadoxturno> empleadoxturnoSet1New = empleado.getEmpleadoxturnoSet1();
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSetOld = persistentEmpleado.getEjecucionetapaproduccionSet();
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSetNew = empleado.getEjecucionetapaproduccionSet();
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet1Old = persistentEmpleado.getEjecucionetapaproduccionSet1();
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet1New = empleado.getEjecucionetapaproduccionSet1();
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSetOld = persistentEmpleado.getMantenimientocorrectivoSet();
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSetNew = empleado.getMantenimientocorrectivoSet();
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSet1Old = persistentEmpleado.getMantenimientocorrectivoSet1();
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSet1New = empleado.getMantenimientocorrectivoSet1();
            Set<Disponibilidadhoraria> disponibilidadhorariaSetOld = persistentEmpleado.getDisponibilidadhorariaSet();
            Set<Disponibilidadhoraria> disponibilidadhorariaSetNew = empleado.getDisponibilidadhorariaSet();
            Set<Disponibilidadhoraria> disponibilidadhorariaSet1Old = persistentEmpleado.getDisponibilidadhorariaSet1();
            Set<Disponibilidadhoraria> disponibilidadhorariaSet1New = empleado.getDisponibilidadhorariaSet1();
            Set<Asistencia> asistenciaSetOld = persistentEmpleado.getAsistenciaSet();
            Set<Asistencia> asistenciaSetNew = empleado.getAsistenciaSet();
            Set<Asistencia> asistenciaSet1Old = persistentEmpleado.getAsistenciaSet1();
            Set<Asistencia> asistenciaSet1New = empleado.getAsistenciaSet1();
            List<String> illegalOrphanMessages = null;
            for (Empleadoxturno empleadoxturnoSetOldEmpleadoxturno : empleadoxturnoSetOld) {
                if (!empleadoxturnoSetNew.contains(empleadoxturnoSetOldEmpleadoxturno)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleadoxturno " + empleadoxturnoSetOldEmpleadoxturno + " since its empleado field is not nullable.");
                }
            }
            for (Empleadoxturno empleadoxturnoSet1OldEmpleadoxturno : empleadoxturnoSet1Old) {
                if (!empleadoxturnoSet1New.contains(empleadoxturnoSet1OldEmpleadoxturno)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleadoxturno " + empleadoxturnoSet1OldEmpleadoxturno + " since its empleado1 field is not nullable.");
                }
            }
            for (Asistencia asistenciaSetOldAsistencia : asistenciaSetOld) {
                if (!asistenciaSetNew.contains(asistenciaSetOldAsistencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asistencia " + asistenciaSetOldAsistencia + " since its empleado1 field is not nullable.");
                }
            }
            for (Asistencia asistenciaSet1OldAsistencia : asistenciaSet1Old) {
                if (!asistenciaSet1New.contains(asistenciaSet1OldAsistencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asistencia " + asistenciaSet1OldAsistencia + " since its empleado2 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cargoNew != null) {
                cargoNew = em.getReference(cargoNew.getClass(), cargoNew.getIdcargo());
                empleado.setCargo(cargoNew);
            }
            if (cargo1New != null) {
                cargo1New = em.getReference(cargo1New.getClass(), cargo1New.getIdcargo());
                empleado.setCargo1(cargo1New);
            }
            if (categoriaNew != null) {
                categoriaNew = em.getReference(categoriaNew.getClass(), categoriaNew.getIdcategoria());
                empleado.setCategoria(categoriaNew);
            }
            if (categoria1New != null) {
                categoria1New = em.getReference(categoria1New.getClass(), categoria1New.getIdcategoria());
                empleado.setCategoria1(categoria1New);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                empleado.setDomicilio(domicilioNew);
            }
            if (domicilio1New != null) {
                domicilio1New = em.getReference(domicilio1New.getClass(), domicilio1New.getIddomicilio());
                empleado.setDomicilio1(domicilio1New);
            }
            if (tipodocumentoNew != null) {
                tipodocumentoNew = em.getReference(tipodocumentoNew.getClass(), tipodocumentoNew.getIdtipodocumento());
                empleado.setTipodocumento(tipodocumentoNew);
            }
            if (tipodocumento1New != null) {
                tipodocumento1New = em.getReference(tipodocumento1New.getClass(), tipodocumento1New.getIdtipodocumento());
                empleado.setTipodocumento1(tipodocumento1New);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                empleado.setUsuario(usuarioNew);
            }
            if (usuario1New != null) {
                usuario1New = em.getReference(usuario1New.getClass(), usuario1New.getIdusuario());
                empleado.setUsuario1(usuario1New);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSetNew = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSetNew) {
                detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSetNew.add(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSetNew = attachedDetalleplanificacionproduccionSetNew;
            empleado.setDetalleplanificacionproduccionSet(detalleplanificacionproduccionSetNew);
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet1New = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSet1New) {
                detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet1New.add(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSet1New = attachedDetalleplanificacionproduccionSet1New;
            empleado.setDetalleplanificacionproduccionSet1(detalleplanificacionproduccionSet1New);
            Set<Empleadoxturno> attachedEmpleadoxturnoSetNew = new HashSet<Empleadoxturno>();
            for (Empleadoxturno empleadoxturnoSetNewEmpleadoxturnoToAttach : empleadoxturnoSetNew) {
                empleadoxturnoSetNewEmpleadoxturnoToAttach = em.getReference(empleadoxturnoSetNewEmpleadoxturnoToAttach.getClass(), empleadoxturnoSetNewEmpleadoxturnoToAttach.getEmpleadoxturnoPK());
                attachedEmpleadoxturnoSetNew.add(empleadoxturnoSetNewEmpleadoxturnoToAttach);
            }
            empleadoxturnoSetNew = attachedEmpleadoxturnoSetNew;
            empleado.setEmpleadoxturnoSet(empleadoxturnoSetNew);
            Set<Empleadoxturno> attachedEmpleadoxturnoSet1New = new HashSet<Empleadoxturno>();
            for (Empleadoxturno empleadoxturnoSet1NewEmpleadoxturnoToAttach : empleadoxturnoSet1New) {
                empleadoxturnoSet1NewEmpleadoxturnoToAttach = em.getReference(empleadoxturnoSet1NewEmpleadoxturnoToAttach.getClass(), empleadoxturnoSet1NewEmpleadoxturnoToAttach.getEmpleadoxturnoPK());
                attachedEmpleadoxturnoSet1New.add(empleadoxturnoSet1NewEmpleadoxturnoToAttach);
            }
            empleadoxturnoSet1New = attachedEmpleadoxturnoSet1New;
            empleado.setEmpleadoxturnoSet1(empleadoxturnoSet1New);
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSetNew = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach : ejecucionetapaproduccionSetNew) {
                ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSetNew.add(ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach);
            }
            ejecucionetapaproduccionSetNew = attachedEjecucionetapaproduccionSetNew;
            empleado.setEjecucionetapaproduccionSet(ejecucionetapaproduccionSetNew);
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSet1New = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach : ejecucionetapaproduccionSet1New) {
                ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSet1New.add(ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach);
            }
            ejecucionetapaproduccionSet1New = attachedEjecucionetapaproduccionSet1New;
            empleado.setEjecucionetapaproduccionSet1(ejecucionetapaproduccionSet1New);
            Set<Mantenimientocorrectivo> attachedMantenimientocorrectivoSetNew = new HashSet<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach : mantenimientocorrectivoSetNew) {
                mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoSetNew.add(mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach);
            }
            mantenimientocorrectivoSetNew = attachedMantenimientocorrectivoSetNew;
            empleado.setMantenimientocorrectivoSet(mantenimientocorrectivoSetNew);
            Set<Mantenimientocorrectivo> attachedMantenimientocorrectivoSet1New = new HashSet<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoSet1NewMantenimientocorrectivoToAttach : mantenimientocorrectivoSet1New) {
                mantenimientocorrectivoSet1NewMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoSet1NewMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoSet1NewMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoSet1New.add(mantenimientocorrectivoSet1NewMantenimientocorrectivoToAttach);
            }
            mantenimientocorrectivoSet1New = attachedMantenimientocorrectivoSet1New;
            empleado.setMantenimientocorrectivoSet1(mantenimientocorrectivoSet1New);
            Set<Disponibilidadhoraria> attachedDisponibilidadhorariaSetNew = new HashSet<Disponibilidadhoraria>();
            for (Disponibilidadhoraria disponibilidadhorariaSetNewDisponibilidadhorariaToAttach : disponibilidadhorariaSetNew) {
                disponibilidadhorariaSetNewDisponibilidadhorariaToAttach = em.getReference(disponibilidadhorariaSetNewDisponibilidadhorariaToAttach.getClass(), disponibilidadhorariaSetNewDisponibilidadhorariaToAttach.getId());
                attachedDisponibilidadhorariaSetNew.add(disponibilidadhorariaSetNewDisponibilidadhorariaToAttach);
            }
            disponibilidadhorariaSetNew = attachedDisponibilidadhorariaSetNew;
            empleado.setDisponibilidadhorariaSet(disponibilidadhorariaSetNew);
            Set<Disponibilidadhoraria> attachedDisponibilidadhorariaSet1New = new HashSet<Disponibilidadhoraria>();
            for (Disponibilidadhoraria disponibilidadhorariaSet1NewDisponibilidadhorariaToAttach : disponibilidadhorariaSet1New) {
                disponibilidadhorariaSet1NewDisponibilidadhorariaToAttach = em.getReference(disponibilidadhorariaSet1NewDisponibilidadhorariaToAttach.getClass(), disponibilidadhorariaSet1NewDisponibilidadhorariaToAttach.getId());
                attachedDisponibilidadhorariaSet1New.add(disponibilidadhorariaSet1NewDisponibilidadhorariaToAttach);
            }
            disponibilidadhorariaSet1New = attachedDisponibilidadhorariaSet1New;
            empleado.setDisponibilidadhorariaSet1(disponibilidadhorariaSet1New);
            Set<Asistencia> attachedAsistenciaSetNew = new HashSet<Asistencia>();
            for (Asistencia asistenciaSetNewAsistenciaToAttach : asistenciaSetNew) {
                asistenciaSetNewAsistenciaToAttach = em.getReference(asistenciaSetNewAsistenciaToAttach.getClass(), asistenciaSetNewAsistenciaToAttach.getAsistenciaPK());
                attachedAsistenciaSetNew.add(asistenciaSetNewAsistenciaToAttach);
            }
            asistenciaSetNew = attachedAsistenciaSetNew;
            empleado.setAsistenciaSet(asistenciaSetNew);
            Set<Asistencia> attachedAsistenciaSet1New = new HashSet<Asistencia>();
            for (Asistencia asistenciaSet1NewAsistenciaToAttach : asistenciaSet1New) {
                asistenciaSet1NewAsistenciaToAttach = em.getReference(asistenciaSet1NewAsistenciaToAttach.getClass(), asistenciaSet1NewAsistenciaToAttach.getAsistenciaPK());
                attachedAsistenciaSet1New.add(asistenciaSet1NewAsistenciaToAttach);
            }
            asistenciaSet1New = attachedAsistenciaSet1New;
            empleado.setAsistenciaSet1(asistenciaSet1New);
            empleado = em.merge(empleado);
            if (cargoOld != null && !cargoOld.equals(cargoNew)) {
                cargoOld.getEmpleadoSet().remove(empleado);
                cargoOld = em.merge(cargoOld);
            }
            if (cargoNew != null && !cargoNew.equals(cargoOld)) {
                cargoNew.getEmpleadoSet().add(empleado);
                cargoNew = em.merge(cargoNew);
            }
            if (cargo1Old != null && !cargo1Old.equals(cargo1New)) {
                cargo1Old.getEmpleadoSet().remove(empleado);
                cargo1Old = em.merge(cargo1Old);
            }
            if (cargo1New != null && !cargo1New.equals(cargo1Old)) {
                cargo1New.getEmpleadoSet().add(empleado);
                cargo1New = em.merge(cargo1New);
            }
            if (categoriaOld != null && !categoriaOld.equals(categoriaNew)) {
                categoriaOld.getEmpleadoSet().remove(empleado);
                categoriaOld = em.merge(categoriaOld);
            }
            if (categoriaNew != null && !categoriaNew.equals(categoriaOld)) {
                categoriaNew.getEmpleadoSet().add(empleado);
                categoriaNew = em.merge(categoriaNew);
            }
            if (categoria1Old != null && !categoria1Old.equals(categoria1New)) {
                categoria1Old.getEmpleadoSet().remove(empleado);
                categoria1Old = em.merge(categoria1Old);
            }
            if (categoria1New != null && !categoria1New.equals(categoria1Old)) {
                categoria1New.getEmpleadoSet().add(empleado);
                categoria1New = em.merge(categoria1New);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getEmpleadoSet().remove(empleado);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getEmpleadoSet().add(empleado);
                domicilioNew = em.merge(domicilioNew);
            }
            if (domicilio1Old != null && !domicilio1Old.equals(domicilio1New)) {
                domicilio1Old.getEmpleadoSet().remove(empleado);
                domicilio1Old = em.merge(domicilio1Old);
            }
            if (domicilio1New != null && !domicilio1New.equals(domicilio1Old)) {
                domicilio1New.getEmpleadoSet().add(empleado);
                domicilio1New = em.merge(domicilio1New);
            }
            if (tipodocumentoOld != null && !tipodocumentoOld.equals(tipodocumentoNew)) {
                tipodocumentoOld.getEmpleadoSet().remove(empleado);
                tipodocumentoOld = em.merge(tipodocumentoOld);
            }
            if (tipodocumentoNew != null && !tipodocumentoNew.equals(tipodocumentoOld)) {
                tipodocumentoNew.getEmpleadoSet().add(empleado);
                tipodocumentoNew = em.merge(tipodocumentoNew);
            }
            if (tipodocumento1Old != null && !tipodocumento1Old.equals(tipodocumento1New)) {
                tipodocumento1Old.getEmpleadoSet().remove(empleado);
                tipodocumento1Old = em.merge(tipodocumento1Old);
            }
            if (tipodocumento1New != null && !tipodocumento1New.equals(tipodocumento1Old)) {
                tipodocumento1New.getEmpleadoSet().add(empleado);
                tipodocumento1New = em.merge(tipodocumento1New);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getEmpleadoSet().remove(empleado);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getEmpleadoSet().add(empleado);
                usuarioNew = em.merge(usuarioNew);
            }
            if (usuario1Old != null && !usuario1Old.equals(usuario1New)) {
                usuario1Old.getEmpleadoSet().remove(empleado);
                usuario1Old = em.merge(usuario1Old);
            }
            if (usuario1New != null && !usuario1New.equals(usuario1Old)) {
                usuario1New.getEmpleadoSet().add(empleado);
                usuario1New = em.merge(usuario1New);
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
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion : detalleplanificacionproduccionSet1Old) {
                if (!detalleplanificacionproduccionSet1New.contains(detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion.setIdempleado1(null);
                    detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion : detalleplanificacionproduccionSet1New) {
                if (!detalleplanificacionproduccionSet1Old.contains(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion)) {
                    Empleado oldIdempleado1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.getIdempleado1();
                    detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.setIdempleado1(empleado);
                    detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                    if (oldIdempleado1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion != null && !oldIdempleado1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.equals(empleado)) {
                        oldIdempleado1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet1().remove(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                        oldIdempleado1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = em.merge(oldIdempleado1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Empleadoxturno empleadoxturnoSetNewEmpleadoxturno : empleadoxturnoSetNew) {
                if (!empleadoxturnoSetOld.contains(empleadoxturnoSetNewEmpleadoxturno)) {
                    Empleado oldEmpleadoOfEmpleadoxturnoSetNewEmpleadoxturno = empleadoxturnoSetNewEmpleadoxturno.getEmpleado();
                    empleadoxturnoSetNewEmpleadoxturno.setEmpleado(empleado);
                    empleadoxturnoSetNewEmpleadoxturno = em.merge(empleadoxturnoSetNewEmpleadoxturno);
                    if (oldEmpleadoOfEmpleadoxturnoSetNewEmpleadoxturno != null && !oldEmpleadoOfEmpleadoxturnoSetNewEmpleadoxturno.equals(empleado)) {
                        oldEmpleadoOfEmpleadoxturnoSetNewEmpleadoxturno.getEmpleadoxturnoSet().remove(empleadoxturnoSetNewEmpleadoxturno);
                        oldEmpleadoOfEmpleadoxturnoSetNewEmpleadoxturno = em.merge(oldEmpleadoOfEmpleadoxturnoSetNewEmpleadoxturno);
                    }
                }
            }
            for (Empleadoxturno empleadoxturnoSet1NewEmpleadoxturno : empleadoxturnoSet1New) {
                if (!empleadoxturnoSet1Old.contains(empleadoxturnoSet1NewEmpleadoxturno)) {
                    Empleado oldEmpleado1OfEmpleadoxturnoSet1NewEmpleadoxturno = empleadoxturnoSet1NewEmpleadoxturno.getEmpleado1();
                    empleadoxturnoSet1NewEmpleadoxturno.setEmpleado1(empleado);
                    empleadoxturnoSet1NewEmpleadoxturno = em.merge(empleadoxturnoSet1NewEmpleadoxturno);
                    if (oldEmpleado1OfEmpleadoxturnoSet1NewEmpleadoxturno != null && !oldEmpleado1OfEmpleadoxturnoSet1NewEmpleadoxturno.equals(empleado)) {
                        oldEmpleado1OfEmpleadoxturnoSet1NewEmpleadoxturno.getEmpleadoxturnoSet1().remove(empleadoxturnoSet1NewEmpleadoxturno);
                        oldEmpleado1OfEmpleadoxturnoSet1NewEmpleadoxturno = em.merge(oldEmpleado1OfEmpleadoxturnoSet1NewEmpleadoxturno);
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
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1OldEjecucionetapaproduccion : ejecucionetapaproduccionSet1Old) {
                if (!ejecucionetapaproduccionSet1New.contains(ejecucionetapaproduccionSet1OldEjecucionetapaproduccion)) {
                    ejecucionetapaproduccionSet1OldEjecucionetapaproduccion.setEmpleado1(null);
                    ejecucionetapaproduccionSet1OldEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSet1OldEjecucionetapaproduccion);
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1NewEjecucionetapaproduccion : ejecucionetapaproduccionSet1New) {
                if (!ejecucionetapaproduccionSet1Old.contains(ejecucionetapaproduccionSet1NewEjecucionetapaproduccion)) {
                    Empleado oldEmpleado1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion = ejecucionetapaproduccionSet1NewEjecucionetapaproduccion.getEmpleado1();
                    ejecucionetapaproduccionSet1NewEjecucionetapaproduccion.setEmpleado1(empleado);
                    ejecucionetapaproduccionSet1NewEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSet1NewEjecucionetapaproduccion);
                    if (oldEmpleado1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion != null && !oldEmpleado1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion.equals(empleado)) {
                        oldEmpleado1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion.getEjecucionetapaproduccionSet1().remove(ejecucionetapaproduccionSet1NewEjecucionetapaproduccion);
                        oldEmpleado1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion = em.merge(oldEmpleado1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion);
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
            for (Mantenimientocorrectivo mantenimientocorrectivoSet1OldMantenimientocorrectivo : mantenimientocorrectivoSet1Old) {
                if (!mantenimientocorrectivoSet1New.contains(mantenimientocorrectivoSet1OldMantenimientocorrectivo)) {
                    mantenimientocorrectivoSet1OldMantenimientocorrectivo.setEmpleado1(null);
                    mantenimientocorrectivoSet1OldMantenimientocorrectivo = em.merge(mantenimientocorrectivoSet1OldMantenimientocorrectivo);
                }
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoSet1NewMantenimientocorrectivo : mantenimientocorrectivoSet1New) {
                if (!mantenimientocorrectivoSet1Old.contains(mantenimientocorrectivoSet1NewMantenimientocorrectivo)) {
                    Empleado oldEmpleado1OfMantenimientocorrectivoSet1NewMantenimientocorrectivo = mantenimientocorrectivoSet1NewMantenimientocorrectivo.getEmpleado1();
                    mantenimientocorrectivoSet1NewMantenimientocorrectivo.setEmpleado1(empleado);
                    mantenimientocorrectivoSet1NewMantenimientocorrectivo = em.merge(mantenimientocorrectivoSet1NewMantenimientocorrectivo);
                    if (oldEmpleado1OfMantenimientocorrectivoSet1NewMantenimientocorrectivo != null && !oldEmpleado1OfMantenimientocorrectivoSet1NewMantenimientocorrectivo.equals(empleado)) {
                        oldEmpleado1OfMantenimientocorrectivoSet1NewMantenimientocorrectivo.getMantenimientocorrectivoSet1().remove(mantenimientocorrectivoSet1NewMantenimientocorrectivo);
                        oldEmpleado1OfMantenimientocorrectivoSet1NewMantenimientocorrectivo = em.merge(oldEmpleado1OfMantenimientocorrectivoSet1NewMantenimientocorrectivo);
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
            for (Disponibilidadhoraria disponibilidadhorariaSet1OldDisponibilidadhoraria : disponibilidadhorariaSet1Old) {
                if (!disponibilidadhorariaSet1New.contains(disponibilidadhorariaSet1OldDisponibilidadhoraria)) {
                    disponibilidadhorariaSet1OldDisponibilidadhoraria.setIdempleado1(null);
                    disponibilidadhorariaSet1OldDisponibilidadhoraria = em.merge(disponibilidadhorariaSet1OldDisponibilidadhoraria);
                }
            }
            for (Disponibilidadhoraria disponibilidadhorariaSet1NewDisponibilidadhoraria : disponibilidadhorariaSet1New) {
                if (!disponibilidadhorariaSet1Old.contains(disponibilidadhorariaSet1NewDisponibilidadhoraria)) {
                    Empleado oldIdempleado1OfDisponibilidadhorariaSet1NewDisponibilidadhoraria = disponibilidadhorariaSet1NewDisponibilidadhoraria.getIdempleado1();
                    disponibilidadhorariaSet1NewDisponibilidadhoraria.setIdempleado1(empleado);
                    disponibilidadhorariaSet1NewDisponibilidadhoraria = em.merge(disponibilidadhorariaSet1NewDisponibilidadhoraria);
                    if (oldIdempleado1OfDisponibilidadhorariaSet1NewDisponibilidadhoraria != null && !oldIdempleado1OfDisponibilidadhorariaSet1NewDisponibilidadhoraria.equals(empleado)) {
                        oldIdempleado1OfDisponibilidadhorariaSet1NewDisponibilidadhoraria.getDisponibilidadhorariaSet1().remove(disponibilidadhorariaSet1NewDisponibilidadhoraria);
                        oldIdempleado1OfDisponibilidadhorariaSet1NewDisponibilidadhoraria = em.merge(oldIdempleado1OfDisponibilidadhorariaSet1NewDisponibilidadhoraria);
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
            for (Asistencia asistenciaSet1NewAsistencia : asistenciaSet1New) {
                if (!asistenciaSet1Old.contains(asistenciaSet1NewAsistencia)) {
                    Empleado oldEmpleado2OfAsistenciaSet1NewAsistencia = asistenciaSet1NewAsistencia.getEmpleado2();
                    asistenciaSet1NewAsistencia.setEmpleado2(empleado);
                    asistenciaSet1NewAsistencia = em.merge(asistenciaSet1NewAsistencia);
                    if (oldEmpleado2OfAsistenciaSet1NewAsistencia != null && !oldEmpleado2OfAsistenciaSet1NewAsistencia.equals(empleado)) {
                        oldEmpleado2OfAsistenciaSet1NewAsistencia.getAsistenciaSet1().remove(asistenciaSet1NewAsistencia);
                        oldEmpleado2OfAsistenciaSet1NewAsistencia = em.merge(oldEmpleado2OfAsistenciaSet1NewAsistencia);
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
            Set<Empleadoxturno> empleadoxturnoSetOrphanCheck = empleado.getEmpleadoxturnoSet();
            for (Empleadoxturno empleadoxturnoSetOrphanCheckEmpleadoxturno : empleadoxturnoSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Empleadoxturno " + empleadoxturnoSetOrphanCheckEmpleadoxturno + " in its empleadoxturnoSet field has a non-nullable empleado field.");
            }
            Set<Empleadoxturno> empleadoxturnoSet1OrphanCheck = empleado.getEmpleadoxturnoSet1();
            for (Empleadoxturno empleadoxturnoSet1OrphanCheckEmpleadoxturno : empleadoxturnoSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Empleadoxturno " + empleadoxturnoSet1OrphanCheckEmpleadoxturno + " in its empleadoxturnoSet1 field has a non-nullable empleado1 field.");
            }
            Set<Asistencia> asistenciaSetOrphanCheck = empleado.getAsistenciaSet();
            for (Asistencia asistenciaSetOrphanCheckAsistencia : asistenciaSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Asistencia " + asistenciaSetOrphanCheckAsistencia + " in its asistenciaSet field has a non-nullable empleado1 field.");
            }
            Set<Asistencia> asistenciaSet1OrphanCheck = empleado.getAsistenciaSet1();
            for (Asistencia asistenciaSet1OrphanCheckAsistencia : asistenciaSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Asistencia " + asistenciaSet1OrphanCheckAsistencia + " in its asistenciaSet1 field has a non-nullable empleado2 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cargo cargo = empleado.getCargo();
            if (cargo != null) {
                cargo.getEmpleadoSet().remove(empleado);
                cargo = em.merge(cargo);
            }
            Cargo cargo1 = empleado.getCargo1();
            if (cargo1 != null) {
                cargo1.getEmpleadoSet().remove(empleado);
                cargo1 = em.merge(cargo1);
            }
            Categoria categoria = empleado.getCategoria();
            if (categoria != null) {
                categoria.getEmpleadoSet().remove(empleado);
                categoria = em.merge(categoria);
            }
            Categoria categoria1 = empleado.getCategoria1();
            if (categoria1 != null) {
                categoria1.getEmpleadoSet().remove(empleado);
                categoria1 = em.merge(categoria1);
            }
            Domicilio domicilio = empleado.getDomicilio();
            if (domicilio != null) {
                domicilio.getEmpleadoSet().remove(empleado);
                domicilio = em.merge(domicilio);
            }
            Domicilio domicilio1 = empleado.getDomicilio1();
            if (domicilio1 != null) {
                domicilio1.getEmpleadoSet().remove(empleado);
                domicilio1 = em.merge(domicilio1);
            }
            Tipodocumento tipodocumento = empleado.getTipodocumento();
            if (tipodocumento != null) {
                tipodocumento.getEmpleadoSet().remove(empleado);
                tipodocumento = em.merge(tipodocumento);
            }
            Tipodocumento tipodocumento1 = empleado.getTipodocumento1();
            if (tipodocumento1 != null) {
                tipodocumento1.getEmpleadoSet().remove(empleado);
                tipodocumento1 = em.merge(tipodocumento1);
            }
            Usuario usuario = empleado.getUsuario();
            if (usuario != null) {
                usuario.getEmpleadoSet().remove(empleado);
                usuario = em.merge(usuario);
            }
            Usuario usuario1 = empleado.getUsuario1();
            if (usuario1 != null) {
                usuario1.getEmpleadoSet().remove(empleado);
                usuario1 = em.merge(usuario1);
            }
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet = empleado.getDetalleplanificacionproduccionSet();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : detalleplanificacionproduccionSet) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdempleado(null);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
            }
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1 = empleado.getDetalleplanificacionproduccionSet1();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1Detalleplanificacionproduccion : detalleplanificacionproduccionSet1) {
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion.setIdempleado1(null);
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
            }
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet = empleado.getEjecucionetapaproduccionSet();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetEjecucionetapaproduccion : ejecucionetapaproduccionSet) {
                ejecucionetapaproduccionSetEjecucionetapaproduccion.setEmpleado(null);
                ejecucionetapaproduccionSetEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSetEjecucionetapaproduccion);
            }
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet1 = empleado.getEjecucionetapaproduccionSet1();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1Ejecucionetapaproduccion : ejecucionetapaproduccionSet1) {
                ejecucionetapaproduccionSet1Ejecucionetapaproduccion.setEmpleado1(null);
                ejecucionetapaproduccionSet1Ejecucionetapaproduccion = em.merge(ejecucionetapaproduccionSet1Ejecucionetapaproduccion);
            }
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSet = empleado.getMantenimientocorrectivoSet();
            for (Mantenimientocorrectivo mantenimientocorrectivoSetMantenimientocorrectivo : mantenimientocorrectivoSet) {
                mantenimientocorrectivoSetMantenimientocorrectivo.setEmpleado(null);
                mantenimientocorrectivoSetMantenimientocorrectivo = em.merge(mantenimientocorrectivoSetMantenimientocorrectivo);
            }
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSet1 = empleado.getMantenimientocorrectivoSet1();
            for (Mantenimientocorrectivo mantenimientocorrectivoSet1Mantenimientocorrectivo : mantenimientocorrectivoSet1) {
                mantenimientocorrectivoSet1Mantenimientocorrectivo.setEmpleado1(null);
                mantenimientocorrectivoSet1Mantenimientocorrectivo = em.merge(mantenimientocorrectivoSet1Mantenimientocorrectivo);
            }
            Set<Disponibilidadhoraria> disponibilidadhorariaSet = empleado.getDisponibilidadhorariaSet();
            for (Disponibilidadhoraria disponibilidadhorariaSetDisponibilidadhoraria : disponibilidadhorariaSet) {
                disponibilidadhorariaSetDisponibilidadhoraria.setIdempleado(null);
                disponibilidadhorariaSetDisponibilidadhoraria = em.merge(disponibilidadhorariaSetDisponibilidadhoraria);
            }
            Set<Disponibilidadhoraria> disponibilidadhorariaSet1 = empleado.getDisponibilidadhorariaSet1();
            for (Disponibilidadhoraria disponibilidadhorariaSet1Disponibilidadhoraria : disponibilidadhorariaSet1) {
                disponibilidadhorariaSet1Disponibilidadhoraria.setIdempleado1(null);
                disponibilidadhorariaSet1Disponibilidadhoraria = em.merge(disponibilidadhorariaSet1Disponibilidadhoraria);
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
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
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
