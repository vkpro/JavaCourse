package ru.luxoft.cources.lab12clients;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection getConnection() throws Exception {
        Connection connection = null;
        String host="localhost";
        String port="5432";
        String db_name="testdb";
        String username="postgres";
        String password="pass";

        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"",
                ""+username+"", ""+password+"");

        return connection;
    }

    public List<Product> getProductById(int id) throws Exception {
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement st = connection.prepareStatement(
                "SELECT description, rate, quantity " +
                        "FROM product " +
                        "WHERE id = ?");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        Product product = null;
        while (rs.next()) {
            product = new Product(
                    id,
                    rs.getString(1),
                    rs.getFloat(2),
                    rs.getInt(3));
            products.add(product);
        }
        rs.close();
        connection.close();
        return products;
    }

    public void addProduct(Product product) throws Exception {
        Connection connection = getConnection();

        PreparedStatement st = connection.prepareStatement(
                "INSERT into product " +
                        "(id, description, rate, quantity) " +
                        "values (?, ?, ?, ?)");
        st.setInt(1, product.getId());
        st.setString(2, product.getDescription());
        st.setFloat(3, product.getRate());
        st.setInt(4, product.getQuantity());

        st.executeUpdate();

        connection.close();
    }

    public void setProduct(Product product) throws Exception {
        Connection connection = getConnection();

        PreparedStatement st = connection.prepareStatement(
                "UPDATE product " +
                        "SET description=?, rate=?, quantity=? " +
                        "WHERE id=?");
        st.setString(1, product.getDescription());
        st.setFloat(2, product.getRate());
        st.setInt(3, product.getQuantity());
        st.setInt(4, product.getId());

        st.executeUpdate();
        connection.close();
    }

    public void removeProduct(int id) throws Exception {
        Connection connection = getConnection();

        PreparedStatement st = connection.prepareStatement(
                "DELETE from product " +
                        "WHERE id = ?");
        st.setInt(1, id);

        st.executeUpdate();
        connection.close();
    }

}
