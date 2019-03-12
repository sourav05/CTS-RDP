package com.fse.s1.TaskManager;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.s1.taskmanager.TaskManagerApplication;
import com.fse.s1.taskmanager.controller.TaskController;
import com.fse.s1.taskmanager.entity.Task;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes ={TaskManagerApplication.class})
public class TaskManagerApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private TaskController controller;
	private Task task;
	//	
	//	@MockBean
	//	private TaskService taskService;

	//	@MockBean
	//	private TaskRepository repository;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		task = new Task();
		task.setTaskId(999);
		task.setTask("Test-Task");
		task.setParent(null);
		task.setPriority(19);
		task.setStartDate(new Date(System.currentTimeMillis()));
		task.setEndDate(new Date(System.currentTimeMillis()+2592000000L));
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void addTask() throws Exception{
		RequestBuilder builder = MockMvcRequestBuilders.post("/api/task/add").content(task.toJson());
		MvcResult result = mockMvc.perform(builder).andReturn();
		Task task = null;
		try{
		task = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Task.class);
		}catch(Exception e ){
			System.out.println("************ ERROR *******************");
		}

		//andReturn().getResponse().getContentAsString().
		System.out.println("******************* " + task);
	}
	
	public void checkById() throws Exception{
		RequestBuilder builder = MockMvcRequestBuilders.get("/api/task/102");
		MvcResult result = mockMvc.perform(builder).andReturn();
		Task task = null;
		try{
		task = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Task.class);
		}catch(Exception e ){
			System.out.println("************ ERROR *******************");
		}

		//andReturn().getResponse().getContentAsString().
		System.out.println("******************* " + task);
	}

}
