package petShop.persistence.impl;

import petShop.domain.Cart;
import petShop.domain.CartItem;
import petShop.domain.Item;
import petShop.persistence.DBUtil;
import petShop.persistence.Dao.CartDao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private static final String GET_ALL_ITEMS="SELECT * FROM %s";

    private static final String REMOVE_ITEM="DELETE FROM %s WHERE ITEMID = '%s'";

    private static final String INSERT_ITEM="INSERT INTO %s VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String CREATE_TABLE="CREATE TABLE IF NOT EXISTS %s (ITEMID VARCHAR(10) NOT NULL , PRODUCTID VARCHAR(10), LISTPRICE DECIMAL(10,2)," +
            "UNITCOST DECIMAL(10,2), SUPPLIER INT, STATUS VARCHAR(2), AMOUNT INT, ATTR1 VARCHAR(80), ATTR2 VARCHAR(80)," +
            "ATTR3 VARCHAR(80), ATTR4 VARCHAR(80), ATTR5 VARCHAR(80))";

    private static final String UPDATE_AMOUNT="UPDATE %s SET AMOUNT = %d WHERE ITEMID = '%s'";

    @Override
    public void insertItem(Item item,String cartName) {
        try{
            //主打一个一问三不知
            //itemid,productid,listprice,unitcost,supplier,status,amount,attr1,attr2,attr3,attr4,attr5
            Connection connection=DBUtil.getConnection();
            Statement statement=connection.createStatement();
            System.out.println("insertItem");
            String sql=String.format(INSERT_ITEM,cartName);
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            //preparedStatement.setString(1,cartName);
            preparedStatement.setString(1,item.getItemId());
            preparedStatement.setString(2,item.getProductId());
            preparedStatement.setBigDecimal(3,item.getListPrice());
            preparedStatement.setBigDecimal(4,item.getUnitCost());
            preparedStatement.setInt(5,item.getSupplierId());
            preparedStatement.setString(6,item.getStatus());
            preparedStatement.setInt(7,1);
            preparedStatement.setString(8,item.getAttribute1());
            preparedStatement.setString(9,item.getAttribute2());
            preparedStatement.setString(10,item.getAttribute3());
            preparedStatement.setString(11,item.getAttribute4());
            preparedStatement.setString(12,item.getAttribute5());
            preparedStatement.execute();
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStaement(preparedStatement);
        } catch (SQLException e) {
            System.out.println("insert into cart fail");
            e.printStackTrace();
        }
    }

    @Override
    public void createNewCart(String userName){
        String cartName=userName+"Cart";
        try{
            Connection connection=DBUtil.getConnection();
            Statement statement=connection.createStatement();
            statement.execute(String.format(CREATE_TABLE,cartName));
            System.out.println("createCart");
            DBUtil.closeConnection(connection);
            DBUtil.closeStaement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeItemByItemId(String itemId,String cartName) {
        try{
            Connection connection=DBUtil.getConnection();
            Statement statement= connection.createStatement();
            String sql=String.format(REMOVE_ITEM,cartName,itemId);
            statement.execute(sql);
            System.out.println("removeItem");
            //DBUtil.closePreparedStaement(preparedStatement);
            DBUtil.closeConnection(connection);
            DBUtil.closeStaement(statement);
        }catch (Exception e){
            System.out.println("delete item in cart fail");
            e.printStackTrace();
        }
    }

    @Override
    public List<CartItem> getAllItems(String cartName) {
        List<CartItem> cartItemList=new ArrayList<CartItem>();
        try{
            Connection connection=DBUtil.getConnection();
            String sql=String.format(GET_ALL_ITEMS,cartName);
            System.out.println("cartName in getAllItems: "+cartName);
            Statement statement=connection.createStatement();
            statement.execute(sql);
            ResultSet resultSet= statement.getResultSet();
            while(resultSet.next()){
                CartItem cartItem=new CartItem();
                Item item=new Item();
                item.setItemId(resultSet.getString("ITEMID"));
                item.setProductId(resultSet.getString("PRODUCTID"));
                item.setListPrice(resultSet.getBigDecimal("LISTPRICE"));
                item.setUnitCost(resultSet.getBigDecimal("UNITCOST"));
                item.setSupplierId(resultSet.getInt("SUPPLIER"));
                item.setStatus(resultSet.getString("STATUS"));
                item.setAttribute1(resultSet.getString("ATTR1"));
                item.setAttribute2(resultSet.getString("ATTR2"));
                item.setAttribute3(resultSet.getString("ATTR3"));
                item.setAttribute4(resultSet.getString("ATTR4"));
                item.setAttribute5(resultSet.getString("ATTR5"));
                cartItem.setQuantity(resultSet.getInt("AMOUNT"));
                cartItem.setItem(item);
                cartItemList.add(cartItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStaement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            System.out.println("get all items in cart fail");
            e.printStackTrace();
        }
        return cartItemList;
    }

    @Override
    public void updateAmount(CartItem cartItem,String cartName) {
        try{
            //"UPDATE %s SET AMOUNT = %d WHERE ITEMID = %s"
            Connection connection=DBUtil.getConnection();
            Statement statement= connection.createStatement();
            String sql=String.format(UPDATE_AMOUNT,cartName,cartItem.getQuantity(),cartItem.getItem().getItemId());
            statement.execute(sql);
            DBUtil.closeConnection(connection);
            DBUtil.closeStaement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        CartDaoImpl cartDao = new CartDaoImpl();
//        CartItem cartItem=new CartItem();
//        Item item = new Item();
//        item.setItemId("11");
//        item.setProductId("22");
//        item.setListPrice(BigDecimal.valueOf(10.12));
//        item.setUnitCost(BigDecimal.valueOf(22.14));
//        item.setSupplierId(10);
//        item.setStatus("2");
//        item.setAttribute1("yes");
//        Item item2 = new Item();
//        item2.setItemId("18");
//        item2.setProductId("22");
//        item2.setListPrice(BigDecimal.valueOf(10.12));
//        item2.setUnitCost(BigDecimal.valueOf(22.14));
//        item2.setSupplierId(10);
//        item2.setStatus("2");
//        item2.setAttribute1("yes");
//        cartItem.setItem(item);
//        cartItem.setQuantity(10);
//        cartDao.updateAmount(cartItem,"j2eecart");
//    }
}
