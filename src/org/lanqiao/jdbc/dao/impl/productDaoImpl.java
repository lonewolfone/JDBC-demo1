package org.lanqiao.jdbc.dao.impl;

import org.lanqiao.jdbc.dao.IProduct;
import org.lanqiao.jdbc.entity.Product;
import org.lanqiao.jdbc.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class productDaoImpl implements IProduct {

    @Override//根据ID查询
    public Product getProductById(int id) throws  Exception {
        Connection connection =Utils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select * from product where id =" +id;
        ResultSet resultSet = statement.executeQuery(sql);
        Product product = new Product();
        while (resultSet.next()){
            product.setId(resultSet.getInt("id"));
            product.setProductName(resultSet.getString("productName"));
            product.setDir_id(resultSet.getInt("Dir_id"));
            product.setSalePrice(resultSet.getDouble("salePrice"));
        }
        Utils.releaseSource(resultSet,statement,connection);
        return product;
    }

    @Override//根据名字查询
    public List<Product> getProductByName(String productName) throws Exception {
        Connection connection =Utils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select  * from product where productName like '% "+productName +" %' ";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()){
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setProductName(resultSet.getString("productName"));
            product.setDir_id(resultSet.getInt("Dir_id"));
            productList.add(product);
        }
        Utils.releaseSource(resultSet,statement,connection);
        return productList;
    }

    @Override//查询所有
    public List<Product> getAll() throws Exception {
        Connection connection =Utils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select  * from product ";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()){
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setProductName(resultSet.getString("productName"));
            product.setDir_id(resultSet.getInt("Dir_id"));
            productList.add(product);
        }
        Utils.releaseSource(resultSet,statement,connection);
        return productList;
    }

    @Override//更新
    public void updateProductById(Product product) throws Exception {
        Connection connection =Utils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "update product set salePrice = "+product.getSalePrice()+"where id ="+product.getId();
        statement.execute(sql);
        Utils.releaseSource(null,statement,connection);

    }

    @Override//删除
    public void deleteProductById(int id) throws Exception {
        Connection connection =Utils.getConnection();
        Statement statement = connection.createStatement();
    }

    @Override
    public void insertProduct(Product product) {

    }
}
