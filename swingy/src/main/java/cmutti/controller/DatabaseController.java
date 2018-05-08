package cmutti.controller;

import cmutti.model.heroes.AHero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseController
{
	public static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public static ArrayList<AHero> retrieveHeroes() {

		ArrayList<AHero> savedHeroes = new ArrayList<AHero>();

		return savedHeroes;
	}

	public static void saveHero(final AHero hero) {
		Thread saveThread = new Thread(new Runnable() {
			public void run() {
				Session session = DatabaseController.sessionFactory.openSession();

				session.beginTransaction();
				session.save(hero);
				session.getTransaction().commit();
				session.close();

				// now lets pull events from the database and list them
				session = sessionFactory.openSession();
				session.beginTransaction();
				List result = session.createQuery("from AHero").list();
				if (result.size() > 0) {
					System.out.println("Saved Heroes (" + result.size() + ") :");
					for (AHero event : (List<AHero>) result) {
						System.out.println(event.getName());
					}
				}
				else {
					System.out.println("No heroes saved");
				}
				session.getTransaction().commit();
				session.close();


				System.exit(0);
			}
		});
		saveThread.start();
	}
}
