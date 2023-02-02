FROM tomcat:9.0-jdk11

# Create user and group mymovie
RUN addgroup mymovie && adduser --system --ingroup mymovie mymovie
# Give all rights to every user
RUN chmod -R 777 $CATALINA_HOME/webapps
# Install maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean
# Use user mmovie
USER mymovie
# Set maven env variables
ENV M2_HOME /usr/share/maven
ENV PATH "${M2_HOME}/bin:${PATH}"
# Set workdir
WORKDIR /app/mymovie-backend/
# Copy the application
COPY . /app/mymovie-backend/
# Build java application
RUN mvn clean install -DskipTests
# Expose port 8080
EXPOSE 8080
# Run the Tomcat server
CMD ["catalina.sh", "run"]