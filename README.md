# Online Quiz Application

A complete web-based quiz application built with Java 24, Spring Boot, and SQLite. Features user authentication, quiz management, real-time scoring, and email notifications.

## 🚀 Features

### User Features
- **User Registration & Login**: Secure authentication with BCrypt password hashing
- **Quiz Taking**: Interactive multiple-choice questions with instant feedback
- **Results Tracking**: View personal quiz history and performance statistics
- **Email Notifications**: Receive quiz results via email after completion

### Admin Features
- **Quiz Management**: Create, edit, and delete quizzes
- **Question Management**: Add multiple-choice questions with correct answers
- **Results Dashboard**: View all user results and performance analytics
- **User Management**: Monitor user activity and quiz attempts

### Technical Features
- **Secure Authentication**: Spring Security with role-based access control
- **Responsive UI**: Bootstrap-powered modern interface
- **SQLite Database**: Lightweight, embedded database with automatic setup
- **Email Integration**: JavaMail API for result notifications
- **RESTful Architecture**: Clean MVC pattern with proper separation of concerns

## 🛠️ Technology Stack

- **Backend**: Java 24, Spring Boot 3.2.2
- **Security**: Spring Security with BCrypt
- **Database**: SQLite with Hibernate JPA
- **Frontend**: Thymeleaf templates with Bootstrap 5
- **Email**: JavaMail API with SMTP
- **Build Tool**: Maven

## 📋 Prerequisites

- Java 24 or higher
- Maven 3.6+
- SMTP email account (optional, for email notifications)

## 🚀 Quick Start

### 1. Clone and Build
```bash
git clone <repository-url>
cd online-quiz-app
mvn clean install
```

### 2. Configure Email (Optional)
Edit `src/main/resources/application.properties`:
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

Or run the JAR file:
```bash
java -jar target/online-quiz-app-0.0.1-SNAPSHOT.jar
```

### 4. Access the Application
- **Application URL**: http://localhost:8080
- **Admin Login**: admin@quizapp.com / admin123
- **Database**: SQLite file `quizapp.db` created automatically

## 📊 Database Schema

The application uses SQLite with the following tables:

### Users Table
- `id` (Primary Key)
- `name` (User's full name)
- `email` (Unique email address)
- `password_hash` (BCrypt hashed password)
- `role` (USER or ADMIN)

### Quizzes Table
- `id` (Primary Key)
- `title` (Quiz title)
- `description` (Quiz description)

### Questions Table
- `id` (Primary Key)
- `quiz_id` (Foreign Key to Quizzes)
- `question_text` (Question content)
- `option_a`, `option_b`, `option_c`, `option_d` (Answer options)
- `correct_option` (Correct answer: A, B, C, or D)

### Results Table
- `id` (Primary Key)
- `user_id` (Foreign Key to Users)
- `quiz_id` (Foreign Key to Quizzes)
- `score` (Number of correct answers)
- `total_questions` (Total questions in quiz)
- `date_taken` (Timestamp of quiz completion)

## 🎯 Usage Guide

### For Users
1. **Sign Up**: Create an account with name, email, and password
2. **Login**: Access your dashboard with registered credentials
3. **Take Quizzes**: Select available quizzes and answer questions
4. **View Results**: Check your scores and performance history

### For Administrators
1. **Login**: Use admin credentials (admin@quizapp.com / admin123)
2. **Create Quizzes**: Add new quizzes with titles and descriptions
3. **Add Questions**: Create multiple-choice questions for each quiz
4. **Monitor Results**: View all user attempts and performance analytics
5. **Manage Content**: Edit or delete existing quizzes and questions

## 🔧 Configuration

### Application Properties
Key configuration options in `application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:sqlite:quizapp.db
spring.jpa.hibernate.ddl-auto=update

# Email Configuration (Optional)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password

# Admin Account
app.admin.email=admin@quizapp.com
app.admin.password=admin123
```

### Security Configuration
- **Password Encryption**: BCrypt with strength 12
- **Session Management**: Spring Security session handling
- **Role-based Access**: USER and ADMIN roles with different permissions
- **CSRF Protection**: Enabled for form submissions

## 📦 Deployment

### Local Deployment
```bash
mvn clean package
java -jar target/online-quiz-app-0.0.1-SNAPSHOT.jar
```

### Production Deployment
1. **Build the JAR**:
   ```bash
   mvn clean package -Dmaven.test.skip=true
   ```

2. **Configure Production Settings**:
   - Update `application.properties` with production database settings
   - Configure SMTP settings for email notifications
   - Set secure admin credentials

3. **Run with Production Profile**:
   ```bash
   java -jar -Dspring.profiles.active=prod target/online-quiz-app-0.0.1-SNAPSHOT.jar
   ```

### Docker Deployment (Optional)
Create a `Dockerfile`:
```dockerfile
FROM openjdk:24-jdk-slim
COPY target/online-quiz-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

Build and run:
```bash
docker build -t quiz-app .
docker run -p 8080:8080 quiz-app
```

## 🧪 Testing

### Manual Testing
1. **User Registration**: Test signup with various inputs
2. **Authentication**: Verify login/logout functionality
3. **Quiz Taking**: Complete quizzes and verify scoring
4. **Admin Functions**: Test quiz and question management
5. **Email Notifications**: Verify email delivery (if configured)

### Test Data
The application automatically creates:
- Default admin user (admin@quizapp.com / admin123)
- Empty database ready for quiz creation

## 🔍 Troubleshooting

### Common Issues

**Database Connection Error**:
- Ensure SQLite JDBC driver is included in dependencies
- Check file permissions for database creation

**Email Not Sending**:
- Verify SMTP configuration in application.properties
- Check firewall settings for SMTP ports
- Use app-specific passwords for Gmail

**Authentication Issues**:
- Clear browser cache and cookies
- Verify user credentials in database
- Check Spring Security configuration

**Build Errors**:
- Ensure Java 24 is installed and configured
- Verify Maven dependencies are resolved
- Check for port conflicts (default: 8080)

## 📝 API Endpoints

### Public Endpoints
- `GET /` - Home page
- `GET /login` - Login page
- `POST /login` - Login processing
- `GET /signup` - Registration page
- `POST /signup` - User registration

### User Endpoints (Authenticated)
- `GET /dashboard` - User dashboard
- `GET /quiz/{id}` - Take quiz
- `POST /quiz/{id}/submit` - Submit quiz answers
- `GET /results` - View user results

### Admin Endpoints (Admin Role)
- `GET /admin/dashboard` - Admin dashboard
- `GET /admin/quiz/create` - Create quiz form
- `POST /admin/quiz/create` - Create quiz
- `GET /admin/quiz/{id}/questions` - Manage questions
- `POST /admin/quiz/{id}/questions` - Add question
- `GET /admin/results` - View all results

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Bootstrap team for the responsive UI components
- SQLite team for the lightweight database solution

---

**Built with ❤️ using Java 24 and Spring Boot**