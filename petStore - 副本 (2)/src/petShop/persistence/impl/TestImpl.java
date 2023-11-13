package petShop.persistence.impl;


import javax.servlet.http.HttpServlet;

//本文件用于测试Impl是否可用
public class TestImpl extends HttpServlet {

    public static void main(String[] args) {
        //AccountDaoImpl测试


        //CategoryImpl测试
        /*CategoryDaoImpl categoryDao = new CategoryDaoImpl();
        System.out.println(categoryDao.getCategoryList().get(1).getName());
        System.out.println(categoryDao.getCategory("BIRDS").getName());*/

        //ItemDaoImpl测试
        /*ItemDaoImpl itemDao = new ItemDaoImpl();
        System.out.println(itemDao.getItem("EST-1").getProductId());*/

        //LineItemDaoImpl测试
        /*LineItemDaoImpl lineItemDao = new LineItemDaoImpl();
        lineItemDao.getLineItemsByOrderId(0);*/

        //ProductDaoImpl测试
        /* ProductDaoImpl productDao = new ProductDaoImpl();
        System.out.println(productDao.getProductListByCategory("BIRDS").get(0).getName());
        System.out.println(productDao.getProduct("AV-CB-01").getName());*/
        //System.out.println(productDao.searchProductList("AV-CB-01").get(0).getName());


        //SequenceDaoImpl测试
        /*SequenceDaoImpl sequenceDao = new SequenceDaoImpl();
        System.out.println(sequenceDao.getSequence(new Sequence("ordernum",100)).getName());*/

        /*CatalogService catelogService = new CatalogService();
        String categoryId = "BIRDS";
        Category category = catelogService.getCategory(categoryId);
        List<Productt> productList = catelogService.getProductListByCategory(categoryId);
        System.out.println(productList.get(0).getName());*/

        /*CategoryFormServlet categoryFormServlet = new CategoryFormServlet();
        categoryFormServlet.test();*/
    }
}

//Order由于其初始数据库为空，不测试

