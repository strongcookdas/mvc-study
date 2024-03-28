package org.example;

public class Customer {
    //menu, cooking 필요해서 전달
    //없으면 주문을 못한다.
    public void order(String menuName, Menu menu, Cooking cooking){
        MenuItem menuItem = menu.choose(menuName);
        Cook cook = cooking.makeCook(menuItem);
    }
}
