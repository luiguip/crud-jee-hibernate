package com.luigui.jpa_example.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.luigui.jpa_example.model.Company;

@ApplicationScoped
public class CompanyRepository {

	private SessionFactory sessionFactory;

	private Session openSession() {
		if(sessionFactory == null) {
			Configuration configuration;
			configuration = new Configuration().configure();
			configuration.addAnnotatedClass(Company.class);
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder.applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());
		}
		return sessionFactory.openSession();
	}
	
	public Company insertCompany(Company company) {
		Session session = openSession();
		session.getTransaction().begin();
		session.persist(company);
		session.getTransaction().commit();
		session.close();
		return company;
	}
	
	
	public Company deleteCompany(Long id) {
		Session session = openSession();
		Company company = (Company) session.get(Company.class , id);
		session.getTransaction().begin();
		session.delete(company);
		session.getTransaction().commit();
		session.close();
		return company;
	}
	
	public Company updateCompany(Company company) {
		Session session = openSession();
		session.getTransaction().begin();
		session.merge(company);
		session.getTransaction().commit();
		session.close();
		return company;
	}
	
	public Company getCompany(Long id) {
		Session session = openSession();
		
		Company company = (Company) session.get(Company.class , id);
		session.close();
		return company;
	}
	
	public List<Company> getAll(){
		Session session = openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = criteriaBuilder.createQuery(Company.class);
		criteria.from(Company.class);
		List<Company> companies = session.createQuery(criteria).getResultList();
		session.close();
		return companies;
	}
	
	
}
