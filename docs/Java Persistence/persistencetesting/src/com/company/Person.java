package com.company;
import com.thoughtworks.xstream.annotations.XStreamAlias;


public class Person {
    public String firstname;
    public String lastname;
    public int age;
    public University uni;


    public Person(String name, String lname, int lAge, University lUni) {
        this.firstname = name;
        this.lastname = lname;
        this.age = lAge;
        this.uni = lUni;
    }
}
