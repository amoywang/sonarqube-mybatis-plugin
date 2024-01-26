package org.sonarsource.plugins.mybatis.xml.util;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.pojo.XmlNode;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

public enum XmlUtil {
    ;

    private static final Logger log = LoggerFactory.getLogger(XmlUtil.class);

    public static void parseXmlNodeSqlTag(String xmlFilePath, Map<String, List<Element>> xmlSqlTagGlobalMap) throws Exception {
        if (null == xmlSqlTagGlobalMap) {
            throw new Exception("xmlSqlTagGlobalMap 不能为null, 且必须为空");
        }
        String fileName = new File(xmlFilePath).getName();
        Document xmlDocument = getXmlDocument(xmlFilePath);
        String xmlContent = xmlDocument.asXML();
        if (StringUtils.isBlank(xmlContent)) {
            return;
        }
        try {
            Element xmlRootElement = xmlDocument.getRootElement();
            boolean isMybatis = xmlRootElement.getName().equalsIgnoreCase(Constant.SQLMAP);
            boolean isIbatis = xmlRootElement.getName().equalsIgnoreCase(Constant.MAPPER);
            if (isMybatis || isIbatis) {
                String namespace = xmlRootElement.attributeValue("namespace");
                List<Element> sqlElementsList = xmlRootElement.elements("sql");
                for (Element sqlElement : sqlElementsList) {
                    String includeSqlId = sqlElement.attributeValue("id");
                    String includeSqlId2 = namespace + "." + deleteXmlIdNameSpace(includeSqlId);
                    if (null == xmlSqlTagGlobalMap.get(includeSqlId2)) {
                        List<Element> elementList = new ArrayList<>();
                        elementList.add(sqlElement);
                        xmlSqlTagGlobalMap.put(includeSqlId2, elementList);
                    } else {
                        xmlSqlTagGlobalMap.get(includeSqlId2).add(sqlElement);
                        log.info("注意含有重复<sql id=[" + includeSqlId2 + "]>");
                    }
                }
                return;
            }
            throw new Exception(fileName + "不是 mybatis/ibatis 文件, 无法识别");
        } catch (DocumentException e) {
            throw new Exception(e.getMessage());
        }
    }

    public static int parseXmlNode(String xmlFilePath, Map<String, List<XmlNode>> xmlNodeMap, Map<String, List<Element>> xmlSqlTagGlobalMap) throws Exception {
        int sum = 0;
        if (null == xmlNodeMap || !xmlNodeMap.isEmpty()) {
            throw new Exception("xmlNodeMap 不能为null, 且必须为空");
        }
        String fileName = new File(xmlFilePath).getName();
        Document xmlDocument = getXmlDocument(xmlFilePath);
        String xmlContent = xmlDocument.asXML();
        if (StringUtils.isBlank(xmlContent)) {
            return 0;
        }
        try {
            Element xmlRootElement = xmlDocument.getRootElement();
            boolean isMybatis = xmlRootElement.getName().equalsIgnoreCase(Constant.SQLMAP);
            boolean isIbatis = xmlRootElement.getName().equalsIgnoreCase(Constant.MAPPER);
            if (isMybatis || isIbatis) {
                String mapperType = xmlRootElement.getName().toUpperCase();
                String namespace = xmlRootElement.attributeValue("namespace");
                StringBuilder comments = new StringBuilder();
                Iterator<Node> nodeIterator = xmlRootElement.nodeIterator();
                while (nodeIterator.hasNext()) {
                    Node next = nodeIterator.next();
                    if (next.getNodeType() == 8) {
                        comments.append("\n").append(next.getText());
                    } else if (next.getNodeType() == 1 && isSqlInsertDeleteUpdateSelect(next)) {
                        XmlNode xmlNode = new XmlNode();
                        String xmlNodeId = ((Element) next).attributeValue("id");
                        String nodeOptType = next.getName();
                        xmlNode.setSqlComments(comments.toString());
                        xmlNode.setNameSpace(namespace);
                        xmlNode.setNodeAsXml(next.asXML());
                        xmlNode.setMapperType(mapperType);
                        xmlNode.setNodeOptType(nodeOptType);
                        xmlNode.setXmlFilePath(xmlFilePath);
                        if ("sql".equals(nodeOptType)) {
                            xmlNode.setSqlNodeIdOrg(xmlNodeId);
                            xmlNodeId = namespace + "." + deleteXmlIdNameSpace(xmlNodeId);
                            if (xmlSqlTagGlobalMap.get(xmlNodeId).size() > 1) {
                                xmlNode.setHasDuplicatedSqlTagId(true);
                            }
                        } else {
                            sum++;
                            xmlNode.setSqlNodeIdOrg(xmlNodeId);
                        }
                        if (null == xmlNodeMap.get(xmlNodeId)) {
                            List<XmlNode> xmlNodeList = new LinkedList<>();
                            xmlNodeList.add(xmlNode);
                            xmlNodeMap.put(xmlNodeId, xmlNodeList);
                        } else {
                            xmlNodeMap.get(xmlNodeId).add(xmlNode);
                            xmlNode.setHasDuplicatedXmlNode(true);
                        }
                        xmlNode.setSqlNodeId(xmlNodeId);
                        comments = new StringBuilder();
                    } else if (next.getNodeType() == 1 && next.getName().equalsIgnoreCase(Constant.TYPEALIAS)) {
                        comments = new StringBuilder();
                    } else if (next.getNodeType() == 1 && next.getName().equalsIgnoreCase(Constant.RESULTMAP)) {
                        comments = new StringBuilder();
                    } else if (next.getNodeType() == 3 && next.getText().trim().equals("")) {
                        comments = new StringBuilder();
                    } else {
                        comments = new StringBuilder();
                    }
                }
                return sum;
            }
            throw new Exception(fileName + "不是 mybatis/ibatis 文件, 无法识别");
        } catch (DocumentException e) {
            throw new Exception(e.getMessage());
        }
    }

    public static Document getXmlDocument(String filePath) {
        SAXReader saxReader = createSAXReader();
        File sqlmapFile = new File(filePath);
        try {
            Document document = saxReader.read(sqlmapFile);
            return document;
        } catch (Exception e) {
            throw new RuntimeException("无法读取XML内容:" + filePath);
        }
    }

    private static boolean isSqlInsertDeleteUpdateSelect(Node node) {
        if (null == node || null == node.getName() || node.getName().isEmpty()) {
            return false;
        }
        String nodeName = node.getName();
        boolean isSql = nodeName.equalsIgnoreCase(Constant.SQL);
        boolean isSelect = nodeName.equalsIgnoreCase(Constant.SELECT);
        boolean isUpdate = nodeName.equalsIgnoreCase(Constant.UPDATE);
        boolean isInsert = nodeName.equalsIgnoreCase(Constant.INSERT);
        boolean isDelete = nodeName.equalsIgnoreCase(Constant.DELETE);
        return isSelect || isUpdate || isInsert || isDelete || isSql;
    }

    private static boolean isInsertDeleteUpdateSelect(Node node) {
        if (null == node || null == node.getName() || node.getName().isEmpty()) {
            return false;
        }
        String nodeName = node.getName();
        boolean isSelect = nodeName.equalsIgnoreCase(Constant.SELECT);
        boolean isUpdate = nodeName.equalsIgnoreCase(Constant.UPDATE);
        boolean isInsert = nodeName.equalsIgnoreCase(Constant.INSERT);
        boolean isDelete = nodeName.equalsIgnoreCase(Constant.DELETE);
        return isSelect || isUpdate || isInsert || isDelete;
    }

    public static String deleteXmlIdNameSpace(String id) {
        if (id.contains(".")) {
            return id.substring(id.lastIndexOf(46) + 1);
        }
        return id;
    }

    public static SAXReader createSAXReader() {
        SAXReader reader = new SAXReader();
        reader.setValidation(false);
        reader.setEntityResolver(new EntityResolver() { // from class: org.sonarsource.plugins.mybatis.xml.util.XmlUtil.1
            @Override // org.xml.sax.EntityResolver
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                return new InputSource(new ByteArrayInputStream("".getBytes()));
            }
        });
        return reader;
    }
}
