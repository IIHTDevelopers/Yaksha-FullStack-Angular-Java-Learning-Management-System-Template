import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Course } from '../model/course';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private readonly baseUrl = '';

  constructor(private http: HttpClient) { }

  createCourse(course: Course) {
    //Write your logic here
  }

  getCourse(id: number) {
    //Write your logic here
  }

  getAllCourses() {
    //Write your logic here
  }

  updateCourse(id: number, course: Course) {
    //Write your logic here
  }

  deleteCourse(id: number) {
    //Write your logic here
  }

  searchByTitle(title: string) {
    //Write your logic here
  }

  searchByStartDate(startDate: Date) {
    //Write your logic here
  }


}
