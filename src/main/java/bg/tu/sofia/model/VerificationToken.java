package bg.tu.sofia.model;

import bg.tu.sofia.utils.Utils;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "verification_token", schema = "public")
public class VerificationToken {
    public static final int EXPIRATION = 60 * 24;// 24 hours

    private Integer id;
    private String token;
    private Date expiryDate;
    private User user;

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = Utils.calculateExpiryDate(EXPIRATION);
    }

    public VerificationToken() {
    }

    @Id
    @GeneratedValue(generator = "verification_token_id_seq")
    @SequenceGenerator(sequenceName = "verification_token_id_seq", name = "verification_token_id_seq", schema = "public", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
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
    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationToken that = (VerificationToken) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(token, that.token) &&
                Objects.equals(expiryDate, that.expiryDate) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, expiryDate, user);
    }

}
