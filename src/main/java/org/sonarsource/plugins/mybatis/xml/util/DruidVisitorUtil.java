package org.sonarsource.plugins.mybatis.xml.util;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.db2.parser.DB2StatementParser;
import com.alibaba.druid.sql.dialect.db2.visitor.DB2SchemaStatVisitor;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.oracle.parser.OracleStatementParser;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.postgresql.parser.PGSQLStatementParser;
import com.alibaba.druid.sql.dialect.postgresql.visitor.PGSchemaStatVisitor;
import com.alibaba.druid.sql.parser.ParserException;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.parser.Token;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarsource.plugins.mybatis.xml.exception.DruidParseException;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

public enum DruidVisitorUtil {
    ;

    private static final Logger log = LoggerFactory.getLogger(DruidVisitorUtil.class);

    public static SchemaStatVisitor getVisitor(String sql, String dbType) throws DruidParseException {
        SchemaStatVisitor visitor;
        if (null != dbType) {
            try {
                if (!dbType.isEmpty()) {
                    visitor = getSqlParserVisitorSingle(sql, dbType);
                    return visitor;
                }
            } catch (Exception ex) {
                throw new DruidParseException("Can not get parse visitor.", ex);
            }
        }
        visitor = getSqlParserVisitorAutoAll(sql);
        return visitor;
    }

    public static SchemaStatVisitor getSqlParserVisitor(String sql, String dbType) throws Exception {
        if (StringUtils.isEmpty(sql)) {
            return null;
        }
        SQLStatementParser dB2StatementParser;
        SchemaStatVisitor dB2SchemaStatVisitor;
        if ("oracle".equalsIgnoreCase(dbType)) {
            dB2StatementParser = new OracleStatementParser(sql);
            dB2SchemaStatVisitor = new OracleSchemaStatVisitor();
        } else if ("gauss".equalsIgnoreCase(dbType)) {
            dB2StatementParser = new OracleStatementParser(sql);
            dB2SchemaStatVisitor = new OracleSchemaStatVisitor();
        } else if ("mysql".equalsIgnoreCase(dbType)) {
            dB2StatementParser = new MySqlStatementParser(sql);
            dB2SchemaStatVisitor = new MySqlSchemaStatVisitor();
        } else if ("postgresql".equalsIgnoreCase(dbType)) {
            dB2StatementParser = new PGSQLStatementParser(sql);
            dB2SchemaStatVisitor = new PGSchemaStatVisitor();
        } else if ("db2".equalsIgnoreCase(dbType)) {
            dB2StatementParser = new DB2StatementParser(sql);
            dB2SchemaStatVisitor = new DB2SchemaStatVisitor();
        } else {
            throw new RuntimeException("暂不支持" + dbType + "类型.");
        }
        try {
            List<SQLStatement> statementList = dB2StatementParser.parseStatementList();
            if (statementList.size() > 1) {
                throw new ParserException(" Mutil-Statment be found.");
            }
            if (dB2StatementParser.getLexer().token() != Token.EOF) {
                throw new ParserException("syntax error. " + sql);
            }
            SQLStatement sqlStatement = statementList.get(0);
            sqlStatement.accept(dB2SchemaStatVisitor);
            return dB2SchemaStatVisitor;
        } catch (Exception e) {
            throw new SQLSyntaxErrorException(e.getMessage());
        }
    }

    public static SchemaStatVisitor getSqlParserVisitorSingle(String sql, String dbType) throws DruidParseException {
        if (StringUtils.isEmpty(sql)) {
            return null;
        }
        StringBuilder errorStr = new StringBuilder();
        errorStr.append("SQL statement:").append(sql).append("\n");
        try {
            SchemaStatVisitor visitor = getSqlParserVisitor(sql, dbType);
            return visitor;
        } catch (Exception ex) {
            errorStr.append(dbType).append(" ERROR:").append(ex.getMessage()).append("\n");
            throw new DruidParseException(errorStr.toString(), null);
        }
    }

    public static SchemaStatVisitor getSqlParserVisitorAutoAll(String sql) throws DruidParseException {
        SchemaStatVisitor visitor;
        SchemaStatVisitor visitor2;
        SchemaStatVisitor visitor3;
        SchemaStatVisitor visitor4;
        SchemaStatVisitor visitor5;
        StringBuilder errorStr = new StringBuilder();
        errorStr.append("SQL statement:").append(sql).append("\n");
        try {
            visitor = getSqlParserVisitor(sql, "mysql");
        } catch (Exception ex) {
            visitor = null;
            errorStr.append("mysql ERROR:").append(ex.getMessage()).append("\n");
        }
        if (null != visitor) {
            return visitor;
        }
        if (null == visitor) {
            try {
                visitor2 = getSqlParserVisitor(sql, "oracle");
            } catch (Exception ex2) {
                visitor2 = null;
                errorStr.append("oracle ERROR:").append(ex2.getMessage()).append("\n");
            }
            if (null == visitor2) {
                try {
                    visitor3 = getSqlParserVisitor(sql, "gauss");
                } catch (Exception ex3) {
                    visitor3 = null;
                    errorStr.append("gauss ERROR:").append(ex3.getMessage()).append("\n");
                }
                if (null == visitor3) {
                    try {
                        visitor4 = getSqlParserVisitor(sql, "postgresql");
                    } catch (Exception ex4) {
                        visitor4 = null;
                        errorStr.append("postgresql ERROR:").append(ex4.getMessage()).append("\n");
                    }
                    if (null == visitor4) {
                        try {
                            visitor5 = getSqlParserVisitor(sql, "db2");
                        } catch (Exception ex5) {
                            visitor5 = null;
                            errorStr.append("db2 ERROR:").append(ex5.getMessage()).append("\n");
                        }
                        if (!errorStr.toString().isEmpty()) {
                            errorStr.append("mysql,oracle,gauss,postgresql,db2 all failed.");
                            throw new DruidParseException(errorStr.toString(), null);
                        }
                        return visitor5;
                    }
                    return visitor4;
                }
                return visitor3;
            }
            return visitor2;
        }
        return visitor;
    }
}
