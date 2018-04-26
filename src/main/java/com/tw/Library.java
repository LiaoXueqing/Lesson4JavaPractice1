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
            System.out.print(Constants.MAIN_MENU_MSG);
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
                System.out.print(Constants.ADD_STUDENT_INFO_MSG);
                processCmd(1);
                break;
            case 2:
                System.out.print(Constants.PRINT_REPORT_MSG);
                processCmd(2);
                break;
            case 3:
                return ;

        }
        return ;
    }
    public void processCmd(int status){
        String input = sc.nextLine();
        boolean flag;
        if(status ==1){
            flag = addStudent(input);
            while(!flag){
                input = sc.nextLine();
                flag = addStudent(input);
            }
        }
        if(status == 2){
            flag = printScores(input);
            while(!flag){
                input = sc.nextLine();
                flag = printScores(input);
            }
        }

    }

    /**
     * 添加学生
     */
    public boolean addStudent(String input) {
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
        if(stu==null){
            System.out.print(Constants.ADD_STUDENT_ERROR_MSG);
            return false;
        }else {
            student_list.add(stu);
            System.out.print("```\n" +
                    "学生" + stu.getName() + "的成绩被添加\n" +
                    "```\n");
            return true;
        }
    }

    /**
     * 生成成绩单
     */
    public boolean printScores(String input){
        List<StudentInfo> stus = new ArrayList<>();
        Matcher matcher = Pattern.compile(STU_NUM_REGREX).matcher(input);
        boolean isMatche = matcher.matches();
        if (!isMatche) {
            System.out.print(Constants.PRINT_REPORT_ERROR_MSG);
            return false;
        }
        List<String> list = Arrays.asList(input.trim().split(",")).stream()
                .map(num -> num.trim())
                .distinct().collect(Collectors.toList());
        for (String num: list) {
            if(getStudentByNum(num)!=null){
                stus.add(getStudentByNum(num));
            }
        }

        if(stus.isEmpty()){
            System.out.print(Constants.PRINT_REPORT_ERROR_MSG);
            return false;
        }else{

            System.out.print("```\n" +
                    "成绩单\n" +
                    "姓名|数学|语文|英语|编程|平均分|总分\n" +
                    "========================\n");

            for (StudentInfo stu : stus) {
                System.out.print(stu.getName() + "|"
                        + stu.getMathsScore() + "|"
                        + stu.getChineseScore() + "|"
                        + stu.getEnglishScore() + "|"
                        + stu.getProgramScore() + "|"
                        + stu.getAverage() + "|"
                        + stu.getTotal()+"\n");
            }

            System.out.print("========================\n" +
                    "全班总分平均数："+getClassAvg()+"\n" +
                    "全班总分中位数："+getClassMid()+"\n" +
                    "```\n");
            return true;
        }
    }
    public StudentInfo getStudentByNum(String num){
        for (StudentInfo stu:student_list) {
            if(stu.getNumber().equals(num)) return stu;
        }
        return null;
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
}
