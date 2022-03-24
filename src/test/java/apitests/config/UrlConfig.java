package apitests.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.Sources;

@Sources({"classpath:tests_url.properties"})
public interface UrlConfig extends Config {
    @Key("rick_and_morty_url")
    String rickAndMortyUrl();

    @Key("create_user_service_url")
    String createUserUrl();
}
