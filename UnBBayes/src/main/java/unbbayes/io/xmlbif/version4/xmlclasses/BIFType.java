package unbbayes.io.xmlbif.version4.xmlclasses;

public interface BIFType {
  public interface HEADERType {
    public java.lang.String getNAME();
  
    public void setNAME(java.lang.String pNAME);
  
    public int getVERSION();
  
    public void setVERSION(int pVERSION);
  
    public java.lang.String getCREATOR();
  
    public void setCREATOR(java.lang.String pCREATOR);
  
  }

  public interface STATICPROPERTYType {
    public java.lang.String getNODESIZE();
  
    public void setNODESIZE(java.lang.String pNODESIZE);
  
    public java.lang.String getNODEFONTNAME();
  
    public void setNODEFONTNAME(java.lang.String pNODEFONTNAME);
  
    public int getNODEFONTSIZE();
  
    public void setNODEFONTSIZE(int pNODEFONTSIZE);
  
    public int getCOLORUTILITY();
  
    public void setCOLORUTILITY(int pCOLORUTILITY);
  
    public int getCOLORDECISION();
  
    public void setCOLORDECISION(int pCOLORDECISION);
  
    public int getCOLORPROBDESCRIPTION();
  
    public void setCOLORPROBDESCRIPTION(int pCOLORPROBDESCRIPTION);
  
    public int getCOLORPROBEXPLANATION();
  
    public void setCOLORPROBEXPLANATION(int pCOLORPROBEXPLANATION);
  
  }

  public interface HIERARCHYType {
    public interface ROOTType {
      public interface LEVELType {
        public java.lang.String getNAME();
      
        public void setNAME(java.lang.String pNAME);
      
      }
    
      
    
    
      public java.lang.String getNAME();
    
      public void setNAME(java.lang.String pNAME);
    
      public java.util.List getLEVEL();
    
    }
  
    
  
  
    public java.util.List getROOT();
  
  }

  public interface NETWORKType {
    public interface VARIABLESType {
      public interface VARType {
        public interface STATENAMEType {
          public java.lang.String getValue();
        
          public void setValue(java.lang.String pValue);
        
        }
      
        public interface METAPHOREType {
          public interface TRIGGERType {
            public java.lang.String getNAME();
          
            public void setNAME(java.lang.String pNAME);
          
            public java.lang.String getCOMMENTS();
          
            public void setCOMMENTS(java.lang.String pCOMMENTS);
          
          }
        
          public interface EXCLUDENTType {
            public java.lang.String getNAME();
          
            public void setNAME(java.lang.String pNAME);
          
            public java.lang.String getCOMMENTS();
          
            public void setCOMMENTS(java.lang.String pCOMMENTS);
          
          }
        
          public interface ESSENCIALType {
            public java.lang.String getNAME();
          
            public void setNAME(java.lang.String pNAME);
          
            public java.lang.String getCOMMENTS();
          
            public void setCOMMENTS(java.lang.String pCOMMENTS);
          
          }
        
          public interface COMPLEMENTARYType {
            public java.lang.String getNAME();
          
            public void setNAME(java.lang.String pNAME);
          
            public java.lang.String getCOMMENTS();
          
            public void setCOMMENTS(java.lang.String pCOMMENTS);
          
          }
        
          public interface NAType {
            public java.lang.String getNAME();
          
            public void setNAME(java.lang.String pNAME);
          
            public java.lang.String getCOMMENTS();
          
            public void setCOMMENTS(java.lang.String pCOMMENTS);
          
          }
        
          
        
          
        
          
        
          
        
          
        
          
        
        
          public java.util.List getDESCRIPTION();
        
          public java.util.List getTRIGGER();
        
          public java.util.List getEXCLUDENT();
        
          public java.util.List getESSENCIAL();
        
          public java.util.List getCOMPLEMENTARY();
        
          public java.util.List getNA();
        
        }
      
        
      
        
      
      
        public java.lang.String getNAME();
      
        public void setNAME(java.lang.String pNAME);
      
        public java.lang.String getTYPE();
      
        public void setTYPE(java.lang.String pTYPE);
      
        public int getXPOS();
      
        public void setXPOS(int pXPOS);
      
        public int getYPOS();
      
        public void setYPOS(int pYPOS);
      
        public java.lang.String getLABEL();
      
        public void setLABEL(java.lang.String pLABEL);
      
        public byte[] getMMIDIA();
      
        public void setMMIDIA(byte[] pMMIDIA);
      
        public java.util.List getSTATENAME();
      
        public java.util.List getMETAPHORE();
      
      }
    
      public interface DECISIONType {
        public interface STATENAMEType {
          public java.lang.String getValue();
        
          public void setValue(java.lang.String pValue);
        
        }
      
        
      
      
        public java.lang.String getNAME();
      
        public void setNAME(java.lang.String pNAME);
      
        public java.lang.String getTYPE();
      
        public void setTYPE(java.lang.String pTYPE);
      
        public int getXPOS();
      
        public void setXPOS(int pXPOS);
      
        public int getYPOS();
      
        public void setYPOS(int pYPOS);
      
        public java.lang.String getLABEL();
      
        public void setLABEL(java.lang.String pLABEL);
      
        public byte[] getMMIDIA();
      
        public void setMMIDIA(byte[] pMMIDIA);
      
        public java.util.List getSTATENAME();
      
      }
    
      public interface UTILITYType {
        public interface STATENAMEType {
          public java.lang.String getValue();
        
          public void setValue(java.lang.String pValue);
        
        }
      
        
      
      
        public java.lang.String getNAME();
      
        public void setNAME(java.lang.String pNAME);
      
        public java.lang.String getTYPE();
      
        public void setTYPE(java.lang.String pTYPE);
      
        public int getXPOS();
      
        public void setXPOS(int pXPOS);
      
        public int getYPOS();
      
        public void setYPOS(int pYPOS);
      
        public java.lang.String getLABEL();
      
        public void setLABEL(java.lang.String pLABEL);
      
        public byte[] getMMIDIA();
      
        public void setMMIDIA(byte[] pMMIDIA);
      
        public java.util.List getSTATENAME();
      
      }
    
      
    
      
    
      
    
    
      public java.util.List getVAR();
    
      public java.util.List getDECISION();
    
      public java.util.List getUTILITY();
    
    }
  
    public interface STRUCTUREType {
      public interface ARCType {
        public java.lang.String getPARENT();
      
        public void setPARENT(java.lang.String pPARENT);
      
        public java.lang.String getCHILD();
      
        public void setCHILD(java.lang.String pCHILD);
      
      }
    
      
    
    
      public java.util.List getARC();
    
    }
  
    public interface POTENTIALType {
      public interface POTType {
        public interface PRIVATEType {
          public java.lang.String getNAME();
        
          public void setNAME(java.lang.String pNAME);
        
        }
      
        public interface CONDSETType {
          public interface CONDLEMType {
            public java.lang.String getNAME();
          
            public void setNAME(java.lang.String pNAME);
          
          }
        
          
        
        
          public java.util.List getCONDLEM();
        
        }
      
        public interface DPISType {
          public interface DPIType {
            public int getINDEXES();
          
            public void setINDEXES(int pINDEXES);
          
            public java.lang.String getValue();
          
            public void setValue(java.lang.String pValue);
          
          }
        
          
        
        
          public java.util.List getDPI();
        
        }
      
        
      
        
      
        
      
      
        public java.lang.String getTYPE();
      
        public void setTYPE(java.lang.String pTYPE);
      
        public java.util.List getPRIVATE();
      
        public java.util.List getCONDSET();
      
        public java.util.List getDPIS();
      
      }
    
      
    
    
      public java.util.List getPOT();
    
    }
  
    
  
    
  
    
  
  
    public java.util.List getVARIABLES();
  
    public java.util.List getSTRUCTURE();
  
    public java.util.List getPOTENTIAL();
  
  }

  

  

  

  


  public int getVERSION();

  public void setVERSION(int pVERSION);

  public java.util.List getHEADER();

  public java.util.List getSTATICPROPERTY();

  public java.util.List getHIERARCHY();

  public java.util.List getNETWORK();

}
