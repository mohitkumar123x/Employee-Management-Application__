# Use Gradle to build and run the application in development
FROM gradle:7.5.1-jdk11

# Set the working directory
WORKDIR /app

# Copy the build files and download dependencies
COPY build.gradle settings.gradle ./
RUN gradle build --no-daemon > /dev/null 2>&1 || true

# Copy the entire project source
COPY src ./src

# Expose the application port
EXPOSE 8080

# Run the application with Spring Boot Gradle Plugin for hot reloading
ENTRYPOINT ["gradle", "bootRun"]

# Commands for building and running the Docker container:
#
# cd IdeaProjects/Employee-Management/backend
# (Fix the path to your project directory)
#
# docker build --platform linux/amd64 -t mohitkumar123x/employee-management-app .
# This builds the Docker image ready for deployment.
#
# docker run -p 8080:8080 \
  #  -e MYSQL_HOST=mysql-host \
  #  -e MYSQL_PORT=3306 \
  #  -e MYSQL_DB=your-db \
  #  -e MYSQL_USER=root \
  #  -e MYSQL_PASSWORD=your-password \
  #  mohitkumar123x/employee-management-app
# This runs the Docker container locally. Replace the environment variables with your own MySQL database details.
#
# docker push mohitkumar123x/employee-management-app
# This pushes the Docker image to Docker Hub.