package com.rcoem;

import org.springframework.beans.factory.annotation.Autowired;

public class Emp {

    private Address address;

    public Emp() {
        super();
    }

    @Autowired
    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Emp [address=" + address + "]";
    }
}