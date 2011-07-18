package unbbayes.io.xmlbif.version4.xmlclasses.impl;

public class NewDataSetTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
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


  public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
    org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
    if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "BIF".equals(pLocalName)) {
      if (__state) {
        if (__childNum != 0) {
          getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "Multiple different particles present in a choive group.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
        } else {
          getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://localhost/xml/model.xsd}BIF has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
        }
      }
      __state = true;
      __childNum = 0;
      org.apache.ws.jaxme.JMManager _2 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(unbbayes.io.xmlbif.version4.xmlclasses.BIF.class);
      java.lang.Object _3 = _2.getElementS();
      org.apache.ws.jaxme.impl.JMSAXElementParser _4 = _2.getHandler();
      _4.init(_1, _3, "http://localhost/xml/model.xsd", "BIF", _1.getLevel());
      _4.setAttributes(pAttr);
      _1.addElementParser(_4);
      return true;
    }
    return false;
  }

  public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
    unbbayes.io.xmlbif.version4.xmlclasses.NewDataSetType _1 = (unbbayes.io.xmlbif.version4.xmlclasses.NewDataSetType) result;
    switch (__childNum) {
      case 0:
        if ("http://localhost/xml/model.xsd".equals(pNamespaceURI)  &&  "BIF".equals(pLocalName)) {
          _1.setBIF(((unbbayes.io.xmlbif.version4.xmlclasses.BIFType) pResult));
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
