package org.sonarsource.plugins.mybatis.xml.consts;


import org.sonarsource.plugins.mybatis.xml.node.commom.IncludeNode;
import org.sonarsource.plugins.mybatis.xml.node.mybatis.*;

import java.util.*;

public class Constant {
    public static final List<String> MYBATIS_SPECIAL_KEY_WORD = new ArrayList();
    public static final List<String> IBATIS_SPECIAL_KEY_WORD = new ArrayList();
    public static final Map<String, String> IBATIS_NODE_PACKAGE_MAP = new HashMap();
    public static final Map<String, String> MYBATIS_NODE_PACKAGE_MAP = new HashMap();
    public static final List SELECT_INSERT_DELETE_UPDATE_LIST = Arrays.asList("select", "insert", "delete", "update");
    public static final List INSERT_UPDATE_LIST = Arrays.asList("insert", "update");
    public static final String SPACE_CHAR = " ";
    public static final String SQLMAP = "SQLMAP";
    public static final String MAPPER = "MAPPER";
    public static final String SELECT = "SELECT";
    public static final String UPDATE = "UPDATE";
    public static final String INSERT = "INSERT";
    public static final String DELETE = "DELETE";
    public static final String SQL = "SQL";
    public static final String TYPEALIAS = "TYPEALIAS";
    public static final String RESULTMAP = "RESULTMAP";
    public static final String DYNAMIC = "dynamic";
    public static final String ISEMPTY = "isempty";
    public static final String ISEQUAL = "isequal";
    public static final String ISGREATERTHAN = "isgreaterthan";
    public static final String ISLESSEQUAL = "islessequal";
    public static final String ISLESSTHAN = "islessthan";
    public static final String ISNOTEMPTY = "isnotempty";
    public static final String ISNOTEQUAL = "isnotequal";
    public static final String ISNOTNULL = "isnotnull";
    public static final String ISNOTPROPERTYAVAILABLE = "isnotpropertyavailable";
    public static final String ISNULL = "isnull";
    public static final String ISPARAMETERPRESENT = "isparameterpresent";
    public static final String ISNOTPARAMETERPRESENT = "isnotparameterpresent";
    public static final String ISPROPERTYAVAILABLE = "ispropertyavailable";
    public static final String ITERATE = "iterate";
    public static final String WHEN = "when";
    public static final String OTHERWISE = "otherwise";
    public static final String WHERE = "where";
    public static final String SET = "set";

    static {
   /*     IBATIS_NODE_PACKAGE_MAP.put(DYNAMIC, DynamicNode.class.getPackage().getName() + "." + DynamicNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ISEMPTY, YIsEmptyNode.class.getPackage().getName() + "." + YIsEmptyNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ISEQUAL, YIsEqualNode.class.getPackage().getName() + "." + YIsEqualNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put("isgreaterequal", YIsGreaterEqualNode.class.getPackage().getName() + "." + YIsGreaterEqualNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ISGREATERTHAN, YIsGreaterThanNode.class.getPackage().getName() + "." + YIsGreaterThanNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ISLESSEQUAL, YIsLessEqualNode.class.getPackage().getName() + "." + YIsLessEqualNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ISLESSTHAN, YIsLessThanNode.class.getPackage().getName() + "." + YIsLessThanNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ISNOTEMPTY, YIsNotEmptyNode.class.getPackage().getName() + "." + YIsNotEmptyNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ISNOTEQUAL, YIsNotEqualNode.class.getPackage().getName() + "." + YIsNotEqualNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ISNOTNULL, YIsNotNullNode.class.getPackage().getName() + "." + YIsNotNullNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ISNOTPROPERTYAVAILABLE, YIsNotPropertyAvailableNode.class.getPackage().getName() + "." + YIsNotPropertyAvailableNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ISNULL, YIsNullNode.class.getPackage().getName() + "." + YIsNullNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ISPARAMETERPRESENT, YIsParameterPresentNode.class.getPackage().getName() + "." + YIsParameterPresentNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ISNOTPARAMETERPRESENT, YIsParameterPresentNode.class.getPackage().getName() + "." + YIsParameterPresentNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ISPROPERTYAVAILABLE, YIsPropertyAvailableNode.class.getPackage().getName() + "." + YIsPropertyAvailableNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put(ITERATE, YIterateNode.class.getPackage().getName() + "." + YIterateNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put("include", IncludeNode.class.getPackage().getName() + "." + IncludeNode.class.getSimpleName());
        IBATIS_NODE_PACKAGE_MAP.put("selectkey", "");
        IBATIS_SPECIAL_KEY_WORD.add("CURRENT TIMESTAMP");*/
        MYBATIS_NODE_PACKAGE_MAP.put("if", ZIfNode.class.getPackage().getName() + "." + ZIfNode.class.getSimpleName());
        MYBATIS_NODE_PACKAGE_MAP.put("choose", ZChooseNode.class.getPackage().getName() + "." + ZChooseNode.class.getSimpleName());
        MYBATIS_NODE_PACKAGE_MAP.put(WHEN, ZWhenNode.class.getPackage().getName() + "." + ZWhenNode.class.getSimpleName());
        MYBATIS_NODE_PACKAGE_MAP.put(OTHERWISE, ZOtherwiseNode.class.getPackage().getName() + "." + ZOtherwiseNode.class.getSimpleName());
        MYBATIS_NODE_PACKAGE_MAP.put("trim", ZTrimNode.class.getPackage().getName() + "." + ZTrimNode.class.getSimpleName());
        MYBATIS_NODE_PACKAGE_MAP.put(WHERE, ZWhereNode.class.getPackage().getName() + "." + ZWhereNode.class.getSimpleName());
        MYBATIS_NODE_PACKAGE_MAP.put(SET, ZSetNode.class.getPackage().getName() + "." + ZSetNode.class.getSimpleName());
        MYBATIS_NODE_PACKAGE_MAP.put("foreach", ZForeachNode.class.getPackage().getName() + "." + ZForeachNode.class.getSimpleName());
        MYBATIS_NODE_PACKAGE_MAP.put("bind", ZBindNode.class.getPackage().getName() + "." + ZBindNode.class.getSimpleName());
        MYBATIS_NODE_PACKAGE_MAP.put("include", IncludeNode.class.getPackage().getName() + "." + IncludeNode.class.getSimpleName());
        MYBATIS_NODE_PACKAGE_MAP.put("selectkey", "");
        MYBATIS_SPECIAL_KEY_WORD.add("CURRENT TIMESTAMP");
    }
}
