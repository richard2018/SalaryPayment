package mockito;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MockitoDemo {
    /**
     * 模拟接口、模拟类
     */
    @Test
    public void createMockObj() {
        List mockedList = mock(List.class);
        assertTrue(mockedList instanceof List);

        ArrayList mockedArrayList = mock(ArrayList.class);
        assertTrue(mockedArrayList instanceof List);
        assertTrue(mockedArrayList instanceof ArrayList);
    }

    /**
     * 使用when() then()定制mock对象的行为
     */
    @Test
    public void configMockObj() {
        List mockedList = mock(List.class);
        when(mockedList.add("one")).thenReturn(true);
        when(mockedList.size()).thenReturn(1);
        assertTrue(mockedList.add("one"));
        assertFalse(mockedList.add("two"));
        assertTrue(mockedList.size() == 1);

        Iterator iterator = mock(Iterator.class);
        when(iterator.next()).thenReturn("Hello,").thenReturn("Mockito!");
        String result = iterator.next() + " " + iterator.next();
        assertTrue("Hello, Mockito!".equals(result));
    }

    /**
     * 模拟异常
     */
    @Test(expected = NoSuchElementException.class)
    public void testForException() {
        Iterator iterator = mock(Iterator.class);
        when(iterator.next()).thenReturn("Hello,").thenReturn("Mockito!");
        String result = iterator.next() + " " + iterator.next();
        assertTrue("Hello, Mockito!".equals(result));

        // 以下为两种写法，写法2更容易理解
        // 写法1:
        // doThrow(new NoSuchElementException()).when(iterator.next());
        // 写法2:
        when(iterator.next()).thenThrow(new NoSuchElementException());

        iterator.next();
    }

    /**
     * 校验mock对象的方法调用
     */
    @Test
    public void testVerify() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.add("two");
        mockedList.add("three");
        mockedList.add("three");
        mockedList.add("three");

        when(mockedList.size()).thenReturn(5);
        assertEquals(5, mockedList.size());
        verify(mockedList, atLeastOnce()).add("one");
        verify(mockedList, times(1)).add("two");
        verify(mockedList, times(3)).add("three");
        verify(mockedList, never()).isEmpty();
    }

    /**
     * 使用spy包装真实对象
     */
    @Test
    public void testSpy() {
        List list = new LinkedList();
        List spyList = spy(list);

        when(spyList.size()).thenReturn(100);
        spyList.add("one");
        spyList.add("two");
        assertEquals("one", spyList.get(0));
        assertEquals("two", spyList.get(1));
        assertEquals(100, spyList.size());
    }

    /**
     * 参数捕获
     */
    @Test
    public void testArgsCapture() {
        List<String> list = asList("1", "2");
        List mockedList = mock(List.class);
        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);
        mockedList.addAll(list);

        verify(mockedList).addAll(argumentCaptor.capture());
        assertEquals(2, argumentCaptor.getValue().size());
        assertEquals(list, argumentCaptor.getValue());
    }

    @Test
    public void argumentMatchersTest() {
        List<String> mockList = mock(List.class);
        when(mockList.get(anyInt())).thenReturn("Hello").thenReturn("World");
        String result = mockList.get(100) + " " + mockList.get(200);

        verify(mockList, times(2)).get(anyInt());
        assertEquals("Hello World", result);
    }

    @Test
    public void argumentMatchersTest2(){
        Map mapMock = mock(Map.class);
        when(mapMock.put(anyInt(), anyString())).thenReturn("world");
        mapMock.put(1, "hello");

        verify(mapMock).put(anyInt(), eq("hello"));
    }

    /**
     * 演示自定义ArgumentMatcher
     */
    @Test
    public void argumentMatchersTest3(){
        List mock = mock(List.class);
        when(mock.addAll(argThat(new IsListOfTwoElements()))).thenReturn(true);

        mock.addAll(Arrays.asList("one", "two"));
        verify(mock).addAll(argThat(new IsListOfTwoElements()));
    }
}
