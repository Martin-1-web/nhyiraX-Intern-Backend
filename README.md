# Image Upload Server

A lightweight Java HTTP server with user authentication and secure image upload functionality.

## Features

✅ User registration and login system
✅ Session-based authentication with cookies
✅ Secure image upload (max 500KB)
✅ File validation:
  - File size limit (500KB)
  - File extension check (.jpg, .jpeg, .png, .gif)
  - Magic number validation (prevents malicious files disguised as images)
✅ User-isolated storage (each user has their own upload directory)
✅ Cross-platform compatible (Windows, macOS, Linux)

## Requirements

- Java 11 or higher
- No external dependencies (uses built-in `com.sun.net.httpserver`)

## Installation

1. Clone the repository:
```bash
   git clone https://github.com/Martin-1-web/nhyiraX-Intern-Backend.git
   cd nhyiraX-Intern-Backend/ImageUploader
```

2. Compile the project:
```bash
   cd src/main/java
   javac com/martin/server/ImageUploadServer.java com/martin/handlers/*.java com/martin/utils/*.java
```

3. Run the server:
```bash
   java -cp . com.martin.server.ImageUploadServer
```

4. Open your browser and go to:
```
   http://localhost:8081
```

## Usage

### Register a New Account
1. Visit `http://localhost:8081/register`
2. Enter a username and password
3. Click "Register"

### Login
1. Visit `http://localhost:8081/login`
2. Enter your credentials
3. Click "Login"

### Upload Images
1. After logging in, visit `http://localhost:8081/upload`
2. Select an image file (max 500KB)
3. Click "Upload"
4. Images are saved in `uploads/your-username/`

## Project Structure
```
src/main/java/com/martin/
├── server/
│   └── ImageUploadServer.java    # Main server class
├── handlers/
│   ├── HomeHandler.java           # Homepage handler
│   ├── RegisterHandler.java       # User registration
│   ├── LoginHandler.java          # User login & sessions
│   └── UploadHandler.java         # Image upload & validation
└── utils/
    └── UserManager.java            # User data management
```

## Security Features

### File Validation
- **Size Check**: Rejects files larger than 500KB
- **Extension Check**: Only allows .jpg, .jpeg, .png, .gif
- **Magic Number Validation**: Verifies file content matches the claimed format
  - JPEG: `FF D8 FF`
  - PNG: `89 50 4E 47`
  - GIF: `47 49 46`

### Authentication
- Session-based authentication using cookies
- User-specific upload directories
- Protected routes (must be logged in to upload)

## File Storage

Uploaded files are stored in:
```
uploads/
└── username/
    ├── timestamp1_filename.jpg
    ├── timestamp2_filename.png
    └── ...
```

Each file is prefixed with a timestamp to ensure unique names.

## Limitations

- Passwords are stored in plain text (for learning purposes only - NOT production-ready)
- No database - users stored in `users/users.txt`
- Single-threaded server
- Port 8081 is hardcoded

## Future Improvements

- [ ] Password hashing (BCrypt)
- [ ] Database integration (PostgreSQL)
- [ ] View uploaded images interface
- [ ] Delete uploaded images
- [ ] User profile page
- [ ] Logout functionality
- [ ] Remember me option
- [ ] Password reset

## Author

Martin - Backend Intern

## License

This project is for educational purposes.
