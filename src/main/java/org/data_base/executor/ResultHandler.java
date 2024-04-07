package org.data_base.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {
	T handle(ResultSet resultSet) throws SQLException;
}
