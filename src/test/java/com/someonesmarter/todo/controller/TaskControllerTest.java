package com.someonesmarter.todo.controller;

import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.someonesmarter.todo.task.TaskController;
import com.someonesmarter.todo.task.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TaskController.class, secure = false)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void shouldReturnExpectedText() throws Exception {
        mockMvc.perform(get("/tasks/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/tasks/list-tasks"));
    }
}
