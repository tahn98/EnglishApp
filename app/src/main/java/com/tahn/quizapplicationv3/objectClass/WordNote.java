package com.tahn.quizapplicationv3.objectClass;

public class WordNote {
    private String word;
    private String desc;

    public WordNote(String word, String desc) {
        this.word = word;
        this.desc = desc;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
