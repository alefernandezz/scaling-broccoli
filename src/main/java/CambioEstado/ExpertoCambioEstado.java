package CambioEstado;

import ABMArticulo.exceptions.CambioEstadoException;
import CambioEstado.dtos.DTOEstadoDestino;
import CambioEstado.dtos.DTOEstadoOrigen;
import CambioEstado.dtos.DTOHistorialEstado;
import CambioEstado.dtos.DTOTramitesVigentes;
import CambioEstado.dtos.TramiteDTO;
import entidades.Consultor;
import entidades.Tramite;
import utils.FachadaPersistencia;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import utils.DTOCriterio;

public class ExpertoCambioEstado {
    
    
    public List<DTOTramitesVigentes> buscarConsultor (int legajoConsultor){
        FachadaPersistencia.getInstance().iniciarTransaccion();
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        
        dto.setAtributo("legajoConsultor");
        dto.setOperacion("=");
        dto.setValor(legajoConsultor);
        
        criterioList.add(dto);
        List<Object> lConsultor = FachadaPersistencia.getInstance().buscar("Consultor", criterioList);
        
        if (lConsultor.isEmpty()) {
            //Agregar que muestre en pantalla que el consultor ingresado no existe
            return null;
        }
        Consultor consultorEncontrado = (Consultor) lConsultor.get(0);
        criterioList.clear();
        
        DTOCriterio criterio1 = new DTOCriterio();
        criterio1.setAtributo("consultor");
        criterio1.setOperacion("=");
        criterio1.setValor(consultorEncontrado);
        
        criterioList.add(criterio1);
        
        DTOCriterio criterio2 = new DTOCriterio();
        criterio2.setAtributo("fechaFinTramite");
        criterio2.setOperacion("=");
        criterio2.setValor(null);
        
        criterioList.add(criterio2);
        List<Object> lTramites = FachadaPersistencia.getInstance().buscar("Tramite", criterioList);
        
        List<DTOTramitesVigentes> dtoTramitesVigentesList = new ArrayList<>();
        for (Object tramiteObj : lTramites) {
            Tramite tramite = (Tramite) tramiteObj;
            TramiteDTO dtoTramite = new TramiteDTO();
        
        // Mapear atributos del tramite al DTO
        dtoTramite.setNroTramite(tramite.getNroTramite());
        dtoTramite.setFechaInicioTramite(tramite.getFechaInicioTramite());
        dtoTramite.setFechaRecepcionTramite(tramite.getFechaRecepcionTramite());
        dtoTramite.setEstadoTramite(tramite.getEstadoTramite());
        //ACA TENGO UN PROBLEMA CON LOS DTO PORQUE SEGUN LA SECUENCIA
        //EL DTOTRAMITE VA ADENTRO DEL DTOTRAMITESVIGENTES
         DTOTramitesVigentes dtoTramiteVigente = new DTOTramitesVigentes();
         dtoTramiteVigente.setTramites(dtoTramite);
        
        // Añadir a la lista de DTOs
        dtoTramitesVigentesList.add(dtoTramiteVigente);
    }
    
    // Devolver la lista de trámites vigentes del consultor
    return dtoTramitesVigentesList;
    }

   
    // Método para cambiar el estado del trámite
   /* public void cambiarEstado(int nroTramite, DTOEstadoOrigen estadoOrigen, DTOEstadoDestino estadoDestino, String descripcion) throws CambioEstadoException {
        // Obtener el trámite actual
        TramiteDTO tramite = obtenerTramite(nroTramite);
        if (tramite == null) {
            throw new CambioEstadoException("El trámite no existe.");
        }

        // Actualizar el estado del trámite
        tramite.setNombreEstadoTramite(estadoDestino.getNombreEstadoDestino());
        // Aquí puedes agregar la lógica para actualizar la fecha de recepción si es necesario

        // Registrar en el historial
        DTOHistorialEstado historial = new DTOHistorialEstado();
        historial.setNombreEstadoTramite(estadoDestino.getNombreEstadoDestino());
        historial.setFechaDesdeTET(LocalDateTime.now());
        // Setear la fecha hasta si es necesario

        // Guardar cambios en la base de datos
        guardarTramite(tramite);
        guardarHistorial(historial);
    }

    // Método para obtener un trámite por número
    /*private TramiteDTO obtenerTramite(int nroTramite) {
        List<TramiteDTO> tramites = FachadaPersistencia.getInstance().buscar("Tramite", nroTramite);
        return tramites.isEmpty() ? null : tramites.get(0);
    }

    // Método para obtener todos los trámites
    public List<Object> buscarConsultor(String legajoConsultor) {
        // Utilizar FachadaPersistencia para buscar al consultor
        FachadaPersistencia.getInstance().iniciarTransaccion();
        List<DTOCriterio> criterio1 = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
/*
        if (legajoConsultor != null) {
            criterio1.setAtributo("legajoConsutltor");
            criterio1.setOperacion("=");
            criterio1.setValor(legajoConsultor);
            System.out.println("No se encontraron trámites para el legajo: " + legajo);
        }

        return tramites;*/
    

    // Método para guardar el trámite actualizado
    private void guardarTramite(TramiteDTO tramite) {
        FachadaPersistencia.getInstance().guardar(tramite);
    }

    // Método para guardar el historial de estado
    private void guardarHistorial(DTOHistorialEstado historial) {
        FachadaPersistencia.getInstance().guardar(historial);
    }

    // Método para obtener el historial de estados
    public List<DTOHistorialEstado> obtenerHistorialEstados(int nroTramite) {
        // Implementa la lógica para recuperar el historial de estados desde la base de datos
        return new ArrayList<>(); // Reemplaza con la implementación real
    }
}