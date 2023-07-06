
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CourseComponent } from './course.component';
import { CourseService } from '../../service/course.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Course } from 'src/app/model/course';
import { of} from 'rxjs';

describe('CourseComponent', () => {
    let component: CourseComponent;
    let fixture: ComponentFixture<CourseComponent>;
    let serviceMock:any;
    let courseService: CourseService;
    const course: Course = {
      id: 1,       
      title: 'Title',
      description: 'Description',
      instructor: 'Instructor',
      duration: 'duration',
      startDate: new Date('2022-01-01'),
      endDate:new Date('2023-01-01'),
      syllabus: 'syllabus'
    }
  
    let mockService = {
      getAllCourses: ()=>{return of([course])},
      deleteCourse: (id:number|string)=>{return of(course)},
      getCourse: ()=>{
        return of([course])
      },
    }

    beforeEach(async () => {
      await TestBed.configureTestingModule({
        declarations: [CourseComponent],
        imports: [FormsModule,ReactiveFormsModule,HttpClientModule,HttpClientTestingModule] ,
        providers: [FormBuilder, CourseService,HttpTestingController,{provide: CourseService, useValue: mockService}] 
      }).compileComponents();
    });

    beforeEach(() => {
      serviceMock={
        getAllCourses:jest.fn(),
        createCourse:jest.fn(),
        updateCourse:jest.fn(),
        deleteCourse:jest.fn(),
        searchByTitle:jest.fn(),
        searchByStartDate:jest.fn(), 
        };

      fixture = TestBed.createComponent(CourseComponent);
      component = fixture.componentInstance;
      courseService = TestBed.inject(CourseService);
      fixture.detectChanges();
    });

    describe("business", ()=>{

        it('should create the course component', () => {
          expect(component).toBeTruthy();
        });

        it('should declare course form referece',()=>{
          expect(component.courseForm).toBeDefined();
        })

        it('should initialize the form',()=>{
          const courseForm={
            id:null,       
            title: '',
            description: '',
            instructor: '',
            duration: '',
            startDate: '',
            endDate:'',
            syllabus: ''
          };
          expect(component.courseForm.value).toEqual(courseForm);
        });
    });

    describe('business',()=>{       

      it('should validate the title field in the form', () => {
        const c = component.courseForm.controls['title'];      
        c.setValue('Title1'); 
        expect(c.valid).toBeTruthy();   
        c.setValue(''); 
        expect(c.invalid).toBeTruthy(); 
        c.setValue('Ti'); 
        expect(c.invalid).toBeTruthy();
        c.setValue('aaaa aaa aaa aaaaa aaaaaaaaaaa aaaaaaaaa a a a a a  a a a a a aaaaaaa aaa aaaaaaa aaa aaaa aaaaaa aaa aaaa aaaaaaa aaaaaa aaaaa aaaaaaaaa aaaaaaaaaaaa aaaaaaaaaaa aaaaaaaaaaaaa aaaaaaaa aaaaaaa aaaaa aaaaaaaa aaaaaaaa aaaaaaaaaa aaaaaaaaaaaaa aaaaaaaaaaaa aaaaaaaaaa aaaaaaaa aaaaaaaaaaaaa aaaaaa aaaaaaa aaaaa aaaa');    
        expect(c.invalid).toBeTruthy();
      });

      it('should validate the description field in the form', () => {
        const c = component.courseForm.controls['description'];        
       c.setValue('description');    
        expect(c.valid).toBeTruthy();    
       c.setValue('');    
        expect(c.invalid).toBeTruthy(); 
      });

      it('should validate the instructor field in the form', () => {
        const c = component.courseForm.controls['instructor'];        
       c.setValue('instructor');    
        expect(c.valid).toBeTruthy();    
       c.setValue('');    
        expect(c.invalid).toBeTruthy(); 
      });

      it('should validate the duration field in the form', () => {
        const c = component.courseForm.controls['duration'];        
       c.setValue('1 month');    
        expect(c.valid).toBeTruthy();    
       c.setValue('');    
        expect(c.invalid).toBeTruthy();   
      });

   
      it('should  validate the startDate field in the form', () => {
        const c = component.courseForm.controls['startDate'];        
       c.setValue('2022-01-01');    
        expect(c.valid).toBeTruthy();    
       c.setValue('');    
        expect(c.invalid).toBeTruthy(); 
      });

      it('should validate the endDate field in the form', () => {
        const c = component.courseForm.controls['endDate'];        
       c.setValue('2023-01-01');    
        expect(c.valid).toBeTruthy();    
       c.setValue('');    
        expect(c.valid).toBeTruthy(); 
      });

      it('validates the syllabus field in the form', () => {
        const c = component.courseForm.controls['syllabus'];        
       c.setValue('syllabus1');    
        expect(c.valid).toBeTruthy();    
        c.setValue('');    
        expect(c.valid).toBeTruthy();   
      });


    });
  
   describe("boundary", ()=>{

      it('should invalidate the form when title length  is greater than 100', () => {
        const form = component.courseForm;
        form.controls['title'].setValue('aaaa aaaa aaaaa aaaaa aa aaaaaaaaaa aaaaaaaaaa aaaa  aaaa aaaa aaaaa aaaaa aa aaaaaaaaaa aaaaaaaaaa aaaa aaaa aaaaa aaaaa aa aaaaaaaaaaaaaaaaaaaa');
        expect(form.invalid).toBeTruthy();
        expect(form.controls['title'].errors?.['maxlength']).toBeTruthy();
      });

      it('should invalidate the form when title length is less than 3', () => {
        const form = component.courseForm;
        form.controls['title'].setValue('Ti');
        expect(form.invalid).toBeTruthy();
        expect(form.controls['title'].errors?.['minlength']).toBeTruthy();
      });

   });

    describe('business',()=>{

      it('should validate the form ',()=>{
        component.courseForm.controls['id'].setValue(1);
        component.courseForm.controls['title'].setValue('title');
        component.courseForm.controls['description'].setValue('description');
        component.courseForm.controls['instructor'].setValue('instructor');
        component.courseForm.controls['duration'].setValue('1 month');
        component.courseForm.controls['startDate'].setValue(new Date('2022-01-01'));
        component.courseForm.controls['endDate'].setValue(new Date('2023-01-01'));
        component.courseForm.controls['syllabus'].setValue('syllabus');
        expect(component.courseForm.valid).toBeTruthy();
      });

        it('should disable the submit button when the form is invalid', () => {
        const form = component.courseForm;
        form.controls['title'].setValue('');
        form.controls['description'].setValue('');
        form.controls['instructor'].setValue('');
        form.controls['duration'].setValue('');
        form.controls['startDate'].setValue('');
        form.controls['endDate'].setValue('');
        form.controls['syllabus'].setValue('');
        fixture.detectChanges();    
        const submitButton = fixture.nativeElement.querySelector('button[type="submit"]');
        expect(submitButton.disabled).toBe(true);
      });

      it('should enable the submit button when the form is valid', () => {
        const form = component.courseForm;
        component.courseForm.controls['title'].setValue('title1');
        component.courseForm.controls['description'].setValue('description1');
        component.courseForm.controls['instructor'].setValue('instructor1');
        component.courseForm.controls['duration'].setValue('duration1');
        component.courseForm.controls['startDate'].setValue(new Date('2022-01-01'));
        component.courseForm.controls['endDate'].setValue(new Date('2023-01-01'));
        component.courseForm.controls['syllabus'].setValue('syllabus');
        fixture.detectChanges();        
        const submitButton = fixture.nativeElement.querySelector('button[type="submit"]');
        expect(submitButton.disabled).toBe(false);
      });
    });

  describe('exception',()=>{

    it('should invalidate the form when empty',()=>{
      component.courseForm.controls['id'].setValue('');
      component.courseForm.controls['title'].setValue('');
      component.courseForm.controls['description'].setValue('');
      component.courseForm.controls['instructor'].setValue('');
      component.courseForm.controls['duration'].setValue('');
      component.courseForm.controls['startDate'].setValue('');
      component.courseForm.controls['endDate'].setValue('');
      component.courseForm.controls['syllabus'].setValue('');
      expect(component.courseForm.valid).toBeFalsy();
    });
  
    // it('id field validity', () => {
    //   const c = component.courseForm.controls['id']
    //   expect(c.invalid).toBeFalsy();            
    // });
    
    it('should required title field', () => {
      const c = component.courseForm.controls['title']
      expect(c.valid).toBeFalsy();      
      c.setValue('');
      expect(c.hasError('required')).toBeTruthy();      
    });
    
    it('should required description field', () => {
      const c = component.courseForm.controls['description']
      expect(c.valid).toBeFalsy();      
      c.setValue('');
      expect(c.hasError('required')).toBeTruthy();      
    });
    
    it('should required duration field', () => {
      const c = component.courseForm.controls['duration']
      expect(c.valid).toBeFalsy();      
      c.setValue('');
      expect(c.hasError('required')).toBeTruthy();      
    });
    
    it('should required startDate field', () => {
      const c = component.courseForm.controls['startDate']
      expect(c.valid).toBeFalsy();      
      c.setValue('');
      expect(c.hasError('required')).toBeTruthy();      
    });

    it('endDate field validity', () => {
      const c = component.courseForm.controls['endDate']
      expect(c.invalid).toBeFalsy();      
      c.setValue('');
      expect(c.hasError('required')).toBeFalsy();      
    });

    it('syllabus field validity', () => {
      const c = component.courseForm.controls['syllabus']
      expect(c.invalid).toBeFalsy();      
      c.setValue('');
      expect(c.hasError('required')).toBeFalsy();      
    });  
  });

 
      describe("business", ()=>{
            it('should fetch all courses', ()=>{    
              component.courses=[];
              jest.spyOn(mockService, 'getAllCourses').mockReturnValue(of([course]));
              component.getAllCourses();        
              expect(component.courses.length).toBeGreaterThan(0);    
              expect(Array.isArray(component.courses)).toBe(true);
            }) 
            
            it('should delete course by id', ()=>{  
              jest.spyOn(mockService, 'deleteCourse').mockReturnValue(of(course));
              component.deleteCourse(1);      
              expect(mockService.deleteCourse).toBeCalledTimes(1);
              expect(mockService.deleteCourse).toBeCalledWith(1);
          
            })
          
            it('should get course  by id', ()=>{  
              jest.spyOn(mockService, 'getCourse')//.mockReturnValue(of(course));
              component.getCourseById(1);      
              expect(mockService.getCourse).toBeCalledTimes(1);
              expect(mockService.getCourse).toBeCalledWith(1);
          
            })

      });
});


