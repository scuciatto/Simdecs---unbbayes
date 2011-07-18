package unbbayes.io.xmlbif.version4.xmlclasses.impl;

public class NewDataSetTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
  public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
    org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
    return _1;
  }

  public java.lang.String getPreferredPrefix(java.lang.String pURI) {
    if (pURI == null) {
      pURI = "";
    }
    if (pURI.equals("http://localhost/xml/model.xsd")) {
      return "";
    }
    return null;
  }

  public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
    unbbayes.io.xmlbif.version4.xmlclasses.NewDataSetType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.NewDataSetType) pObject;
    unbbayes.io.xmlbif.version4.xmlclasses.BIFType _2 = _1.getBIF();
    if (_2 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _3 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(unbbayes.io.xmlbif.version4.xmlclasses.BIF.class).getDriver();
      pController.marshal(_3, "http://localhost/xml/model.xsd", "BIF", _1.getBIF());
    }
  }

}
