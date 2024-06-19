package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {


    @Test
    void totalPriceInEmptyShoppingCartShoulReturn0(){
        ShoppingCart shoppingCart = new ShoppingCart();
        assertThat(shoppingCart.totalPrice()).isZero();
    }

    @Test
    void totalPriceInShoppingCartWithOnlyOneItem(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(new CartItem("product1",1,2.5));
        assertThat(shoppingCart.totalPrice()).isEqualTo(2.5);
    }

    @Test
    void totalPriceInShoppingWithManyItems(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(new CartItem("product1",3,2.5));
        shoppingCart.add(new CartItem("product2",2,1.5));
        shoppingCart.add(new CartItem("product3",4,4.5));
        assertThat(shoppingCart.totalPrice()).isEqualTo(28.5);
    }



}