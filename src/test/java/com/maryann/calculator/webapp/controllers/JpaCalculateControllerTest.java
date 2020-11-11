package com.maryann.calculator.webapp.controllers;

import com.maryann.calculator.db.jpa.JpaLog;
import com.maryann.calculator.db.jpa.JpaLogUtils;
import com.maryann.calculator.services.ExpressionTransformer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JpaCalculateController.class})
@WebMvcTest(controllers = JpaCalculateController.class)
public class JpaCalculateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpressionTransformer trans;

    @MockBean
    private JpaLogUtils dbLogsUtils;

    @Test
    public void controllerTest() throws Exception {
        String input = "27yroot3";
        Mockito.when(trans.transformIntoNumber(input)).thenReturn(3.0);
        Mockito.when(dbLogsUtils.save(any(JpaLog.class))).thenReturn(null);

        MockHttpServletResponse responce = mockMvc.perform(get("/jpa/calculate")
                .param("q", input))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        assertEquals("3", responce.getContentAsString());
    }

    @Test
    public void controllerToException() throws Exception {
        String exception = "tan90";
        Mockito.when(trans.transformIntoNumber(exception)).thenThrow(new RuntimeException());
        Mockito.when(dbLogsUtils.save(any(JpaLog.class))).thenReturn(null);

        MockHttpServletResponse responce = mockMvc.perform(get("/jpa/calculate")
                .param("q", exception))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        assertEquals("Error in expression", responce.getContentAsString());
    }

}