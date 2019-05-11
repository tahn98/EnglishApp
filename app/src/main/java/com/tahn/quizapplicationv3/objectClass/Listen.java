package com.tahn.quizapplicationv3.objectClass;

public class Listen {
    private String name;
    private int fileAudio;
    private int fileScript;

    public Listen(String name, int fileAudio, int fileScript) {
        this.name = name;
        this.fileAudio = fileAudio;
        this.fileScript = fileScript;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFileAudio() {
        return fileAudio;
    }

    public void setFileAudio(int fileAudio) {
        this.fileAudio = fileAudio;
    }

    public int getFileScript() {
        return fileScript;
    }

    public void setFileScript(int fileScript) {
        this.fileScript = fileScript;
    }
}
