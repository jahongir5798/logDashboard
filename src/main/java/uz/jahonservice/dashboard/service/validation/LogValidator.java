package uz.jahonservice.dashboard.service.validation;

import org.springframework.stereotype.Component;
import uz.jahonservice.dashboard.entity.LogEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class LogValidator {

   /* public static void main(String[] args) {
        String text = "date=2025-06-25" +
              1  "time=11:47:33 " +
              2  "eventtime=1750834052959030566 " +
              3  "tz=\"+0500\" " +
              4  "logid=\"0000000013\" " +
              5  "type=\"traffic\" " +
              6  "subtype=\"forward\" " +
              7  "level=\"notice\" " +
              8  "vd=\"root\" " +
              9  "srcip=192.168.10.2 " +
              10  "srcname=\"DESKTOP-PV8UUES\" " +
              11  "srcport=60104 " +
             12   "srcintf=\"port1\" " +
             13   "srcintfrole=\"lan\" " +
             14   "dstip=20.42.65.88 " +
             15   "dstport=443 " +
             16   "dstintf=\"port3\" " +
             17   "dstintfrole=\"wan\" " +
             18   "srccountry=\"Reserved\" " +
             19   "dstcountry=\"United States\" " +
             20   "sessionid=159 " +
             21   "proto=6 " +
             22   "action=\"close\" " +
             23   "policyid=1 " +
             24   "policytype=\"policy\" " +
             25   "poluuid=\"dfcea2d4-4fed-51f0-c05e-b22d53224d20\" " +
             26   "policyname=\"internet\" " +
             27   "service=\"HTTPS\" " +
             28   "trandisp=\"snat\" " +
             29   "transip=192.168.186.135 " +
             30   "transport=60104 " +
             31   "duration=57 " +
             32   "sentbyte=113550 " +
             33   "rcvdbyte=13734 " +
             34   "sentpkt=101 " +
             35   "rcvdpkt=134 " +
             36   "appcat=\"unscanned\" " +
             37   "srchwvendor=\"VMware\" " +
             38   "osname=\"Windows\" " +
             39   "srcswversion=\"10 / 2016\" " +
             40   "mastersrcmac=\"00:0c:29:fc:44:8c\" " +
             41   "srcmac=\"00:0c:29:fc:44:8c\" " +
             42   "srcserver=0";

        String text = "date=2025-06-25 time=11:47:33 eventtime=1750834052959030566 tz=\"+0500\" logid=\"0000000013\" type=\"traffic\" subtype=\"forward\" level=\"notice\" vd=\"root\" srcip=192.168.10.2 srcname=\"DESKTOP-PV8UUES\" srcport=60104 srcintf=\"port1\" srcintfrole=\"lan\" dstip=20.42.65.88 dstport=443 dstintf=\"port3\" dstintfrole=\"wan\" srccountry=\"Reserved\" dstcountry=\"United States\" sessionid=159 proto=6 action=\"close\" policyid=1 policytype=\"policy\" poluuid=\"dfcea2d4-4fed-51f0-c05e-b22d53224d20\" policyname=\"internet\" service=\"HTTPS\" trandisp=\"snat\" transip=192.168.186.135 transport=60104 duration=57 sentbyte=113550 rcvdbyte=13734 sentpkt=101 rcvdpkt=134 appcat=\"unscanned\" srchwvendor=\"VMware\" osname=\"Windows\" srcswversion=\"10 / 2016\" mastersrcmac=\"00:0c:29:fc:44:8c\" srcmac=\"00:0c:29:fc:44:8c\" srcserver=0";
       String text1 = "date=2025-06-25 time=11:47:22 eventtime=1750834042699548370 tz="+0500" logid="0000000013" type="traffic" subtype="forward" level="notice" vd="root" srcip=192.168.10.2 srcname="DESKTOP-PV8UUES" srcport=56862 srcintf="port1" srcintfrole="lan" dstip=23.197.105.167 dstport=80 dstintf="port3" dstintfrole="wan" srccountry="Reserved" dstcountry="United States" sessionid=129 proto=6 action="close" policyid=1 policytype="policy" poluuid="dfcea2d4-4fed-51f0-c05e-b22d53224d20" policyname="internet" service="HTTP" trandisp="snat" transip=192.168.186.135 transport=56862 duration=54 sentbyte=172 rcvdbyte=5087 sentpkt=4 rcvdpkt=7 appcat="unscanned" utmaction="block" countweb=1 srchwvendor="VMware" osname="Windows" srcswversion="10 / 2016" mastersrcmac="00:0c:29:fc:44:8c" srcmac="00:0c:29:fc:44:8c" srcserver=0 utmref=65535-42"
    }*/

    public LogEntity textToLogEntity(LogEntity logEntity, String text) {

        String[] split = text.split(" (?=\\w+\\=)");
        //todo: date joylandi index 0 -> LocalDate
        logEntity.setDate(LocalDate.parse(split[0].substring(split[0].indexOf("=") + 1)));

        //todo: time joylandi index 1 -> LocalTime
        logEntity.setTime( LocalTime.parse(split[1].substring(split[1].indexOf("=") + 1)));

        //todo: eventTime joylandi index 2 -> Long
        logEntity.setEventTime(extractInteger(split[2]));

        //todo: time zone (tz) joylandi index 3 -> string
            logEntity.setTz(extractString(split[3]));

        //todo: log id joylandi index 4 -> string
        logEntity.setLogId(extractString(split[4]));

        //todo: type joylandi index 5 -> string
        logEntity.setType(extractString(split[5]));

        //todo: sub type joylandi index 6 -> string
        logEntity.setSubType(extractString(split[6]));

        //todo: level joylandi index 7 -> string
        logEntity.setLevel(extractString(split[7]));

        //todo: virtual domen (vd) joylandi index 8 -> string
        logEntity.setVd(extractString(split[8]));

        //todo: source id (srcip) joylandi index 9 -> string
        logEntity.setSrcIP(extractStringIP(split[9]));

        //todo: source name (srcname) joylandi index 10 -> string
        logEntity.setSrcName(extractString(split[10]));

        //todo: source port (srcport) joylandi index 11 -> long
        logEntity.setSrcPort(extractInteger(split[11]));

        //todo: source interface (srcintf) joylandi index 12 -> string
        logEntity.setSrcIntf(extractString(split[12]));

        //todo: source interface role (srcinntrole) joylandi index 13 -> string
        logEntity.setSrcIntfRole(extractString(split[13]));

        //todo: destination ip (dstip) joylandi index 14 -> string
        logEntity.setDstIP(extractStringIP(split[14]));

        //todo: destination port (dstport) joytlandi index 15 ->long
        logEntity.setDstPort(extractInteger(split[15]));

        //todo: dst interface joylandi index 16 -> string
        logEntity.setDstIntf(extractString(split[16]));

        //todo: dst interface role joylandi index 17 -> string
        logEntity.setDstIntfRole(extractString(split[17]));

        //todo: sourse country joylandi index 18 -> string
        logEntity.setSrcCountry(extractString(split[18]));

        //todo: destination country index 19 -> string
        logEntity.setDstCountry(extractString(split[19]));

        //todo: session id joylandi index 20 -> long
        logEntity.setSessionId(extractInteger(split[20]));

        //todo: protocol raqami joylandi index 21 -> long
        logEntity.setProto(extractInteger(split[21]));

        //todo: action joylandi index 22 -> string
        logEntity.setAction(extractString(split[22]));

        //todo: policy id joylandi index 23 -> long
        logEntity.setPolicyId(extractInteger(split[23]));

        //todo: policy type joylandi index 24 ->string
        logEntity.setPolicyType(extractString(split[24]));

        //todo: policy uuid joylandi index 25 -> uuid
        logEntity.setPolUUID(extractString(split[25]));

        //todo: policy name joylandi index 26 -> string
        logEntity.setPolicyName(extractString(split[26]));

        //todo: service joylandi index 27 -> string
        logEntity.setService(extractString(split[27]));

        //todo: transdisp joylandi index 28 -> string
        logEntity.setTranDisP(extractString(split[28]));

        //todo: transip (transilatsiya qilingan ip) joylandi index 29 -> string
        logEntity.setTransIP(extractString(split[29]));

        //todo: transport joylandi index 30 -> long
        logEntity.setTransport(extractInteger(split[30]));

        //todo: davomiylik joylandi index 31 -> long
        logEntity.setDuration(extractInteger(split[31]));

        //todo: junatilgan baytlar joylandi index 32 -> long
        logEntity.setSentByte(extractInteger(split[32]));

        //todo: qabul qilingan baytlar index 33 -> long
        logEntity.setRcvdbyte(extractInteger(split[33]));

        //todo: yuborilgan paketlar soni index 34 -> long
        logEntity.setSentPkt(extractInteger(split[34]));

        //todo: qabul  qilingan paketlar soni index 35 -> long
        logEntity.setRcvdPkt(extractInteger(split[35]));

        //todo: application category joylandi index 36 -> string
        logEntity.setAppCat(extractString(split[36]));

        //todo: qurilma ishlab chiqaruvchisi index 37 -> string
        logEntity.setSrchwVendor(extractString(split[37]));

        //todo:  operation system name joylandi index 38
        logEntity.setOsName(extractString(split[38]));

        //todo: qurilma versiyasi joylandi index 39
        logEntity.setSrcswVersion(extractString(split[39]));

        //todo: master sourse mac joylandi index 40
        logEntity.setMasterSrcMac(extractString(split[40]));

        //todo: sourse mac joylandi index 41
        logEntity.setSrcMac(extractString(split[41]));

        //todo: src server joylandi index 42
        logEntity.setSrcserver(extractInteger(split[42]));

        return logEntity;
    }

    private Long extractInteger(String str){
        return Long.parseLong(str.substring(str.indexOf("=") + 1));
    }

    private static String extractString(String str){
        return str.substring(str.indexOf("=") + 2, str.length() - 1);
    }

     private static String extractStringIP(String str){
        return str.substring(str.indexOf("=") + 1);
    }
}
