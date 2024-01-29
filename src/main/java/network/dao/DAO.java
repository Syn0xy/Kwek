package network.dao;

import java.sql.Connection;

public class DAO<E> {

    protected Connection connection;

    protected DAO(Connection connection){
        this.connection = connection;
    }

}
