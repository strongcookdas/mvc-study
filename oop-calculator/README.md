### 계산기 mvc

- 테스트 코드를 작성함으로써 리팩토링에 대한 안정감을 주었다.
- 리팩토링 과정
    1. `Calculator` 클래스에서 operand와 operator를 받아 if문으로 연산을 하는 사칙연산 클래스를 생성
    2. `Calculator` 에서 +, -, *, / 연산자를 필터링하여 연산을 수행하는 것이 많은 책임을 부여한다.
    3. Enum 타입의 `ArithmeticOperator` 을 생성하여 연산자에 따른 기능을 정의하고 `calculation` 이라는 메서드를 통해서 연산자를 필터링한다. 이것을 `Calculator` 클래스에서 사용
    4. Enum 타입 대신에 연산자에 대한 상위 인터페이스인 `NewArithmeticOperator`를 정의하고 해당 인터페이스를 상속받는 구현 클래스를 생성하여 책임 분배
    5. `Calculator` 클래스는 `NewArithmeticOperator` 를 의존하여 다형성을 구현한다.
    6. operand는 양수만 받을 수 있는데 양수를 체크하는 `PositiveNumber`  클래스를 구현하여 `Calculator` 의 `calculator` 메서드 매개변수 타입으로 사용
- 리팩토링을 하면서 테스트 코드를 실행하고 체크함으로써 버그를 예방

### 참고 자료

- [패스트 캠퍼스] 나만의 프레임워크 만들기