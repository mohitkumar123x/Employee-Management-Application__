# Makefile for Employee Management Full-Stack App

# ─── Variables ────────────────────────────────────────────────────────────────
BACKEND_DIR := backend
FRONTEND_DIR := frontend
DOCKER_COMPOSE_FILE := docker-compose.yaml
K8S_DIR := kubernetes
OPENAPI_SPEC := openapi.yaml

# Docker image names
BACKEND_IMAGE := employee-management-app-backend
FRONTEND_IMAGE := employee-management-app-frontend

# ─── Default Target ──────────────────────────────────────────────────────────
.PHONY: help
help:
	@echo ""
	@echo "Usage: make <target>"
	@echo ""
	@echo "Targets:"
	@echo "  backend-build        Build backend (Gradle)"
	@echo "  backend-run          Run backend locally"
	@echo "  backend-test         Run backend JUnit tests"
	@echo ""
	@echo "  frontend-install     Install frontend deps (npm)"
	@echo "  frontend-build       Build frontend for production"
	@echo "  frontend-run         Run frontend dev server"
	@echo "  frontend-test        Run frontend tests"
	@echo ""
	@echo "  docker-build         Build Docker images"
	@echo "  docker-up            Spin up containers via docker-compose"
	@echo "  docker-down          Tear down containers"
	@echo ""
	@echo "  k8s-apply            Deploy to Kubernetes"
	@echo "  k8s-delete           Remove Kubernetes resources"
	@echo ""
	@echo "  openapi-gen-client   Generate API client from OpenAPI spec"
	@echo "  clean                Clean all build artifacts"
	@echo ""

# ─── Backend ─────────────────────────────────────────────────────────────────
.PHONY: backend-build backend-run backend-test
backend-build:
	@echo "→ Building backend with Gradle..."
	cd $(BACKEND_DIR) && gradle build -x test

backend-run:
	@echo "→ Starting backend (Spring Boot)..."
	cd $(BACKEND_DIR) && gradle bootRun

backend-test:
	@echo "→ Running backend tests..."
	cd $(BACKEND_DIR) && gradle test

# ─── Frontend ────────────────────────────────────────────────────────────────
.PHONY: frontend-install frontend-build frontend-run frontend-test
frontend-install:
	@echo "→ Installing frontend dependencies..."
	cd $(FRONTEND_DIR) && npm ci

frontend-build:
	@echo "→ Building frontend for production..."
	cd $(FRONTEND_DIR) && npm run build

frontend-run:
	@echo "→ Starting frontend dev server..."
	cd $(FRONTEND_DIR) && npm start

frontend-test:
	@echo "→ Running frontend tests..."
	cd $(FRONTEND_DIR) && npm test

# ─── Docker / Docker-Compose ─────────────────────────────────────────────────
.PHONY: docker-build docker-up docker-down
docker-build:
	@echo "→ Building Docker images..."
	docker build -t $(BACKEND_IMAGE):latest $(BACKEND_DIR)
	docker build -t $(FRONTEND_IMAGE):latest $(FRONTEND_DIR)

docker-up:
	@echo "→ Starting services via docker-compose..."
	docker compose -f $(DOCKER_COMPOSE_FILE) up --build

docker-down:
	@echo "→ Stopping services..."
	docker compose -f $(DOCKER_COMPOSE_FILE) down

# ─── Kubernetes ──────────────────────────────────────────────────────────────
.PHONY: k8s-apply k8s-delete
k8s-apply:
	@echo "→ Applying Kubernetes manifests..."
	kubectl apply -f $(K8S_DIR)

k8s-delete:
	@echo "→ Deleting Kubernetes resources..."
	kubectl delete -f $(K8S_DIR)

# ─── OpenAPI Client Generation ────────────────────────────────────────────────
.PHONY: openapi-gen-client
openapi-gen-client:
	@echo "→ Generating OpenAPI client (default: javascript)..."
	cd $(shell pwd) && \
	openapi-generator-cli generate \
	  -i $(OPENAPI_SPEC) \
	  -g javascript \
	  -o ./client

# ─── Utilities ────────────────────────────────────────────────────────────────
.PHONY: clean
clean:
	@echo "→ Cleaning artifacts..."
	cd $(BACKEND_DIR) && gradle clean
	cd $(FRONTEND_DIR) && rm -rf node_modules build
	docker image prune -f
