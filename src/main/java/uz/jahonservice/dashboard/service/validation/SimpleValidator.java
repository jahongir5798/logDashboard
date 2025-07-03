package uz.jahonservice.dashboard.service.validation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SimpleValidator {
    public static void main(String[] args) {
       /* String log1 = "date=2025-06-27 time=14:24:06 eventtime=1751016240480258276 tz=\"+0500\" logid=\"0000000013\" type=\"traffic\" subtype=\"forward\" level=\"notice\" vd=\"root\" identifier=0 srcintf=\"unknown-0\" srcintfrole=\"undefined\" dstip=ff02::16 dstintf=\"unknown-0\" dstintfrole=\"undefined\" sessionid=33 proto=58 action=\"accept\" policyid=0 policytype=\"policy\" service=\"icmp6/143/0\" trandisp=\"noop\" duration=0 sentbyte=0 rcvdbyte=0 sentpkt=0 rcvdpkt=0 appcat=\"unscanned\"";

        String log2 = "date=2025-06-25 time=11:47:33 eventtime=1750834053778841516 tz=\"+0500\" logid=\"0000000013\" type=\"traffic\" subtype=\"forward\" level=\"notice\" vd=\"root\" srcip=192.168.10.2 srcname=\"DESKTOP-PV8UUES\" srcport=56479 srcintf=\"port1\" srcintfrole=\"lan\" dstip=23.197.105.167 dstport=80 dstintf=\"port3\" dstintfrole=\"wan\" srccountry=\"Reserved\" dstcountry=\"United States\" sessionid=252 proto=6 action=\"server-rst\" policyid=1 policytype=\"policy\" poluuid=\"dfcea2d4-4fed-51f0-c05e-b22d53224d20\" policyname=\"internet\" service=\"HTTP\" trandisp=\"snat\" transip=192.168.186.135 transport=56479 duration=38 sentbyte=252 rcvdbyte=14989 sentpkt=6 rcvdpkt=15 appcat=\"unscanned\" utmaction=\"block\" countweb=1 srchwvendor=\"VMware\" osname=\"Windows\" srcswversion=\"10 / 2016\" mastersrcmac=\"00:0c:29:fc:44:8c\" srcmac=\"00:0c:29:fc:44:8c\" srcserver=0 utmref=65535-56";

        String log3 = "date=2025-06-25 time=11:47:33 eventtime=1750834053689540372 tz=\"+0500\" logid=\"0000000013\" type=\"traffic\" subtype=\"forward\" level=\"notice\" vd=\"root\" srcip=192.168.10.2 srcname=\"DESKTOP-PV8UUES\" srcport=56483 srcintf=\"port1\" srcintfrole=\"lan\" dstip=185.211.229.17 dstport=80 dstintf=\"port3\" dstintfrole=\"wan\" srccountry=\"Reserved\" dstcountry=\"Kyrgyzstan\" sessionid=264 proto=6 action=\"client-rst\" policyid=1 policytype=\"policy\" poluuid=\"dfcea2d4-4fed-51f0-c05e-b22d53224d20\" policyname=\"internet\" service=\"HTTP\" trandisp=\"snat\" transip=192.168.186.135 transport=56483 duration=22 sentbyte=132 rcvdbyte=88 sentpkt=3 rcvdpkt=2 appcat=\"unscanned\" srchwvendor=\"VMware\" osname=\"Windows\" srcswversion=\"10 / 2016\" mastersrcmac=\"00:0c:29:fc:44:8c\" srcmac=\"00:0c:29:fc:44:8c\" srcserver=0";

        String log4 = "date=2025-06-25 time=11:47:33 eventtime=1750834052998950901 tz=\"+0500\" logid=\"0000000013\" type=\"traffic\" subtype=\"forward\" level=\"notice\" vd=\"root\" srcip=192.168.10.2 srcname=\"DESKTOP-PV8UUES\" srcport=56852 srcintf=\"port1\" srcintfrole=\"lan\" dstip=20.42.73.27 dstport=443 dstintf=\"port3\" dstintfrole=\"wan\" srccountry=\"Reserved\" dstcountry=\"United States\" sessionid=101 proto=6 action=\"close\" policyid=1 policytype=\"policy\" poluuid=\"dfcea2d4-4fed-51f0-c05e-b22d53224d20\" policyname=\"internet\" service=\"HTTPS\" trandisp=\"snat\" transip=192.168.186.135 transport=56852 duration=98 sentbyte=3196 rcvdbyte=16219 sentpkt=20 rcvdpkt=28 appcat=\"unscanned\" srchwvendor=\"VMware\" osname=\"Windows\" srcswversion=\"10 / 2016\" mastersrcmac=\"00:0c:29:fc:44:8c\" srcmac=\"00:0c:29:fc:44:8c\" srcserver=0";

        String log5 = "date=2025-06-25 time=16:45:05 eventtime=1750851905760853511 tz=\"+0500\" logid=\"0000000013\" type=\"traffic\" subtype=\"forward\" level=\"notice\" vd=\"root\" identifier=0 srcintf=\"unknown-0\" srcintfrole=\"undefined\" dstip=ff02::16 dstintf=\"unknown-0\" dstintfrole=\"undefined\" sessionid=33 proto=58 action=\"accept\" policyid=0 policytype=\"policy\" service=\"icmp6/143/0\" trandisp=\"noop\" duration=0 sentbyte=0 rcvdbyte=0 sentpkt=0 rcvdpkt=0 appcat=\"unscanned\"";

//         String[] split = text.split(" (?=\\w+\\=)");

        String []split1 = log1.split(" (?=\\w+\\=)");
        String []split2 = log2.split(" (?=\\w+\\=)");
        String []split3 = log3.split(" (?=\\w+\\=)");
        String []split4 = log4.split(" (?=\\w+\\=)");
        String []split5 = log5.split(" (?=\\w+\\=)");


        for (String s : split3) {
            System.out.println(s);
        }

        System.out.println(split1.length);
        System.out.println(split2.length);
        System.out.println(split3.length);
        System.out.println(split4.length);
        System.out.println(split5.length);*/
         String filePath = "\"C:\\1jahon\\darslar\\markaz amaliyot\\disk-traffic-forward-2025-06-27_1020.log\"";

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            List<String> logs = new ArrayList<>();

            StringBuilder currentLog = new StringBuilder();

            for (String line : lines) {
                if (line.startsWith("date=")) {
                    if (currentLog.length() > 0) {
                        logs.add(currentLog.toString());
                        currentLog.setLength(0);
                    }
                }
                currentLog.append(line).append(" ");
            }

            // So‘nggi logni ham qo‘shamiz
            if (currentLog.length() > 0) {
                logs.add(currentLog.toString());
            }

            // Natijani chiqarish
            for (int i = 0; i < logs.size(); i++) {
                System.out.println("Log #" + (i + 1));
                System.out.println(logs.get(i));
                System.out.println("----------------------------------------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
