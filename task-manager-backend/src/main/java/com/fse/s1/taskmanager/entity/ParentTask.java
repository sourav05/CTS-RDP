package com.fse.s1.taskmanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_PARENT_TASK")
public class ParentTask {
	
	@Id
	@Column(name="PT_PARENT_ID")
	private long parentId;
	
	@Column(name="TP_PARENT_TASK")
	private String parentTask;

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

	@Override
	public String toString() {
		return "ParentTask [parentId=" + parentId + ", parentTask=" + parentTask + "]";
	}

}
