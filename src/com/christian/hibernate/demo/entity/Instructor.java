package com.christian.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//annotate the class as an entirty and map to db table
@Entity
@Table(name="instructor")
public class Instructor {
	//define the fields
	//annotate the fields with db column names
	
	//define the fields
		//annotate the fields with db column names
		@Id
		@GeneratedValue (strategy=GenerationType.IDENTITY)
		@Column (name="id")
		private int id;
		
		
		@Column(name="first_name")
		private String firstName;
		
		@Column(name="last_name")
		private String lastName;
		
		
		@Column(name="email")
		private String email;
		
		//setup mapping between instructor and instructor detail
		@OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="instructor_detail_id")
		private InstructorDetail instructorDetail;
		
		
		@OneToMany(fetch= FetchType.LAZY,mappedBy="instructor",
				cascade= {CascadeType.DETACH, CascadeType.MERGE,
						CascadeType.PERSIST, CascadeType.REFRESH})
		private List<Course> courses;
	
	
	//create constructors
		public Instructor() {
			
		}


		public Instructor(String firstName, String lastName, String email) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
		}

		//generate getter/setter
		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getFirstName() {
			return firstName;
		}


		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}


		public String getLastName() {
			return lastName;
		}


		public void setLastName(String lastName) {
			this.lastName = lastName;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public InstructorDetail getInstructorDetail() {
			return instructorDetail;
		}


		public void setInstructorDetail(InstructorDetail instructorDetail) {
			this.instructorDetail = instructorDetail;
		}
		
		
		
		public List<Course> getCourses() {
			return courses;
		}


		public void setCourses(List<Course> courses) {
			this.courses = courses;
		}
		
		
		//add convience methods for bi-directionsal relationship
		public void add(Course tempCourse) {
			if(courses==null) {
				courses= new ArrayList<>();
			}
			
			courses.add(tempCourse);
			tempCourse.setInstructor(this);
		}
	


		//generate toString
		@Override
		public String toString() {
			
			return "Instructor: [id= " + id+ ", first_name: "+ firstName+ 
					" ,last_name: "+ lastName+ ", email: "+ email+ ", instructordetail: "+ instructorDetail+"]";
		}
}
