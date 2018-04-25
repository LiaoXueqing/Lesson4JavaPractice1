package com.tw;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTest {

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
        StudentInfo stu1 = new StudentInfo("张","321",90,88,86,88);
        Library classUnderTest = new Library();
        StudentInfo stu2 = classUnderTest.formatStudentInfo(s1);
        assertEquals(stu1.getNumber(),stu2.getNumber());
    }
    @Test
    public void testAddStudentErrorResults(){
        String s1 = "张, 321, 数学：90";
        Library classUnderTest = new Library();
        StudentInfo stu = classUnderTest.formatStudentInfo(s1);
        assertThat(stu, equalTo(null));
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
        Library classUnderTest = new Library();
        List<StudentInfo> studentInfos = classUnderTest.formatStudentNos(input);
        assertEquals(2,studentInfos.size());
    }

    @Test
    public void testPrintScoresErrorResults(){
        String input = "学号：321,学号：322";
        Library classUnderTest = new Library();
        List<StudentInfo> studentInfos = classUnderTest.formatStudentNos(input);
        assertTrue(studentInfos.isEmpty());
    }

}
