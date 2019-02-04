package bg.tu.sofia.model;

import bg.tu.sofia.utils.Utils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "forgotten_password", schema = "public")
public class ForgottenPassword implements Serializable {
    private static final int EXPIRATION = 15;

    private Integer id;
    private String token;
    private String email;
    private Date expiryDate;

    public ForgottenPassword(String token, String email) {
        this.token = token;
        this.email = email;
        this.expiryDate = Utils.calculateExpiryDate(EXPIRATION);
    }

    public ForgottenPassword() {
    }

    @Id
    @GeneratedValue(generator = "forgotten_password_id_seq")
    @SequenceGenerator(sequenceName = "forgotten_password_id_seq", name = "forgotten_password_id_seq", schema = "public", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForgottenPassword that = (ForgottenPassword) o;
        return id == that.id &&
                Objects.equals(token, that.token) &&
                Objects.equals(email, that.email) &&
                Objects.equals(expiryDate, that.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, email, expiryDate);
    }
}
