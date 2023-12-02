package com.example.myapplication;

public abstract class Record {
    private String exerciseName;
    private double time;

    public Record(String exerciseName, double time) {
        this.exerciseName = exerciseName;
        this.time = time;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time= time;
    }

    public abstract String getDetails();
}

