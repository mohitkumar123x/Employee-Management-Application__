# Use an official Node.js image for development
FROM node:18

# Set the working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy the rest of the application code
COPY src ./src
COPY public ./public

# Expose the development server port
EXPOSE 3000

# Start the development server
CMD ["npm", "start"]
