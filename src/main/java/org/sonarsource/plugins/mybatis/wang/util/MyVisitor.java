package org.sonarsource.plugins.mybatis.wang.util;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.expr.SQLInSubQueryExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.visitor.SQLASTVisitorAdapter;
import com.alibaba.druid.stat.TableStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyVisitor extends SQLASTVisitorAdapter {
    private static final Logger log = LoggerFactory.getLogger(MyVisitor.class);
    private TableStat.Mode mode;
    private AtomicInteger atomicInteger = new AtomicInteger(1);
    private List<SQLSelectQueryBlock> allSqlSelectQueryBlockList = new LinkedList();
    private List<String> allTableNameList = new LinkedList();

    public boolean visit(SQLSelectQueryBlock x) {
        this.allSqlSelectQueryBlockList.add(x);
        return true;
    }

    public boolean visit(SQLExprTableSource x) {
        SQLExpr table = x.getExpr();
        String tableName = ((SQLName)table).getSimpleName();
        this.allTableNameList.add(tableName);
        return true;
    }

    public void endVisit(SQLInSubQueryExpr x) {
        log.info("SQLInSubQueryExpr:" + x.toString());
    }

    public boolean visit(SQLInsertStatement x) {
        return true;
    }

    public List<SQLSelectQueryBlock> getAllSqlSelectQueryBlockList() {
        return this.allSqlSelectQueryBlockList;
    }

    public List<String> getAllTableNameList() {
        return this.allTableNameList;
    }

    public String getFirstSql() {
        if (null == this.allSqlSelectQueryBlockList || this.allSqlSelectQueryBlockList.isEmpty()) {
            return "";
        }
        this.allSqlSelectQueryBlockList.get(0);
        return "";
    }
}
