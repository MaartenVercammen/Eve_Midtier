package dev.maarten.eve.models.auth;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkTest {

    @Test
    void testLink() {
        //Arrange variables
        Link link = Link.builder()
                .domain("www.test.be")
                .path("/home/test")
                .schema("https")
                .parameters(Map.of("test", "1", "name", "mark"))
                .build();
        //Arrange Mocks

        //Act
        String actual = link.getLink();
        //Assert
        assertThat(actual).isEqualTo("https://www.test.be/home/test?test=1&name=mark");
    }

}
