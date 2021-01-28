import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class VallyTest {
    GeraldFitzGerald config = ConfigFactory.newInstance().create(GeraldFitzGerald.class);

    String userLogin = config.userLogin(),
           userPassword = config.userPassword(),
           interfaceLanguage = "language";

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;

    }

    @BeforeEach
    public void logIn() {
        open("https://dev.vally.tv/");
        $(".sign-in").click();
        $("input[name='email']").val(userLogin);
        $("input[name='password']").val(userPassword);
        $(byText("Sign in")).click();

    }

    @Test
    @DisplayName("Главная страница и меню")
    public void mainPageTest() {
        $(".main-page.site-container.categories-section").shouldHave(text("Hello Kitty"));

    }

    @Test
    @DisplayName("Просмотр видеоконтента")
    public void watchingVideoTest() {
        //open("https://dev.vally.tv/video/bfe0a6f0-09ad-49af-9bea-5784cb561df5");
        $(".video-preview.slick-slide").$("[href='/video/86c056b1-791a-4b52-8b4c-0478c943f0b7']").click();
        $("#vally_player_iframe").click();
        WebElement frame = $("#vally_player_iframe");
        switchTo().frame(frame);
        $(".video-js").click();
        sleep(5000);

    }

    @Test
    @DisplayName("Смена языка интерфейса")
    public void languageChangeTest() {
        $(".language-select.button").click();
        $(".language-select_popover").$(byText(interfaceLanguage)).click();

    }

    @Test
    @DisplayName("Смена цветовой темы на темную")
    public void changeThemeTest() {
        $(".dark_ico").click();
        $(".dark_theme").shouldBe(visible);

    }

}
