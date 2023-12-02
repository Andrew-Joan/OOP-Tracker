package com.example.myapplication;

public class ExerciseRecord extends Record{
    public ExerciseRecord(String exerciseName, double time) {
        super(exerciseName, time);
    }
    @Override
    public String getDetails() {
        if (getTime() >= 120){
            return getExerciseName() + " - " + getTime() / 120 + " Hours";
        } else if (getTime() >= 60 && getTime() < 120) {
            return getExerciseName() + " - " + getTime() / 60 + " Hour";
        } else {
            return getExerciseName() + " - " + getTime() + " Minutes";
        }
    }
    @Override
    public String toString() {
        return getDetails();
    }
}
