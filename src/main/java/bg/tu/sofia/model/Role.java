package bg.tu.sofia.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by denislav.veizov on 4.3.2018 г..
 */
@Entity
@Table(name = "role", schema = "public")
@Cacheable(value = false)
public class Role {
    private Integer id;
    private String name;

    @Id
    @GeneratedValue(generator = "role_id_seq")
    @SequenceGenerator(sequenceName = "role_id_seq", name = "role_id_seq", schema = "public", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (!Objects.equals(id, role.id)) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (id != null ? id.hashCode() : 0);;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
