# ESN

- Покройте, пожалуйста, этот endpoint автотестами REST assured и Java, спецификация эндроинта - https://app.swaggerhub.com/apis/roga88/bunker/1.0.0

- Необходимо сделать структуру проекта, разделив на слои: тесты, шаги, проверки, модели

- При желании можно добавить Allure отчёт

- Проект с автотестами разместите на гитхабе. 

Пример api клиента:

    public class Auth {

        private RequestSpecification baseRequestSpec() {
            return RestAssured
                    .given()
                    .urlEncodingEnabled(false)
                    .baseUri("http://example.org")
                    .contentType(ContentType.JSON)
        }
        
         public ValidatableResponse login(String login, String password) {
    
            return baseRequestSpec()
                    .body(new HashMap<String, Object>() {{
                        put("login", login);
                        put("password", password);
                    }})
                    .when()
                    .post("/login")
                    .then()
                    .statusCode(HttpStatus.SC_OK);
        }
    
    }
