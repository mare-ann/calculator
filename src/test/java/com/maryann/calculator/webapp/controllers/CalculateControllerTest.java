package com.maryann.calculator.webapp.controllers;

import com.maryann.calculator.services.ExpressionTransformer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CalculateController.class)
public class CalculateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpressionTransformer trans;

    @Test
    public void controllerTest() throws Exception {
        String input = "27yroot3";
        Mockito.when(trans.transformIntoNumber(input)).thenReturn(3.0);

        MockHttpServletResponse responce = mockMvc.perform(get("/calculate")
                .param("q", input))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        assertEquals("3", responce.getContentAsString());
    }

    @Test
    public void controllerToException() throws Exception {
        String exception = "tan90";
        Mockito.when(trans.transformIntoNumber(exception)).thenThrow(new RuntimeException());

        MockHttpServletResponse responce = mockMvc.perform(get("/calculate")
                .param("q", exception))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        assertEquals("Error in expression", responce.getContentAsString());
    }

}