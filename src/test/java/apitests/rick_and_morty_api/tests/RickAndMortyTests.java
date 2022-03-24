package apitests.rick_and_morty_api.tests;

import apitests.rick_and_morty_api.BaseTest;
import apitests.rick_and_morty_api.model.Character;
import apitests.rick_and_morty_api.model.Episode;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RickAndMortyTests extends BaseTest {

    private String name = "Morty Smith";
    private Character morty;
    private Episode lastEpisodeObj;
    private Character lastCharacter;
    private String lastEpisode;
    private int episodeAmount;

    @Дано("имя (.*)$")
    public void setDataStep(String name) {
        this.name = name;
    }

    @Когда("получения персонажа по имени")
    public void getMortyInfoStep() {

        morty = getCharactersByName(name);
    }

    @Тогда("проверка что имя полученного персонажа соответствует заданному")
    public void checkNameStep() {
        assertEquals(name, morty.getName());
    }

    @Когда("получение последнего эпизода с персонажем")
    public void getLastEpisodeWithMortyStep() {
        lastEpisode = getCharactersEpisode(morty, morty.getEpisodes().size() - 1);

        assertEquals(Integer.parseInt(lastEpisode.split("https://rickandmortyapi.com/api/episode/")[1]), morty.getEpisodes().size());
    }

    @Тогда("проверка, что полученный эпизод - последний")
    public void checkLastEpisodeIsLastStep() {
        assertEquals(Integer.parseInt(lastEpisode.split("https://rickandmortyapi.com/api/episode/")[1]), morty.getEpisodes().size());
    }

    @Когда("получение количества эпизодов")
    public void getEpisodeAmountStep() {
        episodeAmount = getEpisodeAmount();
    }

    @Когда("получеине эпизода по id")
    public void getEpisodeByIdStep() {
        lastEpisodeObj = getEpisodeById(episodeAmount);
    }

    @Когда("получение последнего персонажа в последнем эпизоде")
    public void getLastCharacterInLastEpStep() {
        lastCharacter = getCharacter(lastEpisodeObj, lastEpisodeObj.getCharacter().size() - 1);
    }

    @Тогда("проверка что полученнный персонаж действительно последний")
    public void checkChapterIsLastStep() {
        assertEquals(lastCharacter.getId(), 825);
    }
}
