package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTest {

    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Library.student_list.clear();
    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void testMockClass() throws Exception {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        String value = "first";
        when(mockedList.get(0)).thenReturn(value);

        assertEquals(mockedList.get(0), value);

    }

    @Test
    public void testAddStudentRightResults() {
        String s1 = "张, 321, 数学：90, 语文：88, 英语：86, 编程：88";
        Library classLibraryTest = new Library();

        assertTrue(classLibraryTest.addStudent(s1));
        assertTrue(systemOut().endsWith("```\n" + "学生张的成绩被添加\n" + "```\n"));

    }

    @Test
    public void testAddStudentErrorResults(){
        String s1 = "张, 321, 数学：90";
        Library classLibraryTest = new Library();

        assertFalse(classLibraryTest.addStudent(s1));
        assertTrue(systemOut().endsWith("```\n" + "请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：\n" + "```\n"));
    }

    @Test
    public void testPrintScoresRightResults() {
        //张, 321, 数学：90, 语文：88, 英语：86, 编程：88
        //王, 322, 数学：78, 语文：90, 英语：86, 编程：96
        StudentInfo stu1 = new StudentInfo("张","321",90,88,86,88);
        StudentInfo stu2 = new StudentInfo("王","322",78,90,86,96);
        Library.student_list.add(stu1);
        Library.student_list.add(stu2);

        String input = "321,322";
        Library classLibraryTest = new Library();
        assertTrue(classLibraryTest.printScores(input));
        assertTrue(systemOut().endsWith("```\n" + "成绩单\n" + "姓名|数学|语文|英语|编程|平均分|总分\n" + "========================\n张|90|88|86|88|88|352\n" + "王|78|90|86|96|87|350\n" + "========================\n" + "全班总分平均数：351.0\n" + "全班总分中位数：351.0\n" + "```\n"));
      }

    @Test
    public void testPrintScoresErrorResults(){
        String input = "学号：321,学号：322";
        Library classLibraryTest = new Library();
        assertFalse(classLibraryTest.printScores(input));
        assertTrue(systemOut().endsWith("```\n" + "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n" + "```\n"));
    }
}
