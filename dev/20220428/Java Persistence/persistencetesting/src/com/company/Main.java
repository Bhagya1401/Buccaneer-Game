package com.company;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;


import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        // create a university
        University aber = new University("Aberystwyth University", "SY233FL");

        // create a new instance of the Person class
        Person student = new Person("Ash", "Bagnall", 21, aber);

        // convert to XML
        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);

        // give the class an alias
        xstream.alias("person", Person.class);
        xstream.alias("university", University.class);

        // convert to XML
        String xml = xstream.toXML(student);

        // print out XML
        System.out.println(xml);


        // write XML to a file
        try {
            FileWriter myWriter = new FileWriter("persons.txt");
            myWriter.write(xml);
            myWriter.close();
            System.out.println("Successfully wrote XML to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }






        // Read the XML and convert it into objects
        String xmlConvert = Files.readString(Paths.get("persons.txt"));

        // convert xml to Java Object
        Person newPerson = (Person)xstream.fromXML(xmlConvert);

        // Print the new XML we've just converted
        System.out.println(
        "Firstname: " + newPerson.firstname +
            " | Lastname: " + newPerson.lastname +
                " | Age: " + newPerson.age +
                    " | University Name: " + newPerson.uni.name +
                        " | University Postcode: " + newPerson.uni.postcode
        );



    }
}


