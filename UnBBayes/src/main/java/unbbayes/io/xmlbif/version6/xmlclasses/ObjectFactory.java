package unbbayes.io.xmlbif.version6.xmlclasses;

public class ObjectFactory {
  private org.apache.ws.jaxme.impl.JAXBContextImpl jaxbContext;

  private java.util.Map properties;


  public ObjectFactory() throws javax.xml.bind.JAXBException {
    jaxbContext = (org.apache.ws.jaxme.impl.JAXBContextImpl) javax.xml.bind.JAXBContext.newInstance("unbbayes.io.xmlbif.version6.xmlclasses");
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

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIF createXMLBIF() throws javax.xml.bind.JAXBException {
    return (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIF) newInstance(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIF.class);
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType createXMLBIFType() throws javax.xml.bind.JAXBException {
    return (unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType) newInstance(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.class);
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HeaderType createXMLBIFTypeHeaderType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.HeaderTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.StaticPropertyType createXMLBIFTypeStaticPropertyType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.StaticPropertyTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType createXMLBIFTypeHierarchyType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.HierarchyTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType.RootType createXMLBIFTypeHierarchyTypeRootType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.HierarchyTypeImpl.RootTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType.RootType.LevelType createXMLBIFTypeHierarchyTypeRootTypeLevelType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.HierarchyTypeImpl.RootTypeImpl.LevelTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType createXMLBIFTypeNetworkType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType createXMLBIFTypeNetworkTypeVariablesType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType createXMLBIFTypeNetworkTypeVariablesTypeVariableType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.StateType createXMLBIFTypeNetworkTypeVariablesTypeVariableTypeStateType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.StateTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType createXMLBIFTypeNetworkTypeVariablesTypeVariableTypeMetaphoreType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.MetaphoreTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType createXMLBIFTypeNetworkTypeVariablesTypeVariableTypeMetaphoreTypeExplanationType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.VariablesTypeImpl.VariableTypeImpl.MetaphoreTypeImpl.ExplanationTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType createXMLBIFTypeNetworkTypeStructureType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.StructureTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType.EdgeType createXMLBIFTypeNetworkTypeStructureTypeEdgeType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.StructureTypeImpl.EdgeTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType createXMLBIFTypeNetworkTypeConditionalDistributionSetType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeOwnerType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.OwnerTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeParentsType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.ParentsTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType.ParentType createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeParentsTypeParentType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.ParentsTypeImpl.ParentTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeCPTType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.CPTTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeCPTTypeDependentParentIndexType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.CPTTypeImpl.DependentParentIndexTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeNormalType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.NormalTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeNormalTypeFunctionType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.NormalTypeImpl.FunctionTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeNormalTypeFunctionTypeConstantsType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.NormalTypeImpl.FunctionTypeImpl.ConstantsTypeImpl();
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeNormalTypeFunctionTypeConstantsTypeConstantType() throws javax.xml.bind.JAXBException {
    return new unbbayes.io.xmlbif.version6.xmlclasses.impl.XMLBIFTypeImpl.NetworkTypeImpl.ConditionalDistributionSetTypeImpl.ConditionalDistributionTypeImpl.NormalTypeImpl.FunctionTypeImpl.ConstantsTypeImpl.ConstantTypeImpl();
  }

}
