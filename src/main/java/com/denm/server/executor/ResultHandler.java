package com.denm.server.executor;

/**
 * Created by DENM on 20.01.2016.
 */
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}