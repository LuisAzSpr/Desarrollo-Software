# Pruebas basadas en especificaciones


### ShoppingCartProject





**Implementa y prueba la clase ShoppingCart y CartItem.**

Instrucciones:

• Implementa las clases ShoppingCart y CartItem según la especificación proporcionada.

**ShopingCart**
```java
public class ShoppingCart {
    private List<CartItem> items = new ArrayList<CartItem>();
    public void add(CartItem item) {
        this.items.add(item);
    }
    public double totalPrice() {
        double totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getUnitPrice() * item.getQuantity();
        }
        return totalPrice;
    }
}
``` 

**CarItem**
```java
public class CartItem {
    
    private final String product;
    private final int quantity;
    private final double unitPrice;
    
    public CartItem(String product, int quantity, double unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    
    public String getProduct() {
        return product;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public double getUnitPrice() {
        return unitPrice;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return quantity == cartItem.quantity && Double.compare(cartItem.unitPrice, unitPrice) ==
                0 && product.equals(cartItem.product);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, unitPrice);
    }
}
``` 

Escribe pruebas unitarias para ShoppingCart que verifiquen el cálculo correcto del precio
total.  Asegúrate de probar diferentes escenarios, como:

• Carrito vacío.

```java
@Test
void totalPriceInEmptyShoppingCartShoulReturn0(){
    ShoppingCart shoppingCart = new ShoppingCart();
    assertThat(shoppingCart.totalPrice()).isZero();
}
```

• Carrito con un solo artículo.

```java
@Test
void totalPriceInShoppingCartWithOnlyOneItem(){
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.add(new CartItem("product1",1,2.5));
    assertThat(shoppingCart.totalPrice()).isEqualTo(2.5);
}
```

• Carrito con múltiples artículos

```java
@Test
void totalPriceInShoppingWithManyItems(){
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.add(new CartItem("product1",3,2.5));
    shoppingCart.add(new CartItem("product2",2,1.5));
    shoppingCart.add(new CartItem("product3",4,4.5));
    assertThat(shoppingCart.totalPrice()).isEqualTo(28.5);
}
``` 

Podemos ver que todos pasan

![img.png](Imagenes%2Fimg.png)

