package bg.tu.sofia.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Book {
    private Integer id;
    private String title;
    private String author;
    private String description;
    private Short year;
    private BookCategory category;
    private Blobs file;
    private Blobs cover;
    private Integer numberOfPages;
    private Date createdDate;

    @Id
    @GeneratedValue(generator = "book_id_seq")
    @SequenceGenerator(sequenceName = "book_id_seq", name = "book_id_seq", schema = "public", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "year")
    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file")
    public Blobs getFile() {
        return file;
    }

    public void setFile(Blobs file) {
        this.file = file;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cover")
    public Blobs getCover() {
        return cover;
    }

    public void setCover(Blobs cover) {
        this.cover = cover;
    }


    @OneToOne
    @JoinColumn(name = "category")
    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    @Basic
    @Column(name = "number_of_pages")
    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Basic
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(description, book.description) &&
                Objects.equals(category, book.category) &&
                Objects.equals(cover, book.cover) &&
                Objects.equals(file, book.file) &&
                Objects.equals(year, book.year);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, author, description, year, category, cover, file);
    }
}
