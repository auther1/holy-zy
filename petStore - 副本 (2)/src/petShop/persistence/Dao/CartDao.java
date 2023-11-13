package petShop.persistence.Dao;

import petShop.domain.CartItem;
import petShop.domain.Item;

import java.util.List;

public interface CartDao {
    void createNewCart(String userName);

    void insertItem(Item item,String cartName);

    void removeItemByItemId(String itemId,String cartName);

    List<CartItem> getAllItems(String cartName);

    void updateAmount(CartItem cartItem,String cartName);
}
