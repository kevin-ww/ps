package com.philips.cn.hr.pps.h2db;

/**
 * Created by kevin on 2015/1/8.
 */

import org.h2.tools.Server;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class DBServer {

    //lifecycle support;
    public DBServer() {
        //Server server = Server.createTcpServer(args).start();

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("my-schema.sql").addScript("my-test-data.sql").build();
            // do stuff against the db (EmbeddedDatabase extends javax.sql.DataSource)
        db.shutdown();
    }
}
