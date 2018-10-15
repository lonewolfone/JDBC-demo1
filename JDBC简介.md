## JDBC

### 1、什么是JDBC

​	操作数据的技术。

​	JDBC（Java Data Base Connectivity,java数据库连接）是一种用于执行SQL语句的Java API，可以为多种关系数据库提供统一访问，它由一组用Java语言编写的类和接口组成。JDBC提供了一种基准，据此可以构建更高级的工具和接口，使数据库开发人员能够编写数据库应用程序。

​	( :是 Java 提供的一些接口,这些接口大部分是数据库厂商提供的(jar包),我们要做的,是连接数据库以后,如何使用Java代码从数据库中存取数据! )

### 2、持久化和持久化技术

- 什么是持久化

  - 一般数据：保存在数据库中 ，使用时保存在内存中

  - 持久化：将数据保存在可掉电式存储设备中供使用。(将内存中的数据保存到硬盘上加以“固化”)

    ​		实现过程：通过各种关系数据库来完成

    ​		主要应用：将内存中的数据存储在关系型数据库中，也可以存储在磁盘文件、XML数据文件中。

    ------

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\30.png)

  - 当当

- 当当 

### 3、在Java中，数据库存储技术只能通过JDBC访问数据库

JDBC访问数据库的形式主要有两种：

- 直接使用JDBC的API去访问数据库服务器(MySQL/Oracal)
- 间接地使用JDBC的API去访问数据库服务器(MySQL/Oracal)
  - 第三方O/R Mapping 工具，如 Hibernate, MyBatis等(底层依然是JDBC).JDBC是java访问数据库的基石，其他技术都是对jdbc的封装。
- dd

### 4、JDBC规范

- JDBC本身是java连接数据库的一个标准，是进行数据库链接的抽象层，由java编写的一组类和接口组成，接口的实现由各个数据库厂商来完成。

- 不使用第三方框架，使用原生JDBC,自己在JDBC上做封装，加快访问数据库的速度。

- 规范即接口，接口即规范。

- Java提供JDBC接口，数据库厂商提供接口实现。

- Java 面向对象语言，接口是它的规范，接口可有 不同的实现( 即：不同数据库厂商提供不同的实现 )，开发人员可使用接口的多态，去创建对象，再使用接口调用方法。

- 当当

### 5、JDBC的API支持两层处理模式进行数据库访问

- JDBC API : 提供了应用程序对JDBC管理器的链接。抽象接口，供开发人员使用。(链接数据库，执行Sql语句，获取结果)

- JDBC Driver API : 提供了JDBC管理器对驱动程序的链接。供开发商开发数据库驱动程序使用。

- JDBC API 使用驱动程序管理器 和 数据库特定的驱动程序来提供异构数据库的透明连接。

- JDBC 驱动程序管理器可确保正确的驱动程序来访问每个数据源。该驱动程序管理器能够支持连接到多个异构数据库的多个并发的驱动程序。

- 图解

  ​    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\31.png)

  - 开发人员通过：JAVA  APPLICATION 
  - 调用               ：JDBC   API
  - API 中 有一个 Driver Manager 驱动管理器
  - 可使用 不同数据库厂商 提供的不同包，实现与数据库的连接

- JDBC的API在哪里？

  - JDK的API中 ，java.sql 包装的就是 JDBC 的API.  

  - 各大数据库厂就会对 JDBC 的API 提供实现类。在开发中(编写的Java代码)，使用到的 JDBC的 接口/类  全部引用到的是 Java.sql 包中的。

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\32.png)

  - 当当

- 当当
### 6、常见的JDBC组件

JDBC 的 API提供了以下的接口和类：

- DriverManager:

- Driver:

- Connection:

- Statement:

- ResultSet:

- SQLException:

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\33.png)

- DD

### 7、JDBC完成CRUD

- #### 获取数据库

  - ##### 加入驱动包

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\34.png)

    ------

    **设置字体大小**

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\35.png)

  - ##### 加载与注册JDBC驱动

    - 加载JDBC驱动需调用Class类的静态方法forName(),向其投递要加载的JDBC驱动的类名

    - DriverManager类是驱动程序管理器类，负责管理驱动程序

    - 通常不用显示调用DriverManager类的registerDriver()方法来注册驱动程序类的实例，因Driver接口的驱动程序类都包含了静态代码块，在这个静态代码块中，会调用DriverManager.registerDriver(Driver driver)方法来注册自身的一个实例。

      ```java
      //加载和注册驱动，通常使用的方法。
      		//Oracle的加载和注册驱动
      		String oracleDriver = "oracle.jdbc.driver.OracleDriver";//数据库的驱动
      		Class.forName(oracleDriver);//加载和注册驱动
      		//Mysql的加载和注册驱动
      		String mysqlDriver = "com.mysql.jdbc.Driver";
      		Class.forName(mysqlDriver);
      ```

    - 当当

  - ##### 获取数据库链接

    目的：获取链接数据库的驱动，通过驱动管理器:DriverManager,其中的一个方法:getConnection(),得到一个Connection对象，即得到数据库的链接

    - 数据库的URL

    - 数据库用户名

    - 数据库密码

      ```java
      //获取Mysql的数据库连接
      		String url = "jdbc:mysql://localhost:3306/mysql";
      		(写法：String url = "jdbc:mysql:///mysql";)
      		String username = "root";
      		String password = "root";
      		//得到 Connection对象
      		Connection conn = DriverManager.getConnection(url, username, password);
      		System.out.println(conn);
      //获取Oracle的数据库链接
      				String url = "jdbc:oracle:thin:@192.168.124.41:1521:orcl";
      				String username ="scott";
      				String password = "tiger";
      				Connection conn = DriverManager.getConnection(url, username, password);
      				System.out.println(conn);
      ```

      所遇到的问题

      ```java
      Fri Oct 12 09:35:21 CST 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
      Exception in thread "main" com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown database 'mm'
      ```

      报错如图:

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\36.png)

      ------

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\37.png)

      ------

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\38.png)

    - 结果如图所示

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\39.png)

    - 常用的几个较为重要的参数：

      ```java
      String url = "jdbc:mysql:///mysql?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
      
      ```

    - 当当

  - ##### 访问数据库

    - 数据库连接被用于向数据库服务器发送命令和SQL语句，在连接建立后，需要对数据库进行访问，执行SQL语句。

    - 在java.sql包中有3个接口分别定义了对数据库的调用的不同方式:

      - Statement		  

      - PrePatedStatement:预编译语句

      - CallableStatement  :存储工程函数

        ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\40.png)

      - Statement的功能在于根据传入的sql语句，将传入sql经过整理组合成数据库能够识别的sql语句(对于静态的sql语句，不需要整理组合：对于预编译sql语句和批量语句，则需要整理)，然后传递sql请求，得到返回结果。对于查询sql,结果会以ResultSet的形式返回。

        ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\41.png)

      - 当当

    - 当当

  - ##### 创建Statement对象，执行sql语句

    据跟新与否的角度，sql语句分为：查询类别和更新类别，即：

    查询类别：select

    更新类别 : Insert 、update、delete 语句

    ```java
    //执行静态sql
    				//获取Statement对象
           		    Statement statement = conn.createStatement();
    				String sql = "insert into stu(name,age) values('艾小羊',33)";
    				boolean  str =statement.execute(sql);
    				System.out.println(str);
    				String sql1 = "delete from stu where id = 2";
    				long str1 = statement.executeLargeUpdate(sql1);
    				System.out.println(str1);
    				String sql2 = "update stu set name = '宙斯' where id = 1";
    				long str2 = statement.executeLargeUpdate(sql2);
    				System.out.println(str2);
    				sta.addBatch(sql);
    				sta.addBatch(sql1);
    				int[] str3 = statement.executeBatch();
    				for(int a:str3) {
    					System.out.println(a);
    				}
    ```

  - 当当

- #### 查询语句

  - ##### 查询一条/行

    ```java
     //执行静态sql,查询语句
            String sql = "select * from stu where id = 1";
            ResultSet res =	sta.executeQuery(sql);
            boolean b =  res.next();//判断是否存在下一条记录
            System.out.println(b);
            //int id = res.getInt(1);//根据列的索引来获取列值
            //System.out.println(id);
            int id1 = res.getInt("id");//根据字段名来获取该字段对应的值
            System.out.println(id1);
            String name =  res.getString(2);
            System.out.println(name);
            int age = res.getInt(3);
            System.out.println(age);
            String sex = res.getString("sex");
            System.out.println(sex);
    ```

    结果如图:

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\42.png)

    ------

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\43.png)

  - ##### 查询多条记录

    ```java
     //执行静态sql,查询语句,查询多条记录
            String sql1 = "select * from stu ";
            ResultSet res1 =sta.executeQuery(sql1);
            while (res1.next()){
                int sid = res1.getInt("id");
                String sname =  res1.getString("name");
                int sage = res1.getInt("age");
                String ssex = res1.getString("sex");
                System.out.println(sid + "--"+ sname +"--" +sage +"--" +ssex);
            }
    ```

    执行结果如图：

    ​    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\44.png)

  - 当当

- #### 对用户密码进行加密

  ```java
  //对于用户的密码验证 当用户在登陆界面输入密码之后，使用Java代码进行一次md5的加密，加密后与数据库中的加密密码进行比较
              //用户从界面输入密码
              String userpaw = "12345";
              MessageDigest messageDigest = MessageDigest.getInstance("md5");//获取摘要对象
              messageDigest.update(userpaw.getBytes("utf-8"));//更新摘要
              String ss = new BigInteger(1,messageDigest.digest()).toString(16);//获取加密后的字符串
  			System.out.println(ss);
  ```

- #### 如何得到插入信息的自增长的id值

  ```java
  //执行静态sql
          //如何得到插入信息的自增长的id值
          String sql = "insert into stu(name,age) values('小陪陪',33)";
          boolean  str = statement.execute(sql,Statement.RETURN_GENERATED_KEYS);
          ResultSet res = statement.getGeneratedKeys();
          if (res.next()){
              System.out.println(res.getInt(1));
          }
  ```

- #### 释放资源:对于数据库的连接属于重量级操作，比较消耗资源

  ```java
  String mysqlDriver = "com.mysql.jdbc.Driver";
          Connection conn = null;
          Statement  statement = null;
          ResultSet resultSet = null;
          try {
              Class.forName(mysqlDriver);
              String url = "jdbc:mysql:///test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
              String username = "root";
              String password = "root";
              //得到 Connection对象
              conn = DriverManager.getConnection(url, username, password);
              statement = conn.createStatement();
              String sql = "select * from stu";
              resultSet = statement.executeQuery(sql);
              while (resultSet.next()){
                  System.out.println(resultSet.getInt("id") + "----"+resultSet.getString("name") + "----"+ resultSet.getString("sex"));
              }
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          } catch (SQLException e) {
              e.printStackTrace();
          }finally {
              try {
                  conn.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }finally {
                  try {
                      statement.close();
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }finally {
                      try {
                          resultSet.close();
                      } catch (SQLException e) {
                          e.printStackTrace();
                      }
                  }
              }
  
          }
  ```

- #### 如何将从数据库中查询到的数据展示到界面

  将表中记录封装为对象

  ***将所有记录全部拿到，封装到一个List中，传递数据时，只需传一个LIst即可：完成对数据的处理**

  ```java
  public class reslesSouce {
      public static void  main(String[] args){
          String mysqlDriver = "com.mysql.jdbc.Driver";
          Connection conn = null;
          Statement  statement = null;
          ResultSet resultSet = null;
          try {
              Class.forName(mysqlDriver);
              String url = "jdbc:mysql:///test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
              String username = "root";
              String password = "root";
              //得到 Connection对象
              conn = DriverManager.getConnection(url, username, password);
              statement = conn.createStatement();
              String sql = "select * from stu";
              resultSet = statement.executeQuery(sql);
              //创建一个List对象
              List<Student> studentList = new ArrayList<>();
              //将表中记录封装为对象
              while (resultSet.next()){
                  Student student = new Student();
                  student.setId(resultSet.getInt("id"));
                  student.setName(resultSet.getString("name"));
                  student.setAge(resultSet.getInt("age"));
                  student.setSex(resultSet.getString("sex"));
                  student.setPassword(resultSet.getString("password"));
                  //将每次得到的对象放到List中
                  studentList.add(student);
              }
              //使用for循环，遍历List
              for(Student str:studentList){
                  System.out.println(str);
              }
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          } catch (SQLException e) {
              e.printStackTrace();
          }finally {
              try {
                  conn.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }finally {
                  try {
                      statement.close();
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }finally {
                      try {
                          resultSet.close();
                      } catch (SQLException e) {
                          e.printStackTrace();
                      }
                  }
              }
  
          }
      }
  }
  ```

- 总结：对数据库的操作

  - 加载和注册驱动

    ```java
    String driverClassName = "";
    Class.forname();
    ```

  - 获取数据库连接Connection

    ```java
    三个参数
    url = "jdbc:mysql://localhost:3306/database_name"
    username
    password
    //获取Connection对象
    Connection conn = DriverManager.getConnection(url,username,password);
    ```

  - 获取Statement对象:sql语句的执行器，执行sql;Connection对象相当于：从Java 到 数据库 的公路，Statement对象：将sql语句发送给数据库，在数据库中执行，执行结果返回回来。

    ```java
    Statement statement = conn.createStatement();
    ```

  - 执行SQL语句：创建SQL语句，让Statement对象发送给数据库执行

    ```java
    String sql = " ";
    //方法
    boolean execute(sql);//可执行任何sql语句，返回一个结果集，若有结果集返回一个true,反之为false.
    executeQuery(sql);//查询语句
    executeUpdate(sql);
    ```

  - 结果集的处理

    ```java
    ResultSet res = statement.executeQuery(sql);
    next();//判断是否有下一条
    //获取数据，(通过结果集的~方法
    getInt();
    getString();
    ```

    - 查询返回一个结果集，结果集相当于一张表，会有一个指针(或光标)指向当前表的开始(头部，并不是第一行)。

    - next() 方法，判断是否有下一条记录，若有，光标向下移动一行。

    - 通过结果集的get方法，从表中获取数据

      ```
      若字段为int型，对应getInt();
      若字段为String型，对应getString();
      每个方法有两种重载方式，通过列的索引获取；
      					通过列的标签获取；
      ```

    - 对于表中结果，完成了对象关系映射。若得到的结果集为多条记录，需将数据库中每条记录封装为一个对象，需要一个与数据库结构所对应的JavaBean(接口的Java类)，有了类之后，可创建对象。若有多个对象，可将其放在List中

    - 释放资源

      ```java
      close();
      ```

      - Connection、Statement、ResulSet为重量级对象，在创建对象时需要的资源和时间比较多，为防止内从泄露和资源浪费，需要关闭资源。
      - dd

    - 当当

  - 当当

  - 当当

- 小结

  - 接口不能创建对象，里边方法为抽象方法
  - 连接数据库的目的：数据的持久化       数据库服务器
  - 数据库持久化框架：ORM
  - 面向对象映射：对象映射到数据库中，完成数据交互
  - 当当

- 当当

### 8、在实际开发中，如何对数据进行操作？实例：通过数据库完成对数据表的增删改查(CRUD)操作。

- ***使用面向对象的思想(封装、继承、多态)，完成对数据库的CURD操作***

- #### 步骤

  - ##### 操作的表，完成数据库的设计

  - ##### 将表映射成Java中的类

    - 包名后加模块名(规范)：org.lanqiao.jdbc._??__(entity/domain/pojo)

    - 将：表  映射为  对象 ，字段  映射为  对象的属性 

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\45.png)

      ------

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\46.png)

      ```java
      public class Product {
          private int id;
          private String productName;
          private int dir_id;
          private double salePrice;
          private String supplier;
          private String brand;
          private double cutoff;
          private double costPrice;
      
          public Product(){
      
          }
      
          public Product(String productName, int dir_id, double salePrice, String supplier, String brand, double cutoff, double costPrice) {
              this.productName = productName;
              this.dir_id = dir_id;
              this.salePrice = salePrice;
              this.supplier = supplier;
              this.brand = brand;
              this.cutoff = cutoff;
              this.costPrice = costPrice;
          }
      
          public Product(int id, String productName, int dir_id, double salePrice, String supplier, String brand, double cutoff, double costPrice) {
              this.id = id;
              this.productName = productName;
              this.dir_id = dir_id;
              this.salePrice = salePrice;
              this.supplier = supplier;
              this.brand = brand;
              this.cutoff = cutoff;
              this.costPrice = costPrice;
          }
      
          public int getId() {
              return id;
          }
      
          public void setId(int id) {
              this.id = id;
          }
      
          public String getProductName() {
              return productName;
          }
      
          public void setProductName(String productName) {
              this.productName = productName;
          }
      
          public int getDir_id() {
              return dir_id;
          }
      
          public void setDir_id(int dir_id) {
              this.dir_id = dir_id;
          }
      
          public double getSalePrice() {
              return salePrice;
          }
      
          public void setSalePrice(double salePrice) {
              this.salePrice = salePrice;
          }
      
          public String getSupplier() {
              return supplier;
          }
      
          public void setSupplier(String supplier) {
              this.supplier = supplier;
          }
      
          public String getBrand() {
              return brand;
          }
      
          public void setBrand(String brand) {
              this.brand = brand;
          }
      
          public double getCutoff() {
              return cutoff;
          }
      
          public void setCutoff(double cutoff) {
              this.cutoff = cutoff;
          }
      
          public double getCostPrice() {
              return costPrice;
          }
      
          public void setCostPrice(double costPrice) {
              this.costPrice = costPrice;
          }
      
          @Override
          public boolean equals(Object o) {
              if (this == o) return true;
              if (o == null || getClass() != o.getClass()) return false;
              Product product = (Product) o;
              return id == product.id &&
                      dir_id == product.dir_id &&
                      Double.compare(product.salePrice, salePrice) == 0 &&
                      Double.compare(product.cutoff, cutoff) == 0 &&
                      Double.compare(product.costPrice, costPrice) == 0 &&
                      Objects.equals(productName, product.productName) &&
                      Objects.equals(supplier, product.supplier) &&
                      Objects.equals(brand, product.brand);
          }
      
          @Override
          public int hashCode() {
              return Objects.hash(id, productName, dir_id, salePrice, supplier, brand, cutoff, costPrice);
          }
      
          @Override
          public String toString() {
              return "Product{" +
                      "id=" + id +
                      ", productName='" + productName + '\'' +
                      ", dir_id=" + dir_id +
                      ", salePrice=" + salePrice +
                      ", supplier='" + supplier + '\'' +
                      ", brand='" + brand + '\'' +
                      ", cutoff=" + cutoff +
                      ", costPrice=" + costPrice +
                      '}';
          }
      }
      ```

    - 当当

  - ##### Dao层：数据访问层，创建接口

    - 接口中只有方法的声明，即抽象类

    - 接口的命名规范：I类名Dao  , 如： IProductDao

    - 创建目录的步骤

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\47.png)

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\48.png)

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\49.png)

    - IProductDao

      ```
      public interface IProduct {
          //1.数据的查询
          //1.1根据条件查询记录
          //1.1.1根据ID查询
          public Product getProductById(int id);
          //1.1.2根据产品名称查询(得到多条记录，返回一个List集合)
          public List<Product> getProductByName(String productName);
          //1.2查询所有的记录
          public List<Product> getAll();
          //2.数据的更新
          public void updateProductById(int id);
          //3.数据的删除
          public void deleteProductById(int id);
          //4.新增数据
          public void insertProduct(Product product);
      }
      ```

    - 当当

  - 接口的实现(实现类)：productDaoImpl

    - 创建目录

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\50.png)

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\51.png)

    - 根据ID查询一条记录

      ```
       @Override//根据ID查询
          public Product getProductById(int id) throws ClassNotFoundException, SQLException {
              String dirverClassName = "com.mysql.jdbc.Driver";
              Class.forName(dirverClassName);
              String url = "jdbc:mysql://localhost:3306/test";
              String user = "root";
              String password = "root";
              Connection connection = DriverManager.getConnection(url,user,password);
              Statement statement = connection.createStatement();
              String sql = "select * from product where id =" +id;
              ResultSet resultSet = statement.executeQuery(sql);
              Product product = new Product();
              while (resultSet.next()){
                  product.setId(resultSet.getInt("id"));
                  product.setProductName(resultSet.getString("productName"));
                  product.setDir_id(resultSet.getInt("Dir_id"));
              }
              return null;
          }
      ```

    - 根据名字查询

      ```java
      @Override//根据名字查询
          public List<Product> getProductByName(String productName) throws ClassNotFoundException, SQLException {
              String dirverClassName = "com.mysql.jdbc.Driver";
              Class.forName(dirverClassName);
              String url = "jdbc:mysql:///test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
              String user = "root";
              String password = "root";
              Connection connection = DriverManager.getConnection(url,user,password);
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
              return productList;
          }
      ```

    - 查询所有

      ```java
       @Override//查询所有
          public List<Product> getAll() throws ClassNotFoundException, SQLException {
      
              String dirverClassName = "com.mysql.jdbc.Driver";
              Class.forName(dirverClassName);
              String url = "jdbc:mysql:///test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
              String user = "root";
              String password = "root";
              Connection connection = DriverManager.getConnection(url,user,password);
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
              return productList;
          }
      ```

    - 更新

      ```java
      @Override//更新
          public void updateProductById(Product product) throws SQLException, ClassNotFoundException {
              String dirverClassName = "com.mysql.jdbc.Driver";
              Class.forName(dirverClassName);
              String url = "jdbc:mysql:///test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
              String user = "root";
              String password = "root";
              Connection connection = DriverManager.getConnection(url,user,password);
              Statement statement = connection.createStatement();
              String sql = "update product set salePrice = "+product.getSalePrice()+"where id ="+product.getId();
              statement.execute(sql);
          }
      ```

    - 当当

    - 当当

  - ##### 创建一个测试类

    ```java
    public class Test {
        public static void main(String[] args) throws SQLException, ClassNotFoundException {
            IProduct iProductp = new productDaoImpl();
            //根据ID查询
            Product product = iProductp.getProductById(1);
            System.out.println(product);
            System.out.println("------------------------------------------------------");
            //根据产品名称查询
            List<Product> productList = iProductp.getProductByName("M");
            for (Product product1:productList){
                System.out.println(product1);
            }
            System.out.println("-------------------------------------------------------");
            //查询所有
            List<Product> productAll = iProductp.getAll();
            for (Product productList2:productAll){
                System.out.println(productList2);
            }
            System.out.println("------------------------------------------------------------");
            //更新
            Product product3 = iProductp.getProductById(1);
             product3.setSalePrice(666);
             iProductp.updateProductById(product3);
        }
    }
    ```

  - ##### 解决代码冗余问题:使用配置文件properties完成对jdbc代码的重构

    - 工具类：utils

    - 一般将其属性和方法设为静态的，在使用时无需创建对象即可使用。

    - (加载和注册驱动)，在类加载时，加载到类中即可，将其写为静态代码块，作为类的成员属性

      工具类Utils中：

      ```java
      public class Utils {
          private static  String driverClassName;
          private static String url;
          private static  String user ;
          private static String password ;
      
          static {
              //读取文件，得到一个输入流
              InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream("jdbc.properties");
              //创建Properties对象(属性对象)
              Properties properties = new Properties();
              try {
                  //将属性流加载到属性对象中
                  properties.load(inputStream);
                  //获取其中内容
                  driverClassName = properties.getProperty("driverClassName");
                  url = properties.getProperty("url");
                  user = properties.getProperty("user");
                  password = properties.getProperty("password");
              } catch (IOException e) {
                  e.printStackTrace();
              }
              try {
                  Class.forName(driverClassName);
              } catch (ClassNotFoundException e) {
                  e.printStackTrace();
              }
          }
      
          public static Connection getConnection(){
              Connection connection = null;
              try {
                  connection = DriverManager.getConnection(url,user,password);
              } catch (SQLException e) {
                  e.printStackTrace();
              }
              return connection;
          }
      
          public static void releaseSource(ResultSet resultSet, Statement statement,Connection connection){
              try {
                  if (resultSet != null){
                      resultSet.close();
                  }
              } catch (SQLException e) {
                  e.printStackTrace();
              }finally {
                  try {
                      if (statement != null){
                          statement.close();
                      }
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }finally {
                      try {
                          if (connection != null){
                              connection.close();
                          }
                      } catch (SQLException e) {
                          e.printStackTrace();
                      }
                  }
              }
          }
      }
      ```

      实现类 productDaoImpl 类中：（其他雷同）

      ```java
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
      ```

    - 配置文件：properties

      - 创建

      - 读取配置文件

      - 放在静态代码块中，随类的加载，获取属性值

      - 代码：

        ```properties
        
        ```

      - 新建工程/项目默认输出目录：bin ,所有Class文件在编译后放在bin下

      - 当当

    - 当当

  - 当当

- 当当

- 当当