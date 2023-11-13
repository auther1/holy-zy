package petShop.persistence.impl;

import petShop.domain.Category;
import petShop.persistence.DBUtil;
import petShop.persistence.Dao.CategoryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private static final String GET_CATEGORY_LIST =
            "SELECT CATID AS categoryId,NAME,DESCN AS description FROM CATEGORY";
    private static final String GET_CATEGORY =
            "SELECT CATID AS categoryId,NAME,DESCN AS description FROM CATEGORY WHERE CATID = ?";

    @Override
    //获得列表
    public List<Category> getCategoryList() {
        List<Category> result = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_CATEGORY_LIST);
            while(resultSet.next()){
                Category category = new Category();
                category.setCategoryId(resultSet.getString("categoryId"));
                category.setName(resultSet.getString("NAME"));
                category.setDescription(resultSet.getString("description"));
                result.add(category);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStaement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    //根据categoryId获得List
    public Category getCategory(String categoryId) {
        Category result = new Category();
        try {
            Connection connection = DBUtil.getConnection();
            //不是一个完整的句子，需要填词，所以用的preparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY);
            preparedStatement.setString(1,categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result.setCategoryId(resultSet.getString("categoryId"));
                result.setName(resultSet.getString("NAME"));
                result.setCategoryId(resultSet.getString("description"));
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
