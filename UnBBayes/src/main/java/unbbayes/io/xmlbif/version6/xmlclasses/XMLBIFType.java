package unbbayes.io.xmlbif.version6.xmlclasses;

import java.util.Vector;

import unbbayes.simdecs.PerguntaNodo;

public interface XMLBIFType {
  public interface HeaderType {
    public double getVersion();
  
    public void setVersion(double pVersion);
  
    public java.lang.String getName();
  
    public void setName(java.lang.String pName);

    public java.lang.String getCreator();
  
    public void setCreator(java.lang.String pCreator);
  
  }

  public interface StaticPropertyType {
    public int getNodeSize();
  
    public void setNodeSize(int pNodeSize);
  
    public java.lang.String getNodeFontName();
  
    public void setNodeFontName(java.lang.String pNodeFontName);
  
    public int getNodeFontSize();
  
    public void setNodeFontSize(int pNodeFontSize);
  
    public int getColorUtilityNode();
  
    public void setColorUtilityNode(int pColorUtilityNode);
  
    public int getColorDecisionNode();
  
    public void setColorDecisionNode(int pColorDecisionNode);
  
    public int getColorDiscreteProbabilisticNode();
  
    public void setColorDiscreteProbabilisticNode(int pColorDiscreteProbabilisticNode);
  
    public int getColorContinuousProbilisticNode();
  
    public void setColorContinuousProbilisticNode(int pColorContinuousProbilisticNode);
  
    public int getColorExplanationNode();
  
    public void setColorExplanationNode(int pColorExplanationNode);
  
  }

  public interface HierarchyType {
    public interface RootType {
      public interface LevelType {
        public java.lang.String getName();
      
        public void setName(java.lang.String pName);
      
      }
    
      
    
    
      public java.lang.String getName();
    
      public void setName(java.lang.String pName);
    
      public java.util.List getLevel();
    
    }
  
    
  
  
    public java.util.List getRoot();
  
  }

  public interface NetworkType {
    public interface VariablesType {
      public interface VariableType {
        public interface StateType {
          public java.lang.String getName();
        
          public void setName(java.lang.String pName);
        
          public java.lang.String getDescription();
        
          public void setDescription(java.lang.String pDescription);
        
          public byte[] getMmedia();
        
          public void setMmedia(byte[] pMmedia);
        
        }
      
        public interface MetaphoreType {
          public interface ExplanationType {
            public java.lang.String getEvidence();
          
            public void setEvidence(java.lang.String pEvidence);
          
            public java.lang.String getEvidenceType();
          
            public void setEvidenceType(java.lang.String pEvidenceType);
          
            public java.lang.String getComments();
          
            public void setComments(java.lang.String pComments);
          
          }
        
          
        
        
          public java.lang.String getDescription();
        
          public void setDescription(java.lang.String pDescription);
        
          public java.util.List getExplanation();
        
        }
      
        
      
      
        public java.lang.String getName();
      
        public void setName(java.lang.String pName);
      
        public java.lang.String getType();
      
        public void setType(java.lang.String pType);
      
        public int getXPos();
      
        public void setXPos(int pXPos);
      
        public int getYPos();
      
        public void setYPos(int pYPos);
      
        public float getWidth();
      
        public void setWidth(float pWidth);
      
        public float getHeight();
      
        public void setHeight(float pHeight);
      
        public int getRgbColor();
      
        public void setRgbColor(int pRgbColor);
      
        public java.lang.String getDescription();
      
        public void setDescription(java.lang.String pDescription);
      
        public byte[] getMmedia();
      
        public void setMmedia(byte[] pMmedia);
      
        public java.util.List getState();
      
        public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType getMetaphore();
      
        public void setMetaphore(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType pMetaphore);
        
        // Definiões do SimDeCs
        public float getValorEtapa();
        public float getTempoEtapa();
        public void setValorEtapa(float pValorEtapa);
        public void setTempoEtapa(float pTempoEtapa);
        public void setBogus(boolean bogus);
        public boolean isBogus();
        public String getPerguntas();     
        public void setPerguntas(String perguntas);
      }
    
      
    
    
      public java.util.List getVariable();
    
    }
  
    public interface StructureType {
      public interface EdgeType {
        public java.lang.String getParent();
      
        public void setParent(java.lang.String pParent);
      
        public java.lang.String getChild();
      
        public void setChild(java.lang.String pChild);
      
      }
    
      
    
    
      public java.util.List getEdge();
    
    }
  
    public interface ConditionalDistributionSetType {
      public interface ConditionalDistributionType {
        public interface OwnerType {
          public java.lang.String getName();
        
          public void setName(java.lang.String pName);
        
        }
      
        public interface ParentsType {
          public interface ParentType {
            public java.lang.String getName();
          
            public void setName(java.lang.String pName);
          
            public int getIndex();
          
            public void setIndex(int pIndex);
          
          }
        
          
        
        
          public java.util.List getParent();
        
        }
      
        public interface CPTType {
          public interface DependentParentIndexType {
            public int getIndex();
          
            public void setIndex(int pIndex);
          
            public double getValue();
          
            public void setValue(double pValue);
          
          }
        
          
        
        
          public java.util.List getDependentParentIndex();
        
        }
      
        public interface NormalType {
          public interface FunctionType {
            public interface ConstantsType {
              public interface ConstantType {
                public int getIndex();
              
                public void setIndex(int pIndex);
              
                public double getValue();
              
                public void setValue(double pValue);
              
              }
            
              
            
            
              public java.util.List getConstant();
            
            }
          
            public int getIndex();
          
            public void setIndex(int pIndex);
          
            public double getMean();
          
            public void setMean(double pMean);
          
            public double getVariance();
          
            public void setVariance(double pVariance);
          
            public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType getConstants();
          
            public void setConstants(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType pConstants);
          
          }
        
          
        
        
          public java.util.List getFunction();
        
        }
      
        public java.lang.String getType();
      
        public void setType(java.lang.String pType);
      
        public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType getOwner();
      
        public void setOwner(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType pOwner);
      
        public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType getParents();
      
        public void setParents(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType pParents);
      
        public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType getCPT();
      
        public void setCPT(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType pCPT);
      
        public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType getNormal();
      
        public void setNormal(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType pNormal);
      
      }
    
      
    
    
      public java.util.List getConditionalDistribution();
    
    }
  
    public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType getVariables();
  
    public void setVariables(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType pVariables);
  
    public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType getStructure();
  
    public void setStructure(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType pStructure);
  
    public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType getConditionalDistributionSet();
  
    public void setConditionalDistributionSet(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType pConditionalDistributionSet);
  
  }

  public double getVersion();

  public void setVersion(double pVersion);

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HeaderType getHeader();

  public void setHeader(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HeaderType pHeader);

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.StaticPropertyType getStaticProperty();

  public void setStaticProperty(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.StaticPropertyType pStaticProperty);

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType getHierarchy();

  public void setHierarchy(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType pHierarchy);

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType getNetwork();

  public void setNetwork(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType pNetwork);

}
