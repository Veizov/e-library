package bg.tu.sofia.filter;

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
}
