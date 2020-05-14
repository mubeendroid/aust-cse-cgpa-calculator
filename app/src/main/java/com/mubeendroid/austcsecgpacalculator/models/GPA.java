package com.mubeendroid.austcsecgpacalculator.models;

public class GPA {

    int year, semester;
    double result;

    public GPA() {
    }

    public GPA(int year, int semester, double result) {
        this.year = year;
        this.semester = semester;
        this.result = result;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
