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

Data Structure and Space complexity:

For course allocation:
Courses - Stored in a array of Course Objects. 
Student - Student id and Preferences are stored in Student Object and array of CoursePreference Objects respectively for processing. Student information is processed line by line and whole student information is NOT stored in any data Structure. The StudentCourseAllocation object gets deleted after that student is allocated.
Allocation - An array of CoursePreference object is used to temporarily store allocated courses. 

For storing the results:
Results - A hashmap to store the student id and an array of CoursePreference. This Result is used to print results to the file and stdout at the end.

Space Complexity:
If the number of Students is considered as n for space complexity calculation. Then space complexity is O(n).

Some design guidelines used: 
1) I have only used composition in all classes not inheritance because non of the classes have "IS-A" relationship between them.
2) There is no interface for model classes since it stores the information for processing and has only getters and setters. 
3) Other than model classes others have been coded to the interface.

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


