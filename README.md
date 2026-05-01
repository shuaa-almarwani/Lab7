# Lab 7: Learning Management System (LMS)


## UML Diagram

![UML Diagram](UML_IMAGE_URL_HERE)


---

## Data Models & Validations

### 1. Course Model
- **ID:** Cannot be null, size > 2.
- **Name:** Cannot be empty.
- **Level:** Must be a positive number (1 to 8).
- **Language:** Cannot be empty.
- **isAvailable:** Default is `false`.

### 2. Student Model
- **ID:** Cannot be null.
- **Name:** Cannot be empty.
- **Email:** Must be a valid email format.
- **Level:** Must be a positive number (1 to 8).

### 3. Teacher Model
- **ID:** Cannot be null.
- **Name:** Cannot be empty.
- **Salary:** Must be a positive number.
- **Subject:** Cannot be empty.

### 4. Project Model
- **ID:** Cannot be null.
- **Title:** Cannot be empty, minimum 5 characters.
- **Status:** Must be "Not Started", "In Progress", or "Done".
- **Description:** Cannot be empty.

### 5. Assignment Model
- **ID:** Cannot be null.
- **Title:** Cannot be empty.
- **Total Marks:** Must be a positive number (up to 100).

---

## LMS Controller Endpoints

### TeacherController
- **CRUD Operations:** Full management of courses.

### StudentController
- **CRUD Operations:** Full management of student records.
- **Check Graduation:** Takes `student_id` and returns "Expected to Graduate" or "Regular Student".

### CourseController
- **CRUD Operations:** Full management of courses.
- **Enroll Student:** Checks if student level and course level match.
- **Get Courses by Level Range:** Filter courses between `min` and `max` levels.
- **Get Available Courses:** Returns only courses where `isAvailable` is true.

### ProjectController
- **CRUD Operations:** Full management of projects.
- **Update Project Status:** Updates state (e.g., to "Done").
- **Search Projects by Title:** Search by keyword.
- **Get Projects by Status:** Filter projects based on status.

### AssignmentController
- **CRUD Operations:** Full management of assignments.
- **Check Student Grade:** Verifies if score is $\le$ total marks.
- **Extend Deadline:** Adds extra days to the finish date.
- **Get Hard Assignments:** List assignments with > 50 marks.
