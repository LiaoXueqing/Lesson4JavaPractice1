package com.tw;

public class StudentInfo {
    private String name;
    private String number;
    private int mathsScore;
    private int chineseScore;
    private int englishScore;
    private int programScore;

    public StudentInfo(String name, String number, int mathsScore, int chineseScore, int englishScore, int programScore) {

        this.name = name;
        this.number = number;
        this.mathsScore = mathsScore;
        this.chineseScore = chineseScore;
        this.englishScore = englishScore;
        this.programScore = programScore;
    }

    public StudentInfo(String num) {
        this.number = num;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getMathsScore() {
        return mathsScore;
    }

    public int getChineseScore() {
        return chineseScore;
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public int getProgramScore() {
        return programScore;
    }

    public int getAverage(){
        return  (this.getChineseScore()+this.getEnglishScore()+this.getProgramScore()+this.getMathsScore())/4;
    }

    public int getTotal(){
        return  this.getChineseScore()+this.getEnglishScore()+this.getProgramScore()+this.getMathsScore();
    }
}
