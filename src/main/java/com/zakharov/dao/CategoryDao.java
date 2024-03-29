package com.zakharov.dao;

import com.zakharov.dmo.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CategoryDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Category category) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<Category> getCategoryList() {
        List<Category> categories = null;
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            TypedQuery<Category> q = session.createQuery("SELECT c FROM Category c", Category.class);
            categories = q.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return categories;
    }

    public Category getCategoryById(long id) {
        Category category = null;
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            category = session.get(Category.class, id);
            transaction.commit();
        } catch (Exception e) {

        }
        return category;
    }

    public void updateCategory(Category category) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(category);
            transaction.commit();
        } catch (Exception e) {

        }
    }

    public void removeCategory(long id) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(Category.class, id));
            transaction.commit();
        } catch (Exception e) {

        }
    }


}
