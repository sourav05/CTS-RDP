package com.fse.s1.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.s1.taskmanager.entity.ParentTask;
import com.fse.s1.taskmanager.repo.ParentTaskRepository;

@Service
public class ParentTaskService implements IParentTaskService{

	@Autowired
	private ParentTaskRepository parentTaskRepository;
	
	
	@Override
	public ParentTask getParentTask(long id){
		return parentTaskRepository.findById(id).orElse(new ParentTask());
	}

	@Override
	public boolean parentTaskExists(long id){
		return parentTaskRepository.existsById(id);
	}

	@Override
	public ParentTask addParentTask(ParentTask parentTask){
		return parentTaskRepository.save(parentTask);
	}
}
