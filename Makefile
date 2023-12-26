run: start-db
	@./gradlew bootRun

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
	@./gradlew clean test

start-db:
	@docker-compose up -d db cloudbeaver

clean-db:
	@./gradlew flyway:clean

generate-schema:
	@rm -rf schema.sql
	@./gradlew bootRun -Pdev,schema

.PHONY: run start logs stop status cleanup test start-db clean-db generate-schema