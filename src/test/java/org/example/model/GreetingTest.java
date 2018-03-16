package org.example.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class GreetingTest {

    @Test
    public void test_Greeting_getText(){
        Greeting gr = new Greeting();
        String str = gr.getText();
        assertEquals("string was not null", null, str);
    }

    @Test
    public void test_Greeting_setTextToNull(){
        String text = null;
        Greeting gr = new Greeting();
        gr.setText(text);

        assertEquals("text was not null", text, gr.getText());
    }
    @Test
    public void test_Greeting_setTextToEmptyString(){
        String text = "";
        Greeting gr = new Greeting();
        gr.setText(text);

        assertEquals("text was not empty string", text, gr.getText());
    }
    @Test
    public void test_Greeting_setText(){
        String text = "Hello World";
        Greeting gr = new Greeting();
        gr.setText(text);

        assertEquals("text was Hello World", text, gr.getText());
    }
    @Test(expected = NullPointerException.class)
    public void test_Greeting_getId(){
        Greeting gr = new Greeting();
        long id = gr.getId();
    }
    @Test
    public void test_Greeting_setIdToNull(){
        Greeting gr = new Greeting();
        gr.setId(null);
        assertEquals("id was not null", null, gr.getId());
    }
    @Test
    public void test_Greeting_setIdToNegative(){
        Long id = -1L;
        Greeting gr = new Greeting();
        gr.setId(id);
        assertEquals("id was not null", id, gr.getId());
    }
    @Test
    public void test_Greeting_setIdToPositive(){
        Long id = 1L;
        Greeting gr = new Greeting();
        gr.setId(id);
        assertEquals("id was not 1", id, gr.getId());
    }


}