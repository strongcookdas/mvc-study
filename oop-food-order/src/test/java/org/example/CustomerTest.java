package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

/*
- 요구사항
- 1. 객체 손님, 메뉴판, 음식, 요리사, 요리
- 2. 객체들 간의 관계
        손님 - 메뉴판
        손님 - 요리사
        요리사 - 요리
- 3. 객체 타입
        손님 타입
        요리 타입
        메뉴판 타입
        메뉴 타입
 */
public class CustomerTest {
    @Test
    @DisplayName("메뉴 이름에 해당하는 요리를 주문한다.")
    void orderTest() {
        Customer customer = new Customer();
        Menu menu = new Menu(List.of(new MenuItem("돈까스",5000),new MenuItem("냉면", 7000)));
        Cooking cooking = new Cooking();

        assertThatCode(() -> customer.order("돈까스", menu, cooking))
                .doesNotThrowAnyException();
    }
}
