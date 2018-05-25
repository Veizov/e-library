package bg.tu.sofia.filter;

import java.util.Date;

/**
 * Created by denislav.veizov on 2.2.2018 г..
 */
public class SearchBookFilter {

    private String title;
    private String author;
    private Short yearFrom;
    private Short yearTo;
    private String description;
    private String category;
    private String sortColumn;
    private String sortOrder;
    private Date dateFrom;
    private Date dateTo;
    private String language;
    private String isbn;
    private Integer numberOfPagesFrom;
    private Integer numberOfPagesTo;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Short getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(Short yearFrom) {
        this.yearFrom = yearFrom;
    }

    public Short getYearTo() {
        return yearTo;
    }

    public void setYearTo(Short yearTo) {
        this.yearTo = yearTo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNumberOfPagesFrom() {
        return numberOfPagesFrom;
    }

    public void setNumberOfPagesFrom(Integer numberOfPagesFrom) {
        this.numberOfPagesFrom = numberOfPagesFrom;
    }

    public Integer getNumberOfPagesTo() {
        return numberOfPagesTo;
    }

    public void setNumberOfPagesTo(Integer numberOfPagesTo) {
        this.numberOfPagesTo = numberOfPagesTo;
    }
}
