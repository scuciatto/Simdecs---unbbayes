package unbbayes.io.xmlbif.version6.xmlclasses.impl;

import java.util.Vector;

import unbbayes.simdecs.PerguntaNodo;

public class XMLBIFTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType {
  public static class HeaderTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HeaderType {
    private double _version;
  
    private java.lang.String _name;
  
    private java.lang.String _creator;
  
  
    public double getVersion() {
      return _version;
    }
  
    public void setVersion(double pVersion) {
      _version = pVersion;
    }
  
    public java.lang.String getName() {
      return _name;
    }
  
    public void setName(java.lang.String pName) {
      _name = pName;
    }
  
    public java.lang.String getCreator() {
      return _creator;
    }
  
    public void setCreator(java.lang.String pCreator) {
      _creator = pCreator;
    }
  
  }

  public static class StaticPropertyTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.StaticPropertyType {
    private int _nodeSize;
  
    private java.lang.String _nodeFontName;
  
    private int _nodeFontSize;
  
    private int _colorUtilityNode;
  
    private int _colorDecisionNode;
  
    private int _colorDiscreteProbabilisticNode;
  
    private int _colorContinuousProbilisticNode;
  
    private int _colorExplanationNode;
  
  
    public int getNodeSize() {
      return _nodeSize;
    }
  
    public void setNodeSize(int pNodeSize) {
      _nodeSize = pNodeSize;
    }
  
    public java.lang.String getNodeFontName() {
      return _nodeFontName;
    }
  
    public void setNodeFontName(java.lang.String pNodeFontName) {
      _nodeFontName = pNodeFontName;
    }
  
    public int getNodeFontSize() {
      return _nodeFontSize;
    }
  
    public void setNodeFontSize(int pNodeFontSize) {
      _nodeFontSize = pNodeFontSize;
    }
  
    public int getColorUtilityNode() {
      return _colorUtilityNode;
    }
  
    public void setColorUtilityNode(int pColorUtilityNode) {
      _colorUtilityNode = pColorUtilityNode;
    }
  
    public int getColorDecisionNode() {
      return _colorDecisionNode;
    }
  
    public void setColorDecisionNode(int pColorDecisionNode) {
      _colorDecisionNode = pColorDecisionNode;
    }
  
    public int getColorDiscreteProbabilisticNode() {
      return _colorDiscreteProbabilisticNode;
    }
  
    public void setColorDiscreteProbabilisticNode(int pColorDiscreteProbabilisticNode) {
      _colorDiscreteProbabilisticNode = pColorDiscreteProbabilisticNode;
    }
  
    public int getColorContinuousProbilisticNode() {
      return _colorContinuousProbilisticNode;
    }
  
    public void setColorContinuousProbilisticNode(int pColorContinuousProbilisticNode) {
      _colorContinuousProbilisticNode = pColorContinuousProbilisticNode;
    }
  
    public int getColorExplanationNode() {
      return _colorExplanationNode;
    }
  
    public void setColorExplanationNode(int pColorExplanationNode) {
      _colorExplanationNode = pColorExplanationNode;
    }
  
  }

  public static class HierarchyTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType {
    public static class RootTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType.RootType {
      public static class LevelTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType.RootType.LevelType {
        private java.lang.String _name;
      
      
        public java.lang.String getName() {
          return _name;
        }
      
        public void setName(java.lang.String pName) {
          _name = pName;
        }
      
      }
    
      private java.lang.String _name;
    
      private java.util.List _level = new java.util.ArrayList();
    
    
      public java.lang.String getName() {
        return _name;
      }
    
      public void setName(java.lang.String pName) {
        _name = pName;
      }
    
      public java.util.List getLevel() {
        return _level;
      }
    
    }
  
    private java.util.List _root = new java.util.ArrayList();
  
  
    public java.util.List getRoot() {
      return _root;
    }
  
  }

  public static class NetworkTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType {
    public static class VariablesTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType {
      public static class VariableTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType {
        public static class StateTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.StateType {
          private java.lang.String _name;
        
          private java.lang.String _description;
        
          private byte[] _mmedia;
        
        
          public java.lang.String getName() {
            return _name;
          }
        
          public void setName(java.lang.String pName) {
            _name = pName;
          }
        
          public java.lang.String getDescription() {
            return _description;
          }
        
          public void setDescription(java.lang.String pDescription) {
            _description = pDescription;
          }
        
          public byte[] getMmedia() {
            return _mmedia;
          }
        
          public void setMmedia(byte[] pMmedia) {
            _mmedia = pMmedia;
          }
        
        }
      
        public static class MetaphoreTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType {
          public static class ExplanationTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType {
            private java.lang.String _evidence;
          
            private java.lang.String _evidenceType;
          
            private java.lang.String _comments;
          
          
            public java.lang.String getEvidence() {
              return _evidence;
            }
          
            public void setEvidence(java.lang.String pEvidence) {
              _evidence = pEvidence;
            }
          
            public java.lang.String getEvidenceType() {
              return _evidenceType;
            }
          
            public void setEvidenceType(java.lang.String pEvidenceType) {
              _evidenceType = pEvidenceType;
            }
          
            public java.lang.String getComments() {
              return _comments;
            }
          
            public void setComments(java.lang.String pComments) {
              _comments = pComments;
            }
          
          }
        
          private java.lang.String _description;
        
          private java.util.List _explanation = new java.util.ArrayList();
        
        
          public java.lang.String getDescription() {
            return _description;
          }
        
          public void setDescription(java.lang.String pDescription) {
            _description = pDescription;
          }
        
          public java.util.List getExplanation() {
            return _explanation;
          }
        
        }
      
        private java.lang.String _name;
      
        private java.lang.String _type;
      
        private int _xPos;
      
        private int _yPos;
      
        private float _width;
      
        private float _height;
      
        private int _rgbColor;
      
        private java.lang.String _description;
      
        private byte[] _mmedia;
      
        private java.util.List _state = new java.util.ArrayList();
      
        private unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType _metaphore;
        
        // Simdecs
        private float _valor;
        private float _tempo;
        private boolean _isBogus;
        private String _perguntas;
      
      
        public java.lang.String getName() {
          return _name;
        }
      
        public void setName(java.lang.String pName) {
          _name = pName;
        }
      
        public java.lang.String getType() {
          return _type;
        }
      
        public void setType(java.lang.String pType) {
          _type = pType;
        }
      
        public int getXPos() {
          return _xPos;
        }
      
        public void setXPos(int pXPos) {
          _xPos = pXPos;
        }
      
        public int getYPos() {
          return _yPos;
        }
      
        public void setYPos(int pYPos) {
          _yPos = pYPos;
        }
      
        public float getWidth() {
          return _width;
        }
      
        public void setWidth(float pWidth) {
          _width = pWidth;
        }
      
        public float getHeight() {
          return _height;
        }
      
        public void setHeight(float pHeight) {
          _height = pHeight;
        }
      
        public int getRgbColor() {
          return _rgbColor;
        }
      
        public void setRgbColor(int pRgbColor) {
          _rgbColor = pRgbColor;
        }
      
        public java.lang.String getDescription() {
          return _description;
        }
      
        public void setDescription(java.lang.String pDescription) {
          _description = pDescription;
        }
      
        public byte[] getMmedia() {
          return _mmedia;
        }
      
        public void setMmedia(byte[] pMmedia) {
          _mmedia = pMmedia;
        }
      
        public java.util.List getState() {
          return _state;
        }
      
        public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType getMetaphore() {
          return _metaphore;
        }
      
        public void setMetaphore(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType pMetaphore) {
          _metaphore = pMetaphore;
        }

        // Simdevs
		public float getValorEtapa() {
			// TODO Auto-generated method stub
			return _valor;
		}

		public float getTempoEtapa() {
			return _tempo;
		}
			
		public void setValorEtapa(float valor) {
			_valor = valor;
			
		}

		public void setTempoEtapa(float tempo) {
			_tempo = tempo;
			
		}
		
		public void setBogus(boolean bogus) {
			_isBogus = bogus;
		}

		public boolean isBogus() {
			return _isBogus;
		}

		public String getPerguntas() {
			return _perguntas;
		}

		public void setPerguntas(String perguntas) {
			_perguntas = perguntas;
		}
      }
    
      private java.util.List _variable = new java.util.ArrayList();
    
    
      public java.util.List getVariable() {
        return _variable;
      }
    
    }
  
    public static class StructureTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType {
      public static class EdgeTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType.EdgeType {
        private java.lang.String _parent;
      
        private java.lang.String _child;
      
      
        public java.lang.String getParent() {
          return _parent;
        }
      
        public void setParent(java.lang.String pParent) {
          _parent = pParent;
        }
      
        public java.lang.String getChild() {
          return _child;
        }
      
        public void setChild(java.lang.String pChild) {
          _child = pChild;
        }
      
      }
    
      private java.util.List _edge = new java.util.ArrayList();
    
    
      public java.util.List getEdge() {
        return _edge;
      }
    
    }
  
    public static class ConditionalDistributionSetTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType {
      public static class ConditionalDistributionTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType {
        public static class OwnerTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType {
          private java.lang.String _name;
        
        
          public java.lang.String getName() {
            return _name;
          }
        
          public void setName(java.lang.String pName) {
            _name = pName;
          }
        
        }
      
        public static class ParentsTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType {
          public static class ParentTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType.ParentType {
            private java.lang.String _name;
          
            private int _index;
          
          
            public java.lang.String getName() {
              return _name;
            }
          
            public void setName(java.lang.String pName) {
              _name = pName;
            }
          
            public int getIndex() {
              return _index;
            }
          
            public void setIndex(int pIndex) {
              _index = pIndex;
            }
          
          }
        
          private java.util.List _parent = new java.util.ArrayList();
        
        
          public java.util.List getParent() {
            return _parent;
          }
        
        }
      
        public static class CPTTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType {
          public static class DependentParentIndexTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType {
            private int _index;
          
            private double _value;
          
          
            public int getIndex() {
              return _index;
            }
          
            public void setIndex(int pIndex) {
              _index = pIndex;
            }
          
            public double getValue() {
              return _value;
            }
          
            public void setValue(double pValue) {
              _value = pValue;
            }
          
          }
        
          private java.util.List _dependentParentIndex = new java.util.ArrayList();
        
        
          public java.util.List getDependentParentIndex() {
            return _dependentParentIndex;
          }
        
        }
      
        public static class NormalTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType {
          public static class FunctionTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType {
            public static class ConstantsTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType {
              public static class ConstantTypeImpl implements unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType {
                private int _index;
              
                private double _value;
              
              
                public int getIndex() {
                  return _index;
                }
              
                public void setIndex(int pIndex) {
                  _index = pIndex;
                }
              
                public double getValue() {
                  return _value;
                }
              
                public void setValue(double pValue) {
                  _value = pValue;
                }
              
              }
            
              private java.util.List _constant = new java.util.ArrayList();
            
            
              public java.util.List getConstant() {
                return _constant;
              }
            
            }
          
            private int _index;
          
            private double _mean;
          
            private double _variance;
          
            private unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType _constants;
          
          
            public int getIndex() {
              return _index;
            }
          
            public void setIndex(int pIndex) {
              _index = pIndex;
            }
          
            public double getMean() {
              return _mean;
            }
          
            public void setMean(double pMean) {
              _mean = pMean;
            }
          
            public double getVariance() {
              return _variance;
            }
          
            public void setVariance(double pVariance) {
              _variance = pVariance;
            }
          
            public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType getConstants() {
              return _constants;
            }
          
            public void setConstants(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType pConstants) {
              _constants = pConstants;
            }
          
          }
        
          private java.util.List _function = new java.util.ArrayList();
        
        
          public java.util.List getFunction() {
            return _function;
          }
        
        }
      
        private java.lang.String _type;
      
        private unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType _owner;
      
        private unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType _parents;
      
        private unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType _cPT;
      
        private unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType _normal;
      
      
        public java.lang.String getType() {
          return _type;
        }
      
        public void setType(java.lang.String pType) {
          _type = pType;
        }
      
        public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType getOwner() {
          return _owner;
        }
      
        public void setOwner(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType pOwner) {
          _owner = pOwner;
        }
      
        public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType getParents() {
          return _parents;
        }
      
        public void setParents(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType pParents) {
          _parents = pParents;
        }
      
        public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType getCPT() {
          return _cPT;
        }
      
        public void setCPT(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType pCPT) {
          _cPT = pCPT;
        }
      
        public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType getNormal() {
          return _normal;
        }
      
        public void setNormal(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType pNormal) {
          _normal = pNormal;
        }
      
      }
    
      private java.util.List _conditionalDistribution = new java.util.ArrayList();
    
    
      public java.util.List getConditionalDistribution() {
        return _conditionalDistribution;
      }
    
    }
  
    private unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType _variables;
  
    private unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType _structure;
  
    private unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType _conditionalDistributionSet;
  
  
    public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType getVariables() {
      return _variables;
    }
  
    public void setVariables(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.VariablesType pVariables) {
      _variables = pVariables;
    }
  
    public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType getStructure() {
      return _structure;
    }
  
    public void setStructure(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.StructureType pStructure) {
      _structure = pStructure;
    }
  
    public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType getConditionalDistributionSet() {
      return _conditionalDistributionSet;
    }
  
    public void setConditionalDistributionSet(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType.ConditionalDistributionSetType pConditionalDistributionSet) {
      _conditionalDistributionSet = pConditionalDistributionSet;
    }
  
  }

  private double _version;

  private unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HeaderType _header;

  private unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.StaticPropertyType _staticProperty;

  private unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType _hierarchy;

  private unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType _network;


  public double getVersion() {
    return _version;
  }

  public void setVersion(double pVersion) {
    _version = pVersion;
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HeaderType getHeader() {
    return _header;
  }

  public void setHeader(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HeaderType pHeader) {
    _header = pHeader;
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.StaticPropertyType getStaticProperty() {
    return _staticProperty;
  }

  public void setStaticProperty(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.StaticPropertyType pStaticProperty) {
    _staticProperty = pStaticProperty;
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType getHierarchy() {
    return _hierarchy;
  }

  public void setHierarchy(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.HierarchyType pHierarchy) {
    _hierarchy = pHierarchy;
  }

  public unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType getNetwork() {
    return _network;
  }

  public void setNetwork(unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType.NetworkType pNetwork) {
    _network = pNetwork;
  }

}
