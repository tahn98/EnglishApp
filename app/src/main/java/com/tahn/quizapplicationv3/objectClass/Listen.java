package com.tahn.quizapplicationv3.objectClass;

public class Listen {
    private String name;
    private int file;

    public Listen(String name, int file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getfile() {
        return file;
    }

    public void setfile(int file) {
        this.file = file;
    }
}
