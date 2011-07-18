package unbbayes.io.xmlbif.version4.xmlclasses;

public class ObjectFactory {
  private org.apache.ws.jaxme.impl.JAXBContextImpl jaxbContext;

  private java.util.Map properties;


  public ObjectFactory() throws javax.xml.bind.JAXBException {
    jaxbContext = (org.apache.ws.jaxme.impl.JAXBContextImpl) javax.xml.bind.JAXBContext.newInstance("unbbayes.io.xmlclasses");
  }

  public java.lang.Object newInstance(java.lang.Class pElementInterface) throws javax.xml.bind.JAXBException {
    return jaxbContext.getManager(pElementInterface).getElementJ();
  }

  public java.lang.Object getProperty(java.lang.String pName) {
    if (properties == null) {
      return null;
    }
    return properties.get(pName);
  }

  public void setProperty(java.lang.String pName, java.lang.Object pValue) {
    if (properties == null) {
      properties = new java.util.HashMap();
    }
    properties.put(pName, pValue);
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIF createBIF() throws javax.xml.bind.JAXBException {
    return (unbbayes.io.xmlbif.version4.xmlclasses.BIF) newInstance(unbbayes.io.xmlbif.version4.xmlclasses.BIF.class);
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType createBIFType() throws javax.xml.bind.JAXBException {
    return (unbbayes.io.xmlbif.version4.xmlclasses.BIFType) newInstance(unbbayes.io.xmlbif.version4.xmlclasses.BIFType.class);
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HEADERType createBIFTypeHEADERType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HEADERTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.STATICPROPERTYType createBIFTypeSTATICPROPERTYType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.STATICPROPERTYTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType createBIFTypeHIERARCHYType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HIERARCHYTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType.ROOTType createBIFTypeHIERARCHYTypeROOTType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HIERARCHYTypeImpl.ROOTTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType.ROOTType.LEVELType createBIFTypeHIERARCHYTypeROOTTypeLEVELType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.HIERARCHYTypeImpl.ROOTTypeImpl.LEVELTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType createBIFTypeNETWORKType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType createBIFTypeNETWORKTypeVARIABLESType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType createBIFTypeNETWORKTypeVARIABLESTypeVARType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.STATENAMEType createBIFTypeNETWORKTypeVARIABLESTypeVARTypeSTATENAMEType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.STATENAMETypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType createBIFTypeNETWORKTypeVARIABLESTypeVARTypeMETAPHOREType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.TRIGGERType createBIFTypeNETWORKTypeVARIABLESTypeVARTypeMETAPHORETypeTRIGGERType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.TRIGGERTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.EXCLUDENTType createBIFTypeNETWORKTypeVARIABLESTypeVARTypeMETAPHORETypeEXCLUDENTType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.EXCLUDENTTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.ESSENCIALType createBIFTypeNETWORKTypeVARIABLESTypeVARTypeMETAPHORETypeESSENCIALType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.ESSENCIALTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.COMPLEMENTARYType createBIFTypeNETWORKTypeVARIABLESTypeVARTypeMETAPHORETypeCOMPLEMENTARYType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.COMPLEMENTARYTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.NAType createBIFTypeNETWORKTypeVARIABLESTypeVARTypeMETAPHORETypeNAType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.VARTypeImpl.METAPHORETypeImpl.NATypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.DECISIONType createBIFTypeNETWORKTypeVARIABLESTypeDECISIONType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.DECISIONTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.DECISIONType.STATENAMEType createBIFTypeNETWORKTypeVARIABLESTypeDECISIONTypeSTATENAMEType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.DECISIONTypeImpl.STATENAMETypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.UTILITYType createBIFTypeNETWORKTypeVARIABLESTypeUTILITYType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.UTILITYTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.UTILITYType.STATENAMEType createBIFTypeNETWORKTypeVARIABLESTypeUTILITYTypeSTATENAMEType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.VARIABLESTypeImpl.UTILITYTypeImpl.STATENAMETypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.STRUCTUREType createBIFTypeNETWORKTypeSTRUCTUREType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.STRUCTURETypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.STRUCTUREType.ARCType createBIFTypeNETWORKTypeSTRUCTURETypeARCType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.STRUCTURETypeImpl.ARCTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType createBIFTypeNETWORKTypePOTENTIALType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType createBIFTypeNETWORKTypePOTENTIALTypePOTType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.PRIVATEType createBIFTypeNETWORKTypePOTENTIALTypePOTTypePRIVATEType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.PRIVATETypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.CONDSETType createBIFTypeNETWORKTypePOTENTIALTypePOTTypeCONDSETType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.CONDSETTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.CONDSETType.CONDLEMType createBIFTypeNETWORKTypePOTENTIALTypePOTTypeCONDSETTypeCONDLEMType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.CONDSETTypeImpl.CONDLEMTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.DPISType createBIFTypeNETWORKTypePOTENTIALTypePOTTypeDPISType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.DPISTypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.DPISType.DPIType createBIFTypeNETWORKTypePOTENTIALTypePOTTypeDPISTypeDPIType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version4.xmlclasses.impl.BIFTypeImpl.NETWORKTypeImpl.POTENTIALTypeImpl.POTTypeImpl.DPISTypeImpl.DPITypeImpl();
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.NewDataSet createNewDataSet() throws javax.xml.bind.JAXBException {
    return (unbbayes.io.xmlbif.version4.xmlclasses.NewDataSet) newInstance(unbbayes.io.xmlbif.version4.xmlclasses.NewDataSet.class);
  }

  public unbbayes.io.xmlbif.version4.xmlclasses.NewDataSetType createNewDataSetType() throws javax.xml.bind.JAXBException {
    return (unbbayes.io.xmlbif.version4.xmlclasses.NewDataSetType) newInstance(unbbayes.io.xmlbif.version4.xmlclasses.NewDataSetType.class);
  }

}
