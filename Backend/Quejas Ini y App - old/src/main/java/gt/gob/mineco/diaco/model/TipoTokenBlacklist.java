package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the token_blacklist database table.
 *
 */
@Entity
@Table(name = "diaco_token_blacklist")
@NamedQueries({
    @NamedQuery(name = "TipoTokenBlacklist.findAll", query = "SELECT t FROM TipoTokenBlacklist t")
    ,
	@NamedQuery(name = "TipoTokenBlacklist.findByToken", query = "SELECT t FROM TipoTokenBlacklist t WHERE t.token = :token")
    ,
        @NamedQuery(name = "TipoTokenBlacklist.findByIdUsuario", query = "SELECT t FROM TipoTokenBlacklist t WHERE t.idUsuario = :idUsuario")
    ,
	@NamedQuery(name = "TipoTokenBlacklist.countByToken", query = "SELECT t FROM TipoTokenBlacklist t WHERE t.token = :token")
})
public class TipoTokenBlacklist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "blacklist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blacklistId;

    @Column(name = "invalidation_date")
    private Timestamp invalidationDate;

    @Column(name = "token")
    private String token;

    @Column(name = "id_usuario")
    private int idUsuario;

    public TipoTokenBlacklist() {
    }

    public Integer getBlacklistId() {
        return this.blacklistId;
    }

    public void setBlacklistId(Integer blacklistId) {
        this.blacklistId = blacklistId;
    }

    public Timestamp getInvalidationDate() {
        return this.invalidationDate;
    }

    public void setInvalidationDate(Timestamp invalidationDate) {
        this.invalidationDate = invalidationDate;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
