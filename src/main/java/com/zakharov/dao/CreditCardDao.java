package com.zakharov.dao;

import com.zakharov.dmo.CreditCard;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CreditCardDao {
    SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(CreditCard card) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(card);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public CreditCard getCardById(long id) {
        Transaction transaction = null;
        CreditCard creditCard = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            creditCard = session.get(CreditCard.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return creditCard;
    }

    public List<CreditCard> getCreditCardsList() {
        List<CreditCard> cards = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            TypedQuery<CreditCard> q = session.createQuery("SELECT card FROM CreditCard card");
            cards = q.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return cards;
    }

    public void deleteCreditCard(long id) {
        Transaction transaction = null;
        CreditCard creditCard = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            creditCard = session.get(CreditCard.class, id);
            session.delete(creditCard);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void updateCreditCard(CreditCard card) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(card);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
