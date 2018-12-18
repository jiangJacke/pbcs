/*   1:    */ package com.example.utils;
/*   2:    */ 
/*   3:    */ import java.io.BufferedReader;
/*   4:    */ import java.io.FileInputStream;
/*   5:    */ import java.io.FileOutputStream;
/*   6:    */ import java.io.InputStreamReader;
/*   7:    */ import java.io.PrintStream;
/*   8:    */ import java.util.ArrayList;
/*   9:    */ import java.util.Date;
/*  10:    */ import java.util.Iterator;
/*  11:    */ import java.util.LinkedHashSet;
/*  12:    */ import java.util.TreeMap;
/*  13:    */ import java.util.TreeSet;
/*  14:    */ 
/*  15:    */ public class CPDLCParser
/*  16:    */   extends ParserO21
/*  17:    */ {
/*  18:  8 */   public TreeSet<String> TsATSP = new TreeSet();
/*  19:  9 */   public TreeSet<String> TsACTP = new TreeSet();
/*  20: 10 */   public TreeSet<String> TsOper = new TreeSet();
/*  21: 11 */   public TreeSet<String> TsMont = new TreeSet();
/*  22: 12 */   public TreeSet<String> TsMeTP = new TreeSet();
/*  23: 13 */   public TreeSet<String> TsACID = new TreeSet();
/*  24: 14 */   public TreeSet<String> TsGrES = new TreeSet();
/*  25: 15 */   public TreeSet<String> TsSelATSP = new TreeSet();
/*  26: 16 */   public TreeSet<String> TsSelACTP = new TreeSet();
/*  27: 17 */   public TreeSet<String> TsSelOper = new TreeSet();
/*  28: 18 */   public TreeSet<String> TsSelMont = new TreeSet();
/*  29: 19 */   public TreeSet<String> TsSelMeTP = new TreeSet();
/*  30: 20 */   public TreeSet<String> TsSelACID = new TreeSet();
/*  31: 21 */   public TreeSet<String> TsSelGrES = new TreeSet();
/*  32: 22 */   public LinkedHashSet<CPDLCRec> hsRecAll = new LinkedHashSet();
/*  33: 23 */   public LinkedHashSet<CPDLCRec> hsRec = new LinkedHashSet();
/*  34: 24 */   public TreeMap<Short, Integer> TrMhis = new TreeMap();
/*  35: 25 */   public TreeMap<Short, Integer> TrMhis2 = new TreeMap();
/*  36: 26 */   public TreeMap<Short, Integer> TrMhis3 = new TreeMap();
/*  37: 27 */   public TreeMap<String, LinkedHashSet> TrMrecFIR = new TreeMap();
/*  38: 28 */   public TreeMap<String, LinkedHashSet> TrMrecMont = new TreeMap();
/*  39: 29 */   public TreeMap<String, LinkedHashSet> TrMrecMeTP = new TreeMap();
/*  40: 30 */   public TreeMap<String, LinkedHashSet> TrMrecGES = new TreeMap();
/*  41: 31 */   public TreeMap<String, LinkedHashSet> TrMrecOper = new TreeMap();
/*  42: 32 */   public TreeMap<String, LinkedHashSet> TrMrecACTP = new TreeMap();
/*  43: 33 */   public TreeMap<String, LinkedHashSet> TrMrecACID = new TreeMap();
/*  44: 38 */   public TreeSet<String> TsUnFIR = new TreeSet();
/*  45: 39 */   public TreeSet<String> TsUnMeTP = new TreeSet();
/*  46: 40 */   public TreeSet<String> TsUnGES = new TreeSet();
/*  47: 41 */   public TreeSet<String> TsUnOper = new TreeSet();
/*  48: 42 */   public TreeSet<String> TsUnACTP = new TreeSet();
/*  49: 43 */   public TreeMap<String, String> TrUnFIR = new TreeMap();
/*  50: 44 */   public TreeMap<String, String> TrUnMeTP = new TreeMap();
/*  51: 45 */   public TreeMap<String, String> TrUnGES = new TreeMap();
/*  52: 46 */   public TreeMap<String, String> TrUnOper = new TreeMap();
/*  53: 47 */   public TreeMap<String, String> TrUnACTP = new TreeMap();
/*  54:    */   public long lNumRec;
/*  55: 51 */   public LinkedHashSet<ADSCRec> hsRecAll2 = new LinkedHashSet();
/*  56: 52 */   public LinkedHashSet<ADSCRec> hsRec2 = new LinkedHashSet();
/*  57: 53 */   public ArrayList<ADSCRec> hsRecAll3 = new ArrayList();
/*  58: 54 */   public ArrayList<ADSCRec2> hsRecAll4 = new ArrayList();
/*  59:    */   
/*  60:    */   public CPDLCParser()
/*  61:    */   {
/*  62: 61 */     System.out.println("CPDLCParser Start at " + new Date());
/*  63:    */   }
/*  64:    */   
/*  65:    */   public boolean ReadFileCPDLC(String sFileName1)
/*  66:    */   {
/*  67: 67 */     ParserType = 0;
/*  68: 68 */     long lNumRec1 = 0L;long lNumFound = 0L;
/*  69: 69 */     long lNegativeRec = 0L;
/*  70: 70 */     String sM = "";
/*  71: 71 */     System.out.println(new Date());
/*  72: 72 */     long lFreeM = Runtime.getRuntime().freeMemory();
/*  73: 73 */     long lTotalM = Runtime.getRuntime().totalMemory();
/*  74: 74 */     System.out.println("Free " + lFreeM + "    Total " + lTotalM);
/*  75:    */     try
/*  76:    */     {
/*  77: 77 */       FileInputStream fIn1 = new FileInputStream(sFileName1);
/*  78: 78 */       BufferedReader BufR1 = new BufferedReader(new InputStreamReader(fIn1));
/*  79:    */       
/*  80:    */ 
/*  81:    */ 
/*  82:    */ 
/*  83:    */ 
/*  84:    */ 
/*  85: 85 */       FileOutputStream fOutm = new FileOutputStream(sFileName1.substring(0, sFileName1.lastIndexOf(".")) + "_R.txt");
/*  86:    */       
/*  87:    */ 
/*  88: 88 */       PrintStream PrtSm = new PrintStream(fOutm);
/*  89: 91 */       while (BufR1.ready())
/*  90:    */       {
/*  91: 93 */         sM = BufR1.readLine();
/*  92: 97 */         if (!sM.startsWith("ATSP,REGNO,ACTYPE,OPER,DATE,"))
/*  93:    */         {
/*  94: 99 */           lNumRec1 += 1L;
/*  95:    */           
/*  96:    */ 
/*  97:102 */           String[] sArrM = sM.split(",");
/*  98:104 */           if ((sArrM.length < 19) || ((sArrM.length == 19) && (!bMedTypeM)))
/*  99:    */           {
/* 100:105 */             System.out.println(lNumRec1 + " Error length !!! \n" + sM);
/* 101:106 */             PrtSm.println(lNumRec1 + " Error length !!! \n" + sM);
/* 102:    */           }
/* 103:    */           else
/* 104:    */           {
/* 105:108 */             CPDLCRec CPDLCrec = new CPDLCRec();
/* 107:110 */             CPDLCrec.fACTP = Float.parseFloat(sArrM[16]);
/* 108:111 */             CPDLCrec.fACP = Float.parseFloat(sArrM[17]);
/* 109:112 */             CPDLCrec.fPort = Float.parseFloat(sArrM[18]);
/* 110:113 */             if ((CPDLCrec.fACTP < 0.0F) || (CPDLCrec.fACP < 0.0F) || (CPDLCrec.fPort < 0.0F))
/* 111:    */             {
/* 112:115 */               System.out.println(lNumRec1 + " Negative Error !!! \n" + sM);
/* 113:    */               
/* 114:117 */               PrtSm.println(lNumRec1 + " Negative Error !!! \n" + sM);
/* 115:118 */               lNegativeRec += 1L;
/* 116:    */             }
/* 117:    */             else
/* 118:    */             {
/* 119:120 */               if (CPDLCrec.fACTP > 3270.0F) {
/* 120:120 */                 CPDLCrec.fACTP = 3270.0F;
/* 121:    */               }
/* 122:121 */               if (CPDLCrec.fACP > 3270.0F) {
/* 123:121 */                 CPDLCrec.fACP = 3270.0F;
/* 124:    */               }
/* 125:122 */               if (CPDLCrec.fPort > 3270.0F) {
/* 126:122 */                 CPDLCrec.fPort = 3270.0F;
/* 127:    */               }
/* 128:124 */               this.TsATSP.add(sArrM[0]);this.TsSelATSP.add(sArrM[0]);
/* 129:125 */               CPDLCrec.ATSP = sArrM[0];
/* 130:126 */               this.TsACID.add(sArrM[1]);
/* 131:127 */               CPDLCrec.AcID = sArrM[1];
/* 132:128 */               this.TsACTP.add(sArrM[2]);this.TsSelACTP.add(sArrM[2]);
/* 133:    */               
/* 134:    */ 
/* 135:131 */               CPDLCrec.AcType = sArrM[2];
/* 136:132 */               this.TsOper.add(sArrM[3]);this.TsSelOper.add(sArrM[3]);
/* 137:133 */               CPDLCrec.Oper = sArrM[3];
/* 138:    */               
/* 139:    */ 
/* 140:136 */               CPDLCrec.GES_RGS = sArrM[5].trim();
/* 141:137 */               this.TsGrES.add(CPDLCrec.GES_RGS);this.TsSelGrES.add(CPDLCrec.GES_RGS);
/* 142:138 */               String sMonth = sArrM[4].substring(4, 6);
/* 143:139 */               this.TsMont.add(sMonth);this.TsSelMont.add(sMonth);
/* 144:    */               
/* 145:    */ 
/* 146:142 */               CPDLCrec.bMonth = Byte.parseByte(sMonth);
/* 147:149 */               if (bMedTypeM)
/* 148:    */               {
/* 149:151 */                 if (TrMapMedT.containsKey(CPDLCrec.GES_RGS)) {
/* 150:152 */                   CPDLCrec.MediaType = ((String)TrMapMedT.get(CPDLCrec.GES_RGS));
/* 151:    */                 } else {
/* 152:154 */                   CPDLCrec.MediaType = sMedTypeDefault;
/* 153:    */                 }
/* 154:155 */                 if (!CPDLCrec.GES_RGS.equals(sArrM[6].trim()))
/* 155:    */                 {
/* 156:157 */                   String sMedType2 = sArrM[6].trim();
/* 157:158 */                   if (TrMapMedT.containsKey(sMedType2)) {
/* 158:159 */                     sMedType2 = (String)TrMapMedT.get(sMedType2);
/* 159:    */                   } else {
/* 160:161 */                     sMedType2 = sMedTypeDefault;
/* 161:    */                   }
/* 162:162 */                   if (!CPDLCrec.MediaType.equals(sMedType2)) {
/* 163:163 */                     CPDLCrec.MediaType = (CPDLCrec.MediaType.substring(0, 1) + sMedType2.substring(0, 1));
/* 164:    */                   }
/* 165:    */                 }
/* 166:    */               }
/* 167:    */               else
/* 168:    */               {
/* 169:168 */                 CPDLCrec.MediaType = sArrM[19];
/* 170:    */               }
/* 171:169 */               this.TsMeTP.add(CPDLCrec.MediaType);this.TsSelMeTP.add(CPDLCrec.MediaType);
/* 172:170 */               this.hsRecAll.add(CPDLCrec);
/* 173:    */               
/* 174:172 */               this.hsRec = new LinkedHashSet();
/* 175:173 */               if (this.TrMrecFIR.containsKey(CPDLCrec.ATSP)) {
/* 176:174 */                 this.hsRec = ((LinkedHashSet)this.TrMrecFIR.get(CPDLCrec.ATSP));
/* 177:    */               }
/* 178:175 */               this.hsRec.add(CPDLCrec);
/* 179:176 */               this.TrMrecFIR.put(CPDLCrec.ATSP, this.hsRec);
/* 180:    */               
/* 181:178 */               this.hsRec = new LinkedHashSet();
/* 182:179 */               if (this.TrMrecMont.containsKey(sMonth)) {
/* 183:180 */                 this.hsRec = ((LinkedHashSet)this.TrMrecMont.get(sMonth));
/* 184:    */               }
/* 185:181 */               this.hsRec.add(CPDLCrec);
/* 186:182 */               this.TrMrecMont.put(sMonth, this.hsRec);
/* 187:    */               
/* 188:184 */               this.hsRec = new LinkedHashSet();
/* 189:185 */               if (this.TrMrecMeTP.containsKey(CPDLCrec.MediaType)) {
/* 190:186 */                 this.hsRec = ((LinkedHashSet)this.TrMrecMeTP.get(CPDLCrec.MediaType));
/* 191:    */               }
/* 192:187 */               this.hsRec.add(CPDLCrec);
/* 193:188 */               this.TrMrecMeTP.put(CPDLCrec.MediaType, this.hsRec);
/* 194:    */               
/* 195:190 */               this.hsRec = new LinkedHashSet();
/* 196:191 */               if (this.TrMrecACID.containsKey(CPDLCrec.AcID)) {
/* 197:192 */                 this.hsRec = ((LinkedHashSet)this.TrMrecACID.get(CPDLCrec.AcID));
/* 198:    */               }
/* 199:193 */               this.hsRec.add(CPDLCrec);
/* 200:194 */               this.TrMrecACID.put(CPDLCrec.AcID, this.hsRec);
/* 201:    */               
/* 202:196 */               LinkedHashSet hsRec2 = new LinkedHashSet();
/* 203:197 */               if (this.TrMrecOper.containsKey(CPDLCrec.Oper)) {
/* 204:198 */                 hsRec2 = (LinkedHashSet)this.TrMrecOper.get(CPDLCrec.Oper);
/* 205:    */               }
/* 206:199 */               hsRec2.add(CPDLCrec);
/* 207:200 */               this.TrMrecOper.put(CPDLCrec.Oper, hsRec2);
/* 208:    */               
/* 209:202 */               LinkedHashSet hsRec3 = new LinkedHashSet();
/* 210:203 */               if (this.TrMrecACTP.containsKey(CPDLCrec.AcType)) {
/* 211:204 */                 hsRec3 = (LinkedHashSet)this.TrMrecACTP.get(CPDLCrec.AcType);
/* 212:    */               }
/* 213:205 */               hsRec3.add(CPDLCrec);
/* 214:206 */               this.TrMrecACTP.put(CPDLCrec.AcType, hsRec3);
/* 215:    */               
/* 216:208 */               LinkedHashSet hsRec4 = new LinkedHashSet();
/* 217:209 */               if (this.TrMrecGES.containsKey(CPDLCrec.GES_RGS)) {
/* 218:210 */                 hsRec4 = (LinkedHashSet)this.TrMrecGES.get(CPDLCrec.GES_RGS);
/* 219:    */               }
/* 220:211 */               hsRec4.add(CPDLCrec);
/* 221:212 */               this.TrMrecGES.put(CPDLCrec.GES_RGS, hsRec4);
/* 222:    */               
/* 223:    */ 
/* 224:    */ 
/* 225:216 */               Float fMMM = new Float(CPDLCrec.fACTP * 10.0F);
/* 226:217 */               short smmmM = fMMM.shortValue();
/* 227:218 */               int iM = 0;
/* 228:219 */               if (this.TrMhis.containsKey(Short.valueOf(smmmM))) {
/* 229:219 */                 iM = ((Integer)this.TrMhis.get(Short.valueOf(smmmM))).intValue();
/* 230:    */               }
/* 231:220 */               iM++;
/* 232:221 */               this.TrMhis.put(Short.valueOf(smmmM), Integer.valueOf(iM));
/* 233:222 */               fMMM = new Float(CPDLCrec.fACP * 10.0F);
/* 234:223 */               smmmM = fMMM.shortValue();
/* 235:224 */               if (smmmM < 0) {
/* 236:225 */                 System.out.println(lNumRec1 + " Negative Short Error !!! \n" + sM + '\n' + smmmM + "   " + fMMM);
/* 237:    */               }
/* 238:227 */               iM = 0;
/* 239:228 */               if (this.TrMhis2.containsKey(Short.valueOf(smmmM))) {
/* 240:228 */                 iM = ((Integer)this.TrMhis2.get(Short.valueOf(smmmM))).intValue();
/* 241:    */               }
/* 242:229 */               iM++;
/* 243:230 */               this.TrMhis2.put(Short.valueOf(smmmM), Integer.valueOf(iM));
/* 244:231 */               fMMM = new Float(CPDLCrec.fPort * 10.0F);
/* 245:232 */               smmmM = fMMM.shortValue();
/* 246:233 */               iM = 0;
/* 247:234 */               if (this.TrMhis3.containsKey(Short.valueOf(smmmM))) {
/* 248:234 */                 iM = ((Integer)this.TrMhis3.get(Short.valueOf(smmmM))).intValue();
/* 249:    */               }
/* 250:235 */               iM++;
/* 251:236 */               this.TrMhis3.put(Short.valueOf(smmmM), Integer.valueOf(iM));
/* 252:    */             }
/* 253:    */           }
/* 254:    */         }
/* 255:    */       }
/* 256:239 */       PrtSm.flush();PrtSm.close();fOutm.close();
/* 257:240 */       BufR1.close();fIn1.close();
/* 258:241 */       System.out.println("All in 1   " + lNumRec1 + "    Negative   " + lNegativeRec + "     All ATSP = " + this.TsATSP.size() + "     All ACID = " + this.TsACID.size() + "  " + this.TrMrecACID.size() + "     All ACTP = " + this.TsACTP.size() + "     All Oper = " + this.TsOper.size() + "     All Month = " + this.TsMont.size() + "     All GES = " + this.TsGrES.size() + "     All MeTP = " + this.TsMeTP.size());
/* 259:    */       
/* 260:    */ 
/* 261:    */ 
/* 262:    */ 
/* 263:    */ 
/* 264:    */ 
/* 265:    */ 
/* 266:    */ 
/* 267:250 */       System.out.println("\n     All ATSP = " + this.TsATSP.size());
/* 268:251 */       Iterator itM = this.TsATSP.iterator();
/* 269:252 */       while (itM.hasNext()) {
/* 270:253 */         System.out.println(itM.next());
/* 271:    */       }
/* 272:254 */       System.out.println("\n     All ACTP = " + this.TsACTP.size());
/* 273:255 */       itM = this.TsACTP.iterator();
/* 274:256 */       while (itM.hasNext()) {
/* 275:257 */         System.out.println(itM.next());
/* 276:    */       }
/* 277:258 */       System.out.println("\n     All Oper = " + this.TsOper.size());
/* 278:259 */       itM = this.TsOper.iterator();
/* 279:260 */       while (itM.hasNext()) {
/* 280:261 */         System.out.println(itM.next());
/* 281:    */       }
/* 282:262 */       System.out.println("\n     All Month = " + this.TsMont.size());
/* 283:263 */       itM = this.TsMont.iterator();
/* 284:264 */       while (itM.hasNext()) {
/* 285:265 */         System.out.println(itM.next());
/* 286:    */       }
/* 287:266 */       System.out.println("\n     All GES = " + this.TsGrES.size());
/* 288:267 */       itM = this.TsGrES.iterator();
/* 289:268 */       while (itM.hasNext()) {
/* 290:269 */         System.out.println(itM.next());
/* 291:    */       }
/* 292:270 */       System.out.println("\n     All MeTP = " + this.TsMeTP.size());
/* 293:271 */       itM = this.TsMeTP.iterator();
/* 294:272 */       while (itM.hasNext()) {
/* 295:273 */         System.out.println(itM.next());
/* 296:    */       }
/* 297:274 */       System.out.println("\n     All Histogram = " + this.TrMhis.size());
/* 298:275 */       for (Short ikey2 : this.TrMhis.keySet())
/* 299:    */       {
/* 300:277 */         System.out.println(ikey2 + "  - " + this.TrMhis.get(ikey2));
/* 301:278 */         lNumFound += ((Integer)this.TrMhis.get(ikey2)).intValue();
/* 302:    */       }
/* 303:280 */       this.lNumRec = lNumRec1;
/* 304:281 */       System.out.println(new Date());
/* 305:282 */       lFreeM = Runtime.getRuntime().freeMemory();
/* 306:283 */       lTotalM = Runtime.getRuntime().totalMemory();
/* 307:284 */       System.out.println("Free " + lFreeM + "    Total " + lTotalM);
/* 308:285 */       System.out.println("-----------------------------------------");
/* 309:    */     }
/* 310:    */     catch (Exception ExM)
/* 311:    */     {
/* 312:287 */       System.out.println(ExM + " !  " + lNumRec1);
/* 313:288 */       return false;
/* 314:    */     }
/* 315:290 */     return true;
/* 316:    */   }
/* 317:    */   
/* 318:    */   public void ReadFileADSC(String sFileName1)
/* 319:    */   {
/* 320:295 */     ParserType = 1;
/* 321:296 */     long lNumRec1 = 0L;long lNumFoundE = 0L;long lNumFoundN = 0L;
/* 322:    */     
/* 323:298 */     System.out.println(new Date());
/* 324:299 */     long lFreeM = Runtime.getRuntime().freeMemory();
/* 325:300 */     long lTotalM = Runtime.getRuntime().totalMemory();
/* 326:301 */     System.out.println("Free " + lFreeM + "    Total " + lTotalM);
/* 327:302 */     float fMaxTransTime = 0.0F;float fMinTransTime = 9999.0F;
/* 328:303 */     int iMax0 = 0;int iMin0 = 999;int iMax1 = 0;int iMin1 = 999;int iMax2 = 0;int iMin2 = 999;
/* 329:304 */     int iMax3 = 0;int iMin3 = 999;int iMax5 = 0;int iMin5 = 999;int iMax8 = 0;int iMin8 = 999;
/* 330:    */     try
/* 331:    */     {
/* 332:308 */       FileInputStream fIn1 = new FileInputStream(sFileName1);
/* 333:309 */       BufferedReader BufR1 = new BufferedReader(new InputStreamReader(fIn1));
/* 334:312 */       while (BufR1.ready())
/* 335:    */       {
/* 336:314 */         String sM = BufR1.readLine();
/* 337:315 */         if (!sM.startsWith("ATSP,REGNO,ACTYPE,OPER,DATE,"))
/* 338:    */         {
/* 339:317 */           lNumRec1 += 1L;
/* 340:318 */           if (lNumRec1 % 100000L == 0L)
/* 341:    */           {
/* 342:320 */             System.gc();
/* 343:321 */             lFreeM = Runtime.getRuntime().freeMemory();
/* 344:322 */             lTotalM = Runtime.getRuntime().totalMemory();
/* 345:    */             
/* 346:324 */             System.out.format("%n%,10d%7s%,15d%7s%,15d", new Object[] { Long.valueOf(lNumRec1), "Free", Long.valueOf(lFreeM), "Total", Long.valueOf(lTotalM) });
/* 347:    */           }
/* 348:327 */           if (lNumRec1 == 5935453L)
/* 349:    */           {
/* 350:328 */             System.out.println(lNumRec1 + "  " + sM);
/* 351:    */           }
/* 352:    */           else
/* 353:    */           {
/* 354:329 */             String[] sArrM = sM.split(",");
/* 355:    */             
/* 356:    */ 
/* 357:    */ 
/* 358:    */ 
/* 359:    */ 
/* 360:335 */             this.TsMont.add(sArrM[4].substring(4, 6));
/* 361:341 */             if (sArrM.length < 13)
/* 362:    */             {
/* 363:342 */               System.out.println(lNumRec1 + "  " + sM);lNumFoundE += 1L;
/* 364:    */             }
/* 365:344 */             ADSCRec ADSCrec = new ADSCRec();
/* 366:345 */             ADSCRec2 ADSCrec2 = new ADSCRec2();
/* 367:    */             
/* 368:347 */             ADSCrec2.sTransTime = Short.parseShort(sArrM[11].trim());
/* 369:348 */             if (ADSCrec2.sTransTime < 0)
/* 370:    */             {
/* 371:349 */               System.out.println(lNumRec1 + "  " + sM);lNumFoundN += 1L;
/* 372:    */             }
/* 373:356 */             this.TsATSP.add(sArrM[0]);this.TsSelATSP.add(sArrM[0]);
/* 374:357 */             ADSCrec.ATSP = new String(sArrM[0]);
/* 375:358 */             ADSCrec2.sATSP = ((short)sArrM[0].hashCode());
/* 376:    */             
/* 377:    */ 
/* 378:    */ 
/* 379:    */ 
/* 380:363 */             this.TsACID.add(sArrM[1].trim());
/* 381:364 */             ADSCrec.AcID = new String(sArrM[1]);
/* 382:365 */             ADSCrec2.sAcID = ((short)sArrM[1].trim().hashCode());
/* 383:    */             
/* 384:    */ 
/* 385:    */ 
/* 386:    */ 
/* 387:370 */             this.TsACTP.add(sArrM[2]);this.TsSelACTP.add(sArrM[2]);
/* 388:    */             
/* 389:    */ 
/* 390:373 */             ADSCrec.AcType = new String(sArrM[2]);
/* 391:    */             
/* 392:    */ 
/* 393:    */ 
/* 394:    */ 
/* 395:    */ 
/* 396:379 */             ADSCrec2.shAcType = ((short)sArrM[2].hashCode());
/* 397:380 */             this.TsOper.add(sArrM[3]);this.TsSelOper.add(sArrM[3]);
/* 398:381 */             ADSCrec.Oper = new String(sArrM[3]);
/* 399:382 */             ADSCrec2.sOper = ((short)sArrM[3].hashCode());
/* 400:    */             
/* 401:    */ 
/* 402:    */ 
/* 403:    */ 
/* 404:387 */             this.TsGrES.add(sArrM[5].trim());
/* 405:388 */             ADSCrec.GES_RGS = new String(sArrM[5]);
/* 406:389 */             ADSCrec2.sGES_RGS = ((short)sArrM[5].trim().hashCode());
/* 407:    */             
/* 408:    */ 
/* 409:    */ 
/* 410:    */ 
/* 411:394 */             String sMonth = sArrM[4].substring(4, 6);
/* 412:395 */             this.TsMont.add(sMonth);this.TsSelMont.add(sMonth);
/* 413:396 */             this.TsMeTP.add(sArrM[12]);this.TsSelMeTP.add(sArrM[12]);
/* 414:397 */             ADSCrec.MediaType = new String(sArrM[12]);
/* 415:    */             
/* 416:399 */             this.hsRecAll2.add(ADSCrec);
/* 417:    */           }
/* 418:    */         }
/* 419:    */       }
/* 420:451 */       BufR1.close();fIn1.close();
/* 421:452 */       System.out.println("All in 1   " + lNumRec1 + "     All ATSP = " + this.TsATSP.size() + "     All ACID = " + this.TsACID.size() + "     All ACTP = " + this.TsACTP.size() + "     All Oper = " + this.TsOper.size() + "     All Month = " + this.TsMont.size() + "     All GES = " + this.TsGrES.size());
/* 422:    */       
/* 423:    */ 
/* 424:    */ 
/* 425:    */ 
/* 426:    */ 
/* 427:    */ 
/* 428:459 */       System.out.println("\n     fMinTransTime = " + fMinTransTime);
/* 429:460 */       System.out.println("\n     fMaxTransTime = " + fMaxTransTime);
/* 430:461 */       Iterator itM = this.TsATSP.iterator();
/* 431:462 */       while (itM.hasNext()) {
/* 432:463 */         System.out.println(itM.next());
/* 433:    */       }
/* 434:464 */       System.out.println("\n     All ACTP = " + this.TsACTP.size());
/* 435:465 */       itM = this.TsACTP.iterator();
/* 436:466 */       while (itM.hasNext()) {
/* 437:467 */         System.out.println(itM.next());
/* 438:    */       }
/* 439:468 */       System.out.println("\n     All Oper = " + this.TsOper.size());
/* 440:469 */       itM = this.TsOper.iterator();
/* 441:470 */       while (itM.hasNext()) {
/* 442:471 */         System.out.println(itM.next());
/* 443:    */       }
/* 444:472 */       System.out.println("\n     All Month = " + this.TsMont.size());
/* 445:473 */       itM = this.TsMont.iterator();
/* 446:474 */       while (itM.hasNext()) {
/* 447:475 */         System.out.println(itM.next());
/* 448:    */       }
/* 449:476 */       System.out.println("\n     All GES = " + this.TsGrES.size());
/* 450:477 */       itM = this.TsGrES.iterator();
/* 451:478 */       while (itM.hasNext()) {
/* 452:479 */         System.out.println(itM.next());
/* 453:    */       }
/* 454:481 */       System.out.println(new Date());
/* 455:482 */       lFreeM = Runtime.getRuntime().freeMemory();
/* 456:483 */       lTotalM = Runtime.getRuntime().totalMemory();
/* 457:484 */       System.out.println("Free " + lFreeM + "    Total " + lTotalM);
/* 458:485 */       System.out.println("-----------------------------------------");
/* 459:486 */       System.out.println("iMin0 = " + iMin0 + "  iMax0 = " + iMax0);
/* 460:487 */       System.out.println("iMin1 = " + iMin1 + "  iMax1 = " + iMax1);
/* 461:488 */       System.out.println("iMin2 = " + iMin2 + "  iMax2 = " + iMax2);
/* 462:489 */       System.out.println("iMin3 = " + iMin3 + "  iMax3 = " + iMax3);
/* 463:490 */       System.out.println("iMin5 = " + iMin5 + "  iMax5 = " + iMax5);
/* 464:    */     }
/* 465:    */     catch (Exception ExM)
/* 466:    */     {
/* 467:492 */       System.out.println(ExM + " !  " + lNumRec1);ExM.printStackTrace();
/* 468:    */     }
/* 469:    */   }
/* 470:    */   
/* 471:    */   public int GetSizeACTP(String sACTPm)
/* 472:    */   {
/* 473:497 */     int iRet = 0;
/* 474:498 */     LinkedHashSet hsRecACTP = new LinkedHashSet();
/* 475:499 */     hsRecACTP = (LinkedHashSet)this.TrMrecACTP.get(sACTPm);
/* 476:500 */     Iterator<CPDLCRec> itRec = hsRecACTP.iterator();
/* 477:501 */     while (itRec.hasNext())
/* 478:    */     {
/* 479:503 */       CPDLCRec CPDLCrec = (CPDLCRec)itRec.next();
/* 480:504 */       if (checkSelect(Byte.toString(CPDLCrec.bMonth), CPDLCrec.ATSP, CPDLCrec.MediaType, CPDLCrec.GES_RGS, CPDLCrec.Oper, CPDLCrec.AcType)) {
/* 481:508 */         iRet++;
/* 482:    */       }
/* 483:    */     }
/* 484:510 */     return iRet;
/* 485:    */   }
/* 486:    */   
/* 487:    */   public int GetSizeGES(String sGESm)
/* 488:    */   {
/* 489:515 */     int iRet = 0;
/* 490:516 */     LinkedHashSet hsRecGES = new LinkedHashSet();
/* 491:517 */     hsRecGES = (LinkedHashSet)this.TrMrecGES.get(sGESm);
/* 492:518 */     Iterator<CPDLCRec> itRec = hsRecGES.iterator();
/* 493:519 */     while (itRec.hasNext())
/* 494:    */     {
/* 495:521 */       CPDLCRec CPDLCrec = (CPDLCRec)itRec.next();
/* 496:522 */       if (checkSelect(Byte.toString(CPDLCrec.bMonth), CPDLCrec.ATSP, CPDLCrec.MediaType, CPDLCrec.GES_RGS, CPDLCrec.Oper, CPDLCrec.AcType)) {
/* 497:526 */         iRet++;
/* 498:    */       }
/* 499:    */     }
/* 500:528 */     return iRet;
/* 501:    */   }
/* 502:    */   
/* 503:    */   public int GetSizeOper(String sOperm)
/* 504:    */   {
/* 505:533 */     int iRet = 0;
/* 506:534 */     LinkedHashSet hsRecOper = new LinkedHashSet();
/* 507:535 */     hsRecOper = (LinkedHashSet)this.TrMrecOper.get(sOperm);
/* 508:536 */     Iterator<CPDLCRec> itRec = hsRecOper.iterator();
/* 509:537 */     while (itRec.hasNext())
/* 510:    */     {
/* 511:539 */       CPDLCRec CPDLCrec = (CPDLCRec)itRec.next();
/* 512:540 */       if (checkSelect(Byte.toString(CPDLCrec.bMonth), CPDLCrec.ATSP, CPDLCrec.MediaType, CPDLCrec.GES_RGS, CPDLCrec.Oper, CPDLCrec.AcType)) {
/* 513:544 */         iRet++;
/* 514:    */       }
/* 515:    */     }
/* 516:546 */     return iRet;
/* 517:    */   }
/* 518:    */   
/* 519:    */   public int GetSizeAirFr(String sAirFrm)
/* 520:    */   {
/* 521:551 */     int iRet = 0;
/* 522:552 */     LinkedHashSet hsRecAirFr = new LinkedHashSet();
/* 523:553 */     hsRecAirFr = (LinkedHashSet)this.TrMrecACID.get(sAirFrm);
/* 524:554 */     Iterator<CPDLCRec> itRec = hsRecAirFr.iterator();
/* 525:555 */     while (itRec.hasNext())
/* 526:    */     {
/* 527:557 */       CPDLCRec CPDLCrec = (CPDLCRec)itRec.next();
/* 528:558 */       if (checkSelect(Byte.toString(CPDLCrec.bMonth), CPDLCrec.ATSP, CPDLCrec.MediaType, CPDLCrec.GES_RGS, CPDLCrec.Oper, CPDLCrec.AcType)) {
/* 529:562 */         iRet++;
/* 530:    */       }
/* 531:    */     }
/* 532:564 */     return iRet;
/* 533:    */   }
/* 534:    */   
/* 535:    */   public int GetSizeMeTP(String sMeTPm)
/* 536:    */   {
/* 537:569 */     int iRet = 0;
/* 538:570 */     LinkedHashSet hsRecMeTP = new LinkedHashSet();
/* 539:571 */     hsRecMeTP = (LinkedHashSet)this.TrMrecMeTP.get(sMeTPm);
/* 540:572 */     Iterator<CPDLCRec> itRec = hsRecMeTP.iterator();
/* 541:573 */     while (itRec.hasNext())
/* 542:    */     {
/* 543:575 */       CPDLCRec CPDLCrec = (CPDLCRec)itRec.next();
/* 544:576 */       if (checkSelect(Byte.toString(CPDLCrec.bMonth), CPDLCrec.ATSP, CPDLCrec.MediaType, CPDLCrec.GES_RGS, CPDLCrec.Oper, CPDLCrec.AcType)) {
/* 545:580 */         iRet++;
/* 546:    */       }
/* 547:    */     }
/* 548:582 */     return iRet;
/* 549:    */   }
/* 550:    */   
/* 551:    */   public int GetSizeMonth(String sMonthm)
/* 552:    */   {
/* 553:587 */     int iRet = 0;
/* 554:588 */     LinkedHashSet hsRecMeTP = new LinkedHashSet();
/* 555:589 */     hsRecMeTP = (LinkedHashSet)this.TrMrecMont.get(sMonthm);
/* 556:590 */     Iterator<CPDLCRec> itRec = hsRecMeTP.iterator();
/* 557:591 */     while (itRec.hasNext())
/* 558:    */     {
/* 559:593 */       CPDLCRec CPDLCrec = (CPDLCRec)itRec.next();
/* 560:594 */       if (checkSelect(Byte.toString(CPDLCrec.bMonth), CPDLCrec.ATSP, CPDLCrec.MediaType, CPDLCrec.GES_RGS, CPDLCrec.Oper, CPDLCrec.AcType)) {
/* 561:598 */         iRet++;
/* 562:    */       }
/* 563:    */     }
/* 564:600 */     return iRet;
/* 565:    */   }
/* 566:    */   
/* 567:    */   public int GetSizeFIR(String sFIRm)
/* 568:    */   {
/* 569:605 */     int iRet = 0;
/* 570:606 */     LinkedHashSet hsRecFIR = new LinkedHashSet();
/* 571:607 */     hsRecFIR = (LinkedHashSet)this.TrMrecFIR.get(sFIRm);
/* 572:608 */     Iterator<CPDLCRec> itRec = hsRecFIR.iterator();
/* 573:609 */     while (itRec.hasNext())
/* 574:    */     {
/* 575:611 */       CPDLCRec CPDLCrec = (CPDLCRec)itRec.next();
/* 576:612 */       if (checkSelect(Byte.toString(CPDLCrec.bMonth), CPDLCrec.ATSP, CPDLCrec.MediaType, CPDLCrec.GES_RGS, CPDLCrec.Oper, CPDLCrec.AcType)) {
/* 577:616 */         iRet++;
/* 578:    */       }
/* 579:    */     }
/* 580:618 */     return iRet;
/* 581:    */   }
/* 582:    */   
/* 583:    */   public boolean checkSelect(String sMonth, String sFIR, String MediaType, String GES_RGS, String Oper, String AcType)
/* 584:    */   {
/* 585:627 */     if (sMonth.length() == 1) {
/* 586:627 */       sMonth = "0" + sMonth;
/* 587:    */     }
/* 588:628 */     if ((this.TsSelMont.contains(sMonth)) && (this.TsSelMeTP.contains(MediaType)) && (this.TsSelGrES.contains(GES_RGS)) && (this.TsSelOper.contains(Oper)) && (this.TsSelATSP.contains(sFIR)) && (this.TsSelACTP.contains(AcType))) {
/* 589:634 */       return true;
/* 590:    */     }
/* 591:636 */     return false;
/* 592:    */   }
/* 593:    */   
/* 594:    */   public TreeMap<Short, Integer> GetTrMhis()
/* 595:    */   {
/* 596:641 */     return this.TrMhis;
/* 597:    */   }
/* 598:    */   
/* 599:    */   public long GetlNumRec()
/* 600:    */   {
/* 601:646 */     return this.lNumRec;
/* 602:    */   }
/* 603:    */   
/* 604:    */   public static void main(String[] args)
/* 605:    */   {
/* 606:654 */     CPDLCParser CPDLCParserM = new CPDLCParser();
/* 607:    */     
/* 608:    */ 
/* 609:    */ 
/* 610:658 */     CPDLCParserM.ReadFileADSC("D:\\work\\Gold\\ANT_JAN2010MAY2011_GOLD.TXT");
/* 611:    */   }
/* 612:    */ }



/* Location:           F:\2017\00_李晓楠交接\交接文档\数据链质量问题工作交接\GPAT工具备份_cpdlc\GPATv3.jar

 * Qualified Name:     faa.ocean21.CPDLCParser

 * JD-Core Version:    0.7.0.1

 */