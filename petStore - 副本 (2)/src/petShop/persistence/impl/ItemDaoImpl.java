package petShop.persistence.impl;

import petShop.domain.Item;
import petShop.persistence.DBUtil;
import petShop.persistence.Dao.ItemDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//注意：
//  由于并不了解updateInventoryQuantity函数的作用及其参数的意义，该函数的PreparedStatement并无意义
//  还可继续改善


public class ItemDaoImpl implements ItemDao {

    private static final String UPDATE_INVENTORY_QUANTITY =
            "UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?";
    private static final String GET_INVENTORY_QUANTITY =
            "SELECT QTY AS value FROM INVENTORY WHERE ITEMID = ?";
    private static final String GET_ITEM_LIST_BY_PRODUCT =
            "SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS \"product.productId\",NAME AS \"product.name\"," +
                    "DESCN AS \"product.description\",CATEGORY AS \"product.categoryId\",STATUS,ATTR1 AS attribute1,ATTR2 AS attribute2," +
                    "ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5 " +
                    "FROM ITEM I, PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID = ?";
private static final String GET_ITEM =
        "SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS \"product.productId\",NAME AS \"product.name\"," +
                "DESCN AS \"product.description\",CATEGORY AS \"product.categoryId\",STATUS,ATTR1 AS attribute1,ATTR2 AS attribute2," +
                "ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5,QTY AS quantity " +
                "from ITEM I, INVENTORY V, PRODUCT P where P.PRODUCTID = I.PRODUCTID and I.ITEMID = V.ITEMID and I.ITEMID = ?";

    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVENTORY_QUANTITY);
            preparedStatement.setString(1,"default");
            preparedStatement.setString(2,"default");
            preparedStatement.execute();
            DBUtil.closePreparedStaement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        int result = 0;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_INVENTORY_QUANTITY);
            preparedStatement.setString(1,itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getInt("value");
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStaement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> result = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_LIST_BY_PRODUCT);
            preparedStatement.setString(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Item item = new Item();
                item.setItemId(resultSet.getString("ITEMID"));
                item.setListPrice(resultSet.getBigDecimal("LISTPRICE"));
                item.setUnitCost(resultSet.getBigDecimal("UNITCOST"));
                item.setSupplierId(resultSet.getInt("supplierId"));
                item.getProduct().setProductId(resultSet.getString("product.productId"));
                item.getProduct().setName(resultSet.getString("product.name"));
                item.getProduct().setDescription(resultSet.getString("product.description"));
                item.getProduct().setCategoryId(resultSet.getString("product.categoryId"));
                item.setStatus(resultSet.getString("STATUS"));
                item.setAttribute1(resultSet.getString("attribute1"));
                item.setAttribute2(resultSet.getString("attribute2"));
                item.setAttribute3(resultSet.getString("attribute3"));
                item.setAttribute4(resultSet.getString("attribute4"));
                item.setAttribute5(resultSet.getString("attribute5"));
                item.setProductId(productId);
                result.add(item);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStaement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item getItem(String itemId) {
        Item result = new Item();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM);
            preparedStatement.setString(1,itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.setItemId(resultSet.getString("ITEMID"));
                result.setListPrice(resultSet.getBigDecimal("LISTPRICE"));
                result.setUnitCost(resultSet.getBigDecimal("UNITCOST"));
                result.setSupplierId(resultSet.getInt("supplierId"));
                result.getProduct().setProductId(resultSet.getString("product.productId"));
                result.getProduct().setName(resultSet.getString("product.name"));
                result.getProduct().setDescription(resultSet.getString("product.description"));
                result.getProduct().setCategoryId(resultSet.getString("product.categoryId"));
                result.setStatus(resultSet.getString("STATUS"));
                result.setAttribute1(resultSet.getString("attribute1"));
                result.setAttribute2(resultSet.getString("attribute2"));
                result.setAttribute3(resultSet.getString("attribute3"));
                result.setAttribute4(resultSet.getString("attribute4"));
                result.setAttribute5(resultSet.getString("attribute5"));
                result.setQuantity(resultSet.getInt("quantity"));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStaement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
