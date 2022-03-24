package apitests.reqresin_api;

import apitests.config.UrlConfig;
import com.google.gson.JsonObject;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUserTest {

    private UrlConfig config = ConfigFactory.create(UrlConfig.class, System.getProperties());
    private String createUserUrl = config.createUserUrl();

    private String name;
    private String work;

    @Дано("работа {word} и имя {word}")
    public void setData(String name, String work) {
        this.name = name;
        this.work = work;
    }

    @Тогда("отправляем запрос и проверяем создание пользователя")
    public void createUserTest() {

        JsonObject userBody = new JsonObject();

        userBody.addProperty("name", name);
        userBody.addProperty("job", work);

        given().contentType(JSON).body(userBody.toString()).when().post(createUserUrl).then().statusCode(201).body("name", is(userBody.get("name").getAsString())).body("job", is(userBody.get("job").getAsString())).body("id", notNullValue()).body("createdAt", notNullValue());
    }
}
