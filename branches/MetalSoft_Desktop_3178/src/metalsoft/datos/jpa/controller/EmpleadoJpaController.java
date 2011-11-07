/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.entity.Empleado;
import metalsoft.datos.jpa.entity.Usuario;
import metalsoft.datos.jpa.entity.Tipodocumento;
import metalsoft.datos.jpa.entity.Domicilio;
import metalsoft.datos.jpa.entity.Categoria;
import metalsoft.datos.jpa.entity.Cargo;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Empleadoxturno;
import metalsoft.datos.jpa.entity.Ejecucionetapaproduccion;
import metalsoft.datos.jpa.entity.Mantenimientocorrectivo;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Disponibilidadhoraria;
import metalsoft.datos.jpa.entity.Asistencia;

/**
 *
 * @author Nino
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getDetalleplanificacionproduccionList() == null) {
            empleado.setDetalleplanificacionproduccionList(new ArrayList<Detalleplanificacionproduccion>());
        }
        if (empleado.getEmpleadoxturnoList() == null) {
            empleado.setEmpleadoxturnoList(new ArrayList<Empleadoxturno>());
        }
        if (empleado.getEjecucionetapaproduccionList() == null) {
            empleado.setEjecucionetapaproduccionList(new ArrayList<Ejecucionetapaproduccion>());
        }
        if (empleado.getMantenimientocorrectivoList() == null) {
            empleado.setMantenimientocorrectivoList(new ArrayList<Mantenimientocorrectivo>());
        }
        if (empleado.getDetalleplanificacioncalidadList() == null) {
            empleado.setDetalleplanificacioncalidadList(new ArrayList<Detalleplanificacioncalidad>());
        }
        if (empleado.getDisponibilidadhorariaList() == null) {
            empleado.setDisponibilidadhorariaList(new ArrayList<Disponibilidadhoraria>());
        }
        if (empleado.getAsistenciaList() == null) {
            empleado.setAsistenciaList(new ArrayList<Asistencia>());
        }
        if (empleado.getEjecucionprocesocalidadList() == null) {
            empleado.setEjecucionprocesocalidadList(new ArrayList<Ejecucionetapaproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = empleado.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                empleado.setUsuario(usuario);
            }
            Tipodocumento tipodocumento = empleado.getTipodocumento();
            if (tipodocumento != null) {
                tipodocumento = em.getReference(tipodocumento.getClass(), tipodocumento.getIdtipodocumento());
                empleado.setTipodocumento(tipodocumento);
            }
            Domicilio domicilio = empleado.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                empleado.setDomicilio(domicilio);
            }
            Categoria categoria = empleado.getCategoria();
            if (categoria != null) {
                categoria = em.getReference(categoria.getClass(), categoria.getIdcategoria());
                empleado.setCategoria(categoria);
            }
            Cargo cargo = empleado.getCargo();
            if (cargo != null) {
                cargo = em.getReference(cargo.getClass(), cargo.getIdcargo());
                empleado.setCargo(cargo);
            }
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionList = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach : empleado.getDetalleplanificacionproduccionList()) {
                detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionList.add(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach);
            }
            empleado.setDetalleplanificacionproduccionList(attachedDetalleplanificacionproduccionList);
            List<Empleadoxturno> attachedEmpleadoxturnoList = new ArrayList<Empleadoxturno>();
            for (Empleadoxturno empleadoxturnoListEmpleadoxturnoToAttach : empleado.getEmpleadoxturnoList()) {
                empleadoxturnoListEmpleadoxturnoToAttach = em.getReference(empleadoxturnoListEmpleadoxturnoToAttach.getClass(), empleadoxturnoListEmpleadoxturnoToAttach.getEmpleadoxturnoPK());
                attachedEmpleadoxturnoList.add(empleadoxturnoListEmpleadoxturnoToAttach);
            }
            empleado.setEmpleadoxturnoList(attachedEmpleadoxturnoList);
            List<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionList = new ArrayList<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListEjecucionetapaproduccionToAttach : empleado.getEjecucionetapaproduccionList()) {
                ejecucionetapaproduccionListEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionListEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionListEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionList.add(ejecucionetapaproduccionListEjecucionetapaproduccionToAttach);
            }
            empleado.setEjecucionetapaproduccionList(attachedEjecucionetapaproduccionList);
            List<Mantenimientocorrectivo> attachedMantenimientocorrectivoList = new ArrayList<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoListMantenimientocorrectivoToAttach : empleado.getMantenimientocorrectivoList()) {
                mantenimientocorrectivoListMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoListMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoListMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoList.add(mantenimientocorrectivoListMantenimientocorrectivoToAttach);
            }
            empleado.setMantenimientocorrectivoList(attachedMantenimientocorrectivoList);
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadList = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach : empleado.getDetalleplanificacioncalidadList()) {
                detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleplanificacioncalidadList.add(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach);
            }
            empleado.setDetalleplanificacioncalidadList(attachedDetalleplanificacioncalidadList);
            List<Disponibilidadhoraria> attachedDisponibilidadhorariaList = new ArrayList<Disponibilidadhoraria>();
            for (Disponibilidadhoraria disponibilidadhorariaListDisponibilidadhorariaToAttach : empleado.getDisponibilidadhorariaList()) {
                disponibilidadhorariaListDisponibilidadhorariaToAttach = em.getReference(disponibilidadhorariaListDisponibilidadhorariaToAttach.getClass(), disponibilidadhorariaListDisponibilidadhorariaToAttach.getId());
                attachedDisponibilidadhorariaList.add(disponibilidadhorariaListDisponibilidadhorariaToAttach);
            }
            empleado.setDisponibilidadhorariaList(attachedDisponibilidadhorariaList);
            List<Asistencia> attachedAsistenciaList = new ArrayList<Asistencia>();
            for (Asistencia asistenciaListAsistenciaToAttach : empleado.getAsistenciaList()) {
                asistenciaListAsistenciaToAttach = em.getReference(asistenciaListAsistenciaToAttach.getClass(), asistenciaListAsistenciaToAttach.getAsistenciaPK());
                attachedAsistenciaList.add(asistenciaListAsistenciaToAttach);
            }
            empleado.setAsistenciaList(attachedAsistenciaList);
            List<Ejecucionetapaproduccion> attachedEjecucionprocesocalidadList = new ArrayList<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionprocesocalidadListEjecucionetapaproduccionToAttach : empleado.getEjecucionprocesocalidadList()) {
                ejecucionprocesocalidadListEjecucionetapaproduccionToAttach = em.getReference(ejecucionprocesocalidadListEjecucionetapaproduccionToAttach.getClass(), ejecucionprocesocalidadListEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionprocesocalidadList.add(ejecucionprocesocalidadListEjecucionetapaproduccionToAttach);
            }
            empleado.setEjecucionprocesocalidadList(attachedEjecucionprocesocalidadList);
            em.persist(empleado);
            if (usuario != null) {
                usuario.getEmpleadoList().add(empleado);
                usuario = em.merge(usuario);
            }
            if (tipodocumento != null) {
                tipodocumento.getEmpleadoList().add(empleado);
                tipodocumento = em.merge(tipodocumento);
            }
            if (domicilio != null) {
                domicilio.getEmpleadoList().add(empleado);
                domicilio = em.merge(domicilio);
            }
            if (categoria != null) {
                categoria.getEmpleadoList().add(empleado);
                categoria = em.merge(categoria);
            }
            if (cargo != null) {
                cargo.getEmpleadoList().add(empleado);
                cargo = em.merge(cargo);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : empleado.getDetalleplanificacionproduccionList()) {
                Empleado oldIdempleadoOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = detalleplanificacionproduccionListDetalleplanificacionproduccion.getIdempleado();
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIdempleado(empleado);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                if (oldIdempleadoOfDetalleplanificacionproduccionListDetalleplanificacionproduccion != null) {
                    oldIdempleadoOfDetalleplanificacionproduccionListDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                    oldIdempleadoOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(oldIdempleadoOfDetalleplanificacionproduccionListDetalleplanificacionproduccion);
                }
            }
            for (Empleadoxturno empleadoxturnoListEmpleadoxturno : empleado.getEmpleadoxturnoList()) {
                Empleado oldEmpleadoOfEmpleadoxturnoListEmpleadoxturno = empleadoxturnoListEmpleadoxturno.getEmpleado();
                empleadoxturnoListEmpleadoxturno.setEmpleado(empleado);
                empleadoxturnoListEmpleadoxturno = em.merge(empleadoxturnoListEmpleadoxturno);
                if (oldEmpleadoOfEmpleadoxturnoListEmpleadoxturno != null) {
                    oldEmpleadoOfEmpleadoxturnoListEmpleadoxturno.getEmpleadoxturnoList().remove(empleadoxturnoListEmpleadoxturno);
                    oldEmpleadoOfEmpleadoxturnoListEmpleadoxturno = em.merge(oldEmpleadoOfEmpleadoxturnoListEmpleadoxturno);
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListEjecucionetapaproduccion : empleado.getEjecucionetapaproduccionList()) {
                Empleado oldEmpleadoOfEjecucionetapaproduccionListEjecucionetapaproduccion = ejecucionetapaproduccionListEjecucionetapaproduccion.getEmpleado();
                ejecucionetapaproduccionListEjecucionetapaproduccion.setEmpleado(empleado);
                ejecucionetapaproduccionListEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionListEjecucionetapaproduccion);
                if (oldEmpleadoOfEjecucionetapaproduccionListEjecucionetapaproduccion != null) {
                    oldEmpleadoOfEjecucionetapaproduccionListEjecucionetapaproduccion.getEjecucionetapaproduccionList().remove(ejecucionetapaproduccionListEjecucionetapaproduccion);
                    oldEmpleadoOfEjecucionetapaproduccionListEjecucionetapaproduccion = em.merge(oldEmpleadoOfEjecucionetapaproduccionListEjecucionetapaproduccion);
                }
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoListMantenimientocorrectivo : empleado.getMantenimientocorrectivoList()) {
                Empleado oldEmpleadoOfMantenimientocorrectivoListMantenimientocorrectivo = mantenimientocorrectivoListMantenimientocorrectivo.getEmpleado();
                mantenimientocorrectivoListMantenimientocorrectivo.setEmpleado(empleado);
                mantenimientocorrectivoListMantenimientocorrectivo = em.merge(mantenimientocorrectivoListMantenimientocorrectivo);
                if (oldEmpleadoOfMantenimientocorrectivoListMantenimientocorrectivo != null) {
                    oldEmpleadoOfMantenimientocorrectivoListMantenimientocorrectivo.getMantenimientocorrectivoList().remove(mantenimientocorrectivoListMantenimientocorrectivo);
                    oldEmpleadoOfMantenimientocorrectivoListMantenimientocorrectivo = em.merge(oldEmpleadoOfMantenimientocorrectivoListMantenimientocorrectivo);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidad : empleado.getDetalleplanificacioncalidadList()) {
                Empleado oldEmpleadoOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = detalleplanificacioncalidadListDetalleplanificacioncalidad.getEmpleado();
                detalleplanificacioncalidadListDetalleplanificacioncalidad.setEmpleado(empleado);
                detalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                if (oldEmpleadoOfDetalleplanificacioncalidadListDetalleplanificacioncalidad != null) {
                    oldEmpleadoOfDetalleplanificacioncalidadListDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                    oldEmpleadoOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(oldEmpleadoOfDetalleplanificacioncalidadListDetalleplanificacioncalidad);
                }
            }
            for (Disponibilidadhoraria disponibilidadhorariaListDisponibilidadhoraria : empleado.getDisponibilidadhorariaList()) {
                Empleado oldIdempleadoOfDisponibilidadhorariaListDisponibilidadhoraria = disponibilidadhorariaListDisponibilidadhoraria.getIdempleado();
                disponibilidadhorariaListDisponibilidadhoraria.setIdempleado(empleado);
                disponibilidadhorariaListDisponibilidadhoraria = em.merge(disponibilidadhorariaListDisponibilidadhoraria);
                if (oldIdempleadoOfDisponibilidadhorariaListDisponibilidadhoraria != null) {
                    oldIdempleadoOfDisponibilidadhorariaListDisponibilidadhoraria.getDisponibilidadhorariaList().remove(disponibilidadhorariaListDisponibilidadhoraria);
                    oldIdempleadoOfDisponibilidadhorariaListDisponibilidadhoraria = em.merge(oldIdempleadoOfDisponibilidadhorariaListDisponibilidadhoraria);
                }
            }
            for (Asistencia asistenciaListAsistencia : empleado.getAsistenciaList()) {
                Empleado oldEmpleado1OfAsistenciaListAsistencia = asistenciaListAsistencia.getEmpleado1();
                asistenciaListAsistencia.setEmpleado1(empleado);
                asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
                if (oldEmpleado1OfAsistenciaListAsistencia != null) {
                    oldEmpleado1OfAsistenciaListAsistencia.getAsistenciaList().remove(asistenciaListAsistencia);
                    oldEmpleado1OfAsistenciaListAsistencia = em.merge(oldEmpleado1OfAsistenciaListAsistencia);
                }
            }
            for (Ejecucionetapaproduccion ejecucionprocesocalidadListEjecucionetapaproduccion : empleado.getEjecucionprocesocalidadList()) {
                Empleado oldEmpleadoOfEjecucionprocesocalidadListEjecucionetapaproduccion = ejecucionprocesocalidadListEjecucionetapaproduccion.getEmpleado();
                ejecucionprocesocalidadListEjecucionetapaproduccion.setEmpleado(empleado);
                ejecucionprocesocalidadListEjecucionetapaproduccion = em.merge(ejecucionprocesocalidadListEjecucionetapaproduccion);
                if (oldEmpleadoOfEjecucionprocesocalidadListEjecucionetapaproduccion != null) {
                    oldEmpleadoOfEjecucionprocesocalidadListEjecucionetapaproduccion.getEjecucionprocesocalidadList().remove(ejecucionprocesocalidadListEjecucionetapaproduccion);
                    oldEmpleadoOfEjecucionprocesocalidadListEjecucionetapaproduccion = em.merge(oldEmpleadoOfEjecucionprocesocalidadListEjecucionetapaproduccion);
                }
            }
            em.getTransaction().commit();
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
            Usuario usuarioOld = persistentEmpleado.getUsuario();
            Usuario usuarioNew = empleado.getUsuario();
            Tipodocumento tipodocumentoOld = persistentEmpleado.getTipodocumento();
            Tipodocumento tipodocumentoNew = empleado.getTipodocumento();
            Domicilio domicilioOld = persistentEmpleado.getDomicilio();
            Domicilio domicilioNew = empleado.getDomicilio();
            Categoria categoriaOld = persistentEmpleado.getCategoria();
            Categoria categoriaNew = empleado.getCategoria();
            Cargo cargoOld = persistentEmpleado.getCargo();
            Cargo cargoNew = empleado.getCargo();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListOld = persistentEmpleado.getDetalleplanificacionproduccionList();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListNew = empleado.getDetalleplanificacionproduccionList();
            List<Empleadoxturno> empleadoxturnoListOld = persistentEmpleado.getEmpleadoxturnoList();
            List<Empleadoxturno> empleadoxturnoListNew = empleado.getEmpleadoxturnoList();
            List<Ejecucionetapaproduccion> ejecucionetapaproduccionListOld = persistentEmpleado.getEjecucionetapaproduccionList();
            List<Ejecucionetapaproduccion> ejecucionetapaproduccionListNew = empleado.getEjecucionetapaproduccionList();
            List<Mantenimientocorrectivo> mantenimientocorrectivoListOld = persistentEmpleado.getMantenimientocorrectivoList();
            List<Mantenimientocorrectivo> mantenimientocorrectivoListNew = empleado.getMantenimientocorrectivoList();
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListOld = persistentEmpleado.getDetalleplanificacioncalidadList();
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListNew = empleado.getDetalleplanificacioncalidadList();
            List<Disponibilidadhoraria> disponibilidadhorariaListOld = persistentEmpleado.getDisponibilidadhorariaList();
            List<Disponibilidadhoraria> disponibilidadhorariaListNew = empleado.getDisponibilidadhorariaList();
            List<Asistencia> asistenciaListOld = persistentEmpleado.getAsistenciaList();
            List<Asistencia> asistenciaListNew = empleado.getAsistenciaList();
            List<Ejecucionetapaproduccion> ejecucionprocesocalidadListOld = persistentEmpleado.getEjecucionprocesocalidadList();
            List<Ejecucionetapaproduccion> ejecucionprocesocalidadListNew = empleado.getEjecucionprocesocalidadList();
            List<String> illegalOrphanMessages = null;
            for (Empleadoxturno empleadoxturnoListOldEmpleadoxturno : empleadoxturnoListOld) {
                if (!empleadoxturnoListNew.contains(empleadoxturnoListOldEmpleadoxturno)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleadoxturno " + empleadoxturnoListOldEmpleadoxturno + " since its empleado field is not nullable.");
                }
            }
            for (Asistencia asistenciaListOldAsistencia : asistenciaListOld) {
                if (!asistenciaListNew.contains(asistenciaListOldAsistencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asistencia " + asistenciaListOldAsistencia + " since its empleado1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                empleado.setUsuario(usuarioNew);
            }
            if (tipodocumentoNew != null) {
                tipodocumentoNew = em.getReference(tipodocumentoNew.getClass(), tipodocumentoNew.getIdtipodocumento());
                empleado.setTipodocumento(tipodocumentoNew);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                empleado.setDomicilio(domicilioNew);
            }
            if (categoriaNew != null) {
                categoriaNew = em.getReference(categoriaNew.getClass(), categoriaNew.getIdcategoria());
                empleado.setCategoria(categoriaNew);
            }
            if (cargoNew != null) {
                cargoNew = em.getReference(cargoNew.getClass(), cargoNew.getIdcargo());
                empleado.setCargo(cargoNew);
            }
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionListNew = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionListNew) {
                detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionListNew.add(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionListNew = attachedDetalleplanificacionproduccionListNew;
            empleado.setDetalleplanificacionproduccionList(detalleplanificacionproduccionListNew);
            List<Empleadoxturno> attachedEmpleadoxturnoListNew = new ArrayList<Empleadoxturno>();
            for (Empleadoxturno empleadoxturnoListNewEmpleadoxturnoToAttach : empleadoxturnoListNew) {
                empleadoxturnoListNewEmpleadoxturnoToAttach = em.getReference(empleadoxturnoListNewEmpleadoxturnoToAttach.getClass(), empleadoxturnoListNewEmpleadoxturnoToAttach.getEmpleadoxturnoPK());
                attachedEmpleadoxturnoListNew.add(empleadoxturnoListNewEmpleadoxturnoToAttach);
            }
            empleadoxturnoListNew = attachedEmpleadoxturnoListNew;
            empleado.setEmpleadoxturnoList(empleadoxturnoListNew);
            List<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionListNew = new ArrayList<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach : ejecucionetapaproduccionListNew) {
                ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionListNew.add(ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach);
            }
            ejecucionetapaproduccionListNew = attachedEjecucionetapaproduccionListNew;
            empleado.setEjecucionetapaproduccionList(ejecucionetapaproduccionListNew);
            List<Mantenimientocorrectivo> attachedMantenimientocorrectivoListNew = new ArrayList<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoListNewMantenimientocorrectivoToAttach : mantenimientocorrectivoListNew) {
                mantenimientocorrectivoListNewMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoListNewMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoListNewMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoListNew.add(mantenimientocorrectivoListNewMantenimientocorrectivoToAttach);
            }
            mantenimientocorrectivoListNew = attachedMantenimientocorrectivoListNew;
            empleado.setMantenimientocorrectivoList(mantenimientocorrectivoListNew);
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadListNew = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadListNew) {
                detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleplanificacioncalidadListNew.add(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadListNew = attachedDetalleplanificacioncalidadListNew;
            empleado.setDetalleplanificacioncalidadList(detalleplanificacioncalidadListNew);
            List<Disponibilidadhoraria> attachedDisponibilidadhorariaListNew = new ArrayList<Disponibilidadhoraria>();
            for (Disponibilidadhoraria disponibilidadhorariaListNewDisponibilidadhorariaToAttach : disponibilidadhorariaListNew) {
                disponibilidadhorariaListNewDisponibilidadhorariaToAttach = em.getReference(disponibilidadhorariaListNewDisponibilidadhorariaToAttach.getClass(), disponibilidadhorariaListNewDisponibilidadhorariaToAttach.getId());
                attachedDisponibilidadhorariaListNew.add(disponibilidadhorariaListNewDisponibilidadhorariaToAttach);
            }
            disponibilidadhorariaListNew = attachedDisponibilidadhorariaListNew;
            empleado.setDisponibilidadhorariaList(disponibilidadhorariaListNew);
            List<Asistencia> attachedAsistenciaListNew = new ArrayList<Asistencia>();
            for (Asistencia asistenciaListNewAsistenciaToAttach : asistenciaListNew) {
                asistenciaListNewAsistenciaToAttach = em.getReference(asistenciaListNewAsistenciaToAttach.getClass(), asistenciaListNewAsistenciaToAttach.getAsistenciaPK());
                attachedAsistenciaListNew.add(asistenciaListNewAsistenciaToAttach);
            }
            asistenciaListNew = attachedAsistenciaListNew;
            empleado.setAsistenciaList(asistenciaListNew);
            List<Ejecucionetapaproduccion> attachedEjecucionprocesocalidadListNew = new ArrayList<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionprocesocalidadListNewEjecucionetapaproduccionToAttach : ejecucionprocesocalidadListNew) {
                ejecucionprocesocalidadListNewEjecucionetapaproduccionToAttach = em.getReference(ejecucionprocesocalidadListNewEjecucionetapaproduccionToAttach.getClass(), ejecucionprocesocalidadListNewEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionprocesocalidadListNew.add(ejecucionprocesocalidadListNewEjecucionetapaproduccionToAttach);
            }
            ejecucionprocesocalidadListNew = attachedEjecucionprocesocalidadListNew;
            empleado.setEjecucionprocesocalidadList(ejecucionprocesocalidadListNew);
            empleado = em.merge(empleado);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getEmpleadoList().remove(empleado);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getEmpleadoList().add(empleado);
                usuarioNew = em.merge(usuarioNew);
            }
            if (tipodocumentoOld != null && !tipodocumentoOld.equals(tipodocumentoNew)) {
                tipodocumentoOld.getEmpleadoList().remove(empleado);
                tipodocumentoOld = em.merge(tipodocumentoOld);
            }
            if (tipodocumentoNew != null && !tipodocumentoNew.equals(tipodocumentoOld)) {
                tipodocumentoNew.getEmpleadoList().add(empleado);
                tipodocumentoNew = em.merge(tipodocumentoNew);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getEmpleadoList().remove(empleado);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getEmpleadoList().add(empleado);
                domicilioNew = em.merge(domicilioNew);
            }
            if (categoriaOld != null && !categoriaOld.equals(categoriaNew)) {
                categoriaOld.getEmpleadoList().remove(empleado);
                categoriaOld = em.merge(categoriaOld);
            }
            if (categoriaNew != null && !categoriaNew.equals(categoriaOld)) {
                categoriaNew.getEmpleadoList().add(empleado);
                categoriaNew = em.merge(categoriaNew);
            }
            if (cargoOld != null && !cargoOld.equals(cargoNew)) {
                cargoOld.getEmpleadoList().remove(empleado);
                cargoOld = em.merge(cargoOld);
            }
            if (cargoNew != null && !cargoNew.equals(cargoOld)) {
                cargoNew.getEmpleadoList().add(empleado);
                cargoNew = em.merge(cargoNew);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListOldDetalleplanificacionproduccion : detalleplanificacionproduccionListOld) {
                if (!detalleplanificacionproduccionListNew.contains(detalleplanificacionproduccionListOldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionListOldDetalleplanificacionproduccion.setIdempleado(null);
                    detalleplanificacionproduccionListOldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListOldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccion : detalleplanificacionproduccionListNew) {
                if (!detalleplanificacionproduccionListOld.contains(detalleplanificacionproduccionListNewDetalleplanificacionproduccion)) {
                    Empleado oldIdempleadoOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = detalleplanificacionproduccionListNewDetalleplanificacionproduccion.getIdempleado();
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion.setIdempleado(empleado);
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                    if (oldIdempleadoOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion != null && !oldIdempleadoOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.equals(empleado)) {
                        oldIdempleadoOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                        oldIdempleadoOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(oldIdempleadoOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Empleadoxturno empleadoxturnoListNewEmpleadoxturno : empleadoxturnoListNew) {
                if (!empleadoxturnoListOld.contains(empleadoxturnoListNewEmpleadoxturno)) {
                    Empleado oldEmpleadoOfEmpleadoxturnoListNewEmpleadoxturno = empleadoxturnoListNewEmpleadoxturno.getEmpleado();
                    empleadoxturnoListNewEmpleadoxturno.setEmpleado(empleado);
                    empleadoxturnoListNewEmpleadoxturno = em.merge(empleadoxturnoListNewEmpleadoxturno);
                    if (oldEmpleadoOfEmpleadoxturnoListNewEmpleadoxturno != null && !oldEmpleadoOfEmpleadoxturnoListNewEmpleadoxturno.equals(empleado)) {
                        oldEmpleadoOfEmpleadoxturnoListNewEmpleadoxturno.getEmpleadoxturnoList().remove(empleadoxturnoListNewEmpleadoxturno);
                        oldEmpleadoOfEmpleadoxturnoListNewEmpleadoxturno = em.merge(oldEmpleadoOfEmpleadoxturnoListNewEmpleadoxturno);
                    }
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListOldEjecucionetapaproduccion : ejecucionetapaproduccionListOld) {
                if (!ejecucionetapaproduccionListNew.contains(ejecucionetapaproduccionListOldEjecucionetapaproduccion)) {
                    ejecucionetapaproduccionListOldEjecucionetapaproduccion.setEmpleado(null);
                    ejecucionetapaproduccionListOldEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionListOldEjecucionetapaproduccion);
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListNewEjecucionetapaproduccion : ejecucionetapaproduccionListNew) {
                if (!ejecucionetapaproduccionListOld.contains(ejecucionetapaproduccionListNewEjecucionetapaproduccion)) {
                    Empleado oldEmpleadoOfEjecucionetapaproduccionListNewEjecucionetapaproduccion = ejecucionetapaproduccionListNewEjecucionetapaproduccion.getEmpleado();
                    ejecucionetapaproduccionListNewEjecucionetapaproduccion.setEmpleado(empleado);
                    ejecucionetapaproduccionListNewEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionListNewEjecucionetapaproduccion);
                    if (oldEmpleadoOfEjecucionetapaproduccionListNewEjecucionetapaproduccion != null && !oldEmpleadoOfEjecucionetapaproduccionListNewEjecucionetapaproduccion.equals(empleado)) {
                        oldEmpleadoOfEjecucionetapaproduccionListNewEjecucionetapaproduccion.getEjecucionetapaproduccionList().remove(ejecucionetapaproduccionListNewEjecucionetapaproduccion);
                        oldEmpleadoOfEjecucionetapaproduccionListNewEjecucionetapaproduccion = em.merge(oldEmpleadoOfEjecucionetapaproduccionListNewEjecucionetapaproduccion);
                    }
                }
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoListOldMantenimientocorrectivo : mantenimientocorrectivoListOld) {
                if (!mantenimientocorrectivoListNew.contains(mantenimientocorrectivoListOldMantenimientocorrectivo)) {
                    mantenimientocorrectivoListOldMantenimientocorrectivo.setEmpleado(null);
                    mantenimientocorrectivoListOldMantenimientocorrectivo = em.merge(mantenimientocorrectivoListOldMantenimientocorrectivo);
                }
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoListNewMantenimientocorrectivo : mantenimientocorrectivoListNew) {
                if (!mantenimientocorrectivoListOld.contains(mantenimientocorrectivoListNewMantenimientocorrectivo)) {
                    Empleado oldEmpleadoOfMantenimientocorrectivoListNewMantenimientocorrectivo = mantenimientocorrectivoListNewMantenimientocorrectivo.getEmpleado();
                    mantenimientocorrectivoListNewMantenimientocorrectivo.setEmpleado(empleado);
                    mantenimientocorrectivoListNewMantenimientocorrectivo = em.merge(mantenimientocorrectivoListNewMantenimientocorrectivo);
                    if (oldEmpleadoOfMantenimientocorrectivoListNewMantenimientocorrectivo != null && !oldEmpleadoOfMantenimientocorrectivoListNewMantenimientocorrectivo.equals(empleado)) {
                        oldEmpleadoOfMantenimientocorrectivoListNewMantenimientocorrectivo.getMantenimientocorrectivoList().remove(mantenimientocorrectivoListNewMantenimientocorrectivo);
                        oldEmpleadoOfMantenimientocorrectivoListNewMantenimientocorrectivo = em.merge(oldEmpleadoOfMantenimientocorrectivoListNewMantenimientocorrectivo);
                    }
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListOldDetalleplanificacioncalidad : detalleplanificacioncalidadListOld) {
                if (!detalleplanificacioncalidadListNew.contains(detalleplanificacioncalidadListOldDetalleplanificacioncalidad)) {
                    detalleplanificacioncalidadListOldDetalleplanificacioncalidad.setEmpleado(null);
                    detalleplanificacioncalidadListOldDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListOldDetalleplanificacioncalidad);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidad : detalleplanificacioncalidadListNew) {
                if (!detalleplanificacioncalidadListOld.contains(detalleplanificacioncalidadListNewDetalleplanificacioncalidad)) {
                    Empleado oldEmpleadoOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = detalleplanificacioncalidadListNewDetalleplanificacioncalidad.getEmpleado();
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad.setEmpleado(empleado);
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                    if (oldEmpleadoOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad != null && !oldEmpleadoOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.equals(empleado)) {
                        oldEmpleadoOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                        oldEmpleadoOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(oldEmpleadoOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                    }
                }
            }
            for (Disponibilidadhoraria disponibilidadhorariaListOldDisponibilidadhoraria : disponibilidadhorariaListOld) {
                if (!disponibilidadhorariaListNew.contains(disponibilidadhorariaListOldDisponibilidadhoraria)) {
                    disponibilidadhorariaListOldDisponibilidadhoraria.setIdempleado(null);
                    disponibilidadhorariaListOldDisponibilidadhoraria = em.merge(disponibilidadhorariaListOldDisponibilidadhoraria);
                }
            }
            for (Disponibilidadhoraria disponibilidadhorariaListNewDisponibilidadhoraria : disponibilidadhorariaListNew) {
                if (!disponibilidadhorariaListOld.contains(disponibilidadhorariaListNewDisponibilidadhoraria)) {
                    Empleado oldIdempleadoOfDisponibilidadhorariaListNewDisponibilidadhoraria = disponibilidadhorariaListNewDisponibilidadhoraria.getIdempleado();
                    disponibilidadhorariaListNewDisponibilidadhoraria.setIdempleado(empleado);
                    disponibilidadhorariaListNewDisponibilidadhoraria = em.merge(disponibilidadhorariaListNewDisponibilidadhoraria);
                    if (oldIdempleadoOfDisponibilidadhorariaListNewDisponibilidadhoraria != null && !oldIdempleadoOfDisponibilidadhorariaListNewDisponibilidadhoraria.equals(empleado)) {
                        oldIdempleadoOfDisponibilidadhorariaListNewDisponibilidadhoraria.getDisponibilidadhorariaList().remove(disponibilidadhorariaListNewDisponibilidadhoraria);
                        oldIdempleadoOfDisponibilidadhorariaListNewDisponibilidadhoraria = em.merge(oldIdempleadoOfDisponibilidadhorariaListNewDisponibilidadhoraria);
                    }
                }
            }
            for (Asistencia asistenciaListNewAsistencia : asistenciaListNew) {
                if (!asistenciaListOld.contains(asistenciaListNewAsistencia)) {
                    Empleado oldEmpleado1OfAsistenciaListNewAsistencia = asistenciaListNewAsistencia.getEmpleado1();
                    asistenciaListNewAsistencia.setEmpleado1(empleado);
                    asistenciaListNewAsistencia = em.merge(asistenciaListNewAsistencia);
                    if (oldEmpleado1OfAsistenciaListNewAsistencia != null && !oldEmpleado1OfAsistenciaListNewAsistencia.equals(empleado)) {
                        oldEmpleado1OfAsistenciaListNewAsistencia.getAsistenciaList().remove(asistenciaListNewAsistencia);
                        oldEmpleado1OfAsistenciaListNewAsistencia = em.merge(oldEmpleado1OfAsistenciaListNewAsistencia);
                    }
                }
            }
            for (Ejecucionetapaproduccion ejecucionprocesocalidadListOldEjecucionetapaproduccion : ejecucionprocesocalidadListOld) {
                if (!ejecucionprocesocalidadListNew.contains(ejecucionprocesocalidadListOldEjecucionetapaproduccion)) {
                    ejecucionprocesocalidadListOldEjecucionetapaproduccion.setEmpleado(null);
                    ejecucionprocesocalidadListOldEjecucionetapaproduccion = em.merge(ejecucionprocesocalidadListOldEjecucionetapaproduccion);
                }
            }
            for (Ejecucionetapaproduccion ejecucionprocesocalidadListNewEjecucionetapaproduccion : ejecucionprocesocalidadListNew) {
                if (!ejecucionprocesocalidadListOld.contains(ejecucionprocesocalidadListNewEjecucionetapaproduccion)) {
                    Empleado oldEmpleadoOfEjecucionprocesocalidadListNewEjecucionetapaproduccion = ejecucionprocesocalidadListNewEjecucionetapaproduccion.getEmpleado();
                    ejecucionprocesocalidadListNewEjecucionetapaproduccion.setEmpleado(empleado);
                    ejecucionprocesocalidadListNewEjecucionetapaproduccion = em.merge(ejecucionprocesocalidadListNewEjecucionetapaproduccion);
                    if (oldEmpleadoOfEjecucionprocesocalidadListNewEjecucionetapaproduccion != null && !oldEmpleadoOfEjecucionprocesocalidadListNewEjecucionetapaproduccion.equals(empleado)) {
                        oldEmpleadoOfEjecucionprocesocalidadListNewEjecucionetapaproduccion.getEjecucionprocesocalidadList().remove(ejecucionprocesocalidadListNewEjecucionetapaproduccion);
                        oldEmpleadoOfEjecucionprocesocalidadListNewEjecucionetapaproduccion = em.merge(oldEmpleadoOfEjecucionprocesocalidadListNewEjecucionetapaproduccion);
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
            List<Empleadoxturno> empleadoxturnoListOrphanCheck = empleado.getEmpleadoxturnoList();
            for (Empleadoxturno empleadoxturnoListOrphanCheckEmpleadoxturno : empleadoxturnoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Empleadoxturno " + empleadoxturnoListOrphanCheckEmpleadoxturno + " in its empleadoxturnoList field has a non-nullable empleado field.");
            }
            List<Asistencia> asistenciaListOrphanCheck = empleado.getAsistenciaList();
            for (Asistencia asistenciaListOrphanCheckAsistencia : asistenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Asistencia " + asistenciaListOrphanCheckAsistencia + " in its asistenciaList field has a non-nullable empleado1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuario = empleado.getUsuario();
            if (usuario != null) {
                usuario.getEmpleadoList().remove(empleado);
                usuario = em.merge(usuario);
            }
            Tipodocumento tipodocumento = empleado.getTipodocumento();
            if (tipodocumento != null) {
                tipodocumento.getEmpleadoList().remove(empleado);
                tipodocumento = em.merge(tipodocumento);
            }
            Domicilio domicilio = empleado.getDomicilio();
            if (domicilio != null) {
                domicilio.getEmpleadoList().remove(empleado);
                domicilio = em.merge(domicilio);
            }
            Categoria categoria = empleado.getCategoria();
            if (categoria != null) {
                categoria.getEmpleadoList().remove(empleado);
                categoria = em.merge(categoria);
            }
            Cargo cargo = empleado.getCargo();
            if (cargo != null) {
                cargo.getEmpleadoList().remove(empleado);
                cargo = em.merge(cargo);
            }
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionList = empleado.getDetalleplanificacionproduccionList();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : detalleplanificacionproduccionList) {
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIdempleado(null);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
            }
            List<Ejecucionetapaproduccion> ejecucionetapaproduccionList = empleado.getEjecucionetapaproduccionList();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListEjecucionetapaproduccion : ejecucionetapaproduccionList) {
                ejecucionetapaproduccionListEjecucionetapaproduccion.setEmpleado(null);
                ejecucionetapaproduccionListEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionListEjecucionetapaproduccion);
            }
            List<Mantenimientocorrectivo> mantenimientocorrectivoList = empleado.getMantenimientocorrectivoList();
            for (Mantenimientocorrectivo mantenimientocorrectivoListMantenimientocorrectivo : mantenimientocorrectivoList) {
                mantenimientocorrectivoListMantenimientocorrectivo.setEmpleado(null);
                mantenimientocorrectivoListMantenimientocorrectivo = em.merge(mantenimientocorrectivoListMantenimientocorrectivo);
            }
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadList = empleado.getDetalleplanificacioncalidadList();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidad : detalleplanificacioncalidadList) {
                detalleplanificacioncalidadListDetalleplanificacioncalidad.setEmpleado(null);
                detalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListDetalleplanificacioncalidad);
            }
            List<Disponibilidadhoraria> disponibilidadhorariaList = empleado.getDisponibilidadhorariaList();
            for (Disponibilidadhoraria disponibilidadhorariaListDisponibilidadhoraria : disponibilidadhorariaList) {
                disponibilidadhorariaListDisponibilidadhoraria.setIdempleado(null);
                disponibilidadhorariaListDisponibilidadhoraria = em.merge(disponibilidadhorariaListDisponibilidadhoraria);
            }
            List<Ejecucionetapaproduccion> ejecucionprocesocalidadList = empleado.getEjecucionprocesocalidadList();
            for (Ejecucionetapaproduccion ejecucionprocesocalidadListEjecucionetapaproduccion : ejecucionprocesocalidadList) {
                ejecucionprocesocalidadListEjecucionetapaproduccion.setEmpleado(null);
                ejecucionprocesocalidadListEjecucionetapaproduccion = em.merge(ejecucionprocesocalidadListEjecucionetapaproduccion);
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
