package gt.gob.mineco.diaco.service;

public interface NotificacionService {
    
    public Boolean consumerNotification(String to, String link, Boolean flag);
    public Boolean permanentCommunitacion(String to, String descripcion, String observacion, Boolean flag);
}
