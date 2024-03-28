package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class MenuItemItemTest {
    @Test
    @DisplayName("메뉴항목을 생성한다.")
    void createTest() {
        assertThatCode(() -> new MenuItem("만두",1000))
                .doesNotThrowAnyException();
    }
}
