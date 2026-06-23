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
import net.sf.jsqlparser.statement.select.Select;

public class CacheTableStatement implements Statement {

    private boolean lazy;
    private Table table;
    private List<String> options;
    private Select select;

    public boolean isLazy() {
        return lazy;
    }

    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Select getSelect() {
        return select;
    }

    public void setSelect(Select select) {
        this.select = select;
    }

    @Override
    public void accept(StatementVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CACHE");
        if (lazy) {
            sb.append(" LAZY");
        }
        sb.append(" TABLE ").append(table);
        if (options != null && !options.isEmpty()) {
            sb.append(" OPTIONS (").append(String.join(", ", options)).append(")");
        }
        if (select != null) {
            sb.append(" AS ").append(select);
        }
        return sb.toString();
    }
}
