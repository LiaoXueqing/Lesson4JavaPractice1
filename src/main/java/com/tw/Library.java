package com.tw;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Library {
    private static final String STU_REGREX = "([^, ]+), (\\w+), 数学：(\\d+), 语文：(\\d+), 英语：(\\d+), 编程：(\\d+)";
    private static final String STU_NUM_REGREX = "^(\\d+[\\s]*+[,]+[\\s]*)*(\\d+)$";
    /**
     * 存储输入的学生信息
     */
    static List<StudentInfo> student_list = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public boolean someLibraryMethod() {
        return true;
    }

    public static void main(String[] args){
        new Library().master();
    }

    public void master(){
        int chooseId = 3;
        do {
            System.out.println(Constants.MAIN_MENU_MSG);
            chooseId = Integer.parseInt(sc.nextLine());
            process(chooseId);
        }while(chooseId != 3);
    }

    /**
     * 根据输入1，2，3进行不同的处理
     * @param chooseId
     */
    public void process(int chooseId){
        switch (chooseId){
            case 1:
                System.out.println(Constants.ADD_STUDENT_INFO_MSG);
                addStudent();
                break;
            case 2:
                System.out.println(Constants.PRINT_REPORT_MSG);
                printScores();
                break;
            case 3:
                return ;

        }
        return ;
    }

    /**
     * 添加学生
     */
    public void addStudent(){
        String input = sc.nextLine();
        StudentInfo stu = formatStudentInfo(input);
        while(stu==null){
            System.out.println(Constants.ADD_STUDENT_ERROR_MSG);
            input = sc.nextLine();
            stu = formatStudentInfo(input);
        }
        student_list.add(stu);
        System.out.println("```\n" +
                    "学生"+stu.getName()+"的成绩被添加\n" +
                    "```");

        return ;
    }

    public StudentInfo formatStudentInfo(String input) {
        StudentInfo stu = null;
        Matcher matcher = Pattern.compile(STU_REGREX).matcher(input);
        if (matcher.matches()) {
            stu = new StudentInfo(matcher.group(1),
                    matcher.group(2),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)),
                    Integer.parseInt(matcher.group(5)),
                    Integer.parseInt(matcher.group(6)));
        }
        return stu;
    }

    /**
     * 生成成绩单
     */
    public void printScores(){

        String input = sc.nextLine();
        List<StudentInfo> studentInfos = formatStudentNos(input);
        while(studentInfos.isEmpty()){
            System.out.println(Constants.PRINT_REPORT_ERROR_MSG);
            input = sc.nextLine();
            studentInfos = formatStudentNos(input);
        }

        System.out.println("```\n" +
                "成绩单\n" +
                "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================");

        for (StudentInfo stu : studentInfos) {
            System.out.println(stu.getName() + "|"
                    + stu.getMathsScore() + "|"
                    + stu.getChineseScore() + "|"
                    + stu.getEnglishScore() + "|"
                    + stu.getProgramScore() + "|"
                    + stu.getAverage() + "|"
                    + stu.getTotal() );
        }

        System.out.println("========================\n" +
                "全班总分平均数："+getClassAvg()+"\n" +
                "全班总分中位数："+getClassMid()+"\n" +
                "```");

    }
    public double getClassAvg(){
        return student_list.stream().mapToInt(StudentInfo::getTotal).average().orElse(0);
    }
    public double getClassMid(){
        double classMid = 0;
        int size = student_list.size();
        if (size != 0) {
            if (size % 2 == 0) {
                classMid = student_list.stream().sorted(Comparator.comparing(StudentInfo::getTotal)).skip(size / 2 - 1).limit(2).mapToInt(StudentInfo::getTotal).average().getAsDouble();
            } else {
                classMid = student_list.stream().sorted(Comparator.comparing(StudentInfo::getTotal)).skip(size / 2).limit(1).mapToInt(StudentInfo::getTotal).sum();
            }
        }
        return classMid;
    }
    public List<StudentInfo> formatStudentNos(String input){
        List<StudentInfo> stus = new ArrayList<>();
        Matcher matcher = Pattern.compile(STU_NUM_REGREX).matcher(input);
        boolean isMatche = matcher.matches();

        if (!isMatche) {
            return stus;
        }
        List<String> list = Arrays.asList(input.trim().split(",")).stream()
                .map(num -> num.trim())
                .distinct().collect(Collectors.toList());
        for (String num: list) {
            if(getStudentByNum(num)!=null){
                stus.add(getStudentByNum(num));
            }
        }
        return stus;
    }
    public StudentInfo getStudentByNum(String num){
        for (StudentInfo stu:student_list) {
            if(stu.getNumber().equals(num)) return stu;
        }
        return null;
    }
}
