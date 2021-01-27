import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class VallyTest {
    GeraldFitzGerald config = ConfigFactory.newInstance().create(GeraldFitzGerald.class);

    String userLogin = config.userLogin(),
           userPassword = config.userPassword();

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
    public void mainPage() {

        $(".main-page.site-container.categories-section").shouldHave(text("Hello Kitty"));

    }


}
