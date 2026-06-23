/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.statement.spark;

import java.util.List;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.StatementVisitor;

public class LoadDataStatement implements Statement {

    private boolean local;
    private String inPath;
    private boolean overwrite;
    private Table table;
    private List<String> partition;

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public String getInPath() {
        return inPath;
    }

    public void setInPath(String inPath) {
        this.inPath = inPath;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<String> getPartition() {
        return partition;
    }

    public void setPartition(List<String> partition) {
        this.partition = partition;
    }

    @Override
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LOAD DATA");
        if (local) {
            sb.append(" LOCAL");
        }
        sb.append(" INPATH ").append(inPath);
        if (overwrite) {
            sb.append(" OVERWRITE");
        }
        sb.append(" INTO TABLE ").append(table);
        if (partition != null && !partition.isEmpty()) {
            sb.append(" PARTITION (").append(String.join(", ", partition)).append(")");
        }
        return sb.toString();
    }
}
