package bg.tu.sofia.repository.custom.impl;

import bg.tu.sofia.filter.SearchBookFilter;
import bg.tu.sofia.model.Book;
import bg.tu.sofia.repository.custom.BookCustom;
import bg.tu.sofia.utils.SorterUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class BookCustomImpl extends ABaseCustom implements BookCustom {

    @Override
    public List<Book> findBooks(SearchBookFilter filter, Integer page, Integer pageSize) {
        TypedQuery<Book> query = em.createQuery(buildQuery(filter, false), Book.class);
        addQueryParamsForIpActions(filter, query);
        query.setMaxResults(pageSize);
        query.setFirstResult((page - 1) * pageSize);
        List<Book> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public int countBooks(SearchBookFilter filter) {
        TypedQuery<Long> countQuery = em.createQuery(buildQuery(filter, true), Long.class);
        addQueryParamsForIpActions(filter, countQuery);
        Long count = countQuery.getSingleResult();
        return count.intValue();
    }

    private String buildQuery(SearchBookFilter filter, boolean isCount) {
        StringBuilder queryBuilder = new StringBuilder("SELECT ").append(isCount ? " COUNT(l) " : " l ")
                .append(" FROM ").append(Book.class.getSimpleName()).append(" l ").append(" WHERE 1=1 ");

        if (filter != null) {
            if (!StringUtils.isEmpty(filter.getTitle()))
                queryBuilder.append(" AND (UPPER(l.title) LIKE UPPER(:title)) ");
            if (!StringUtils.isEmpty(filter.getAuthor()))
                queryBuilder.append(" AND (UPPER(l.author) LIKE UPPER(:author)) ");
            if (!StringUtils.isEmpty(filter.getYearFrom()))
                queryBuilder.append(" AND l.year >= :yearFrom ");
            if (!StringUtils.isEmpty(filter.getYearTo()))
                queryBuilder.append(" AND l.year <= :yearTo ");
            if (!StringUtils.isEmpty(filter.getCategory()))
                queryBuilder.append(" AND l.category.name = :category ");

            if (!StringUtils.isEmpty(filter.getSortColumn()) && !StringUtils.isEmpty(filter.getSortOrder()) && !isCount) {
                String[] columns = SorterUtils.bookSorterColumnMap().get(filter.getSortColumn()).split(",");
                String order = String.join(" " + filter.getSortOrder() + " , ", columns) + " " + filter.getSortOrder();
                queryBuilder.append(" ORDER BY ").append(order);
            }

        }
        return queryBuilder.toString();

    }


    private void addQueryParamsForIpActions(SearchBookFilter filter, Query query) {

        if (filter != null) {
            if (!StringUtils.isEmpty(filter.getTitle()))
                query.setParameter("title", "%" + filter.getTitle() + "%");
            if (!StringUtils.isEmpty(filter.getAuthor()))
                query.setParameter("author", "%" + filter.getAuthor() + "%");
            if (!StringUtils.isEmpty(filter.getYearFrom()))
                query.setParameter("yearFrom", filter.getYearFrom());
            if (!StringUtils.isEmpty(filter.getYearTo()))
                query.setParameter("yearTo", filter.getYearTo());
            if (!StringUtils.isEmpty(filter.getCategory()))
                query.setParameter("category", filter.getCategory());

        }
    }


}
