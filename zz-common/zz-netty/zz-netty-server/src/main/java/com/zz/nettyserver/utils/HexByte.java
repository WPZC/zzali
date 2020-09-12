package com.zz.nettyserver.utils;

import org.apache.commons.codec.binary.Hex;

import java.text.ParseException;


public class HexByte {
    private static String hexStr =  "0123456789ABCDEF";
    private static String[] binaryArray =
            {"0000","0001","0010","0011",
                    "0100","0101","0110","0111",
                    "1000","1001","1010","1011",
                    "1100","1101","1110","1111"};

    /**
     *
     * @param
     * @return 二进制数组转换为二进制字符串   2-2
     */
    public static String bytes2BinStr(byte[] bArray){

        String outStr = "";
        int pos = 0;
        for(byte b:bArray){
            //高四位
            pos = (b&0xF0)>>4;
            outStr+=binaryArray[pos];
            //低四位
            pos=b&0x0F;
            outStr+=binaryArray[pos];
        }
        return outStr;
    }

    public static String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0) {
            return null;
        }
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }

    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789abcdef".indexOf(c);
        return b;
    }


    /**
     *
     * @param bytes
     * @return 将二进制数组转换为十六进制字符串  2-16
     */
    public static String bin2HexStr(byte[] bytes){

        String result = "";
        String hex = "";
        for(int i=0;i<bytes.length;i++){
            //字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i]&0xF0)>>4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i]&0x0F));
            result +=hex;  //+" "
        }
        return result;
    }

    /**
     *
     * @param hexString
     * @return 将十六进制转换为二进制字节数组   16-2
     */
    public static byte[] hexStr2BinArr(String hexString){
        //hexString的长度对2取整，作为bytes的长度
        int len = hexString.length()/2;
        byte[] bytes = new byte[len];
        byte high = 0;//字节高四位
        byte low = 0;//字节低四位
        for(int i=0;i<len;i++){
            //右移四位得到高位
            high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);
            low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));
            bytes[i] = (byte) (high|low);//高地位做或运算
        }
        return bytes;
    }

    /**
     *
     * @param hexString
     * @return 将十六进制转换为二进制字符串   16-2
     */
    public static String hexStr2BinStr(String hexString){
        return bytes2BinStr(hexStr2BinArr(hexString));
    }


    /**
     * 16进制转换成为string类型字符串
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }






    /**
     * 字符串转换成十六进制字符串
     * @param //String str 待转换的ASCII字符串
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) throws Exception {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes("GBK");
        int bit;

        for (int i = 0; i < bs.length; i++)
        {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);

        }
        return sb.toString().trim().toUpperCase();
    }

    /**
     * 十六进制转换字符串
     * @param  //Byte字符串(Byte之间无分隔符 如:[616C6B])
     * @return String 对应的字符串
     */
    public static String hexStr2Str(String hexStr)
    {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;

        for (int i = 0; i < bytes.length; i++)
        {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * bytes转换成十六进制字符串
     * @param  b byte数组
     * @return String 每个Byte值之间空格分隔
     */
    public static String byte2HexStr(byte[] b)
    {
        String stmp="";
        StringBuilder sb = new StringBuilder("");
        for (int n=0;n<b.length;n++)
        {
            stmp = Integer.toHexString(b[n] & 0xFF);
            sb.append((stmp.length()==1)? "0"+stmp : stmp);
            sb.append("");
        }
        return sb.toString().toUpperCase().trim();
    }

    /**
     * bytes字符串转换为Byte值
     * @param  //src Byte字符串，每个Byte之间没有分隔符
     * @return byte[]
     */
    public static byte[] hexStr2Bytes(String hexStr)
    {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;

    }

    /**
     * String的字符串转换成unicode的String
     * @param  strText 全角字符串
     * @return String 每个unicode之间无分隔符
     * @throws Exception
     */
    public static String strToUnicode(String strText)
            throws Exception
    {
        char c;
        StringBuilder str = new StringBuilder();
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++)
        {
            c = strText.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128) {
                str.append("\\u" + strHex);
            } else // 低位在前面补00
            {
                str.append("\\u00" + strHex);
            }
        }
        return str.toString();
    }

    /**
     * unicode的String转换成String的字符串
     * @param //String hex 16进制值字符串 （一个unicode为2byte）
     * @return String 全角字符串
     */
    public static String unicodeToString(String hex)
    {
        int t = hex.length() / 6;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++)
        {
            String s = hex.substring(i * 6, (i + 1) * 6);
            // 高位需要补上00再转
            String s1 = s.substring(2, 4) + "00";
            // 低位直接转
            String s2 = s.substring(4);
            // 将16进制的string转为int
            int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
            // 将int转换为字符
            char[] chars = Character.toChars(n);
            str.append(new String(chars));
        }
        return str.toString();
    }

    /**
     * 转化时间
     * @param time
     * @return
     */
    public static String hexDataToStr(String time){
        String rs = "";
        for (int i = 0;i<time.length()-2;i=i+2){
            rs += Integer.parseInt(time.substring(i,i+2),16)+"-";
        }
        rs+=Integer.parseInt(time.substring(time.length()-2,time.length()),16);
        return rs;
    }


    /**
     * @功能: BCD码转为10进制串(阿拉伯数据)
     * @参数: BCD码
     * @结果: 10进制串
     */
    public static String bcd2Str(byte[] bytes) {
        StringBuffer temp = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
            temp.append((byte) (bytes[i] & 0x0f));
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
                .toString().substring(1) : temp.toString();
    }

    /**
     * @功能: 10进制串转为BCD码
     * @参数: 10进制串
     * @结果: BCD码
     */
    public static byte[] str2Bcd(String asc) {
        int len = asc.length();
        int mod = len % 2;
        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }
        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }
        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;
        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }
            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }

    /**
     * 十进制转十六
     * @param d
     * @return
     */
    public static String tenToHex(String d,int zj){

        String C = Integer.toHexString(Integer.parseInt(d));
        int w = C.length();
        if (zj == 1){
            if (C.length() == 1){
                C = "0"+C;
            }
        }else if (zj == 2){
            if (C.length()<4){
                for (int i = 0;i<4-w;i++){
                    C = "0"+C;
                }
            }
        }

        return C.toUpperCase();
    }


    public static String tenToHexTime(String time){

        String rs = "";

        for (int i = 0;i<time.length();i+=2){

            if(Integer.toHexString(Integer.parseInt(time.substring(i,i+2))).length() == 2){
                rs += Integer.toHexString(Integer.parseInt(time.substring(i,i+2)));
            }else {
                rs += "0"+Integer.toHexString(Integer.parseInt(time.substring(i,i+2)));
            }


        }

        return rs;
    }

    /**
     * 十进制转二进制
     * 长度
     * @return
     */
    public static String tenToTwo(String msg){
        int d = msg.length()/2;
        int a = Integer.toBinaryString(d).length();
        String c = "";
        for (int i = 0;i<=(10-a);i++){
            c +="0";
        }
        c+=Integer.toBinaryString(d);
        return c;
    }




    public static String twoToHex(String str){
        //System.out.println(str);
        String rs = "";
        String c = Long.toHexString(Long.parseLong(str,2));
        for (int i = 0;i<4-c.length();i++){
            rs += "0";
        }
        rs+=c;
        return rs ;
    };

    public static String binToDword(String str){
        String rs = "";
        String c = Long.toHexString(Long.parseLong(str,2));
        for (int i = 0;i<8-c.length();i++){
            rs += "0";
        }
        rs+=c;
        return rs ;
    };
    public static String binToByte(String str){
        String rs = "";
        String c = Long.toHexString(Long.parseLong(str,2));
        for (int i = 0;i<2-c.length();i++){
            rs += "0";
        }
        rs+=c;
        return rs ;
    };

    public static String lowHigh(int var0) {
        int var1 = 1;
        int var2 = var0 >> 8;
        int var3 = var0 & 255;
        String var4 = Integer.toHexString(var2);
        String var5 = Integer.toHexString(var3);
        if(var4.length() > 2) {
            do {
                if(var1 > 1) {
                    var2 >>= 8;
                }
                var4 = Integer.toHexString(var2 >> 8);
                var5 = var5 + Integer.toHexString(var2 & 255);
                ++var1;
            } while(var4.length() > 2);
        }
        if(var4.length() < 2) {
            var4 = "0" + var4;
        }
        if(var5.length() < 2) {
            var5 = "0" + var5;
        }
        return var5 + var4;
    }

    /**
     * 将byte数组中的元素倒序排列 
     */
    public static byte[] bytesReverseOrder(byte[] b,int j,int size){
        byte[] result = new byte[size];
        int z=0;
        for(int i=j*size; i<(j+1)*size; i++) {
            if(i>=b.length){
                result[z++]=00;
            }else{
                result[z++]=b[i];
            }
        }
        return result;
    }

    /**
     * 十六进制字符串转十进制
     * @param hexStr
     * @return
     */
    public static String hexStrToIntStr(String hexStr){

        return Integer.parseInt(hexStr,16)+"";
    }

    public static long dwordBytesToLong(byte[] data) {
        return (data[3] << 8 * 3) + (data[2] << 8 * 2) + (data[1] << 8)
                + data[0];
    }

    public static byte[] longToDword(long value) {
        byte[] data = new byte[4];

        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (value >> (8 * i));
        }
        return data;
    }

    public static String hexToBinStr(String hexStr){

        byte[] s = hexStr2Bytes(hexStr);

        String rs = "";
        String bl = "";
        for (int i = 0;i<s.length;i++){
            bl = Integer.toBinaryString(s[i]);
            if(bl.length()<8){
                for (int y = 0;y<8-bl.length();){
                    bl = "0"+bl;
                }
            }
            rs += bl;
        }


        return rs;
    }

    public static String hexToInt(String hexstr){
        return ""+Integer.parseInt(hexstr,16);
    }

    /**
     * 将Hex编码字符串转换成普通字符串
     *
     * @param dataCoding 反编码格式，15表示GBK编码，8表示UnicodeBigUnmarked编码，0表示ISO8859-1编码
     * @param hexStr Hex编码字符串
     * @return 普通字符串
     */
    public static String decodeHexStr(int dataCoding, String hexStr) {
        String realStr = null;
        try {
            if (hexStr != null) {
                if (dataCoding == 15) {
                    realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "GBK");
                } else if ((dataCoding & 0x0C) == 0x08) {
                    realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "UnicodeBigUnmarked");
                } else if(dataCoding == 11){
                    realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "ISO8859-1");
                } else if(dataCoding == 12){
                    realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "UTF-8");

                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return realStr;
    }



    /**
     *
     * @param str 输入需要转的字符串
     * @param type 字符串类型（例，hexstr）
     * @return
     */
    public static String characterAi(String str,String type){

        String rs = "";
        int p = 0;

        switch (type){
            case "WORDTOINT"://hex word转str int
                rs = hexStrToIntStr(str);
                break;
            case "WORDTOBIN"://WORDTOBIN
                rs = hexString2binaryString(str);
                break;
            case "DWORDTOBIN"://hex dword转str bin 十六进制转二进制
                //rs = hexToBinStr(str);
                rs = Integer.toBinaryString(Integer.parseInt(str,16));
                if(rs.length()<32){
                    for (int i = 0,num = 32-rs.length();i<num;i++){
                        rs = "0"+rs;
                    }
                }
                break;
            case "BYTETOINT":
                rs = hexToInt(str);
                break;
            case "HEXTOSTR":
                rs = decodeHexStr(15,str);
                break;
            case "STRTOHEX"://GBK
                try {
                    rs = str2HexStr(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "INTTOHEX":
                rs = Integer.toHexString(Integer.parseInt(str)).toUpperCase();
                if(rs.length()==1){
                    rs="0"+rs;
                }
                break;
            case "BINTOHEX":
                rs = twoToHex(str);
                break;
            case "BINTODWORD":
                rs = binToDword(str);
                break;
            case "BINTOBYTE":
                rs = binToDword(str);
                break;
            case "INTTODWORD":
                rs = Integer.toHexString(Integer.parseInt(str));
                p = rs.length();
                for (int i = 0;i<8-p;i++){
                    rs = "0"+rs;
                }
                break;
            case "LONGTODWORD":
                rs = Long.toHexString(new Double(Double.parseDouble(str)).longValue());
                p = rs.length();
                for (int i = 0;i<8-p;i++){
                    rs = "0"+rs;
                }
                break;
            case "INTTOWORD":
                rs = Integer.toHexString(Integer.parseInt(str));
                p = rs.length();
                for (int i = 0;i<4-p;i++){
                    rs = "0"+rs;
                }
                break;
            case "INTTOBYTE":
                rs = Integer.toHexString(Integer.parseInt(str));
                p = rs.length();
                for (int i = 0;i<2-p;i++){
                    rs = "0"+rs;
                }
                break;
            case "DWORDTOLONG":
                rs =Long.parseLong(str,16)+"";
                break;
            default:


        }
        return rs.toUpperCase();
    }



    public static void main(String[] args) throws ParseException {
//        String str = Double.parseDouble("39.23456")*1000000+"";//"420621003010800079";
//        String str2 = "013B434D4E45543B3B3B3139322E3136382E312E3130333B17703B3B312E30333B312E30303B0E10";
//
//
//        String qssj = "2019-04-23 15:08:45";
//          22257
//        qssj = qssj.replace("-","");
//        qssj = qssj.replace(":","");
//        qssj = qssj.replace(" ","");
//        qssj = qssj.substring(2);
        //7E00033232323537005709191221172423010200AB02020089030200770504419EF5C3060441428F5C070203E80804000000000902014F0A044281CCCD0C04000000000D04000000000E04000000000F0400000000507E
        //3139322E3136382E312E32
        try {
            System.out.println(characterAi("22257","STRTOHEX"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
