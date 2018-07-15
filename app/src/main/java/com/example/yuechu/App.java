package com.example.yuechu;

import android.app.Application;


import com.example.yuechu.data.Person;

public class App extends Application {
    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
