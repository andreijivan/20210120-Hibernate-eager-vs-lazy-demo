package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class EagerLazyDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			Instructor tempInstructor = session.get(Instructor.class, 1);
			System.out.println("luv2code: Instructor: " + tempInstructor);
			System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
			
			session.getTransaction().commit();
			session.close();
			System.out.println("\nluv2code: The session is now closed!\n");
			//How to get tempInstructor courses and bypass lazy loading.
			//option 1: call getter method while session is open (line 29)
			System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
			//System.out.println("luv2code: Instructor: " + tempInstructor);
			System.out.println("luv2code: Done!");
			
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
