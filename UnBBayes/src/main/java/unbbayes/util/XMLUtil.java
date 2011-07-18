/*
 *  UnBBayes
 *  Copyright (C) 2002, 2008 Universidade de Brasilia - http://www.unb.br
 *
 *  This file is part of UnBBayes.
 *
 *  UnBBayes is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  UnBBayes is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with UnBBayes.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package unbbayes.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * This class is a XML utility class. It requires two Apache Java modules:
 *     -- Xerces (Java XML parsers)
 *     -- Xalan (XSLT stylesheet processors).
 * <p>
 * <p>
 * @author Copyright (c) 2001 by RDA Corporation, Inc. All Rights Reserved.
 */
public class XMLUtil {
   private XMLUtil(){}

  /**
   * returns an XML DOM object.
   *
   * @param poStringInput     String XML string input
   * @return                  Document XML DOM document
   */
   public static Document getDocument(String poStringInput) throws Exception {
      Document retDocument = null;
      try
      {
         ByteArrayInputStream in = new ByteArrayInputStream( poStringInput.getBytes() );
         DocumentBuilderFactory docBuilderfactory = DocumentBuilderFactory.newInstance();
         docBuilderfactory.setValidating(false);
         retDocument = docBuilderfactory.newDocumentBuilder().parse(in);
      }
      catch(Exception ex){
         throw new Exception(ex.getMessage());
      }
      return retDocument;
   }

  /**
   * returns an XML DOM object.
   *
   * @param poInputSource     InputSource XML string stream input
   * @return                  Document XML DOM document
   */
   public static Document getDocument(InputSource poInputSource) throws Exception  {
      Document retDocument = null;
      try {
         DocumentBuilderFactory docBuilderfactory = DocumentBuilderFactory.newInstance();
         docBuilderfactory.setValidating(false);
         retDocument = docBuilderfactory.newDocumentBuilder().parse(poInputSource);
      }
      catch(Exception ex){
         throw new Exception(ex.getMessage());
      }
      return retDocument;
   }

  /**
   * returns an XML string.
   *
   * @param pDocument         Document XML DOM document
   * @return                  String XML string
   */
   public static String getXML(Document pDocument) throws Exception {
      String retString = null;
      try{
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         Transformer serializer = TransformerFactory.newInstance().newTransformer();
         serializer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
         serializer.transform(new DOMSource(pDocument), new StreamResult(out));
         retString = out.toString();
         out.close();
      }
      catch(Exception ex){
         throw new Exception(ex.getMessage());
      }
      return retString;
   }

   /**
   * returns an XML string.
   *
   * @param pNode         Node XML Document node
   * @return              String XML string
   */
   public static String getXML(Node pNode)
   {
      String retString = null;
      try
      {
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         Transformer serializer = TransformerFactory.newInstance().newTransformer();
         serializer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
         serializer.transform(new DOMSource(pNode), new StreamResult(out));
         retString = out.toString();
         out.close();
      }
      catch(Exception ex)
      {}
      return retString;
   }

  /**
   * returns a XML node list.
   *
   * @param pDocument         Document XML DOM document
   * @param psTagName         String XML node name
   * @return                  NodeList XML node list
   */
   public static org.w3c.dom.NodeList getNodeList(Document pDocument, String psTagName){
      return pDocument.getDocumentElement().getElementsByTagName(psTagName);
   }

  /**
   * returns a XML element.
   *
   * @param pDocument         Document XML DOM document
   * @param psTagName         String XML node name
   * @param index             int XML node position
   * @return                  Element XML node element
   */
   public static Element getElement(Document pDocument, String psTagName, int index ){
      NodeList rows = pDocument.getDocumentElement().getElementsByTagName(psTagName );
      return (Element)rows.item(index);
   }

  /**
   * returns a XML node size.
   *
   * @param pDocument         Document XML DOM document
   * @param psTagName         String XML node name
   * @return                  int XML node size
   */
   public static int getSize(Document pDocument, String psTagName ){
      NodeList rows = pDocument.getDocumentElement().getElementsByTagName(psTagName);
      return rows.getLength();
   }

  /**
   * returns a XML node value.
   *
   * @param pDocument         Document XML DOM document
   * @param psTagName         String XML node name
   * @return                  String XML node value
   */
   public static String getValue(Document pDocument, String psTagName ) throws Exception {
      String s = null;
      try {
         NodeList elements = pDocument.getDocumentElement().getElementsByTagName( psTagName );
         Node node = elements.item( 0 );
         NodeList nodes = node.getChildNodes();
         //find a value whose value is non-whitespace
         for( int i=0; i<nodes.getLength(); i++)
         {
            s = ((Node)nodes.item( i )).getNodeValue().trim();
            if(s.equals("") || s.equals("\r")) continue;
         }
      }
      catch(Exception ex){
         throw new Exception(ex.getMessage());
      }
      return s;
   }

  /**
   * returns a XML element value.
   *
   * @param pElement          Document XML element
   * @return                  String XML node value
   */
   public static String getValue(Element pElement) throws Exception {
      String s = null;
      try{
         NodeList nodes = pElement.getChildNodes();
         //find a value whose value is non-whitespace
         for( int i=0; i<nodes.getLength(); i++)
         {
            s = ((Node)nodes.item( i )).getNodeValue().trim();
            if(s.equals("") || s.equals("\r")) continue;
         }
      }
      catch(Exception ex){
         throw new Exception(ex.getMessage());
      }
      return s;
   }

  /**
   * returns a XML node value.
   *
   * @param pNode             Document XML node
   * @return                  String XML node value
   */
   public static String getValue(Node pNode) throws Exception {
      String s = null;
      try {
         NodeList nodes = pNode.getChildNodes();
         for( int i=0; i<nodes.getLength(); i++){
            s = ((Node)nodes.item( i )).getNodeValue().trim();
            if(s.equals("") || s.equals("\r")) continue;
         }
      }
      catch(Exception ex){
         throw new Exception(ex.getMessage());
      }
      return s;
   }

  /**
   * creates a new empty DOM document.
   *
   * @return                  Document
   */
   public static Document createDocument()
   {
      Document retDocument = null;
      try
      {
         DocumentBuilderFactory docBuilderfactory = DocumentBuilderFactory.newInstance();
         retDocument = docBuilderfactory.newDocumentBuilder().newDocument();
      }
      catch(Exception ex)
      {
         ex.printStackTrace();
      }
      return retDocument;
   }

  /**
   * Create a new node tree based on a node tree
   *
   * @param poDocument        Source Document XML
   * @param xPath             the XPATH
   *
   * @return                  Node
   */
   public static Node getNodeTree(Document poDocument, String xPath)
   {
      Node elementNode = null;
      Node node = null;
      try
      {
         elementNode = XPathAPI.selectSingleNode(poDocument, xPath);
         // clone the whole node tree
         node = elementNode.cloneNode(true);
         elementNode = null;
      }
      catch(Exception ex)
      {
         ex.printStackTrace();
      }
      return node;
   }

  /**
   * Insert a node into the document.
   *
   * @param poDocument        Source Document XML
   * @param poNode            Node where the new node is going to be inserted into
   * @param poNodeToInsert    Node to be inserted into
   * @param pbFlag            Flag to indicate if poNodeToInsert is created by document poDocument
   *
   * @return                  Node
   */
   public static void insertNode(Document poDocument, Node poNode, Node poNodeToInsert, boolean pbFlag)
   {
      Node elementNode = null;
      try
      {
         if (!pbFlag)
         {
            elementNode = poDocument.importNode(poNodeToInsert, true);
            poNode.appendChild(elementNode);
         }
         else
         {
            poNode.appendChild(poNodeToInsert);
         }
      }
      catch(Exception ex)
      {
         ex.printStackTrace();
      }
   }

  /**
   * Insert a node into the document.
   *
   * @param poDocument        Source Document XML
   * @param poNode            Node where the new node is going to be inserted into
   * @param poNodeToInsert    Node to be inserted into
   *
   * @return                  Node
   */
   public static void insertNode(Document poDocument, Node poNode, Node poNodeToInsert)
   {
      insertNode(poDocument, poNode, poNodeToInsert, true);
   }
}