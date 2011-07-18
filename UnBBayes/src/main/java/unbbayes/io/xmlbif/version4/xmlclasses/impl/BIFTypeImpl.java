package unbbayes.io.xmlbif.version4.xmlclasses.impl;

public class BIFTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType {
  public static class HEADERTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HEADERType {
    private java.lang.String _nAME;
  
    private int _vERSION;
  
    private java.lang.String _cREATOR;
  
  
    public java.lang.String getNAME() {
      return _nAME;
    }
  
    public void setNAME(java.lang.String pNAME) {
      _nAME = pNAME;
    }
  
    public int getVERSION() {
      return _vERSION;
    }
  
    public void setVERSION(int pVERSION) {
      _vERSION = pVERSION;
    }
  
    public java.lang.String getCREATOR() {
      return _cREATOR;
    }
  
    public void setCREATOR(java.lang.String pCREATOR) {
      _cREATOR = pCREATOR;
    }
  
  }

  public static class STATICPROPERTYTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.STATICPROPERTYType {
    private java.lang.String _nODESIZE;
  
    private java.lang.String _nODEFONTNAME;
  
    private int _nODEFONTSIZE;
  
    private int _cOLORUTILITY;
  
    private int _cOLORDECISION;
  
    private int _cOLORPROBDESCRIPTION;
  
    private int _cOLORPROBEXPLANATION;
  
  
    public java.lang.String getNODESIZE() {
      return _nODESIZE;
    }
  
    public void setNODESIZE(java.lang.String pNODESIZE) {
      _nODESIZE = pNODESIZE;
    }
  
    public java.lang.String getNODEFONTNAME() {
      return _nODEFONTNAME;
    }
  
    public void setNODEFONTNAME(java.lang.String pNODEFONTNAME) {
      _nODEFONTNAME = pNODEFONTNAME;
    }
  
    public int getNODEFONTSIZE() {
      return _nODEFONTSIZE;
    }
  
    public void setNODEFONTSIZE(int pNODEFONTSIZE) {
      _nODEFONTSIZE = pNODEFONTSIZE;
    }
  
    public int getCOLORUTILITY() {
      return _cOLORUTILITY;
    }
  
    public void setCOLORUTILITY(int pCOLORUTILITY) {
      _cOLORUTILITY = pCOLORUTILITY;
    }
  
    public int getCOLORDECISION() {
      return _cOLORDECISION;
    }
  
    public void setCOLORDECISION(int pCOLORDECISION) {
      _cOLORDECISION = pCOLORDECISION;
    }
  
    public int getCOLORPROBDESCRIPTION() {
      return _cOLORPROBDESCRIPTION;
    }
  
    public void setCOLORPROBDESCRIPTION(int pCOLORPROBDESCRIPTION) {
      _cOLORPROBDESCRIPTION = pCOLORPROBDESCRIPTION;
    }
  
    public int getCOLORPROBEXPLANATION() {
      return _cOLORPROBEXPLANATION;
    }
  
    public void setCOLORPROBEXPLANATION(int pCOLORPROBEXPLANATION) {
      _cOLORPROBEXPLANATION = pCOLORPROBEXPLANATION;
    }
  
  }

  public static class HIERARCHYTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType {
    public static class ROOTTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType.ROOTType {
      public static class LEVELTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.HIERARCHYType.ROOTType.LEVELType {
        private java.lang.String _nAME;
      
      
        public java.lang.String getNAME() {
          return _nAME;
        }
      
        public void setNAME(java.lang.String pNAME) {
          _nAME = pNAME;
        }
      
      }
    
      private java.lang.String _nAME;
    
      private java.util.List _lEVEL = new java.util.ArrayList();
    
    
      public java.lang.String getNAME() {
        return _nAME;
      }
    
      public void setNAME(java.lang.String pNAME) {
        _nAME = pNAME;
      }
    
      public java.util.List getLEVEL() {
        return _lEVEL;
      }
    
    }
  
    private java.util.List _rOOT = new java.util.ArrayList();
  
  
    public java.util.List getROOT() {
      return _rOOT;
    }
  
  }

  public static class NETWORKTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType {
    public static class VARIABLESTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType {
      public static class VARTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType {
        public static class STATENAMETypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.STATENAMEType {
          private java.lang.String _value;
        
        
          public java.lang.String getValue() {
            return _value;
          }
        
          public void setValue(java.lang.String pValue) {
            _value = pValue;
          }
        
        }
      
        public static class METAPHORETypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType {
          public static class TRIGGERTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.TRIGGERType {
            private java.lang.String _nAME;
          
            private java.lang.String _cOMMENTS;
          
          
            public java.lang.String getNAME() {
              return _nAME;
            }
          
            public void setNAME(java.lang.String pNAME) {
              _nAME = pNAME;
            }
          
            public java.lang.String getCOMMENTS() {
              return _cOMMENTS;
            }
          
            public void setCOMMENTS(java.lang.String pCOMMENTS) {
              _cOMMENTS = pCOMMENTS;
            }
          
          }
        
          public static class EXCLUDENTTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.EXCLUDENTType {
            private java.lang.String _nAME;
          
            private java.lang.String _cOMMENTS;
          
          
            public java.lang.String getNAME() {
              return _nAME;
            }
          
            public void setNAME(java.lang.String pNAME) {
              _nAME = pNAME;
            }
          
            public java.lang.String getCOMMENTS() {
              return _cOMMENTS;
            }
          
            public void setCOMMENTS(java.lang.String pCOMMENTS) {
              _cOMMENTS = pCOMMENTS;
            }
          
          }
        
          public static class ESSENCIALTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.ESSENCIALType {
            private java.lang.String _nAME;
          
            private java.lang.String _cOMMENTS;
          
          
            public java.lang.String getNAME() {
              return _nAME;
            }
          
            public void setNAME(java.lang.String pNAME) {
              _nAME = pNAME;
            }
          
            public java.lang.String getCOMMENTS() {
              return _cOMMENTS;
            }
          
            public void setCOMMENTS(java.lang.String pCOMMENTS) {
              _cOMMENTS = pCOMMENTS;
            }
          
          }
        
          public static class COMPLEMENTARYTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.COMPLEMENTARYType {
            private java.lang.String _nAME;
          
            private java.lang.String _cOMMENTS;
          
          
            public java.lang.String getNAME() {
              return _nAME;
            }
          
            public void setNAME(java.lang.String pNAME) {
              _nAME = pNAME;
            }
          
            public java.lang.String getCOMMENTS() {
              return _cOMMENTS;
            }
          
            public void setCOMMENTS(java.lang.String pCOMMENTS) {
              _cOMMENTS = pCOMMENTS;
            }
          
          }
        
          public static class NATypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.VARType.METAPHOREType.NAType {
            private java.lang.String _nAME;
          
            private java.lang.String _cOMMENTS;
          
          
            public java.lang.String getNAME() {
              return _nAME;
            }
          
            public void setNAME(java.lang.String pNAME) {
              _nAME = pNAME;
            }
          
            public java.lang.String getCOMMENTS() {
              return _cOMMENTS;
            }
          
            public void setCOMMENTS(java.lang.String pCOMMENTS) {
              _cOMMENTS = pCOMMENTS;
            }
          
          }
        
          private java.util.List _dESCRIPTION = new java.util.ArrayList();
        
          private java.util.List _tRIGGER = new java.util.ArrayList();
        
          private java.util.List _eXCLUDENT = new java.util.ArrayList();
        
          private java.util.List _eSSENCIAL = new java.util.ArrayList();
        
          private java.util.List _cOMPLEMENTARY = new java.util.ArrayList();
        
          private java.util.List _nA = new java.util.ArrayList();
        
        
          public java.util.List getDESCRIPTION() {
            return _dESCRIPTION;
          }
        
          public java.util.List getTRIGGER() {
            return _tRIGGER;
          }
        
          public java.util.List getEXCLUDENT() {
            return _eXCLUDENT;
          }
        
          public java.util.List getESSENCIAL() {
            return _eSSENCIAL;
          }
        
          public java.util.List getCOMPLEMENTARY() {
            return _cOMPLEMENTARY;
          }
        
          public java.util.List getNA() {
            return _nA;
          }
        
        }
      
        private java.lang.String _nAME;
      
        private java.lang.String _tYPE;
      
        private int _xPOS;
      
        private int _yPOS;
      
        private java.lang.String _lABEL;
      
        private byte[] _mMIDIA;
      
        private java.util.List _sTATENAME = new java.util.ArrayList();
      
        private java.util.List _mETAPHORE = new java.util.ArrayList();
      
      
        public java.lang.String getNAME() {
          return _nAME;
        }
      
        public void setNAME(java.lang.String pNAME) {
          _nAME = pNAME;
        }
      
        public java.lang.String getTYPE() {
          return _tYPE;
        }
      
        public void setTYPE(java.lang.String pTYPE) {
          _tYPE = pTYPE;
        }
      
        public int getXPOS() {
          return _xPOS;
        }
      
        public void setXPOS(int pXPOS) {
          _xPOS = pXPOS;
        }
      
        public int getYPOS() {
          return _yPOS;
        }
      
        public void setYPOS(int pYPOS) {
          _yPOS = pYPOS;
        }
      
        public java.lang.String getLABEL() {
          return _lABEL;
        }
      
        public void setLABEL(java.lang.String pLABEL) {
          _lABEL = pLABEL;
        }
      
        public byte[] getMMIDIA() {
          return _mMIDIA;
        }
      
        public void setMMIDIA(byte[] pMMIDIA) {
          _mMIDIA = pMMIDIA;
        }
      
        public java.util.List getSTATENAME() {
          return _sTATENAME;
        }
      
        public java.util.List getMETAPHORE() {
          return _mETAPHORE;
        }
      
      }
    
      public static class DECISIONTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.DECISIONType {
        public static class STATENAMETypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.DECISIONType.STATENAMEType {
          private java.lang.String _value;
        
        
          public java.lang.String getValue() {
            return _value;
          }
        
          public void setValue(java.lang.String pValue) {
            _value = pValue;
          }
        
        }
      
        private java.lang.String _nAME;
      
        private java.lang.String _tYPE;
      
        private int _xPOS;
      
        private int _yPOS;
      
        private java.lang.String _lABEL;
      
        private byte[] _mMIDIA;
      
        private java.util.List _sTATENAME = new java.util.ArrayList();
      
      
        public java.lang.String getNAME() {
          return _nAME;
        }
      
        public void setNAME(java.lang.String pNAME) {
          _nAME = pNAME;
        }
      
        public java.lang.String getTYPE() {
          return _tYPE;
        }
      
        public void setTYPE(java.lang.String pTYPE) {
          _tYPE = pTYPE;
        }
      
        public int getXPOS() {
          return _xPOS;
        }
      
        public void setXPOS(int pXPOS) {
          _xPOS = pXPOS;
        }
      
        public int getYPOS() {
          return _yPOS;
        }
      
        public void setYPOS(int pYPOS) {
          _yPOS = pYPOS;
        }
      
        public java.lang.String getLABEL() {
          return _lABEL;
        }
      
        public void setLABEL(java.lang.String pLABEL) {
          _lABEL = pLABEL;
        }
      
        public byte[] getMMIDIA() {
          return _mMIDIA;
        }
      
        public void setMMIDIA(byte[] pMMIDIA) {
          _mMIDIA = pMMIDIA;
        }
      
        public java.util.List getSTATENAME() {
          return _sTATENAME;
        }
      
      }
    
      public static class UTILITYTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.UTILITYType {
        public static class STATENAMETypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.VARIABLESType.UTILITYType.STATENAMEType {
          private java.lang.String _value;
        
        
          public java.lang.String getValue() {
            return _value;
          }
        
          public void setValue(java.lang.String pValue) {
            _value = pValue;
          }
        
        }
      
        private java.lang.String _nAME;
      
        private java.lang.String _tYPE;
      
        private int _xPOS;
      
        private int _yPOS;
      
        private java.lang.String _lABEL;
      
        private byte[] _mMIDIA;
      
        private java.util.List _sTATENAME = new java.util.ArrayList();
      
      
        public java.lang.String getNAME() {
          return _nAME;
        }
      
        public void setNAME(java.lang.String pNAME) {
          _nAME = pNAME;
        }
      
        public java.lang.String getTYPE() {
          return _tYPE;
        }
      
        public void setTYPE(java.lang.String pTYPE) {
          _tYPE = pTYPE;
        }
      
        public int getXPOS() {
          return _xPOS;
        }
      
        public void setXPOS(int pXPOS) {
          _xPOS = pXPOS;
        }
      
        public int getYPOS() {
          return _yPOS;
        }
      
        public void setYPOS(int pYPOS) {
          _yPOS = pYPOS;
        }
      
        public java.lang.String getLABEL() {
          return _lABEL;
        }
      
        public void setLABEL(java.lang.String pLABEL) {
          _lABEL = pLABEL;
        }
      
        public byte[] getMMIDIA() {
          return _mMIDIA;
        }
      
        public void setMMIDIA(byte[] pMMIDIA) {
          _mMIDIA = pMMIDIA;
        }
      
        public java.util.List getSTATENAME() {
          return _sTATENAME;
        }
      
      }
    
      private java.util.List _vAR = new java.util.ArrayList();
    
      private java.util.List _dECISION = new java.util.ArrayList();
    
      private java.util.List _uTILITY = new java.util.ArrayList();
    
    
      public java.util.List getVAR() {
        return _vAR;
      }
    
      public java.util.List getDECISION() {
        return _dECISION;
      }
    
      public java.util.List getUTILITY() {
        return _uTILITY;
      }
    
    }
  
    public static class STRUCTURETypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.STRUCTUREType {
      public static class ARCTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.STRUCTUREType.ARCType {
        private java.lang.String _pARENT;
      
        private java.lang.String _cHILD;
      
      
        public java.lang.String getPARENT() {
          return _pARENT;
        }
      
        public void setPARENT(java.lang.String pPARENT) {
          _pARENT = pPARENT;
        }
      
        public java.lang.String getCHILD() {
          return _cHILD;
        }
      
        public void setCHILD(java.lang.String pCHILD) {
          _cHILD = pCHILD;
        }
      
      }
    
      private java.util.List _aRC = new java.util.ArrayList();
    
    
      public java.util.List getARC() {
        return _aRC;
      }
    
    }
  
    public static class POTENTIALTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType {
      public static class POTTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType {
        public static class PRIVATETypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.PRIVATEType {
          private java.lang.String _nAME;
        
        
          public java.lang.String getNAME() {
            return _nAME;
          }
        
          public void setNAME(java.lang.String pNAME) {
            _nAME = pNAME;
          }
        
        }
      
        public static class CONDSETTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.CONDSETType {
          public static class CONDLEMTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.CONDSETType.CONDLEMType {
            private java.lang.String _nAME;
          
          
            public java.lang.String getNAME() {
              return _nAME;
            }
          
            public void setNAME(java.lang.String pNAME) {
              _nAME = pNAME;
            }
          
          }
        
          private java.util.List _cONDLEM = new java.util.ArrayList();
        
        
          public java.util.List getCONDLEM() {
            return _cONDLEM;
          }
        
        }
      
        public static class DPISTypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.DPISType {
          public static class DPITypeImpl implements unbbayes.io.xmlbif.version4.xmlclasses.BIFType.NETWORKType.POTENTIALType.POTType.DPISType.DPIType {
            private int _iNDEXES;
          
            private java.lang.String _value;
          
          
            public int getINDEXES() {
              return _iNDEXES;
            }
          
            public void setINDEXES(int pINDEXES) {
              _iNDEXES = pINDEXES;
            }
          
            public java.lang.String getValue() {
              return _value;
            }
          
            public void setValue(java.lang.String pValue) {
              _value = pValue;
            }
          
          }
        
          private java.util.List _dPI = new java.util.ArrayList();
        
        
          public java.util.List getDPI() {
            return _dPI;
          }
        
        }
      
        private java.lang.String _tYPE;
      
        private java.util.List _pRIVATE = new java.util.ArrayList();
      
        private java.util.List _cONDSET = new java.util.ArrayList();
      
        private java.util.List _dPIS = new java.util.ArrayList();
      
      
        public java.lang.String getTYPE() {
          return _tYPE;
        }
      
        public void setTYPE(java.lang.String pTYPE) {
          _tYPE = pTYPE;
        }
      
        public java.util.List getPRIVATE() {
          return _pRIVATE;
        }
      
        public java.util.List getCONDSET() {
          return _cONDSET;
        }
      
        public java.util.List getDPIS() {
          return _dPIS;
        }
      
      }
    
      private java.util.List _pOT = new java.util.ArrayList();
    
    
      public java.util.List getPOT() {
        return _pOT;
      }
    
    }
  
    private java.util.List _vARIABLES = new java.util.ArrayList();
  
    private java.util.List _sTRUCTURE = new java.util.ArrayList();
  
    private java.util.List _pOTENTIAL = new java.util.ArrayList();
  
  
    public java.util.List getVARIABLES() {
      return _vARIABLES;
    }
  
    public java.util.List getSTRUCTURE() {
      return _sTRUCTURE;
    }
  
    public java.util.List getPOTENTIAL() {
      return _pOTENTIAL;
    }
  
  }

  private int _vERSION;

  private java.util.List _hEADER = new java.util.ArrayList();

  private java.util.List _sTATICPROPERTY = new java.util.ArrayList();

  private java.util.List _hIERARCHY = new java.util.ArrayList();

  private java.util.List _nETWORK = new java.util.ArrayList();


  public int getVERSION() {
    return _vERSION;
  }

  public void setVERSION(int pVERSION) {
    _vERSION = pVERSION;
  }

  public java.util.List getHEADER() {
    return _hEADER;
  }

  public java.util.List getSTATICPROPERTY() {
    return _sTATICPROPERTY;
  }

  public java.util.List getHIERARCHY() {
    return _hIERARCHY;
  }

  public java.util.List getNETWORK() {
    return _nETWORK;
  }

}
