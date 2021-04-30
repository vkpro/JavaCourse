package ru.luxoft.cources.lab12clients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection getConnection() {
        var host = "localhost";
        var port = "5432";
        var dbName = "testdb";
        var username = "postgres";
        var password = "pass";
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://%s:%s/%s".formatted(host, port, dbName);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ConnectionException(ex);
        }
    }

    public List<Product> getProductById(int id) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (
                var connection = getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT description, rate, quantity " +
                                "FROM product " +
                                "WHERE id = ?")
        ) {
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        id,
                        rs.getString(1),
                        rs.getFloat(2),
                        rs.getInt(3));
                products.add(product);
            }
            rs.close();
            return products;
        }
    }

    public void addProduct(Product product) throws SQLException {
        try (var connection = getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "INSERT into product " +
                             "(id, description, rate, quantity) " +
                             "values (?, ?, ?, ?)")
        ) {
            st.setInt(1, product.getId());
            st.setString(2, product.getDescription());
            st.setFloat(3, product.getRate());
            st.setInt(4, product.getQuantity());

            st.executeUpdate();
        }
    }

    public void setProduct(Product product) throws SQLException {
        try (var connection = getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE product " +
                             "SET description=?, rate=?, quantity=? " +
                             "WHERE id=?")
        ) {
            st.setString(1, product.getDescription());
            st.setFloat(2, product.getRate());
            st.setInt(3, product.getQuantity());
            st.setInt(4, product.getId());

            st.executeUpdate();
        }
    }

    public void removeProduct(int id) throws SQLException {
        try (var connection = getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "DELETE from product WHERE id = ?")
        ) {
            st.setInt(1, id);

            st.executeUpdate();
        }
    }

    private static class ConnectionException extends RuntimeException {
        public ConnectionException(Throwable ex) {
            super(ex);
        }
    }
}
