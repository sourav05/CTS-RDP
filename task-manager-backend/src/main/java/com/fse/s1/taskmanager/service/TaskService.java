package com.fse.s1.taskmanager.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.s1.taskmanager.entity.Task;
import com.fse.s1.taskmanager.repo.TaskRepository;
import com.fse.s1.taskmanager.to.SearchCriteria;
import com.fse.s1.taskmanager.util.TaskUtil;

@Service
public class TaskService implements ITaskService{

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private IParentTaskService parentTaskService;

	@Override
	public Task getTask(long id){
		Task task = taskRepository.findById(id).orElse(new Task());
		return task;
	}

	@Override
	public List<Task> getFilteredTask(SearchCriteria criteria){
		criteria = TaskUtil.convertObjectFieldsFromEmptyToNull(criteria);
		List<Task> taskList = new LinkedList<>();
		boolean isNumber = true;
		try{
			Integer.parseInt(criteria.getTask());
		}catch(NumberFormatException e){
			isNumber = false;
		}
		
		if(!isNumber){
		taskRepository
		.findTasksByTaskAndParentTask(criteria.getTask(), 
									  criteria.getParentTask(), 
									  criteria.getPriorityFrom(),
									  criteria.getPriorityTo(),
									  criteria.getStartDate(),
									  criteria.getEndDate())
		.forEach(e -> {
						  taskList.add(e);
					  });
		}else{
			Task task = this.getTask(Long.parseLong(criteria.getTask()));
			if(task != null && task.getTaskId() != 0)
				taskList.add(task);
		}
		return taskList;
	}

	@Override
	public Task saveTask(Task taskDetails){
		if(parentExists(taskDetails)){
			Task td = taskRepository.save(taskDetails);
			return td;
		}
		return null;
	}

	@Override
	public Task updateTask(Task taskDetails){
		if(taskDetails.getTaskId() == 0L)
			return null;
		if(parentExists(taskDetails)){
			Task td = taskRepository.save(taskDetails);
			return td;
		}
		return null;
	}

	@Override
	public void deleteTask(long id){
		boolean exists = this.taskRepository.existsById(id);
		if(exists)
			this.taskRepository.deleteById(id);
	}
	
	private boolean parentExists(Task taskDetails){
		boolean parentExists = parentTaskService.parentTaskExists(taskDetails.getParent().getParentId());
		if(!parentExists && taskDetails.getParent() != null){
			parentTaskService.addParentTask(taskDetails.getParent());
			parentExists = true;
		}
		return parentExists;
	}
}
