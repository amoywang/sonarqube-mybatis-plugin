package org.sonarsource.plugins.mybatis.xml;

public abstract class BaseResult {
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof BaseResult) {
            BaseResult other = (BaseResult) o;
            return other.canEqual(this);
        }
        return false;
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseResult;
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "BaseResult()";
    }
}
