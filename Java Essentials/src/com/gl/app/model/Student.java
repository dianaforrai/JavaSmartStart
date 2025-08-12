package com.gl.app.model;

import java.util.*;

class Student {
    private String name;
    private Map<String, Double> coursesAndGrades;

    public Student(String name) {
        this.name = name;
        this.coursesAndGrades = new HashMap<>();
    }

    public void addCourseGrade(String course, double grade) {
        coursesAndGrades.put(course, grade);
    }

    // Method to calculate average grade for this student
    public double calculateAverageGrade() {
        if (coursesAndGrades.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        for (double grade : coursesAndGrades.values()) {
            total += grade;
        }
        return total / coursesAndGrades.size();
    }
}

class Classroom {
    private Set<Student> students;

    public Classroom() {
        this.students = new HashSet<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    // Method to calculate average grade for the classroom
    public double calculateAverageClassGrade() {
        if (students.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        int count = 0;
        for (Student student : students) {
            double avg = student.calculateAverageGrade();
            // Only include students who have grades
            if (avg > 0) {
                total += avg;
                count++;
            }
        }
        return count == 0 ? 0.0 : total / count;
    }
}

class School {
    private List<Classroom> classrooms;

    public School() {
        this.classrooms = new ArrayList<>();
    }

    public void addClassroom(Classroom classroom) {
        classrooms.add(classroom);
    }

    // Method to calculate average grade for the school
    public double calculateAverageSchoolGrade() {
        if (classrooms.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        int count = 0;
        for (Classroom classroom : classrooms) {
            double avg = classroom.calculateAverageClassGrade();
            // Only include classrooms that have grades
            if (avg > 0) {
                total += avg;
                count++;
            }
        }
        return count == 0 ? 0.0 : total / count;
    }
}
