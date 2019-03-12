import { Component, OnInit } from '@angular/core';
import { TaskService } from 'src/app/task-manager/task.service';
import { TaskDetails } from 'src/app/task-manager/task.model';
import { Router } from '@angular/router';
import { ParentTask } from 'src/app/task-manager/parent-task.model';

@Component({
  selector: 'app-task-manager-update',
  templateUrl: './task-manager-update.component.html',
  styleUrls: ['./task-manager-update.component.css']
})
export class TaskManagerUpdateComponent implements OnInit {

  sliderPointerVal:number = 1;
  taskId:number;
  taskName:string;
  parent:ParentTask;
  parentId:string;
  startDate:string;
  endDate:string;
  constructor(private taskService:TaskService, private router:Router) { }

  ngOnInit() {
    let task = this.taskService.fetchUpdatableTask();
    this.taskId = task.taskId;
    this.sliderPointerVal = task.priority;
    this.taskName = task.task;
    this.parent = task.parent;
    this.parentId = task.parent.parentId;
    this.startDate = task.startDate;
    this.endDate = task.endDate;
  }

  updateTask(){
    let task = new TaskDetails(this.taskId,this.taskName,this.parent, this.sliderPointerVal,
                              this.startDate, this.endDate);
    this.taskService.updateTask(task)
      .subscribe(data => {
        console.log(data);
        if(data != null){
          alert("data updated successfully");
          this.cancelUpdate();
        }
      });
  }

  cancelUpdate(){
    this.router.navigate(['/view']);
  }
}
