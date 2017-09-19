package org.angelholm.init;

import org.angelholm.model.Gender;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Main {


    public static void main(String[] args) {


            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file


            SessionFactory factory = cfg.buildSessionFactory();


            Session session = factory.openSession();


            Transaction t = session.beginTransaction();

            Gender gender = new Gender();
            gender.setId(1);
            gender.setName("MALE");
            gender.setCode("M");

            session.persist(gender);//persisting the object

            t.commit();//transaction is commited
            session.close();

            System.out.println("successfully saved");



    }
}