
import { Component } from '@angular/core';
import { FormGroup} from '@angular/forms';
import { Course } from '../../model/course';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})

export class CourseComponent {
  courseForm!: FormGroup;
  courses: Course[] = [];
  constructor() {
    //Write your logic here
  }

  createForm(): void {
    //Write your logic here
  }

  createCourse(): void {
    //Write your logic here
  }

  ngOnInit(): void {
    //Write your logic here
  }

  getAllCourses(): void {
    //Write your logic here
  }

  updateCourse(): void {
    //Write your logic here
  }

  deleteCourse(id: number): void {
    //Write your logic here
  }

  getCourseById(courseId: number): void {
    //Write your logic here
  }

  searchByTitle(): void {
    //Write your logic here
  }

  searchByStartDate(): void {
    //Write your logic here
  }

}
