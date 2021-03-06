package bg.tu.sofia.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "book_category", schema = "public")
public class BookCategory {
    private Integer id;
    private String name;
    private String nameEn;

    @Id
    @GeneratedValue(generator = "book_category_id_seq")
    @SequenceGenerator(sequenceName = "book_category_id_seq", name = "book_category_id_seq", schema = "public", allocationSize = 1, initialValue = 1)
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

    @Basic
    @Column(name = "name_en")
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCategory that = (BookCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(nameEn, that.nameEn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nameEn);
    }
}
