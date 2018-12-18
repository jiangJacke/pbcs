/*  1:   */ package com.example.utils;
/*  2:   */ 
/*  3:   */ import java.io.BufferedReader;
/*  4:   */ import java.io.FileInputStream;
/*  5:   */ import java.io.InputStreamReader;
/*  6:   */ import java.util.TreeMap;
/*  7:   */ 
/*  8:   */ public abstract class ParserO21
/*  9:   */ {
/* 10: 9 */   public static int ParserType = 0;
/* 11:   */   public static final int ParserCPDLC = 0;
/* 12:   */   public static final int ParserADSC = 1;
/* 13:   */   public static String sMedTypeM;
/* 14:24 */   public static boolean bMedTypeM = false;
/* 15:   */   public static String sMedTypeFileName;
/* 16:   */   public static TreeMap<String, String> TrMapMedT;
/* 17:   */   public static String sMedTypeDefault;
/* 18:   */   
/* 19:   */   abstract int GetSizeACTP(String paramString);
/* 20:   */   
/* 21:   */   abstract int GetSizeOper(String paramString);
/* 22:   */   
/* 23:   */   abstract int GetSizeAirFr(String paramString);
/* 24:   */   
/* 25:   */   abstract int GetSizeMeTP(String paramString);
/* 26:   */   
/* 27:   */   abstract int GetSizeMonth(String paramString);
/* 28:   */   
/* 29:   */   abstract int GetSizeFIR(String paramString);
/* 30:   */   
/* 31:   */   abstract int GetSizeGES(String paramString);
/* 32:   */   
/* 33:   */   abstract TreeMap<Short, Integer> GetTrMhis();
/* 34:   */   
/* 35:   */   abstract long GetlNumRec();
/* 36:   */   
/* 37:   */   public static void SetupParserType(String sFileName)
/* 38:   */   {
/* 39:   */     try
/* 40:   */     {
/* 41:37 */       FileInputStream fIn1 = new FileInputStream(sFileName);
/* 42:38 */       BufferedReader BufR1 = new BufferedReader(new InputStreamReader(fIn1));
/* 43:39 */       String sM = BufR1.readLine();
/* 44:   */       
/* 45:   */ 
/* 46:   */ 
/* 47:   */ 
/* 48:   */ 
/* 49:   */ 
/* 50:   */ 
/* 51:   */ 
/* 52:   */ 
/* 53:   */ 
/* 54:50 */       String[] sArrM = sM.split(",");
/* 55:51 */       if (sArrM.length == 20)
/* 56:   */       {
/* 57:52 */         ParserType = 0;bMedTypeM = false;
/* 58:   */       }
/* 59:54 */       else if (sArrM.length == 19)
/* 60:   */       {
/* 61:55 */         ParserType = 0;bMedTypeM = true;
/* 62:   */       }
/* 63:57 */       else if (sArrM.length == 13)
/* 64:   */       {
/* 65:58 */         ParserType = 1;bMedTypeM = false;
/* 66:   */       }
/* 67:60 */       else if (sArrM.length == 12)
/* 68:   */       {
/* 69:61 */         ParserType = 1;bMedTypeM = true;
/* 70:   */       }
/* 71:   */       else
/* 72:   */       {
/* 73:63 */         ParserType = -1;
/* 74:   */       }
/* 75:   */     }
/* 76:   */     catch (Exception ExM)
/* 77:   */     {
/* 78:65 */       ParserType = -1;ExM.printStackTrace();
/* 79:   */     }
/* 80:   */   }
/* 81:   */   
/* 82:   */   public static void SetupMediaType(String sFileName)
/* 83:   */   {
/* 84:   */     try
/* 85:   */     {
/* 86:72 */       FileInputStream fIn1 = new FileInputStream(sFileName);
/* 87:73 */       BufferedReader BufR1 = new BufferedReader(new InputStreamReader(fIn1));
/* 88:74 */       String sM = BufR1.readLine();
/* 89:75 */       if (sM.startsWith("default")) {
/* 90:76 */         sMedTypeDefault = sM.substring(sM.lastIndexOf(" ") + 1);
/* 91:   */       }
/* 92:77 */       TrMapMedT = new TreeMap();
/* 93:78 */       while (BufR1.ready())
/* 94:   */       {
/* 95:80 */         sM = BufR1.readLine();
/* 96:81 */         String[] sArrM = sM.split("\\s");
/* 97:   */         
/* 98:83 */         TrMapMedT.put(sArrM[0], sArrM[(sArrM.length - 1)]);
/* 99:   */       }
/* :0:85 */       BufR1.close();fIn1.close();
/* :1:   */     }
/* :2:   */     catch (Exception ExM)
/* :3:   */     {
/* :4:87 */       bMedTypeM = false;sMedTypeFileName = null;
/* :5:88 */       sMedTypeDefault = null;TrMapMedT = null;
/* :6:89 */       ExM.printStackTrace();
/* :7:   */     }
/* :8:   */   }
/* :9:   */ }


