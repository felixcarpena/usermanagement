.PHONY: up
up:
	docker-compose up -d

.PHONY: test
test: up
	docker-compose exec -u $$UID:$$GID back ./mvnw clean test

.PHONY: create-db
create-db: up
	docker-compose exec db mysql -u root -pdb-admin -e 'create database if not exists user_management;'

.PHONY: drop-db
drop-db: up
	docker-compose exec db mysql -u root -pdb-admin -e 'drop database if exists user_management;'

.PHONY: init-db
init-db: up
	docker-compose exec -u $$UID:$$GID back ./mvnw flyway:migrate -Dflyway.configFiles=flyway.conf

.PHONY: dependencies
dependencies: up
	docker-compose exec -u $$UID:$$GID back ./mvnw dependency:resolve