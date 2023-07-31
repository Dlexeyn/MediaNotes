package medianotes.config;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ApplcationDataSource {

    private static final PGSimpleDataSource ds;
    private static final Connection connection;

    static {
        ds = new PGSimpleDataSource();
        ds.setServerNames(new String[]{"localhost:5432"});
        ds.setDatabaseName("medianotesdb");
        ds.setUser("root");
        ds.setPassword("Alexert_190623L");

        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
