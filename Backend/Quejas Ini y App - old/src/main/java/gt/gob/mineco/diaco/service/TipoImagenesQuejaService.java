package gt.gob.mineco.diaco.service;

import gt.gob.mineco.diaco.model.TipoTipoImagen;
import gt.gob.mineco.diaco.util.ResponseRs;
import java.io.InputStream;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

public interface TipoImagenesQuejaService {

    public ResponseRs
            saveImagenPub(
                    InputStream uploadedInputStream,
                    FormDataContentDisposition fileDetail,
                    Integer id_queja,
                    Integer id_tipo_imagen,
                    Integer id_categoria_imagen,
                    Integer id_flujo);

    TipoTipoImagen findTipoImagen(String tipo);
}
