# CSX42: Assignment 1
## Name: Spoorthi Sathya Narayana Murthy

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in studentCoursesMgmt/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile studentCoursesMgmt/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile studentCoursesMgmt/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile studentCoursesMgmt/src/build.xml run -Darg0=<input_file.txt> -Darg1=<delete_file.txt> -Darg2=<output1_file.txt> -Darg3=<output2_file.txt> -Darg4=<output3_file.txt>

## Replace <fileName.txt> with real file names. For example, if the files are available in the path,
## you can run it in the following manner:

ant -buildfile studentCoursesMgmt/src/build.xml run -Darg0=input_file.txt -Darg1=delete_file.txt -Darg2=output1_file.txt -Darg3=output2_file.txt -Darg4=output3_file.txt

Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
## Description:

## Model classes: 
These classes acts as templates for data. Just like database schema. Models does not have interfaces because they just act as templates with only getters and setter. 
1. Course.java - It has data members related to course. 
2. Student.java - It has data members realted to student. 
3. CoursePreference.java - It has one course object and student preference for that course. 
4. Results.java- It stores results for final output.



## Utility classes: 
1)StudentCourseManagement.java
- It reads the course input and stores in an array from courseInfo.txt. The reference to this array is passed everywhere. 
- It reads student information and preferences of student from coursePrefs.txt. This data along with available course list is used to for student course allocation.
- It validates command line arguments.
- Deletes older output files if exists.

2)StudentCourseAllocation.java 
- It gets all the necessary information from StudentCourseManagement.java and allocates courses to student based on FCFS algorithm. 
- Stores the output for each student in Results.

3)FileProcessor.java - It contains all the generic functions that can be used for file input and output. 

4)FileInput.java - This class is reading input files specific to this assignment. It methods read data line by line and                       split's it based on the delimiter in the line. It uses methods in FileProcessor to do the job. 

5)FileOutput.java - All the output emitting helper methods are in this class. It uses FileProcessor object and implements                     FileDisplayInterface and StdoutDisplayInterface.


## Data Structure for storing result :  
A hashmap is used to store the results in Results.java. It stores Student id as key and an array of allocated courses as value. I have used hashmap for two reasons, It is dynamic as we don't know number of students and we can easily fetch information based on student id.

## Time Complexity:
Allocation is based on FCFS algorithm. If n is number of students and m is number of preferences given, my worst case time complexity will be T(n) = O(mn). Since m=9 for this assignment, T(n) = O(n).

## Space Complexity: 
If the number of Students is considered as n for space complexity calculation. Then space complexity is O(n).

## Some design guidelines used:
1. I have only used composition in all classes not inheritance because non of the classes have "IS-A" relationship between them.
2. There is no interface for model classes since it stores the information for processing and has only getters and setters.
3. Other than model classes others have been coded to the interface.


-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.""

Date: 09/25/2023


