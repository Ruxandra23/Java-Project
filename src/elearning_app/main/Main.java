package elearning_app.main;

import elearning_app.models.*;
import elearning_app.services.CourseService;
import elearning_app.services.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static CourseService courseService = new CourseService(userService);
    private static User currentUser = null;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            if (currentUser == null) {
                showAuthMenu();
            } else if (currentUser instanceof Student) {
                showStudentMenu();
            } else if (currentUser instanceof Mentor) {
                showMentorMenu();
            }

            System.out.println("\nDo you want to continue? (yes/no)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("no")) {
                running = false;
                System.out.println("Thank you for using our e-learning platform!");
            }
        }

        scanner.close();
    }

    private static void showAuthMenu() {
        System.out.println("\n===== Welcome to E-Learning Platform =====");
        System.out.println("1. Login");
        System.out.println("2. Register as Student");
        System.out.println("3. Register as Mentor");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");

        int choice = getIntInput();
        scanner.nextLine();
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                registerStudent();
                break;
            case 3:
                registerMentor();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    private static void showStudentMenu() {
        Student student = (Student) currentUser;
        System.out.println("\n===== Student Menu =====");
        System.out.println("Welcome, " + student.getName() + "!");
        System.out.println("1. View All Courses");
        System.out.println("2. View Courses by Category");
        System.out.println("3. Search Courses");
        System.out.println("4. View Top Rated Courses");
        System.out.println("5. Enroll in Course");
        System.out.println("6. Update Course Progress");
        System.out.println("7. Rate Course");
        System.out.println("8. Logout");
        System.out.print("Enter your choice: ");

        int choice = getIntInput();
        scanner.nextLine();

        switch (choice) {
            case 1:
                viewAllCourses();
                break;
            case 2:
                viewCoursesByCategory();
                break;
            case 3:
                searchCourses();
                break;
            case 4:
                viewTopRatedCourses();
                break;
            case 5:
                enrollInCourse(student.getId());
                break;
            case 6:
                updateCourseProgress(student.getId());
                break;
            case 7:
                rateCourse();
                break;
            case 8:
                logout();
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    private static void showMentorMenu() {
        Mentor mentor = (Mentor) currentUser;
        System.out.println("\n===== Mentor Menu =====");
        System.out.println("Welcome, " + mentor.getName() + "!");
        System.out.println("1. Create New Course");
        System.out.println("2. Create New Category");
        System.out.println("3. Add Lesson to Course");
        System.out.println("4. Add Quiz to Course");
        System.out.println("5. Add Question to Quiz");
        System.out.println("6. Add Challenge to Course");
        System.out.println("7. View My Courses");
        System.out.println("8. Logout");
        System.out.print("Enter your choice: ");

        int choice = getIntInput();
        scanner.nextLine();

        switch (choice) {
            case 1:
                createCourse(mentor.getId());
                break;
            case 2:
                createCategory();
                break;
            case 3:
                addLessonToCourse();
                break;
            case 4:
                addQuizToCourse();
                break;
            case 5:
                addQuestionToQuiz();
                break;
            case 6:
                addChallengeToCourse();
                break;
            case 7:
                viewMentorCourses(mentor.getId());
                break;
            case 8:
                logout();
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }


    private static void login() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userService.authenticateUser(email, password);
        if (user != null) {
            currentUser = user;
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

    private static void registerStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Student student = userService.registerStudent(name, email, password);
        if (student != null) {
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Registration failed. Email might already be in use.");
        }
    }

    private static void registerMentor() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("Enter bio: ");
        String bio = scanner.nextLine();

        Mentor mentor = userService.registerMentor(name, email, password, specialization, bio);
        if (mentor != null) {
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Registration failed. Email might already be in use.");
        }
    }

    private static void logout() {
        currentUser = null;
        System.out.println("Logged out successfully!");
    }


    private static void viewAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        displayCourses(courses);
    }

    private static void viewCoursesByCategory() {
        displayAllCategories();
        System.out.print("Enter category ID: ");
        String categoryId = scanner.nextLine();

        List<Course> courses = courseService.getCoursesByCategory(categoryId);
        displayCourses(courses);
    }

    private static void searchCourses() {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        List<Course> courses = courseService.searchCoursesByKeyword(keyword);
        displayCourses(courses);
    }

    private static void viewTopRatedCourses() {
        List<Course> courses = courseService.getTopRatedCourses();
        displayCourses(courses);
    }

    private static void viewMentorCourses(String mentorId) {
        // Note: You might need to add this method to CourseService
        System.out.println("Functionality not implemented yet.");
    }


    private static void createCourse(String mentorId) {
        displayAllCategories();

        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter course description: ");
        String description = scanner.nextLine();
        System.out.print("Enter category ID: ");
        String categoryId = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = getDoubleInput();
        scanner.nextLine(); // Consume newline

        Course course = courseService.createCourse(title, description, mentorId, categoryId, price);
        if (course != null) {
            System.out.println("Course created successfully! Course ID: " + course.getId());
        } else {
            System.out.println("Course creation failed.");
        }
    }

    private static void createCategory() {
        System.out.print("Enter category name: ");
        String name = scanner.nextLine();
        System.out.print("Enter category description: ");
        String description = scanner.nextLine();

        Category category = courseService.createCategory(name, description);
        if (category != null) {
            System.out.println("Category created successfully! Category ID: " + category.getId());
        } else {
            System.out.println("Category creation failed.");
        }
    }

    private static void addLessonToCourse() {
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter lesson title: ");
        String title = scanner.nextLine();
        System.out.print("Enter lesson content: ");
        String content = scanner.nextLine();
        System.out.print("Enter duration (minutes): ");
        int duration = getIntInput();
        System.out.print("Enter order in course: ");
        int order = getIntInput();
        scanner.nextLine(); // Consume newline

        Lesson lesson = courseService.addLessonToCourse(courseId, title, content, duration, order);
        if (lesson != null) {
            System.out.println("Lesson added successfully!");
        } else {
            System.out.println("Failed to add lesson.");
        }
    }

    private static void addQuizToCourse() {
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter quiz title: ");
        String title = scanner.nextLine();
        System.out.print("Enter passing score: ");
        int passingScore = getIntInput();
        scanner.nextLine(); // Consume newline

        Quiz quiz = courseService.addQuizToCourse(courseId, title, passingScore);
        if (quiz != null) {
            System.out.println("Quiz added successfully! Quiz ID: " + quiz.getId());
        } else {
            System.out.println("Failed to add quiz.");
        }
    }

    private static void addQuestionToQuiz() {
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter quiz ID: ");
        String quizId = scanner.nextLine();
        System.out.print("Enter question: ");
        String question = scanner.nextLine();
        System.out.print("Enter answer: ");
        String answer = scanner.nextLine();

        courseService.addQuestionToQuiz(courseId, quizId, question, answer);
        System.out.println("Question added successfully!");
    }

    private static void addChallengeToCourse() {
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter challenge title: ");
        String title = scanner.nextLine();
        System.out.print("Enter challenge description: ");
        String description = scanner.nextLine();
        System.out.print("Enter difficulty (1-5): ");
        int difficulty = getIntInput();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter task: ");
        String task = scanner.nextLine();
        System.out.print("Enter solution: ");
        String solution = scanner.nextLine();

        LearningChallenge challenge = courseService.addChallengeToCourse(courseId, title, description, difficulty, task, solution);
        if (challenge != null) {
            System.out.println("Challenge added successfully!");
        } else {
            System.out.println("Failed to add challenge.");
        }
    }


    private static void enrollInCourse(String studentId) {
        viewAllCourses();
        System.out.print("Enter course ID to enroll: ");
        String courseId = scanner.nextLine();

        courseService.enrollStudentInCourse(studentId, courseId);
        System.out.println("Enrolled successfully!");
    }

    private static void updateCourseProgress(String studentId) {
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter progress (0-100): ");
        double progress = getDoubleInput();
        scanner.nextLine();

        courseService.updateStudentProgress(studentId, courseId, progress);
        System.out.println("Progress updated successfully!");
    }

    private static void rateCourse() {
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter rating (1-5): ");
        double rating = getDoubleInput();
        scanner.nextLine();
        System.out.print("Enter review (optional): ");
        String review = scanner.nextLine();

        courseService.addCourseRating(courseId, rating, review);
        System.out.println("Rating submitted successfully!");
    }

  
    private static void displayCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("\n===== Courses =====");
        for (Course course : courses) {
            System.out.println("ID: " + course.getId());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Price: $" + course.getPrice());
            //System.out.println("Rating: " + course.getAverageRating());
            System.out.println("-------------------------");
        }
    }

    private static void displayAllCategories() {
        List<Category> categories = courseService.getAllCategories();

        System.out.println("\n===== Categories =====");
        for (Category category : categories) {
            System.out.println("ID: " + category.getId() + " - " + category.getName());
        }
        System.out.println("-------------------------");
    }

    private static int getIntInput() {
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                input = scanner.nextInt();
                validInput = true;
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
        }

        return input;
    }

    private static double getDoubleInput() {
        double input = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                input = scanner.nextDouble();
                validInput = true;
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
        }

        return input;
    }
}