package com.christian.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.christian.hibernate.demo.entity.Course;
import com.christian.hibernate.demo.entity.Instructor;
import com.christian.hibernate.demo.entity.InstructorDetail;
import com.christian.hibernate.demo.entity.Review;
import com.christian.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		
		///create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create session
		
		Session session= factory.getCurrentSession();
		
		try {
			
			//start txn
			session.beginTransaction();
			
			//create a course
			Course tempCourse1= new Course("How to MonsterHunter");
			//Course tempCourse2= new Course("How to Game");
			
			//save the course
			System.out.println("Saving course");
			session.save(tempCourse1);
			
			//create the students
			
			Student tempStudent1 = new Student("John","Doe","john@doe.com");
			Student tempStudent2 = new Student("Mary","sue","mary@sue.com");
			Student tempStudent3 = new Student("C","note","c@note.com");
			
			//add students
			
			tempCourse1.addStudent(tempStudent1);
			tempCourse1.addStudent(tempStudent2);
			tempCourse1.addStudent(tempStudent3);
			
			
			//tempCourse2.addStudent(tempStudent1);
			//tempCourse2.addStudent(tempStudent2);
			
			
			
			//save students to the course
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			//session.save(tempCourse2);
			System.out.println("Saving students");
			
			//comit txn
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
		}finally {
			session.close();
			factory.close();
		}
	}

}
