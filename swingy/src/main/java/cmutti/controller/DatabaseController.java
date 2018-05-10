package cmutti.controller;

import cmutti.model.heroes.AHero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseController
{
	public static SessionFactory sessionFactory = null;
	static {
		if (sessionFactory == null) {
			java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); // Disable this line if you want to see all hibernate logs
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
	}

	public static ArrayList<AHero> retrieveHeroes(final boolean printToConsole) {
		if (printToConsole)
			System.out.println("Retrieving saved Heroes..");

		ArrayList<AHero> savedHeroes = new ArrayList<AHero>();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List result = session.createQuery("from AHero").list();
		if (result.size() > 0) {
			if (printToConsole)
				System.out.println("Found " + result.size() + " Heroes !");
			for (AHero hero : (List<AHero>) result) {
				savedHeroes.add(hero);
			}
		}
		else if (printToConsole) {
			System.out.println("No Heroes found");
		}
		session.getTransaction().commit();
		session.close();

		return savedHeroes;
	}

	public static void saveHero(final AHero hero, final boolean printToConsole) {
		Thread saveThread = new Thread(new Runnable() {
			public void run() {
				if (printToConsole)
					System.out.println("Saving Hero data..");

				Session session = DatabaseController.sessionFactory.openSession();
				session.beginTransaction();
				session.saveOrUpdate(hero);
				session.getTransaction().commit();
				session.close();

				if (printToConsole)
					System.out.println("Saving completed !");
				System.exit(0);
			}
		});
		saveThread.start();
	}
}
