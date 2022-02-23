package com.company;
import com.thoughtworks.xstream.annotations.XStreamAlias;


public class University {
    public String name;
    public String postcode;

    public University(String uniName, String uniPostcode) {
        this.name = uniName;
        this.postcode = uniPostcode;
    }
}
