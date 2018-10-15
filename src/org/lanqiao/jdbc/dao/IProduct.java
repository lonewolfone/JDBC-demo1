package org.lanqiao.jdbc.dao;

import org.lanqiao.jdbc.entity.Product;

import java.util.List;

public interface IProduct {
    //1.数据的查询
    //1.1根据条件查询记录
    //1.1.1根据ID查询
    public Product getProductById(int id) throws Exception;
    //1.1.2根据产品名称查询(得到多条记录，返回一个List集合)
    public List<Product> getProductByName(String productName) throws  Exception;
    //1.2查询所有的记录
    public List<Product> getAll() throws  Exception;
    //2.数据的更新
    public void updateProductById(Product product) throws  Exception;
    //3.数据的删除
    public void deleteProductById(int id) throws  Exception;
    //4.新增数据
    public void insertProduct(Product product);
}
