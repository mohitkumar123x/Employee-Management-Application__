FROM node:18-alpine AS builder
WORKDIR /app

COPY package.json package-lock.json ./
RUN npm ci

COPY . .

ARG REACT_APP_API_BASE_URL
ENV REACT_APP_API_BASE_URL=$REACT_APP_API_BASE_URL
RUN npm run build

FROM node:18-alpine
WORKDIR /app

RUN npm install -g serve
COPY --from=builder /app/build ./build

EXPOSE 3000
CMD ["sh", "-c", "serve -s build -l ${PORT:-3000}"]
