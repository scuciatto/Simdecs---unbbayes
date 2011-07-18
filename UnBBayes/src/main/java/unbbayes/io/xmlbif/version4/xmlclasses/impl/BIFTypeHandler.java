package unbbayes.io.xmlbif.version4.xmlclasses.impl;

public class BIFTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  public static class HEADERTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
    /** The current state. The following values are valid states:
     *  0 = Before parsing the element
     *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}NAME
     *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}VERSION
     *  3 = While or after parsing the child element {http://localhost/xml/model.xsd}CREATOR
     * 
     */
    private int __state;
  
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      switch (__state) {
        case 0:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NAME".equals(pLocalName)) {
            __state = 1;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "VERSION".equals(pLocalName)) {
            __state = 2;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "CREATOR".equals(pLocalName)) {
            __state = 3;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 1:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "VERSION".equals(pLocalName)) {
            __state = 2;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "CREATOR".equals(pLocalName)) {
            __state = 3;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 2:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "CREATOR".equals(pLocalName)) {
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
      unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HEADERType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HEADERType) result;
      switch (__state) {
        case 1:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NAME".equals(pLocalName)) {
            _1.setNAME((java.lang.String) pResult);
            return;
          }
          break;
        case 2:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "VERSION".equals(pLocalName)) {
            try {
              _1.setVERSION(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
            } catch (java.lang.Exception _2) {
              getHandler().parseConversionEvent("Failed to convert value of {http://localhost/xml/model.xsd}VERSION: " + pResult, _2);
            }
            return;
          }
          break;
        case 3:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "CREATOR".equals(pLocalName)) {
            _1.setCREATOR((java.lang.String) pResult);
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

  public static class STATICPROPERTYTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
    /** The current state. The following values are valid states:
     *  0 = Before parsing the element
     *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}NODESIZE
     *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}NODEFONTNAME
     *  3 = While or after parsing the child element {http://localhost/xml/model.xsd}NODEFONTSIZE
     *  4 = While or after parsing the child element {http://localhost/xml/model.xsd}COLORUTILITY
     *  5 = While or after parsing the child element {http://localhost/xml/model.xsd}COLORDECISION
     *  6 = While or after parsing the child element {http://localhost/xml/model.xsd}COLORPROBDESCRIPTION
     *  7 = While or after parsing the child element {http://localhost/xml/model.xsd}COLORPROBEXPLANATION
     * 
     */
    private int __state;
  
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      switch (__state) {
        case 0:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NODESIZE".equals(pLocalName)) {
            __state = 1;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NODEFONTNAME".equals(pLocalName)) {
            __state = 2;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NODEFONTSIZE".equals(pLocalName)) {
            __state = 3;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORUTILITY".equals(pLocalName)) {
            __state = 4;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORDECISION".equals(pLocalName)) {
            __state = 5;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBDESCRIPTION".equals(pLocalName)) {
            __state = 6;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBEXPLANATION".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 1:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NODEFONTNAME".equals(pLocalName)) {
            __state = 2;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NODEFONTSIZE".equals(pLocalName)) {
            __state = 3;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORUTILITY".equals(pLocalName)) {
            __state = 4;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORDECISION".equals(pLocalName)) {
            __state = 5;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBDESCRIPTION".equals(pLocalName)) {
            __state = 6;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBEXPLANATION".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 2:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NODEFONTSIZE".equals(pLocalName)) {
            __state = 3;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORUTILITY".equals(pLocalName)) {
            __state = 4;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORDECISION".equals(pLocalName)) {
            __state = 5;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBDESCRIPTION".equals(pLocalName)) {
            __state = 6;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBEXPLANATION".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 3:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORUTILITY".equals(pLocalName)) {
            __state = 4;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORDECISION".equals(pLocalName)) {
            __state = 5;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBDESCRIPTION".equals(pLocalName)) {
            __state = 6;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBEXPLANATION".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 4:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORDECISION".equals(pLocalName)) {
            __state = 5;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBDESCRIPTION".equals(pLocalName)) {
            __state = 6;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBEXPLANATION".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 5:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBDESCRIPTION".equals(pLocalName)) {
            __state = 6;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBEXPLANATION".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 6:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBEXPLANATION".equals(pLocalName)) {
            __state = 7;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 7:
          break;
        default:
          throw new java.lang.IllegalStateException("Invalid state: " + __state);
      }
      return false;
    }
  
    public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
      unbbayes.io.xmlbif.version4.xmlclasses.BIFType.STATICPROPERTYType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.STATICPROPERTYType) result;
      switch (__state) {
        case 1:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NODESIZE".equals(pLocalName)) {
            _1.setNODESIZE((java.lang.String) pResult);
            return;
          }
          break;
        case 2:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NODEFONTNAME".equals(pLocalName)) {
            _1.setNODEFONTNAME((java.lang.String) pResult);
            return;
          }
          break;
        case 3:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NODEFONTSIZE".equals(pLocalName)) {
            try {
              _1.setNODEFONTSIZE(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
            } catch (java.lang.Exception _2) {
              getHandler().parseConversionEvent("Failed to convert value of {http://localhost/xml/model.xsd}NODEFONTSIZE: " + pResult, _2);
            }
            return;
          }
          break;
        case 4:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORUTILITY".equals(pLocalName)) {
            try {
              _1.setCOLORUTILITY(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
            } catch (java.lang.Exception _3) {
              getHandler().parseConversionEvent("Failed to convert value of {http://localhost/xml/model.xsd}COLORUTILITY: " + pResult, _3);
            }
            return;
          }
          break;
        case 5:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORDECISION".equals(pLocalName)) {
            try {
              _1.setCOLORDECISION(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
            } catch (java.lang.Exception _4) {
              getHandler().parseConversionEvent("Failed to convert value of {http://localhost/xml/model.xsd}COLORDECISION: " + pResult, _4);
            }
            return;
          }
          break;
        case 6:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBDESCRIPTION".equals(pLocalName)) {
            try {
              _1.setCOLORPROBDESCRIPTION(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
            } catch (java.lang.Exception _5) {
              getHandler().parseConversionEvent("Failed to convert value of {http://localhost/xml/model.xsd}COLORPROBDESCRIPTION: " + pResult, _5);
            }
            return;
          }
          break;
        case 7:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COLORPROBEXPLANATION".equals(pLocalName)) {
            try {
              _1.setCOLORPROBEXPLANATION(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
            } catch (java.lang.Exception _6) {
              getHandler().parseConversionEvent("Failed to convert value of {http://localhost/xml/model.xsd}COLORPROBEXPLANATION: " + pResult, _6);
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

  public static class HIERARCHYTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
    public static class ROOTTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
      public static class LEVELTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
        public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
          if (pURI == null) {
            pURI = "";
          }
          unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType.ROOTType.LEVELType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType.ROOTType.LEVELType) result;
          if ("".equals(pURI)) {
            if ("NAME".equals(pLocalName)) {
              _1.setNAME((java.lang.String) pValue);
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
       *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}LEVEL
       * 
       */
      private int __state;
    
    
      public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
        if (pURI == null) {
          pURI = "";
        }
        unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType.ROOTType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType.ROOTType) result;
        if ("".equals(pURI)) {
          if ("NAME".equals(pLocalName)) {
            _1.setNAME((java.lang.String) pValue);
            return;
          }
        }
        super.addAttribute(pURI, pLocalName, pValue);
      }
    
      public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
        org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
        switch (__state) {
          case 0:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "LEVEL".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HIERARCHYTypeImpl.ROOTTypeImpl.LEVELTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.HIERARCHYTypeHandler.ROOTTypeHandler.LEVELTypeHandler();
              _3.init(_1, _2, "http://localhost/xml/model.xsd", "LEVEL", _1.getLevel());
              _3.setAttributes(pAttr);
              _1.addElementParser(_3);
              return true;
            }
            break;
          case 1:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "LEVEL".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HIERARCHYTypeImpl.ROOTTypeImpl.LEVELTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.HIERARCHYTypeHandler.ROOTTypeHandler.LEVELTypeHandler();
              _5.init(_1, _4, "http://localhost/xml/model.xsd", "LEVEL", _1.getLevel());
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
        unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType.ROOTType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType.ROOTType) result;
        switch (__state) {
          case 1:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "LEVEL".equals(pLocalName)) {
              _1.getLEVEL().add(pResult);
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
     *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}ROOT
     * 
     */
    private int __state;
  
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      switch (__state) {
        case 0:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "ROOT".equals(pLocalName)) {
            __state = 1;
            java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HIERARCHYTypeImpl.ROOTTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.HIERARCHYTypeHandler.ROOTTypeHandler();
            _3.init(_1, _2, "http://localhost/xml/model.xsd", "ROOT", _1.getLevel());
            _3.setAttributes(pAttr);
            _1.addElementParser(_3);
            return true;
          }
          break;
        case 1:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "ROOT".equals(pLocalName)) {
            __state = 1;
            java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HIERARCHYTypeImpl.ROOTTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.HIERARCHYTypeHandler.ROOTTypeHandler();
            _5.init(_1, _4, "http://localhost/xml/model.xsd", "ROOT", _1.getLevel());
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
      unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType) result;
      switch (__state) {
        case 1:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "ROOT".equals(pLocalName)) {
            _1.getROOT().add(pResult);
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

  public static class NETWORKTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
    public static class VARIABLESTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
      public static class VARTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
        public static class STATENAMETypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
          public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
            return false;
          }
        
          public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.STATENAMEType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.STATENAMEType) result;
            _1.setValue((java.lang.String) pResult);
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
      
        public static class METAPHORETypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
          public static class TRIGGERTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
            /** The current state. The following values are valid states:
             *  0 = Before parsing the element
             *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}NAME
             *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}COMMENTS
             * 
             */
            private int __state;
          
          
            public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
              org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
              switch (__state) {
                case 0:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NAME".equals(pLocalName)) {
                    __state = 1;
                    _1.addSimpleAtomicState();
                    return true;
                  }
                  break;
                case 1:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMMENTS".equals(pLocalName)) {
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
              unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.TRIGGERType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.TRIGGERType) result;
              switch (__state) {
                case 1:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NAME".equals(pLocalName)) {
                    _1.setNAME((java.lang.String) pResult);
                    return;
                  }
                  break;
                case 2:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMMENTS".equals(pLocalName)) {
                    _1.setCOMMENTS((java.lang.String) pResult);
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
                  return true;
                default:
                  return false;
              }
            }
          
          }
        
          public static class EXCLUDENTTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
            /** The current state. The following values are valid states:
             *  0 = Before parsing the element
             *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}NAME
             *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}COMMENTS
             * 
             */
            private int __state;
          
          
            public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
              org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
              switch (__state) {
                case 0:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NAME".equals(pLocalName)) {
                    __state = 1;
                    _1.addSimpleAtomicState();
                    return true;
                  }
                  break;
                case 1:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMMENTS".equals(pLocalName)) {
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
              unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.EXCLUDENTType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.EXCLUDENTType) result;
              switch (__state) {
                case 1:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NAME".equals(pLocalName)) {
                    _1.setNAME((java.lang.String) pResult);
                    return;
                  }
                  break;
                case 2:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMMENTS".equals(pLocalName)) {
                    _1.setCOMMENTS((java.lang.String) pResult);
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
                  return true;
                default:
                  return false;
              }
            }
          
          }
        
          public static class ESSENCIALTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
            /** The current state. The following values are valid states:
             *  0 = Before parsing the element
             *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}NAME
             *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}COMMENTS
             * 
             */
            private int __state;
          
          
            public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
              org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
              switch (__state) {
                case 0:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NAME".equals(pLocalName)) {
                    __state = 1;
                    _1.addSimpleAtomicState();
                    return true;
                  }
                  break;
                case 1:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMMENTS".equals(pLocalName)) {
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
              unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.ESSENCIALType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.ESSENCIALType) result;
              switch (__state) {
                case 1:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NAME".equals(pLocalName)) {
                    _1.setNAME((java.lang.String) pResult);
                    return;
                  }
                  break;
                case 2:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMMENTS".equals(pLocalName)) {
                    _1.setCOMMENTS((java.lang.String) pResult);
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
                  return true;
                default:
                  return false;
              }
            }
          
          }
        
          public static class COMPLEMENTARYTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
            /** The current state. The following values are valid states:
             *  0 = Before parsing the element
             *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}NAME
             *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}COMMENTS
             * 
             */
            private int __state;
          
          
            public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
              org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
              switch (__state) {
                case 0:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NAME".equals(pLocalName)) {
                    __state = 1;
                    _1.addSimpleAtomicState();
                    return true;
                  }
                  break;
                case 1:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMMENTS".equals(pLocalName)) {
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
              unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.COMPLEMENTARYType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.COMPLEMENTARYType) result;
              switch (__state) {
                case 1:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NAME".equals(pLocalName)) {
                    _1.setNAME((java.lang.String) pResult);
                    return;
                  }
                  break;
                case 2:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMMENTS".equals(pLocalName)) {
                    _1.setCOMMENTS((java.lang.String) pResult);
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
                  return true;
                default:
                  return false;
              }
            }
          
          }
        
          public static class NATypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
            /** The current state. The following values are valid states:
             *  0 = Before parsing the element
             *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}NAME
             *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}COMMENTS
             * 
             */
            private int __state;
          
          
            public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
              org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
              switch (__state) {
                case 0:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NAME".equals(pLocalName)) {
                    __state = 1;
                    _1.addSimpleAtomicState();
                    return true;
                  }
                  break;
                case 1:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMMENTS".equals(pLocalName)) {
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
              unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.NAType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.NAType) result;
              switch (__state) {
                case 1:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NAME".equals(pLocalName)) {
                    _1.setNAME((java.lang.String) pResult);
                    return;
                  }
                  break;
                case 2:
                  if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMMENTS".equals(pLocalName)) {
                    _1.setCOMMENTS((java.lang.String) pResult);
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
                  return true;
                default:
                  return false;
              }
            }
          
          }
        
          /** The current state. The following values are valid states:
           *  0 = Before parsing the element
           *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}DESCRIPTION
           *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}TRIGGER
           *  3 = While or after parsing the child element {http://localhost/xml/model.xsd}EXCLUDENT
           *  4 = While or after parsing the child element {http://localhost/xml/model.xsd}ESSENCIAL
           *  5 = While or after parsing the child element {http://localhost/xml/model.xsd}COMPLEMENTARY
           *  6 = While or after parsing the child element {http://localhost/xml/model.xsd}NA
           * 
           */
          private int __state;
        
        
          public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
            org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
            switch (__state) {
              case 0:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DESCRIPTION".equals(pLocalName)) {
                  __state = 1;
                  _1.addSimpleAtomicState();
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "TRIGGER".equals(pLocalName)) {
                  __state = 2;
                  java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.TRIGGERTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.TRIGGERTypeHandler();
                  _3.init(_1, _2, "http://localhost/xml/model.xsd", "TRIGGER", _1.getLevel());
                  _3.setAttributes(pAttr);
                  _1.addElementParser(_3);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "EXCLUDENT".equals(pLocalName)) {
                  __state = 3;
                  java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.EXCLUDENTTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.EXCLUDENTTypeHandler();
                  _5.init(_1, _4, "http://localhost/xml/model.xsd", "EXCLUDENT", _1.getLevel());
                  _5.setAttributes(pAttr);
                  _1.addElementParser(_5);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "ESSENCIAL".equals(pLocalName)) {
                  __state = 4;
                  java.lang.Object _6 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.ESSENCIALTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _7 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.ESSENCIALTypeHandler();
                  _7.init(_1, _6, "http://localhost/xml/model.xsd", "ESSENCIAL", _1.getLevel());
                  _7.setAttributes(pAttr);
                  _1.addElementParser(_7);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMPLEMENTARY".equals(pLocalName)) {
                  __state = 5;
                  java.lang.Object _8 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.COMPLEMENTARYTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _9 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.COMPLEMENTARYTypeHandler();
                  _9.init(_1, _8, "http://localhost/xml/model.xsd", "COMPLEMENTARY", _1.getLevel());
                  _9.setAttributes(pAttr);
                  _1.addElementParser(_9);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NA".equals(pLocalName)) {
                  __state = 6;
                  java.lang.Object _10 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.NATypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _11 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.NATypeHandler();
                  _11.init(_1, _10, "http://localhost/xml/model.xsd", "NA", _1.getLevel());
                  _11.setAttributes(pAttr);
                  _1.addElementParser(_11);
                  return true;
                }
                break;
              case 1:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DESCRIPTION".equals(pLocalName)) {
                  __state = 1;
                  _1.addSimpleAtomicState();
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "TRIGGER".equals(pLocalName)) {
                  __state = 2;
                  java.lang.Object _12 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.TRIGGERTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _13 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.TRIGGERTypeHandler();
                  _13.init(_1, _12, "http://localhost/xml/model.xsd", "TRIGGER", _1.getLevel());
                  _13.setAttributes(pAttr);
                  _1.addElementParser(_13);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "EXCLUDENT".equals(pLocalName)) {
                  __state = 3;
                  java.lang.Object _14 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.EXCLUDENTTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _15 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.EXCLUDENTTypeHandler();
                  _15.init(_1, _14, "http://localhost/xml/model.xsd", "EXCLUDENT", _1.getLevel());
                  _15.setAttributes(pAttr);
                  _1.addElementParser(_15);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "ESSENCIAL".equals(pLocalName)) {
                  __state = 4;
                  java.lang.Object _16 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.ESSENCIALTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _17 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.ESSENCIALTypeHandler();
                  _17.init(_1, _16, "http://localhost/xml/model.xsd", "ESSENCIAL", _1.getLevel());
                  _17.setAttributes(pAttr);
                  _1.addElementParser(_17);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMPLEMENTARY".equals(pLocalName)) {
                  __state = 5;
                  java.lang.Object _18 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.COMPLEMENTARYTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _19 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.COMPLEMENTARYTypeHandler();
                  _19.init(_1, _18, "http://localhost/xml/model.xsd", "COMPLEMENTARY", _1.getLevel());
                  _19.setAttributes(pAttr);
                  _1.addElementParser(_19);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NA".equals(pLocalName)) {
                  __state = 6;
                  java.lang.Object _20 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.NATypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _21 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.NATypeHandler();
                  _21.init(_1, _20, "http://localhost/xml/model.xsd", "NA", _1.getLevel());
                  _21.setAttributes(pAttr);
                  _1.addElementParser(_21);
                  return true;
                }
                break;
              case 2:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "TRIGGER".equals(pLocalName)) {
                  __state = 2;
                  java.lang.Object _22 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.TRIGGERTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _23 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.TRIGGERTypeHandler();
                  _23.init(_1, _22, "http://localhost/xml/model.xsd", "TRIGGER", _1.getLevel());
                  _23.setAttributes(pAttr);
                  _1.addElementParser(_23);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "EXCLUDENT".equals(pLocalName)) {
                  __state = 3;
                  java.lang.Object _24 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.EXCLUDENTTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _25 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.EXCLUDENTTypeHandler();
                  _25.init(_1, _24, "http://localhost/xml/model.xsd", "EXCLUDENT", _1.getLevel());
                  _25.setAttributes(pAttr);
                  _1.addElementParser(_25);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "ESSENCIAL".equals(pLocalName)) {
                  __state = 4;
                  java.lang.Object _26 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.ESSENCIALTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _27 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.ESSENCIALTypeHandler();
                  _27.init(_1, _26, "http://localhost/xml/model.xsd", "ESSENCIAL", _1.getLevel());
                  _27.setAttributes(pAttr);
                  _1.addElementParser(_27);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMPLEMENTARY".equals(pLocalName)) {
                  __state = 5;
                  java.lang.Object _28 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.COMPLEMENTARYTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _29 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.COMPLEMENTARYTypeHandler();
                  _29.init(_1, _28, "http://localhost/xml/model.xsd", "COMPLEMENTARY", _1.getLevel());
                  _29.setAttributes(pAttr);
                  _1.addElementParser(_29);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NA".equals(pLocalName)) {
                  __state = 6;
                  java.lang.Object _30 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.NATypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _31 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.NATypeHandler();
                  _31.init(_1, _30, "http://localhost/xml/model.xsd", "NA", _1.getLevel());
                  _31.setAttributes(pAttr);
                  _1.addElementParser(_31);
                  return true;
                }
                break;
              case 3:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "EXCLUDENT".equals(pLocalName)) {
                  __state = 3;
                  java.lang.Object _32 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.EXCLUDENTTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _33 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.EXCLUDENTTypeHandler();
                  _33.init(_1, _32, "http://localhost/xml/model.xsd", "EXCLUDENT", _1.getLevel());
                  _33.setAttributes(pAttr);
                  _1.addElementParser(_33);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "ESSENCIAL".equals(pLocalName)) {
                  __state = 4;
                  java.lang.Object _34 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.ESSENCIALTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _35 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.ESSENCIALTypeHandler();
                  _35.init(_1, _34, "http://localhost/xml/model.xsd", "ESSENCIAL", _1.getLevel());
                  _35.setAttributes(pAttr);
                  _1.addElementParser(_35);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMPLEMENTARY".equals(pLocalName)) {
                  __state = 5;
                  java.lang.Object _36 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.COMPLEMENTARYTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _37 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.COMPLEMENTARYTypeHandler();
                  _37.init(_1, _36, "http://localhost/xml/model.xsd", "COMPLEMENTARY", _1.getLevel());
                  _37.setAttributes(pAttr);
                  _1.addElementParser(_37);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NA".equals(pLocalName)) {
                  __state = 6;
                  java.lang.Object _38 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.NATypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _39 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.NATypeHandler();
                  _39.init(_1, _38, "http://localhost/xml/model.xsd", "NA", _1.getLevel());
                  _39.setAttributes(pAttr);
                  _1.addElementParser(_39);
                  return true;
                }
                break;
              case 4:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "ESSENCIAL".equals(pLocalName)) {
                  __state = 4;
                  java.lang.Object _40 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.ESSENCIALTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _41 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.ESSENCIALTypeHandler();
                  _41.init(_1, _40, "http://localhost/xml/model.xsd", "ESSENCIAL", _1.getLevel());
                  _41.setAttributes(pAttr);
                  _1.addElementParser(_41);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMPLEMENTARY".equals(pLocalName)) {
                  __state = 5;
                  java.lang.Object _42 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.COMPLEMENTARYTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _43 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.COMPLEMENTARYTypeHandler();
                  _43.init(_1, _42, "http://localhost/xml/model.xsd", "COMPLEMENTARY", _1.getLevel());
                  _43.setAttributes(pAttr);
                  _1.addElementParser(_43);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NA".equals(pLocalName)) {
                  __state = 6;
                  java.lang.Object _44 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.NATypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _45 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.NATypeHandler();
                  _45.init(_1, _44, "http://localhost/xml/model.xsd", "NA", _1.getLevel());
                  _45.setAttributes(pAttr);
                  _1.addElementParser(_45);
                  return true;
                }
                break;
              case 5:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMPLEMENTARY".equals(pLocalName)) {
                  __state = 5;
                  java.lang.Object _46 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.COMPLEMENTARYTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _47 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.COMPLEMENTARYTypeHandler();
                  _47.init(_1, _46, "http://localhost/xml/model.xsd", "COMPLEMENTARY", _1.getLevel());
                  _47.setAttributes(pAttr);
                  _1.addElementParser(_47);
                  return true;
                } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NA".equals(pLocalName)) {
                  __state = 6;
                  java.lang.Object _48 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.NATypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _49 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.NATypeHandler();
                  _49.init(_1, _48, "http://localhost/xml/model.xsd", "NA", _1.getLevel());
                  _49.setAttributes(pAttr);
                  _1.addElementParser(_49);
                  return true;
                }
                break;
              case 6:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NA".equals(pLocalName)) {
                  __state = 6;
                  java.lang.Object _50 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.NATypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _51 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler.NATypeHandler();
                  _51.init(_1, _50, "http://localhost/xml/model.xsd", "NA", _1.getLevel());
                  _51.setAttributes(pAttr);
                  _1.addElementParser(_51);
                  return true;
                }
                break;
              default:
                throw new java.lang.IllegalStateException("Invalid state: " + __state);
            }
            return false;
          }
        
          public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType) result;
            switch (__state) {
              case 1:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DESCRIPTION".equals(pLocalName)) {
                  _1.getDESCRIPTION().add((java.lang.String) pResult);
                  return;
                }
                break;
              case 2:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "TRIGGER".equals(pLocalName)) {
                  _1.getTRIGGER().add(pResult);
                  return;
                }
                break;
              case 3:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "EXCLUDENT".equals(pLocalName)) {
                  _1.getEXCLUDENT().add(pResult);
                  return;
                }
                break;
              case 4:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "ESSENCIAL".equals(pLocalName)) {
                  _1.getESSENCIAL().add(pResult);
                  return;
                }
                break;
              case 5:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "COMPLEMENTARY".equals(pLocalName)) {
                  _1.getCOMPLEMENTARY().add(pResult);
                  return;
                }
                break;
              case 6:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NA".equals(pLocalName)) {
                  _1.getNA().add(pResult);
                  return;
                }
                break;
              default:
                throw new java.lang.IllegalStateException("Illegal state: " + __state);
            }
          }
        
          public boolean isFinished() {
            switch (__state) {
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
      
        /** The current state. The following values are valid states:
         *  0 = Before parsing the element
         *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}LABEL
         *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}MMIDIA
         *  3 = While or after parsing the child element {http://localhost/xml/model.xsd}STATENAME
         *  4 = While or after parsing the child element {http://localhost/xml/model.xsd}METAPHORE
         * 
         */
        private int __state;
      
      
        public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
          if (pURI == null) {
            pURI = "";
          }
          unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType) result;
          if ("".equals(pURI)) {
            if ("NAME".equals(pLocalName)) {
              _1.setNAME((java.lang.String) pValue);
              return;
            } else if ("TYPE".equals(pLocalName)) {
              _1.setTYPE((java.lang.String) pValue);
              return;
            } else if ("XPOS".equals(pLocalName)) {
              try {
                _1.setXPOS(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
              } catch (java.lang.Exception _2) {
                getHandler().parseConversionEvent("Failed to convert value of @XPOS: " + pValue, _2);
              }
              return;
            } else if ("YPOS".equals(pLocalName)) {
              try {
                _1.setYPOS(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
              } catch (java.lang.Exception _3) {
                getHandler().parseConversionEvent("Failed to convert value of @YPOS: " + pValue, _3);
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
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "LABEL".equals(pLocalName)) {
                __state = 1;
                _1.addSimpleAtomicState();
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "MMIDIA".equals(pLocalName)) {
                __state = 2;
                _1.addSimpleAtomicState();
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.STATENAMETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.STATENAMETypeHandler();
                _3.init(_1, _2, "http://localhost/xml/model.xsd", "STATENAME", _1.getLevel());
                _3.setAttributes(pAttr);
                _1.addElementParser(_3);
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "METAPHORE".equals(pLocalName)) {
                __state = 4;
                java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler();
                _5.init(_1, _4, "http://localhost/xml/model.xsd", "METAPHORE", _1.getLevel());
                _5.setAttributes(pAttr);
                _1.addElementParser(_5);
                return true;
              }
              break;
            case 1:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "MMIDIA".equals(pLocalName)) {
                __state = 2;
                _1.addSimpleAtomicState();
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _6 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.STATENAMETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _7 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.STATENAMETypeHandler();
                _7.init(_1, _6, "http://localhost/xml/model.xsd", "STATENAME", _1.getLevel());
                _7.setAttributes(pAttr);
                _1.addElementParser(_7);
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "METAPHORE".equals(pLocalName)) {
                __state = 4;
                java.lang.Object _8 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _9 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler();
                _9.init(_1, _8, "http://localhost/xml/model.xsd", "METAPHORE", _1.getLevel());
                _9.setAttributes(pAttr);
                _1.addElementParser(_9);
                return true;
              }
              break;
            case 2:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _10 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.STATENAMETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _11 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.STATENAMETypeHandler();
                _11.init(_1, _10, "http://localhost/xml/model.xsd", "STATENAME", _1.getLevel());
                _11.setAttributes(pAttr);
                _1.addElementParser(_11);
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "METAPHORE".equals(pLocalName)) {
                __state = 4;
                java.lang.Object _12 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _13 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler();
                _13.init(_1, _12, "http://localhost/xml/model.xsd", "METAPHORE", _1.getLevel());
                _13.setAttributes(pAttr);
                _1.addElementParser(_13);
                return true;
              }
              break;
            case 3:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _14 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.STATENAMETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _15 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.STATENAMETypeHandler();
                _15.init(_1, _14, "http://localhost/xml/model.xsd", "STATENAME", _1.getLevel());
                _15.setAttributes(pAttr);
                _1.addElementParser(_15);
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "METAPHORE".equals(pLocalName)) {
                __state = 4;
                java.lang.Object _16 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _17 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler();
                _17.init(_1, _16, "http://localhost/xml/model.xsd", "METAPHORE", _1.getLevel());
                _17.setAttributes(pAttr);
                _1.addElementParser(_17);
                return true;
              }
              break;
            case 4:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "METAPHORE".equals(pLocalName)) {
                __state = 4;
                java.lang.Object _18 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _19 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler.METAPHORETypeHandler();
                _19.init(_1, _18, "http://localhost/xml/model.xsd", "METAPHORE", _1.getLevel());
                _19.setAttributes(pAttr);
                _1.addElementParser(_19);
                return true;
              }
              break;
            default:
              throw new java.lang.IllegalStateException("Invalid state: " + __state);
          }
          return false;
        }
      
        public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
          unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType) result;
          switch (__state) {
            case 1:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "LABEL".equals(pLocalName)) {
                _1.setLABEL((java.lang.String) pResult);
                return;
              }
              break;
            case 2:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "MMIDIA".equals(pLocalName)) {
                try {
                  _1.setMMIDIA(getHandler().getDatatypeConverter().parseBase64Binary((java.lang.String) pResult));
                } catch (java.lang.Exception _2) {
                  getHandler().parseConversionEvent("Failed to convert value of {http://localhost/xml/model.xsd}MMIDIA: " + pResult, _2);
                }
                return;
              }
              break;
            case 3:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                _1.getSTATENAME().add(pResult);
                return;
              }
              break;
            case 4:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "METAPHORE".equals(pLocalName)) {
                _1.getMETAPHORE().add(pResult);
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
    
      public static class DECISIONTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
        public static class STATENAMETypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
          public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
            return false;
          }
        
          public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.DECISIONType.STATENAMEType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.DECISIONType.STATENAMEType) result;
            _1.setValue((java.lang.String) pResult);
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
         *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}LABEL
         *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}MMIDIA
         *  3 = While or after parsing the child element {http://localhost/xml/model.xsd}STATENAME
         * 
         */
        private int __state;
      
      
        public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
          if (pURI == null) {
            pURI = "";
          }
          unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.DECISIONType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.DECISIONType) result;
          if ("".equals(pURI)) {
            if ("NAME".equals(pLocalName)) {
              _1.setNAME((java.lang.String) pValue);
              return;
            } else if ("TYPE".equals(pLocalName)) {
              _1.setTYPE((java.lang.String) pValue);
              return;
            } else if ("XPOS".equals(pLocalName)) {
              try {
                _1.setXPOS(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
              } catch (java.lang.Exception _2) {
                getHandler().parseConversionEvent("Failed to convert value of @XPOS: " + pValue, _2);
              }
              return;
            } else if ("YPOS".equals(pLocalName)) {
              try {
                _1.setYPOS(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
              } catch (java.lang.Exception _3) {
                getHandler().parseConversionEvent("Failed to convert value of @YPOS: " + pValue, _3);
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
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "LABEL".equals(pLocalName)) {
                __state = 1;
                _1.addSimpleAtomicState();
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "MMIDIA".equals(pLocalName)) {
                __state = 2;
                _1.addSimpleAtomicState();
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.DECISIONTypeImpl.STATENAMETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.DECISIONTypeHandler.STATENAMETypeHandler();
                _3.init(_1, _2, "http://localhost/xml/model.xsd", "STATENAME", _1.getLevel());
                _3.setAttributes(pAttr);
                _1.addElementParser(_3);
                return true;
              }
              break;
            case 1:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "MMIDIA".equals(pLocalName)) {
                __state = 2;
                _1.addSimpleAtomicState();
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.DECISIONTypeImpl.STATENAMETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.DECISIONTypeHandler.STATENAMETypeHandler();
                _5.init(_1, _4, "http://localhost/xml/model.xsd", "STATENAME", _1.getLevel());
                _5.setAttributes(pAttr);
                _1.addElementParser(_5);
                return true;
              }
              break;
            case 2:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _6 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.DECISIONTypeImpl.STATENAMETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _7 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.DECISIONTypeHandler.STATENAMETypeHandler();
                _7.init(_1, _6, "http://localhost/xml/model.xsd", "STATENAME", _1.getLevel());
                _7.setAttributes(pAttr);
                _1.addElementParser(_7);
                return true;
              }
              break;
            case 3:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _8 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.DECISIONTypeImpl.STATENAMETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _9 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.DECISIONTypeHandler.STATENAMETypeHandler();
                _9.init(_1, _8, "http://localhost/xml/model.xsd", "STATENAME", _1.getLevel());
                _9.setAttributes(pAttr);
                _1.addElementParser(_9);
                return true;
              }
              break;
            default:
              throw new java.lang.IllegalStateException("Invalid state: " + __state);
          }
          return false;
        }
      
        public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
          unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.DECISIONType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.DECISIONType) result;
          switch (__state) {
            case 1:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "LABEL".equals(pLocalName)) {
                _1.setLABEL((java.lang.String) pResult);
                return;
              }
              break;
            case 2:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "MMIDIA".equals(pLocalName)) {
                try {
                  _1.setMMIDIA(getHandler().getDatatypeConverter().parseBase64Binary((java.lang.String) pResult));
                } catch (java.lang.Exception _2) {
                  getHandler().parseConversionEvent("Failed to convert value of {http://localhost/xml/model.xsd}MMIDIA: " + pResult, _2);
                }
                return;
              }
              break;
            case 3:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                _1.getSTATENAME().add(pResult);
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
    
      public static class UTILITYTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
        public static class STATENAMETypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
          public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
            return false;
          }
        
          public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.UTILITYType.STATENAMEType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.UTILITYType.STATENAMEType) result;
            _1.setValue((java.lang.String) pResult);
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
         *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}LABEL
         *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}MMIDIA
         *  3 = While or after parsing the child element {http://localhost/xml/model.xsd}STATENAME
         * 
         */
        private int __state;
      
      
        public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
          if (pURI == null) {
            pURI = "";
          }
          unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.UTILITYType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.UTILITYType) result;
          if ("".equals(pURI)) {
            if ("NAME".equals(pLocalName)) {
              _1.setNAME((java.lang.String) pValue);
              return;
            } else if ("TYPE".equals(pLocalName)) {
              _1.setTYPE((java.lang.String) pValue);
              return;
            } else if ("XPOS".equals(pLocalName)) {
              try {
                _1.setXPOS(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
              } catch (java.lang.Exception _2) {
                getHandler().parseConversionEvent("Failed to convert value of @XPOS: " + pValue, _2);
              }
              return;
            } else if ("YPOS".equals(pLocalName)) {
              try {
                _1.setYPOS(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
              } catch (java.lang.Exception _3) {
                getHandler().parseConversionEvent("Failed to convert value of @YPOS: " + pValue, _3);
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
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "LABEL".equals(pLocalName)) {
                __state = 1;
                _1.addSimpleAtomicState();
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "MMIDIA".equals(pLocalName)) {
                __state = 2;
                _1.addSimpleAtomicState();
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.UTILITYTypeImpl.STATENAMETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.UTILITYTypeHandler.STATENAMETypeHandler();
                _3.init(_1, _2, "http://localhost/xml/model.xsd", "STATENAME", _1.getLevel());
                _3.setAttributes(pAttr);
                _1.addElementParser(_3);
                return true;
              }
              break;
            case 1:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "MMIDIA".equals(pLocalName)) {
                __state = 2;
                _1.addSimpleAtomicState();
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.UTILITYTypeImpl.STATENAMETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.UTILITYTypeHandler.STATENAMETypeHandler();
                _5.init(_1, _4, "http://localhost/xml/model.xsd", "STATENAME", _1.getLevel());
                _5.setAttributes(pAttr);
                _1.addElementParser(_5);
                return true;
              }
              break;
            case 2:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _6 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.UTILITYTypeImpl.STATENAMETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _7 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.UTILITYTypeHandler.STATENAMETypeHandler();
                _7.init(_1, _6, "http://localhost/xml/model.xsd", "STATENAME", _1.getLevel());
                _7.setAttributes(pAttr);
                _1.addElementParser(_7);
                return true;
              }
              break;
            case 3:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _8 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.UTILITYTypeImpl.STATENAMETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _9 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.UTILITYTypeHandler.STATENAMETypeHandler();
                _9.init(_1, _8, "http://localhost/xml/model.xsd", "STATENAME", _1.getLevel());
                _9.setAttributes(pAttr);
                _1.addElementParser(_9);
                return true;
              }
              break;
            default:
              throw new java.lang.IllegalStateException("Invalid state: " + __state);
          }
          return false;
        }
      
        public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
          unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.UTILITYType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.UTILITYType) result;
          switch (__state) {
            case 1:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "LABEL".equals(pLocalName)) {
                _1.setLABEL((java.lang.String) pResult);
                return;
              }
              break;
            case 2:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "MMIDIA".equals(pLocalName)) {
                try {
                  _1.setMMIDIA(getHandler().getDatatypeConverter().parseBase64Binary((java.lang.String) pResult));
                } catch (java.lang.Exception _2) {
                  getHandler().parseConversionEvent("Failed to convert value of {http://localhost/xml/model.xsd}MMIDIA: " + pResult, _2);
                }
                return;
              }
              break;
            case 3:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATENAME".equals(pLocalName)) {
                _1.getSTATENAME().add(pResult);
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
    
      /** The current state. The following values are valid states:
       *  0 = Before parsing the element
       *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}VAR
       *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}DECISION
       *  3 = While or after parsing the child element {http://localhost/xml/model.xsd}UTILITY
       * 
       */
      private int __state;
    
    
      public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
        org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
        switch (__state) {
          case 0:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "VAR".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler();
              _3.init(_1, _2, "http://localhost/xml/model.xsd", "VAR", _1.getLevel());
              _3.setAttributes(pAttr);
              _1.addElementParser(_3);
              return true;
            } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DECISION".equals(pLocalName)) {
              __state = 2;
              java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.DECISIONTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.DECISIONTypeHandler();
              _5.init(_1, _4, "http://localhost/xml/model.xsd", "DECISION", _1.getLevel());
              _5.setAttributes(pAttr);
              _1.addElementParser(_5);
              return true;
            } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "UTILITY".equals(pLocalName)) {
              __state = 3;
              java.lang.Object _6 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.UTILITYTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _7 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.UTILITYTypeHandler();
              _7.init(_1, _6, "http://localhost/xml/model.xsd", "UTILITY", _1.getLevel());
              _7.setAttributes(pAttr);
              _1.addElementParser(_7);
              return true;
            }
            break;
          case 1:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "VAR".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _8 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _9 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.VARTypeHandler();
              _9.init(_1, _8, "http://localhost/xml/model.xsd", "VAR", _1.getLevel());
              _9.setAttributes(pAttr);
              _1.addElementParser(_9);
              return true;
            } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DECISION".equals(pLocalName)) {
              __state = 2;
              java.lang.Object _10 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.DECISIONTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _11 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.DECISIONTypeHandler();
              _11.init(_1, _10, "http://localhost/xml/model.xsd", "DECISION", _1.getLevel());
              _11.setAttributes(pAttr);
              _1.addElementParser(_11);
              return true;
            } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "UTILITY".equals(pLocalName)) {
              __state = 3;
              java.lang.Object _12 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.UTILITYTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _13 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.UTILITYTypeHandler();
              _13.init(_1, _12, "http://localhost/xml/model.xsd", "UTILITY", _1.getLevel());
              _13.setAttributes(pAttr);
              _1.addElementParser(_13);
              return true;
            }
            break;
          case 2:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DECISION".equals(pLocalName)) {
              __state = 2;
              java.lang.Object _14 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.DECISIONTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _15 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.DECISIONTypeHandler();
              _15.init(_1, _14, "http://localhost/xml/model.xsd", "DECISION", _1.getLevel());
              _15.setAttributes(pAttr);
              _1.addElementParser(_15);
              return true;
            } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "UTILITY".equals(pLocalName)) {
              __state = 3;
              java.lang.Object _16 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.UTILITYTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _17 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.UTILITYTypeHandler();
              _17.init(_1, _16, "http://localhost/xml/model.xsd", "UTILITY", _1.getLevel());
              _17.setAttributes(pAttr);
              _1.addElementParser(_17);
              return true;
            }
            break;
          case 3:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "UTILITY".equals(pLocalName)) {
              __state = 3;
              java.lang.Object _18 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.UTILITYTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _19 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler.UTILITYTypeHandler();
              _19.init(_1, _18, "http://localhost/xml/model.xsd", "UTILITY", _1.getLevel());
              _19.setAttributes(pAttr);
              _1.addElementParser(_19);
              return true;
            }
            break;
          default:
            throw new java.lang.IllegalStateException("Invalid state: " + __state);
        }
        return false;
      }
    
      public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
        unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType) result;
        switch (__state) {
          case 1:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "VAR".equals(pLocalName)) {
              _1.getVAR().add(pResult);
              return;
            }
            break;
          case 2:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DECISION".equals(pLocalName)) {
              _1.getDECISION().add(pResult);
              return;
            }
            break;
          case 3:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "UTILITY".equals(pLocalName)) {
              _1.getUTILITY().add(pResult);
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
  
    public static class STRUCTURETypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
      public static class ARCTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
        public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
          if (pURI == null) {
            pURI = "";
          }
          unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.STRUCTUREType.ARCType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.STRUCTUREType.ARCType) result;
          if ("".equals(pURI)) {
            if ("PARENT".equals(pLocalName)) {
              _1.setPARENT((java.lang.String) pValue);
              return;
            } else if ("CHILD".equals(pLocalName)) {
              _1.setCHILD((java.lang.String) pValue);
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
       *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}ARC
       * 
       */
      private int __state;
    
    
      public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
        org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
        switch (__state) {
          case 0:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "ARC".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.STRUCTURETypeImpl.ARCTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.STRUCTURETypeHandler.ARCTypeHandler();
              _3.init(_1, _2, "http://localhost/xml/model.xsd", "ARC", _1.getLevel());
              _3.setAttributes(pAttr);
              _1.addElementParser(_3);
              return true;
            }
            break;
          case 1:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "ARC".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.STRUCTURETypeImpl.ARCTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.STRUCTURETypeHandler.ARCTypeHandler();
              _5.init(_1, _4, "http://localhost/xml/model.xsd", "ARC", _1.getLevel());
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
        unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.STRUCTUREType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.STRUCTUREType) result;
        switch (__state) {
          case 1:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "ARC".equals(pLocalName)) {
              _1.getARC().add(pResult);
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
  
    public static class POTENTIALTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
      public static class POTTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
        public static class PRIVATETypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
          public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
            if (pURI == null) {
              pURI = "";
            }
            unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.PRIVATEType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.PRIVATEType) result;
            if ("".equals(pURI)) {
              if ("NAME".equals(pLocalName)) {
                _1.setNAME((java.lang.String) pValue);
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
      
        public static class CONDSETTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
          public static class CONDLEMTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
            public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
              if (pURI == null) {
                pURI = "";
              }
              unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.CONDSETType.CONDLEMType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.CONDSETType.CONDLEMType) result;
              if ("".equals(pURI)) {
                if ("NAME".equals(pLocalName)) {
                  _1.setNAME((java.lang.String) pValue);
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
           *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}CONDLEM
           * 
           */
          private int __state;
        
        
          public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
            org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
            switch (__state) {
              case 0:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "CONDLEM".equals(pLocalName)) {
                  __state = 1;
                  java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.CONDSETTypeImpl.CONDLEMTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler.CONDSETTypeHandler.CONDLEMTypeHandler();
                  _3.init(_1, _2, "http://localhost/xml/model.xsd", "CONDLEM", _1.getLevel());
                  _3.setAttributes(pAttr);
                  _1.addElementParser(_3);
                  return true;
                }
                break;
              case 1:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "CONDLEM".equals(pLocalName)) {
                  __state = 1;
                  java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.CONDSETTypeImpl.CONDLEMTypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler.CONDSETTypeHandler.CONDLEMTypeHandler();
                  _5.init(_1, _4, "http://localhost/xml/model.xsd", "CONDLEM", _1.getLevel());
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
            unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.CONDSETType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.CONDSETType) result;
            switch (__state) {
              case 1:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "CONDLEM".equals(pLocalName)) {
                  _1.getCONDLEM().add(pResult);
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
      
        public static class DPISTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
          public static class DPITypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
            public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
              if (pURI == null) {
                pURI = "";
              }
              unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.DPISType.DPIType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.DPISType.DPIType) result;
              if ("".equals(pURI)) {
                if ("INDEXES".equals(pLocalName)) {
                  try {
                    _1.setINDEXES(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
                  } catch (java.lang.Exception _2) {
                    getHandler().parseConversionEvent("Failed to convert value of @INDEXES: " + pValue, _2);
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
              unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.DPISType.DPIType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.DPISType.DPIType) result;
              _1.setValue((java.lang.String) pResult);
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
           *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}DPI
           * 
           */
          private int __state;
        
        
          public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
            org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
            switch (__state) {
              case 0:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DPI".equals(pLocalName)) {
                  __state = 1;
                  java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.DPISTypeImpl.DPITypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler.DPISTypeHandler.DPITypeHandler();
                  _3.init(_1, _2, "http://localhost/xml/model.xsd", "DPI", _1.getLevel());
                  _3.setAttributes(pAttr);
                  _1.addElementParser(_3);
                  return true;
                }
                break;
              case 1:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DPI".equals(pLocalName)) {
                  __state = 1;
                  java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.DPISTypeImpl.DPITypeImpl();
                  org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler.DPISTypeHandler.DPITypeHandler();
                  _5.init(_1, _4, "http://localhost/xml/model.xsd", "DPI", _1.getLevel());
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
            unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.DPISType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.DPISType) result;
            switch (__state) {
              case 1:
                if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DPI".equals(pLocalName)) {
                  _1.getDPI().add(pResult);
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
         *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}PRIVATE
         *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}CONDSET
         *  3 = While or after parsing the child element {http://localhost/xml/model.xsd}DPIS
         * 
         */
        private int __state;
      
      
        public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
          if (pURI == null) {
            pURI = "";
          }
          unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType) result;
          if ("".equals(pURI)) {
            if ("TYPE".equals(pLocalName)) {
              _1.setTYPE((java.lang.String) pValue);
              return;
            }
          }
          super.addAttribute(pURI, pLocalName, pValue);
        }
      
        public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
          org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
          switch (__state) {
            case 0:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "PRIVATE".equals(pLocalName)) {
                __state = 1;
                java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.PRIVATETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler.PRIVATETypeHandler();
                _3.init(_1, _2, "http://localhost/xml/model.xsd", "PRIVATE", _1.getLevel());
                _3.setAttributes(pAttr);
                _1.addElementParser(_3);
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "CONDSET".equals(pLocalName)) {
                __state = 2;
                java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.CONDSETTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler.CONDSETTypeHandler();
                _5.init(_1, _4, "http://localhost/xml/model.xsd", "CONDSET", _1.getLevel());
                _5.setAttributes(pAttr);
                _1.addElementParser(_5);
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DPIS".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _6 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.DPISTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _7 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler.DPISTypeHandler();
                _7.init(_1, _6, "http://localhost/xml/model.xsd", "DPIS", _1.getLevel());
                _7.setAttributes(pAttr);
                _1.addElementParser(_7);
                return true;
              }
              break;
            case 1:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "PRIVATE".equals(pLocalName)) {
                __state = 1;
                java.lang.Object _8 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.PRIVATETypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _9 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler.PRIVATETypeHandler();
                _9.init(_1, _8, "http://localhost/xml/model.xsd", "PRIVATE", _1.getLevel());
                _9.setAttributes(pAttr);
                _1.addElementParser(_9);
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "CONDSET".equals(pLocalName)) {
                __state = 2;
                java.lang.Object _10 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.CONDSETTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _11 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler.CONDSETTypeHandler();
                _11.init(_1, _10, "http://localhost/xml/model.xsd", "CONDSET", _1.getLevel());
                _11.setAttributes(pAttr);
                _1.addElementParser(_11);
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DPIS".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _12 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.DPISTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _13 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler.DPISTypeHandler();
                _13.init(_1, _12, "http://localhost/xml/model.xsd", "DPIS", _1.getLevel());
                _13.setAttributes(pAttr);
                _1.addElementParser(_13);
                return true;
              }
              break;
            case 2:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "CONDSET".equals(pLocalName)) {
                __state = 2;
                java.lang.Object _14 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.CONDSETTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _15 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler.CONDSETTypeHandler();
                _15.init(_1, _14, "http://localhost/xml/model.xsd", "CONDSET", _1.getLevel());
                _15.setAttributes(pAttr);
                _1.addElementParser(_15);
                return true;
              } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DPIS".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _16 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.DPISTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _17 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler.DPISTypeHandler();
                _17.init(_1, _16, "http://localhost/xml/model.xsd", "DPIS", _1.getLevel());
                _17.setAttributes(pAttr);
                _1.addElementParser(_17);
                return true;
              }
              break;
            case 3:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DPIS".equals(pLocalName)) {
                __state = 3;
                java.lang.Object _18 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.DPISTypeImpl();
                org.apache.ws.jaxme.impl.JMSAXElementParser _19 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler.DPISTypeHandler();
                _19.init(_1, _18, "http://localhost/xml/model.xsd", "DPIS", _1.getLevel());
                _19.setAttributes(pAttr);
                _1.addElementParser(_19);
                return true;
              }
              break;
            default:
              throw new java.lang.IllegalStateException("Invalid state: " + __state);
          }
          return false;
        }
      
        public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
          unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType) result;
          switch (__state) {
            case 1:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "PRIVATE".equals(pLocalName)) {
                _1.getPRIVATE().add(pResult);
                return;
              }
              break;
            case 2:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "CONDSET".equals(pLocalName)) {
                _1.getCONDSET().add(pResult);
                return;
              }
              break;
            case 3:
              if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "DPIS".equals(pLocalName)) {
                _1.getDPIS().add(pResult);
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
    
      /** The current state. The following values are valid states:
       *  0 = Before parsing the element
       *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}POT
       * 
       */
      private int __state;
    
    
      public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
        org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
        switch (__state) {
          case 0:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "POT".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler();
              _3.init(_1, _2, "http://localhost/xml/model.xsd", "POT", _1.getLevel());
              _3.setAttributes(pAttr);
              _1.addElementParser(_3);
              return true;
            }
            break;
          case 1:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "POT".equals(pLocalName)) {
              __state = 1;
              java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl();
              org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler.POTTypeHandler();
              _5.init(_1, _4, "http://localhost/xml/model.xsd", "POT", _1.getLevel());
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
        unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType) result;
        switch (__state) {
          case 1:
            if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "POT".equals(pLocalName)) {
              _1.getPOT().add(pResult);
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
     *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}VARIABLES
     *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}STRUCTURE
     *  3 = While or after parsing the child element {http://localhost/xml/model.xsd}POTENTIAL
     * 
     */
    private int __state;
  
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      switch (__state) {
        case 0:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "VARIABLES".equals(pLocalName)) {
            __state = 1;
            java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler();
            _3.init(_1, _2, "http://localhost/xml/model.xsd", "VARIABLES", _1.getLevel());
            _3.setAttributes(pAttr);
            _1.addElementParser(_3);
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STRUCTURE".equals(pLocalName)) {
            __state = 2;
            java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.STRUCTURETypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.STRUCTURETypeHandler();
            _5.init(_1, _4, "http://localhost/xml/model.xsd", "STRUCTURE", _1.getLevel());
            _5.setAttributes(pAttr);
            _1.addElementParser(_5);
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "POTENTIAL".equals(pLocalName)) {
            __state = 3;
            java.lang.Object _6 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _7 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler();
            _7.init(_1, _6, "http://localhost/xml/model.xsd", "POTENTIAL", _1.getLevel());
            _7.setAttributes(pAttr);
            _1.addElementParser(_7);
            return true;
          }
          break;
        case 1:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "VARIABLES".equals(pLocalName)) {
            __state = 1;
            java.lang.Object _8 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _9 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.VARIABLESTypeHandler();
            _9.init(_1, _8, "http://localhost/xml/model.xsd", "VARIABLES", _1.getLevel());
            _9.setAttributes(pAttr);
            _1.addElementParser(_9);
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STRUCTURE".equals(pLocalName)) {
            __state = 2;
            java.lang.Object _10 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.STRUCTURETypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _11 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.STRUCTURETypeHandler();
            _11.init(_1, _10, "http://localhost/xml/model.xsd", "STRUCTURE", _1.getLevel());
            _11.setAttributes(pAttr);
            _1.addElementParser(_11);
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "POTENTIAL".equals(pLocalName)) {
            __state = 3;
            java.lang.Object _12 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _13 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler();
            _13.init(_1, _12, "http://localhost/xml/model.xsd", "POTENTIAL", _1.getLevel());
            _13.setAttributes(pAttr);
            _1.addElementParser(_13);
            return true;
          }
          break;
        case 2:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STRUCTURE".equals(pLocalName)) {
            __state = 2;
            java.lang.Object _14 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.STRUCTURETypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _15 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.STRUCTURETypeHandler();
            _15.init(_1, _14, "http://localhost/xml/model.xsd", "STRUCTURE", _1.getLevel());
            _15.setAttributes(pAttr);
            _1.addElementParser(_15);
            return true;
          } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "POTENTIAL".equals(pLocalName)) {
            __state = 3;
            java.lang.Object _16 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _17 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler();
            _17.init(_1, _16, "http://localhost/xml/model.xsd", "POTENTIAL", _1.getLevel());
            _17.setAttributes(pAttr);
            _1.addElementParser(_17);
            return true;
          }
          break;
        case 3:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "POTENTIAL".equals(pLocalName)) {
            __state = 3;
            java.lang.Object _18 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl();
            org.apache.ws.jaxme.impl.JMSAXElementParser _19 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler.POTENTIALTypeHandler();
            _19.init(_1, _18, "http://localhost/xml/model.xsd", "POTENTIAL", _1.getLevel());
            _19.setAttributes(pAttr);
            _1.addElementParser(_19);
            return true;
          }
          break;
        default:
          throw new java.lang.IllegalStateException("Invalid state: " + __state);
      }
      return false;
    }
  
    public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
      unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType) result;
      switch (__state) {
        case 1:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "VARIABLES".equals(pLocalName)) {
            _1.getVARIABLES().add(pResult);
            return;
          }
          break;
        case 2:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STRUCTURE".equals(pLocalName)) {
            _1.getSTRUCTURE().add(pResult);
            return;
          }
          break;
        case 3:
          if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "POTENTIAL".equals(pLocalName)) {
            _1.getPOTENTIAL().add(pResult);
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

  /** The current state. The following values are valid states:
   *  0 = Before parsing the element
   *  1 = While or after parsing the child element {http://localhost/xml/model.xsd}HEADER
   *  2 = While or after parsing the child element {http://localhost/xml/model.xsd}STATICPROPERTY
   *  3 = While or after parsing the child element {http://localhost/xml/model.xsd}HIERARCHY
   *  4 = While or after parsing the child element {http://localhost/xml/model.xsd}NETWORK
   * 
   */
  private int __state;


  public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
    if (pURI == null) {
      pURI = "";
    }
    unbbayes.io.xmlbif.version4.xmlclasses.BIFType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType) result;
    if ("".equals(pURI)) {
      if ("VERSION".equals(pLocalName)) {
        try {
          _1.setVERSION(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
        } catch (java.lang.Exception _2) {
          getHandler().parseConversionEvent("Failed to convert value of @VERSION: " + pValue, _2);
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
        if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "HEADER".equals(pLocalName)) {
          __state = 1;
          java.lang.Object _2 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HEADERTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.HEADERTypeHandler();
          _3.init(_1, _2, "http://localhost/xml/model.xsd", "HEADER", _1.getLevel());
          _3.setAttributes(pAttr);
          _1.addElementParser(_3);
          return true;
        } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATICPROPERTY".equals(pLocalName)) {
          __state = 2;
          java.lang.Object _4 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.STATICPROPERTYTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.STATICPROPERTYTypeHandler();
          _5.init(_1, _4, "http://localhost/xml/model.xsd", "STATICPROPERTY", _1.getLevel());
          _5.setAttributes(pAttr);
          _1.addElementParser(_5);
          return true;
        } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "HIERARCHY".equals(pLocalName)) {
          __state = 3;
          java.lang.Object _6 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HIERARCHYTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _7 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.HIERARCHYTypeHandler();
          _7.init(_1, _6, "http://localhost/xml/model.xsd", "HIERARCHY", _1.getLevel());
          _7.setAttributes(pAttr);
          _1.addElementParser(_7);
          return true;
        } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NETWORK".equals(pLocalName)) {
          __state = 4;
          java.lang.Object _8 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _9 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler();
          _9.init(_1, _8, "http://localhost/xml/model.xsd", "NETWORK", _1.getLevel());
          _9.setAttributes(pAttr);
          _1.addElementParser(_9);
          return true;
        }
        break;
      case 1:
        if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "HEADER".equals(pLocalName)) {
          __state = 1;
          java.lang.Object _10 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HEADERTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _11 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.HEADERTypeHandler();
          _11.init(_1, _10, "http://localhost/xml/model.xsd", "HEADER", _1.getLevel());
          _11.setAttributes(pAttr);
          _1.addElementParser(_11);
          return true;
        } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATICPROPERTY".equals(pLocalName)) {
          __state = 2;
          java.lang.Object _12 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.STATICPROPERTYTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _13 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.STATICPROPERTYTypeHandler();
          _13.init(_1, _12, "http://localhost/xml/model.xsd", "STATICPROPERTY", _1.getLevel());
          _13.setAttributes(pAttr);
          _1.addElementParser(_13);
          return true;
        } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "HIERARCHY".equals(pLocalName)) {
          __state = 3;
          java.lang.Object _14 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HIERARCHYTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _15 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.HIERARCHYTypeHandler();
          _15.init(_1, _14, "http://localhost/xml/model.xsd", "HIERARCHY", _1.getLevel());
          _15.setAttributes(pAttr);
          _1.addElementParser(_15);
          return true;
        } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NETWORK".equals(pLocalName)) {
          __state = 4;
          java.lang.Object _16 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _17 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler();
          _17.init(_1, _16, "http://localhost/xml/model.xsd", "NETWORK", _1.getLevel());
          _17.setAttributes(pAttr);
          _1.addElementParser(_17);
          return true;
        }
        break;
      case 2:
        if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATICPROPERTY".equals(pLocalName)) {
          __state = 2;
          java.lang.Object _18 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.STATICPROPERTYTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _19 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.STATICPROPERTYTypeHandler();
          _19.init(_1, _18, "http://localhost/xml/model.xsd", "STATICPROPERTY", _1.getLevel());
          _19.setAttributes(pAttr);
          _1.addElementParser(_19);
          return true;
        } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "HIERARCHY".equals(pLocalName)) {
          __state = 3;
          java.lang.Object _20 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HIERARCHYTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _21 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.HIERARCHYTypeHandler();
          _21.init(_1, _20, "http://localhost/xml/model.xsd", "HIERARCHY", _1.getLevel());
          _21.setAttributes(pAttr);
          _1.addElementParser(_21);
          return true;
        } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NETWORK".equals(pLocalName)) {
          __state = 4;
          java.lang.Object _22 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _23 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler();
          _23.init(_1, _22, "http://localhost/xml/model.xsd", "NETWORK", _1.getLevel());
          _23.setAttributes(pAttr);
          _1.addElementParser(_23);
          return true;
        }
        break;
      case 3:
        if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "HIERARCHY".equals(pLocalName)) {
          __state = 3;
          java.lang.Object _24 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HIERARCHYTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _25 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.HIERARCHYTypeHandler();
          _25.init(_1, _24, "http://localhost/xml/model.xsd", "HIERARCHY", _1.getLevel());
          _25.setAttributes(pAttr);
          _1.addElementParser(_25);
          return true;
        } else if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NETWORK".equals(pLocalName)) {
          __state = 4;
          java.lang.Object _26 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _27 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler();
          _27.init(_1, _26, "http://localhost/xml/model.xsd", "NETWORK", _1.getLevel());
          _27.setAttributes(pAttr);
          _1.addElementParser(_27);
          return true;
        }
        break;
      case 4:
        if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NETWORK".equals(pLocalName)) {
          __state = 4;
          java.lang.Object _28 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _29 =  new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeHandler.NETWORKTypeHandler();
          _29.init(_1, _28, "http://localhost/xml/model.xsd", "NETWORK", _1.getLevel());
          _29.setAttributes(pAttr);
          _1.addElementParser(_29);
          return true;
        }
        break;
      default:
        throw new java.lang.IllegalStateException("Invalid state: " + __state);
    }
    return false;
  }

  public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
    unbbayes.io.xmlbif.version4.xmlclasses.BIFType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.BIFType) result;
    switch (__state) {
      case 1:
        if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "HEADER".equals(pLocalName)) {
          _1.getHEADER().add(pResult);
          return;
        }
        break;
      case 2:
        if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "STATICPROPERTY".equals(pLocalName)) {
          _1.getSTATICPROPERTY().add(pResult);
          return;
        }
        break;
      case 3:
        if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "HIERARCHY".equals(pLocalName)) {
          _1.getHIERARCHY().add(pResult);
          return;
        }
        break;
      case 4:
        if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "NETWORK".equals(pLocalName)) {
          _1.getNETWORK().add(pResult);
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
