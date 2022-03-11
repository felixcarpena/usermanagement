.PHONY: up
up:
	docker-compose up -d

.PHONY: tests
tests: up
	docker-compose exec -u $$UID:$$GID back ./mvnw test