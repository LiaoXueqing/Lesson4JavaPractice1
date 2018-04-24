package com.tw;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class LibraryTest {
    @Mock
    private StudentInfo stu;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(stu);
    }

    @Test
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

/*    @Test
    public void testMockClass() throws Exception {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        String value = "first";
        when(mockedList.get(0)).thenReturn(value);

        assertEquals(mockedList.get(0), value);

    }*/
    @Test
    public void testAddStudent() {
        when(stu.getNumber()).thenReturn("321");
        assertEquals(stu.getNumber(),"321");
    }
    @Test
    public void testPrintScores() {

    }

}
