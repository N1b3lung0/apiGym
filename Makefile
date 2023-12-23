run: start-db
	@./mvnw spring-boot:run

start: start-db
	@docker-compose up -d --build app

logs:
	@docker-compose logs -f app

stop:
	@docker-compose down

status:
	@docker ps -a

cleanup:
	@docker rmi $$(docker images -f "dangling=true" -q)

test:
	@./mvnw clean test

start-db:
	@docker-compose up -d db cloudbeaver

clean-db:
	@./mvnw flyway:clean

generate-schema:
	@rm -rf schema.sql
	@./gradlew bootRun -Pdev,schema

sonar: start-sonar
	@./mvnw clean verify sonar:sonar

start-sonar:
	@docker-compose up -d sonar

.PHONY: run start logs stop status cleanup test start-db clean-db generate-schema sonar start-sonar