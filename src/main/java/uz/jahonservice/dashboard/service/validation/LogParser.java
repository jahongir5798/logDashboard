package uz.jahonservice.dashboard.service.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uz.jahonservice.dashboard.entity.LogEntity;
import uz.jahonservice.dashboard.exception.MyException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Component
public class LogParser {

    public  Map<String, Object> parseLog(String log) {
        LogEntity logEntity = new LogEntity();
        Map<String, Object> result = new LinkedHashMap<>();

        String regex = "(\\w+)=((\"[^\"]*\")|[^\\s\"]+)";
        var pattern = java.util.regex.Pattern.compile(regex);
        var matcher = pattern.matcher(log);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        while (matcher.find()) {
            String key = matcher.group(1);
            String rawValue = matcher.group(2);

            if (rawValue.startsWith("\"") && rawValue.endsWith("\"")) {
                rawValue = rawValue.substring(1, rawValue.length() - 1);
            }

            Object value;

            switch (key) {
                case "date":
                    value = LocalDate.parse(rawValue, dateFormatter);
                    break;
                case "time":
                    value = LocalTime.parse(rawValue, timeFormatter);
                    break;
                case "eventtime":
                case "srcport":
                case "dstport":
                case "transport":
                case "duration":
                case "sentbyte":
                case "rcvdbyte":
                case "sentpkt":
                case "rcvdpkt":
                case "sessionid":
                case "identifier":
                case "proto":
                case "policyid":
                case "srcserver":
                    try {
                        value = Long.parseLong(rawValue);
                    } catch (NumberFormatException e) {
                        value = rawValue;
                    }
                    break;
                default:
                    value = rawValue;
                    break;
            }

            result.put(key, value);
        }

        return result;
    }

    public LogEntity toLogEntity(Map<String, Object> keyValueLog, LogEntity logEntity) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        try {

            logEntity.setDate(LocalDate.parse((keyValueLog.getOrDefault("date", "null")).toString(), dateFormatter));
            logEntity.setTime(LocalTime.parse((keyValueLog.getOrDefault("time", "null")).toString(), timeFormatter));
            logEntity.setEventTime(keyValueLog.containsKey("eventtime") ? Long.parseLong(keyValueLog.get("eventtime").toString()) : null);
            logEntity.setTz(keyValueLog.getOrDefault("tz", "null").toString());
            logEntity.setLogId(keyValueLog.getOrDefault("logid", "null").toString());
            logEntity.setType(keyValueLog.getOrDefault("type", "null").toString());
            logEntity.setSubType(keyValueLog.getOrDefault("subtype", "null").toString());
            logEntity.setLevel(keyValueLog.getOrDefault("level", "null").toString());

            logEntity.setVd(keyValueLog.getOrDefault("vd", "null").toString());
            logEntity.setIdentifier(keyValueLog.containsKey("identifier") ? Long.parseLong(keyValueLog.get("identifier").toString()) : null);
            logEntity.setSrcIP(keyValueLog.getOrDefault("srcip", "null").toString());
            logEntity.setSrcName(keyValueLog.getOrDefault("srcname", "null").toString());
            logEntity.setSrcPort(keyValueLog.containsKey("srcport") ? Long.parseLong(keyValueLog.get("srcport").toString()) : null);
            logEntity.setSrcIntf(keyValueLog.getOrDefault("srcintf", "null").toString());
            logEntity.setSrcIntfRole(keyValueLog.getOrDefault("srcintfrole", "null").toString());
            logEntity.setDstIP(keyValueLog.getOrDefault("dstip", "null").toString());
            logEntity.setDstPort(keyValueLog.containsKey("dstport") ? Long.parseLong(keyValueLog.get("dstport").toString()) : null);
            logEntity.setDstIntf(keyValueLog.getOrDefault("dstintf", "null").toString());
            logEntity.setDstIntfRole(keyValueLog.getOrDefault("dstintfrole", "null").toString());
            logEntity.setSrcCountry(keyValueLog.getOrDefault("srccountry", "null").toString());
            logEntity.setDstCountry(keyValueLog.getOrDefault("dstcountry", "null").toString());

            logEntity.setSessionId(keyValueLog.containsKey("sessionid") ? Long.parseLong(keyValueLog.get("sessionid").toString()) : null);
            logEntity.setProto(keyValueLog.containsKey("proto") ? Long.parseLong(keyValueLog.get("proto").toString()) : null);
            logEntity.setAction(keyValueLog.getOrDefault("action", "null").toString());
            logEntity.setPolicyId(keyValueLog.containsKey("policyid") ? Long.parseLong(keyValueLog.get("policyid").toString()) : null);
            logEntity.setPolicyType(keyValueLog.getOrDefault("policytype", "null").toString());
            logEntity.setPolUUID(keyValueLog.getOrDefault("poluuid", "null").toString());
            logEntity.setPolicyName(keyValueLog.getOrDefault("policyname", "null").toString());
            logEntity.setService(keyValueLog.getOrDefault("service", "null").toString());

            logEntity.setTranDisP(keyValueLog.getOrDefault("trandisp", "null").toString());
            logEntity.setTransIP(keyValueLog.getOrDefault("transip", "null").toString());
            logEntity.setTransport(keyValueLog.containsKey("transport") ? Long.parseLong(keyValueLog.get("transport").toString()) : null);
            logEntity.setDuration(keyValueLog.containsKey("duration") ? Long.parseLong(keyValueLog.get("duration").toString()) : null);
            logEntity.setSentByte(keyValueLog.containsKey("sentbyte") ? Long.parseLong(keyValueLog.get("sentbyte").toString()) : null);
            logEntity.setRcvdbyte(keyValueLog.containsKey("rcvdbyte") ? Long.parseLong(keyValueLog.get("rcvdbyte").toString()) : null);
            logEntity.setSentPkt(keyValueLog.containsKey("sentpkt") ? Long.parseLong(keyValueLog.get("sentpkt").toString()) : null);
            logEntity.setRcvdPkt(keyValueLog.containsKey("rcvdpkt") ? Long.parseLong(keyValueLog.get("rcvdpkt").toString()) : null);

            logEntity.setAppCat(keyValueLog.getOrDefault("appcat", "null").toString());
            logEntity.setUtmAction(keyValueLog.getOrDefault("utmaction", "null").toString());
            logEntity.setCountWeb(keyValueLog.containsKey("countweb") ? Long.parseLong(keyValueLog.get("countweb").toString()) : null);
            logEntity.setSrchwVendor(keyValueLog.getOrDefault("srchwvendor", "null").toString());
            logEntity.setOsName(keyValueLog.getOrDefault("osname", "null").toString());
            logEntity.setSrcswVersion(keyValueLog.getOrDefault("srcswversion", "null").toString());
            logEntity.setMasterSrcMac(keyValueLog.getOrDefault("mastersrcmac", "null").toString());
            logEntity.setSrcMac(keyValueLog.getOrDefault("srcmac", "null").toString());
            logEntity.setSrcserver(keyValueLog.containsKey("srcserver") ? Long.parseLong(keyValueLog.get("srcserver").toString()) : null);

        }catch (Exception e){
            throw new MyException(e.getMessage() + "nul pointer otgan bulsa LOng tiplilarni joylashda muammo bor ");
        }

        return logEntity;
    }


//    public static void main(String[] args) {
//        String log = "date=2025-06-25 time=11:46:41 eventtime=1750834001819166243 tz=\"+0500\" logid=\"0000000013\" type=\"traffic\" subtype=\"forward\" level=\"notice\" vd=\"root\" srcip=192.168.10.2 srcname=\"DESKTOP-PV8UUES\" srcport=52189 srcintf=\"port1\" srcintfrole=\"lan\" dstip=213.180.204.63 dstport=443 dstintf=\"port3\" dstintfrole=\"wan\" srccountry=\"Reserved\" dstcountry=\"Russian Federation\" sessionid=161 proto=6 action=\"client-rst\" policyid=1 policytype=\"policy\" poluuid=\"dfcea2d4-4fed-51f0-c05e-b22d53224d20\" policyname=\"internet\" service=\"HTTPS\" trandisp=\"snat\" transip=192.168.186.135 transport=52189 duration=5 sentbyte=1385 rcvdbyte=5072 sentpkt=9 rcvdpkt=10 appcat=\"unscanned\" srchwvendor=\"VMware\" osname=\"Windows\" srcswversion=\"10 / 2016\" mastersrcmac=\"00:0c:29:fc:44:8c\" srcmac=\"00:0c:29:fc:44:8c\" srcserver=0";
//
//        Map<String, Object> parsedLog = parseLog(log);
//
////        parsedLog.forEach((k, v) -> System.out.println(k + " = " + v + " (" + v.getClass().getSimpleName() + ")"));
//
//        String str = parsedLog.containsKey("date") ? parsedLog.get("date").toString() : "topilmadi";
//        System.out.println(str);
//
//    }
}
