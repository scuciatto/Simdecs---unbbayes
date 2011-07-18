package unbbayes.io.xmlbif.version6.xmlclasses.impl;

public class XMLBIFTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
  public static class HeaderTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
    public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
      org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
      return _1;
    }
  
    public java.lang.String getPreferredPrefix(java.lang.String pURI) {
      if (pURI == null) {
        pURI = "";
      }
      if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
        return "xbifns";
      }
      return null;
    }
  
    public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
      unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HeaderType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HeaderType) pObject;
      pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "version", pController.getDatatypeConverter().printDouble(_1.getVersion()));
      java.lang.String _2 = _1.getName();
      if (_2 != null) {
        pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "name", _1.getName());
      }
      java.lang.String _3 = _1.getCreator();
      if (_3 != null) {
        pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "creator", _1.getCreator());
      }
    }
  
  }

  public static class StaticPropertyTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
    public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
      org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
      return _1;
    }
  
    public java.lang.String getPreferredPrefix(java.lang.String pURI) {
      if (pURI == null) {
        pURI = "";
      }
      if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
        return "xbifns";
      }
      return null;
    }
  
    public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
      unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.StaticPropertyType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.StaticPropertyType) pObject;
      pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "nodeSize", pController.getDatatypeConverter().printInt(_1.getNodeSize()));
      java.lang.String _2 = _1.getNodeFontName();
      if (_2 != null) {
        pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "nodeFontName", _1.getNodeFontName());
      }
      pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "nodeFontSize", pController.getDatatypeConverter().printInt(_1.getNodeFontSize()));
      pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "colorUtilityNode", pController.getDatatypeConverter().printInt(_1.getColorUtilityNode()));
      pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "colorDecisionNode", pController.getDatatypeConverter().printInt(_1.getColorDecisionNode()));
      pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "colorDiscreteProbabilisticNode", pController.getDatatypeConverter().printInt(_1.getColorDiscreteProbabilisticNode()));
      pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "colorContinuousProbilisticNode", pController.getDatatypeConverter().printInt(_1.getColorContinuousProbilisticNode()));
      pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "colorExplanationNode", pController.getDatatypeConverter().printInt(_1.getColorExplanationNode()));
    }
  
  }

  public static class HierarchyTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
    public static class RootTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
      public static class LevelTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
        public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
          org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
          unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType.RootType.LevelType _2 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType.RootType.LevelType) pObject;
          java.lang.String _3 = _2.getName();
          if (_3 != null) {
            _1.addAttribute("", "name", pController.getAttrQName(this, "", "name"), "CDATA", _2.getName());
          }
          return _1;
        }
      
        public java.lang.String getPreferredPrefix(java.lang.String pURI) {
          return null;
        }
      
        public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
        }
      
      }
    
      public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
        org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
        unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType.RootType _2 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType.RootType) pObject;
        java.lang.String _3 = _2.getName();
        if (_3 != null) {
          _1.addAttribute("", "name", pController.getAttrQName(this, "", "name"), "CDATA", _2.getName());
        }
        return _1;
      }
    
      public java.lang.String getPreferredPrefix(java.lang.String pURI) {
        if (pURI == null) {
          pURI = "";
        }
        if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
          return "xbifns";
        }
        return null;
      }
    
      public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
        unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType.RootType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType.RootType) pObject;
        java.util.List _2 = _1.getLevel();
        for (int _3 = 0;  _3 < (_2).size();  _3++) {
          org.apache.ws.jaxme.impl.JMSAXDriver _4 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.HierarchyTypeDriver.RootTypeDriver.LevelTypeDriver();
          pController.marshal(_4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "level", (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType.RootType.LevelType)_2.get(_3));
        }
      }
    
    }
  
    public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
      org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
      return _1;
    }
  
    public java.lang.String getPreferredPrefix(java.lang.String pURI) {
      if (pURI == null) {
        pURI = "";
      }
      if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
        return "xbifns";
      }
      return null;
    }
  
    public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
      unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType) pObject;
      java.util.List _2 = _1.getRoot();
      for (int _3 = 0;  _3 < (_2).size();  _3++) {
        org.apache.ws.jaxme.impl.JMSAXDriver _4 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.HierarchyTypeDriver.RootTypeDriver();
        pController.marshal(_4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "root", (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType.RootType)_2.get(_3));
      }
    }
  
  }

  public static class NetworkTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
    public static class VariablesTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
      public static class VariableTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
        public static class StateTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
          public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
            org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
            unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.StateType _2 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.StateType) pObject;
            java.lang.String _3 = _2.getName();
            if (_3 != null) {
              _1.addAttribute("", "name", pController.getAttrQName(this, "", "name"), "CDATA", _2.getName());
            }
            return _1;
          }
        
          public java.lang.String getPreferredPrefix(java.lang.String pURI) {
            if (pURI == null) {
              pURI = "";
            }
            if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
              return "xbifns";
            }
            return null;
          }
        
          public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.StateType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.StateType) pObject;
            java.lang.String _2 = _1.getDescription();
            if (_2 != null) {
              pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "description", _1.getDescription());
            }
            byte[] _3 = _1.getMmedia();
            if (_3 != null) {
              pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "mmedia", pController.getDatatypeConverter().printBase64Binary(_1.getMmedia()));
            }
          }
        
        }
      
        public static class MetaphoreTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
          public static class ExplanationTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
            public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
              org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
              unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType _2 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType) pObject;
              java.lang.String _3 = _2.getEvidence();
              if (_3 != null) {
                _1.addAttribute("", "evidence", pController.getAttrQName(this, "", "evidence"), "CDATA", _2.getEvidence());
              }
              java.lang.String _4 = _2.getEvidenceType();
              if (_4 != null) {
                _1.addAttribute("", "evidenceType", pController.getAttrQName(this, "", "evidenceType"), "CDATA", _2.getEvidenceType());
              }
              return _1;
            }
          
            public java.lang.String getPreferredPrefix(java.lang.String pURI) {
              if (pURI == null) {
                pURI = "";
              }
              if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
                return "xbifns";
              }
              return null;
            }
          
            public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
              unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType) pObject;
              java.lang.String _2 = _1.getComments();
              if (_2 != null) {
                pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "comments", _1.getComments());
              }
            }
          
          }
        
          public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
            org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
            return _1;
          }
        
          public java.lang.String getPreferredPrefix(java.lang.String pURI) {
            if (pURI == null) {
              pURI = "";
            }
            if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
              return "xbifns";
            }
            return null;
          }
        
          public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType) pObject;
            java.lang.String _2 = _1.getDescription();
            if (_2 != null) {
              pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "description", _1.getDescription());
            }
            java.util.List _3 = _1.getExplanation();
            for (int _4 = 0;  _4 < (_3).size();  _4++) {
              org.apache.ws.jaxme.impl.JMSAXDriver _5 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.VariablesTypeDriver.VariableTypeDriver.MetaphoreTypeDriver.ExplanationTypeDriver();
              pController.marshal(_5, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "explanation", (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType)_3.get(_4));
            }
          }
        
        }
      
        public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
          org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
          unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType _2 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType) pObject;
          java.lang.String _3 = _2.getName();
          if (_3 != null) {
            _1.addAttribute("", "name", pController.getAttrQName(this, "", "name"), "CDATA", _2.getName());
          }
          java.lang.String _4 = _2.getType();
          if (_4 != null) {
            _1.addAttribute("", "type", pController.getAttrQName(this, "", "type"), "CDATA", _2.getType());
          }
          _1.addAttribute("", "xPos", pController.getAttrQName(this, "", "xPos"), "CDATA", pController.getDatatypeConverter().printInt(_2.getXPos()));
          _1.addAttribute("", "yPos", pController.getAttrQName(this, "", "yPos"), "CDATA", pController.getDatatypeConverter().printInt(_2.getYPos()));
          _1.addAttribute("", "width", pController.getAttrQName(this, "", "width"), "CDATA", pController.getDatatypeConverter().printFloat(_2.getWidth()));
          _1.addAttribute("", "height", pController.getAttrQName(this, "", "height"), "CDATA", pController.getDatatypeConverter().printFloat(_2.getHeight()));
          _1.addAttribute("", "rgbColor", pController.getAttrQName(this, "", "rgbColor"), "CDATA", pController.getDatatypeConverter().printInt(_2.getRgbColor()));
          _1.addAttribute("", "custoEtapa", pController.getAttrQName(this, "", "custoEtapa"), "CDATA", pController.getDatatypeConverter().printFloat(_2.getValorEtapa()));
          _1.addAttribute("", "tempoEtapa", pController.getAttrQName(this, "", "tempoEtapa"), "CDATA", pController.getDatatypeConverter().printFloat(_2.getTempoEtapa()));
          _1.addAttribute("", "bogus", pController.getAttrQName(this, "", "bogus"), "CDATA", pController.getDatatypeConverter().printBoolean(_2.isBogus()));
          _1.addAttribute("", "perguntas", pController.getAttrQName(this, "", "perguntas"), "CDATA", pController.getDatatypeConverter().printString(_2.getPerguntas()));
          return _1;
        }
      
        public java.lang.String getPreferredPrefix(java.lang.String pURI) {
          if (pURI == null) {
            pURI = "";
          }
          if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
            return "xbifns";
          }
          return null;
        }
      
        public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
          unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType) pObject;
          java.lang.String _2 = _1.getDescription();
          if (_2 != null) {
            pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "description", _1.getDescription());
          }
          byte[] _3 = _1.getMmedia();
          if (_3 != null) {
            pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "mmedia", pController.getDatatypeConverter().printBase64Binary(_1.getMmedia()));
          }
          java.util.List _4 = _1.getState();
          for (int _5 = 0;  _5 < (_4).size();  _5++) {
            org.apache.ws.jaxme.impl.JMSAXDriver _6 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.VariablesTypeDriver.VariableTypeDriver.StateTypeDriver();
            pController.marshal(_6, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "state", (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.StateType)_4.get(_5));
          }
          unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType _7 = _1.getMetaphore();
          if (_7 != null) {
            org.apache.ws.jaxme.impl.JMSAXDriver _8 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.VariablesTypeDriver.VariableTypeDriver.MetaphoreTypeDriver();
            pController.marshal(_8, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "metaphore", _1.getMetaphore());
          }
        }
      
      }
    
      public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
        org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
        return _1;
      }
    
      public java.lang.String getPreferredPrefix(java.lang.String pURI) {
        if (pURI == null) {
          pURI = "";
        }
        if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
          return "xbifns";
        }
        return null;
      }
    
      public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
        unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType) pObject;
        java.util.List _2 = _1.getVariable();
        for (int _3 = 0;  _3 < (_2).size();  _3++) {
          org.apache.ws.jaxme.impl.JMSAXDriver _4 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.VariablesTypeDriver.VariableTypeDriver();
          pController.marshal(_4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "variable", (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType)_2.get(_3));
        }
      }
    
    }
  
    public static class StructureTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
      public static class EdgeTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
        public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
          org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
          unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType.EdgeType _2 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType.EdgeType) pObject;
          java.lang.String _3 = _2.getParent();
          if (_3 != null) {
            _1.addAttribute("", "parent", pController.getAttrQName(this, "", "parent"), "CDATA", _2.getParent());
          }
          java.lang.String _4 = _2.getChild();
          if (_4 != null) {
            _1.addAttribute("", "child", pController.getAttrQName(this, "", "child"), "CDATA", _2.getChild());
          }
          return _1;
        }
      
        public java.lang.String getPreferredPrefix(java.lang.String pURI) {
          return null;
        }
      
        public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
        }
      
      }
    
      public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
        org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
        return _1;
      }
    
      public java.lang.String getPreferredPrefix(java.lang.String pURI) {
        if (pURI == null) {
          pURI = "";
        }
        if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
          return "xbifns";
        }
        return null;
      }
    
      public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
        unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType) pObject;
        java.util.List _2 = _1.getEdge();
        for (int _3 = 0;  _3 < (_2).size();  _3++) {
          org.apache.ws.jaxme.impl.JMSAXDriver _4 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.StructureTypeDriver.EdgeTypeDriver();
          pController.marshal(_4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "edge", (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType.EdgeType)_2.get(_3));
        }
      }
    
    }
  
    public static class ConditionalDistributionSetTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
      public static class ConditionalDistributionTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
        public static class OwnerTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
          public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
            org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
            unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType _2 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType) pObject;
            java.lang.String _3 = _2.getName();
            if (_3 != null) {
              _1.addAttribute("", "name", pController.getAttrQName(this, "", "name"), "CDATA", _2.getName());
            }
            return _1;
          }
        
          public java.lang.String getPreferredPrefix(java.lang.String pURI) {
            return null;
          }
        
          public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
          }
        
        }
      
        public static class ParentsTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
          public static class ParentTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
            public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
              org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
              unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType.ParentType _2 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType.ParentType) pObject;
              java.lang.String _3 = _2.getName();
              if (_3 != null) {
                _1.addAttribute("", "name", pController.getAttrQName(this, "", "name"), "CDATA", _2.getName());
              }
              _1.addAttribute("", "index", pController.getAttrQName(this, "", "index"), "CDATA", pController.getDatatypeConverter().printInt(_2.getIndex()));
              return _1;
            }
          
            public java.lang.String getPreferredPrefix(java.lang.String pURI) {
              return null;
            }
          
            public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
            }
          
          }
        
          public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
            org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
            return _1;
          }
        
          public java.lang.String getPreferredPrefix(java.lang.String pURI) {
            if (pURI == null) {
              pURI = "";
            }
            if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
              return "xbifns";
            }
            return null;
          }
        
          public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType) pObject;
            java.util.List _2 = _1.getParent();
            for (int _3 = 0;  _3 < (_2).size();  _3++) {
              org.apache.ws.jaxme.impl.JMSAXDriver _4 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.ConditionalDistributionSetTypeDriver.ConditionalDistributionTypeDriver.ParentsTypeDriver.ParentTypeDriver();
              pController.marshal(_4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "parent", (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType.ParentType)_2.get(_3));
            }
          }
        
        }
      
        public static class CPTTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
          public static class DependentParentIndexTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
            public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
              org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
              unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType _2 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType) pObject;
              _1.addAttribute("", "index", pController.getAttrQName(this, "", "index"), "CDATA", pController.getDatatypeConverter().printInt(_2.getIndex()));
              return _1;
            }
          
            public java.lang.String getPreferredPrefix(java.lang.String pURI) {
              return null;
            }
          
            public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
              unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType) pObject;
              java.lang.String _2 = pController.getDatatypeConverter().printDouble(_1.getValue());
              if (_2 != null  &&  _2.length() > 0) {
                char[] _3 = _2.toCharArray();
                pHandler.characters(_3, 0, _3.length);
              }
            }
          
          }
        
          public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
            org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
            return _1;
          }
        
          public java.lang.String getPreferredPrefix(java.lang.String pURI) {
            if (pURI == null) {
              pURI = "";
            }
            if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
              return "xbifns";
            }
            return null;
          }
        
          public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType) pObject;
            java.util.List _2 = _1.getDependentParentIndex();
            for (int _3 = 0;  _3 < (_2).size();  _3++) {
              org.apache.ws.jaxme.impl.JMSAXDriver _4 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.ConditionalDistributionSetTypeDriver.ConditionalDistributionTypeDriver.CPTTypeDriver.DependentParentIndexTypeDriver();
              pController.marshal(_4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "dependentParentIndex", (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType)_2.get(_3));
            }
          }
        
        }
      
        public static class NormalTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
          public static class FunctionTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
            public static class ConstantsTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
              public static class ConstantTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
                public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
                  org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
                  unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType _2 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType) pObject;
                  _1.addAttribute("", "index", pController.getAttrQName(this, "", "index"), "CDATA", pController.getDatatypeConverter().printInt(_2.getIndex()));
                  return _1;
                }
              
                public java.lang.String getPreferredPrefix(java.lang.String pURI) {
                  return null;
                }
              
                public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
                  unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType) pObject;
                  java.lang.String _2 = pController.getDatatypeConverter().printDouble(_1.getValue());
                  if (_2 != null  &&  _2.length() > 0) {
                    char[] _3 = _2.toCharArray();
                    pHandler.characters(_3, 0, _3.length);
                  }
                }
              
              }
            
              public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
                org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
                return _1;
              }
            
              public java.lang.String getPreferredPrefix(java.lang.String pURI) {
                if (pURI == null) {
                  pURI = "";
                }
                if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
                  return "xbifns";
                }
                return null;
              }
            
              public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
                unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType) pObject;
                java.util.List _2 = _1.getConstant();
                for (int _3 = 0;  _3 < (_2).size();  _3++) {
                  org.apache.ws.jaxme.impl.JMSAXDriver _4 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.ConditionalDistributionSetTypeDriver.ConditionalDistributionTypeDriver.NormalTypeDriver.FunctionTypeDriver.ConstantsTypeDriver.ConstantTypeDriver();
                  pController.marshal(_4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "constant", (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType)_2.get(_3));
                }
              }
            
            }
          
            public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
              org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
              unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType _2 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType) pObject;
              _1.addAttribute("", "index", pController.getAttrQName(this, "", "index"), "CDATA", pController.getDatatypeConverter().printInt(_2.getIndex()));
              return _1;
            }
          
            public java.lang.String getPreferredPrefix(java.lang.String pURI) {
              if (pURI == null) {
                pURI = "";
              }
              if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
                return "xbifns";
              }
              return null;
            }
          
            public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
              unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType) pObject;
              pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "mean", pController.getDatatypeConverter().printDouble(_1.getMean()));
              pController.marshalSimpleChild(this, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "variance", pController.getDatatypeConverter().printDouble(_1.getVariance()));
              unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType _2 = _1.getConstants();
              if (_2 != null) {
                org.apache.ws.jaxme.impl.JMSAXDriver _3 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.ConditionalDistributionSetTypeDriver.ConditionalDistributionTypeDriver.NormalTypeDriver.FunctionTypeDriver.ConstantsTypeDriver();
                pController.marshal(_3, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "constants", _1.getConstants());
              }
            }
          
          }
        
          public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
            org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
            return _1;
          }
        
          public java.lang.String getPreferredPrefix(java.lang.String pURI) {
            if (pURI == null) {
              pURI = "";
            }
            if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
              return "xbifns";
            }
            return null;
          }
        
          public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
            unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType) pObject;
            java.util.List _2 = _1.getFunction();
            for (int _3 = 0;  _3 < (_2).size();  _3++) {
              org.apache.ws.jaxme.impl.JMSAXDriver _4 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.ConditionalDistributionSetTypeDriver.ConditionalDistributionTypeDriver.NormalTypeDriver.FunctionTypeDriver();
              pController.marshal(_4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "function", (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType)_2.get(_3));
            }
          }
        
        }
      
        public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
          org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
          unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType _2 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType) pObject;
          java.lang.String _3 = _2.getType();
          if (_3 != null) {
            _1.addAttribute("", "type", pController.getAttrQName(this, "", "type"), "CDATA", _2.getType());
          }
          return _1;
        }
      
        public java.lang.String getPreferredPrefix(java.lang.String pURI) {
          if (pURI == null) {
            pURI = "";
          }
          if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
            return "xbifns";
          }
          return null;
        }
      
        public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
          unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType) pObject;
          unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType _2 = _1.getOwner();
          if (_2 != null) {
            org.apache.ws.jaxme.impl.JMSAXDriver _3 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.ConditionalDistributionSetTypeDriver.ConditionalDistributionTypeDriver.OwnerTypeDriver();
            pController.marshal(_3, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "owner", _1.getOwner());
          }
          unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType _4 = _1.getParents();
          if (_4 != null) {
            org.apache.ws.jaxme.impl.JMSAXDriver _5 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.ConditionalDistributionSetTypeDriver.ConditionalDistributionTypeDriver.ParentsTypeDriver();
            pController.marshal(_5, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "parents", _1.getParents());
          }
          unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType _6 = _1.getCPT();
          if (_6 != null) {
            org.apache.ws.jaxme.impl.JMSAXDriver _7 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.ConditionalDistributionSetTypeDriver.ConditionalDistributionTypeDriver.CPTTypeDriver();
            pController.marshal(_7, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "CPT", _1.getCPT());
          }
          unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType _8 = _1.getNormal();
          if (_8 != null) {
            org.apache.ws.jaxme.impl.JMSAXDriver _9 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.ConditionalDistributionSetTypeDriver.ConditionalDistributionTypeDriver.NormalTypeDriver();
            pController.marshal(_9, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "normal", _1.getNormal());
          }
        }
      
      }
    
      public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
        org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
        return _1;
      }
    
      public java.lang.String getPreferredPrefix(java.lang.String pURI) {
        if (pURI == null) {
          pURI = "";
        }
        if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
          return "xbifns";
        }
        return null;
      }
    
      public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
        unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType) pObject;
        java.util.List _2 = _1.getConditionalDistribution();
        for (int _3 = 0;  _3 < (_2).size();  _3++) {
          org.apache.ws.jaxme.impl.JMSAXDriver _4 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.ConditionalDistributionSetTypeDriver.ConditionalDistributionTypeDriver();
          pController.marshal(_4, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "conditionalDistribution", (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType)_2.get(_3));
        }
      }
    
    }
  
    public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
      org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
      return _1;
    }
  
    public java.lang.String getPreferredPrefix(java.lang.String pURI) {
      if (pURI == null) {
        pURI = "";
      }
      if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
        return "xbifns";
      }
      return null;
    }
  
    public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
      unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType) pObject;
      unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType _2 = _1.getVariables();
      if (_2 != null) {
        org.apache.ws.jaxme.impl.JMSAXDriver _3 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.VariablesTypeDriver();
        pController.marshal(_3, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "variables", _1.getVariables());
      }
      unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType _4 = _1.getStructure();
      if (_4 != null) {
        org.apache.ws.jaxme.impl.JMSAXDriver _5 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.StructureTypeDriver();
        pController.marshal(_5, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "structure", _1.getStructure());
      }
      unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType _6 = _1.getConditionalDistributionSet();
      if (_6 != null) {
        org.apache.ws.jaxme.impl.JMSAXDriver _7 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver.ConditionalDistributionSetTypeDriver();
        pController.marshal(_7, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "conditionalDistributionSet", _1.getConditionalDistributionSet());
      }
    }
  
  }

  public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
    org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
    unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType _2 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType) pObject;
    _1.addAttribute("", "version", pController.getAttrQName(this, "", "version"), "CDATA", pController.getDatatypeConverter().printDouble(_2.getVersion()));
    return _1;
  }

  public java.lang.String getPreferredPrefix(java.lang.String pURI) {
    if (pURI == null) {
      pURI = "";
    }
    if (pURI.equals("http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd")) {
      return "xbifns";
    }
    return null;
  }

  public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
    unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType _1 = (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType) pObject;
    unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HeaderType _2 = _1.getHeader();
    if (_2 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _3 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.HeaderTypeDriver();
      pController.marshal(_3, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "header", _1.getHeader());
    }
    unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.StaticPropertyType _4 = _1.getStaticProperty();
    if (_4 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _5 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.StaticPropertyTypeDriver();
      pController.marshal(_5, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "staticProperty", _1.getStaticProperty());
    }
    unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType _6 = _1.getHierarchy();
    if (_6 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _7 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.HierarchyTypeDriver();
      pController.marshal(_7, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "hierarchy", _1.getHierarchy());
    }
    unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType _8 = _1.getNetwork();
    if (_8 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _9 = new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeDriver.NetworkTypeDriver();
      pController.marshal(_9, "http://unbbayes.sourceforge.net/xml/XMLBIF_0_6.xsd", "network", _1.getNetwork());
    }
  }

}
