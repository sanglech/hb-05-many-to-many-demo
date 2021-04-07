package com.christian.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.christian.hibernate.demo.entity.Course;
import com.christian.hibernate.demo.entity.Instructor;
import com.christian.hibernate.demo.entity.InstructorDetail;
import com.christian.hibernate.demo.entity.Review;
import com.christian.hibernate.demo.entity.Student;

public class GetCoursesForStudentDemo {

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
			
			//get the student from db
			
			int studentId=2;
			
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("Courses for student: "+ tempStudent.getCourses());
			
			
			//comit txn
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
		}finally {
			session.close();
			factory.close();
		}
	}

}
