package main.java.com.example;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Test
    void testFindUser() throws SQLException {
        try (MockedStatic<DriverManager> mockedDriverManager = Mockito.mockStatic(DriverManager.class)) {
            Connection mockConn = mock(Connection.class);
            Statement mockStmt = mock(Statement.class);

            mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                               .thenReturn(mockConn);
            
            when(mockConn.createStatement()).thenReturn(mockStmt);
            
            UserService service = new UserService();
            service.findUser("testUser");
            
            verify(mockStmt).executeQuery(anyString());
        }
    }

    @Test
    void testDeleteUser() throws SQLException {
        try (MockedStatic<DriverManager> mockedDriverManager = Mockito.mockStatic(DriverManager.class)) {
            Connection mockConn = mock(Connection.class);
            PreparedStatement mockStmt = mock(PreparedStatement.class);

            mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                               .thenReturn(mockConn);
            
            when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);
            
            UserService service = new UserService();
            service.deleteUser("testUser");
            
            verify(mockStmt).setString(1, "testUser");
            verify(mockStmt).execute();
        }
    }

    @Test
    void testNotUsed() {
        UserService service = new UserService();
        service.notUsed();
    }
}
