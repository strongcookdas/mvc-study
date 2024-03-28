package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CookingTest {
    @Test
    @DisplayName("메뉴에 해당하는 음식을 만든다.")
    void makeCook() {
        Cooking cooking = new Cooking();
        MenuItem menuItem = new MenuItem("돈까스", 5000);
        Cook cook = cooking.makeCook(menuItem);
        assertThat(cook).isEqualTo(new Cook("돈까스", 5000));
    }
}
