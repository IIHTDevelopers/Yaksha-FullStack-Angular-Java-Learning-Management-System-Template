
import { HttpParams } from '@angular/common/http';
import { CourseService } from './course.service';
import { of } from 'rxjs';

describe('CourseService', () => {
  let service: CourseService;
  let httpClientSpy: any;
  beforeEach(() => {
    httpClientSpy = {
      get: jest.fn(),
      post: jest.fn(),
      put: jest.fn(),
      delete: jest.fn(), 
    };
    service = new CourseService(httpClientSpy);
  });

  describe('business',()=>{     

    it('should get all courses by calling getAllCourses() in service', () => {
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/learningmanagement/lms';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res)); 
      service.getAllCourses();
      expect(httpClientSpy.get).toBeCalledTimes(1); 
      expect(httpClientSpy.get).toHaveBeenCalledWith(url); 
    });
        
    it('should get course by calling getCourse() in service', () => {
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/learningmanagement/lms/1';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res)); 
      service.getCourse(1);
      expect(httpClientSpy.get).toBeCalledTimes(1); 
      expect(httpClientSpy.get).toHaveBeenCalledWith(url); 
    });

    it('should create course by calling createCourse() in service', () => {
      const data = {
        id: 1,       
        title: 'Title',
        description: 'Description',
        instructor: 'Instructor',
        duration: 'duration',
        startDate: new Date('2022-01-01'),
        endDate:new Date('2023-01-01'),
        syllabus: 'syllabus'
       };

      const res = 'some message';
      const url = 'http://127.0.0.1:8081/learningmanagement/lms';
      jest.spyOn(httpClientSpy, 'post').mockReturnValue(of(res));
      service.createCourse(data);
      expect(httpClientSpy.post).toBeCalledTimes(1);
      expect(httpClientSpy.post).toHaveBeenCalledWith(url,data);
    });
  
    it('should update course by calling updateCourse() in service', () => {
      const command1 = 1;
      const data = {
        id: 1,       
        title: 'Title',
        description: 'Description',
        instructor: 'Instructor',
        duration: 'duration',
        startDate: new Date('2022-01-01'),
        endDate:new Date('2023-01-01'),
        syllabus: 'syllabus'
       };
  
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/learningmanagement/lms/1';
      jest.spyOn(httpClientSpy, 'put').mockReturnValue(of(res));
      service.updateCourse(data.id,data);
      expect(httpClientSpy.put).toBeCalledTimes(1);
      expect(httpClientSpy.put).toHaveBeenCalledWith(url,data);
    });
  
    it('should delete course by calling deleteCourse() in service', () => {
      const command = 1;
      const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/learningmanagement/lms/1';
      jest.spyOn(httpClientSpy, 'delete').mockReturnValue(of(res));
      service.deleteCourse(1);
      expect(httpClientSpy.delete).toBeCalledTimes(1);
      expect(httpClientSpy.delete).toHaveBeenCalledWith(API_URL);
    });
    
    it('should search course with title by calling searchByTitle() in service', () => {
  
      const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/learningmanagement/lms/search?title=title1';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res));
      service.searchByTitle('title1');
      expect(httpClientSpy.get).toBeCalledTimes(1);
      expect(httpClientSpy.get).toHaveBeenCalledWith(API_URL);
    });
    
    it('should search course with start date by calling searchByStartDate() in service', () => {
      const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/learningmanagement/lms/search?startDate=Sun Jan 01 2023 00:00:00 GMT+0000 (Coordinated Universal Time)';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res));
      service.searchByStartDate(new Date('2023-01-01'));
      expect(httpClientSpy.get).toBeCalledTimes(1);
      expect(httpClientSpy.get).toHaveBeenCalledWith(API_URL);
    });

    });  

  });

