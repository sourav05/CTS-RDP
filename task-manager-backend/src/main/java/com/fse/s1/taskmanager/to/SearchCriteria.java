package com.fse.s1.taskmanager.to;

import java.io.Serializable;

public class SearchCriteria implements Serializable{

	private static final long serialVersionUID = 7400328091014684906L;
	
	private String task;
	private int parentTask;
	private int priorityFrom;
	private int priorityTo;
	private String startDate;
	private String endDate;

	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public int getParentTask() {
		return parentTask;
	}
	public void setParentTask(int parentTask) {
		this.parentTask = parentTask;
	}
	public int getPriorityFrom() {
		return priorityFrom;
	}
	public void setPriorityFrom(int priorityFrom) {
		this.priorityFrom = priorityFrom;
	}
	public int getPriorityTo() {
		return priorityTo;
	}
	public void setPriorityTo(int priorityTo) {
		this.priorityTo = priorityTo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
