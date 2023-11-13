package petShop.persistence.Dao;

import petShop.domain.LineItem;

import java.util.List;

public interface LineItemDao {

    List<LineItem> getLineItemsByOrderId(int orderId);

    void insertLineItem(LineItem lineItem);

}
