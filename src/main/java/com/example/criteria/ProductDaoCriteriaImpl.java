package com.example.criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.simple.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductDaoCriteriaImpl implements ProductDaoCriteria {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void saveProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(product);
        tx.commit();
        session.close();
    }

    
    @Override
    public Map<Integer, Long> getProductsGroupedByYear() {
        Session session = this.sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
        Root<Product> root = criteriaQuery.from(Product.class);

        // Group by manufacturingYear and count the number of products for each year
        criteriaQuery.multiselect(root.get("manufacturingYear"), builder.count(root.get("productId")));
        criteriaQuery.groupBy(root.get("manufacturingYear"));

        Query<Object[]> query = session.createQuery(criteriaQuery);
        List<Object[]> result = query.getResultList();

        session.close();

        // Convert the result list into a Map<Integer, Long> with year as key and count as value
        return result.stream()
                .collect(Collectors.toMap(
                        objArray -> (Integer) objArray[0], // Year
                        objArray -> (Long) objArray[1]     // Count
                ));
    }

    @Override
    public List<Product> getAllProductsOrderedByName() {
        Session session = this.sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        // Order products by name in ascending order
        criteriaQuery.orderBy(builder.asc(root.get("name")));

        Query<Product> query = session.createQuery(criteriaQuery);
        List<Product> products = query.getResultList();

        session.close();
        return products;
    }

}
