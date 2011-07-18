package unbbayes.io.xmlbif.version5.xmlclasses.impl;

public class XMLBIFTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  public class HeaderAndStaticPropertyAndHierarchyHandler extends org.apache.ws.jaxme.impl.JMSAXGroupParser {
    /** The current state. The following values are valid states:
     *  0 = Before parsing the element
     *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}header
     *  2 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}staticProperty
     *  3 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}hierarchy
     *  4 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}network
     * 
     */
    private int __state;
  
  
    public org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl getHandler() {
      return unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.this.getHandler();
    }
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      switch (__state) {
        case 0:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "header".equals(pLocalName)) {
            __state = 1;
            java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.HeaderTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.HeaderTypeHandler();
            _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "header", _1.getLevel());
            _3.setAttributes(pAttr);
            _1.addElementParser(_3);
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "staticProperty".equals(pLocalName)) {
            __state = 2;
            java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.StaticPropertyTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.StaticPropertyTypeHandler();
            _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "staticProperty", _1.getLevel());
            _5.setAttributes(pAttr);
            _1.addElementParser(_5);
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "hierarchy".equals(pLocalName)) {
            __state = 3;
            java.lang.Object _6 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.HierarchyTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _7 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.HierarchyTypeHandler();
            _7.init(_1, _6, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "hierarchy", _1.getLevel());
            _7.setAttributes(pAttr);
            _1.addElementParser(_7);
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "network".equals(pLocalName)) {
            __state = 4;
            java.lang.Object _8 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _9 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler();
            _9.init(_1, _8, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "network", _1.getLevel());
            _9.setAttributes(pAttr);
            _1.addElementParser(_9);
            return true;
          }
          break;
        case 1:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "staticProperty".equals(pLocalName)) {
            __state = 2;
            java.lang.Object _10 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.StaticPropertyTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _11 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.StaticPropertyTypeHandler();
            _11.init(_1, _10, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "staticProperty", _1.getLevel());
            _11.setAttributes(pAttr);
            _1.addElementParser(_11);
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "hierarchy".equals(pLocalName)) {
            __state = 3;
            java.lang.Object _12 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.HierarchyTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _13 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.HierarchyTypeHandler();
            _13.init(_1, _12, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "hierarchy", _1.getLevel());
            _13.setAttributes(pAttr);
            _1.addElementParser(_13);
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "network".equals(pLocalName)) {
            __state = 4;
            java.lang.Object _14 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _15 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler();
            _15.init(_1, _14, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "network", _1.getLevel());
            _15.setAttributes(pAttr);
            _1.addElementParser(_15);
            return true;
          }
          break;
        case 2:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "hierarchy".equals(pLocalName)) {
            __state = 3;
            java.lang.Object _16 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.HierarchyTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _17 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.HierarchyTypeHandler();
            _17.init(_1, _16, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "hierarchy", _1.getLevel());
            _17.setAttributes(pAttr);
            _1.addElementParser(_17);
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "network".equals(pLocalName)) {
            __state = 4;
            java.lang.Object _18 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _19 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler();
            _19.init(_1, _18, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "network", _1.getLevel());
            _19.setAttributes(pAttr);
            _1.addElementParser(_19);
            return true;
          }
          break;
        case 3:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "network".equals(pLocalName)) {
            __state = 4;
            java.lang.Object _20 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _21 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler();
            _21.init(_1, _20, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "network", _1.getLevel());
            _21.setAttributes(pAttr);
            _1.addElementParser(_21);
            return true;
          }
          break;
        case 4:
          break;
        default:
          throw new java.lang.IllegalStateException("Invalid state: " + __state);
      }
      return false;
    }
  
    public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
      unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType) result;
      switch (__state) {
        case 1:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "header".equals(pLocalName)) {
            _1.setHeader(((unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.HeaderType) pResult));
            return;
          }
          break;
        case 2:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "staticProperty".equals(pLocalName)) {
            _1.setStaticProperty(((unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.StaticPropertyType) pResult));
            return;
          }
          break;
        case 3:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "hierarchy".equals(pLocalName)) {
            _1.setHierarchy(((unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.HierarchyType) pResult));
            return;
          }
          break;
        case 4:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "network".equals(pLocalName)) {
            _1.setNetwork(((unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType) pResult));
            return;
          }
          break;
        default:
          throw new java.lang.IllegalStateException("Illegal state: " + __state);
      }
    }
  
    public boolean isFinished() {
      switch (__state) {
        case 4:
        case 3:
        case 2:
        case 1:
        case 0:
          return true;
        default:
          return false;
      }
    }
  
  }

  public static class HeaderTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
    /** The current state. The following values are valid states:
     *  0 = Before parsing the element
     *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}version
     *  2 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}name
     *  3 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}creator
     * 
     */
    private int __state;
  
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      switch (__state) {
        case 0:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "version".equals(pLocalName)) {
            __state = 1;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "name".equals(pLocalName)) {
            __state = 2;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "creator".equals(pLocalName)) {
            __state = 3;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 1:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "name".equals(pLocalName)) {
            __state = 2;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "creator".equals(pLocalName)) {
            __state = 3;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 2:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "creator".equals(pLocalName)) {
            __state = 3;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 3:
          break;
        default:
          throw new java.lang.IllegalStateException("Invalid state: " + __state);
      }
      return false;
    }
  
    public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
      unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.HeaderType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.HeaderType) result;
      switch (__state) {
        case 1:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "version".equals(pLocalName)) {
            try {
              _1.setVersion(getHandler().getDatatypeConverter().parseDouble((java.lang.String) pResult));
            } catch (java.lang.Exception _2) {
              getHandler().parseConversionEvent("Failed to convert value of {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}version: " + pResult, _2);
            }
            return;
          }
          break;
        case 2:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "name".equals(pLocalName)) {
            _1.setName((java.lang.String) pResult);
            return;
          }
          break;
        case 3:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "creator".equals(pLocalName)) {
            _1.setCreator((java.lang.String) pResult);
            return;
          }
          break;
        default:
          throw new java.lang.IllegalStateException("Illegal state: " + __state);
      }
    }
  
    public boolean isFinished() {
      switch (__state) {
        case 3:
        case 2:
        case 1:
        case 0:
          return true;
        default:
          return false;
      }
    }
  
  }

  public static class StaticPropertyTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
    /** The current state. The following values are valid states:
     *  0 = Before parsing the element
     *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}nodeSize
     *  2 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}nodeFontName
     *  3 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}nodeFontSize
     *  4 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}colorUtilityNode
     *  5 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}colorDecisionNode
     *  6 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}colorDiscreteProbabilisticNode
     *  7 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}colorContinuousProbilisticNode
     *  8 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}colorExplanationNode
     * 
     */
    private int __state;
  
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      switch (__state) {
        case 0:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "nodeSize".equals(pLocalName)) {
            __state = 1;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "nodeFontName".equals(pLocalName)) {
            __state = 2;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "nodeFontSize".equals(pLocalName)) {
            __state = 3;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorUtilityNode".equals(pLocalName)) {
            __state = 4;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorDecisionNode".equals(pLocalName)) {
            __state = 5;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorDiscreteProbabilisticNode".equals(pLocalName)) {
            __state = 6;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorContinuousProbilisticNode".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorExplanationNode".equals(pLocalName)) {
            __state = 8;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 1:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "nodeFontName".equals(pLocalName)) {
            __state = 2;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "nodeFontSize".equals(pLocalName)) {
            __state = 3;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorUtilityNode".equals(pLocalName)) {
            __state = 4;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorDecisionNode".equals(pLocalName)) {
            __state = 5;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorDiscreteProbabilisticNode".equals(pLocalName)) {
            __state = 6;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorContinuousProbilisticNode".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorExplanationNode".equals(pLocalName)) {
            __state = 8;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 2:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "nodeFontSize".equals(pLocalName)) {
            __state = 3;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorUtilityNode".equals(pLocalName)) {
            __state = 4;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorDecisionNode".equals(pLocalName)) {
            __state = 5;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorDiscreteProbabilisticNode".equals(pLocalName)) {
            __state = 6;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorContinuousProbilisticNode".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorExplanationNode".equals(pLocalName)) {
            __state = 8;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 3:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorUtilityNode".equals(pLocalName)) {
            __state = 4;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorDecisionNode".equals(pLocalName)) {
            __state = 5;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorDiscreteProbabilisticNode".equals(pLocalName)) {
            __state = 6;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorContinuousProbilisticNode".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorExplanationNode".equals(pLocalName)) {
            __state = 8;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 4:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorDecisionNode".equals(pLocalName)) {
            __state = 5;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorDiscreteProbabilisticNode".equals(pLocalName)) {
            __state = 6;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorContinuousProbilisticNode".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorExplanationNode".equals(pLocalName)) {
            __state = 8;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 5:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorDiscreteProbabilisticNode".equals(pLocalName)) {
            __state = 6;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorContinuousProbilisticNode".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorExplanationNode".equals(pLocalName)) {
            __state = 8;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 6:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorContinuousProbilisticNode".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorExplanationNode".equals(pLocalName)) {
            __state = 8;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 7:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorExplanationNode".equals(pLocalName)) {
            __state = 8;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 8:
          break;
        default:
          throw new java.lang.IllegalStateException("Invalid state: " + __state);
      }
      return false;
    }
  
    public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
      unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.StaticPropertyType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.StaticPropertyType) result;
      switch (__state) {
        case 1:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "nodeSize".equals(pLocalName)) {
            try {
              _1.setNodeSize(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
            } catch (java.lang.Exception _2) {
              getHandler().parseConversionEvent("Failed to convert value of {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}nodeSize: " + pResult, _2);
            }
            return;
          }
          break;
        case 2:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "nodeFontName".equals(pLocalName)) {
            _1.setNodeFontName((java.lang.String) pResult);
            return;
          }
          break;
        case 3:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "nodeFontSize".equals(pLocalName)) {
            try {
              _1.setNodeFontSize(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
            } catch (java.lang.Exception _3) {
              getHandler().parseConversionEvent("Failed to convert value of {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}nodeFontSize: " + pResult, _3);
            }
            return;
          }
          break;
        case 4:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorUtilityNode".equals(pLocalName)) {
            try {
              _1.setColorUtilityNode(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
            } catch (java.lang.Exception _4) {
              getHandler().parseConversionEvent("Failed to convert value of {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}colorUtilityNode: " + pResult, _4);
            }
            return;
          }
          break;
        case 5:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorDecisionNode".equals(pLocalName)) {
            try {
              _1.setColorDecisionNode(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
            } catch (java.lang.Exception _5) {
              getHandler().parseConversionEvent("Failed to convert value of {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}colorDecisionNode: " + pResult, _5);
            }
            return;
          }
          break;
        case 6:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorDiscreteProbabilisticNode".equals(pLocalName)) {
            try {
              _1.setColorDiscreteProbabilisticNode(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
            } catch (java.lang.Exception _6) {
              getHandler().parseConversionEvent("Failed to convert value of {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}colorDiscreteProbabilisticNode: " + pResult, _6);
            }
            return;
          }
          break;
        case 7:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorContinuousProbilisticNode".equals(pLocalName)) {
            try {
              _1.setColorContinuousProbilisticNode(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
            } catch (java.lang.Exception _7) {
              getHandler().parseConversionEvent("Failed to convert value of {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}colorContinuousProbilisticNode: " + pResult, _7);
            }
            return;
          }
          break;
        case 8:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "colorExplanationNode".equals(pLocalName)) {
            try {
              _1.setColorExplanationNode(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
            } catch (java.lang.Exception _8) {
              getHandler().parseConversionEvent("Failed to convert value of {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}colorExplanationNode: " + pResult, _8);
            }
            return;
          }
          break;
        default:
          throw new java.lang.IllegalStateException("Illegal state: " + __state);
      }
    }
  
    public boolean isFinished() {
      switch (__state) {
        case 8:
        case 7:
        case 6:
        case 5:
        case 4:
        case 3:
        case 2:
        case 1:
        case 0:
          return true;
        default:
          return false;
      }
    }
  
  }

  public static class HierarchyTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
    public static class RootTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
      public static class LevelTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
        public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
          if (pURI == null) {
            pURI = "";
          }
          unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.HierarchyType.RootType.LevelType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.HierarchyType.RootType.LevelType) result;
          if ("".equals(pURI)) {
            if ("name".equals(pLocalName)) {
              _1.setName((java.lang.String) pValue);
              return;
            }
          }
          super.addAttribute(pURI, pLocalName, pValue);
        }
      
        public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
          return false;
        }
      
        public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
        }
      
        public boolean isFinished() {
          return true;
        }
      
        public boolean isEmpty() {
          return true;
        }
      
        public boolean isAtomic() {
          return false;
        }
      
      }
    
      /** The current state. The following values are valid states:
       *  0 = Before parsing the element
       *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}level
       * 
       */
      private int __state;
    
    
      public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
        if (pURI == null) {
          pURI = "";
        }
        unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.HierarchyType.RootType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.HierarchyType.RootType) result;
        if ("".equals(pURI)) {
          if ("name".equals(pLocalName)) {
            _1.setName((java.lang.String) pValue);
            return;
          }
        }
        super.addAttribute(pURI, pLocalName, pValue);
      }
    
      public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
        org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
        switch (__state) {
          case 0:
            if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "level".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.HierarchyTypeImpl.RootTypeImpl.LevelTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.HierarchyTypeHandler.RootTypeHandler.LevelTypeHandler();
              _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "level", _1.getLevel());
              _3.setAttributes(pAttr);
              _1.addElementParser(_3);
              return true;
            }
            break;
          case 1:
            if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "level".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.HierarchyTypeImpl.RootTypeImpl.LevelTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.HierarchyTypeHandler.RootTypeHandler.LevelTypeHandler();
              _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "level", _1.getLevel());
              _5.setAttributes(pAttr);
              _1.addElementParser(_5);
              return true;
            }
            break;
          default:
            throw new java.lang.IllegalStateException("Invalid state: " + __state);
        }
        return false;
      }
    
      public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
        unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.HierarchyType.RootType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.HierarchyType.RootType) result;
        switch (__state) {
          case 1:
            if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "level".equals(pLocalName)) {
              _1.getLevel().add(pResult);
              return;
            }
            break;
          default:
            throw new java.lang.IllegalStateException("Illegal state: " + __state);
        }
      }
    
      public boolean isFinished() {
        switch (__state) {
          case 1:
          case 0:
            return true;
          default:
            return false;
        }
      }
    
    }
  
    /** The current state. The following values are valid states:
     *  0 = Before parsing the element
     *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}root
     * 
     */
    private int __state;
  
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      switch (__state) {
        case 0:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "root".equals(pLocalName)) {
            __state = 1;
            java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.HierarchyTypeImpl.RootTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.HierarchyTypeHandler.RootTypeHandler();
            _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "root", _1.getLevel());
            _3.setAttributes(pAttr);
            _1.addElementParser(_3);
            return true;
          }
          break;
        case 1:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "root".equals(pLocalName)) {
            __state = 1;
            java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.HierarchyTypeImpl.RootTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.HierarchyTypeHandler.RootTypeHandler();
            _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "root", _1.getLevel());
            _5.setAttributes(pAttr);
            _1.addElementParser(_5);
            return true;
          }
          break;
        default:
          throw new java.lang.IllegalStateException("Invalid state: " + __state);
      }
      return false;
    }
  
    public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
      unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.HierarchyType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.HierarchyType) result;
      switch (__state) {
        case 1:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "root".equals(pLocalName)) {
            _1.getRoot().add(pResult);
            return;
          }
          break;
        default:
          throw new java.lang.IllegalStateException("Illegal state: " + __state);
      }
    }
  
    public boolean isFinished() {
      switch (__state) {
        case 1:
        case 0:
          return true;
        default:
          return false;
      }
    }
  
  }

  public static class NetworkTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
    public static class VariablesTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
      public static class VariableTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
        public static class StateTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
          /** The current state. The following values are valid states:
           *  0 = Before parsing the element
           *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}description
           *  2 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}mmedia
           * 
           */
          private int __state;
        
        
          public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
            if (pURI == null) {
              pURI = "";
            }
            unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.StateType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.StateType) result;
            if ("".equals(pURI)) {
              if ("name".equals(pLocalName)) {
                _1.setName((java.lang.String) pValue);
                return;
              }
            }
            super.addAttribute(pURI, pLocalName, pValue);
          }
        
          public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
            org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
            switch (__state) {
              case 0:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "description".equals(pLocalName)) {
                  __state = 1;
                  _1.addSimpleAtomicState();
                  return true;
                } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "mmedia".equals(pLocalName)) {
                  __state = 2;
                  _1.addSimpleAtomicState();
                  return true;
                }
                break;
              case 1:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "mmedia".equals(pLocalName)) {
                  __state = 2;
                  _1.addSimpleAtomicState();
                  return true;
                }
                break;
              case 2:
                break;
              default:
                throw new java.lang.IllegalStateException("Invalid state: " + __state);
            }
            return false;
          }
        
          public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.StateType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.StateType) result;
            switch (__state) {
              case 1:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "description".equals(pLocalName)) {
                  _1.setDescription((java.lang.String) pResult);
                  return;
                }
                break;
              case 2:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "mmedia".equals(pLocalName)) {
                  try {
                    _1.setMmedia(getHandler().getDatatypeConverter().parseBase64Binary((java.lang.String) pResult));
                  } catch (java.lang.Exception _2) {
                    getHandler().parseConversionEvent("Failed to convert value of {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}mmedia: " + pResult, _2);
                  }
                  return;
                }
                break;
              default:
                throw new java.lang.IllegalStateException("Illegal state: " + __state);
            }
          }
        
          public boolean isFinished() {
            switch (__state) {
              case 2:
              case 1:
              case 0:
                return true;
              default:
                return false;
            }
          }
        
        }
      
        public static class MetaphoreTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
          public static class ExplanationTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
            /** The current state. The following values are valid states:
             *  0 = Before parsing the element
             *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}comments
             * 
             */
            private int __state;
          
          
            public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
              if (pURI == null) {
                pURI = "";
              }
              unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType) result;
              if ("".equals(pURI)) {
                if ("evidence".equals(pLocalName)) {
                  _1.setEvidence((java.lang.String) pValue);
                  return;
                } else if ("evidenceType".equals(pLocalName)) {
                  _1.setEvidenceType((java.lang.String) pValue);
                  return;
                }
              }
              super.addAttribute(pURI, pLocalName, pValue);
            }
          
            public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
              org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
              switch (__state) {
                case 0:
                  if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "comments".equals(pLocalName)) {
                    __state = 1;
                    _1.addSimpleAtomicState();
                    return true;
                  }
                  break;
                case 1:
                  break;
                default:
                  throw new java.lang.IllegalStateException("Invalid state: " + __state);
              }
              return false;
            }
          
            public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
              unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType) result;
              switch (__state) {
                case 1:
                  if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "comments".equals(pLocalName)) {
                    _1.setComments((java.lang.String) pResult);
                    return;
                  }
                  break;
                default:
                  throw new java.lang.IllegalStateException("Illegal state: " + __state);
              }
            }
          
            public boolean isFinished() {
              switch (__state) {
                case 1:
                case 0:
                  return true;
                default:
                  return false;
              }
            }
          
          }
        
          /** The current state. The following values are valid states:
           *  0 = Before parsing the element
           *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}description
           *  2 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}explanation
           * 
           */
          private int __state;
        
        
          public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
            org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
            switch (__state) {
              case 0:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "description".equals(pLocalName)) {
                  __state = 1;
                  _1.addSimpleAtomicState();
                  return true;
                } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "explanation".equals(pLocalName)) {
                  __state = 2;
                  java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.MetaphoreTypeImpl.ExplanationTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler.VariableTypeHandler.MetaphoreTypeHandler.ExplanationTypeHandler();
                  _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "explanation", _1.getLevel());
                  _3.setAttributes(pAttr);
                  _1.addElementParser(_3);
                  return true;
                }
                break;
              case 1:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "explanation".equals(pLocalName)) {
                  __state = 2;
                  java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.MetaphoreTypeImpl.ExplanationTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler.VariableTypeHandler.MetaphoreTypeHandler.ExplanationTypeHandler();
                  _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "explanation", _1.getLevel());
                  _5.setAttributes(pAttr);
                  _1.addElementParser(_5);
                  return true;
                }
                break;
              case 2:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "explanation".equals(pLocalName)) {
                  __state = 2;
                  java.lang.Object _6 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.MetaphoreTypeImpl.ExplanationTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _7 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler.VariableTypeHandler.MetaphoreTypeHandler.ExplanationTypeHandler();
                  _7.init(_1, _6, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "explanation", _1.getLevel());
                  _7.setAttributes(pAttr);
                  _1.addElementParser(_7);
                  return true;
                }
                break;
              default:
                throw new java.lang.IllegalStateException("Invalid state: " + __state);
            }
            return false;
          }
        
          public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType) result;
            switch (__state) {
              case 1:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "description".equals(pLocalName)) {
                  _1.setDescription((java.lang.String) pResult);
                  return;
                }
                break;
              case 2:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "explanation".equals(pLocalName)) {
                  _1.getExplanation().add(pResult);
                  return;
                }
                break;
              default:
                throw new java.lang.IllegalStateException("Illegal state: " + __state);
            }
          }
        
          public boolean isFinished() {
            switch (__state) {
              case 2:
              case 1:
              case 0:
                return true;
              default:
                return false;
            }
          }
        
        }
      
        /** The current state. The following values are valid states:
         *  0 = Before parsing the element
         *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}description
         *  2 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}mmedia
         *  3 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}state
         *  4 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}metaphore
         * 
         */
        private int __state;
      
      
        public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
          if (pURI == null) {
            pURI = "";
          }
          unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType) result;
          if ("".equals(pURI)) {
            if ("name".equals(pLocalName)) {
              _1.setName((java.lang.String) pValue);
              return;
            } else if ("type".equals(pLocalName)) {
              _1.setType((java.lang.String) pValue);
              return;
            } else if ("xPos".equals(pLocalName)) {
              try {
                _1.setXPos(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
              } catch (java.lang.Exception _2) {
                getHandler().parseConversionEvent("Failed to convert value of @xPos: " + pValue, _2);
              }
              return;
            } else if ("yPos".equals(pLocalName)) {
              try {
                _1.setYPos(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
              } catch (java.lang.Exception _3) {
                getHandler().parseConversionEvent("Failed to convert value of @yPos: " + pValue, _3);
              }
              return;
           //by young
            } else if ("Color".equals(pLocalName)) {
                try {
                  _1.setCOLOR(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
                } catch (java.lang.Exception _4) {
                  getHandler().parseConversionEvent("Failed to convert value of @Color: " + pValue, _4);
                }
                return;
              }//by young end
          }
          super.addAttribute(pURI, pLocalName, pValue);
        }
      
        public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
          org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
          switch (__state) {
            case 0:
              if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "description".equals(pLocalName)) {
                __state = 1;
                _1.addSimpleAtomicState();
                return true;
              } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "mmedia".equals(pLocalName)) {
                __state = 2;
                _1.addSimpleAtomicState();
                return true;
              } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "state".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.StateTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler.VariableTypeHandler.StateTypeHandler();
                _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "state", _1.getLevel());
                _3.setAttributes(pAttr);
                _1.addElementParser(_3);
                return true;
              } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "metaphore".equals(pLocalName)) {
                __state = 4;
                java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.MetaphoreTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler.VariableTypeHandler.MetaphoreTypeHandler();
                _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "metaphore", _1.getLevel());
                _5.setAttributes(pAttr);
                _1.addElementParser(_5);
                return true;
              }
              break;
            case 1:
              if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "mmedia".equals(pLocalName)) {
                __state = 2;
                _1.addSimpleAtomicState();
                return true;
              } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "state".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _6 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.StateTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _7 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler.VariableTypeHandler.StateTypeHandler();
                _7.init(_1, _6, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "state", _1.getLevel());
                _7.setAttributes(pAttr);
                _1.addElementParser(_7);
                return true;
              } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "metaphore".equals(pLocalName)) {
                __state = 4;
                java.lang.Object _8 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.MetaphoreTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _9 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler.VariableTypeHandler.MetaphoreTypeHandler();
                _9.init(_1, _8, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "metaphore", _1.getLevel());
                _9.setAttributes(pAttr);
                _1.addElementParser(_9);
                return true;
              }
              break;
            case 2:
              if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "state".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _10 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.StateTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _11 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler.VariableTypeHandler.StateTypeHandler();
                _11.init(_1, _10, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "state", _1.getLevel());
                _11.setAttributes(pAttr);
                _1.addElementParser(_11);
                return true;
              } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "metaphore".equals(pLocalName)) {
                __state = 4;
                java.lang.Object _12 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.MetaphoreTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _13 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler.VariableTypeHandler.MetaphoreTypeHandler();
                _13.init(_1, _12, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "metaphore", _1.getLevel());
                _13.setAttributes(pAttr);
                _1.addElementParser(_13);
                return true;
              }
              break;
            case 3:
              if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "state".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _14 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.StateTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _15 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler.VariableTypeHandler.StateTypeHandler();
                _15.init(_1, _14, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "state", _1.getLevel());
                _15.setAttributes(pAttr);
                _1.addElementParser(_15);
                return true;
              } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "metaphore".equals(pLocalName)) {
                __state = 4;
                java.lang.Object _16 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.MetaphoreTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _17 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler.VariableTypeHandler.MetaphoreTypeHandler();
                _17.init(_1, _16, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "metaphore", _1.getLevel());
                _17.setAttributes(pAttr);
                _1.addElementParser(_17);
                return true;
              }
              break;
            case 4:
              break;
            default:
              throw new java.lang.IllegalStateException("Invalid state: " + __state);
          }
          return false;
        }
      
        public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
          unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType) result;
          switch (__state) {
            case 1:
              if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "description".equals(pLocalName)) {
                _1.setDescription((java.lang.String) pResult);
                return;
              }
              break;
            case 2:
              if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "mmedia".equals(pLocalName)) {
                try {
                  _1.setMmedia(getHandler().getDatatypeConverter().parseBase64Binary((java.lang.String) pResult));
                } catch (java.lang.Exception _2) {
                  getHandler().parseConversionEvent("Failed to convert value of {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}mmedia: " + pResult, _2);
                }
                return;
              }
              break;
            case 3:
              if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "state".equals(pLocalName)) {
                _1.getState().add(pResult);
                return;
              }
              break;
            case 4:
              if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "metaphore".equals(pLocalName)) {
                _1.setMetaphore(((unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType) pResult));
                return;
              }
              break;
            default:
              throw new java.lang.IllegalStateException("Illegal state: " + __state);
          }
        }
      
        public boolean isFinished() {
          switch (__state) {
            case 4:
            case 3:
            case 2:
            case 1:
            case 0:
              return true;
            default:
              return false;
          }
        }
      
      }
    
      /** The current state. The following values are valid states:
       *  0 = Before parsing the element
       *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}variable
       * 
       */
      private int __state;
    
    
      public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
        org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
        switch (__state) {
          case 0:
            if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "variable".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler.VariableTypeHandler();
              _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "variable", _1.getLevel());
              _3.setAttributes(pAttr);
              _1.addElementParser(_3);
              return true;
            }
            break;
          case 1:
            if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "variable".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler.VariableTypeHandler();
              _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "variable", _1.getLevel());
              _5.setAttributes(pAttr);
              _1.addElementParser(_5);
              return true;
            }
            break;
          default:
            throw new java.lang.IllegalStateException("Invalid state: " + __state);
        }
        return false;
      }
    
      public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
        unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType) result;
        switch (__state) {
          case 1:
            if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "variable".equals(pLocalName)) {
              _1.getVariable().add(pResult);
              return;
            }
            break;
          default:
            throw new java.lang.IllegalStateException("Illegal state: " + __state);
        }
      }
    
      public boolean isFinished() {
        switch (__state) {
          case 1:
          case 0:
            return true;
          default:
            return false;
        }
      }
    
    }
  
    public static class StructureTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
      public static class EdgeTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
        public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
          if (pURI == null) {
            pURI = "";
          }
          unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.StructureType.EdgeType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.StructureType.EdgeType) result;
          if ("".equals(pURI)) {
            if ("parent".equals(pLocalName)) {
              _1.setParent((java.lang.String) pValue);
              return;
            } else if ("child".equals(pLocalName)) {
              _1.setChild((java.lang.String) pValue);
              return;
            }
          }
          super.addAttribute(pURI, pLocalName, pValue);
        }
      
        public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
          return false;
        }
      
        public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
        }
      
        public boolean isFinished() {
          return true;
        }
      
        public boolean isEmpty() {
          return true;
        }
      
        public boolean isAtomic() {
          return false;
        }
      
      }
    
      /** The current state. The following values are valid states:
       *  0 = Before parsing the element
       *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}edge
       * 
       */
      private int __state;
    
    
      public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
        org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
        switch (__state) {
          case 0:
            if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "edge".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.StructureTypeImpl.EdgeTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.StructureTypeHandler.EdgeTypeHandler();
              _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "edge", _1.getLevel());
              _3.setAttributes(pAttr);
              _1.addElementParser(_3);
              return true;
            }
            break;
          case 1:
            if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "edge".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.StructureTypeImpl.EdgeTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.StructureTypeHandler.EdgeTypeHandler();
              _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "edge", _1.getLevel());
              _5.setAttributes(pAttr);
              _1.addElementParser(_5);
              return true;
            }
            break;
          default:
            throw new java.lang.IllegalStateException("Invalid state: " + __state);
        }
        return false;
      }
    
      public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
        unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.StructureType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.StructureType) result;
        switch (__state) {
          case 1:
            if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "edge".equals(pLocalName)) {
              _1.getEdge().add(pResult);
              return;
            }
            break;
          default:
            throw new java.lang.IllegalStateException("Illegal state: " + __state);
        }
      }
    
      public boolean isFinished() {
        switch (__state) {
          case 1:
          case 0:
            return true;
          default:
            return false;
        }
      }
    
    }
  
    public static class ConditionalDistributionSetTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
      public static class ConditionalDistributionTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
        public static class OwnerTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
          public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
            if (pURI == null) {
              pURI = "";
            }
            unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType) result;
            if ("".equals(pURI)) {
              if ("name".equals(pLocalName)) {
                _1.setName((java.lang.String) pValue);
                return;
              }
            }
            super.addAttribute(pURI, pLocalName, pValue);
          }
        
          public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
            return false;
          }
        
          public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
          }
        
          public boolean isFinished() {
            return true;
          }
        
          public boolean isEmpty() {
            return true;
          }
        
          public boolean isAtomic() {
            return false;
          }
        
        }
      
        public static class ParentsTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
          public static class ParentTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
            public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
              if (pURI == null) {
                pURI = "";
              }
              unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType.ParentType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType.ParentType) result;
              if ("".equals(pURI)) {
                if ("name".equals(pLocalName)) {
                  _1.setName((java.lang.String) pValue);
                  return;
                } else if ("index".equals(pLocalName)) {
                  try {
                    _1.setIndex(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
                  } catch (java.lang.Exception _2) {
                    getHandler().parseConversionEvent("Failed to convert value of @index: " + pValue, _2);
                  }
                  return;
                }
              }
              super.addAttribute(pURI, pLocalName, pValue);
            }
          
            public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
              return false;
            }
          
            public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
            }
          
            public boolean isFinished() {
              return true;
            }
          
            public boolean isEmpty() {
              return true;
            }
          
            public boolean isAtomic() {
              return false;
            }
          
          }
        
          /** The current state. The following values are valid states:
           *  0 = Before parsing the element
           *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}parent
           * 
           */
          private int __state;
        
        
          public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
            org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
            switch (__state) {
              case 0:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "parent".equals(pLocalName)) {
                  __state = 1;
                  java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.ParentsTypeImpl.ParentTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.ParentsTypeHandler.ParentTypeHandler();
                  _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "parent", _1.getLevel());
                  _3.setAttributes(pAttr);
                  _1.addElementParser(_3);
                  return true;
                }
                break;
              case 1:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "parent".equals(pLocalName)) {
                  __state = 1;
                  java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.ParentsTypeImpl.ParentTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.ParentsTypeHandler.ParentTypeHandler();
                  _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "parent", _1.getLevel());
                  _5.setAttributes(pAttr);
                  _1.addElementParser(_5);
                  return true;
                }
                break;
              default:
                throw new java.lang.IllegalStateException("Invalid state: " + __state);
            }
            return false;
          }
        
          public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType) result;
            switch (__state) {
              case 1:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "parent".equals(pLocalName)) {
                  _1.getParent().add(pResult);
                  return;
                }
                break;
              default:
                throw new java.lang.IllegalStateException("Illegal state: " + __state);
            }
          }
        
          public boolean isFinished() {
            switch (__state) {
              case 1:
              case 0:
                return true;
              default:
                return false;
            }
          }
        
        }
      
        public class CPTChoiceNormalHandler extends org.apache.ws.jaxme.impl.JMSAXGroupParser {
          /** Will be set to true, if the first child is parsed.
           * It is an error, if another child is parsed, and the
           * fields value is true.
           * 
           */
          private boolean __state;
        
          /** Index of the particle being currently parsed
           * 
           */
          private int __childNum;
        
        
          public org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl getHandler() {
            return unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.this.getHandler();
          }
        
          public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
            org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
            if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "CPT".equals(pLocalName)) {
              if (__state) {
                if (__childNum != 0) {
                  getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "Multiple different particles present in a choive group.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
                } else {
                  getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}CPT has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
                }
              }
              __state = true;
              __childNum = 0;
              java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.CPTTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.CPTTypeHandler();
              _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "CPT", _1.getLevel());
              _3.setAttributes(pAttr);
              _1.addElementParser(_3);
              return true;
            } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "normal".equals(pLocalName)) {
              if (__state) {
                if (__childNum != 1) {
                  getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "Multiple different particles present in a choive group.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
                } else {
                  getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}normal has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
                }
              }
              __state = true;
              __childNum = 1;
              java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.NormalTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.NormalTypeHandler();
              _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "normal", _1.getLevel());
              _5.setAttributes(pAttr);
              _1.addElementParser(_5);
              return true;
            }
            return false;
          }
        
          public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType) result;
            switch (__childNum) {
              case 0:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "CPT".equals(pLocalName)) {
                  _1.setCPT(((unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType) pResult));
                  return;
                }
                break;
              case 1:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "normal".equals(pLocalName)) {
                  _1.setNormal(((unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType) pResult));
                  return;
                }
                break;
              default:
                throw new java.lang.IllegalStateException("Illegal state: " + __childNum);
            }
          }
        
          public boolean isFinished() {
            return __state;
          }
        
        }
      
        public static class CPTTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
          public static class DependentParentIndexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
            public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
              if (pURI == null) {
                pURI = "";
              }
              unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType) result;
              if ("".equals(pURI)) {
                if ("index".equals(pLocalName)) {
                  try {
                    _1.setIndex(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
                  } catch (java.lang.Exception _2) {
                    getHandler().parseConversionEvent("Failed to convert value of @index: " + pValue, _2);
                  }
                  return;
                }
              }
              super.addAttribute(pURI, pLocalName, pValue);
            }
          
            public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
              return false;
            }
          
            public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
              unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType) result;
              try {
                _1.setValue(getHandler().getDatatypeConverter().parseDouble((java.lang.String) pResult));
              } catch (java.lang.Exception _2) {
                getHandler().parseConversionEvent("Failed to convert value of value: " + pResult, _2);
              }
            }
          
            public boolean isFinished() {
              return true;
            }
          
            public boolean isEmpty() {
              return false;
            }
          
            public boolean isAtomic() {
              return true;
            }
          
          }
        
          /** The current state. The following values are valid states:
           *  0 = Before parsing the element
           *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}dependentParentIndex
           * 
           */
          private int __state;
        
        
          public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
            org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
            switch (__state) {
              case 0:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "dependentParentIndex".equals(pLocalName)) {
                  __state = 1;
                  java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.CPTTypeImpl.DependentParentIndexTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.CPTTypeHandler.DependentParentIndexTypeHandler();
                  _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "dependentParentIndex", _1.getLevel());
                  _3.setAttributes(pAttr);
                  _1.addElementParser(_3);
                  return true;
                }
                break;
              case 1:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "dependentParentIndex".equals(pLocalName)) {
                  __state = 1;
                  java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.CPTTypeImpl.DependentParentIndexTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.CPTTypeHandler.DependentParentIndexTypeHandler();
                  _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "dependentParentIndex", _1.getLevel());
                  _5.setAttributes(pAttr);
                  _1.addElementParser(_5);
                  return true;
                }
                break;
              default:
                throw new java.lang.IllegalStateException("Invalid state: " + __state);
            }
            return false;
          }
        
          public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType) result;
            switch (__state) {
              case 1:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "dependentParentIndex".equals(pLocalName)) {
                  _1.getDependentParentIndex().add(pResult);
                  return;
                }
                break;
              default:
                throw new java.lang.IllegalStateException("Illegal state: " + __state);
            }
          }
        
          public boolean isFinished() {
            switch (__state) {
              case 1:
                return true;
              default:
                return false;
            }
          }
        
        }
      
        public static class NormalTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
          public static class FunctionTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
            public static class ConstantsTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
              public static class ConstantTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
                public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
                  if (pURI == null) {
                    pURI = "";
                  }
                  unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType) result;
                  if ("".equals(pURI)) {
                    if ("index".equals(pLocalName)) {
                      try {
                        _1.setIndex(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
                      } catch (java.lang.Exception _2) {
                        getHandler().parseConversionEvent("Failed to convert value of @index: " + pValue, _2);
                      }
                      return;
                    }
                  }
                  super.addAttribute(pURI, pLocalName, pValue);
                }
              
                public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
                  return false;
                }
              
                public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
                  unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType) result;
                  try {
                    _1.setValue(getHandler().getDatatypeConverter().parseDouble((java.lang.String) pResult));
                  } catch (java.lang.Exception _2) {
                    getHandler().parseConversionEvent("Failed to convert value of value: " + pResult, _2);
                  }
                }
              
                public boolean isFinished() {
                  return true;
                }
              
                public boolean isEmpty() {
                  return false;
                }
              
                public boolean isAtomic() {
                  return true;
                }
              
              }
            
              /** The current state. The following values are valid states:
               *  0 = Before parsing the element
               *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}constant
               * 
               */
              private int __state;
            
            
              public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
                org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
                switch (__state) {
                  case 0:
                    if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "constant".equals(pLocalName)) {
                      __state = 1;
                      java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.NormalTypeImpl.FunctionTypeImpl.ConstantsTypeImpl.ConstantTypeImpl();
                      org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.NormalTypeHandler.FunctionTypeHandler.ConstantsTypeHandler.ConstantTypeHandler();
                      _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "constant", _1.getLevel());
                      _3.setAttributes(pAttr);
                      _1.addElementParser(_3);
                      return true;
                    }
                    break;
                  case 1:
                    if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "constant".equals(pLocalName)) {
                      __state = 1;
                      java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.NormalTypeImpl.FunctionTypeImpl.ConstantsTypeImpl.ConstantTypeImpl();
                      org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.NormalTypeHandler.FunctionTypeHandler.ConstantsTypeHandler.ConstantTypeHandler();
                      _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "constant", _1.getLevel());
                      _5.setAttributes(pAttr);
                      _1.addElementParser(_5);
                      return true;
                    }
                    break;
                  default:
                    throw new java.lang.IllegalStateException("Invalid state: " + __state);
                }
                return false;
              }
            
              public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
                unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType) result;
                switch (__state) {
                  case 1:
                    if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "constant".equals(pLocalName)) {
                      _1.getConstant().add(pResult);
                      return;
                    }
                    break;
                  default:
                    throw new java.lang.IllegalStateException("Illegal state: " + __state);
                }
              }
            
              public boolean isFinished() {
                switch (__state) {
                  case 1:
                    return true;
                  default:
                    return false;
                }
              }
            
            }
          
            /** The current state. The following values are valid states:
             *  0 = Before parsing the element
             *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}mean
             *  2 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}variance
             *  3 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}constants
             * 
             */
            private int __state;
          
          
            public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
              if (pURI == null) {
                pURI = "";
              }
              unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType) result;
              if ("".equals(pURI)) {
                if ("index".equals(pLocalName)) {
                  try {
                    _1.setIndex(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
                  } catch (java.lang.Exception _2) {
                    getHandler().parseConversionEvent("Failed to convert value of @index: " + pValue, _2);
                  }
                  return;
                }
              }
              super.addAttribute(pURI, pLocalName, pValue);
            }
          
            public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
              org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
              switch (__state) {
                case 0:
                  if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "mean".equals(pLocalName)) {
                    __state = 1;
                    _1.addSimpleAtomicState();
                    return true;
                  }
                  break;
                case 1:
                  if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "variance".equals(pLocalName)) {
                    __state = 2;
                    _1.addSimpleAtomicState();
                    return true;
                  }
                  break;
                case 2:
                  if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "constants".equals(pLocalName)) {
                    __state = 3;
                    java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.NormalTypeImpl.FunctionTypeImpl.ConstantsTypeImpl();
                    org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.NormalTypeHandler.FunctionTypeHandler.ConstantsTypeHandler();
                    _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "constants", _1.getLevel());
                    _3.setAttributes(pAttr);
                    _1.addElementParser(_3);
                    return true;
                  }
                  break;
                case 3:
                  break;
                default:
                  throw new java.lang.IllegalStateException("Invalid state: " + __state);
              }
              return false;
            }
          
            public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
              unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType) result;
              switch (__state) {
                case 1:
                  if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "mean".equals(pLocalName)) {
                    try {
                      _1.setMean(getHandler().getDatatypeConverter().parseDouble((java.lang.String) pResult));
                    } catch (java.lang.Exception _2) {
                      getHandler().parseConversionEvent("Failed to convert value of {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}mean: " + pResult, _2);
                    }
                    return;
                  }
                  break;
                case 2:
                  if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "variance".equals(pLocalName)) {
                    try {
                      _1.setVariance(getHandler().getDatatypeConverter().parseDouble((java.lang.String) pResult));
                    } catch (java.lang.Exception _3) {
                      getHandler().parseConversionEvent("Failed to convert value of {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}variance: " + pResult, _3);
                    }
                    return;
                  }
                  break;
                case 3:
                  if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "constants".equals(pLocalName)) {
                    _1.setConstants(((unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType) pResult));
                    return;
                  }
                  break;
                default:
                  throw new java.lang.IllegalStateException("Illegal state: " + __state);
              }
            }
          
            public boolean isFinished() {
              switch (__state) {
                case 3:
                case 2:
                  return true;
                default:
                  return false;
              }
            }
          
          }
        
          /** The current state. The following values are valid states:
           *  0 = Before parsing the element
           *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}function
           * 
           */
          private int __state;
        
        
          public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
            org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
            switch (__state) {
              case 0:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "function".equals(pLocalName)) {
                  __state = 1;
                  java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.NormalTypeImpl.FunctionTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.NormalTypeHandler.FunctionTypeHandler();
                  _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "function", _1.getLevel());
                  _3.setAttributes(pAttr);
                  _1.addElementParser(_3);
                  return true;
                }
                break;
              case 1:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "function".equals(pLocalName)) {
                  __state = 1;
                  java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.NormalTypeImpl.FunctionTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.NormalTypeHandler.FunctionTypeHandler();
                  _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "function", _1.getLevel());
                  _5.setAttributes(pAttr);
                  _1.addElementParser(_5);
                  return true;
                }
                break;
              default:
                throw new java.lang.IllegalStateException("Invalid state: " + __state);
            }
            return false;
          }
        
          public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType) result;
            switch (__state) {
              case 1:
                if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "function".equals(pLocalName)) {
                  _1.getFunction().add(pResult);
                  return;
                }
                break;
              default:
                throw new java.lang.IllegalStateException("Illegal state: " + __state);
            }
          }
        
          public boolean isFinished() {
            switch (__state) {
              case 1:
                return true;
              default:
                return false;
            }
          }
        
        }
      
        /** The current state. The following values are valid states:
         *  0 = Before parsing the element
         *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}owner
         *  2 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}parents
         *  3 = While parsing the nested group CPTChoiceNormal
         * 
         */
        private int __state;
      
      
        public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
          if (pURI == null) {
            pURI = "";
          }
          unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType) result;
          if ("".equals(pURI)) {
            if ("type".equals(pLocalName)) {
              _1.setType((java.lang.String) pValue);
              return;
            }
          }
          super.addAttribute(pURI, pLocalName, pValue);
        }
      
        public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
          org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
          switch (__state) {
            case 0:
              if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "owner".equals(pLocalName)) {
                __state = 1;
                java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.OwnerTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.OwnerTypeHandler();
                _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "owner", _1.getLevel());
                _3.setAttributes(pAttr);
                _1.addElementParser(_3);
                return true;
              }
              break;
            case 1:
              if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "parents".equals(pLocalName)) {
                __state = 2;
                java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.ParentsTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.ParentsTypeHandler();
                _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "parents", _1.getLevel());
                _5.setAttributes(pAttr);
                _1.addElementParser(_5);
                return true;
              } else if (_1.testGroupParser(new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.CPTChoiceNormalHandler(), pNamespaceURI, pLocalName, pQName, pAttr)) {
                __state = 3;
                return true;
              }
              break;
            case 2:
              if (_1.testGroupParser(new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler.CPTChoiceNormalHandler(), pNamespaceURI, pLocalName, pQName, pAttr)) {
                __state = 3;
                return true;
              }
              break;
            case 3:
              break;
            default:
              throw new java.lang.IllegalStateException("Invalid state: " + __state);
          }
          return false;
        }
      
        public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
          unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType) result;
          switch (__state) {
            case 1:
              if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "owner".equals(pLocalName)) {
                _1.setOwner(((unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType) pResult));
                return;
              }
              break;
            case 2:
              if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "parents".equals(pLocalName)) {
                _1.setParents(((unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType) pResult));
                return;
              }
              break;
            case 3:
              throw new java.lang.IllegalStateException("This case should be handled by a nested group parser.");
            default:
              throw new java.lang.IllegalStateException("Illegal state: " + __state);
          }
        }
      
        public boolean isFinished() {
          switch (__state) {
            case 3:
              return true;
            default:
              return false;
          }
        }
      
      }
    
      /** The current state. The following values are valid states:
       *  0 = Before parsing the element
       *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}conditionalDistribution
       * 
       */
      private int __state;
    
    
      public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
        org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
        switch (__state) {
          case 0:
            if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "conditionalDistribution".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler();
              _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "conditionalDistribution", _1.getLevel());
              _3.setAttributes(pAttr);
              _1.addElementParser(_3);
              return true;
            }
            break;
          case 1:
            if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "conditionalDistribution".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler.ConditionalDistributionTypeHandler();
              _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "conditionalDistribution", _1.getLevel());
              _5.setAttributes(pAttr);
              _1.addElementParser(_5);
              return true;
            }
            break;
          default:
            throw new java.lang.IllegalStateException("Invalid state: " + __state);
        }
        return false;
      }
    
      public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
        unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType) result;
        switch (__state) {
          case 1:
            if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "conditionalDistribution".equals(pLocalName)) {
              _1.getConditionalDistribution().add(pResult);
              return;
            }
            break;
          default:
            throw new java.lang.IllegalStateException("Illegal state: " + __state);
        }
      }
    
      public boolean isFinished() {
        switch (__state) {
          case 1:
          case 0:
            return true;
          default:
            return false;
        }
      }
    
    }
  
    /** The current state. The following values are valid states:
     *  0 = Before parsing the element
     *  1 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}variables
     *  2 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}structure
     *  3 = While or after parsing the child element {http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd}conditionalDistributionSet
     * 
     */
    private int __state;
  
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      switch (__state) {
        case 0:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "variables".equals(pLocalName)) {
            __state = 1;
            java.lang.Object _2 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.VariablesTypeHandler();
            _3.init(_1, _2, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "variables", _1.getLevel());
            _3.setAttributes(pAttr);
            _1.addElementParser(_3);
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "structure".equals(pLocalName)) {
            __state = 2;
            java.lang.Object _4 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.StructureTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.StructureTypeHandler();
            _5.init(_1, _4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "structure", _1.getLevel());
            _5.setAttributes(pAttr);
            _1.addElementParser(_5);
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "conditionalDistributionSet".equals(pLocalName)) {
            __state = 3;
            java.lang.Object _6 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _7 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler();
            _7.init(_1, _6, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "conditionalDistributionSet", _1.getLevel());
            _7.setAttributes(pAttr);
            _1.addElementParser(_7);
            return true;
          }
          break;
        case 1:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "structure".equals(pLocalName)) {
            __state = 2;
            java.lang.Object _8 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.StructureTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _9 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.StructureTypeHandler();
            _9.init(_1, _8, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "structure", _1.getLevel());
            _9.setAttributes(pAttr);
            _1.addElementParser(_9);
            return true;
          } else if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "conditionalDistributionSet".equals(pLocalName)) {
            __state = 3;
            java.lang.Object _10 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _11 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler();
            _11.init(_1, _10, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "conditionalDistributionSet", _1.getLevel());
            _11.setAttributes(pAttr);
            _1.addElementParser(_11);
            return true;
          }
          break;
        case 2:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "conditionalDistributionSet".equals(pLocalName)) {
            __state = 3;
            java.lang.Object _12 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _13 =  new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.NetworkTypeHandler.ConditionalDistributionSetTypeHandler();
            _13.init(_1, _12, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd", "conditionalDistributionSet", _1.getLevel());
            _13.setAttributes(pAttr);
            _1.addElementParser(_13);
            return true;
          }
          break;
        case 3:
          break;
        default:
          throw new java.lang.IllegalStateException("Invalid state: " + __state);
      }
      return false;
    }
  
    public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
      unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType) result;
      switch (__state) {
        case 1:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "variables".equals(pLocalName)) {
            _1.setVariables(((unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.VariablesType) pResult));
            return;
          }
          break;
        case 2:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "structure".equals(pLocalName)) {
            _1.setStructure(((unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.StructureType) pResult));
            return;
          }
          break;
        case 3:
          if ("http://unbbayes.sourceforge.net/xml/XMLBIF_0_5.xsd".equals(pNamespaceURI)  &&  "conditionalDistributionSet".equals(pLocalName)) {
            _1.setConditionalDistributionSet(((unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType) pResult));
            return;
          }
          break;
        default:
          throw new java.lang.IllegalStateException("Illegal state: " + __state);
      }
    }
  
    public boolean isFinished() {
      switch (__state) {
        case 3:
        case 2:
        case 1:
        case 0:
          return true;
        default:
          return false;
      }
    }
  
  }

  /** Will be set to true, if the first child is parsed.
   * It is an error, if another child is parsed, and the
   * fields value is true.
   * 
   */
  private boolean __state;

  /** Index of the particle being currently parsed
   * 
   */
  private int __childNum;


  public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
    if (pURI == null) {
      pURI = "";
    }
    unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType) result;
    if ("".equals(pURI)) {
      if ("version".equals(pLocalName)) {
        try {
          _1.setVersion(getHandler().getDatatypeConverter().parseDouble((java.lang.String) pValue));
        } catch (java.lang.Exception _2) {
          getHandler().parseConversionEvent("Failed to convert value of @version: " + pValue, _2);
        }
        return;
      }
    }
    super.addAttribute(pURI, pLocalName, pValue);
  }

  public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
    org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
    if (_1.testGroupParser(new unbbayes.io.xmlbif.version5.xmlclasses.impl.XMLBIFTypeHandler.HeaderAndStaticPropertyAndHierarchyHandler(), pNamespaceURI, pLocalName, pQName, pAttr)) {
      if (__state) {
        if (__childNum != 0) {
          getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "Multiple different particles present in a choive group.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
        } else {
          getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The group HeaderAndStaticPropertyAndHierarchy has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
        }
      }
      __state = true;
      __childNum = 0;
      return true;
    }
    return false;
  }

  public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
    unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType _1 = (unbbayes.io.xmlbif.version5.xmlclasses.XMLBIFType) result;
    switch (__childNum) {
      case 0:
        throw new java.lang.IllegalStateException("This case should be handled by a nested group parser.");
      default:
        throw new java.lang.IllegalStateException("Illegal state: " + __childNum);
    }
  }

  public boolean isFinished() {
    return true;
  }

}
