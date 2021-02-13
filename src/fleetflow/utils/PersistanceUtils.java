/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fleetflow.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author henry
 */
public class PersistanceUtils {

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public java.sql.Date parseDate(String date) {
        try {
            return (java.sql.Date) new Date(DATE_FORMAT.parse(date).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public java.sql.Timestamp parseTimestamp(String timestamp) {
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void store(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FleetFlowPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        em.close();
        emf.close();
    }

    public void clearTable(String table) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FleetFlowPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Query drop;
            drop = em.createQuery("DELETE FROM "  + table);
            drop.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        em.close();
        emf.close();
    }
}
