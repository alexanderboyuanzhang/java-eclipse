package com.list.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class SortStudent implements Comparator<Student> {

	public int compare(Student a, Student b) {
		if (a.getCgpa() != b.getCgpa()) {
			if (b.getCgpa() > a.getCgpa())
				return 1;
			else
				return -1; // descending order
		} else if (a.getFname().equals(b.getFname()) == false)
			return a.getFname().compareTo(b.getFname());
		else
			return a.getId() - b.getId();
	}
}

class Student {
	private int id;
	private String fname;
	private double cgpa;

	public Student(int id, String fname, double cgpa) {
		super();
		this.id = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}

	public int getId() {
		return id;
	}

	public String getFname() {
		return fname;
	}

	public double getCgpa() {
		return cgpa;
	}
	public String toString() {
		return String.valueOf(this.id) + " " + this.fname + " " + String.valueOf(this.cgpa);
	}
}

// Driver class 
class ComparatorTest {
	public static void main(String[] args) {
		ArrayList<Student> ar = new ArrayList<Student>();
		ar.add(new Student(111, "bbbb", 3.88));
		ar.add(new Student(131, "aaaa", 3.55));
		ar.add(new Student(121, "cccc", 6.8));

		System.out.println("Unsorted");
		for (int i = 0; i < ar.size(); i++)
			System.out.println(ar.get(i));

		Collections.sort(ar, new SortStudent());

		System.out.println("\nSorted by rollno");
		for (int i = 0; i < ar.size(); i++)
			System.out.println(ar.get(i));
	}
}